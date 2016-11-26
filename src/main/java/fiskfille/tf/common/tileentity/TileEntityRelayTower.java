package fiskfille.tf.common.tileentity;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

import com.google.common.collect.Lists;

import fiskfille.tf.common.chunk.ForcedChunk;
import fiskfille.tf.common.chunk.SubTicket;
import fiskfille.tf.common.chunk.TFChunkManager;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.common.network.MessageUpdateEnergyState;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFHelper;

public class TileEntityRelayTower extends TileEntity implements IEnergyTransmitter, IEnergyReceiver, IChunkLoaderTile
{
    public TransmissionHandler transmissionHandler = new TransmissionHandler(this);
    public ReceiverHandler receiverHandler = new ReceiverHandler(this);

    public EnergyStorage storage = new EnergyStorage(100);

    public int animationTimer;
    public float lastUsage;

    public Ticket chunkTicket;

    @Override
    public void updateEntity()
    {
        ++animationTimer;

        if (getBlockMetadata() < 4)
        {
            if (!worldObj.isRemote)
            {
                if (chunkTicket == null)
                {
                    Ticket ticket = TFChunkManager.getTicketForChunk(ForcedChunk.fromTile(this));

                    if (ticket != null)
                    {
                        SubTicket subTicket = SubTicket.fromTile(this);
                        forceChunks(subTicket.assign(ticket));
                    }
                }
            }

            transmissionHandler.onUpdate(worldObj);
            receiverHandler.onUpdate(worldObj);

            if (worldObj.isRemote)
            {
                TFHelper.applyClientEnergyUsage(this);
            }
            else
            {
                List<TileEntity> tilesToPower = getTilesToPower();

                for (TileEntity tile : tilesToPower)
                {
                    IEnergyReceiver receiver = (IEnergyReceiver) tile;
                    TFHelper.transferEnergy(receiver, this, Math.min(getEnergy(), 100F) / tilesToPower.size());
                }

                float usage = storage.calculateUsage();

                if (Math.abs(usage - lastUsage) > 0.001F)
                {
                    updateClientEnergy();
                }

                lastUsage = usage;
            }
        }
    }

    public List<TileEntity> getTilesToPower()
    {
        List<TileEntity> tilesToPower = Lists.newArrayList();

        for (TileEntity tile : transmissionHandler.getReceivers())
        {
            if (canPowerReach(tile))
            {
                tilesToPower.add(tile);
            }
        }

        return tilesToPower;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0.35D, 0, 0.35D).addCoord(0, 2, 0);

        if (getBlockMetadata() < 4)
        {
            for (TileEntity tile : transmissionHandler.getReceivers())
            {
                if (tile instanceof IEnergyTransmitter)
                {
                    if (((IEnergyTransmitter) tile).getTransmissionHandler().getReceivers().contains(this))
                    {
                        continue;
                    }
                }

                bounds = TFHelper.wrapAroundAABB(bounds, tile.getRenderBoundingBox());
            }
        }

        return bounds;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        transmissionHandler.readFromNBT(nbt);
        receiverHandler.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        transmissionHandler.writeToNBT(nbt);
        receiverHandler.writeToNBT(nbt);
        storage.writeToNBT(nbt);
    }

    public ReceiverHandler getReceiverHandler()
    {
        return receiverHandler;
    }

    public TransmissionHandler getTransmissionHandler()
    {
        return transmissionHandler;
    }

    @Override
    public boolean isPowering(TileEntity tile)
    {
        return getTilesToPower().contains(tile);
    }

    @Override
    public boolean canPowerReach(TileEntity tile)
    {
        if (tile instanceof IEnergyReceiver)
        {
            IEnergyReceiver receiver = (IEnergyReceiver) tile;
            Vec3 hit = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
            Vec3 original = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
            Vec3 output = getEnergyOutputOffset().addVector(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F);

            double deltaScale = 1F / hit.distanceTo(output);
            output = Vec3.createVectorHelper(output.xCoord + (hit.xCoord - output.xCoord) * deltaScale, output.yCoord + (hit.yCoord - output.yCoord) * deltaScale, output.zCoord + (hit.zCoord - output.zCoord) * deltaScale);
            MovingObjectPosition result = TFEnergyHelper.rayTraceBlocks(worldObj, output, hit);

            if (result != null)
            {
                hit = result.hitVec;
            }

            if (hit.xCoord == original.xCoord && hit.yCoord == original.yCoord && hit.zCoord == original.zCoord)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public float getRange()
    {
        return 15;
    }

    @Override
    public boolean canReceiveEnergy(TileEntity from)
    {
        return getBlockMetadata() < 4;
    }

    @Override
    public Vec3 getEnergyOutputOffset()
    {
        return getEnergyInputOffset();
    }

    @Override
    public Vec3 getEnergyInputOffset()
    {
        return Vec3.createVectorHelper(0, 1.5F, 0);
    }

    @Override
    public float receiveEnergy(float amount)
    {
        for (TileEntity tile : transmissionHandler.getReceivers())
        {
            if (canPowerReach(tile))
            {
                return storage.add(amount);
            }
        }

        return 0;
    }

    @Override
    public float extractEnergy(float amount)
    {
        return storage.remove(amount);
    }

    @Override
    public float getEnergy()
    {
        return storage.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return storage.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        storage.setUsage(usage);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        writeToNBT(syncData);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public void updateClientEnergy()
    {
        TFNetworkManager.networkWrapper.sendToDimension(new MessageUpdateEnergyState(this), this.worldObj.provider.dimensionId);
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
            releaseChunks();
        }
    }

    @Override
    public void forceChunks(SubTicket subTicket)
    {
        releaseChunks();
        chunkTicket = subTicket.owner;
        TFChunkManager.forceChunk(subTicket.owner, ForcedChunk.fromTile(this));
    }

    public void releaseChunks()
    {
        if (chunkTicket != null)
        {
            TFChunkManager.releaseChunk(SubTicket.fromTile(chunkTicket, this), ForcedChunk.fromTile(this));
            chunkTicket = null;
        }
    }
}
