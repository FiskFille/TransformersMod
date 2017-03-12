package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEmpty extends ContainerBasic
{
    public ContainerEmpty(InventoryPlayer inventoryPlayer, int yOffset)
    {
        super(null);
        addPlayerInventory(inventoryPlayer, yOffset);
    }

    public ContainerEmpty(InventoryPlayer inventoryPlayer)
    {
        this(inventoryPlayer, 0);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId >= 0 && slotId < 27)
            {
                if (!mergeItemStack(itemstack1, 27, 36, false))
                {
                    return null;
                }
            }
            else if (slotId >= 27 && slotId < 36 && !mergeItemStack(itemstack1, 0, 27, false))
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

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
