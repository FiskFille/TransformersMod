package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.tileentity.TileEntityColumn;

public class ContainerColumn extends ContainerBasic
{
    public ContainerColumn(InventoryPlayer inventoryPlayer, TileEntityColumn tile)
    {
        super(tile);

        for (int i = 0; i < tile.getSizeInventory(); ++i)
        {
            final int j = i;
            addSlotToContainer(new Slot(tile, i, 25 + i * 22, 75)
            {
                @Override
                public boolean isItemValid(ItemStack itemstack)
                {
                    return inventory.isItemValidForSlot(j, itemstack);
                }
            });
        }

        addPlayerInventory(inventoryPlayer, 24);
    }

    @Override
    public TileEntityColumn getTile()
    {
        return (TileEntityColumn) super.getTile();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int MAX = getTile().getSizeInventory() - 1;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId > MAX)
            {
                if (getTile().isItemValidForSlot(slotId, itemstack1))
                {
                    if (!mergeItemStack(itemstack1, 0, MAX + 2, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= MAX + 1 && slotId < MAX + 28)
                {
                    if (!mergeItemStack(itemstack1, MAX + 28, MAX + 37, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= MAX + 28 && slotId < MAX + 37 && !mergeItemStack(itemstack1, MAX + 1, MAX + 28, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, MAX + 1, MAX + 37, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
