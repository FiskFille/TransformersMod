package fiskfille.tf.tick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.TFHelper;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.misc.TFMotionManager;
import fiskfille.tf.misc.TFNitroParticleHandler;
import fiskfille.tf.misc.VehicleMotion;
import fiskfille.tf.misc.VehicleType;
import fiskfille.tf.packet.PacketCloudtrapJetpack;
import fiskfille.tf.proxy.ClientProxy;
import fiskfille.tf.render.entity.CustomEntityRenderer;

public class ClientTickHandler
{
	public static List<EntityPlayer> cloudtrapJetpacking = new ArrayList<EntityPlayer>();
	private Minecraft mc = Minecraft.getMinecraft();
	private boolean prevJetpacking;

	public void onPlayerTick(EntityPlayer player)
	{	
		int transformationTimer = TFDataManager.getTransformationTimer(player);
		float offsetY = 1.0F - (float)transformationTimer / 20;
		
		if (transformationTimer == 0 && TFHelper.isPlayerCar(player))
		{
			offsetY += 0.1F;
		}
		
		CustomEntityRenderer.setOffsetY(player, -offsetY);
		boolean isTransformer = TFHelper.isPlayerTransformer(player);
		
		if (!isTransformer)
		{
			TFDataManager.setInVehicleMode(player, false);
		}

		boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
		
		if(!isTransformer && !inVehicleMode)
		{
			TFDataManager.setInStealthMode(player, false);
		}

		int stealthModeTimer = TFDataManager.getStealthModeTimer(player);

		if (stealthModeTimer < 5 && !TFDataManager.isInStealthMode(player))
		{
			TFDataManager.setStealthModeTimer(player, stealthModeTimer + 1);
		}
		if (stealthModeTimer > 0 && TFDataManager.isInStealthMode(player))
		{
			TFDataManager.setStealthModeTimer(player, stealthModeTimer - 1);
		}

		VehicleMotion transformedPlayer = TFMotionManager.transformedPlayerMap.get(player);

		if (player != null)
		{	
			if (inVehicleMode && transformationTimer < 10)
			{
				player.setSprinting(false);

				if(player == Minecraft.getMinecraft().thePlayer)
				{
					GameSettings gameSettings = mc.gameSettings;
					
					if (Keyboard.isKeyDown(Keyboard.KEY_R) && Minecraft.getMinecraft().currentScreen == null)
					{
						gameSettings.thirdPersonView = 2;
					}
					else if (ClientProxy.keyBindingVehicleFirstPerson.getIsKeyPressed())
					{
						gameSettings.thirdPersonView = 0;
					}
					else
					{
						gameSettings.thirdPersonView = 3;
					}
					
					VehicleType transformerType = TFHelper.getTransformerType(player);

					if (transformerType != null)
					{
						transformerType.performMotion(player);
					}
				}
				
				TFNitroParticleHandler.makeNitroParticles(player);
			}
			else
			{
				if(TFHelper.isPlayerCloudtrap(player))
				{
					boolean isClientPlayer = mc.thePlayer == player;
					boolean jetpacking = mc.gameSettings.keyBindJump.getIsKeyPressed();
					if(isClientPlayer)
					{
						if(prevJetpacking != jetpacking)
						{
							TransformersMod.packetPipeline.sendToServer(new PacketCloudtrapJetpack(player, jetpacking));
							prevJetpacking = jetpacking;
						}
					}
					else
					{
						for (EntityPlayer cPlayer : cloudtrapJetpacking)
						{
							for (int i = 0; i < 20; ++i)
							{
								Random rand = new Random();
								cPlayer.worldObj.spawnParticle("flame", cPlayer.posX, cPlayer.posY, cPlayer.posZ, rand.nextFloat() / 4 - 0.125F, -0.8F, rand.nextFloat() / 4 - 0.125F);
							}
						}
					}

					if(jetpacking)
					{
						player.motionY += 0.09F;

						if(isClientPlayer)
						{
							for (int i = 0; i < 20; ++i)
							{
								Random rand = new Random();
								player.worldObj.spawnParticle("flame", player.posX, player.posY - 1.5F, player.posZ, rand.nextFloat() / 4 - 0.125F, -0.8F, rand.nextFloat() / 4 - 0.125F);
							}
						}
					}
				}

				if (TFHelper.isPlayerSkystrike(player) && !player.capabilities.isFlying)
				{
					if (player.motionY < 0.0D)
					{
						player.motionY *= 0.85F;
					}
					else
					{
						player.motionY += 0.02D;
					}
				}
				player.stepHeight = 0.5F;

				if (player.isPotionActive(Potion.resistance) && player.getActivePotionEffect(Potion.resistance).getDuration() < 1)
				{
					player.removePotionEffect(Potion.resistance.id);
				}
			}
		}

		int nitro = transformedPlayer == null ? 0 : transformedPlayer.getNitro();
		boolean moveForward = Minecraft.getMinecraft().gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed();

		if (nitro < 160 && !((nitroPressed && !TFDataManager.isInStealthMode(player)) && moveForward && inVehicleMode && transformationTimer < 10))
		{
			++nitro;
		}

		TFMotionManager.setNitro(player, nitro);
	}
	
