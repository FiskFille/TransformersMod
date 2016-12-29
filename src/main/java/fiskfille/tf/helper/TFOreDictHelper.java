package fiskfille.tf.helper;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;

public class TFOreDictHelper
{
    public static List<String> getAliases(ItemStack itemstack)
    {
        List<String> list = Lists.newArrayList();
        int[] ids = OreDictionary.getOreIDs(itemstack);

        for (int i = 0; i < ids.length; ++i)
        {
            int id = ids[i];
            list.add(OreDictionary.getOreName(id));
        }

        return list;
    }
}
