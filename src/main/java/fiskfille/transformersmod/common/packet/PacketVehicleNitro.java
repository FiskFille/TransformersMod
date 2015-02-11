package fiskfille.transformersmod.common.packet;

import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.client.particle.NitroParticleHandler;
import fiskfille.transformersmod.common.packet.base.TFPacketManager;
import fiskfille.transformersmod.common.packet.base.TransformersPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketVehicleNitro extends TransformersPacket
{
	private int id;
	private boolean nitroOn;
	
	public PacketVehicleNitro()
	{
		
	}
	
	public PacketVehicleNitro(EntityPlayer player, boolean nitroOn)
	{
		this.id = player.getEntityId();
		this.nitroOn = nitroOn;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(id);
		buffer.writeBoolean(nitroOn);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		id = buffer.readInt();
		nitroOn = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		Entity entity = player.worldObj.getEntityByID(id);
		
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer fromPlayer = (EntityPlayer) entity;
		
			if (fromPlayer != player)
			{
				NitroParticleHandler.setNitro(fromPlayer, nitroOn);
			}
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		TFPacketManager.packetPipeline.sendToDimension(this, player.dimension);
	}
}
