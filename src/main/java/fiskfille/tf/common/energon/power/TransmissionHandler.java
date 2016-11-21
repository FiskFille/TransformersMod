package fiskfille.tf.common.energon.power;

import com.google.common.collect.Lists;
import fiskfille.tf.helper.TFEnergyHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Handles all receivers this transmitter is transmitting to
 */
public class TransmissionHandler
{
    private Set<ChunkCoordinates> receiverCoords = new HashSet<ChunkCoordinates>();
    private List<TileEntity> receivers = Lists.newArrayList();
    private TileEntity owner;
    private IEnergyContainer energyContainer;

    private Queue<ChunkCoordinates> queuedReceivers = new ArrayDeque<ChunkCoordinates>();

    public TransmissionHandler(TileEntity owner)
    {
        this.owner = owner;
        this.energyContainer = (IEnergyContainer) owner;
    }

    public void onUpdate(World world)
    {
        if (!world.isRemote)
        {
            for (Iterator<TileEntity> iterator = this.receivers.iterator(); iterator.hasNext(); )
            {
                TileEntity receiverTile = iterator.next();

                if (receiverTile instanceof IEnergyReceiver)
                {
                    IEnergyReceiver receiver = (IEnergyReceiver) receiverTile;

                    boolean invalid = world.getChunkProvider().chunkExists(receiverTile.xCoord >> 4, receiverTile.zCoord >> 4) && (receiverTile.isInvalid() || !exists(world, receiverTile));
                    boolean outRange = !(TFEnergyHelper.isInRange(owner, receiverTile) && (receiverTile instanceof IEnergyTransmitter || receiver.getEnergy() < receiver.getMaxEnergy()));
                    boolean destroyed = !invalid && !exists(world, receiverTile);

                    if (invalid || outRange || destroyed)
                    {
                        iterator.remove();
                        this.receiverCoords.remove(new ChunkCoordinates(receiverTile.xCoord, receiverTile.yCoord, receiverTile.zCoord));
                        this.owner.markDirty();
                        this.energyContainer.updateClientEnergy();
                    }
                }
                else
                {
                    iterator.remove();
                    this.receiverCoords.remove(new ChunkCoordinates(receiverTile.xCoord, receiverTile.yCoord, receiverTile.zCoord));
                    this.owner.markDirty();
                    this.energyContainer.updateClientEnergy();
                }
            }
        }

        boolean dirty = false;

        while (queuedReceivers.size() > 0)
        {
            ChunkCoordinates receiver = queuedReceivers.poll();
            dirty |= processQueue(world, receiver);
        }

        if (!world.isRemote && dirty)
        {
            energyContainer.updateClientEnergy();
        }
    }

    private boolean processQueue(World world, ChunkCoordinates receiver)
    {
        if (!receiverCoords.contains(receiver))
        {
            TileEntity tile = world.getTileEntity(receiver.posX, receiver.posY, receiver.posZ);

            if (tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canReceiveEnergy(this.owner))
            {
                add(receiver, tile);
                return true;
            }
        }

        return false;
    }

    public void add(ChunkCoordinates coordinates, TileEntity tile)
    {
        if (!receiverCoords.contains(coordinates))
        {
            receiverCoords.add(coordinates);
            receivers.add(tile);

            IEnergyReceiver receiver = (IEnergyReceiver) tile;
            receiver.getReceiverHandler().add(new ChunkCoordinates(this.owner.xCoord, this.owner.yCoord, this.owner.zCoord), this.owner);

            this.owner.markDirty();
        }
    }

    private boolean exists(World world, TileEntity tile)
    {
        return world.getBlock(tile.xCoord, tile.yCoord, tile.zCoord) == tile.getBlockType();
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        receiverCoords.clear();
        receivers.clear();
        queuedReceivers.clear();

        NBTTagCompound energy = nbt.getCompoundTag("EmB");
        NBTTagList receiverList = energy.getTagList("Receivers", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < receiverList.tagCount(); ++i)
        {
            NBTTagCompound receiverTag = receiverList.getCompoundTagAt(i);
            ChunkCoordinates pos = new ChunkCoordinates(receiverTag.getInteger("x"), receiverTag.getInteger("y"), receiverTag.getInteger("z"));

            queue(pos);
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound energy = nbt.getCompoundTag("EmB");

        NBTTagList receiverList = new NBTTagList();

        for (ChunkCoordinates pos : receiverCoords)
        {
            writePos(receiverList, pos);
        }

        for (ChunkCoordinates pos : queuedReceivers)
        {
            writePos(receiverList, pos);
        }

        energy.setTag("Receivers", receiverList);

        nbt.setTag("EmB", energy);
    }

    private void writePos(NBTTagList list, ChunkCoordinates pos)
    {
        NBTTagCompound posTag = new NBTTagCompound();
        posTag.setInteger("x", pos.posX);
        posTag.setInteger("y", pos.posY);
        posTag.setInteger("z", pos.posZ);
        list.appendTag(posTag);
    }

    public void queue(ChunkCoordinates coordinate)
    {
        this.queuedReceivers.add(coordinate);
    }

    public void reset(World world, Set<ChunkCoordinates> newReceivers)
    {
        ChunkCoordinates coordinates = new ChunkCoordinates(owner.xCoord, owner.yCoord, owner.zCoord);

        for (ChunkCoordinates receiverPos : receiverCoords)
        {
            TileEntity tileReceiver = world.getTileEntity(receiverPos.posX, receiverPos.posY, receiverPos.posZ);

            ReceiverHandler receiverHandler = TFEnergyHelper.getReceiverHandler(tileReceiver);

            if (receiverHandler != null)
            {
                receiverHandler.remove(coordinates, owner);
            }
        }

        receiverCoords.clear();
        receivers.clear();
        queuedReceivers.clear();

        for (ChunkCoordinates receiver : newReceivers)
        {
            processQueue(world, receiver);
        }

        if (!world.isRemote)
        {
            energyContainer.updateClientEnergy();
        }

        owner.markDirty();
    }

    public List<TileEntity> getReceivers()
    {
        return receivers;
    }

    public Set<ChunkCoordinates> getReceiverCoords()
    {
        return receiverCoords;
    }
}
