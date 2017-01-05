package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerBasic extends Container
{
    protected final TileEntity tileentity;
    protected final World worldObj;

    public ContainerBasic(TileEntity tile)
    {
        tileentity = tile;
        worldObj = tile.getWorldObj();
    }

    public TileEntity getTile()
    {
        return tileentity;
    }

    public void addPlayerInventory(InventoryPlayer inventoryPlayer, int yOffset)
    {
        int i;
        int j;

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + yOffset + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142 + yOffset));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        if (tileentity != null)
        {
            if (tileentity instanceof IInventory)
            {
                return ((IInventory) tileentity).isUseableByPlayer(player);
            }

            return player.getDistanceSq(tileentity.xCoord + 0.5D, tileentity.yCoord + 0.5D, tileentity.zCoord + 0.5D) <= 64.0D;
        }

        return false;
    }

    @Override
    protected boolean mergeItemStack(ItemStack stackToMove, int fromId, int toId, boolean descending)
    {
        boolean success = false;
        int id = fromId;

        if (descending)
        {
            id = toId - 1;
        }

        Slot slot;
        ItemStack dstStack;

        if (stackToMove.isStackable())
        {
            while (stackToMove.stackSize > 0 && (!descending && id < toId || descending && id >= fromId))
            {
                slot = (Slot) inventorySlots.get(id);
                dstStack = slot.getStack();

                if (dstStack != null && dstStack.getItem() == stackToMove.getItem() && (!stackToMove.getHasSubtypes() || stackToMove.getItemDamage() == dstStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(stackToMove, dstStack))
                {
                    int maxStackSize = Math.min(slot.inventory.getInventoryStackLimit(), Math.min(dstStack.getMaxStackSize(), slot.getSlotStackLimit()));
                    int combinedStackSize = dstStack.stackSize + stackToMove.stackSize;

                    if (combinedStackSize <= maxStackSize)
                    {
                        stackToMove.stackSize = 0;
                        dstStack.stackSize = combinedStackSize;
                        slot.onSlotChanged();
                        success = true;
                    }
                    else if (dstStack.stackSize < maxStackSize)
                    {
                        stackToMove.stackSize -= maxStackSize - dstStack.stackSize;
                        dstStack.stackSize = maxStackSize;
                        slot.onSlotChanged();
                        success = true;
                    }
                }

                if (descending)
                {
                    --id;
                }
                else
                {
                    ++id;
                }
            }
        }

        if (stackToMove.stackSize > 0)
        {
            if (descending)
            {
                id = toId - 1;
            }
            else
            {
                id = fromId;
            }

            while (!descending && id < toId || descending && id >= fromId)
            {
                slot = (Slot) inventorySlots.get(id);
                dstStack = slot.getStack();

                if (dstStack == null)
                {
                    int maxStackSize = Math.min(slot.inventory.getInventoryStackLimit(), Math.min(stackToMove.getMaxStackSize(), slot.getSlotStackLimit()));
                    ItemStack itemstack1 = stackToMove.copy();
                    itemstack1.stackSize = Math.min(maxStackSize, itemstack1.stackSize);
                    slot.putStack(itemstack1);

                    maxStackSize = Math.min(slot.inventory.getInventoryStackLimit(), Math.min(slot.getStack().getMaxStackSize(), slot.getSlotStackLimit()));
                    stackToMove.stackSize = Math.max(stackToMove.stackSize - itemstack1.stackSize, 0);
                    slot.onSlotChanged();
                    success = true;
                    break;
                }

                if (descending)
                {
                    --id;
                }
                else
                {
                    ++id;
                }
            }
        }

        return success;
    }
}
