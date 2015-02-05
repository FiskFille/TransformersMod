package fiskfille.tf.packet;

import fiskfille.tf.TransformersMod;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

//Not in use
public class PacketSound extends TransformersPacket
{
	private String sound;
	private float volume;
	private float pitch;
	
	private int id;
	
	public PacketSound()
	{
		
	}
	
	public PacketSound(String sound, Entity entity, float volume, float pitch)
	{
		this.id = entity.getEntityId();
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		ByteBufUtils.writeUTF8String(buffer, sound);
		buffer.writeInt(id);
		buffer.writeFloat(volume);
		buffer.writeFloat(pitch);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		sound = ByteBufUtils.readUTF8String(buffer);
		id = buffer.readInt();
		volume = buffer.readFloat();
		pitch = buffer.readFloat();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		Entity entity = player.worldObj.getEntityByID(id);
		
		if (entity != null)
		{
			entity.worldObj.playSound(entity.posX, entity.posY - (double)entity.yOffset, entity.posZ, TransformersMod.modid + ":" + sound, 1, 1, false);
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		TransformersMod.packetPipeline.sendToAll(this);
	}
}
