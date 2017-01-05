package fiskfille.tf.common.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;

public class ContainerEnergonProcessor extends ContainerBasic
{
    private int lastBurnTime;
    private int lastPowerTime;
    private int lastFillTime;
    private int lastCurrentMaxPowerTime;

    public ContainerEnergonProcessor(InventoryPlayer inventoryPlayer, TileEntityEnergonProcessor tile)
    {
        super(tile);
        addSlotToContainer(new Slot(tile, 0, 24, 53));
        addSlotToContainer(new Slot(tile, 1, 24, 17));
        addSlotToContainer(new Slot(tile, 2, 138, 53)
        {
            @Override
            public int getSlotStackLimit()
            {
                return 1;
            }
        });

        addPlayerInventory(inventoryPlayer, 0);
    }

    @Override
    public TileEntityEnergonProcessor getTile()
    {
        return (TileEntityEnergonProcessor) super.getTile();
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, getTile().burnTime);
        icrafting.sendProgressBarUpdate(this, 1, getTile().powerTime);
        icrafting.sendProgressBarUpdate(this, 2, getTile().fillTime);
        icrafting.sendProgressBarUpdate(this, 3, getTile().currentMaxPowerTime);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (Object crafter : crafters)
        {
            ICrafting icrafting = (ICrafting) crafter;

            if (lastBurnTime != getTile().burnTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, getTile().burnTime);
            }

            if (lastPowerTime != getTile().powerTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, getTile().powerTime);
            }

            if (lastFillTime != getTile().fillTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, getTile().fillTime);
            }

            if (lastCurrentMaxPowerTime != getTile().currentMaxPowerTime)
            {
                icrafting.sendProgressBarUpdate(this, 3, getTile().currentMaxPowerTime);
            }
        }

        lastBurnTime = getTile().burnTime;
        lastPowerTime = getTile().powerTime;
        lastFillTime = getTile().fillTime;
        lastCurrentMaxPowerTime = getTile().currentMaxPowerTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            getTile().burnTime = value;
        }
        else if (id == 1)
        {
            getTile().powerTime = value;
        }
        else if (id == 2)
        {
            getTile().fillTime = value;
        }
        else if (id == 3)
        {
            getTile().currentMaxPowerTime = value;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
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
                if (itemstack1.getItem() instanceof IEnergon || Block.getBlockFromItem(itemstack1.getItem()) instanceof IEnergon)
                {
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
                else if (itemstack1.getItem() instanceof IFluidContainerItem && (ItemFuelCanister.isEmpty(itemstack1) || !ItemFuelCanister.isFull(itemstack1) && ItemFuelCanister.getContainerFluid(itemstack1).getFluid() == TFFluids.energon))
                {
                    if (!mergeItemStack(itemstack1, OUTPUT, OUTPUT + 1, false))
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

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
