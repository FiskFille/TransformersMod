package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class ContainerTransmitter extends ContainerBasic
{
    private TileEntityTransmitter tileentity;
    private int lastBurnTime;
    private int lastPowerTime;
    private int lastLiquidColor;
    private int lastFillTime;
    private int lastCurrentMaxPowerTime;

    public ContainerTransmitter(InventoryPlayer inventoryPlayer, TileEntityTransmitter tile)
    {
        tileentity = tile;
        addSlotToContainer(new Slot(tile, 0, 45, 35)
        {
        	public boolean isItemValid(ItemStack itemstack)
        	{
        		return itemstack.getItem() instanceof IFluidContainerItem && !ItemFuelCanister.isEmpty(itemstack) && ItemFuelCanister.getContainerFluid(itemstack).getFluid() == TFFluids.energon;
        	}
        	
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

    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
//        icrafting.sendProgressBarUpdate(this, 0, tileentity.burnTime);
//        icrafting.sendProgressBarUpdate(this, 1, tileentity.powerTime);
//        icrafting.sendProgressBarUpdate(this, 2, tileentity.liquidColor);
//        icrafting.sendProgressBarUpdate(this, 3, tileentity.fillTime);
//        icrafting.sendProgressBarUpdate(this, 4, tileentity.currentMaxPowerTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

//        for (int i = 0; i < crafters.size(); ++i)
//        {
//            ICrafting icrafting = (ICrafting) crafters.get(i);
//
//            if (lastBurnTime != tileentity.burnTime)
//            {
//                icrafting.sendProgressBarUpdate(this, 0, tileentity.burnTime);
//            }
//
//            if (lastPowerTime != tileentity.powerTime)
//            {
//                icrafting.sendProgressBarUpdate(this, 1, tileentity.powerTime);
//            }
//
//            if (lastLiquidColor != tileentity.liquidColor)
//            {
//                icrafting.sendProgressBarUpdate(this, 2, tileentity.liquidColor);
//            }
//
//            if (lastFillTime != tileentity.fillTime)
//            {
//                icrafting.sendProgressBarUpdate(this, 3, tileentity.fillTime);
//            }
//
//            if (lastCurrentMaxPowerTime != tileentity.currentMaxPowerTime)
//            {
//                icrafting.sendProgressBarUpdate(this, 4, tileentity.currentMaxPowerTime);
//            }
//        }
//
//        lastBurnTime = tileentity.burnTime;
//        lastPowerTime = tileentity.powerTime;
//        lastLiquidColor = tileentity.liquidColor;
//        lastFillTime = tileentity.fillTime;
//        lastCurrentMaxPowerTime = tileentity.currentMaxPowerTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
//        if (id == 0)
//        {
//            tileentity.burnTime = value;
//        }
//        else if (id == 1)
//        {
//            tileentity.powerTime = value;
//        }
//        else if (id == 2)
//        {
//            tileentity.liquidColor = value;
//        }
//        else if (id == 3)
//        {
//            tileentity.fillTime = value;
//        }
//        else if (id == 4)
//        {
//            tileentity.currentMaxPowerTime = value;
//        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int INPUT = 0;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // If itemstack is in Output stack
            if (slotId == INPUT)
            {
                // try to place in player inventory / action bar; add 36 + 1 because mergeItemStack uses < index,
                // so the last slot in the inventory won't get checked if you don't add 1
                if (!mergeItemStack(itemstack1, INPUT + 1, INPUT + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            // itemstack is in player inventory, try to place in appropriate furnace slot
            else if (slotId > INPUT)
            {
                if (slotId >= INPUT + 1 && slotId < INPUT + 28)
                {
                    // place in action bar
                    if (!mergeItemStack(itemstack1, INPUT + 28, INPUT + 37, false))
                    {
                        return null;
                    }
                }
                // item in action bar - place in player inventory
                else if (slotId >= INPUT + 28 && slotId < INPUT + 37 && !mergeItemStack(itemstack1, INPUT + 1, INPUT + 28, false))
                {
                    return null;
                }
            }
            // In one of the infuser slots; try to place in player inventory / action bar
            else if (!mergeItemStack(itemstack1, INPUT + 1, INPUT + 37, false))
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
