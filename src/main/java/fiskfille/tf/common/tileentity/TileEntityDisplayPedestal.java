package fiskfille.tf.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityDisplayPedestal extends TileEntityContainer
{
    private ItemStack[] inventory = new ItemStack[1];
    
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(1, 1, 1);
    }

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
