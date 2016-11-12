package fiskfille.tf.common.energon.power;

import com.google.common.collect.Lists;
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

/**
 * Handles all transmitters transmitting energy to this tile
 */
public class ReceiverHandler
{
    private Set<ChunkCoordinates> transmitterCoords = new HashSet<ChunkCoordinates>();
    private List<TileEntity> transmitters = Lists.newArrayList();
    private TileEntity tile;
    private IEnergyContainer energyContainer;

    private List<ChunkCoordinates> queuedTransmitters = new LinkedList<ChunkCoordinates>();

    public ReceiverHandler(TileEntity tile)
    {
        this.tile = tile;
        this.energyContainer = (IEnergyContainer) tile;
    }

    public void onUpdate(World world)
    {
        if (!world.isRemote)
        {
            for (Iterator<TileEntity> iterator = this.transmitters.iterator(); iterator.hasNext(); )
            {
                TileEntity transmitterTile = iterator.next();

                boolean invalid = world.getChunkProvider().chunkExists(transmitterTile.xCoord >> 4, transmitterTile.zCoord >> 4) && (transmitterTile.isInvalid() || !exists(world, transmitterTile));
                boolean outRange = transmitterTile instanceof IEnergyTransmitter && !TFEnergyHelper.isInRange(transmitterTile, tile);
                boolean destroyed = !invalid && !exists(world, transmitterTile);

                if (invalid || outRange || destroyed)
                {
                    iterator.remove();
                    this.transmitterCoords.remove(new ChunkCoordinates(transmitterTile.xCoord, transmitterTile.yCoord, transmitterTile.zCoord));
                    this.tile.markDirty();
                    this.energyContainer.updateClients();
                }
            }
        }

        if (this.tile instanceof IEnergyReceiver)
        {
            boolean dirty = false;

            for (ChunkCoordinates transmitter : queuedTransmitters)
            {
                if (!transmitterCoords.contains(transmitter))
                {
                    TileEntity tile = world.getTileEntity(transmitter.posX, transmitter.posY, transmitter.posZ);

                    if (tile instanceof IEnergyTransmitter && ((IEnergyReceiver) this.tile).canReceiveEnergy(tile))
                    {
                        add(transmitter, tile);
                        dirty = true;
                    }
                }
            }

            if (dirty && !world.isRemote)
            {
                energyContainer.updateClients();
            }
        }

        queuedTransmitters.clear();
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
        }

        if (!transmitters.contains(tile))
        {
            transmitters.add(tile);
        }

        this.tile.markDirty();
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
        this.tile.markDirty();

        if (!world.isRemote)
        {
            energyContainer.updateClients();
        }
    }

    public void remove(ChunkCoordinates coordinates, TileEntity tile)
    {
        transmitterCoords.remove(coordinates);
        transmitters.remove(tile);
        this.tile.markDirty();
    }
}
