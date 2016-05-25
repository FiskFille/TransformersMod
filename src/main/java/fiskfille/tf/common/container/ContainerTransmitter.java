package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class ContainerTransmitter extends ContainerBasic
{
    private TileEntityTransmitter tileentity;
//    private int lastBurnTime;

    public ContainerTransmitter(InventoryPlayer inventoryPlayer, TileEntityTransmitter tile)
    {
        tileentity = tile;
        addSlotToContainer(new Slot(tile, 0, 47, 35)
        {
            public int getSlotStackLimit()
            {
                return 1;
            }
        });

        addPlayerInventory(inventoryPlayer, 0);
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

//    public void addCraftingToCrafters(ICrafting icrafting)
//    {
//        super.addCraftingToCrafters(icrafting);
//        icrafting.sendProgressBarUpdate(this, 0, tileentity.burnTime);
//    }
//
//    public void detectAndSendChanges()
//    {
//        super.detectAndSendChanges();
//
//        for (int i = 0; i < crafters.size(); ++i)
//        {
//            ICrafting icrafting = (ICrafting) crafters.get(i);
//
//            if (lastBurnTime != tileentity.burnTime)
//            {
//                icrafting.sendProgressBarUpdate(this, 0, tileentity.burnTime);
//            }
//        }
//
//        lastBurnTime = tileentity.burnTime;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public void updateProgressBar(int id, int value)
//    {
//        if (id == 0)
//        {
//            tileentity.burnTime = value;
//        }
//
//        if (id == 1)
//        {
//            tileentity.powerTime = value;
//        }
//
//        if (id == 2)
//        {
//            tileentity.liquidAmount = value;
//        }
//
//        if (id == 3)
//        {
//            tileentity.liquidColor = value;
//        }
//
//        if (id == 4)
//        {
//            tileentity.fillTime = value;
//        }
//
//        if (id == 5)
//        {
//            tileentity.currentMaxPowerTime = value;
//        }
//    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int FUEL = 0;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId == FUEL)
            {
                if (!mergeItemStack(itemstack1, FUEL + 1, FUEL + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotId > FUEL)
            {
                if (itemstack1.getItem() == TFItems.filledFuelCanister)
                {
                    if (!mergeItemStack(itemstack1, FUEL, FUEL + 1, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= FUEL + 1 && slotId < FUEL + 28)
                {
                    if (!mergeItemStack(itemstack1, FUEL + 28, FUEL + 37, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= FUEL + 28 && slotId < FUEL + 37 && !mergeItemStack(itemstack1, FUEL + 1, FUEL + 28, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, FUEL + 1, FUEL + 37, false))
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
