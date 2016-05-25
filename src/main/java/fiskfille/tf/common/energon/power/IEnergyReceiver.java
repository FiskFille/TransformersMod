package fiskfille.tf.common.energon.power;

import net.minecraft.util.Vec3;

public interface IEnergyReceiver extends IEnergyContainer
{
	public boolean canReceiveEnergy();
	
	public Vec3 getEnergyInputOffset();
}
