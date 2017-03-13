package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.helper.TFArmorHelper;

public class ContainerDisplayStationArmor extends ContainerBasic
{
    public InventoryDisplayStationArmor craftMatrix = new InventoryDisplayStationArmor(this);

    public ContainerDisplayStationArmor(final InventoryPlayer inventoryPlayer, TileEntityDisplayStation tile)
    {
        super(tile);

        for (int i = 0; i < 4; ++i)
        {
            final int j = i;
            addSlotToContainer(new SlotDisplayStationArmor(this, tile, tile, i, 25, 18 + i * 18)
            {
                @Override
                public boolean isItemValid(ItemStack itemstack)
                {
                    return super.isItemValid(itemstack) && itemstack.getItem().isValidArmor(itemstack, j, inventoryPlayer.player);
                }
            });
        }

        for (int i = 0; i < 4; ++i)
        {
            final int j = i;
            addSlotToContainer(new SlotDisplayStationArmor(this, craftMatrix, tile, i, 47, 18 + i * 18)
            {
                @Override
                public boolean isItemValid(ItemStack itemstack)
                {
                    return super.isItemValid(itemstack) && itemstack.getItem().isValidArmor(itemstack, j, inventoryPlayer.player);
                }
            });
        }

        addPlayerInventory(inventoryPlayer, 20);

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemstack = tile.getStackInSlot(i);

            if (itemstack != null)
            {
                craftMatrix.setInventorySlotContents(i, TFArmorHelper.getArmorShell(itemstack));
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int MAX = 7;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId > MAX)
            {
                boolean flag = true;
                boolean flag1 = itemstack1.getItem() instanceof ItemTransformerArmor;
                
                for (int i = 0; i <= 8; ++i)
                {
                    Slot slot1 = (Slot) inventorySlots.get(i % 4);
                    
                    if (itemstack1.getItem().isValidArmor(itemstack1, i % 4, player) && (i < 4 ? flag1 : !flag1 && slot1.getHasStack()))
                    {
                        if (!mergeItemStack(itemstack1, i, i + 1, false))
                        {
                            return null;
                        }
                        
                        flag = false;
                        break;
                    }
                }
                
                if (flag)
                {
                    if (slotId >= MAX + 1 && slotId < MAX + 28)
                    {
                        // place in action bar
                        if (!mergeItemStack(itemstack1, MAX + 28, MAX + 37, false))
                        {
                            return null;
                        }
                    }
                    // item in action bar - place in player inventory
                    else if (slotId >= MAX + 28 && slotId < MAX + 37 && !mergeItemStack(itemstack1, MAX + 1, MAX + 28, false))
                    {
                        return null;
                    }
                }
            }
            // In one of the infuser slots; try to place in player inventory / action bar
            else if (!mergeItemStack(itemstack1, MAX + 1, MAX + 37, false))
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
