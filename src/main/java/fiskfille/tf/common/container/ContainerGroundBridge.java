package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.item.TFItems;

public class ContainerGroundBridge extends ContainerBasic
{
    public InventoryGroundBridge inventory;

    public ContainerGroundBridge(InventoryPlayer inventoryPlayer, InventoryGroundBridge inventoryGroundBridge)
    {
        super(null);
        inventory = inventoryGroundBridge;

        addSlotToContainer(new Slot(inventoryGroundBridge, 0, 13, 56)
        {
            @Override
            public boolean isItemValid(ItemStack itemstack)
            {
                return inventory.isItemValidForSlot(slotNumber, itemstack);
            }

            @Override
            public boolean canTakeStack(EntityPlayer player)
            {
                return isItemValid(getStack());
            }
        });

        addPlayerInventory(inventoryPlayer, 0);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int CSD = 0;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId == CSD)
            {
                if (!mergeItemStack(itemstack1, CSD + 1, CSD + 37, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotId > CSD)
            {
                Slot slot1 = (Slot) inventorySlots.get(CSD);

                if (slot1 != null && slot1.isItemValid(itemstack1))
                {
                    if (!mergeItemStack(itemstack1, CSD, CSD + 1, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= CSD + 1 && slotId < CSD + 28)
                {
                    if (!mergeItemStack(itemstack1, CSD + 28, CSD + 37, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= CSD + 28 && slotId < CSD + 37 && !mergeItemStack(itemstack1, CSD + 1, CSD + 28, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, CSD + 1, CSD + 37, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
