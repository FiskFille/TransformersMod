package fiskfille.tf.common;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.capability.TFCapabilities;
import fiskfille.tf.common.generator.TFWorldGenHandler;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.recipe.TFRecipes;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy
{
    public void onPreInit()
    {
        TFBlocks.register();
        TFItems.register();

        TFRecipes.register();

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
}
