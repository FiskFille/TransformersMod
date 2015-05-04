package fiskfille.tf.common.registry;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TransformersMod;

public class TFItemRegistry
{
    public static void registerItem(Item item, String name, String modId)
    {
        item.setCreativeTab(TransformersMod.tabTransformers);
        
        registerItemNoTab(item, name, modId);
    }
    
    public static void registerIngot(Item item, String name, String modId, String oreDictName)
    {
        registerItem(item, name, modId);
        OreDictionary.registerOre(oreDictName, item);
    }
    
    public static void registerItemNoTab(Item item, String name, String modId)
    {
        String unlocalizedName = name.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");
        
        item.setUnlocalizedName(unlocalizedName);
        item.setTextureName(modId + ":" + unlocalizedName);
        
        GameRegistry.registerItem(item, unlocalizedName);
    }
}