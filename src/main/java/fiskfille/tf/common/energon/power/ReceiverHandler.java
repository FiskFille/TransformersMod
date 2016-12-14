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
 * Handles all transmitters transmitting energy to this tile
 */
public class ReceiverHandler
{
    private Set<TargetingTransmitter> transmitters = new HashSet<TargetingTransmitter>();
    private TileEntity owner;
    private IEnergyContainer energyContainer;

    private Queue<TargetingTransmitter> queuedTransmitters = new ArrayDeque<TargetingTransmitter>();
    private TargetReceiver receiver;

    private boolean canReach;

    public ReceiverHandler(TileEntity owner)
    {
        this.owner = owner;
        this.energyContainer = (IEnergyContainer) owner;
    }

    protected void init()
    {
        if (owner instanceof IEnergyReceiver)
        {
            ChunkCoordinates coordinates = new ChunkCoordinates(owner.xCoord, owner.yCoord, owner.zCoord);
            IEnergyReceiver energyReceiver = (IEnergyReceiver) owner;
            this.receiver = new TargetReceiver(coordinates, energyReceiver.getEnergyInputOffset(), owner, energyReceiver);
        }
    }

    public void onUpdate(World world)
    {
        if (this.receiver == null)
        {
            init();
        }

        if (!world.isRemote)
        {
            for (Iterator<TargetingTransmitter> iterator = this.transmitters.iterator(); iterator.hasNext(); )
            {
                TargetingTransmitter transmitter = iterator.next();
                TileEntity transmitterTile = transmitter.getTile();

                boolean invalid = world.getChunkProvider().chunkExists(transmitterTile.xCoord >> 4, transmitterTile.zCoord >> 4) && (transmitterTile.isInvalid() || !exists(world, transmitterTile));
                boolean outRange = transmitterTile instanceof IEnergyTransmitter && !TFEnergyHelper.isInRange(transmitterTile, owner);
                boolean destroyed = !invalid && !exists(world, transmitterTile);

                if (invalid || outRange || destroyed)
                {
                    iterator.remove();
                    this.owner.markDirty();
                    this.energyContainer.updateClientEnergy();
                }
            }

            boolean canReach = TFEnergyHelper.canPowerChainReach(owner);

            if (this.canReach != canReach)
            {
                energyContainer.updateClientEnergy();
                this.canReach = canReach;
            }
        }

        if (this.owner instanceof IEnergyReceiver)
        {
            boolean dirty = false;

            while (queuedTransmitters.size() > 0)
            {
                TargetingTransmitter transmitter = queuedTransmitters.poll();

                if (!transmitters.contains(transmitter))
                {
//                    if (!world.isRemote)
                    {
                        transmitter.load(world);
                    }

                    TileEntity tile = transmitter.getTile();

                    if ((tile instanceof IEnergyTransmitter && ((IEnergyReceiver) this.owner).canReceiveEnergy(tile)) || world.isRemote)
                    {
                        add(transmitter);
                        dirty = true;
                    }
                }
            }

            if (dirty && !world.isRemote)
            {
                energyContainer.updateClientEnergy();
            }
        }
    }

    private boolean exists(World world, TileEntity tile)
    {
        return world.getBlock(tile.xCoord, tile.yCoord, tile.zCoord) == tile.getBlockType();
    }

    public void add(TargetingTransmitter transmitter)
    {
        if (!transmitters.contains(transmitter))
        {
            transmitters.add(transmitter);
            this.owner.markDirty();
        }
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        transmitters.clear();
        queuedTransmitters.clear();

        NBTTagCompound energy = nbt.getCompoundTag("EmB");
        NBTTagList transmitterList = energy.getTagList("Transmitters", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < transmitterList.tagCount(); ++i)
        {
            NBTTagCompound transmitterTag = transmitterList.getCompoundTagAt(i);
            queue(TargetingTransmitter.readFromNBT(transmitterTag));
        }

        canReach = energy.getBoolean("CanReach");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound energy = nbt.getCompoundTag("EmB");

        NBTTagList transmitterList = new NBTTagList();

        for (TargetingTransmitter transmitter : transmitters)
        {
            writeTransmitter(transmitterList, transmitter);
        }

        for (TargetingTransmitter transmitter : queuedTransmitters)
        {
            writeTransmitter(transmitterList, transmitter);
        }

        energy.setTag("Transmitters", transmitterList);
        energy.setBoolean("CanReach", canReach);

        nbt.setTag("EmB", energy);
    }

    private void writeTransmitter(NBTTagList list, TargetingTransmitter transmitter)
    {
        NBTTagCompound tag = new NBTTagCompound();
        transmitter.writeToNBT(tag);
        list.appendTag(tag);
    }

    public void queue(TargetingTransmitter transmitter)
    {
        this.queuedTransmitters.add(transmitter);
    }

    public Set<TargetingTransmitter> getTransmitters()
    {
        return transmitters;
    }

    public void reset(World world, Set<TargetingTransmitter> newTransmitters)
    {
        transmitters.clear();
        queuedTransmitters.clear();
        queuedTransmitters.addAll(newTransmitters);
        this.owner.markDirty();

        if (!world.isRemote)
        {
            energyContainer.updateClientEnergy();
        }
    }

    public void remove(ChunkCoordinates coordinates)
    {
        remove(new TargetingTransmitter(coordinates));
    }

    public void remove(TargetingTransmitter transmitter)
    {
        transmitters.remove(transmitter);
        owner.markDirty();
    }

    public TargetReceiver getReceiver()
    {
        return receiver;
    }

    public boolean canReach()
    {
        return canReach;
    }

    public void setCanReach(boolean canReach)
    {
        this.canReach = canReach;
    }
}
