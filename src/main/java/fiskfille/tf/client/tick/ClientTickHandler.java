package fiskfille.tf.client.tick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.client.render.entity.CustomEntityRenderer;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.packet.PacketCloudtrapJetpack;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;

public class ClientTickHandler
{
	public static List<EntityPlayer> cloudtrapJetpacking = new ArrayList<EntityPlayer>();
	private Minecraft mc = Minecraft.getMinecraft();
	private boolean prevJetpacking;

	public void onPlayerTick(EntityPlayer player)
	{	
		ItemStack itemstack = player.getHeldItem();
		Transformer transformer = TFHelper.getTransformer(player);

		boolean inVehicleMode = TFDataManager.isInVehicleMode(player);

		int transformationTimer = TFDataManager.getTransformationTimer(player);
		float offsetY = getCameraOffset(player, transformer) + (float)transformationTimer / 20;

		CustomEntityRenderer.setOffsetY(player, offsetY);

		int stealthModeTimer = TFDataManager.getStealthModeTimer(player);

		if (stealthModeTimer < 5 && !TFDataManager.isInStealthMode(player))
		{
			TFDataManager.setStealthModeTimer(player, stealthModeTimer + 1);
		}
		if (stealthModeTimer > 0 && TFDataManager.isInStealthMode(player))
		{
			TFDataManager.setStealthModeTimer(player, stealthModeTimer - 1);
		}
		
		if (TFHelper.isPlayerVurp(player) && itemstack != null && itemstack.getItem() == TFItems.vurpsSniper)
		{
			if (TFKeyBinds.keyBindingZoom.getIsKeyPressed())
			{
				if (TFDataManager.getZoomTimer(player) < 10)
				{
					TFDataManager.setZoomTimer(player, TFDataManager.getZoomTimer(player) + 1);
				}
			}
			else
			{
				if (TFDataManager.getZoomTimer(player) > 0)
				{
					TFDataManager.setZoomTimer(player, TFDataManager.getZoomTimer(player) - 1);
				}
			}
		}
		
		VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

		if (inVehicleMode && transformationTimer < 10)
		{
			player.setSprinting(false);

			if (player == Minecraft.getMinecraft().thePlayer)
			{
				GameSettings gameSettings = mc.gameSettings;

				if (Keyboard.isKeyDown(Keyboard.KEY_R) && Minecraft.getMinecraft().currentScreen == null)
				{
					gameSettings.thirdPersonView = 2;
				}
				else if (TFKeyBinds.keyBindingVehicleFirstPerson.getIsKeyPressed())
				{
					gameSettings.thirdPersonView = 0;
				}
				else
				{
					gameSettings.thirdPersonView = 3;
				}

				if(transformer != null)
				{
					transformer.updateMovement(player);
				}
			}

			NitroParticleHandler.doNitroParticles(player);
		}
		else
		{
			if (transformer != null)
			{
				if (transformer.hasJetpack())
				{
					boolean isClientPlayer = mc.thePlayer == player;
					boolean jetpacking = mc.gameSettings.keyBindJump.getIsKeyPressed();

					if (isClientPlayer)
					{
						if (prevJetpacking != jetpacking)
						{
							TFPacketManager.networkWrapper.sendToServer(new PacketCloudtrapJetpack(player, jetpacking));
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

					if (jetpacking)
					{
						player.motionY += 0.09F;

						if (isClientPlayer)
						{
							for (int i = 0; i < 20; ++i)
							{
								Random rand = new Random();
								player.worldObj.spawnParticle("flame", player.posX, player.posY - 1.5F, player.posZ, rand.nextFloat() / 4 - 0.125F, -0.8F, rand.nextFloat() / 4 - 0.125F);
							}
						}
					}
				}
			}

			player.stepHeight = 0.5F;

			if (player.isPotionActive(Potion.resistance) && player.getActivePotionEffect(Potion.resistance).getDuration() < 1)
			{
				player.removePotionEffect(Potion.resistance.id);
			}
		}

		int nitro = transformedPlayer == null ? 0 : transformedPlayer.getNitro();
		boolean moveForward = Minecraft.getMinecraft().gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed();

		if ((nitro < 160 && (player.capabilities.isCreativeMode || !((nitroPressed && !TFDataManager.isInStealthMode(player)) && moveForward && inVehicleMode && transformationTimer < 10))))
		{
			++nitro;
		}

		TFMotionManager.setNitro(player, nitro);

		if(transformer == null && TFPlayerData.getData(player).vehicle)
		{
			TFDataManager.setInVehicleMode(player, false);
		}
	}

	private float getCameraOffset(EntityPlayer player, Transformer transformer) 
	{
		if (transformer != null)
		{
			if (TFDataManager.getTransformationTimer(player) > 10)
			{
				return transformer.getCameraYOffset(player);
			}
			else
			{
				return transformer.getVehicleCameraYOffset(player);
			}
		}
		else
		{
			return -1;
		}
	}

	public void handleTransformation(EntityPlayer player)
	{
		Transformer transformer = TFHelper.getTransformer(player);

		int transformationTimer = TFDataManager.getTransformationTimer(player);
		boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
		VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

		if (transformationTimer < 20 && !inVehicleMode)
		{
			transformationTimer++;
			
			TFDataManager.setTransformationTimer(player, transformationTimer);

			if(transformer != null)
			{
				transformer.transformationTick(player, transformationTimer);
			}

			TFMotionManager.setForwardVelocity(player, 0.0D);

			if (transformationTimer == 19)
			{
				if (Minecraft.getMinecraft().thePlayer == player)
				{
					Minecraft.getMinecraft().gameSettings.thirdPersonView = TFConfig.firstPersonAfterTransformation ? 0 : Minecraft.getMinecraft().gameSettings.thirdPersonView;
				}
			}
		}
		else if (transformationTimer > 0 && inVehicleMode)
		{
			transformationTimer--;
			
			TFDataManager.setTransformationTimer(player, transformationTimer);

			if(transformer != null)
			{
				transformer.transformationTick(player, transformationTimer);
			}
		}
	}

	public void onClientTickEnd()
	{
		EntityPlayer player = mc.thePlayer;

		try
		{
			Transformer transformer = TFHelper.getTransformer(player);

			float thirdPersonDistance = 4.0F - (2.0F - (float)TFDataManager.getTransformationTimer(player) / 10);

			if (transformer != null && (transformer.canZoom(player)) && TFDataManager.isInVehicleMode(player) && TFKeyBinds.keyBindingZoom.getIsKeyPressed())
			{
				thirdPersonDistance = transformer.getZoomAmount(player);
			}
			else
			{
				thirdPersonDistance = transformer.getThirdPersonDistance(player);
			}

			ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.entityRenderer, thirdPersonDistance, new String[] { "thirdPersonDistance", "E", "field_78490_B" });
		}
		catch (Exception e) {}
	}

	public void onClientTickStart()
	{

	}
}