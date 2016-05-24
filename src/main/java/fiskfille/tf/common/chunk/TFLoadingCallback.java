package fiskfille.tf.common.chunk;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

import com.google.common.collect.Lists;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;

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
			TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(blockX, blockY, blockZ);
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
			Block block = world.getBlock(blockX, blockY, blockZ);

			if (block == TFBlocks.groundBridgeControlPanel)
			{
				validTickets.add(ticket);
			}
		}

		return validTickets;
	}
}
