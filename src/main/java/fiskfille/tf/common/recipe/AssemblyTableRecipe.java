package fiskfille.tf.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AssemblyTableRecipe implements IRecipe
{
    public final int recipeWidth;
    public final int recipeHeight;
    public final ItemStack[] recipeItems;
    private ItemStack recipeOutput;
    private boolean field_92101_f;

    public AssemblyTableRecipe(int width, int height, ItemStack[] ingredients, ItemStack result)
    {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeItems = ingredients;
        this.recipeOutput = result;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    public boolean matches(InventoryCrafting inventoryCrafting, World world)
    {
        for (int i = 0; i <= 5 - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= 5 - this.recipeHeight; ++j)
            {
                if (this.checkMatch(inventoryCrafting, i, j))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkMatch(InventoryCrafting inventoryCrafting, int x, int y)
    {
        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                int i1 = k - x;
                int j1 = l - y;
                ItemStack itemstack = null;

                if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight)
                {
                    itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
                }

                ItemStack itemstack1 = inventoryCrafting.getStackInRowAndColumn(k, l);

                if (itemstack1 != null || itemstack != null)
                {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null)
                    {
                        return false;
                    }

                    if (itemstack.getItem() != itemstack1.getItem())
                    {
                        return false;
                    }

                    if (itemstack1.stackSize < itemstack.stackSize)
                    {
                        return false;
                    }

                    if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage())
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
    {
        ItemStack itemstack = this.getRecipeOutput().copy();

        if (this.field_92101_f)
        {
            for (int i = 0; i < inventoryCrafting.getSizeInventory(); ++i)
            {
                ItemStack itemstack1 = inventoryCrafting.getStackInSlot(i);

                if (itemstack1 != null && itemstack1.hasTagCompound())
                {
                    itemstack.setTagCompound((NBTTagCompound) itemstack1.stackTagCompound.copy());
                }
            }
        }

        return itemstack;
    }

    public int getRecipeSize()
    {
        return this.recipeWidth * this.recipeHeight;
    }

    public AssemblyTableRecipe func_92100_c()
    {
        this.field_92101_f = true;
        return this;
    }
}