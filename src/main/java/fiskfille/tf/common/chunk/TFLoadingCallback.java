package fiskfille.tf.common.chunk;

import java.util.List;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

import com.google.common.collect.Lists;

import fiskfille.tf.common.tileentity.IChunkLoaderTile;

public class TFLoadingCallback implements ForgeChunkManager.OrderedLoadingCallback
{
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world)
	{
		for (Ticket ticket : tickets)
		{
		    List<SubTicket> subTickets = SubTicket.getChildren(ticket);
            
            for (SubTicket subTicket : subTickets)
            {
                IChunkLoaderTile tile = (IChunkLoaderTile) world.getTileEntity(subTicket.xCoord, subTicket.yCoord, subTicket.zCoord);
                tile.forceChunks(subTicket);
            }
		}
	}

	@Override
	public List<Ticket> ticketsLoaded(List<Ticket> tickets, World world, int maxTicketCount)
	{
		List<Ticket> validTickets = Lists.newArrayList();

		for (Ticket ticket : tickets)
		{
		    List<SubTicket> subTickets = SubTicket.getChildren(ticket);
		    
		    for (int i = 0; i < subTickets.size(); ++i)
		    {
		        SubTicket subTicket = subTickets.get(i);
		        
	            if (world.getTileEntity(subTicket.xCoord, subTicket.yCoord, subTicket.zCoord) instanceof IChunkLoaderTile)
	            {
	                validTickets.add(ticket);
	                break;
	            }
	            else
	            {
	                subTicket.remove();
	            }
		    }
		}

		return validTickets;
	}
}
