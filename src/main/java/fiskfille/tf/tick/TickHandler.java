package fiskfille.tf.tick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.tf.TFHelper;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.data.TFPlayerData;
import fiskfille.tf.misc.TFMotionManager;
import fiskfille.tf.proxy.ClientProxy;
import fiskfille.tf.transformer.Transformer;

public class TickHandler
{
	public static int time = 0;
	public static boolean hasDisplayedEasterEggMessage = false;

	public static boolean prevViewBobbing;
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) 
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
		if (ClientProxy.keyBindingTransform.getIsKeyPressed() && Minecraft.getMinecraft().currentScreen == null && (TFHelper.isPlayerTransformer(player)))
		{
			GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
			int transformationTimer = TFDataManager.getTransformationTimer(player);
			
			if (inVehicleMode && transformationTimer == 0)
			{
				TFDataManager.setInVehicleMode(player, false);
				gameSettings.viewBobbing = prevViewBobbing;
				player.playSound(TransformersMod.modid + ":transform_robot", 1.0F, 1.0F);
				TFPlayerData.getData(player).stealthMode = false;
			}
			else if (!inVehicleMode && transformationTimer == 20)
			{
				TFDataManager.setInVehicleMode(player, true);
				prevViewBobbing = gameSettings.viewBobbing;
				gameSettings.viewBobbing = false;
				player.playSound(TransformersMod.modid + ":transform_vehicle", 1.0F, 1.0F);
				TFPlayerData.getData(player).stealthMode = false;
				TFMotionManager.resetPlayer(player);
			}

			EntityRenderer entityRenderer = Minecraft.getMinecraft().entityRenderer;

			try
			{
				float camRoll = ClientProxy.camRollField.getFloat(entityRenderer);
				ClientProxy.camRollField.set(entityRenderer, 0);
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

		if (ClientProxy.keyBindingStealthMode.getIsKeyPressed())
		{
			Transformer transformer = TFHelper.getTransformer(player);
			
			if (transformer != null)
			{
				if (inVehicleMode && Minecraft.getMinecraft().currentScreen == null && transformer.hasStealthForce(player))
				{
					int stealthModeTimer = TFDataManager.getStealthModeTimer(player);
					
					if (TFDataManager.isInStealthMode(player) && stealthModeTimer == 0)
					{
						TFDataManager.setInStealthMode(player, false);
						player.playSound(TransformersMod.modid + ":transform_robot", 1.0F, 1.5F);
					}
					else if (!TFDataManager.isInStealthMode(player) && stealthModeTimer == 5)
					{
						TFDataManager.setInStealthMode(player, true);
						player.playSound(TransformersMod.modid + ":transform_vehicle", 1.0F, 1.5F);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		++time;
		EntityPlayer player = event.player;

		if (player.worldObj.isRemote)
		{
			if (time % 2 == 0)
			{
				TransformersMod.proxy.tickHandler.onPlayerTick(player);
			}
			
			TransformersMod.proxy.tickHandler.handleTransformation(player);
		}
		
		
	}

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		switch (event.phase)
		{
		case START:
		{
			TransformersMod.proxy.tickHandler.onClientTickStart();
			break;
		}
		case END:
		{
			TransformersMod.proxy.tickHandler.onClientTickEnd();
			break;
		}
		}		
	}
}