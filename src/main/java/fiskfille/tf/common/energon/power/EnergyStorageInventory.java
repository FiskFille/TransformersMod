package fiskfille.tf.common.energon.power;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class EnergyStorageInventory extends EnergyStorage
{
    protected final IInventory energyInventory;

    public EnergyStorageInventory(IInventory inventory)
    {
        super(0);
        energyInventory = inventory;
    }

    @Override
    public float remove(float amount)
    {
        if (amount <= 0)
        {
            return 0;
        }

        float max = Math.min(amount, getEnergy());
        float removed = 0;

        for (int i = 0; i < energyInventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = energyInventory.getStackInSlot(i);

            if (itemstack != null && itemstack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) itemstack.getItem();
                float f = container.extractEnergy(itemstack, max, true);

                f = Math.min(f, max);
                removed += container.extractEnergy(itemstack, f, false);
                max -= f;

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
    public float add(float amount)
    {
        if (amount <= 0)
        {
            return 0;
        }

        float max = Math.max(Math.min(amount, getMaxEnergy() - getEnergy()), 0);
        float added = 0;

        for (int i = 0; i < energyInventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = energyInventory.getStackInSlot(i);

            if (itemstack != null && itemstack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) itemstack.getItem();
                float f = container.receiveEnergy(itemstack, max, true);

                f = Math.min(f, max);
                added += container.receiveEnergy(itemstack, f, false);
                max -= f;

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
            ItemStack itemstack = energyInventory.getStackInSlot(i);

            if (itemstack != null && itemstack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) itemstack.getItem();
                energy += container.getEnergyStored(itemstack);
            }
        }

        return energy;
    }

    @Override
    public float getMaxEnergy()
    {
        int max = 0;

        for (int j = 0; j < energyInventory.getSizeInventory(); ++j)
        {
            ItemStack itemstack = energyInventory.getStackInSlot(j);

            if (itemstack != null && itemstack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) itemstack.getItem();
                max += container.getEnergyCapacity(itemstack);
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
        float f = getEnergy();
        energyUsage = f - lastEnergy;
        lastEnergy = f;

        return energyUsage;
    }
}
