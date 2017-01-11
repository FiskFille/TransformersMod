package fiskfille.tf.common.energon.power;

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
import java.util.Queue;
import java.util.Set;

/**
 * Handles all receivers this transmitter is transmitting to
 */
public class TransmissionHandler
{
    private Set<TargetReceiver> receivers = new HashSet<TargetReceiver>();
    private TileEntity owner;
    private IEnergyContainer energyContainer;

    private Queue<TargetReceiver> queuedReceivers = new ArrayDeque<TargetReceiver>();

    private TargetingTransmitter transmitter;

    public TransmissionHandler(TileEntity owner)
    {
        this.owner = owner;
        this.energyContainer = (IEnergyContainer) owner;
    }

    protected void init()
    {
        if (owner instanceof IEnergyTransmitter)
        {
            ChunkCoordinates coordinates = new ChunkCoordinates(owner.xCoord, owner.yCoord, owner.zCoord);
            IEnergyTransmitter energyTransmitter = (IEnergyTransmitter) owner;
            this.transmitter = new TargetingTransmitter(coordinates, owner, energyTransmitter);
        }
    }

    public void onUpdate(World world)
    {
        if (this.transmitter == null)
        {
            init();
        }

        if (!world.isRemote)
        {
            for (Iterator<TargetReceiver> iterator = this.receivers.iterator(); iterator.hasNext();)
            {
                TargetReceiver targetReceiver = iterator.next();
                TileEntity receiverTile = targetReceiver.getTile();

                if (receiverTile instanceof IEnergyReceiver)
                {
                    boolean invalid = world.getChunkProvider().chunkExists(receiverTile.xCoord >> 4, receiverTile.zCoord >> 4) && (receiverTile.isInvalid() || !exists(world, receiverTile));
                    boolean outRange = !TFEnergyHelper.isInRange(owner, receiverTile);
                    boolean destroyed = !invalid && !exists(world, receiverTile);

                    if (invalid || outRange || destroyed)
                    {
                        iterator.remove();
                        this.owner.markDirty();
                        this.energyContainer.updateClientEnergy();
                    }
                }
                else
                {
                    iterator.remove();
                    this.owner.markDirty();
                    this.energyContainer.updateClientEnergy();
                }
            }
        }

        boolean dirty = false;

        while (queuedReceivers.size() > 0)
        {
            TargetReceiver receiver = queuedReceivers.poll();
            dirty |= processQueue(world, receiver);
        }

        if (!world.isRemote && dirty)
        {
            energyContainer.updateClientEnergy();
        }
    }

    private boolean processQueue(World world, TargetReceiver receiver)
    {
        if (!receivers.contains(receiver))
        {
            if (!world.isRemote)
            {
                receiver.load(world);

                if (TFEnergyHelper.isGrandParentTo(receiver.getTile(), this.owner))
                {
                    return false;
                }
            }

            TileEntity tile = receiver.getTile();

            if (world.isRemote || tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canReceiveEnergy(this.owner))
            {
                add(receiver);
                return true;
            }
        }

        return false;
    }

    public void add(TargetReceiver target)
    {
        if (!receivers.contains(target))
        {
            receivers.add(target);

            if (target.getTile() != null)
            {
                IEnergyReceiver receiver = target.getReceiver();
                receiver.getReceiverHandler().add(transmitter);
            }

            this.owner.markDirty();
        }
    }

    private boolean exists(World world, TileEntity tile)
    {
        return world.getBlock(tile.xCoord, tile.yCoord, tile.zCoord) == tile.getBlockType();
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        receivers.clear();
        queuedReceivers.clear();

        NBTTagCompound energy = nbt.getCompoundTag("EmB");
        NBTTagList receiverList = energy.getTagList("Receivers", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < receiverList.tagCount(); ++i)
        {
            NBTTagCompound receiverTag = receiverList.getCompoundTagAt(i);
            queue(TargetReceiver.readFromNBT(receiverTag));
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound energy = nbt.getCompoundTag("EmB");

        NBTTagList receiverList = new NBTTagList();

        for (TargetReceiver receiver : receivers)
        {
            writeTarget(receiverList, receiver);
        }

        for (TargetReceiver receiver : queuedReceivers)
        {
            writeTarget(receiverList, receiver);
        }

        energy.setTag("Receivers", receiverList);

        nbt.setTag("EmB", energy);
    }

    private void writeTarget(NBTTagList list, TargetReceiver target)
    {
        NBTTagCompound tag = new NBTTagCompound();
        target.writeToNBT(tag);
        list.appendTag(tag);
    }

    public void queue(TargetReceiver receiver)
    {
        this.queuedReceivers.add(receiver);
    }

    public void reset(World world, Set<TargetReceiver> newReceivers)
    {
        if (!world.isRemote)
        {
            for (TargetReceiver target : receivers)
            {
                ReceiverHandler receiverHandler = TFEnergyHelper.getReceiverHandler(target.getTile());

                if (receiverHandler != null)
                {
                    receiverHandler.remove(transmitter);
                }
            }
        }

        receivers.clear();
        queuedReceivers.clear();

        for (TargetReceiver receiver : newReceivers)
        {
            processQueue(world, receiver);
        }

        if (!world.isRemote)
        {
            energyContainer.updateClientEnergy();
        }

        owner.markDirty();
    }

    public Set<TargetReceiver> getReceivers()
    {
        return receivers;
    }

    public TargetReceiver getReceiver(TileEntity tile)
    {
        for (TargetReceiver receiver : receivers)
        {
            ChunkCoordinates coordinates = receiver.getCoordinates();

            if (coordinates.posX == tile.xCoord && coordinates.posY == tile.yCoord && coordinates.posZ == tile.zCoord)
            {
                return receiver;
            }
        }

        return null;
    }

    public TargetingTransmitter getTransmitter()
    {
        return transmitter;
    }
}
