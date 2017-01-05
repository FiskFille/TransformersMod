package fiskfille.tf.common.container;

import java.util.concurrent.Callable;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ReportedException;
import fiskfille.tf.common.item.TFItems;

public class InventoryGroundBridge implements IInventory
{
    public EntityPlayer player;
    public ItemStack remoteItem;

    public ItemStack[] inventory = new ItemStack[1];

    public InventoryGroundBridge(EntityPlayer player, ItemStack itemstack)
    {
        this.player = player;
        remoteItem = itemstack;

        if (itemstack != null)
        {
            if (!itemstack.hasTagCompound())
            {
                itemstack.setTagCompound(new NBTTagCompound());
            }

            NBTTagCompound nbt = itemstack.getTagCompound();

            for (int i = 0; i < getSizeInventory(); i++)
            {
                if (nbt.hasKey("Slot" + i))
                {
                    inventory[i] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("Slot" + i));
                }
            }
        }
    }

    public ItemStack getFirstItemStack()
    {
        for (ItemStack itemstack : inventory)
        {
            if (itemstack != null)
            {
                return itemstack;
            }
        }

        return null;
    }

    public int getFirstSlotID()
    {
        for (int i = 0; i < inventory.length; ++i)
        {
            ItemStack itemstack = inventory[i];

            if (itemstack != null)
            {
                return i;
            }
        }

        return -1;
    }

    public boolean addItemStackToInventory(ItemStack itemstack)
    {
        boolean flag = addItemStackToInventoryTemp(itemstack);

        if (flag)
        {
            if (remoteItem != null)
            {
                NBTTagCompound nbt = remoteItem.getTagCompound();

                for (int j = 0; j < getSizeInventory(); j++)
                {
                    if (inventory[j] != null)
                    {
                        nbt.setTag("Slot" + j, inventory[j].writeToNBT(nbt.getCompoundTag("Slot" + j)));
                    }
                    else if (nbt.hasKey("Slot" + j))
                    {
                        nbt.removeTag("Slot" + j);
                    }
                }
            }
        }

        return flag;
    }

    private boolean addItemStackToInventoryTemp(final ItemStack itemstack)
    {
        if (itemstack != null && itemstack.stackSize != 0 && itemstack.getItem() != null)
        {
            try
            {
                int i;

                if (itemstack.isItemDamaged())
                {
                    i = getFirstEmptyStack();

                    if (i >= 0)
                    {
                        inventory[i] = ItemStack.copyItemStack(itemstack);
                        inventory[i].animationsToGo = 5;
                        itemstack.stackSize = 0;
                        return true;
                    }
                    else if (player.capabilities.isCreativeMode)
                    {
                        itemstack.stackSize = 0;
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    do
                    {
                        i = itemstack.stackSize;
                        itemstack.stackSize = storePartialItemStack(itemstack);
                    }
                    while (itemstack.stackSize > 0 && itemstack.stackSize < i);

                    if (itemstack.stackSize == i && player.capabilities.isCreativeMode)
                    {
                        itemstack.stackSize = 0;
                        return true;
                    }
                    else
                    {
                        return itemstack.stackSize < i;
                    }
                }
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
                crashreportcategory.addCrashSection("Item ID", Integer.valueOf(Item.getIdFromItem(itemstack.getItem())));
                crashreportcategory.addCrashSection("Item data", Integer.valueOf(itemstack.getItemDamage()));
                crashreportcategory.addCrashSectionCallable("Item name", new Callable()
                {
                    @Override
                    public String call()
                    {
                        return itemstack.getDisplayName();
                    }
                });
                throw new ReportedException(crashreport);
            }
        }
        else
        {
            return false;
        }
    }

    public int getFirstEmptyStack()
    {
        for (int i = 0; i < inventory.length; ++i)
        {
            if (inventory[i] == null)
            {
                return i;
            }
        }

        return -1;
    }

    private int storePartialItemStack(ItemStack itemstack)
    {
        Item item = itemstack.getItem();
        int i = itemstack.stackSize;
        int j;

        if (itemstack.getMaxStackSize() == 1)
        {
            j = getFirstEmptyStack();

            if (j < 0)
            {
                return i;
            }
            else
            {
                if (inventory[j] == null)
                {
                    inventory[j] = ItemStack.copyItemStack(itemstack);
                }

                return 0;
            }
        }
        else
        {
            j = storeItemStack(itemstack);

            if (j < 0)
            {
                j = getFirstEmptyStack();
            }

            if (j < 0)
            {
                return i;
            }
            else
            {
                if (inventory[j] == null)
                {
                    inventory[j] = new ItemStack(item, 0, itemstack.getItemDamage());

                    if (itemstack.hasTagCompound())
                    {
                        inventory[j].setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                    }
                }

                int k = i;

                if (i > inventory[j].getMaxStackSize() - inventory[j].stackSize)
                {
                    k = inventory[j].getMaxStackSize() - inventory[j].stackSize;
                }

                if (k > getInventoryStackLimit() - inventory[j].stackSize)
                {
                    k = getInventoryStackLimit() - inventory[j].stackSize;
                }

                if (k == 0)
                {
                    return i;
                }
                else
                {
                    i -= k;
                    inventory[j].stackSize += k;
                    inventory[j].animationsToGo = 5;
                    return i;
                }
            }
        }
    }

    private int storeItemStack(ItemStack itemstack)
    {
        for (int i = 0; i < inventory.length; ++i)
        {
            if (inventory[i] != null && inventory[i].getItem() == itemstack.getItem() && inventory[i].isStackable() && inventory[i].stackSize < inventory[i].getMaxStackSize() && inventory[i].stackSize < getInventoryStackLimit() && (!inventory[i].getHasSubtypes() || inventory[i].getItemDamage() == itemstack.getItemDamage()) && ItemStack.areItemStackTagsEqual(inventory[i], itemstack))
            {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null)
        {
            if (stack.stackSize > amount)
            {
                stack = stack.splitStack(amount);
                markDirty();
            }
            else
            {
                setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack stack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return "Ground Bridge Remote";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public void markDirty()
    {
        ItemStack itemstack = player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote ? player.getHeldItem() : remoteItem;

        if (itemstack != null)
        {
            NBTTagCompound nbt = itemstack.getTagCompound();

            for (int i = 0; i < getSizeInventory(); i++)
            {
                if (inventory[i] != null)
                {
                    nbt.setTag("Slot" + i, inventory[i].writeToNBT(nbt.getCompoundTag("Slot" + i)));
                }
                else if (nbt.hasKey("Slot" + i))
                {
                    nbt.removeTag("Slot" + i);
                }
            }
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return stack.getItem() == TFItems.csd;
    }
}
