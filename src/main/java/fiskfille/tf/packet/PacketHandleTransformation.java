package fiskfille.tf.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.data.TFPlayerData;
import fiskfille.tf.event.PlayerTransformEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class PacketHandleTransformation extends TransformersPacket
{
	public int id;
	private boolean transformed;

	public PacketHandleTransformation()
	{

	}

	public PacketHandleTransformation(EntityPlayer player, boolean mode)
	{
		this.id = player.getEntityId();
		this.transformed = mode;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(id);
		buffer.writeBoolean(transformed);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		this.id = buffer.readInt();
		this.transformed = buffer.readBoolean();
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

			MinecraftForge.EVENT_BUS.post(new PlayerTransformEvent(player, transformed, playerData.stealthForce));
			
			playerData.stealthForce = false;

			TFDataManager.setTransformationTimer(from, transformed ? 20 : 0);
			String suffix = transformed ? "vehicle" : "robot";
			
			from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, TransformersMod.modid + ":transform_" + suffix, 1, 1, false);

			playerData.vehicle = transformed;
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		if (player != null)
		{
			TFDataManager.setInVehicleMode(player, transformed);
			
			TransformersMod.packetPipeline.sendToDimension(new PacketBroadcastStealthState(player), player.dimension);
		}
	}
}