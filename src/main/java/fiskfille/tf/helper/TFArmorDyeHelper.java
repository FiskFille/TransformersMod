package fiskfille.tf.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public class TFArmorDyeHelper
{
    public static boolean isDyed(ItemStack itemstack)
    {
        return itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("CustomColor");
    }

    public static void removeColor(ItemStack itemstack)
    {
        if (itemstack != null)
        {
            if (!itemstack.hasTagCompound())
            {
                itemstack.setTagCompound(new NBTTagCompound());
            }

            if (itemstack.getTagCompound().hasKey("CustomColor"))
            {
                itemstack.getTagCompound().removeTag("CustomColor");
            }
        }
    }

    public static int getPrimaryColor(ItemStack itemstack)
    {
        if (itemstack != null)
        {
            if (!itemstack.hasTagCompound())
            {
                itemstack.setTagCompound(new NBTTagCompound());
            }

            NBTTagCompound nbt = itemstack.getTagCompound().getCompoundTag("CustomColor");

            if (nbt != null)
            {
                return nbt.getInteger("PrimaryColor");
            }
        }

        return 0;
    }

    public static int getSecondaryColor(ItemStack itemstack)
    {
        if (itemstack != null)
        {
            if (!itemstack.hasTagCompound())
            {
                itemstack.setTagCompound(new NBTTagCompound());
            }

            NBTTagCompound nbt = itemstack.getTagCompound().getCompoundTag("CustomColor");

            if (nbt != null)
            {
                return nbt.getInteger("SecondaryColor");
            }
        }

        return 0;
    }

    public static void setPrimaryColor(ItemStack itemstack, int i)
    {
        NBTTagCompound nbttagcompound = itemstack.getTagCompound();

        if (nbttagcompound == null)
        {
            nbttagcompound = new NBTTagCompound();
            itemstack.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("CustomColor");

        if (!nbttagcompound.hasKey("CustomColor", NBT.TAG_COMPOUND))
        {
            nbttagcompound.setTag("CustomColor", nbttagcompound1);
        }

        nbttagcompound1.setInteger("PrimaryColor", i);
    }

    public static void setSecondaryColor(ItemStack itemstack, int i)
    {
        NBTTagCompound nbttagcompound = itemstack.getTagCompound();

        if (nbttagcompound == null)
        {
            nbttagcompound = new NBTTagCompound();
            itemstack.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("CustomColor");

        if (!nbttagcompound.hasKey("CustomColor", NBT.TAG_COMPOUND))
        {
            nbttagcompound.setTag("CustomColor", nbttagcompound1);
        }

        nbttagcompound1.setInteger("SecondaryColor", i);
    }

    public static boolean areColorsIdentical(ItemStack... itemstacks)
    {
        if (itemstacks.length > 1)
        {
            int primary = getPrimaryColor(itemstacks[0]);
            int secondary = getSecondaryColor(itemstacks[0]);
            
            for (ItemStack itemstack : itemstacks)
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
