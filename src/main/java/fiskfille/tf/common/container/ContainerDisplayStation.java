package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;

public class ContainerDisplayStation extends ContainerBasic
{
    private TileEntityDisplayStation tileentity;
    private EntityPlayer player;
    
    public ContainerDisplayStation(InventoryPlayer inventoryPlayer, TileEntityDisplayStation tile)
    {
        tileentity = tile;
        player = inventoryPlayer.player;
        
        for (int i = 0; i < 4; ++i)
        {
            final int k = i;
            addSlotToContainer(new Slot(tile, i, 49, 18 + i * 18)
            {
                public int getSlotStackLimit()
                {
                    return 1;
                }

                public boolean isItemValid(ItemStack itemstack)
                {
                    if (itemstack == null)
                    {
                        return false;
                    }
                    
                    return itemstack.getItem().isValidArmor(itemstack, k, player);
                }

                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex()
                {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }
        
        addSlotToContainer(new Slot(tile, 4, 111, 45)
        {
            public int getSlotStackLimit()
            {
                return 1;
            }

            public boolean isItemValid(ItemStack itemstack)
            {
                if (itemstack == null)
                {
                    return false;
                }
                
                return itemstack.getItem() == TFItems.displayVehicle;
            }
        });

        addPlayerInventory(inventoryPlayer, 20);
    }
    
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
    
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(slotId);
        int HEAD = 0;
        int CHEST = 1;
        int LEGS = 2;
        int FEET = 3;
        int VEHICLE = 4;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // If itemstack is in Output stack
            if (slotId == VEHICLE)
            {
                // try to place in player inventory / action bar; add 36 + 1 because mergeItemStack uses < index,
                // so the last slot in the inventory won't get checked if you don't add 1
                if (!mergeItemStack(itemstack1, VEHICLE + 1, VEHICLE + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            // itemstack is in player inventory, try to place in appropriate furnace slot
            else if (slotId > VEHICLE)
            {
                if (itemstack1.getItem().isValidArmor(itemstack1, HEAD, player))
                {
                    if (!mergeItemStack(itemstack1, HEAD, HEAD + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, CHEST, player))
                {
                    if (!mergeItemStack(itemstack1, CHEST, CHEST + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, LEGS, player))
                {
                    if (!mergeItemStack(itemstack1, LEGS, LEGS + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, FEET, player))
                {
                    if (!mergeItemStack(itemstack1, FEET, FEET + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem() == TFItems.displayVehicle)
                {
                    if (!mergeItemStack(itemstack1, VEHICLE, VEHICLE + 1, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= VEHICLE + 1 && slotId < VEHICLE + 28)
                {
                    // place in action bar
                    if (!mergeItemStack(itemstack1, VEHICLE + 28, VEHICLE + 37, false))
                    {
                        return null;
                    }
                }
                // item in action bar - place in player inventory
                else if (slotId >= VEHICLE + 28 && slotId < VEHICLE + 37 && !mergeItemStack(itemstack1, VEHICLE + 1, VEHICLE + 28, false))
                {
                    return null;
                }
            }
            // In one of the infuser slots; try to place in player inventory / action bar
            else if (!mergeItemStack(itemstack1, VEHICLE + 1, VEHICLE + 37, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
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
