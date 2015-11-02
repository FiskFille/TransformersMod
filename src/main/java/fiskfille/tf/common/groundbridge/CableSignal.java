package fiskfille.tf.common.groundbridge;

import fiskfille.tf.common.tileentity.TileEntityControlPanel;

public class CableSignal
{
	public TileEntityControlPanel source;
	public Object message;
	
	public CableSignal(TileEntityControlPanel source, Object message)
	{
		this.source = source;
		this.message = message;
	}
}
