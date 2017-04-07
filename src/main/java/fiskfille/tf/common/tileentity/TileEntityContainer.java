package fiskfille.tf.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import fiskfille.tf.helper.TFTileHelper;

public abstract class TileEntityContainer extends TileEntityTF implements IInventory
{
    protected ItemStack[] inventory = new ItemStack[getSizeInventory()];
    
    public ItemStack[] getItemStacks()
    {
        if (hasWorldObj())
        {
            TileEntityContainer base = TFTileHelper.getTileBase(this);

            if (base != this)
            {
                if (base != null)
                {
                    return base.getItemStacks();
                }

                return new ItemStack[getSizeInventory()];
            }
        }
        
        return inventory;
    }

    public void setItemStacks(ItemStack[] itemstacks)
    {
        if (hasWorldObj())
        {
            TileEntityContainer base = TFTileHelper.getTileBase(this);

            if (base != this)
            {
                if (base != null)
                {
                    base.setItemStacks(itemstacks);
                }

                return;
            }
        }
        
        inventory = itemstacks;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return getItemStacks()[slot];
    }

    @Override
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

    @Override
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

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        getItemStacks()[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        if (nbt.hasKey("LoadInventory"))
        {
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
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        if (TFTileHelper.getTileBase(this) != this)
        {
            return;
        }
        
        nbt.setBoolean("LoadInventory", true);
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

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
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
        return true;
    }
}
