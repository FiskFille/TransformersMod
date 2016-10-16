package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.helper.TFArmorHelper;

public class ContainerDisplayStationArmor extends ContainerBasic
{
	public InventoryDisplayStationArmor craftMatrix = new InventoryDisplayStationArmor(this);
    private EntityPlayer player;

    public ContainerDisplayStationArmor(InventoryPlayer inventoryPlayer, TileEntityDisplayStation tile)
    {
        TileEntityDisplayStation tileentity = tile;
        player = inventoryPlayer.player;

        for (int i = 0; i < 4; ++i)
        {
            final int k = i;
            addSlotToContainer(new SlotDisplayStationArmor(this, tile, tile, i, 25, 18 + i * 18)
            {
                @Override
                public boolean isItemValid(ItemStack itemstack)
                {
                    return !(itemstack == null || !(itemstack.getItem() instanceof ItemTransformerArmor)) && itemstack.getItem().isValidArmor(itemstack, k, player);

                }

                @Override
                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex()
                {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }
        
        for (int i = 0; i < 4; ++i)
        {
            final int k = i;
            addSlotToContainer(new SlotDisplayStationArmor(this, craftMatrix, tile, i, 47, 18 + i * 18)
            {
                @Override
                public int getSlotStackLimit()
                {
                    return 1;
                }

                @Override
                public boolean isItemValid(ItemStack itemstack)
                {
                    return !(itemstack == null || itemstack.getItem() instanceof ItemTransformerArmor) && itemstack.getItem().isValidArmor(itemstack, k, player);

                }

                @Override
                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex()
                {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }
        
        addPlayerInventory(inventoryPlayer, 20);
        
        for (int i = 0; i < 4; ++i)
        {
        	ItemStack itemstack = tileentity.getStackInSlot(i);

        	if (itemstack != null)
        	{
        		craftMatrix.setInventorySlotContents(i, TFArmorHelper.getArmorShell(itemstack));
        	}
        }
    }

    @Override
    public void onCraftMatrixChanged(IInventory iinventory)
    {
    	super.onCraftMatrixChanged(iinventory);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int HEAD = 0;
        int CHEST = 1;
        int LEGS = 2;
        int FEET = 3;
        int HEAD2 = 4;
        int CHEST2 = 5;
        int LEGS2 = 6;
        int FEET2 = 7;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // If itemstack is in Output stack
            /*if (slotId == LEGS2)
            {
                // try to place in player inventory / action bar; add 36 + 1 because mergeItemStack uses < index,
                // so the last slot in the inventory won't get checked if you don't add 1
                if (!mergeItemStack(itemstack1, LEGS2 + 1, LEGS2 + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            // itemstack is in player inventory, try to place in appropriate furnace slot
            else */if (slotId > FEET2)
            {
                if (itemstack1.getItem().isValidArmor(itemstack1, HEAD, player) && (itemstack1.getItem() instanceof ItemTransformerArmor))
                {
                    if (!mergeItemStack(itemstack1, HEAD, HEAD + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, CHEST, player) && (itemstack1.getItem() instanceof ItemTransformerArmor))
                {
                    if (!mergeItemStack(itemstack1, CHEST, CHEST + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, LEGS, player) && (itemstack1.getItem() instanceof ItemTransformerArmor))
                {
                    if (!mergeItemStack(itemstack1, LEGS, LEGS + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, FEET, player) && (itemstack1.getItem() instanceof ItemTransformerArmor))
                {
                    if (!mergeItemStack(itemstack1, FEET, FEET + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, HEAD2, player) && !(itemstack1.getItem() instanceof ItemTransformerArmor))
                {
                    if (!mergeItemStack(itemstack1, HEAD2, HEAD2 + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, CHEST2, player) && !(itemstack1.getItem() instanceof ItemTransformerArmor))
                {
                    if (!mergeItemStack(itemstack1, CHEST2, CHEST2 + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, LEGS2, player) && !(itemstack1.getItem() instanceof ItemTransformerArmor))
                {
                    if (!mergeItemStack(itemstack1, LEGS2, LEGS2 + 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem().isValidArmor(itemstack1, FEET2, player) && !(itemstack1.getItem() instanceof ItemTransformerArmor))
                {
                    if (!mergeItemStack(itemstack1, FEET2, FEET2 + 1, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= FEET2 + 1 && slotId < FEET2 + 28)
                {
                    // place in action bar
                    if (!mergeItemStack(itemstack1, FEET2 + 28, FEET2 + 37, false))
                    {
                        return null;
                    }
                }
                // item in action bar - place in player inventory
                else if (slotId >= FEET2 + 28 && slotId < FEET2 + 37 && !mergeItemStack(itemstack1, FEET2 + 1, FEET2 + 28, false))
                {
                    return null;
                }
            }
            // In one of the infuser slots; try to place in player inventory / action bar
            else if (!mergeItemStack(itemstack1, FEET2 + 1, FEET2 + 37, false))
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
