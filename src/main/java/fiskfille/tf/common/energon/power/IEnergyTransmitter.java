package fiskfille.tf.common.energon.power;

import net.minecraft.util.Vec3;

public interface IEnergyTransmitter extends IEnergyContainer
{
	public ReceiverHandler getReceiverHandler();
	
	public float getRange();
	
	public Vec3 getEnergyOutputOffset();
}
