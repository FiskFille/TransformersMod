package fiskfille.tf.common.chunk;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
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
            NBTTagCompound modData = ticket.getModData();
            int blockX = modData.getInteger("blockX");
			int blockY = modData.getInteger("blockY");
			int blockZ = modData.getInteger("blockZ");
			IChunkLoaderTile tile = (IChunkLoaderTile) world.getTileEntity(blockX, blockY, blockZ);
			tile.loadTicket(ticket);
		}
	}

	@Override
	public List<Ticket> ticketsLoaded(List<Ticket> tickets, World world, int maxTicketCount)
	{
		List<Ticket> validTickets = Lists.newArrayList();

		for (Ticket ticket : tickets)
		{
            NBTTagCompound modData = ticket.getModData();

            int blockX = modData.getInteger("blockX");
			int blockY = modData.getInteger("blockY");
			int blockZ = modData.getInteger("blockZ");

			if (world.getTileEntity(blockX, blockY, blockZ) instanceof IChunkLoaderTile)
			{
				validTickets.add(ticket);
			}
		}

		return validTickets;
	}
}
