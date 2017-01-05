package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;

public class ContainerGroundBridge extends ContainerBasic
{
    public InventoryGroundBridge inventory;

    public ContainerGroundBridge(InventoryPlayer inventoryPlayer, InventoryGroundBridge inventoryGroundBridge, TileEntityControlPanel tile)
    {
        super(tile);
        inventory = inventoryGroundBridge;

        addSlotToContainer(new Slot(inventoryGroundBridge, 0, 13, 56)
        {
            @Override
            public boolean isItemValid(ItemStack itemstack)
            {
                return itemstack.getItem() == TFItems.csd;
            }
        });

        addPlayerInventory(inventoryPlayer, 0);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return super.canInteractWith(player) && player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int CSD = 0;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId > CSD)
            {
                if (itemstack1.getItem() == TFItems.csd)
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

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
