package fiskfille.tf.transformer.base;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.entity.EntityTankShell;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.keybinds.TFKeyBinds;
import fiskfille.tf.misc.TFMotionManager;
import fiskfille.tf.misc.TFNitroParticleHandler;
import fiskfille.tf.misc.VehicleMotion;
import fiskfille.tf.packet.PacketVehicleNitro;

public abstract class TransformerTank extends Transformer
{
	public TransformerTank(String name) 
	{
		super(name);
	}

	@Override
	public String getShootSound()
	{
		return TransformersMod.modid + ":tankfire";
	}
	
	@Override
	public float fall(EntityPlayer player, float distance)
	{
		return TFDataManager.isInVehicleMode(player) ? 0 : super.fall(player, distance);
	}
	
	@Override
	public void updateMovement(EntityPlayer player)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		boolean moveForward = minecraft.gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || minecraft.gameSettings.keyBindSprint.getIsKeyPressed();

		player.stepHeight = 1.0F;
		VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

		int nitro = 0;
		double vel = 0;

		if (transformedPlayer != null)
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

			Vec3 vec3 = TFMotionManager.getFrontCoords(player, 0, vel);
			player.motionX = (vec3.xCoord - player.posX);
			player.motionZ = (vec3.zCoord - player.posZ);
			if (vel <= 0) {vel = 0;}
			if (vel > 0.2D) {vel = 0.2D;}
			if (vel < 0.02D && !moveForward) {vel = 0;}

			boolean prevNitro = TFMotionManager.prevNitro;
			
			if (nitro > 0 && nitroPressed && moveForward && player == Minecraft.getMinecraft().thePlayer)
			{
				if (!player.capabilities.isCreativeMode) --nitro;

				if (!prevNitro)
				{
					TransformersMod.packetPipeline.sendToServer(new PacketVehicleNitro(player, true));
					TFMotionManager.prevNitro = true;
				}

				for (int i = 0; i < 4; ++i)
				{
					Vec3 side = TFMotionManager.getBackSideCoords(player, 0.15F, i < 2, -0.6, false);
					Random rand = new Random();
					player.worldObj.spawnParticle("smoke", side.xCoord, player.posY - 1.4F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
				}
			}
			else
			{
				if (prevNitro)
				{
					TransformersMod.packetPipeline.sendToServer(new PacketVehicleNitro(player, false));
					TFMotionManager.prevNitro = false;
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
	
	@Override
	public boolean canShoot(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public Item getShootItem()
	{
		return TFItems.tankShell;
	}

	@Override
	public Entity getShootEntity(EntityPlayer player)
	{
		return new EntityTankShell(player.worldObj, player, 3);
	}
	
	@Override
	public void doNitroParticles(EntityPlayer player)
	{
		for (int i = 0; i < 4; ++i)
		{
			Vec3 side = TFNitroParticleHandler.getBackSideCoords(player, 0.15F, i < 2, -1, false);
			Random rand = new Random();
			player.worldObj.spawnParticle("smoke", side.xCoord, player.posY - 0F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
		}
	}
}
