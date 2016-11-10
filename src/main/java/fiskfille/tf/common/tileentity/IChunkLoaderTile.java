package fiskfille.tf.common.tileentity;

import net.minecraftforge.common.ForgeChunkManager.Ticket;

public interface IChunkLoaderTile
{
	public void loadChunks();

	public void unloadChunks();
	
	public void loadTicket(Ticket ticket);
}
