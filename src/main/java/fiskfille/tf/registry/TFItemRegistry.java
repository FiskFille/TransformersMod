package fiskfille.tf.registry;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class TFItemRegistry
{
	public static ArrayList<Item> itemList = new ArrayList();
	
	
	public static void registerItem(Item item, String name, String modId)
	{
		String s = name.toLowerCase().replace(' ', '_').replace("'", "");
		itemList.add(item);
		item.setTextureName(s);
		item.setUnlocalizedName(s);
		GameRegistry.registerItem(item, s, modId);
	}
	
	public static void registerIngot(Item item, String name, String modId, String oreDictName)
	{
		registerItem(item, name, modId);
		OreDictionary.registerOre(oreDictName, item);
	}
}