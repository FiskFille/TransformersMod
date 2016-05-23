package fiskfille.tf.common.tileentity;

import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

import com.google.common.collect.Lists;

import fiskfille.tf.common.energon.IEnergonPowered;

public class TileEntityTransmitter extends TileEntity
{
	public int animationTimer;
	
	public void updateEntity()
	{
		++animationTimer;
		
		if (getBlockMetadata() < 4)
		{
			
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
		
		if (getBlockMetadata() < 4)
		{
			for (TileEntity tile : (List<TileEntity>)worldObj.loadedTileEntityList)
			{
				if (tile instanceof IEnergonPowered)
				{
					IEnergonPowered energonPowered = (IEnergonPowered)tile;
					Vec3 vec3 = energonPowered.getPowerInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
					Vec3 vec31 = Vec3.createVectorHelper(xCoord + 0.5F, yCoord + 2.5F, zCoord + 0.5F);
					
					if (energonPowered.canBePowered() && vec3.distanceTo(vec31) <= getRadius())
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
		if (tile instanceof IEnergonPowered)
		{
			IEnergonPowered energonPowered = (IEnergonPowered)tile;
			Vec3 vec3 = energonPowered.getPowerInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
			Vec3 vec31 = energonPowered.getPowerInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
			Vec3 vec32 = Vec3.createVectorHelper(xCoord + 0.5F, yCoord + 2.5F, zCoord + 0.5F);
			
			double d = 1.0F / vec3.distanceTo(vec32);
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
		float radius = getRadius();
		return super.getRenderBoundingBox().copy().expand(0.5D, 0, 0.5D).addCoord(0, 2, 0).expand(radius, radius, radius);
	}
}
