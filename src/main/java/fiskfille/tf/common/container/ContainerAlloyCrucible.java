package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;

public class ContainerAlloyCrucible extends ContainerBasic
{
    private TileEntityAlloyCrucible tileentity;
    private int lastSmeltTime;

    public ContainerAlloyCrucible(InventoryPlayer inventoryPlayer, TileEntityAlloyCrucible tile)
    {
        tileentity = tile;
        
        for (int i = 0; i < 3; ++i)
        {
            addSlotToContainer(new Slot(tile, i, 73, 17 + i * 18));
        }
        
        addSlotToContainer(new SlotAlloyCrucible(inventoryPlayer.player, tile, 3, 107, 26));
        addPlayerInventory(inventoryPlayer, 0);
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, tileentity.smeltTime);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting) crafters.get(i);

            if (lastSmeltTime != tileentity.smeltTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, tileentity.smeltTime);
            }
        }

        lastSmeltTime = tileentity.smeltTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            tileentity.smeltTime = value;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileentity.isUseableByPlayer(player);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int OUTPUT = 3;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId == OUTPUT)
            {
                if (!mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotId > OUTPUT)
            {
                boolean flag = false;
                
                for (int i = 0; i < OUTPUT; ++i)
                {
                    if (itemstack != null && itemstack.getTagCompound() == itemstack1.getTagCompound() && itemstack.getItemDamage() == itemstack1.getItemDamage() && itemstack.getItem() == itemstack1.getItem())
                    {
                        flag = true;
                        break;
                    }
                }
                
                if (flag)
                {
                    if (!mergeItemStack(itemstack1, 0, 2 + 1, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= OUTPUT + 1 && slotId < OUTPUT + 28)
                {
                    if (!mergeItemStack(itemstack1, OUTPUT + 28, OUTPUT + 37, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= OUTPUT + 28 && slotId < OUTPUT + 37 && !mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 28, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 37, false))
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

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        
        return itemstack;
    }
}
