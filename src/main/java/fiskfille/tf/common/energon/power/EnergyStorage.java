package fiskfille.tf.common.energon.power;

import net.minecraft.nbt.NBTTagCompound;

public class EnergyStorage
{
    protected final float maxEnergy;
    protected float energy;
    protected float energyUsage;
    protected float lastEnergy;

    public EnergyStorage(float max)
    {
        maxEnergy = max;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        NBTTagCompound storage = nbt.getCompoundTag("EmB");
        energy = storage.getFloat("Energy");
        energyUsage = storage.getFloat("Usage");
        lastEnergy = energy - energyUsage;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound storage = new NBTTagCompound();
        storage.setFloat("Energy", energy);
        storage.setFloat("Usage", energyUsage);
        nbt.setTag("EmB", storage);
    }

    public float remove(float amount, boolean simulate)
    {
        float actual = Math.min(amount, energy);

        if (!simulate)
        {
            energy -= actual;
        }

        return actual;
    }

    public float add(float amount, boolean simulate)
    {
        float actual = Math.max(Math.min(amount, maxEnergy - energy), 0);

        if (!simulate)
        {
            energy += actual;
        }

        return actual;
    }

    public float getEnergy()
    {
        if (energy <= 1E-16)
        {
            energy = 0;
        }

        return energy;
    }

    public float getMaxEnergy()
    {
        return maxEnergy;
    }

    public float set(float energy)
    {
        this.energy = Math.min(getMaxEnergy(), Math.max(0.0F, energy));
        return this.energy;
    }

    public void setUsage(float usage)
    {
        this.energyUsage = usage;
        this.lastEnergy = energy;
    }

    public float getUsage()
    {
        return energyUsage;
    }

    public float calculateUsage()
    {
        energyUsage = energy - lastEnergy;
        lastEnergy = energy;
        return energyUsage;
    }
}
