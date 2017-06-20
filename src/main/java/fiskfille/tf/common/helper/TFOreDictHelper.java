package fiskfille.tf.common.helper;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class TFOreDictHelper
{
    public static List<String> getAliases(ItemStack itemstack)
    {
        List<String> list = Lists.newArrayList();
        int[] ids = OreDictionary.getOreIDs(itemstack);

        for (int id : ids)
        {
            list.add(OreDictionary.getOreName(id));
        }

        return list;
    }
}
