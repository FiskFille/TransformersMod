package fiskfille.tf.common;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.common.capability.TFCapabilities;
import fiskfille.tf.common.generator.TFWorldGenHandler;
import fiskfille.tf.common.network.TFNetworkManager;
import fiskfille.tf.common.recipe.TFRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy
{
    public void onPreInit()
    {
        TFRecipes.register();

        TFNetworkManager.register();

        TransformerManager.register();

        TFCapabilities.register();

        MinecraftForge.ORE_GEN_BUS.register(new TFWorldGenHandler());
    }

    public void onInit()
    {

    }

    public void onPostInit()
    {

    }

    public float getRenderTick()
    {
        return 1.0F;
    }

    public void schedule(Runnable runnable, MessageContext ctx)
    {
        WorldServer server = ctx.getServerHandler().player.getServerWorld();
        server.addScheduledTask(runnable);
    }

    public EntityPlayer getPlayer(MessageContext ctx)
    {
        return ctx.getServerHandler().player;
    }

    public void onRegisterModels()
    {
    }
}
