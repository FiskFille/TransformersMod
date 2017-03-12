package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class ContainerTransmitter extends ContainerBasic
{
    public ContainerTransmitter(InventoryPlayer inventoryPlayer, TileEntityTransmitter tile)
    {
        super(tile);
        addSlotToContainer(new Slot(tile, 0, 45, 35)
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

        addPlayerInventory(inventoryPlayer, 0);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int INPUT = 0;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId == INPUT)
            {
                if (!mergeItemStack(itemstack1, INPUT + 1, INPUT + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotId > INPUT)
            {
                if (itemstack1.getItem() instanceof IFluidContainerItem && !ItemFuelCanister.isEmpty(itemstack1) && ItemFuelCanister.getContainerFluid(itemstack1).getFluid() == TFFluids.energon)
                {
                    if (!mergeItemStack(itemstack1, INPUT, INPUT + 1, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= INPUT + 1 && slotId < INPUT + 28)
                {
                    if (!mergeItemStack(itemstack1, INPUT + 28, INPUT + 37, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= INPUT + 28 && slotId < INPUT + 37 && !mergeItemStack(itemstack1, INPUT + 1, INPUT + 28, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, INPUT + 1, INPUT + 37, false))
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
