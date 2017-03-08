package fiskfille.tf.common.energon.power;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class EnergyStorageInventory extends EnergyStorage
{
    protected final TileEntity tile;
    protected final IInventory energyInventory;

    public EnergyStorageInventory(TileEntity tileentity, IInventory inventory)
    {
        super(0);
        tile = tileentity;
        energyInventory = inventory;
    }

    @Override
    public EnergyStorageInventory copy()
    {
        EnergyStorageInventory storage = new EnergyStorageInventory(tile, energyInventory);

        return storage;
    }

    @Override
    public float remove(float amount, boolean simulate)
    {
        if (amount <= 0)
        {
            return 0;
        }

        float max = Math.min(amount, getEnergy());
        float removed = 0;

        for (int i = 0; i < energyInventory.getSizeInventory(); ++i)
        {
            ItemStack stack = energyInventory.getStackInSlot(i);

            if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) stack.getItem();
                float extracted = container.extractEnergy(stack, max, true);

                extracted = Math.min(extracted, max);
                removed += container.extractEnergy(stack, extracted, simulate || tile.getWorldObj().isRemote);
                max -= extracted;

                if (max <= 0)
                {
                    break;
                }
            }
        }

        energy -= removed;

        return removed;
    }

    @Override
    public float add(float amount, boolean simulate)
    {
        if (amount <= 0)
        {
            return 0;
        }

        float max = Math.max(Math.min(amount, getMaxEnergy() - getEnergy()), 0);
        float added = 0;

        for (int i = 0; i < energyInventory.getSizeInventory(); ++i)
        {
            ItemStack stack = energyInventory.getStackInSlot(i);

            if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) stack.getItem();
                float extracted = container.receiveEnergy(stack, max, true);

                extracted = Math.min(extracted, max);
                added += container.receiveEnergy(stack, extracted, simulate || tile.getWorldObj().isRemote);
                max -= extracted;

                if (max <= 0)
                {
                    break;
                }
            }
        }

        energy += added;

        return added;
    }

    @Override
    public float getEnergy()
    {
        energy = 0;

        for (int i = 0; i < energyInventory.getSizeInventory(); ++i)
        {
            ItemStack stack = energyInventory.getStackInSlot(i);

            if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) stack.getItem();
                energy += container.getEnergyStored(stack);
            }
        }

        return energy;
    }

    @Override
    public float getMaxEnergy()
    {
        int max = 0;

        for (int i = 0; i < energyInventory.getSizeInventory(); ++i)
        {
            ItemStack stack = energyInventory.getStackInSlot(i);

            if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) stack.getItem();
                max += container.getEnergyCapacity(stack);
            }
        }

        return max;
    }

    @Override
    public float set(float amount)
    {
        energy = Math.min(getMaxEnergy(), Math.max(0, amount));
        return energy;
    }

    @Override
    public void setUsage(float usage)
    {
        energyUsage = usage;
        lastEnergy = getEnergy();
    }

    @Override
    public float getUsage()
    {
        return energyUsage;
    }

    @Override
    public float calculateUsage()
    {        
        energyUsage = energy - lastEnergy;
        lastEnergy = energy;
        return energyUsage;
    }
}
