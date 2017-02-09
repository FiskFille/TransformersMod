package fiskfille.tf.common.energon.power;

import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.helper.TFEnergyHelper;
import io.netty.buffer.ByteBuf;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * Handles all receivers this transmitter is transmitting to
 */
public class TransmissionHandler
{
    private Set<ReceiverEntry> receivers = new HashSet<ReceiverEntry>();
    private Queue<ReceiverEntry> queuedReceivers = new ArrayDeque<ReceiverEntry>();
    
    private NetworkEntry owner;
    private boolean needsUpdate = false;
    
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(receivers.size());

        for (ReceiverEntry receiver : receivers)
        {
            receiver.toBytes(buf);
        }
    }
    
    public void fromBytes(ByteBuf buf)
    {
        int receiverCount = buf.readInt();

        for (int i = 0; i < receiverCount; i++)
        {
            receivers.add(ReceiverEntry.fromBytes(buf));
        }
    }

    public void onUpdate(World world)
    {
        if (owner == null)
        {
            return;
        }
        
        for (Iterator<ReceiverEntry> iterator = receivers.iterator(); iterator.hasNext();)
        {
            ReceiverEntry receiver = iterator.next();
            TileEntity receiverTile = receiver.getTile();
            
            if (receiverTile == null)
            {
                receiver.load(world);
                receiverTile = receiver.getTile();
            }

            if (receiverTile instanceof IEnergyReceiver)
            {
                boolean invalid = world.getChunkProvider().chunkExists(receiverTile.xCoord >> 4, receiverTile.zCoord >> 4) && (receiverTile.isInvalid() || !exists(world, receiverTile));
                boolean outRange = !TFEnergyHelper.isInRange(owner.getTile(), receiverTile);
                boolean destroyed = !invalid && !exists(world, receiverTile);

                if (!invalid && !outRange && !destroyed)
                {
                    boolean flag = receiver.canReach();
                    receiver.setCanReach(canPowerReach(receiver));
                    
                    if (flag != receiver.canReach())
                    {
                        needsUpdate = true;
                    }
                    
                    continue;
                }
            }
            
            iterator.remove();
            needsUpdate = true;
        }

        while (queuedReceivers.size() > 0)
        {
            ReceiverEntry receiver = queuedReceivers.poll();
            needsUpdate |= processQueue(world, receiver);
        }
        
        if (needsUpdate)
        {
            owner.getTile().markDirty();
        }
    }
    
    private boolean canPowerReach(ReceiverEntry entry)
    {
        TileEntity tile = owner.getTile();
        IEnergyTransmitter transmitter = owner.getTransmitter();
        IEnergyReceiver receiver = entry.getReceiver();

        DimensionalCoords coords = entry.getCoords();
        Vec3 hit = receiver.getEnergyInputOffset().addVector(coords.posX + 0.5F, coords.posY + 0.5F, coords.posZ + 0.5F);
        Vec3 original = receiver.getEnergyInputOffset().addVector(coords.posX + 0.5F, coords.posY + 0.5F, coords.posZ + 0.5F);
        Vec3 output = transmitter.getEnergyOutputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);

        double deltaScale = 1F / hit.distanceTo(output);
        output = Vec3.createVectorHelper(output.xCoord + (hit.xCoord - output.xCoord) * deltaScale, output.yCoord + (hit.yCoord - output.yCoord) * deltaScale, output.zCoord + (hit.zCoord - output.zCoord) * deltaScale);
        MovingObjectPosition result = TFEnergyHelper.rayTraceBlocks(tile.getWorldObj(), output, hit);

        if (result != null)
        {
            hit = result.hitVec;
        }

        return hit.xCoord == original.xCoord && hit.yCoord == original.yCoord && hit.zCoord == original.zCoord;
    }
    
    public void kill()
    {
        for (Iterator<ReceiverEntry> iterator = receivers.iterator(); iterator.hasNext();)
        {
            ReceiverEntry entry = iterator.next();
            IEnergyReceiver receiver = entry.getReceiver();
            
            receiver.getReceiverHandler().remove(owner);
        }
    }

    private boolean processQueue(World world, ReceiverEntry receiver)
    {
        if (!receivers.contains(receiver))
        {
            receiver.load(world);
            TileEntity tile = receiver.getTile();
            
            if (tile instanceof IEnergyTransmitter && TFEnergyHelper.getDescendants(receiver.getTransmitter()).contains(owner.getCoords()))
            {
                return false;
            }
            else if (tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canReceiveEnergy(owner.getTile()))
            {
                add(receiver);
                return true;
            }
        }

        return false;
    }

    public void add(ReceiverEntry receiver)
    {
        if (!receivers.contains(receiver))
        {
            receivers.add(receiver);

            if (receiver.getTile() != null)
            {
                IEnergyReceiver energyReceiver = receiver.getReceiver();
                energyReceiver.getReceiverHandler().add(owner);
            }

            needsUpdate = true;
        }
    }
    
    public void remove(ReceiverEntry entry)
    {
        if (receivers.contains(entry))
        {
            receivers.remove(entry);

            if (entry.getTile() != null)
            {
                IEnergyReceiver receiver = entry.getReceiver();
                receiver.getReceiverHandler().remove(owner);
            }

            needsUpdate = true;
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
        NBTTagList receiverList = energy.getTagList("Receivers", NBT.TAG_COMPOUND);

        for (int i = 0; i < receiverList.tagCount(); ++i)
        {
            NBTTagCompound receiverTag = receiverList.getCompoundTagAt(i);
            queue(ReceiverEntry.readFromNBT(receiverTag));
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound energy = nbt.getCompoundTag("EmB");
        NBTTagList receiverList = new NBTTagList();

        for (ReceiverEntry receiver : receivers)
        {
            writeReceiver(receiverList, receiver);
        }

        for (ReceiverEntry receiver : queuedReceivers)
        {
            writeReceiver(receiverList, receiver);
        }

        energy.setTag("Receivers", receiverList);
        nbt.setTag("EmB", energy);
    }

    private void writeReceiver(NBTTagList list, ReceiverEntry receiver)
    {
        NBTTagCompound tag = new NBTTagCompound();
        receiver.writeToNBT(tag);
        list.appendTag(tag);
    }

    public void queue(ReceiverEntry receiver)
    {
        queuedReceivers.add(receiver);
    }

    public void reset(World world, Set<ReceiverEntry> newReceivers)
    {
        for (ReceiverEntry receiver : receivers)
        {
            ReceiverHandler receiverHandler = TFEnergyHelper.getReceiverHandler(receiver.getTile());

            if (receiverHandler != null)
            {
                receiverHandler.remove(owner);
            }
        }

        receivers.clear();
        queuedReceivers.clear();

        for (ReceiverEntry receiver : newReceivers)
        {
            processQueue(world, receiver);
        }

        needsUpdate = true;
    }

    public Set<ReceiverEntry> getReceivers()
    {
        return receivers;
    }

    public ReceiverEntry getReceiver(DimensionalCoords coords)
    {
        for (ReceiverEntry receiver : receivers)
        {
            if (receiver.getCoords().equals(coords))
            {
                return receiver;
            }
        }

        return null;
    }

    public NetworkEntry getOwner()
    {
        return owner;
    }
    
    public void setOwner(TileEntity tile)
    {
        owner = new NetworkEntry(tile);
    }

    public boolean needsUpdate()
    {
        return needsUpdate;
    }

    public void setNeedsUpdate(boolean flag)
    {
        needsUpdate = flag;
    }
}
