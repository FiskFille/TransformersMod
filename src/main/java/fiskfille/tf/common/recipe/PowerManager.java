package fiskfille.tf.common.recipe;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class PowerManager
{
    public static Map<ItemStack, Integer> powerSources = Maps.newHashMap();

    public static void load()
    {
        addPowerSource(Items.redstone, 0, 400);
        addPowerSource(Blocks.redstone_block, 0, 3600);
    }

    public static void addPowerSource(Item item, int metadata, int amount)
    {
        powerSources.put(new ItemStack(item, 1, metadata), amount);
    }

    public static void addPowerSource(Block block, int metadata, int amount)
    {
        powerSources.put(new ItemStack(block, 1, metadata), amount);
    }

    public static boolean isPowerSource(Item item, int metadata)
    {
        return getPowerSourceAmount(item, metadata) > 0;
    }

    public static boolean isPowerSource(ItemStack itemstack)
    {
        return itemstack == null ? false : isPowerSource(itemstack.getItem(), itemstack.getItemDamage());
    }

    public static int getPowerSourceAmount(Item item, int metadata)
    {
        for (Map.Entry<ItemStack, Integer> e : powerSources.entrySet())
        {
            ItemStack itemstack = new ItemStack(item, 1, metadata);

            if (e.getKey().getItem() == item && e.getKey().getItemDamage() == metadata)
            {
                return e.getValue();
            }
        }

        return 0;
    }

    public static int getPowerSourceAmount(ItemStack itemstack)
    {
        return getPowerSourceAmount(itemstack.getItem(), itemstack.getItemDamage());
    }
}
