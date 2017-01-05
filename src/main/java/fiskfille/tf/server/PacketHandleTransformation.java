package fiskfille.tf.server;

import fiskfille.tf.data.TFPlayerData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketHandleTransformation extends TransformersPacket
{
	public boolean mode;
	public String uuid;
	
	public PacketHandleTransformation()
	{
		
	}
	
	public PacketHandleTransformation(UUID uuid, boolean mode)
	{
		this.mode = mode;
		this.uuid = uuid.toString();
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		ByteBufUtils.writeUTF8String(buffer, uuid);
		buffer.writeBoolean(mode);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		this.uuid = ByteBufUtils.readUTF8String(buffer);
		this.mode = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		EntityPlayer from = null;
		
		for (Object cPlayer : Minecraft.getMinecraft().theWorld.playerEntities) 
		{
			if (cPlayer instanceof EntityPlayer)
			{
				if (((EntityPlayer) cPlayer).getUniqueID().equals(UUID.fromString(uuid)))
				{
					from = (EntityPlayer)cPlayer;
					break;
				}
			}
		}
		
		System.out.println(from);
		
		if(from != null)
		{
			System.out.println(from.getCommandSenderName() + ": " + mode);
			TFPlayerData.setInVehichleMode(from, mode);
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		EntityPlayer from = null;
		
		for (World world : MinecraftServer.getServer().worldServers)
		{
			for (Object cPlayer : world.playerEntities) 
			{
				if (cPlayer instanceof EntityPlayer)
				{
					if (((EntityPlayer) cPlayer).getUniqueID().equals(UUID.fromString(uuid)))
					{
						from = (EntityPlayer)cPlayer;
						break;
					}
				}
			}
		}
		
		System.out.println(from);
		
		if(from != null)
		{
			System.out.println(from.getCommandSenderName() + ": " + mode);
			TFPlayerData.setInVehichleMode(from, mode);
			TFPlayerData.sync(player);
		}
	}
}