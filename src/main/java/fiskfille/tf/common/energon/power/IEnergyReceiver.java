package fiskfille.tf.common.energon.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public interface IEnergyReceiver extends IEnergyContainer
{
	public boolean canReceiveEnergy(TileEntity from);
	
	public Vec3 getEnergyInputOffset();
}
