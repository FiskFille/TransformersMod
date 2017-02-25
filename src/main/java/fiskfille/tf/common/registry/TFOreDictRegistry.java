package fiskfille.tf.common.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;

public class TFOreDictRegistry
{
    public static void register()
    {
        registerOre("dustEnergon", TFItems.energonDust);
        
        registerOre("blockGlass", TFBlocks.stainedGlass);
        registerOre("paneGlass", TFBlocks.stainedGlassPane);
        registerOre("blockClayHardened", Blocks.hardened_clay);
        registerOre("blockClayHardened", Blocks.stained_hardened_clay);
        registerOre("blockClayHardened", TFBlocks.stainedHardenedClay);
    }
    
    private static void registerOre(String name, Item item)
    {
        registerOre(name, item, OreDictionary.WILDCARD_VALUE);
    }

    private static void registerOre(String name, Item item, int metadata)
    {
        OreDictionary.registerOre(name, new ItemStack(item, 1, metadata));
    }

    private static void registerOre(String name, Block block)
    {
        registerOre(name, block, OreDictionary.WILDCARD_VALUE);
    }

    private static void registerOre(String name, Block block, int metadata)
    {
        OreDictionary.registerOre(name, new ItemStack(block, 1, metadata));
    }
}
