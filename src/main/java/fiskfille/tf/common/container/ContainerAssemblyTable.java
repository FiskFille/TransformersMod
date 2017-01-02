package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.recipe.AssemblyTableCraftingManager;
import fiskfille.tf.common.tileentity.TileEntityAssemblyTable;

public class ContainerAssemblyTable extends ContainerBasic
{
    public InventoryAssembly craftMatrix = new InventoryAssembly(this, 5, 5);
    public IInventory craftResult = new InventoryCraftResult();

    public ContainerAssemblyTable(InventoryPlayer inventoryPlayer, TileEntityAssemblyTable tile)
    {
        super(tile);
        addSlotToContainer(new SlotAssembly(inventoryPlayer.player, craftMatrix, craftResult, 0, 136, 25));
        addSlotToContainer(new Slot(craftMatrix, 0, 136, 56));
        addSlotToContainer(new Slot(craftMatrix, 4, 136, 74));
        addSlotToContainer(new Slot(craftMatrix, 20, 136, 92));
        addSlotToContainer(new Slot(craftMatrix, 1, 42, 25));
        addSlotToContainer(new Slot(craftMatrix, 2, 60, 25));
        addSlotToContainer(new Slot(craftMatrix, 3, 78, 25));
        addSlotToContainer(new Slot(craftMatrix, 21, 42, 105));
        addSlotToContainer(new Slot(craftMatrix, 22, 60, 105));
        addSlotToContainer(new Slot(craftMatrix, 23, 78, 105));
        addSlotToContainer(new Slot(craftMatrix, 5, 20, 47));
        addSlotToContainer(new Slot(craftMatrix, 10, 20, 65));
        addSlotToContainer(new Slot(craftMatrix, 15, 20, 83));
        addSlotToContainer(new Slot(craftMatrix, 9, 100, 47));
        addSlotToContainer(new Slot(craftMatrix, 14, 100, 65));
        addSlotToContainer(new Slot(craftMatrix, 19, 100, 83));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                addSlotToContainer(new Slot(craftMatrix, 6 + i + j * 2 + j * 3, 42 + i * 18, 47 + j * 18));
            }
        }

        addPlayerInventory(inventoryPlayer, 56);
        onCraftMatrixChanged(craftMatrix);
    }

    @Override
    public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
    {
        ItemStack itemstack = super.slotClick(p_75144_1_, p_75144_2_, p_75144_3_, p_75144_4_);
        onCraftMatrixChanged(craftMatrix);
        return itemstack;
    }

    @Override
    public void onCraftMatrixChanged(IInventory iinventory)
    {
        craftResult.setInventorySlotContents(0, AssemblyTableCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj));
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!worldObj.isRemote)
        {
            for (int i = 0; i < 25; ++i)
            {
                ItemStack itemstack = craftMatrix.getStackInSlotOnClosing(i);

                if (itemstack != null)
                {
                    player.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);
        int MAX = 24;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId == MAX)
            {
                if (!mergeItemStack(itemstack1, MAX + 1, MAX + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotId > MAX)
            {
                if (slotId >= MAX + 1 && slotId < MAX + 28)
                {
                    if (!mergeItemStack(itemstack1, MAX + 28, MAX + 37, false))
                    {
                        return null;
                    }
                }
                else if (slotId >= MAX + 28 && slotId < MAX + 37 && !mergeItemStack(itemstack1, MAX + 1, MAX + 28, false))
                {
                    return null;
                }
            }
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
    public boolean func_94530_a(ItemStack itemstack, Slot slot)
    {
        return slot.inventory != craftResult && super.func_94530_a(itemstack, slot);
    }
}
