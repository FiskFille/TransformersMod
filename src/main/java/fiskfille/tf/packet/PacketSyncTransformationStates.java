package fiskfille.tf.packet;

import fiskfille.tf.data.TFPlayerData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketSyncTransformationStates extends TransformersPacket
{
	private Map<UUID, Boolean[]> states;

	public PacketSyncTransformationStates()
	{

	}

	public PacketSyncTransformationStates(Map<UUID, Boolean[]> states)
	{
		this.states = states;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(states.size());

		for (Entry<UUID, Boolean[]> entry : states.entrySet())
		{
			ByteBufUtils.writeUTF8String(buffer, entry.getKey().toString());
			buffer.writeBoolean(entry.getValue()[0]);
			buffer.writeBoolean(entry.getValue()[1]);
		}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		states = new HashMap<UUID, Boolean[]>();

		int count = buffer.readInt();

		for (int i = 0; i < count; i++) 
		{
			states.put(UUID.fromString(ByteBufUtils.readUTF8String(buffer)), new Boolean[]{buffer.readBoolean(), buffer.readBoolean()});
		}
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		if (states != null)
		{
			for (Object cPlayer : Minecraft.getMinecraft().theWorld.playerEntities) 
			{
				if (cPlayer instanceof EntityPlayer)
				{
					for (Entry<UUID, Boolean[]> state : states.entrySet()) 
					{
						EntityPlayer currentPlayer = (EntityPlayer) cPlayer;

						UUID uuid = state.getKey();
						if (uuid != null && uuid.equals(currentPlayer.getUniqueID()))
						{
							TFPlayerData.getData(currentPlayer).mode = state.getValue()[0];
							TFPlayerData.getData(currentPlayer).stealthMode = state.getValue()[1];
							//TFDataManager.setTransformationTimer(currentPlayer, state.getValue() ? 0 : 10);
						}
					}
				}
			}
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
	}
}