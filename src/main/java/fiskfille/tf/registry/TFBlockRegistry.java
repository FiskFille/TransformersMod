package fiskfille.tf.registry;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class TFBlockRegistry
{
	public static ArrayList<Block> blockList = new ArrayList();
	
	
	public static void registerBlock(Block block, String name)
	{
		blockList.add(block);
		block.setBlockName(name.toLowerCase().replace(' ', '_').replace("'", ""));
		GameRegistry.registerBlock(block, name.toLowerCase().replace(' ', '_').replace("'", ""));
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
}