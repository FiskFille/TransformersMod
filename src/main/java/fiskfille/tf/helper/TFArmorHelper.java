package fiskfille.tf.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TFArmorHelper
{
	public static ItemStack getArmorShell(ItemStack itemstack)
	{
		if (itemstack != null)
        {
            if (!itemstack.hasTagCompound())
            {
                itemstack.setTagCompound(new NBTTagCompound());
            }

            NBTTagCompound nbt = itemstack.getTagCompound().getCompoundTag("ArmorShell");

            if (nbt != null)
            {
            	return ItemStack.loadItemStackFromNBT(nbt);
            }
        }
		
		return null;
	}
	
	public static void setArmorShell(ItemStack itemstack, ItemStack shell)
	{
		if (itemstack != null)
        {
            if (!itemstack.hasTagCompound())
            {
                itemstack.setTagCompound(new NBTTagCompound());
            }

            if (shell == null)
            {
            	itemstack.getTagCompound().removeTag("ArmorShell");
            }
            else
            {
            	NBTTagCompound nbt = new NBTTagCompound();
            	shell.writeToNBT(nbt);
            	itemstack.getTagCompound().setTag("ArmorShell", nbt);
            }
        }
	}
}
