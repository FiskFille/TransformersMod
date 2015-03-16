package fiskfille.tf.common.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.tick.ClientTickHandler;
import fiskfille.tf.common.packet.base.TFPacketManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketCloudtrapJetpack implements IMessage
{
	private int id;
	private boolean jetpacking;

	public PacketCloudtrapJetpack()
	{

	}

	public PacketCloudtrapJetpack(EntityPlayer player, boolean j)
	{
		id = player.getEntityId();
		jetpacking = j;
	}

	public void fromBytes(ByteBuf buf)
	{
		id = buf.readInt();
		jetpacking = buf.readBoolean();
	}

	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(id);
		buf.writeBoolean(jetpacking);
	}

	public static class Handler implements IMessageHandler<PacketCloudtrapJetpack, IMessage>
	{
		public IMessage onMessage(PacketCloudtrapJetpack message, MessageContext ctx)
		{
			if (ctx.side.isClient())
			{
				EntityPlayer player = TransformersMod.proxy.getPlayer();
				EntityPlayer from = null;
				Entity entity = player.worldObj.getEntityByID(message.id);

				if (entity instanceof EntityPlayer) from = (EntityPlayer) entity;

				if (from != null && from != player)
				{
					ClientTickHandler.cloudtrapJetpacking.put(from, message.jetpacking);
				}
			}
			else
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;

				TFPacketManager.networkWrapper.sendToAllAround(new PacketCloudtrapJetpack(player, message.jetpacking), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 64));
			}

			return null;
		}
	}
}