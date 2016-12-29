package fiskfille.tf.common.registry;

import net.minecraft.block.Block;
import cpw.mods.fml.common.FMLCommonHandler;
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
        GameRegistry.registerBlock(block, unlocalizedName);

        if (FMLCommonHandler.instance().getSide().isClient() && block.getCreativeTabToDisplayOn() == null)
        {
            block.setCreativeTab(TransformersMod.tabTransformers);
        }
    }

    public static void registerItemBlock(Block block, String name, Class clazz)
    {
        String unlocalizedName = name.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");

        block.setBlockName(unlocalizedName);
        block.setBlockTextureName(TransformersMod.modid + ":" + unlocalizedName);
        GameRegistry.registerBlock(block, clazz, unlocalizedName);

        if (FMLCommonHandler.instance().getSide().isClient() && block.getCreativeTabToDisplayOn() == null)
        {
            block.setCreativeTab(TransformersMod.tabTransformers);
        }
    }

    public static void registerItemBlock(Block block, String name)
    {
        registerItemBlock(block, name, ItemBlockWithMetadata.class);
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
