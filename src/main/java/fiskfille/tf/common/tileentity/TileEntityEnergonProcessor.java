package fiskfille.tf.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.helper.LiquidHelper;

public class TileEntityEnergonProcessor extends TileEntityLiquidContainer implements ISidedInventory
{
    private static final int[] slotsTop = new int[]{1};
    private static final int[] slotsBottom = new int[]{2};
    private static final int[] slotsSides = new int[]{0, 2};

    private ItemStack[] itemStacks = new ItemStack[3];

    public int burnTime;
    public int powerTime;
    public int fillTime;
    public int currentMaxPowerTime;
    public float animationTimer;
    public int animationBurnTime;

    public void updateEntity()
    {
        if (burnTime > 0)
        {
            ++animationBurnTime;
            animationTimer += 0.001F;
            animationTimer *= 1.05F;
        }
        else
        {
            animationBurnTime = 0;
            animationTimer *= 0.95F;
        }

        if (animationTimer > 1.0F)
        {
            animationTimer = 1.0F;
        }

        if (!worldObj.isRemote)
        {
            ItemStack power = getStackInSlot(0);
            ItemStack crystal = getStackInSlot(1);
            ItemStack canister = getStackInSlot(2);

            if (powerTime > 0)
            {
                --powerTime;
            }

            if (powerTime <= 0 && power != null && isItemValidForSlot(0, power) && canBurnCrystal(crystal))
            {
                currentMaxPowerTime = powerTime = PowerManager.getPowerSourceAmount(power) + 2;
                decrStackSize(0, 1);
                markDirty();
            }

            if (powerTime > 0 && canBurnCrystal(crystal))
            {
                if (burnTime < 200)
                {
                    ++burnTime;
                }
                else
                {
                    addContents(crystal);
                    decrStackSize(1, 1);
                    burnTime = 0;
                }
            }
            else if (burnTime > 0)
            {
                --burnTime;
            }

            if (liquidAmount > 0 && canister != null && canister.getItem() == TFItems.emptyFuelCanister && ItemFuelCanister.getContents(canister).isEmpty())
            {
                if (fillTime < 100)
                {
                    ++fillTime;
                }
                else
                {
                	setInventorySlotContents(2, fillCanister(canister));
                	fillTime = 0;
                }
            }
            else if (fillTime > 0)
            {
                --fillTime;
            }
        }
        
        blendColor();
    }
    
    public AxisAlignedBB getRenderBoundingBox()
	{
		return super.getRenderBoundingBox().addCoord(0, 0.5D, 0);
	}
    
    public boolean canBurnCrystal(ItemStack itemstack)
    {
        if (itemstack != null && isItemValidForSlot(1, itemstack))
        {
            IEnergon ienergon = (IEnergon) (itemstack.getItem() instanceof ItemBlock ? Block.getBlockFromItem(itemstack.getItem()) : itemstack.getItem());

            if (liquidAmount + ienergon.getMass() <= getMaxStorage())
            {
                return true;
            }
        }

        return false;
    }
    
    public float getMaxStorage()
    {
    	return LiquidHelper.ENERGON_PROCESSOR_STORAGE;
    }

    public int getSizeInventory()
    {
        return itemStacks.length;
    }

    public ItemStack getStackInSlot(int slot)
    {
        return itemStacks[slot];
    }

    public ItemStack decrStackSize(int slot, int amount)
    {
        if (itemStacks[slot] != null)
        {
            ItemStack itemstack;

            if (itemStacks[slot].stackSize <= amount)
            {
                itemstack = itemStacks[slot];
                itemStacks[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = itemStacks[slot].splitStack(amount);

                if (itemStacks[slot].stackSize == 0)
                {
                    itemStacks[slot] = null;
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
        if (itemStacks[slot] != null)
        {
            ItemStack itemstack = itemStacks[slot];
            itemStacks[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        itemStacks[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    public String getInventoryName()
    {
        return "gui.energon_processor";
    }

    public boolean hasCustomInventoryName()
    {
        return false;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        itemStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte slot = nbttagcompound1.getByte("Slot");

            if (slot >= 0 && slot < itemStacks.length)
            {
                itemStacks[slot] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        burnTime = nbt.getInteger("BurnTime");
        powerTime = nbt.getInteger("PowerTime");
        fillTime = nbt.getInteger("FillTime");
        currentMaxPowerTime = nbt.getInteger("MaxPowerTime");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("BurnTime", burnTime);
        nbt.setInteger("PowerTime", powerTime);
        nbt.setInteger("FillTime", fillTime);
        nbt.setInteger("MaxPowerTime", currentMaxPowerTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < itemStacks.length; ++i)
        {
            if (itemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                itemStacks[i].writeToNBT(nbttagcompound1);
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
        return slot == 0 ? PowerManager.isPowerSource(stack) : (slot == 1 ? (stack.getItem() instanceof IEnergon || Block.getBlockFromItem(stack.getItem()) instanceof IEnergon) : (slot == 2 ? stack.getItem() == TFItems.emptyFuelCanister && stack.stackSize == 1 && itemStacks[slot] == null : false));
    }

    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
    }

    public boolean canInsertItem(int slot, ItemStack stack, int p_102007_3_)
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return isItemValidForSlot(slot, stack);
    }

    public boolean canExtractItem(int slot, ItemStack stack, int p_102008_3_)
    {
        return p_102008_3_ != 0 || slot == 0 || (slot == 2 && stack.getItem() == TFItems.filledFuelCanister);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        writeToNBT(syncData);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }
}
