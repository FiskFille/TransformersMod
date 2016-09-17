package fiskfille.tf.common.registry;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TransformersMod;

public class TFItemRegistry
{
    public static void registerItem(Item item, String name)
    {
        item.setCreativeTab(TransformersMod.tabTransformers);

        registerItemNoTab(item, name);
    }

    public static void registerIngot(Item item, String name, String oreDictName)
    {
        registerItem(item, name);

        if (item.getHasSubtypes())
        {
            List<ItemStack> list = Lists.newArrayList();
            item.getSubItems(item, item.getCreativeTab(), list);

            for (ItemStack itemstack : list)
            {
                OreDictionary.registerOre(oreDictName, itemstack);
            }
        }
        else
        {
            OreDictionary.registerOre(oreDictName, item);
        }
    }

    public static void registerItemNoTab(Item item, String name)
    {
        String unlocalizedName = name.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");

        item.setUnlocalizedName(unlocalizedName);
        item.setTextureName(TransformersMod.modid + ":" + unlocalizedName);

        GameRegistry.registerItem(item, unlocalizedName);
    }
}
