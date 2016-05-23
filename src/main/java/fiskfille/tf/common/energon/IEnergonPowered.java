package fiskfille.tf.common.energon;

import net.minecraft.util.Vec3;

public interface IEnergonPowered
{
	public boolean canBePowered();
	
	public Vec3 getPowerInputOffset();
}
