package fiskfille.transformersmod.client.tick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.client.keybinds.TFKeyBinds;
import fiskfille.transformersmod.common.item.TFItems;
import fiskfille.transformersmod.common.motion.TFMotionManager;
import fiskfille.transformersmod.common.playerdata.TFDataManager;
import fiskfille.transformersmod.common.playerdata.TFPlayerData;
import fiskfille.transformersmod.common.proxy.ClientProxy;
import fiskfille.transformersmod.common.transformer.base.Transformer;
import fiskfille.transformersmod.helper.TFHelper;

public class TickHandler
{
	public static int time = 0;
	public static boolean hasDisplayedEasterEggMessage = false;

	public static boolean prevViewBobbing;
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) 
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ItemStack itemstack = player.getHeldItem();
		
		boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
		
		if (TFKeyBinds.keyBindingTransform.getIsKeyPressed() && Minecraft.getMinecraft().currentScreen == null && (TFHelper.isPlayerTransformer(player)))
		{
			GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
			int transformationTimer = TFDataManager.getTransformationTimer(player);
			
			if (inVehicleMode && transformationTimer == 0)
			{
				TFDataManager.setInVehicleMode(player, false);
				gameSettings.viewBobbing = prevViewBobbing;
				player.playSound(TransformersMod.modid + ":transform_robot", 1.0F, 1.0F);
				TFPlayerData.getData(player).stealthForce = false;
			}
			else if (!inVehicleMode && transformationTimer == 20)
			{
				TFDataManager.setInVehicleMode(player, true);
				prevViewBobbing = gameSettings.viewBobbing;
				gameSettings.viewBobbing = false;
				player.playSound(TransformersMod.modid + ":transform_vehicle", 1.0F, 1.0F);
				TFPlayerData.getData(player).stealthForce = false;
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

		if (TFKeyBinds.keyBindingStealthMode.getIsKeyPressed())
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
		
		if (TFDataManager.getZoomTimer(player) > 7)
		{
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1, 0));
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