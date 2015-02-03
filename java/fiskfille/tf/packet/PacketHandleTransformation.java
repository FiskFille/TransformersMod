package fiskfille.tf.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.data.TFPlayerData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketHandleTransformation extends TransformersPacket
{
	public int id;
	private boolean mode;

	public PacketHandleTransformation()
	{

	}

	public PacketHandleTransformation(EntityPlayer player, boolean mode)
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

			playerData.stealthMode = false;

			TFDataManager.setTransformationTimer(from, mode ? 20 : 0);
			String suffix = mode ? "vehicle" : "robot";
			
			from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, TransformersMod.modid + ":transform_" + suffix, 1, 1, false);

			playerData.mode = mode;
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
//		EntityPlayer from = null;
//
//		player.addStat(TFAchievements.transform, 1);
//		
//		for (World world : MinecraftServer.getServer().worldServers)
//		{
//			Entity entity = world.getEntityByID(id);
//
//			if (entity instanceof EntityPlayer)
//			{
//				from = (EntityPlayer) entity;
//				break;
//			}
//		}

		if (player != null)
		{
			TFDataManager.setInVehicleMode(player, mode);
			
			TransformersMod.packetPipeline.sendToDimension(new PacketBroadcastStealthState(player), player.dimension);
		}
	}
}