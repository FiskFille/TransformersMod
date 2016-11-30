package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.item.TFItems;

public class InventoryGroundBridge implements IInventory
{
    private ItemStack[] stackResult = new ItemStack[1];

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return stackResult[0];
    }

    @Override
    public String getInventoryName()
    {
        return "Ground Bridge";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (stackResult[0] != null)
        {
            ItemStack itemstack = stackResult[0];
            stackResult[0] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (stackResult[0] != null)
        {
            ItemStack itemstack = stackResult[0];
            stackResult[0] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        stackResult[0] = itemstack;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public void markDirty() {}

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return itemstack.getItem() == TFItems.csd;
    }
}
