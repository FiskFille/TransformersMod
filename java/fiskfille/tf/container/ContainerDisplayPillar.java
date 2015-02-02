package fiskfille.tf.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.tileentity.TileEntityDisplayPillar;

public class ContainerDisplayPillar extends ContainerBasic
{
	private TileEntityDisplayPillar pillar;
    private int lastExtractTime;
    private int lastPowerTime;
    
    public ContainerDisplayPillar(InventoryPlayer par1InventoryPlayer, TileEntityDisplayPillar par2TileEntityDisplayPillar)
    {
    	this.pillar = par2TileEntityDisplayPillar;
        int i;
        int j;
        
        this.addSlotToContainer(new Slot(par2TileEntityDisplayPillar, 0, 80, 35));
        this.addPlayerInventory(par1InventoryPlayer, 0);
    }
    
    public boolean canInteractWith(EntityPlayer player)
    {
    	return true;
    }
    
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
//        Slot slot = (Slot)this.inventorySlots.get(par2);
//
//        if (slot != null && slot.getHasStack())
//        {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//
//            if (par2 == 2)
//            {
//                if (!this.mergeItemStack(itemstack1, 3, 39, true))
//                {
//                    return null;
//                }
//
//                slot.onSlotChange(itemstack1, itemstack);
//            }
//            else if (par2 != 1 && par2 != 0)
//            {
//                if (PowerManager.instance().isItemPower(itemstack1))
//                {
//                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
//                    {
//                        return null;
//                    }
//                }
//                else if (ExtractorRecipes.extracting().getExtractionResult(itemstack1) != null)
//                {
//                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
//                    {
//                        return null;
//                    }
//                }
//                else if (par2 >= 3 && par2 < 30)
//                {
//                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
//                    {
//                        return null;
//                    }
//                }
//                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
//                {
//                    return null;
//                }
//            }
//            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
//            {
//                return null;
//            }
//
//            if (itemstack1.stackSize == 0)
//            {
//                slot.putStack((ItemStack)null);
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

        return itemstack;
    }
}