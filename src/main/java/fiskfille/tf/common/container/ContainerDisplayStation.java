package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.component.IComponent;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.transformer.base.Transformer;

public class ContainerDisplayStation extends ContainerBasic
{
    private EntityPlayer player;

    public ContainerDisplayStation(InventoryPlayer inventoryPlayer, TileEntityDisplayStation tile)
    {
        super(tile);
        player = inventoryPlayer.player;

        for (int i = 0; i < 4; ++i)
        {
            final int j = i;
            addSlotToContainer(new Slot(tile, i, 13, 18 + i * 18)
            {
                @Override
                public int getSlotStackLimit()
                {
                    return 1;
                }

                @Override
                public boolean isItemValid(ItemStack itemstack)
                {
                    Transformer newArmor = null;
                    
                    if (itemstack.getItem() instanceof ItemTransformerArmor)
                    {
                        newArmor = ((ItemTransformerArmor) itemstack.getItem()).getTransformer();
                        
                        for (int k = 0; k < 4; ++k)
                        {
                            ItemStack armor = getTile().getStackInSlot(k);
                            
                            if (armor != null && armor.getItem() instanceof ItemTransformerArmor)
                            {
                                if (newArmor != ((ItemTransformerArmor) armor.getItem()).getTransformer())
                                {
                                    return false;
                                }
                            }
                        }
                    }
                    
                    return itemstack.getItem().isValidArmor(itemstack, j, player) && itemstack.getItem() instanceof ItemTransformerArmor;
                }
            });
        }

        addSlotToContainer(new SlotComponent(tile, 4, 147, 18));
        addSlotToContainer(new SlotComponent(tile, 5, 147, 36));
        addSlotToContainer(new Slot(tile, 6, 75, 45)
        {
            @Override
            public int getSlotStackLimit()
            {
                return 1;
            }

            @Override
            public boolean isItemValid(ItemStack itemstack)
            {
                return itemstack.getItem() == TFItems.displayVehicle;
            }
        });

        addPlayerInventory(inventoryPlayer, 20);
    }
    
    @Override
    public TileEntityDisplayStation getTile()
    {
        return (TileEntityDisplayStation) super.getTile();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int COMPONENT1 = 4;
        int COMPONENT2 = 5;
        int VEHICLE = 6;

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
                boolean flag = true;
                
                for (int i = 0; i < 4; ++i)
                {
                    Slot slot1 = (Slot) inventorySlots.get(i);
                    
                    if (itemstack1.getItem().isValidArmor(itemstack1, i, player) && slot1.isItemValid(itemstack1))
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
                    if (itemstack1.getItem() == TFItems.displayVehicle)
                    {
                        if (!mergeItemStack(itemstack1, VEHICLE, VEHICLE + 1, false))
                        {
                            return null;
                        }
                    }
                    else if (itemstack1.getItem() instanceof IComponent)
                    {
                        if (!mergeItemStack(itemstack1, COMPONENT1, COMPONENT2 + 1, false))
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
            }
            // In one of the infuser slots; try to place in player inventory / action bar
            else if (!mergeItemStack(itemstack1, VEHICLE + 1, VEHICLE + 37, false))
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
