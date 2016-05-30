package fiskfille.tf.common.energon.power;

import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.google.common.collect.Maps;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class EnergyStorage
{
	protected final float maxEnergy;

	protected Map<String, Float> contents = Maps.newHashMap();
	protected float energy;
	protected int energyColor = 0xffffff;

	public EnergyStorage(float max)
	{
		maxEnergy = max;
	}
	
	public void update()
	{
		for (Map.Entry<String, Float> e : contents.entrySet())
        {
            Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
            float percent = TFHelper.getPercentOf(e.getKey(), contents);

            if (energon != null && percent > 0)
            {
                energyColor = TFRenderHelper.blend(energyColor, energon.getColor(), percent);
            }
        }
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		NBTTagCompound nbttagcompound = nbt.getCompoundTag("EmL");
		NBTTagList nbttaglist = nbttagcompound.getTagList("Contents", 10);
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			contents.put(nbttagcompound1.getString("Id"), nbttagcompound1.getFloat("Amount"));
		}
		
		energy = nbttagcompound.getFloat("Energy");
		energyColor = nbttagcompound.getInteger("EnergyColor");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		NBTTagList nbttaglist = new NBTTagList();
		
		for (Map.Entry<String, Float> e : contents.entrySet())
		{
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setString("Id", e.getKey());
			nbttagcompound1.setFloat("Amount", e.getValue());
			nbttaglist.appendTag(nbttagcompound1);
		}
		
		nbttagcompound.setFloat("Energy", energy);
		nbttagcompound.setInteger("EnergyColor", energyColor);
		nbttagcompound.setTag("Contents", nbttaglist);
		nbt.setTag("EmL", nbttagcompound);
	}

	public float remove(float amount)
	{
		float f = Math.min(amount, energy);
		float f1 = energy;
		energy -= f;
		
		return f;
	}

	public float add(float amount, Energon type)
	{
		if (!contents.containsKey(type.getId()))
		{
			contents.put(type.getId(), 0.0F);
		}
		
		float f = Math.max(Math.min(amount, maxEnergy - energy), 0);
		float f1 = contents.get(type.getId());
		float f2 = energy;
		
		energy += f;
		contents.put(type.getId(), f1 + f);
		
		return f;
	}

	public float add(float amount, Map<String, Float> contents)
	{
		float f = 0;
		
		for (Map.Entry<String, Float> e : contents.entrySet())
		{
			String s = e.getKey();
			f += add(amount * TFHelper.getPercentOf(s, contents), TransformersAPI.getEnergonTypeByName(s));
		}
		
		return f;
	}
	
	public float getEnergy()
	{
		return energy;
	}
	
	public float getMaxEnergy()
	{
		return maxEnergy;
	}
	
	public Map<String, Float> getContents()
	{
		return contents;
	}
	
	public int getEnergyColor()
	{
        return energyColor;
	}
}
