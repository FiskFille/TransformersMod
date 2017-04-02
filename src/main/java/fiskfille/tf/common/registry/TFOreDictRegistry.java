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
        registerOre("dye", TFItems.dye);
        
        // Minerals
        registerOre("transformium", TFItems.transformiumFragment);
        registerOre("ingotTransformiumAlloy", TFItems.transformiumAlloy);
        registerOre("ingotFluxAlloyCrude", TFItems.crudeFluxAlloy);
        registerOre("ingotFluxAlloyRefined", TFItems.refinedFluxAlloy);
        registerOre("ingotEnergonAlloy", TFItems.energonAlloy);
        registerOre("nuggetTransformiumAlloy", TFItems.transformiumAlloyNugget);
        registerOre("nuggetFluxAlloyCrude", TFItems.crudeFluxAlloyNugget);
        registerOre("nuggetFluxAlloyRefined", TFItems.refinedFluxAlloyNugget);
        registerOre("nuggetEnergonAlloy", TFItems.energonAlloyNugget);
        registerOre("dustEnergon", TFItems.energonDust);
        registerOre("oreTransformium", TFBlocks.transformiumOre);
        registerOre("oreEnergon", TFBlocks.energonOre);
        registerOre("blockTransformium", TFBlocks.transformiumBlock);
        registerOre("blockTransformiumAlloy", TFBlocks.transformiumAlloyBlock);
        registerOre("blockFluxAlloyCrude", TFBlocks.crudeFluxAlloyBlock);
        registerOre("blockFluxAlloyRefined", TFBlocks.refinedFluxAlloyBlock);
        registerOre("blockEnergonAlloy", TFBlocks.energonAlloyBlock);
        registerOre("blockEnergon", TFBlocks.energonCube, TFBlocks.redEnergonCube);
        registerOre("blockEnergonBlue", TFBlocks.energonCube);
        registerOre("blockEnergonRed", TFBlocks.redEnergonCube);
        
        // Blocks
        registerOre("blockGlass", TFBlocks.stainedGlass);
        registerOre("paneGlass", TFBlocks.stainedGlassPane);
        registerOre("blockClayHardened", Blocks.hardened_clay, Blocks.stained_hardened_clay, TFBlocks.stainedHardenedClay);
        registerOre("wool", Blocks.wool, TFBlocks.wool);
        registerOre("carpet", Blocks.carpet, TFBlocks.carpet);
        registerOre("slabStone", new ItemStack(Blocks.stone_slab, 1, 0));
    }
    
    private static void registerOre(String name, Object... args)
    {
        for (Object obj : args)
        {
            if (obj instanceof Item)
            {
                OreDictionary.registerOre(name, new ItemStack((Item) obj, 1, OreDictionary.WILDCARD_VALUE));
            }
            else if (obj instanceof Block)
            {
                OreDictionary.registerOre(name, new ItemStack((Block) obj, 1, OreDictionary.WILDCARD_VALUE));
            }
            else if (obj instanceof ItemStack)
            {
                OreDictionary.registerOre(name, (ItemStack) obj);
            }
        }
    }
}
