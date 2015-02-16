package fiskfille.tf.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.item.armor.ITransformerArmor;

public class RecipesDisplayItems implements IRecipe
{
	public boolean matches(InventoryCrafting inventoryCrafting, World world)
	{
		ItemStack[] stacks = new ItemStack[9];

		for (int i = 0; i < stacks.length; i++) 
		{
			stacks[i] = inventoryCrafting.getStackInSlot(i);
		}

		ItemStack head = null;
		ItemStack chest = null;
		ItemStack legs = null;
		ItemStack boots = null;

		for (ItemStack itemStack : stacks)
		{
			if(itemStack != null)
			{
				Item item = itemStack.getItem();

				if(item instanceof ITransformerArmor && item instanceof ItemArmor)
				{
					ItemArmor armorItem = (ItemArmor) item;

					if(armorItem.armorType == 0)
					{
						head = itemStack;
					}
					else if(armorItem.armorType == 1)
					{
						chest = itemStack;
					}
					else if(armorItem.armorType == 2)
					{
						legs = itemStack;
					}
					else if(armorItem.armorType == 3)
					{
						boots = itemStack;
					}
				}
			}
		}

		if(head != null && chest != null && legs != null && boots != null)
		{
			return true;
		}

		return false;
	}

	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
	{
		ItemStack itemstack = new ItemStack(TFItems.displayVehicle, 1);
		itemstack.setTagCompound(new NBTTagCompound());

		ItemStack[] stacks = new ItemStack[9];

		for (int i = 0; i < stacks.length; i++) 
		{
			stacks[i] = inventoryCrafting.getStackInSlot(i);
		}

		ItemStack head = null;
		ItemStack chest = null;
		ItemStack legs = null;
		ItemStack feet = null;

		for (ItemStack itemStack : stacks)
		{
			if(itemStack != null)
			{
				Item item = itemStack.getItem();

				if(item instanceof ITransformerArmor && item instanceof ItemArmor)
				{
					ItemArmor armorItem = (ItemArmor) item;

					if(armorItem.armorType == 0)
					{
						head = itemStack;
					}
					else if(armorItem.armorType == 1)
					{
						chest = itemStack;
					}
					else if(armorItem.armorType == 2)
					{
						legs = itemStack;
					}
					else if(armorItem.armorType == 3)
					{
						feet = itemStack;
					}
				}
			}
		}

		if(head != null && chest != null && legs != null && feet != null)
		{
			Item headItem = head.getItem();
			Item chestItem = chest.getItem();
			Item legsItem = legs.getItem();
			Item feetItem = feet.getItem();
			
			if (headItem == TFItems.skystrikeHelmet && chestItem == TFItems.skystrikeChestplate && legsItem == TFItems.skystrikeLeggings && feetItem == TFItems.skystrikeBoots)
			{
				itemstack.setItemDamage(0);
				setNBTData(head, chest, legs, feet, itemstack);
			}
			else if (headItem == TFItems.purgeHelmet && chestItem == TFItems.purgeChestplate && legsItem == TFItems.purgeLeggings && feetItem == TFItems.purgeBoots)
			{
				itemstack.setItemDamage(1);
				setNBTData(head, chest, legs, feet, itemstack);
			}
			else if (headItem == TFItems.vurpHelmet && chestItem == TFItems.vurpChestplate && legsItem == TFItems.vurpLeggings && feetItem == TFItems.vurpBoots)
			{
				itemstack.setItemDamage(2);
				setNBTData(head, chest, legs, feet, itemstack);
			}
			else if (headItem == TFItems.subwooferHelmet && chestItem == TFItems.subwooferChestplate && legsItem == TFItems.subwooferLeggings && feetItem == TFItems.subwooferBoots)
			{
				itemstack.setItemDamage(3);
				setNBTData(head, chest, legs, feet, itemstack);
			}
			else if (headItem == TFItems.cloudtrapHelmet && chestItem == TFItems.cloudtrapChestplate && legsItem == TFItems.cloudtrapLeggings && feetItem == TFItems.cloudtrapBoots)
			{
				itemstack.setItemDamage(4);
				setNBTData(head, chest, legs, feet, itemstack);
			}
		}

		return itemstack;
	}

	public void setNBTData(ItemStack head, ItemStack chest, ItemStack legs, ItemStack feet, ItemStack itemstack)
	{
		ItemStack[] itemstacks = {head, chest, legs, feet};
		NBTTagList itemsList = new NBTTagList();

		for (int i = 0; i < itemstacks.length; ++i)
		{
			if (itemstacks[i] != null)
			{
				NBTTagCompound itemTag = new NBTTagCompound();
				itemTag.setByte("Slot", (byte)i);
				itemstacks[i].writeToNBT(itemTag);
				itemsList.appendTag(itemTag);
			}
		}

		itemstack.getTagCompound().setTag("Items", itemsList);
	}

	public int getRecipeSize()
	{
		return 10;
	}

	public ItemStack getRecipeOutput()
	{
		return null;
	}
}