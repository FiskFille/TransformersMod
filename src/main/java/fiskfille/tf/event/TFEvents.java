package fiskfille.tf.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fiskfille.tf.gui.GuiOverlay;
import fiskfille.tf.tick.TickHandler;

public class TFEvents 
{
	public static void registerEvents(Side side)
	{
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
		FMLCommonHandler.instance().bus().register(new CommonEventHandler());
		
		if (side.isClient())
		{
			FMLCommonHandler.instance().bus().register(new TickHandler());
			MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
			FMLCommonHandler.instance().bus().register(new ClientEventHandler());
			MinecraftForge.EVENT_BUS.register(new GuiOverlay(Minecraft.getMinecraft()));
		}
	}
}
