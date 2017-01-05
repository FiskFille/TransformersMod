package fiskfille.tf.main.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.tf.data.TFPlayerData;
import fiskfille.tf.main.MainClass;
import fiskfille.tf.main.TFHelper;

public class TickHandler
{
	public static int time = 0;
	public static boolean hasDisplayedEasterEggMessage = false;
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) 
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		if (ClientProxy.keyBindingTransform.getIsKeyPressed() && Minecraft.getMinecraft().currentScreen == null && (TFHelper.isPlayerSkystrike(player) || TFHelper.isPlayerPurge(player) || TFHelper.isPlayerVurp(player) || TFHelper.isPlayerSubwoofer(player)))
		{
			if (TFPlayerData.isInVehicleMode(player) && TFPlayerData.getTransformationTimer(player) == 0)
			{
				TFPlayerData.setInVehichleMode(player, false);
				TFPlayerData.sync(player);
				player.playSound(MainClass.modid + ":transform_robot", 1.0F, 1.0F);
			}
			else if (!TFPlayerData.isInVehicleMode(player) && TFPlayerData.getTransformationTimer(player) == 10)
			{
				TFPlayerData.setInVehichleMode(player, true);
				TFPlayerData.sync(player);
				player.playSound(MainClass.modid + ":transform_vehicle", 1.0F, 1.0F);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		++time;
		EntityPlayer player = event.player;
		
		if (player.worldObj.isRemote && time % 2 == 0)
		{
			MainClass.proxy.tickHandler.onPlayerTick(player);
		}
	}
	
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		switch (event.phase)
		{
		case START:
		{
			MainClass.proxy.tickHandler.onClientTickStart();
			break;
		}
		case END:
		{
			MainClass.proxy.tickHandler.onClientTickEnd();
			break;
		}
		}		
	}
}