package fiskfille.tf.common.energon.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public interface IEnergyTransmitter extends IEnergyContainer
{
	public ReceiverHandler getReceiverHandler();
	
	public boolean isPowering(TileEntity tile);
	
	public boolean canPowerReach(TileEntity tile);
	
	public float getRange();
	
	public Vec3 getEnergyOutputOffset();
}
