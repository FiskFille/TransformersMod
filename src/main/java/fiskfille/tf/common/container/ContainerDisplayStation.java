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
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;

public class ContainerDisplayStation extends ContainerBasic
{
    private EntityPlayer player;

    public ContainerDisplayStation(InventoryPlayer inventoryPlayer, final TileEntityDisplayStation tile)
    {
        super(tile);
        player = inventoryPlayer.player;

        for (int i = 0; i < 4; ++i)
        {
            final int finalSlotIndex = i;
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
                    return itemstack.getItem().isValidArmor(itemstack, finalSlotIndex, tile.fakePlayer) && itemstack.getItem() instanceof ItemTransformerArmor;
                }
            });
        }
        
        for (int i = 0; i < 4; ++i)
        {
            final int finalSlotIndex = i;
            addSlotToContainer(new Slot(inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 92, 18 + i * 18)
            {
                @Override
                public int getSlotStackLimit()
                {
                    return 1;
                }

                @Override
                public boolean isItemValid(ItemStack stack)
                {
                    return stack.getItem().isValidArmor(stack, finalSlotIndex, player);
                }

                @Override
                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex()
                {
                    return ItemArmor.func_94602_b(finalSlotIndex);
                }
            });
        }

        addSlotToContainer(new SlotComponent(tile, 4, 147, 18));
        addSlotToContainer(new SlotComponent(tile, 5, 147, 36));
        addSlotToContainer(new Slot(tile, 6, 144, 63)
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
        int HELMET1 = 0;
        int BOOTS2 = 7;
        int MAX = 10;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // stack is in player inventory, try to place in appropriate furnace slot
            if (slotId > MAX)
            {
                boolean flag = true;
                
                for (int i = 0; i <= MAX; ++i)
                {
                    Slot slot1 = (Slot) inventorySlots.get(i);
                    
                    if (slot1 != null && slot1.isItemValid(itemstack1))
                    {
                        if (mergeItemStack(itemstack1, i, i + 1, false))
                        {
                            flag = false;
                        }
                    }
                }
                
                if (flag && slotId >= MAX + 28 && slotId < MAX + 37 && !mergeItemStack(itemstack1, MAX + 1, MAX + 28, false))
                {
                    return null;
                }
            }
            else if (slotId >= HELMET1 && slotId <= BOOTS2)
            {
                boolean flag = true;
                
                for (int i = 0; i <= MAX; ++i)
                {
                    Slot slot1 = (Slot) inventorySlots.get(i);
                    
                    if (slot1 != null && slot1.isItemValid(itemstack1))
                    {
                        if (mergeItemStack(itemstack1, i, i + 1, false))
                        {
                            flag = false;
                        }
                    }
                }
                
                // In one of the infuser slots; try to place in player inventory / action bar
                if (flag && !mergeItemStack(itemstack1, MAX + 1, MAX + 37, false))
                {
                    return null;
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
    
    @Override
    public void detectAndSendChanges()
    {
        for (int i = 0; i < inventorySlots.size(); ++i)
        {
            ItemStack itemstack = ((Slot) inventorySlots.get(i)).getStack();
            ItemStack itemstack1 = (ItemStack) inventoryItemStacks.get(i);

            if (!ItemStack.areItemStacksEqual(itemstack1, itemstack))
            {
                itemstack1 = itemstack == null ? null : itemstack.copy();
                inventoryItemStacks.set(i, itemstack1);

                getTile().markDirty();
            }
        }
    }
}
