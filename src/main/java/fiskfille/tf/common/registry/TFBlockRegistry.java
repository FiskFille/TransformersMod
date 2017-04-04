package fiskfille.tf.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.block.BlockMachineBase;
import fiskfille.tf.common.item.ItemBlockWithMetadata;
import fiskfille.tf.common.item.ItemMachine;

public class TFBlockRegistry
{
    public static void registerBlock(Block block, String name)
    {
        String unlocalizedName = name.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");

        block.setBlockName(unlocalizedName);
        block.setBlockTextureName(getMod() + ":" + unlocalizedName);
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
        block.setBlockTextureName(getMod() + ":" + unlocalizedName);
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

    public static void registerItemBlockAsTileEntity(Block block, String name, Class clazz, Class clazz1)
    {
        registerItemBlock(block, name, clazz1);
        GameRegistry.registerTileEntity(clazz, name);

        if (block instanceof BlockMachineBase)
        {
            ((BlockMachineBase) block).tileClass = clazz;
        }
    }

    public static void registerTileEntity(Block block, String name, Class clazz)
    {
        registerItemBlockAsTileEntity(block, name, clazz, ItemBlock.class);
    }

    public static void registerMachine(Block block, String name, Class clazz)
    {
        registerItemBlockAsTileEntity(block, name, clazz, ItemMachine.class);
    }
    
    private static String getMod()
    {
        return Loader.instance().activeModContainer().getModId();
    }
}
