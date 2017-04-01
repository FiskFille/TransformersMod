package fiskfille.tf.common.energon.power;

public interface IEnergyContainer
{
    float receiveEnergy(float amount, boolean simulate);

    float extractEnergy(float amount, boolean simulate);

    float getEnergy();

    float getMaxEnergy();

    float getEnergyUsage();
}
