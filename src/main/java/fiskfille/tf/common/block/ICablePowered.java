package fiskfille.tf.common.block;

import net.minecraft.world.World;
import fiskfille.tf.common.groundbridge.CableSignal;
import fiskfille.tf.common.tileentity.TileEntityCable;

public interface ICablePowered
{
	/**
	 * Gets called when adjacent cables send a signal to this block.
	 * 
	 * @param world The world object
	 * @param x The x coordinate of this block
	 * @param y The y coordinate of this block
	 * @param z The z coordinate of this block
	 * @param signal The signal sent through this block
	 */
	public void updatePoweredState(World world, int x, int y, int z, CableSignal signal);
	
	/**
	 * Whether adjacent cables can connect to and send a signal through this
	 * block.
	 * 
	 * @param cable The adjacent cable
	 * @param x The x coordinate of this block
	 * @param y The y coordinate of this block
	 * @param z The z coordinate of this block
	 */
	public boolean connectTo(TileEntityCable cable, int x, int y, int z);
}
