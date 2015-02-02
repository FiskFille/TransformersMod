package fiskfille.tf.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.data.TFPlayerData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PacketHandleStealthTransformation extends TransformersPacket
{
	public int id;
	private boolean mode;

	public PacketHandleStealthTransformation()
	{

	}

	public PacketHandleStealthTransformation(EntityPlayer player, boolean mode)
	{
		this.id = player.getEntityId();
		this.mode = mode;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(id);
		buffer.writeBoolean(mode);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		this.id = buffer.readInt();
		this.mode = buffer.readBoolean();
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
			TFPlayerData playerData = TFPlayerData.getData(from);
			TFDataManager.setStealthModeTimer(from, mode ? 5 : 0);

			String suffix = mode ? "vehicle" : "robot";
			
			from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, TransformersMod.modid + ":transform_" + suffix, 1, 1.5F, false);

			playerData.stealthMode = mode;
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
			TFDataManager.setInStealthMode(player, mode);
		}
	}
}