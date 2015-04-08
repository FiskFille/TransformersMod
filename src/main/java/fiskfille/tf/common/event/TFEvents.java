package fiskfille.tf.common.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.gui.GuiOverlay;
import fiskfille.tf.client.tick.TickHandler;
import fiskfille.tf.helper.TFShootManager;

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
