package fiskfille.tf.main.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.Vec3;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.data.TFPlayerData;
import fiskfille.tf.main.TFConfig;
import fiskfille.tf.main.TFHelper;
import fiskfille.tf.render.entity.EntityRendererAlt;

public class ClientTickHandler
{
	private Minecraft mc = Minecraft.getMinecraft(); 

	public void onPlayerTick(EntityPlayer player)
	{
		float offsetY = 1.0F - (float)TFPlayerData.getTransformationTimer(player) / 10;
		EntityRendererAlt.offsetY = -offsetY;

		if (!TFHelper.getInstance().isPlayerSkystrike(player) && !TFHelper.isPlayerPurge(player) && !TFHelper.isPlayerVurp(player) && !TFHelper.isPlayerSubwoofer(player))
		{
			TFPlayerData.setInVehichleMode(player, false);
		}

		if (TFPlayerData.getTransformationTimer(player) < 10 && !TFPlayerData.isInVehicleMode(player))
		{
			TFPlayerData.setTransformationTimer(player, TFPlayerData.getTransformationTimer(player) + 1);
			TFVehichleModeMotionManager.velocityMap.put(player.getCommandSenderName(), 0.0D);

			if (TFPlayerData.getTransformationTimer(player) == 9)
			{
				Minecraft.getMinecraft().gameSettings.thirdPersonView = TFConfig.firstPersonAfterTransformation ? 0 : Minecraft.getMinecraft().gameSettings.thirdPersonView;
			}
		}

		if (TFPlayerData.getTransformationTimer(player) > 0 && TFPlayerData.isInVehicleMode(player))
		{
			TFPlayerData.setTransformationTimer(player, TFPlayerData.getTransformationTimer(player) - 1);

			if (TFHelper.isPlayerVurp(player) || TFHelper.isPlayerSubwoofer(player))
			{
				double vel = TFVehichleModeMotionManager.velocityMap.get(player.getCommandSenderName()) == null ? 0 : TFVehichleModeMotionManager.velocityMap.get(player.getCommandSenderName());
				Vec3 vec3 = TFVehichleModeMotionManager.getFrontCoords(player, 0, vel);

				vel += 0.05D;

				player.motionX = (vec3.xCoord - player.posX);
				player.motionZ = (vec3.zCoord - player.posZ);

				if (TFPlayerData.getTransformationTimer(player) >= 7)
				{
					player.motionY += 0.2D;
				}

				TFVehichleModeMotionManager.velocityMap.put(player.getCommandSenderName(), vel);
			}
		}


		if (player != null)
		{	
			if (TFPlayerData.isInVehicleMode(player) && TFPlayerData.getTransformationTimer(player) < 5)
			{
				player.setSprinting(false);
				Minecraft.getMinecraft().gameSettings.viewBobbing = false;
				Minecraft.getMinecraft().gameSettings.thirdPersonView = Keyboard.isKeyDown(Keyboard.KEY_R) && Minecraft.getMinecraft().currentScreen == null ? 2 : 3;

				if (TFHelper.isPlayerSkystrike(player))
				{
					TFVehichleModeMotionManager.motionJet(player);
				}
				if (TFHelper.isPlayerPurge(player))
				{
					TFVehichleModeMotionManager.motionTank(player);
				}
				if (TFHelper.isPlayerVurp(player))
				{
					TFVehichleModeMotionManager.motionCar(player);
				}
				if (TFHelper.isPlayerSubwoofer(player))
				{
					TFVehichleModeMotionManager.motionCar(player);
				}
			}
			else
			{
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
				Minecraft.getMinecraft().gameSettings.viewBobbing = true;

				if (player.isPotionActive(Potion.resistance) && player.getActivePotionEffect(Potion.resistance).getDuration() < 1)
				{
					player.removePotionEffect(Potion.resistance.id);
				}
			}
		}

		int nitro = TFVehichleModeMotionManager.nitroMap.get(player.getCommandSenderName()) == null ? 0 : TFVehichleModeMotionManager.nitroMap.get(player.getCommandSenderName());
		boolean moveForward = Minecraft.getMinecraft().gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed();

		if (nitro < 160 && !(nitroPressed && moveForward && TFPlayerData.isInVehicleMode(player) && TFPlayerData.getTransformationTimer(player) < 5))
		{
			++nitro;
		}

		TFVehichleModeMotionManager.nitroMap.put(player.getCommandSenderName(), nitro);
	}

	public void onClientTickEnd()
	{
		EntityPlayer player = mc.thePlayer;
		
		try
		{
//			ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.entityRenderer, 0, new String[] { "camRoll", "R", "field_78495_O" });
			
			
			float thirdPersonDistance = 4.0F - (2.0F - (float)TFPlayerData.getTransformationTimer(player) / 5);
			ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.entityRenderer, TFHelper.isPlayerSkystrike(player) ? 4.0F : thirdPersonDistance, new String[] { "thirdPersonDistance", "E", "field_78490_B" });
		}
		catch (Exception e) {}
	}

	public void onClientTickStart()
	{

	}
}