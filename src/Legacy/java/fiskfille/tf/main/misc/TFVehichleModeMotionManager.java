package fiskfille.tf.main.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import fiskfille.tf.sound.CarLoopSoundHandler;

public class TFVehichleModeMotionManager
{
	public static final Random rand = new Random();
	public static Map<String, Double> velocityMap = new HashMap<String, Double>();
	public static Map<String, Integer> nitroMap = new HashMap<String, Integer>();
	
	public static void motionJet(EntityPlayer player)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		boolean moveForward = minecraft.gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || minecraft.gameSettings.keyBindSprint.getIsKeyPressed();
		int nitro = nitroMap.get(player.getDisplayName()) == null ? 0 : nitroMap.get(player.getDisplayName());
		double vel = velocityMap.get(player.getDisplayName()) == null ? 0 : velocityMap.get(player.getDisplayName());
		double increment = ((nitroPressed && nitro > 0 ? 1.4D : 1.0D) - vel) / 10 + 0.001D;
		
		if (moveForward && vel <= 1.41D)
		{
			vel += increment;
		}
		if (vel > 0.02D && !moveForward)
		{
			vel -= 0.02D;
		}
		if (vel <= 0.02D)
		{
			vel = 0;
		}
		
		if ((player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != Blocks.air || player.worldObj.getBlock((int)player.posX, (int)player.posY - 2, (int)player.posZ) != Blocks.air || player.worldObj.getBlock((int)player.posX, (int)player.posY - 3, (int)player.posZ) != Blocks.air))
		{
			player.setPosition(player.posX, player.posY + 0.8, player.posZ);
		}
		
		Vec3 vec3 = getFrontCoords(player, vel);
		player.motionX = (vec3.xCoord - player.posX);
		player.motionY = (vec3.yCoord - player.posY);
		player.motionZ = (vec3.zCoord - player.posZ);
		if (vel <= 0.3F) {vel = 0.3F;}
		if (vel > 1.41F) {vel = 1.41F;}
		
		if (nitro > 0 && nitroPressed && moveForward)
		{
			--nitro;
			
			for (int i = 0; i < 4; ++i)
			{
				Vec3 side = getSideCoords(player, 0.15F, i < 2);
				player.worldObj.spawnParticle("flame", side.xCoord, side.yCoord - 0.1F, side.zCoord, rand.nextFloat() / 20, -0.2F + rand.nextFloat() / 20, rand.nextFloat() / 20);
			}
		}
		
