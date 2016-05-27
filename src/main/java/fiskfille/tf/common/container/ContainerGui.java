package fiskfille.tf.common.container;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerGui extends ContainerBasic
{
    public ContainerGui(InventoryPlayer inventoryPlayer)
    {
        addPlayerInventory(inventoryPlayer, 0);
    }

//    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
//    {
//        ItemStack itemstack = null;
//        Slot slot = (Slot) inventorySlots.get(slotId);
//        int FUEL = 0;
//
//        if (slot != null && slot.getHasStack())
//        {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//
//            if (slotId == FUEL)
//            {
//                if (!mergeItemStack(itemstack1, FUEL + 1, FUEL + 36 + 1, true))
//                {
//                    return null;
//                }
//
//                slot.onSlotChange(itemstack1, itemstack);
//            }
//            else if (slotId > FUEL)
//            {
//                if (itemstack1.getItem() == TFItems.filledFuelCanister)
//                {
//                    if (!mergeItemStack(itemstack1, FUEL, FUEL + 1, false))
//                    {
//                        return null;
//                    }
//                }
//                else if (slotId >= FUEL + 1 && slotId < FUEL + 28)
//                {
//                    if (!mergeItemStack(itemstack1, FUEL + 28, FUEL + 37, false))
//                    {
//                        return null;
//                    }
//                }
//                else if (slotId >= FUEL + 28 && slotId < FUEL + 37 && !mergeItemStack(itemstack1, FUEL + 1, FUEL + 28, false))
//                {
//                    return null;
//                }
//            }
//            else if (!mergeItemStack(itemstack1, FUEL + 1, FUEL + 37, false))
//            {
//                return null;
//            }
//
//            if (itemstack1.stackSize == 0)
//            {
//                slot.putStack((ItemStack) null);
//            }
//            else
//            {
//                slot.onSlotChanged();
//            }
//
//            if (itemstack1.stackSize == itemstack.stackSize)
//            {
//                return null;
//            }
//
//            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
//        }
//        
//        return itemstack;
//    }
}
