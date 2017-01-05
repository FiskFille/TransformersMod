package fiskfille.tf.common.recipe;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;

public class Dyes
{
    public static final int BLACK = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BROWN = 3;
    public static final int BLUE = 4;
    public static final int PURPLE = 5;
    public static final int CYAN = 6;
    public static final int SILVER = 7;
    public static final int GRAY = 8;
    public static final int PINK = 9;
    public static final int LIME = 10;
    public static final int YELLOW = 11;
    public static final int LIGHT_BLUE = 12;
    public static final int MAGENTA = 13;
    public static final int ORANGE = 14;
    public static final int WHITE = 15;

    public static final int DARK_RED = 16;
    public static final int BEIGE = 17;
    public static final int DARK_GRAY = 18;
    public static final int PALE_GREEN = 19;
    public static final int PALE_BROWN = 20;

    private LinkedList<ItemStack> itemstacks = Lists.newLinkedList();
    private static Map<Integer, String> names = Maps.newHashMap();

    public static LinkedList<Integer> dyes = Lists.newLinkedList();

    public Dyes(int... dyes)
    {
        for (int i = 0; i < dyes.length; ++i)
        {
            if (i % 2 == 1)
            {
                Item item = Items.dye;
                int id = dyes[i - 1];
                int amount = dyes[i];

                if (id >= 16)
                {
                    item = TFItems.dye;
                    id -= 16;
                }

                itemstacks.add(new ItemStack(item, amount, id));
            }
        }
    }

    public Dyes invert()
    {
        for (int i = 0; i < itemstacks.size(); ++i)
        {
            ItemStack itemstack = itemstacks.get(i);

            if (itemstack.getItem() == Items.dye)
            {
                itemstack.setItemDamage(15 - itemstack.getItemDamage());
            }
        }

        return this;
    }

    public ItemStack[] compile()
    {
        return itemstacks.toArray(new ItemStack[itemstacks.size()]);
    }

    public static ItemStack get(int id)
    {
        return get(id, 1);
    }

    public static ItemStack get(int id, int amount)
    {
        return new Dyes(id, amount).compile()[0];
    }

    public static ItemStack dye(Object obj, int id)
    {
        ItemStack itemstack = null;

        if (obj instanceof Block)
        {
            itemstack = new ItemStack((Block) obj);
        }
        else if (obj instanceof Item)
        {
            itemstack = new ItemStack((Item) obj);
        }
        else
        {
            itemstack = (ItemStack) obj;
        }

        if (itemstack != null && getName(itemstack) != null)
        {
            if (itemstack.getItem() instanceof ItemBlock && !itemstack.getItem().delegate.name().startsWith(TransformersMod.modid + ":"))
            {
                itemstack.setItemDamage(MathHelper.clamp_int(15 - id, 0, 15));
            }
            else
            {
                itemstack.setItemDamage(get(id, 1).getItemDamage());
            }

            return itemstack;
        }

        return null;
    }

    public static String getName(int id)
    {
        return names.get(id);
    }

    public static String getName(ItemStack itemstack)
    {
        if (itemstack != null)
        {
            Item item = itemstack.getItem();
            int id = itemstack.getItemDamage();

            if (item == Items.dye || item == TFItems.dye)
            {
                if (item == TFItems.dye)
                {
                    id += 16;
                }

                return getName(id);
            }
            else if (item instanceof ItemBlock)
            {
                Block block = Block.getBlockFromItem(item);

                if (block instanceof BlockStainedGlass || block instanceof BlockStainedGlassPane || block instanceof BlockColored || item instanceof ItemCloth)
                {
                    if (item.delegate.name().startsWith("minecraft:"))
                    {
                        id = 15 - id;
                    }

                    return getName(id);
                }
            }
        }

        return null;
    }

    static
    {
        Lists.newArrayList();

        for (Field field : Dyes.class.getFields())
        {
            String s = field.getType().getName();

            if (s.equals("int"))
            {
                try
                {
                    names.put(field.getInt(null), field.getName());
                    dyes.add(field.getInt(null));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
