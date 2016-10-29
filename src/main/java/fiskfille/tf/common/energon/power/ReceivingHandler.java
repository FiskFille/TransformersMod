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

public class ReceivingHandler
{
    private Set<ChunkCoordinates> receivingCoords = new HashSet<ChunkCoordinates>();
    private List<TileEntity> receiving = Lists.newArrayList();
    private TileEntity tile;

    private List<ChunkCoordinates> queuedReceiving = new LinkedList<ChunkCoordinates>();

    public ReceivingHandler(TileEntity tile)
    {
        this.tile = tile;
    }

    public void onUpdate(World world)
    {
        for (Iterator<TileEntity> iterator = this.receiving.iterator(); iterator.hasNext(); )
        {
            TileEntity receiverTile = iterator.next();

            if (receiverTile.isInvalid() || (receiverTile instanceof IEnergyTransmitter && !TFEnergyHelper.isInRange(receiverTile, tile)) || (world.getTileEntity(receiverTile.xCoord, receiverTile.yCoord, receiverTile.zCoord) != receiverTile))
            {
                iterator.remove();
                this.tile.markDirty();
            }
        }

        if (this.tile instanceof IEnergyReceiver)
        {
            boolean dirty = false;

            for (ChunkCoordinates receiving : queuedReceiving)
            {
                if (!receivingCoords.contains(receiving))
                {
                    TileEntity tile = world.getTileEntity(receiving.posX, receiving.posY, receiving.posZ);

                    if (tile instanceof IEnergyTransmitter && ((IEnergyReceiver) this.tile).canReceiveEnergy(tile))
                    {
                        add(receiving, tile);
                        dirty = true;
                    }
                }
            }

            if (dirty && !world.isRemote)
            {
                this.updateClients();
            }
        }

        queuedReceiving.clear();
    }

    public void add(ChunkCoordinates coordinates, TileEntity tile)
    {
        receivingCoords.add(coordinates);
        receiving.add(tile);
        this.tile.markDirty();
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        receivingCoords.clear();
        receiving.clear();
        queuedReceiving.clear();

        NBTTagCompound energy = nbt.getCompoundTag("EmB");
        NBTTagList receiverList = energy.getTagList("Receiving", Constants.NBT.TAG_COMPOUND);

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

        for (ChunkCoordinates pos : receivingCoords)
        {
            writePos(receiverList, pos);
        }

        for (ChunkCoordinates pos : queuedReceiving)
        {
            writePos(receiverList, pos);
        }

        energy.setTag("Receiving", receiverList);

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
        this.queuedReceiving.add(coordinate);
    }

    public List<TileEntity> getReceiving()
    {
        return receiving;
    }

    public Set<ChunkCoordinates> getReceivingCoords()
    {
        return receivingCoords;
    }

    public void reset(Set<ChunkCoordinates> newReceiving)
    {
        receivingCoords.clear();
        receiving.clear();
        queuedReceiving.clear();
        queuedReceiving.addAll(newReceiving);
        this.tile.markDirty();
    }

    public void remove(ChunkCoordinates coordinates, TileEntity tile)
    {
        receivingCoords.remove(coordinates);
        receiving.remove(tile);
        this.tile.markDirty();
    }
}
