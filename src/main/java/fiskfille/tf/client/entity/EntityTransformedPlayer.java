package fiskfille.tf.client.entity;

import fiskfille.tf.common.playerdata.TFDataManager;
import scala.annotation.tailrec;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTransformedPlayer extends EntityLivingBase
{
	private EntityLivingBase transformed;

	public EntityTransformedPlayer(World world) 
	{
		super(world);
	}

	public EntityTransformedPlayer(World world, EntityLivingBase transformed)
	{
		super(world);
		this.transformed = transformed;

		copyData();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		copyData();
		
		if(transformed != null)
		{
			if(!transformed.isInvisible())
			{
				transformed.setInvisible(true);
			}
			
			if(transformed instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) transformed;

				if(TFDataManager.getTransformationTimer(player) > 0 && (!TFDataManager.isInVehicleMode(player)))
				{
					this.setDead();
					transformed.setInvisible(false); //TODO had invisibility?
				}
			}
		}
	}

	private void copyData()
	{
		if(transformed != null)
		{
			posX = transformed.posX;
			posY = transformed.posY;
			posZ = transformed.posZ;

			prevPosX = transformed.prevPosX;
			prevPosY = transformed.prevPosY;
			prevPosZ = transformed.prevPosZ;

			motionX = transformed.motionX;
			motionY = transformed.motionY;
			motionZ = transformed.motionZ;

			this.setVelocity(motionX, motionY, motionZ);
			
			dimension = transformed.dimension;

			rotationYaw = transformed.rotationYaw;
			rotationYawHead = transformed.rotationYawHead;
			rotationPitch = transformed.rotationPitch;
			prevRotationYaw = transformed.prevRotationYaw;
			prevRotationPitch = transformed.prevRotationPitch;
			prevRotationYawHead = transformed.prevRotationYawHead;

			riddenByEntity = transformed.riddenByEntity;
			ridingEntity = transformed.ridingEntity;
		}
	}

	@Override
	public ItemStack getHeldItem()
	{
		if(transformed != null)
		{
			return transformed.getHeldItem();
		}
		
		return null;
	}

	@Override
	public ItemStack getEquipmentInSlot(int slot)
	{
		if(transformed != null)
		{
			return transformed.getEquipmentInSlot(slot);
		}
		
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int slot, ItemStack stack) 
	{
		if(transformed != null)
		{
			transformed.setCurrentItemOrArmor(slot, stack);
		}
	}

	@Override
	public ItemStack[] getLastActiveItems() 
	{
		if(transformed != null)
		{
			return transformed.getLastActiveItems();
		}
		
		return new ItemStack[0];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("transformed", transformed.getEntityId());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		transformed = (EntityLivingBase) worldObj.getEntityByID(nbt.getInteger("transformed"));
	}
}
