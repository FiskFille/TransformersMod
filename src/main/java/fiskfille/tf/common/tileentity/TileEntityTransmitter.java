package fiskfille.tf.common.tileentity;

import java.util.List;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

import com.google.common.collect.Lists;

import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.TFEnergonManager;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyContainer;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.helper.TFHelper;

public class TileEntityTransmitter extends TileEntity implements IEnergyContainer
{
	public EnergyStorage storage = new EnergyStorage(1000000000);
	
	public int animationTimer;
	public int prevEnergyColor;
	public float prevEnergy;
	
	public void updateEntity()
	{
		prevEnergyColor = storage.getEnergyColor();
		prevEnergy = storage.getEnergy();
		++animationTimer;
		
		if (getBlockMetadata() < 4)
		{
			storage.update();
			receiveEnergy(100000, Energon.createContentMap(1, TFEnergonManager.energon));
			
			for (TileEntity tile : getTilesToPower())
			{
				IEnergyReceiver receiver = (IEnergyReceiver)tile;
				TFHelper.transferEnergy(receiver, this, 1000F / getTilesToPower().size());
			}
		}
	}
	
	public List<TileEntity> getTilesToPower()
	{
		List<TileEntity> list = Lists.newArrayList();
		
		for (TileEntity tile : getTilesToTryPower())
		{
			if (canPower(tile))
			{
				list.add(tile);
			}
		}
		
		return list;
	}
	
	public List<TileEntity> getTilesToTryPower()
	{
		List<TileEntity> list = Lists.newArrayList();
		
		if (getBlockMetadata() < 4 && getEnergy() > 0)
		{
			for (TileEntity tile : (List<TileEntity>)worldObj.loadedTileEntityList)
			{
				if (tile instanceof IEnergyReceiver)
				{
					IEnergyReceiver receiver = (IEnergyReceiver)tile;
					Vec3 vec3 = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
					Vec3 vec31 = Vec3.createVectorHelper(xCoord + 0.5F, yCoord + 2.5F, zCoord + 0.5F);
					
					if (receiver.canReceiveEnergy() && receiver.getEnergy() < receiver.getMaxEnergy() && vec3.distanceTo(vec31) <= getRadius())
					{
						list.add(tile);
					}
				}
			}
		}
		
		return list;
	}
	
	public boolean canPower(TileEntity tile)
	{
		if (tile instanceof IEnergyReceiver)
		{
			IEnergyReceiver receiver = (IEnergyReceiver)tile;
			Vec3 vec3 = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
			Vec3 vec31 = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
			Vec3 vec32 = Vec3.createVectorHelper(xCoord + 0.5F, yCoord + 2.5F, zCoord + 0.5F);
			
			double d = 1F / vec3.distanceTo(vec32);
			vec32 = Vec3.createVectorHelper(vec32.xCoord + (vec3.xCoord - vec32.xCoord) * d, vec32.yCoord + (vec3.yCoord - vec32.yCoord) * d, vec32.zCoord + (vec3.zCoord - vec32.zCoord) * d);
			MovingObjectPosition mop = worldObj.rayTraceBlocks(vec32, vec3);
			
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
	
	public boolean isPowering(TileEntity tile)
	{
		return getTilesToPower().contains(tile);
	}
	
	public float getRadius()
	{
		return 20;
	}
	
	public AxisAlignedBB getRenderBoundingBox()
	{
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0.35D, 0, 0.35D).addCoord(0, 2, 0);
		
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
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}
	
	@Override
	public float receiveEnergy(float amount, Map<String, Float> contents)
	{
		return storage.add(amount, contents);
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
	public Map<String, Float> getEnergyContents()
	{
		return storage.getContents();
	}
	
	@Override
	public int getEnergyColor()
	{
		return storage.getEnergyColor();
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
