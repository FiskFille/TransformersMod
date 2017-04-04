package fiskfille.tf.common.proxy;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fiskfille.tf.TFReflection;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.displayable.TFDisplayableManager;
import fiskfille.tf.client.gui.GuiHandlerTF;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.energon.TFEnergonManager;
import fiskfille.tf.common.entity.TFEntities;
import fiskfille.tf.common.event.CommonEventHandler;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.generator.TFWorldGenHandler;
import fiskfille.tf.common.item.ItemHandler;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.TFSubItems;
import fiskfille.tf.common.registry.TFOreDictRegistry;
import fiskfille.tf.common.tick.CommonTickHandler;
import fiskfille.tf.helper.TFShootManager;

public class CommonProxy
{
    protected Queue<Runnable> tasks = new LinkedBlockingDeque<Runnable>();

    public void preInit()
    {
        TFReflection.common();

        TFEnergonManager.registerEnergonTypes();
        TFItems.register();
        TFBlocks.register();
        TFFluids.register();
        TFOreDictRegistry.register();
        TFEntities.register();
        TFDisplayableManager.registerDisplayables();
        TFGui.register();
        
        NetworkRegistry.INSTANCE.registerGuiHandler(TransformersMod.modid, new GuiHandlerTF());
        registerEventHandler(new TFWorldGenHandler());
        registerEventHandler(new CommonEventHandler());
        registerEventHandler(new CommonTickHandler());
        registerEventHandler(new TFShootManager());
    }

    public void init()
    {
        ItemHandler.init();
    }

    public void registerEventHandler(Object obj)
    {
        MinecraftForge.EVENT_BUS.register(obj);
        MinecraftForge.ORE_GEN_BUS.register(obj);
        FMLCommonHandler.instance().bus().register(obj);
    }

    public World getWorld()
    {
        return null;
    }

    public EntityPlayer getPlayer()
    {
        return null;
    }

    public float getRenderTick()
    {
        return 0;
    }

    public void queueTask(Runnable task)
    {
        tasks.add(task);
    }

    public void runTasks()
    {
        while (!tasks.isEmpty())
        {
            Runnable task = tasks.poll();

            if (task != null)
            {
                task.run();
            }
        }
    }
}
