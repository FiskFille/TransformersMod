package fiskfille.tf.common.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TransformersMod;

public class TFItemRegistry
{
    public static void registerItem(Item item, String name)
    {
        if (FMLCommonHandler.instance().getSide().isClient() && item.getCreativeTab() == null)
        {
            item.setCreativeTab(TransformersMod.tabTransformers);
        }

        registerItemNoTab(item, name);
    }

    public static void registerIngot(Item item, String name, String oreDictName)
    {
        registerItem(item, name);
        OreDictionary.registerOre(oreDictName, new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
    }

    public static void registerItemNoTab(Item item, String name)
    {
        String unlocalizedName = name.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");

        item.setUnlocalizedName(unlocalizedName);
        item.setTextureName(TransformersMod.modid + ":" + unlocalizedName);
        GameRegistry.registerItem(item, unlocalizedName);
    }
}
