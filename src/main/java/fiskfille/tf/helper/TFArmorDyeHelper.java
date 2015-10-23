package fiskfille.tf.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import fiskfille.tf.common.item.ItemFuelCanister;

public class TFArmorDyeHelper
{
	public static boolean isDyed(ItemStack itemstack)
	{
		return getPrimaryColor(itemstack) != 0 || getSecondaryColor(itemstack) != 0;
	}
	
	public static int getPrimaryColor(ItemStack itemstack)
	{
		if (itemstack != null)
		{
			if (!itemstack.hasTagCompound())
	        {
	            itemstack.setTagCompound(new NBTTagCompound());
	            NBTTagCompound nbt = new NBTTagCompound();
//	            nbt.setInteger("PrimaryColor", 0xFFFFFF);
//	            nbt.setInteger("SecondaryColor", 0xFFFFFF);
	            
	            itemstack.getTagCompound().setTag("CustomColor", nbt);
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
	            NBTTagCompound nbt = new NBTTagCompound();
//	            nbt.setInteger("PrimaryColor", 0xFFFFFF);
//	            nbt.setInteger("SecondaryColor", 0xFFFFFF);
	            
	            itemstack.getTagCompound().setTag("CustomColor", nbt);
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

        if (!nbttagcompound.hasKey("CustomColor", 10))
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

        if (!nbttagcompound.hasKey("CustomColor", 10))
        {
            nbttagcompound.setTag("CustomColor", nbttagcompound1);
        }

        nbttagcompound1.setInteger("SecondaryColor", i);
	}
	
	public static boolean areColorsIdentical(ItemStack head, ItemStack chest, ItemStack legs)
	{
		return getPrimaryColor(head) == getPrimaryColor(chest) && getPrimaryColor(chest) == getPrimaryColor(legs) && getSecondaryColor(head) == getSecondaryColor(chest) && getSecondaryColor(chest) == getSecondaryColor(legs);
	}
}
