package fiskfille.tf.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import fiskfille.tf.TFHelper;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.packet.PacketVehicleNitro;
import fiskfille.tf.proxy.ClientProxy;

public class TFMotionManager
{
	public static Map<EntityPlayer, VehicleMotion> transformedPlayerMap = new HashMap<EntityPlayer, VehicleMotion>();

	private static float prevYaw;
	private static boolean prevNitro;

	public static void motionJet(EntityPlayer player)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		boolean moveForward = minecraft.gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || minecraft.gameSettings.keyBindSprint.getIsKeyPressed();
		VehicleMotion transformedPlayer = transformedPlayerMap.get(player);
		int nitro = 0;
		double vel = 0;

		if(transformedPlayer != null)
		{
			nitro = transformedPlayer.getNitro();
			vel = transformedPlayer.getForwardVelocity();

			double increment = ((nitroPressed && nitro > 0 ? 6.84D : 1.36D) - vel) / 10 + 0.001D;

			if (moveForward && vel <= 1.41D)
			{
				vel += increment;
			}
			if (vel > 0.14D && !moveForward)
			{
				vel -= 0.14D;
			}
			if (vel <= 0.14D)
			{
				vel = 0.14D;
			}

			if ((player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != Blocks.air || player.worldObj.getBlock((int)player.posX, (int)player.posY - 2, (int)player.posZ) != Blocks.air || player.worldObj.getBlock((int)player.posX, (int)player.posY - 3, (int)player.posZ) != Blocks.air))
			{
				player.setPosition(player.posX, player.posY + 0.8, player.posZ);
			}

			Vec3 vec3 = getFrontCoords(player, vel, true);
			player.motionX = (vec3.xCoord - player.posX);
			player.motionY = (vec3.yCoord - player.posY);
			player.motionZ = (vec3.zCoord - player.posZ);
			if (vel <= 0.09F) {vel = 0.09F;}
			if (vel > 1.41F) {vel = 1.41F;}

			if(player == Minecraft.getMinecraft().thePlayer)
			{
				if (nitro > 0 && nitroPressed && moveForward)
				{
					--nitro;
					if (!prevNitro)
					{
						TransformersMod.packetPipeline.sendToServer(new PacketVehicleNitro(player, true));
						prevNitro = true;
					}

					for (int i = 0; i < 4; ++i)
					{
						Vec3 side = getBackSideCoords(player, 0.15F, i < 2, -1.5, true);
						Random rand = new Random();
						player.worldObj.spawnParticle("flame", side.xCoord, side.yCoord - 0.2F, side.zCoord, rand.nextFloat() / 20, -0.2F + rand.nextFloat() / 20, rand.nextFloat() / 20);
					}
				}
				else
				{
					if (prevNitro)
					{
						TransformersMod.packetPipeline.sendToServer(new PacketVehicleNitro(player, false));
						prevNitro = false;
					}
				}

				try
				{
					if(TFConfig.rollWithJet && TFHelper.isPlayerJet(player))
					{
						EntityRenderer entityRenderer = Minecraft.getMinecraft().entityRenderer;

						float yaw = ((ClientProxy.modelBipedMain.bipedHead.rotateAngleY - ClientProxy.modelBipedMain.bipedBody.rotateAngleY) / 100) * 10000;
						float beforeMod = yaw;

						if(yaw - prevYaw > 100)
						{
							yaw = prevYaw + 100;
						}
						else if (yaw - prevYaw < -100)
						{
							yaw = prevYaw - 100;
						}

						prevYaw = beforeMod;
						ClientProxy.camRollField.set(entityRenderer, yaw);
					}
				}
				catch (IllegalArgumentException e) 
				{
					e.printStackTrace();
				} 
				catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}

			transformedPlayer.setNitro(nitro);
			transformedPlayer.setForwardVelocity(vel);
		}
	}

	public static void motionTank(EntityPlayer player)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		boolean moveForward = minecraft.gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || minecraft.gameSettings.keyBindSprint.getIsKeyPressed();

		player.stepHeight = 1.0F;
		VehicleMotion transformedPlayer = transformedPlayerMap.get(player);

		int nitro = 0;
		double vel = 0;

		if(transformedPlayer != null)
		{
			nitro = transformedPlayer.getNitro();
			vel = transformedPlayer.getForwardVelocity();
			double increment = ((nitroPressed && nitro > 0 ? 0.15D : 0.035D) - vel) / 10 + 0.001D;

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

			if (nitro > 0 && nitroPressed && moveForward && player == Minecraft.getMinecraft().thePlayer)
			{
				--nitro;

				if (!prevNitro)
				{
					TransformersMod.packetPipeline.sendToServer(new PacketVehicleNitro(player, true));
					prevNitro = true;
				}

				for (int i = 0; i < 4; ++i)
				{
					Vec3 side = getBackSideCoords(player, 0.15F, i < 2, -0.6, false);
					Random rand = new Random();
					player.worldObj.spawnParticle("smoke", side.xCoord, player.posY - 1.4F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
				}
			}
			else
			{
				if (prevNitro)
				{
					TransformersMod.packetPipeline.sendToServer(new PacketVehicleNitro(player, false));
					prevNitro = false;
				}
			}

			transformedPlayer.setNitro(nitro);
			transformedPlayer.setForwardVelocity(vel);

			if (player.isInWater())
			{
				player.motionY = -0.1F;
			}
		}
	}

	public static void motionCar(EntityPlayer player)
	{
		Minecraft mc = Minecraft.getMinecraft();
		boolean inStealthMode = TFDataManager.isInStealthMode(player);
		boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
		boolean moveSide = player.moveStrafing != 0;
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();
		boolean driftPressed = ClientProxy.keyBindingBrake.getIsKeyPressed();
		int nitro = 0;
		double forwardVelocity = 0;
		double horizontalVelocity = 0;
		
		player.stepHeight = 1.0F;

		VehicleMotion transformedPlayer = transformedPlayerMap.get(player);

		if(transformedPlayer != null)
		{
			nitro = transformedPlayer.getNitro();
			forwardVelocity = transformedPlayer.getForwardVelocity();
			horizontalVelocity = transformedPlayer.getHorizontalVelocity();
			double increment;

			if(inStealthMode)
			{
				increment = (0.328D - forwardVelocity) / 10 + 0.001D;
			}
			else
			{
				increment = ((nitroPressed && nitro > 0 ? 5.5D : 0.74D) - forwardVelocity) / 10 + 0.001D;
			}

			if (moveForward && forwardVelocity <= 1.0D)
			{
				forwardVelocity += increment * 0.5F;
			}
			else if(forwardVelocity > 0.02D)
			{
				forwardVelocity -= 0.02D;
			}
			else if(forwardVelocity <= 0.02D)
			{
				forwardVelocity = 0;
			}
			
			if (moveSide && horizontalVelocity <= 1.0D && inStealthMode)
			{
				horizontalVelocity += increment * 0.5F;
			}
			else if(horizontalVelocity > 0.02D)
			{
				horizontalVelocity-= 0.02D;
			}
			else if(horizontalVelocity <= 0.02D)
			{
				horizontalVelocity = 0;
			}

			if (driftPressed && player.onGround && forwardVelocity > 0)
			{
				float f = ClientProxy.modelBipedMain.bipedHead.rotateAngleY - (ClientProxy.modelBipedMain.bipedBody.rotateAngleY - ClientProxy.modelBipedMain.bipedHead.rotateAngleY) / 3;
				forwardVelocity -= 0.03D;

				Vec3 vec3 = getSideCoords(player, forwardVelocity, -(int)(f * 45)/*f > -0.25D && f < 0.25D ? 0 : (f > 0 ? -45 : 30)*/);
				player.motionX = (vec3.xCoord - player.posX);
				player.motionZ = (vec3.zCoord - player.posZ);

				if (forwardVelocity > 0.1D)
				{
					for (int i = 0; i < 10; ++i)
					{
						Vec3 side = getBackSideCoords(player, 0.15F, i < 2, -1.5, false);
						player.worldObj.spawnParticle("reddust", side.xCoord, player.posY - 1.5F, side.zCoord, -1, 0, 0);
					}
				}

				float decrement = (float) (1.0F - (increment * 0.5F));

				if (f > 1.0F && forwardVelocity > 0.0F)
				{
					player.rotationYaw += decrement;
				}
				if (f < -1.0F && forwardVelocity > 0.0F)
				{
					player.rotationYaw -= decrement;
				}
			}
			else
			{
				Vec3 forwardVec = getFrontCoords(player, 0, forwardVelocity);
				player.motionX = (forwardVec.xCoord - player.posX);
				player.motionZ = (forwardVec.zCoord - player.posZ);
				
//				Vec3 sideVec = getSideCoords(player, 0, horizontalVelocity > 0);
//				player.motionX = (sideVec.xCoord - player.posX);
//				player.motionZ = (sideVec.zCoord - player.posZ);
			}

			if (forwardVelocity <= 0) {forwardVelocity = 0;}
			if (forwardVelocity > 1) {forwardVelocity = 1;}

			if (nitro > 0 && nitroPressed && moveForward && player == mc.thePlayer && !inStealthMode)
			{
				--nitro;

				if (!prevNitro)
				{
					TransformersMod.packetPipeline.sendToServer(new PacketVehicleNitro(player, true));
					prevNitro = true;
				}

				for (int i = 0; i < 4; ++i)
				{
					Vec3 side = getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
					Random rand = new Random();
					player.worldObj.spawnParticle("smoke", side.xCoord, player.posY - 1.6F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
				}

				for (int i = 0; i < 10; ++i)
				{
					Vec3 side = getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
					Random rand = new Random();
					player.worldObj.spawnParticle("smoke", side.xCoord, player.posY - 1.6F, side.zCoord, rand.nextFloat() / 10, rand.nextFloat() / 10 + 0.05F, rand.nextFloat() / 10);
				}
			}
			else
			{
				if (prevNitro)
				{
					TransformersMod.packetPipeline.sendToServer(new PacketVehicleNitro(player, false));
					prevNitro = false;
				}
			}

			transformedPlayer.setNitro(nitro);
			transformedPlayer.setForwardVelocity(forwardVelocity);
			transformedPlayer.setHorizontalVelocity(horizontalVelocity);

			if (player.isInWater())
			{
				player.motionY = -0.1F;
			}
		}
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
		
		if(!pitch)
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
	public static void setForwardVelocity(EntityPlayer player, double vel) 
	{
		VehicleMotion transformedPlayer = transformedPlayerMap.get(player);

		if(transformedPlayer != null)
		{
			transformedPlayer.setForwardVelocity(vel);
		}
		else
		{
			transformedPlayerMap.put(player, new VehicleMotion(0, vel, 0));
		}
	}

	public static void setNitro(EntityPlayer player, int nitro) 
	{
		VehicleMotion transformedPlayer = transformedPlayerMap.get(player);

		if(transformedPlayer != null)
		{
			transformedPlayer.setNitro(nitro);
		}
		else
		{
			transformedPlayerMap.put(player, new VehicleMotion(nitro, 0, 0));
		}
	}

	public static void setHorizontalVelocity(EntityPlayer player, double vel) 
	{
		VehicleMotion transformedPlayer = transformedPlayerMap.get(player);

		if(transformedPlayer != null)
		{
			transformedPlayer.setHorizontalVelocity(vel);
		}
		else
		{
			transformedPlayerMap.put(player, new VehicleMotion(0, 0, vel));
		}
	}
	
	public static void resetPlayer(EntityPlayer player)
	{
		VehicleMotion vehicleMotion = transformedPlayerMap.get(player);
		vehicleMotion.setRoll(0);
	}
}