package fiskfille.tf.common.energon.power;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.network.NetworkRegistry;
import fiskfille.tf.common.network.MessageUpdateReceivers;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ReceiverHandler
{
    private Set<ChunkCoordinates> receiverCoords = new HashSet<ChunkCoordinates>();
    private List<TileEntity> receivers = Lists.newArrayList();
    private TileEntity tile;

    private List<ChunkCoordinates> queuedReceivers = new LinkedList<ChunkCoordinates>();

    public ReceiverHandler(TileEntity tile)
    {
        this.tile = tile;
    }

    public void onUpdate(World world)
    {
        for (Iterator<TileEntity> iterator = this.receivers.iterator(); iterator.hasNext(); )
        {
            TileEntity receiverTile = iterator.next();

            if (receiverTile instanceof IEnergyReceiver)
            {
                IEnergyReceiver receiver = (IEnergyReceiver) receiverTile;

                if (receiverTile.isInvalid() || !(TFEnergyHelper.isInRange(tile, receiverTile) && (receiverTile instanceof IEnergyTransmitter || receiver.getEnergy() < receiver.getMaxEnergy())) || world.getTileEntity(receiverTile.xCoord, receiverTile.yCoord, receiverTile.zCoord) != receiverTile)
                {
                    iterator.remove();
                    this.tile.markDirty();
                }
            }
            else
            {
                iterator.remove();
                this.tile.markDirty();
            }
        }

        for (ChunkCoordinates receiver : queuedReceivers)
        {
            processQueue(world, receiver);
        }

        queuedReceivers.clear();
    }

    private boolean processQueue(World world, ChunkCoordinates receiver)
    {
        if (!receiverCoords.contains(receiver))
        {
            TileEntity tile = world.getTileEntity(receiver.posX, receiver.posY, receiver.posZ);

            if (tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canReceiveEnergy(this.tile))
            {
                add(receiver, tile);
                return true;
            }
        }

        return false;
    }

    public void add(ChunkCoordinates coordinates, TileEntity tile)
    {
        receiverCoords.add(coordinates);
        receivers.add(tile);

        this.tile.markDirty();
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

    public void updateClients()
    {
        NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(this.tile.getWorldObj().provider.dimensionId, this.tile.xCoord + 0.5F, this.tile.yCoord, this.tile.zCoord + 0.5F, 128);
        TFNetworkManager.networkWrapper.sendToAllAround(new MessageUpdateReceivers(this.tile), target);
    }

    public void queue(ChunkCoordinates coordinate)
    {
        this.queuedReceivers.add(coordinate);
    }

    public void reset(World world, Set<ChunkCoordinates> newReceivers)
    {
        for (ChunkCoordinates pos : receiverCoords)
        {
            TileEntity tileReceiver = world.getTileEntity(pos.posX, pos.posY, pos.posZ);

            ReceivingHandler receivingHandler = TFEnergyHelper.getReceivingHandler(tileReceiver);

            if (receivingHandler != null)
            {
                receivingHandler.remove(new ChunkCoordinates(tile.xCoord, tile.yCoord, tile.zCoord), tileReceiver);
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
            this.updateClients();
        }

        for (ChunkCoordinates pos : newReceivers)
        {
            TileEntity tileReceiver = world.getTileEntity(pos.posX, pos.posY, pos.posZ);

            ReceivingHandler receivingHandler = TFEnergyHelper.getReceivingHandler(tileReceiver);

            if (receivingHandler != null)
            {
                receivingHandler.queue(new ChunkCoordinates(tile.xCoord, tile.yCoord, tile.zCoord));
            }
        }

        this.tile.markDirty();
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
