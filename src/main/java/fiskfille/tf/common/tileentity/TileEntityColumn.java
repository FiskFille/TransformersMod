package fiskfille.tf.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.EnergyStorageInventory;
import fiskfille.tf.common.energon.power.IEnergyContainer;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFTileHelper;

public class TileEntityColumn extends TileEntityContainer implements IEnergyReceiver, IMultiTile
{
    public ReceiverHandler receiverHandler = new ReceiverHandler(this);
    public EnergyStorage storage = new EnergyStorageInventory(this, this);

    public ItemStack[] inventory = new ItemStack[6];
    public ItemStack[] lastInventory = new ItemStack[6];

    public float lastUsage;

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (getBlockMetadata() < 4)
        {
            if (!worldObj.isRemote)
            {
                TileEntity tile = TFTileHelper.getTileBase(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord));

                if (tile instanceof IEnergyContainer)
                {
                    IEnergyContainer receiver = (IEnergyContainer) tile;
                    TFEnergyHelper.transferEnergy(receiver, this, 100, false);
                }

                tile = TFTileHelper.getTileBase(worldObj.getTileEntity(xCoord, yCoord + 2, zCoord));

                if (tile instanceof IEnergyContainer)
                {
                    IEnergyContainer receiver = (IEnergyContainer) tile;

                    if (getMaxEnergy() > 0)
                    {
                        TFEnergyHelper.transferEnergy(this, receiver, 100, false);
                    }
                }

                boolean dirty = false;

                for (int i = 0; i < inventory.length; i++)
                {
                    ItemStack current = inventory[i];
                    ItemStack previous = lastInventory[i];

                    if (!ItemStack.areItemStacksEqual(current, previous) || !ItemStack.areItemStackTagsEqual(current, previous))
                    {
                        dirty = true;
                        break;
                    }
                }

                if (dirty)
                {
                    markBlockForUpdate();

                    for (int i = 0; i < inventory.length; i++)
                    {
                        ItemStack itemstack = inventory[i];

                        if (itemstack != null)
                        {
                            lastInventory[i] = itemstack.copy();
                        }
                    }
                }
            }

            lastUsage = storage.getUsage();
            storage.calculateUsage();
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return super.getRenderBoundingBox().addCoord(0, 1, 0).expand(0.25F, 0, 0.25F);
    }

    @Override
    public ItemStack[] getItemStacks()
    {
        return inventory;
    }

    @Override
    public void setItemStacks(ItemStack[] itemstacks)
    {
        inventory = itemstacks;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        super.writeCustomNBT(nbt);
        storage.writeToNBT(nbt);
    }

    @Override
    public float receiveEnergy(float amount, boolean simulate)
    {
        return storage.add(amount, simulate);
    }

    @Override
    public float extractEnergy(float amount, boolean simulate)
    {
        return storage.remove(amount, simulate);
    }

    @Override
    public float getEnergy()
    {
        return storage.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return storage.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        storage.setUsage(usage);
    }

    @Override
    public ReceiverHandler getReceiverHandler()
    {
        return receiverHandler;
    }

    @Override
    public boolean canReceiveEnergy(TileEntity from)
    {
        return getBlockMetadata() < 4;
    }

    @Override
    public Vec3 getEnergyInputOffset()
    {
        return Vec3.createVectorHelper(0, 1.5D, 0);
    }

    @Override
    public int getMapColor()
    {
        return 0xFF0000;
    }

    @Override
    public String getInventoryName()
    {
        return "gui.energy_column";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return getBlockMetadata() < 4 && itemstack.getItem() == TFItems.powerCanister;
    }

    @Override
    public int[] getBaseOffsets(int metadata)
    {
        return new int[] {0, -metadata / 4, 0};
    }
}
