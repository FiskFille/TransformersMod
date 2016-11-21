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
 * Handles all transmitters transmitting energy to this tile
 */
public class ReceiverHandler
{
    private Set<ChunkCoordinates> transmitterCoords = new HashSet<ChunkCoordinates>();
    private List<TileEntity> transmitters = Lists.newArrayList();
    private TileEntity owner;
    private IEnergyContainer energyContainer;

    private Queue<ChunkCoordinates> queuedTransmitters = new ArrayDeque<ChunkCoordinates>();

    public ReceiverHandler(TileEntity owner)
    {
        this.owner = owner;
        this.energyContainer = (IEnergyContainer) owner;
    }

    public void onUpdate(World world)
    {
        if (!world.isRemote)
        {
            for (Iterator<TileEntity> iterator = this.transmitters.iterator(); iterator.hasNext(); )
            {
                TileEntity transmitterTile = iterator.next();

                boolean invalid = world.getChunkProvider().chunkExists(transmitterTile.xCoord >> 4, transmitterTile.zCoord >> 4) && (transmitterTile.isInvalid() || !exists(world, transmitterTile));
                boolean outRange = transmitterTile instanceof IEnergyTransmitter && !TFEnergyHelper.isInRange(transmitterTile, owner);
                boolean destroyed = !invalid && !exists(world, transmitterTile);

                if (invalid || outRange || destroyed)
                {
                    iterator.remove();
                    this.transmitterCoords.remove(new ChunkCoordinates(transmitterTile.xCoord, transmitterTile.yCoord, transmitterTile.zCoord));
                    this.owner.markDirty();
                    this.energyContainer.updateClientEnergy();
                }
            }
        }

        if (this.owner instanceof IEnergyReceiver)
        {
            boolean dirty = false;

            while (queuedTransmitters.size() > 0)
            {
                ChunkCoordinates transmitter = queuedTransmitters.poll();

                if (!transmitterCoords.contains(transmitter))
                {
                    TileEntity tile = world.getTileEntity(transmitter.posX, transmitter.posY, transmitter.posZ);

                    if (tile instanceof IEnergyTransmitter && ((IEnergyReceiver) this.owner).canReceiveEnergy(tile))
                    {
                        add(transmitter, tile);
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

    public void add(ChunkCoordinates coordinates, TileEntity tile)
    {
        if (!transmitterCoords.contains(coordinates))
        {
            transmitterCoords.add(coordinates);
            transmitters.add(tile);
            this.owner.markDirty();
        }
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        transmitterCoords.clear();
        transmitters.clear();
        queuedTransmitters.clear();

        NBTTagCompound energy = nbt.getCompoundTag("EmB");
        NBTTagList transmitterList = energy.getTagList("Transmitters", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < transmitterList.tagCount(); ++i)
        {
            NBTTagCompound receiverTag = transmitterList.getCompoundTagAt(i);
            ChunkCoordinates pos = new ChunkCoordinates(receiverTag.getInteger("x"), receiverTag.getInteger("y"), receiverTag.getInteger("z"));

            queue(pos);
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound energy = nbt.getCompoundTag("EmB");

        NBTTagList transmitterList = new NBTTagList();

        for (ChunkCoordinates pos : transmitterCoords)
        {
            writePos(transmitterList, pos);
        }

        for (ChunkCoordinates pos : queuedTransmitters)
        {
            writePos(transmitterList, pos);
        }

        energy.setTag("Transmitters", transmitterList);

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
        this.queuedTransmitters.add(coordinate);
    }

    public List<TileEntity> getTransmitters()
    {
        return transmitters;
    }

    public Set<ChunkCoordinates> getTransmitterCoords()
    {
        return transmitterCoords;
    }

    public void reset(World world, Set<ChunkCoordinates> newTransmitters)
    {
        transmitterCoords.clear();
        transmitters.clear();
        queuedTransmitters.clear();
        queuedTransmitters.addAll(newTransmitters);
        this.owner.markDirty();

        if (!world.isRemote)
        {
            energyContainer.updateClientEnergy();
        }
    }

    public void remove(ChunkCoordinates coordinates, TileEntity tile)
    {
        transmitterCoords.remove(coordinates);
        transmitters.remove(tile);
        this.owner.markDirty();
    }
}
