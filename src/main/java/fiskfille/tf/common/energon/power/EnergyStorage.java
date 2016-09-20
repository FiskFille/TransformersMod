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
		NBTTagCompound nbttagcompound = nbt.getCompoundTag("EmB");
		energy = nbttagcompound.getFloat("Energy");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		NBTTagCompound nbttagcompound = nbt.getCompoundTag("EmB");
		nbttagcompound.setFloat("Energy", energy);
		nbt.setTag("EmB", nbttagcompound);
	}

	public float remove(float amount)
	{
		float f = Math.min(amount, energy);
		energy -= f;
		
		return f;
	}

	public float add(float amount)
	{
		float f = Math.max(Math.min(amount, maxEnergy - energy), 0);
		energy += f;
		
		return f;
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
}
