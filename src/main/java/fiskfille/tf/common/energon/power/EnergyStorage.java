package fiskfille.tf.common.energon.power;

import io.netty.buffer.ByteBuf;
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
    
    public EnergyStorage copy()
    {
        EnergyStorage storage = new EnergyStorage(getMaxEnergy());
        storage.energy = energy;
        storage.energyUsage = energyUsage;
        storage.lastEnergy = lastEnergy;
        
        return storage;
    }
    
    public void toBytes(ByteBuf buf)
    {
        buf.writeFloat(energy);
        buf.writeFloat(energyUsage);
    }
    
    public void fromBytes(ByteBuf buf)
    {
        energy = buf.readFloat();
        energyUsage = buf.readFloat();
        lastEnergy = energy - energyUsage;
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
        NBTTagCompound storage = nbt.getCompoundTag("EmB");
        storage.setFloat("Energy", energy);
        storage.setFloat("Usage", energyUsage);
        nbt.setTag("EmB", storage);
    }

    public float remove(float amount, boolean simulate)
    {
        float actual = Math.min(amount, getEnergy());

        if (!simulate)
        {
            energy -= actual;
        }

        return actual;
    }

    public float add(float amount, boolean simulate)
    {
        float actual = Math.max(Math.min(amount, getMaxEnergy() - getEnergy()), 0);

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

    public float set(float amount)
    {
        energy = Math.min(getMaxEnergy(), Math.max(0.0F, amount));
        return energy;
    }

    public void setUsage(float usage)
    {
        energyUsage = usage;
        lastEnergy = energy;
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
