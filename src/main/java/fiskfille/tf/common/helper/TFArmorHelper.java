package fiskfille.tf.common.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ISpecialArmor;

public class TFArmorHelper
{
    public static ItemStack getArmorShell(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            if (!stack.hasTagCompound())
            {
                stack.setTagCompound(new NBTTagCompound());
            }

            NBTTagCompound shell = stack.getTagCompound().getCompoundTag("ArmorShell");

            if (!stack.isEmpty())
            {
                return new ItemStack(shell);
            }
        }

        return ItemStack.EMPTY;
    }

    public static void setArmorShell(ItemStack stack, ItemStack shell)
    {
        if (!stack.isEmpty())
        {
            if (!stack.hasTagCompound())
            {
                stack.setTagCompound(new NBTTagCompound());
            }

            if (shell == null)
            {
                stack.getTagCompound().removeTag("ArmorShell");
            }
            else
            {
                NBTTagCompound nbt = new NBTTagCompound();
                shell.writeToNBT(nbt);
                stack.getTagCompound().setTag("ArmorShell", nbt);
            }
        }
    }

    public static int getArmorValue(EntityPlayer player, ItemStack stack, int slot)
    {
        if (!stack.isEmpty())
        {
            if (stack.getItem() instanceof ISpecialArmor)
            {
                return ((ISpecialArmor) stack.getItem()).getArmorDisplay(player, stack, slot);
            }
            else if (stack.getItem() instanceof ItemArmor)
            {
                return ((ItemArmor) stack.getItem()).damageReduceAmount;
            }
        }

        return 0;
    }
}
