package fiskfille.tf.common.energon.power;

public interface IEnergyContainer
{
	public float receiveEnergy(float amount);
	
	public float extractEnergy(float amount);
	
	public float getEnergy();
	
	public float getMaxEnergy();
}
