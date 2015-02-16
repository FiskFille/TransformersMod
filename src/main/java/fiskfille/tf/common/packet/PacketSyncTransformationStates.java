package fiskfille.tf.common.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class PacketSyncTransformationStates implements IMessage
{
	private Map<UUID, Boolean[]> states;

	public PacketSyncTransformationStates()
	{

	}

	public PacketSyncTransformationStates(Map<UUID, Boolean[]> s)
	{
		states = s;
	}

    public void fromBytes(ByteBuf buf)
    {
        states = new HashMap<UUID, Boolean[]>();
        int count = buf.readInt();

        for (int i = 0; i < count; i++) states.put(UUID.fromString(ByteBufUtils.readUTF8String(buf)), new Boolean[]{buf.readBoolean(), buf.readBoolean()});
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(states.size());

        for (Entry<UUID, Boolean[]> entry : states.entrySet())
        {
            ByteBufUtils.writeUTF8String(buf, entry.getKey().toString());
            buf.writeBoolean(entry.getValue()[0]);
            buf.writeBoolean(entry.getValue()[1]);
        }
    }

    public static class Handler implements IMessageHandler<PacketSyncTransformationStates, IMessage>
    {
        public IMessage onMessage(PacketSyncTransformationStates message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                if (message.states != null)
                {
                    for (Object cPlayer : TransformersMod.proxy.getWorld().playerEntities)
                    {
                        if (cPlayer instanceof EntityPlayer)
                        {
                            for (Entry<UUID, Boolean[]> state : message.states.entrySet())
                            {
                                EntityPlayer currentPlayer = (EntityPlayer) cPlayer;

                                UUID uuid = state.getKey();
                                if (uuid != null && uuid.equals(currentPlayer.getUniqueID()))
                                {
                                    TFPlayerData.getData(currentPlayer).vehicle = state.getValue()[0];
                                    TFPlayerData.getData(currentPlayer).stealthForce = state.getValue()[1];
                                }
                            }
                        }
                    }
                }
            }

            return null;
        }
    }
}