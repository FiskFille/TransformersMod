package fiskfille.tf.common.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;

public class ContainerEnergonProcessor extends ContainerBasic
{
    private TileEntityEnergonProcessor tileentity;
    private int lastBurnTime;
    private int lastPowerTime;
    private int lastLiquidColor;
    private int lastFillTime;
    private int lastCurrentMaxPowerTime;

    public ContainerEnergonProcessor(InventoryPlayer inventoryPlayer, TileEntityEnergonProcessor tile)
    {
        tileentity = tile;
        addSlotToContainer(new Slot(tile, 0, 24, 53));
        addSlotToContainer(new Slot(tile, 1, 24, 17));
        addSlotToContainer(new Slot(tile, 2, 138, 53)
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

    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, tileentity.burnTime);
        icrafting.sendProgressBarUpdate(this, 1, tileentity.powerTime);
//        icrafting.sendProgressBarUpdate(this, 2, tileentity.liquidColor);
        icrafting.sendProgressBarUpdate(this, 3, tileentity.fillTime);
        icrafting.sendProgressBarUpdate(this, 4, tileentity.currentMaxPowerTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting) crafters.get(i);

            if (lastBurnTime != tileentity.burnTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, tileentity.burnTime);
            }

            if (lastPowerTime != tileentity.powerTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, tileentity.powerTime);
            }

//            if (lastLiquidColor != tileentity.liquidColor)
//            {
//                icrafting.sendProgressBarUpdate(this, 2, tileentity.liquidColor);
//            }

            if (lastFillTime != tileentity.fillTime)
            {
                icrafting.sendProgressBarUpdate(this, 3, tileentity.fillTime);
            }

            if (lastCurrentMaxPowerTime != tileentity.currentMaxPowerTime)
            {
                icrafting.sendProgressBarUpdate(this, 4, tileentity.currentMaxPowerTime);
            }
        }

        lastBurnTime = tileentity.burnTime;
        lastPowerTime = tileentity.powerTime;
//        lastLiquidColor = tileentity.liquidColor;
        lastFillTime = tileentity.fillTime;
        lastCurrentMaxPowerTime = tileentity.currentMaxPowerTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            tileentity.burnTime = value;
        }
        else if (id == 1)
        {
            tileentity.powerTime = value;
        }
//        else if (id == 2)
//        {
//            tileentity.liquidColor = value;
//        }
        else if (id == 3)
        {
            tileentity.fillTime = value;
        }
        else if (id == 4)
        {
            tileentity.currentMaxPowerTime = value;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int FUEL = 0;
        int INPUT = 1;
        int OUTPUT = 2;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // If itemstack is in Output stack
            if (slotId == OUTPUT)
            {
                // try to place in player inventory / action bar; add 36 + 1 because mergeItemStack uses < index,
                // so the last slot in the inventory won't get checked if you don't add 1
                if (!mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            // itemstack is in player inventory, try to place in appropriate furnace slot
            else if (slotId > OUTPUT)
            {
                // if it can be smelted, place in the input slots
                if (itemstack1.getItem() instanceof IEnergon || Block.getBlockFromItem(itemstack1.getItem()) instanceof IEnergon)
                {
                    // try to place in either Input slot; add 1 to final input slot because mergeItemStack uses < index
                    if (!mergeItemStack(itemstack1, INPUT, INPUT + 1, false))
                    {
                        return null;
                    }
                }
                else if (PowerManager.isPowerSource(itemstack1))
                {
                    if (!mergeItemStack(itemstack1, FUEL, FUEL + 1, false))
                    {
                        return null;
                    }
                }
                // item in player's inventory, but not in action bar
                else if (slotId >= OUTPUT + 1 && slotId < OUTPUT + 28)
                {
                    // place in action bar
                    if (!mergeItemStack(itemstack1, OUTPUT + 28, OUTPUT + 37, false))
                    {
                        return null;
                    }
                }
                // item in action bar - place in player inventory
                else if (slotId >= OUTPUT + 28 && slotId < OUTPUT + 37 && !mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 28, false))
                {
                    return null;
                }
            }
            // In one of the infuser slots; try to place in player inventory / action bar
            else if (!mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 37, false))
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
