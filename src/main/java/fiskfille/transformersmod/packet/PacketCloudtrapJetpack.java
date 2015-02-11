package fiskfille.transformersmod.packet;

import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.tick.ClientTickHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PacketCloudtrapJetpack extends TransformersPacket
{
	private int id;
	private boolean jetpacking;

	public PacketCloudtrapJetpack()
	{

	}

	public PacketCloudtrapJetpack(EntityPlayer player, boolean jetpacking)
	{
		this.id = player.getEntityId();
		this.jetpacking = jetpacking;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(id);
		buffer.writeBoolean(jetpacking);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		this.id = buffer.readInt();
		this.jetpacking = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		EntityPlayer from = null;

		Entity entity = player.worldObj.getEntityByID(id);

		if (entity instanceof EntityPlayer)
		{
			from = (EntityPlayer) entity;
		}

		if (from != null && from != Minecraft.getMinecraft().thePlayer)
		{
			if (jetpacking)
			{
				if (!ClientTickHandler.cloudtrapJetpacking.contains(from))
				{
					ClientTickHandler.cloudtrapJetpacking.add(from);
				}
			}
			else
			{
				ClientTickHandler.cloudtrapJetpacking.remove(from);
			}
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		EntityPlayer from = null;

		for (World world : MinecraftServer.getServer().worldServers)
		{
			Entity entity = world.getEntityByID(id);

			if (entity instanceof EntityPlayer)
			{
				from = (EntityPlayer) entity;
				break;
			}
		}

		if (from != null)
		{
			TransformersMod.packetPipeline.sendToAll(this);
		}
	}
}