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

import com.google.common.collect.Lists;

import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFHelper;

public class TileEntityRelayTower extends TileEntity implements IEnergyTransmitter, IEnergyReceiver
{
	public ReceiverHandler receiverHandler = new ReceiverHandler(this);
	public EnergyStorage storage = new EnergyStorage(100);
	
	public int animationTimer;
	
	public void updateEntity()
	{
		++animationTimer;
		receiverHandler.onUpdate(worldObj);
		
		if (getBlockMetadata() < 4)
		{
			for (TileEntity tile : getTilesToPower())
			{
				IEnergyReceiver receiver = (IEnergyReceiver)tile;
				TFHelper.transferEnergy(receiver, this, Math.min(getEnergy(), 100F) / getTilesToPower().size());
			}
		}
	}
	
	public List<TileEntity> getTilesToPower()
	{
		List<TileEntity> list = Lists.newArrayList();
		
		for (TileEntity tile : getTilesToTryPower())
		{
			if (canPowerReach(tile))
			{
				list.add(tile);
			}
		}
		
		return list;
	}
	
	public List<TileEntity> getTilesToTryPower()
	{
		List<TileEntity> list = Lists.newArrayList();
		
		if (getBlockMetadata() < 4)
		{
			for (TileEntity tile : receiverHandler.receivers)
			{
				if (tile instanceof IEnergyReceiver)
				{
					IEnergyReceiver receiver = (IEnergyReceiver)tile;
					
					if (TFEnergyHelper.isInRange(this, tile) && (tile instanceof IEnergyTransmitter || receiver.getEnergy() < receiver.getMaxEnergy()))
					{
						list.add(tile);
					}
				}
			}
		}
		
		return list;
	}
	
	public AxisAlignedBB getRenderBoundingBox()
	{
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0.35D, 0, 0.35D).addCoord(0, 1, 0);
		
		if (getBlockMetadata() < 4)
		{
			for (TileEntity tile : getTilesToTryPower())
			{
				aabb = TFHelper.wrapAroundAABB(aabb, tile.getRenderBoundingBox());
			}
		}
		
		return aabb;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		receiverHandler.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		receiverHandler.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}

	@Override
	public ReceiverHandler getReceiverHandler()
	{
		return receiverHandler;
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
			IEnergyReceiver receiver = (IEnergyReceiver)tile;
			Vec3 vec3 = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
			Vec3 vec31 = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
			Vec3 vec32 = getEnergyOutputOffset().addVector(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F);
			
			double d = 1F / vec3.distanceTo(vec32);
			vec32 = Vec3.createVectorHelper(vec32.xCoord + (vec3.xCoord - vec32.xCoord) * d, vec32.yCoord + (vec3.yCoord - vec32.yCoord) * d, vec32.zCoord + (vec3.zCoord - vec32.zCoord) * d);
			MovingObjectPosition mop = TFEnergyHelper.rayTraceBlocks(worldObj, vec32, vec3);
			
			if (mop != null)
			{	
				vec3 = mop.hitVec;
			}
			
			if (vec3.xCoord == vec31.xCoord && vec3.yCoord == vec31.yCoord && vec3.zCoord == vec31.zCoord)
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
		for (TileEntity tile : receiverHandler.receivers)
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
}
