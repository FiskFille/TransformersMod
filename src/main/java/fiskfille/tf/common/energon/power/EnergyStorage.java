package fiskfille.tf.common.energon.power;

import net.minecraft.nbt.NBTTagCompound;

public class EnergyStorage
{
    protected final float maxEnergy;
    protected float energy;

    public EnergyStorage(float max)
    {
        maxEnergy = max;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        NBTTagCompound storage = nbt.getCompoundTag("EmB");
        energy = storage.getFloat("Energy");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound storage = nbt.getCompoundTag("EmB");
        storage.setFloat("Energy", energy);
        nbt.setTag("EmB", storage);
    }

    public float remove(float amount)
    {
        float actual = Math.min(amount, energy);
        energy -= actual;

        return actual;
    }

    public float add(float amount)
    {
        float actual = Math.max(Math.min(amount, maxEnergy - energy), 0);
        energy += actual;

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
}
