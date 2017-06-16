package fiskfille.tf.common.recipe;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TFRecipes
{
    public static void register()
    {
        GameRegistry.addSmelting(TFBlocks.TRANSFORMIUM_ORE, new ItemStack(TFItems.TRANSFORMIUM_FRAGMENT), 1.0F);
    }
}
