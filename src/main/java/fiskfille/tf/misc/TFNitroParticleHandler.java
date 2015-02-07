package fiskfille.tf.misc;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.transformer.base.Transformer;

public class TFNitroParticleHandler 
{
	private static Map<EntityPlayer, Boolean> nitroOnMap = new HashMap<EntityPlayer, Boolean>();
	
	public static void doNitroParticles(EntityPlayer player) 
	{
		boolean nitro = getNitro(player);
		
		if (nitro)
		{
			if (TFDataManager.isInVehicleMode(player))
			{
				if (player != Minecraft.getMinecraft().thePlayer)
				{
					Transformer transformer = TFHelper.getTransformer(player);
					
					if (transformer != null)
					{
						transformer.doNitroParticles(player);
					}
				}
			}
		}
	}
	
	public static void setNitro(EntityPlayer player, boolean nitro)
	{
		nitroOnMap.put(player, nitro);
	}
	
	public static boolean getNitro(EntityPlayer player)
	{
		boolean nitro = false;
		
		Boolean nitroObj = nitroOnMap.get(player);
		
		if (nitroObj == null)
		{
			nitroOnMap.put(player, false);
			nitroObj = false;
		}
		
		nitro = nitroObj;
		
		return nitro;
	}
	
	public static Vec3 getBackSideCoords(EntityPlayer player, double amount, boolean side, double backAmount, boolean pitch)
	{
		Vec3 front = getFrontCoords(player, backAmount, pitch).addVector(-player.posX, -player.posY, -player.posZ);
		return getSideCoords(player, amount, side).addVector(front.xCoord, front.yCoord, front.zCoord);
	}
	
	public static Vec3 getSideCoords(EntityPlayer player, double amount, int side)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) + side);
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
		return vec31;
	}

	public static Vec3 getSideCoords(EntityPlayer player, double amount, boolean side)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) + (side ? -90 : 90));
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
		return vec31;
	}

	public static Vec3 getFrontCoords(EntityPlayer player, double amount, boolean pitch)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		
		if (!pitch)
		{
			f1 = 0;
		}
		
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
		return vec31;
	}

	public static Vec3 getFrontCoords(EntityPlayer player, float angle, double amount)
	{
		float f = 1.0F;
		float f1 = angle * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
		return vec31;
	}

	public static Vec3 getAboveCoords(EntityPlayer player, float angle, double amount)
	{
		float f = 1.0F;
		float f1 = angle * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
		return vec31;
	}

	public static Vec3 getVerticalCoords(EntityPlayer player, float angle, double amount)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f - angle;
		float f2 = angle * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
		return vec31;
	}
}