		nitroMap.put(player.getDisplayName(), nitro);
		velocityMap.put(player.getDisplayName(), vel);
	}
	
	public static void motionTank(EntityPlayer player)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		boolean moveForward = minecraft.gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || minecraft.gameSettings.keyBindSprint.getIsKeyPressed();
		
		player.stepHeight = 1.0F;
		int nitro = nitroMap.get(player.getDisplayName()) == null ? 0 : nitroMap.get(player.getDisplayName());
		double vel = velocityMap.get(player.getDisplayName()) == null ? 0 : velocityMap.get(player.getDisplayName());
		double increment = ((nitroPressed && nitro > 0 ? 0.2D : 0.1D) - vel) / 10 + 0.001D;
		
		if (moveForward && vel <= 1.0D)
		{
			vel += increment * 0.5F;
		}
		if (vel > 0.02D && !moveForward)
		{
			vel -= 0.02D;
		}
		if (vel < 0.01D && !moveForward)
		{
			vel = 0.0D;
		}
		
		Vec3 vec3 = getFrontCoords(player, 0, vel);
		player.motionX = (vec3.xCoord - player.posX);
		player.motionZ = (vec3.zCoord - player.posZ);
		if (vel <= 0) {vel = 0;}
		if (vel > 0.2D) {vel = 0.2D;}
		if (vel < 0.02D && !moveForward) {vel = 0;}
		
		if (nitro > 0 && nitroPressed && moveForward)
		{
			--nitro;
			
			for (int i = 0; i < 4; ++i)
			{
				Vec3 side = getSideCoords(player, 0.15F, i < 2);
				player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord - 1.6F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
			}
		}
		
		if (player.isInWater())
		{
			player.motionY = -0.1F;
		}
		
		nitroMap.put(player.getDisplayName(), nitro);
		velocityMap.put(player.getDisplayName(), vel);
	}
	
	public static void motionCar(EntityPlayer player)
	{
		Minecraft mc = Minecraft.getMinecraft();
		boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();
		boolean driftPressed = ClientProxy.keyBindingBrake.getIsKeyPressed();
		
		int nitro = nitroMap.get(player.getDisplayName()) == null ? 0 : nitroMap.get(player.getDisplayName());
		double vel = velocityMap.get(player.getDisplayName()) == null ? 0 : velocityMap.get(player.getDisplayName());
		double increment = ((nitroPressed && nitro > 0 ? 1.0D : 0.6D) - vel) / 10 + 0.001D;
		
		if (moveForward && vel <= 1.0D)
		{
			vel += increment * 0.5F;
		}
		if (vel > 0.02D && !moveForward)
		{
			vel -= 0.02D;
		}
		if (vel <= 0.02D)
		{
			vel = 0;
		}
		if (vel <= 0.3D && moveForward && nitroPressed && nitro > 0)
		{
			for (int i = 0; i < 10; ++i)
			{
				Vec3 side = getSideCoords(player, 0.15F, i < 2);
				player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord - 1.6F, side.zCoord, rand.nextFloat() / 10, rand.nextFloat() / 10 + 0.05F, rand.nextFloat() / 10);
			}
		}
		
		if (driftPressed && vel > 0.0D && player.onGround)
		{
			float f = ClientProxy.modelBipedMain.bipedHead.rotateAngleY - (ClientProxy.modelBipedMain.bipedBody.rotateAngleY - ClientProxy.modelBipedMain.bipedHead.rotateAngleY) / 3;
			vel -= 0.03D;
			
			Vec3 vec3 = getSideCoords(player, vel, -(int)(f * 45)/*f > -0.25D && f < 0.25D ? 0 : (f > 0 ? -45 : 30)*/);
			player.motionX = (vec3.xCoord - player.posX);
			player.motionZ = (vec3.zCoord - player.posZ);
			
			if (vel > 0.1D)
			{
				for (int i = 0; i < 10; ++i)
				{
					Vec3 side = getSideCoords(player, 0.15F, i < 2);
					player.worldObj.spawnParticle("reddust", side.xCoord, side.yCoord - 1.5F, side.zCoord, -1, 0, 0);
				}
			}
			
			float decrement = (float) (1.0F - (increment * 0.5F));
			
			if (f > 1.0F && vel > 0.0F)
			{
				player.rotationYaw += decrement;
			}
			if (f < -1.0F && vel > 0.0F)
			{
				player.rotationYaw -= decrement;
			}
		}
		else
		{
			Vec3 vec3 = getFrontCoords(player, 0, vel);
			player.motionX = (vec3.xCoord - player.posX);
			player.motionZ = (vec3.zCoord - player.posZ);
		}
		
		if (vel <= 0) {vel = 0;}
		if (vel > 1) {vel = 1;}
		
		if (nitro > 0 && nitroPressed && moveForward)
		{
			--nitro;
			
			for (int i = 0; i < 4; ++i)
			{
				Vec3 side = getSideCoords(player, 0.15F, i < 2);
				player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord - 1.6F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
			}
		}
		
		if (player.isInWater())
		{
			player.motionY = -0.1F;
		}
		
		nitroMap.put(player.getDisplayName(), nitro);
		velocityMap.put(player.getDisplayName(), vel);
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
	public static Vec3 getFrontCoords(EntityPlayer player, double amount)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
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