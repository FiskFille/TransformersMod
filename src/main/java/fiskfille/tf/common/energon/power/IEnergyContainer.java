package fiskfille.tf.common.energon.power;

import java.util.Map;

public interface IEnergyContainer
{
	public float receiveEnergy(float amount, Map<String, Float> contents);
	
	public float extractEnergy(float amount);
	
	public float getEnergy();
	
	public float getMaxEnergy();
	
	public Map<String, Float> getEnergyContents();
	
	public int getEnergyColor();
}