	public void handleTransformation(EntityPlayer player)
	{
		int transformationTimer = TFDataManager.getTransformationTimer(player);
		boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
		VehicleMotion transformedPlayer = TFMotionManager.transformedPlayerMap.get(player);
		
		if (transformationTimer < 20 && !inVehicleMode)
		{
			TFDataManager.setTransformationTimer(player, transformationTimer + 1);
			TFMotionManager.setForwardVelocity(player, 0.0D);

			if (transformationTimer == 19)
			{
				if(Minecraft.getMinecraft().thePlayer == player)
				{
					Minecraft.getMinecraft().gameSettings.thirdPersonView = TFConfig.firstPersonAfterTransformation ? 0 : Minecraft.getMinecraft().gameSettings.thirdPersonView;
				}
			}
		}
		else if (transformationTimer > 0 && inVehicleMode)
		{
			TFDataManager.setTransformationTimer(player, transformationTimer - 1);

			if (TFHelper.isPlayerCar(player))
			{
				double forwardVelocity = transformedPlayer == null ? 0 : transformedPlayer.getForwardVelocity();
				double horizontalVelocity = transformedPlayer == null ? 0 : transformedPlayer.getHorizontalVelocity();
				Vec3 forwardVec = TFMotionManager.getFrontCoords(player, 0, forwardVelocity);
				Vec3 horizontalVec = TFMotionManager.getSideCoords(player, 0, horizontalVelocity > 0);

				forwardVelocity += 0.05D;
				horizontalVelocity += 0.05D;

				player.motionX = (forwardVec.xCoord - player.posX);
				player.motionZ = (forwardVec.zCoord - player.posZ);
				
				player.motionX = (horizontalVec.xCoord - player.posX);
				player.motionZ = (horizontalVec.zCoord - player.posZ);
				
				if (transformationTimer >= 14)
				{
					player.motionY += 0.2D;
				}
			}
		}
	}
	
	public void onClientTickEnd()
	{
		EntityPlayer player = mc.thePlayer;

		try
		{
			float thirdPersonDistance = 4.0F - (2.0F - (float)TFDataManager.getTransformationTimer(player) / 10);

			if ((TFHelper.isPlayerPurge(player) || TFDataManager.isInStealthMode(player) ) && TFDataManager.isInVehicleMode(player) && ClientProxy.keyBindingZoom.getIsKeyPressed())
			{
				thirdPersonDistance = 0.1F;
			}
			else if(TFHelper.isPlayerJet(player))
			{
				thirdPersonDistance = 4.0F;
			}

			ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.entityRenderer, thirdPersonDistance, new String[] { "thirdPersonDistance", "E", "field_78490_B" });
		}
		catch (Exception e) {}
	}

	public void onClientTickStart()
	{

	}
}