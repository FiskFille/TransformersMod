package fiskfille.transformersmod.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fiskfille.transformersmod.gui.GuiOverlay;
import fiskfille.transformersmod.helper.TFShootManager;
import fiskfille.transformersmod.tick.TickHandler;

public class TFEvents 
{
	public static void registerEvents(Side side)
	{
		registerEventHandler(new CommonEventHandler());
		registerEventHandler(new TFShootManager());
		
		if (side.isClient())
		{
			registerEventHandler(new TickHandler());
			registerEventHandler(new ClientEventHandler());
			MinecraftForge.EVENT_BUS.register(new GuiOverlay(Minecraft.getMinecraft()));
		}
	}
	
	private static void registerEventHandler(Object obj)
	{
		FMLCommonHandler.instance().bus().register(obj);
		MinecraftForge.EVENT_BUS.register(obj);
	}
}
