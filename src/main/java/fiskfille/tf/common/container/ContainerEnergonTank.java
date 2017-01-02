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
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;

public class ContainerEnergonTank extends ContainerBasic
{
    private int lastFillTime;

    public ContainerEnergonTank(InventoryPlayer inventoryPlayer, TileEntityEnergonTank tile)
    {
        super(tile);
        addSlotToContainer(new Slot(tile, 0, 29, 35)
        {
            @Override
            public boolean isItemValid(ItemStack itemstack)
            {
                return itemstack.getItem() instanceof IFluidContainerItem && !ItemFuelCanister.isEmpty(itemstack) && ItemFuelCanister.getContainerFluid(itemstack).getFluid() == TFFluids.energon;
            }

            @Override
            public int getSlotStackLimit()
            {
                return 1;
            }
        });

        addSlotToContainer(new Slot(tile, 1, 123, 53)
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
    public TileEntityEnergonTank getTile()
    {
        return (TileEntityEnergonTank) super.getTile();
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, getTile().fillTime);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (Object crafter : crafters)
        {
            ICrafting icrafting = (ICrafting) crafter;

            if (lastFillTime != getTile().fillTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, getTile().fillTime);
            }
        }

        lastFillTime = getTile().fillTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            getTile().fillTime = value;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int INPUT = 0;
        int OUTPUT = 1;

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
                if (itemstack1.getItem() instanceof IFluidContainerItem)
                {
                    if (!ItemFuelCanister.isEmpty(itemstack1) && ItemFuelCanister.getContainerFluid(itemstack1).getFluid() == TFFluids.energon)
                    {
                        if (!mergeItemStack(itemstack1, INPUT, INPUT + 1, false))
                        {
                            return null;
                        }
                    }

                    if (ItemFuelCanister.isEmpty(itemstack1) || !ItemFuelCanister.isFull(itemstack1) && ItemFuelCanister.getContainerFluid(itemstack1).getFluid() == TFFluids.energon)
                    {
                        if (!mergeItemStack(itemstack1, OUTPUT, OUTPUT + 1, false))
                        {
                            return null;
                        }
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
