package fiskfille.transformersmod.common.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.common.packet.base.TFPacketManager;
import fiskfille.transformersmod.common.packet.base.TransformersPacket;
import fiskfille.transformersmod.common.playerdata.TFDataManager;
import fiskfille.transformersmod.common.playerdata.TFPlayerData;

public class PacketBroadcastTransformationState extends TransformersPacket
{
	private int id;
	
	private boolean vehicle;
	
	public PacketBroadcastTransformationState()
	{
		
	}
	
	public PacketBroadcastTransformationState(EntityPlayer player)
	{
		this.id = player.getEntityId();
		this.vehicle = TFDataManager.isInVehicleMode(player);
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(id);
		buffer.writeBoolean(vehicle);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		id = buffer.readInt();
		vehicle = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		Entity lookupEntity = player.worldObj.getEntityByID(id);
	
		if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
		{
			EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;
			
			TFPlayerData playerData = TFPlayerData.getData(lookupPlayer);
			playerData.vehicle = vehicle;
			TFDataManager.setTransformationTimer(lookupPlayer, vehicle ? 0 : 10);
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		TFPacketManager.packetPipeline.sendToDimension(this, player.dimension);
		TFPlayerData playerData = TFPlayerData.getData(player);
		playerData.vehicle = vehicle;
	}
}
