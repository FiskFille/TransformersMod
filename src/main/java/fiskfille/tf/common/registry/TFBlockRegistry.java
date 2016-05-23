package fiskfille.tf.common.registry;

import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.ItemBlockWithMetadata;

public class TFBlockRegistry
{
    public static void registerBlock(Block block, String name)
    {
        String unlocalizedName = name.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");

        block.setBlockName(unlocalizedName);
        block.setBlockTextureName(TransformersMod.modid + ":" + unlocalizedName);
        block.setCreativeTab(TransformersMod.tabTransformers);

        GameRegistry.registerBlock(block, unlocalizedName);
    }

    public static void registerItemBlock(Block block, String name, Class clazz)
    {
        String unlocalizedName = name.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");

        block.setBlockName(unlocalizedName);
        block.setBlockTextureName(TransformersMod.modid + ":" + unlocalizedName);
        block.setCreativeTab(TransformersMod.tabTransformers);

        GameRegistry.registerBlock(block, clazz, unlocalizedName);
    }

    public static void registerItemBlock(Block block, String name)
    {
        registerItemBlock(block, name, ItemBlockWithMetadata.class);
    }

    public static void registerOre(Block block, String name, String oreDictName)
    {
        registerBlock(block, name);
        OreDictionary.registerOre(oreDictName, block);
    }

    public static void registerOreAsTileEntity(Block block, String name, String oreDictName, Class clazz)
    {
        registerOre(block, name, oreDictName);
        GameRegistry.registerTileEntity(clazz, name);
    }

    public static void registerTileEntity(Block block, String name, Class clazz)
    {
        registerBlock(block, name);
        GameRegistry.registerTileEntity(clazz, name);
    }

    public static void registerItemBlockAsTileEntity(Block block, String name, Class clazz, Class clazz1)
    {
        registerItemBlock(block, name, clazz1);
        GameRegistry.registerTileEntity(clazz, name);
    }
}
