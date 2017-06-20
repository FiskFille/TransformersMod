package fiskfille.tf.common.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public class TFArmorDyeHelper
{
    public static boolean isDyed(ItemStack stack)
    {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("CustomColor");
    }

    public static void removeColor(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            if (!stack.hasTagCompound())
            {
                stack.setTagCompound(new NBTTagCompound());
            }

            if (stack.getTagCompound().hasKey("CustomColor"))
            {
                stack.getTagCompound().removeTag("CustomColor");
            }
        }
    }

    public static int getPrimaryColor(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            if (!stack.hasTagCompound())
            {
                stack.setTagCompound(new NBTTagCompound());
            }

            NBTTagCompound customColor = stack.getTagCompound().getCompoundTag("CustomColor");

            if (customColor != null)
            {
                return customColor.getInteger("PrimaryColor");
            }
        }

        return 0;
    }

    public static int getSecondaryColor(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            if (!stack.hasTagCompound())
            {
                stack.setTagCompound(new NBTTagCompound());
            }

            NBTTagCompound customColor = stack.getTagCompound().getCompoundTag("CustomColor");

            if (customColor != null)
            {
                return customColor.getInteger("SecondaryColor");
            }
        }

        return 0;
    }

    public static void setPrimaryColor(ItemStack stack, int primaryColor)
    {
        NBTTagCompound tag = stack.getTagCompound();

        if (tag == null)
        {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        NBTTagCompound customColor = tag.getCompoundTag("CustomColor");

        if (!tag.hasKey("CustomColor", NBT.TAG_COMPOUND))
        {
            tag.setTag("CustomColor", customColor);
        }

        customColor.setInteger("PrimaryColor", primaryColor);
    }

    public static void setSecondaryColor(ItemStack stack, int color)
    {
        NBTTagCompound tag = stack.getTagCompound();

        if (tag == null)
        {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        NBTTagCompound customColor = tag.getCompoundTag("CustomColor");

        if (!tag.hasKey("CustomColor", NBT.TAG_COMPOUND))
        {
            tag.setTag("CustomColor", customColor);
        }

        customColor.setInteger("SecondaryColor", color);
    }

    public static boolean areColorsIdentical(ItemStack... stacks)
    {
        if (stacks.length > 1)
        {
            int primary = getPrimaryColor(stacks[0]);
            int secondary = getSecondaryColor(stacks[0]);

            for (ItemStack itemstack : stacks)
            {
                if (itemstack == null || getPrimaryColor(itemstack) != primary || getSecondaryColor(itemstack) != secondary)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
