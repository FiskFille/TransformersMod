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
    public TileEntityControlPanel controlPanel;
    
    public ContainerGroundBridge(InventoryPlayer inventoryPlayer, InventoryGroundBridge inventoryGroundBridge, TileEntityControlPanel tile)
    {
        inventory = inventoryGroundBridge;
        controlPanel = tile;
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
        return controlPanel != null && player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote;
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int csd = 0;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId > csd)
            {
                if (itemstack1.getItem() == TFItems.csd)
                {
                    if (!mergeItemStack(itemstack1, csd, csd + 1, false))
                    {
                        return null;
                    }
                }
                // item in player's inventory, but not in action bar
                else if (slotId >= csd + 1 && slotId < csd + 28)
                {
                    // place in action bar
                    if (!mergeItemStack(itemstack1, csd + 28, csd + 37, false))
                    {
                        return null;
                    }
                }
                // item in action bar - place in player inventory
                else if (slotId >= csd + 28 && slotId < csd + 37 && !mergeItemStack(itemstack1, csd + 1, csd + 28, false))
                {
                    return null;
                }
            }
            // In one of the infuser slots; try to place in player inventory / action bar
            else if (!mergeItemStack(itemstack1, csd + 1, csd + 37, false))
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
