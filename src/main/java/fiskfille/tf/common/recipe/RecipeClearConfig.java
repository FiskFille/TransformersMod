package fiskfille.tf.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import fiskfille.tf.common.item.ItemMachine;

public class RecipeClearConfig implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inventory, World world)
    {
        boolean flag = false;

        for (int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inventory.getStackInSlot(i);

            if (itemstack != null)
            {
                if (itemstack.getItem() instanceof ItemMachine && itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
                {
                    flag = true;
                }
                else
                {
                    return false;
                }
            }
        }

        return flag;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventory)
    {
        for (int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inventory.getStackInSlot(i);

            if (itemstack != null)
            {
                itemstack = itemstack.copy();
                itemstack.getTagCompound().removeTag("ConfigDataTF");
                itemstack.stackSize = 1;

                if (itemstack.getTagCompound().hasNoTags())
                {
                    itemstack.setTagCompound(null);
                }

                return itemstack;
            }
        }

        return null;
    }

    @Override
    public int getRecipeSize()
    {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return null;
    }
}
