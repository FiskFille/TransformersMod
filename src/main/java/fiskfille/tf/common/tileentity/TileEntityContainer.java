package fiskfille.tf.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityContainer extends TileEntity implements IInventory
{    
    public abstract ItemStack[] getItemStacks();
    
    public abstract ItemStack[] setItemStacks(ItemStack[] itemstacks);

    public int getSizeInventory()
    {
        return getItemStacks().length;
    }

    public ItemStack getStackInSlot(int slot)
    {
        return getItemStacks()[slot];
    }

    public ItemStack decrStackSize(int slot, int amount)
    {
        if (getItemStacks()[slot] != null)
        {
            ItemStack itemstack;

            if (getItemStacks()[slot].stackSize <= amount)
            {
                itemstack = getItemStacks()[slot];
                getItemStacks()[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = getItemStacks()[slot].splitStack(amount);

                if (getItemStacks()[slot].stackSize == 0)
                {
                    getItemStacks()[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (getItemStacks()[slot] != null)
        {
            ItemStack itemstack = getItemStacks()[slot];
            getItemStacks()[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        getItemStacks()[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    public String getInventoryName()
    {
        return "gui.display_station";
    }

    public boolean hasCustomInventoryName()
    {
        return false;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        setItemStacks(new ItemStack[getSizeInventory()]);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte slot = nbttagcompound1.getByte("Slot");

            if (slot >= 0 && slot < getItemStacks().length)
            {
                getItemStacks()[slot] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < getItemStacks().length; ++i)
        {
            if (getItemStacks()[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                getItemStacks()[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return true;
    }
}
