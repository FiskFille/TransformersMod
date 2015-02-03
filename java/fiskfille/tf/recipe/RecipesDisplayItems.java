package fiskfille.tf.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import fiskfille.tf.item.TFItems;

public class RecipesDisplayItems implements IRecipe
{
    public boolean matches(InventoryCrafting inventoryCrafting, World world)
    {
		if (inventoryCrafting.getStackInSlot(0) != null && inventoryCrafting.getStackInSlot(1) != null && inventoryCrafting.getStackInSlot(2) == null && inventoryCrafting.getStackInSlot(3) != null && inventoryCrafting.getStackInSlot(4) != null && inventoryCrafting.getStackInSlot(5) == null && inventoryCrafting.getStackInSlot(6) == null && inventoryCrafting.getStackInSlot(7) == null && inventoryCrafting.getStackInSlot(8) == null)
		{
			Item head = inventoryCrafting.getStackInSlot(0).getItem();
			Item chest = inventoryCrafting.getStackInSlot(1).getItem();
			Item legs = inventoryCrafting.getStackInSlot(3).getItem();
			Item feet = inventoryCrafting.getStackInSlot(4).getItem();
			
	    	if (head == TFItems.skystrikeHelmet && chest == TFItems.skystrikeChestplate && legs == TFItems.skystrikeLeggings && feet == TFItems.skystrikeBoots)
	    	{
	    		return true;
	    	}
	    	if (head == TFItems.purgeHelmet && chest == TFItems.purgeChestplate && legs == TFItems.purgeLeggings && feet == TFItems.purgeBoots)
	    	{
	    		return true;
	    	}
	    	if (head == TFItems.vurpHelmet && chest == TFItems.vurpChestplate && legs == TFItems.vurpLeggings && feet == TFItems.vurpBoots)
	    	{
	    		return true;
	    	}
	    	if (head == TFItems.subwooferHelmet && chest == TFItems.subwooferChestplate && legs == TFItems.subwooferLeggings && feet == TFItems.subwooferBoots)
	    	{
	    		return true;
	    	}
	    	if (head == TFItems.cloudtrapHelmet && chest == TFItems.cloudtrapChestplate && legs == TFItems.cloudtrapLeggings && feet == TFItems.cloudtrapBoots)
	    	{
	    		return true;
	    	}
		}
    	
        return false;
    }
    
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
    {
    	ItemStack itemstack = new ItemStack(TFItems.displayVehicle, 1);
    	itemstack.setTagCompound(new NBTTagCompound());
    	
    	if (inventoryCrafting.getStackInSlot(0) != null && inventoryCrafting.getStackInSlot(1) != null && inventoryCrafting.getStackInSlot(2) == null && inventoryCrafting.getStackInSlot(3) != null && inventoryCrafting.getStackInSlot(4) != null && inventoryCrafting.getStackInSlot(5) == null && inventoryCrafting.getStackInSlot(6) == null && inventoryCrafting.getStackInSlot(7) == null && inventoryCrafting.getStackInSlot(8) == null)
		{
			Item head = inventoryCrafting.getStackInSlot(0).getItem();
			Item chest = inventoryCrafting.getStackInSlot(1).getItem();
			Item legs = inventoryCrafting.getStackInSlot(3).getItem();
			Item feet = inventoryCrafting.getStackInSlot(4).getItem();
			
	    	if (head == TFItems.skystrikeHelmet && chest == TFItems.skystrikeChestplate && legs == TFItems.skystrikeLeggings && feet == TFItems.skystrikeBoots)
	    	{
	    		itemstack.setItemDamage(0);
	    		setNBTData(inventoryCrafting, itemstack);
	    	}
	    	if (head == TFItems.purgeHelmet && chest == TFItems.purgeChestplate && legs == TFItems.purgeLeggings && feet == TFItems.purgeBoots)
	    	{
	    		itemstack.setItemDamage(1);
	    		setNBTData(inventoryCrafting, itemstack);
	    	}
	    	if (head == TFItems.vurpHelmet && chest == TFItems.vurpChestplate && legs == TFItems.vurpLeggings && feet == TFItems.vurpBoots)
	    	{
	    		itemstack.setItemDamage(2);
	    		setNBTData(inventoryCrafting, itemstack);
	    	}
	    	if (head == TFItems.subwooferHelmet && chest == TFItems.subwooferChestplate && legs == TFItems.subwooferLeggings && feet == TFItems.subwooferBoots)
	    	{
	    		itemstack.setItemDamage(3);
	    		setNBTData(inventoryCrafting, itemstack);
	    	}
	    	if (head == TFItems.cloudtrapHelmet && chest == TFItems.cloudtrapChestplate && legs == TFItems.cloudtrapLeggings && feet == TFItems.cloudtrapBoots)
	    	{
	    		itemstack.setItemDamage(4);
	    		setNBTData(inventoryCrafting, itemstack);
	    	}
		}
    	
        return itemstack;
    }
    
    public void setNBTData(InventoryCrafting inventoryCrafting, ItemStack itemstack)
    {
        ItemStack[] itemstacks = {inventoryCrafting.getStackInSlot(0), inventoryCrafting.getStackInSlot(1), inventoryCrafting.getStackInSlot(3), inventoryCrafting.getStackInSlot(4)};
        NBTTagList nbttaglist = new NBTTagList();
        
        for (int i = 0; i < itemstacks.length; ++i)
        {
            if (itemstacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                itemstacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        
        itemstack.getTagCompound().setTag("Items", nbttaglist);
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