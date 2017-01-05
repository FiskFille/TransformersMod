package fiskfille.tf.common.tileentity;

import net.minecraft.item.ItemStack;

public class TileEntityDisplayPillar extends TileEntityContainer
{
    private ItemStack[] inventory = new ItemStack[1];

    @Override
    public ItemStack[] getItemStacks()
    {
        return inventory;
    }

    @Override
    public void setItemStacks(ItemStack[] itemstacks)
    {
        inventory = itemstacks;
    }

    public void setDisplayItem(ItemStack item, boolean sync)
    {
        if (item != getDisplayItem())
        {
            if (sync)
            {
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }

            setInventorySlotContents(0, ItemStack.copyItemStack(item));
        }
    }

    public ItemStack getDisplayItem()
    {
        return getStackInSlot(0);
    }

    @Override
    public String getInventoryName()
    {
        return "";
    }
}
