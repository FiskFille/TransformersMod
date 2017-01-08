package fiskfille.tf.common.chunk;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fiskfille.tf.TFLog;
import fiskfille.tf.TransformersMod;

public class TFChunkManager
{
    private static Map<World, LinkedList<Ticket>> ticketsForWorld = Maps.newHashMap();
    private static Map<ForcedChunk, Integer> chunkForcers = Maps.newHashMap();

    public static void forceChunk(Ticket ticket, ForcedChunk chunk)
    {
        int i = chunkForcers.get(chunk) == null ? 0 : chunkForcers.get(chunk);

        if (i == 0)
        {
            ForgeChunkManager.forceChunk(ticket, chunk);
        }

        chunkForcers.put(chunk, ++i);
    }

    public static void releaseChunk(SubTicket subTicket, ForcedChunk chunk)
    {
        int i = chunkForcers.get(chunk) == null ? 0 : chunkForcers.get(chunk);
        subTicket.remove();

        if (i == 1)
        {
            ForgeChunkManager.unforceChunk(subTicket.owner, chunk);
        }

        chunkForcers.put(chunk, Math.max(--i, 0));

        for (Map.Entry<World, LinkedList<Ticket>> e : ticketsForWorld.entrySet())
        {
            for (int j = 0; j < ticketsForWorld.get(e.getKey()).size(); ++j)
            {
                Ticket ticket = ticketsForWorld.get(e.getKey()).get(j);

                if (ticket.getChunkList().size() == 0)
                {
                    try
                    {
                        ForgeChunkManager.releaseTicket(ticket);
                    }
                    catch (Exception e1)
                    {
                    }

                    ticketsForWorld.get(e.getKey()).remove(j);
                }
            }
        }
    }

    public static void clearCache()
    {
        ticketsForWorld.clear();
        chunkForcers.clear();
    }

    public static void debug()
    {
        int entries = 0;
        int chunks = 0;
        int tickets = 0;

        for (Map.Entry<World, LinkedList<Ticket>> e : ticketsForWorld.entrySet())
        {
            if (e.getValue() != null)
            {
                for (int i = 0; i < e.getValue().size(); ++i)
                {
                    Ticket ticket = e.getValue().get(i);
                    chunks += ticket.getChunkList().size();

                    for (int j = 0; j < ticket.getChunkList().size(); ++j)
                    {
                        ChunkCoordIntPair coords = ticket.getChunkList().asList().get(j);
                        Integer k = chunkForcers.get(new ForcedChunk(ticket.world, coords.chunkXPos, coords.chunkZPos));

                        entries += k != null ? k : 0;
                    }
                }

                tickets += e.getValue().size();
            }
        }

        TFLog.info("%s entries / %s chunks / %s tickets", entries, chunks, tickets);
    }

    public static Ticket getTicketForChunk(ForcedChunk chunk)
    {
        World world = chunk.worldObj;

        if (ticketsForWorld.get(world) == null)
        {
            ticketsForWorld.put(world, new LinkedList<Ticket>());
        }

        for (int i = 0; i < ticketsForWorld.get(world).size(); ++i)
        {
            Ticket ticket = ticketsForWorld.get(world).get(i);
            List<SubTicket> list = SubTicket.getChildren(ticket);

            for (SubTicket subTicket : list)
            {
                if (subTicket.xCoord << 4 == chunk.chunkXPos && subTicket.zCoord << 4 == chunk.chunkZPos)
                {
                    return ticket;
                }
            }
        }

        return requestTicket(world);
    }

    public static Ticket requestTicket(World world)
    {
        if (ticketsForWorld.get(world) == null)
        {
            ticketsForWorld.put(world, new LinkedList<Ticket>());
        }

        return getNextAvailableTicket(world);
    }

    private static void newTicket(World world)
    {
        LinkedList<Ticket> list = ticketsForWorld.get(world);
        Ticket ticket = ForgeChunkManager.requestTicket(TransformersMod.instance, world, Type.NORMAL);

        if (ticket != null)
        {
            list.add(ticket);
            ticketsForWorld.put(world, list);
        }
    }

    private static LinkedList<Ticket> getAvailableTickets(World world)
    {
        LinkedList<Ticket> list = Lists.newLinkedList();

        for (int i = 0; i < ticketsForWorld.get(world).size(); ++i)
        {
            Ticket ticket = ticketsForWorld.get(world).get(i);

            if (ticket.getChunkList().size() < ticket.getMaxChunkListDepth())
            {
                list.add(ticket);
            }
        }

        if (list.isEmpty())
        {
            newTicket(world);
        }

        return list;
    }

    private static Ticket getNextAvailableTicket(World world)
    {
        return getAvailableTickets(world).isEmpty() ? null : getAvailableTickets(world).getLast();
    }
}
