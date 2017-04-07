package fiskfille.tf.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import fiskfille.tf.TransformersAPI;

public class TileEntityDisplayPedestal extends TileEntityContainer
{
    @Override
    public int getSizeInventory()
    {
        return 1;
    }
    
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(1, 1, 1);
    }

    public void setDisplayItem(ItemStack itemstack, boolean sync)
    {
        if (itemstack != getDisplayItem())
        {
            if (sync)
            {
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }

            setInventorySlotContents(0, itemstack);
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
    
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return TransformersAPI.hasDisplayable(stack.getItem());
    }
}
