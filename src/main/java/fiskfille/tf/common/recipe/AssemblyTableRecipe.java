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
	public final ItemStack[] recipeDyes;
	private ItemStack recipeOutput;
	private boolean field_92101_f;

	public AssemblyTableRecipe(int width, int height, ItemStack[] ingredients, ItemStack[] dyes, ItemStack result)
	{
		recipeWidth = width;
		recipeHeight = height;
		recipeItems = ingredients;
		recipeDyes = dyes;
		recipeOutput = result;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return recipeOutput;
	}

	@Override
	public boolean matches(InventoryCrafting inventory, World world)
	{
		for (int i = 0; i <= 5 - recipeWidth; ++i)
		{
			for (int j = 0; j <= 5 - recipeHeight; ++j)
			{
				if (checkMatch(inventory, i, j))
				{
					return true;
				}
			}
		}

		return false;
	}

	private boolean checkMatch(InventoryCrafting inventory, int x, int y)
	{
		for (int row = 0; row < 5; ++row)
		{
			for (int column = 0; column < 5; ++column)
			{
				int x1 = row - x;
				int y1 = column - y;
				int id = x1 + y1 * recipeWidth;
				ItemStack itemstack = null;

				if (x1 >= 0 && y1 >= 0 && x1 < recipeWidth && y1 < recipeHeight)
				{
					itemstack = recipeItems[id];
				}
				
				if (id == 0)
				{
					itemstack = recipeDyes[0];
				}
				else if (id == 4)
				{
					itemstack = recipeDyes[1];
				}
				else if (id == 20)
				{
					itemstack = recipeDyes[2];
				}

				ItemStack itemstack1 = inventory.getStackInRowAndColumn(row, column);

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

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory)
	{
		ItemStack itemstack = getRecipeOutput().copy();

		if (field_92101_f)
		{
			for (int i = 0; i < inventory.getSizeInventory(); ++i)
			{
				ItemStack itemstack1 = inventory.getStackInSlot(i);

				if (itemstack1 != null && itemstack1.hasTagCompound())
				{
					itemstack.setTagCompound((NBTTagCompound) itemstack1.getTagCompound().copy());
				}
			}
		}

		return itemstack;
	}

	@Override
	public int getRecipeSize()
	{
		return recipeWidth * recipeHeight;
	}

	public AssemblyTableRecipe func_92100_c()
	{
		field_92101_f = true;
		return this;
	}
}
