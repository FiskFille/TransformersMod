package fiskfille.tf.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ISpecialArmor;

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
    
    public static int getArmorValue(EntityPlayer player, ItemStack itemstack, int slot)
    {
        if (itemstack != null)
        {
            if (itemstack.getItem() instanceof ISpecialArmor)
            {
                return ((ISpecialArmor) itemstack.getItem()).getArmorDisplay(player, itemstack, slot);
            }
            else if (itemstack.getItem() instanceof ItemArmor)
            {
                return ((ItemArmor) itemstack.getItem()).damageReduceAmount;
            }
        }

        return 0;
    }
}
