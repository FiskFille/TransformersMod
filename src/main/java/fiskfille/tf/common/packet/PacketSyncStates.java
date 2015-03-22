package fiskfille.tf.common.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncStates implements IMessage
{
    private Map<UUID, Boolean[]> states;

    public PacketSyncStates()
    {

    }

    public PacketSyncStates(Map<UUID, Boolean[]> s)
    {
        states = s;
    }

    public void fromBytes(ByteBuf buf)
    {
        states = new HashMap<UUID, Boolean[]>();
        int count = buf.readInt();

        for (int i = 0; i < count; i++)
            states.put(UUID.fromString(ByteBufUtils.readUTF8String(buf)),
                    new Boolean[] { buf.readBoolean(), buf.readBoolean() });
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

    public static class Handler implements
            IMessageHandler<PacketSyncStates, IMessage>
    {
        public IMessage onMessage(PacketSyncStates message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                if (message.states != null)
                {
                    for (Object cPlayer : TransformersMod.proxy.getWorld().playerEntities)
                    {
                        if (cPlayer instanceof EntityPlayer)
                        {
                            for (Entry<UUID, Boolean[]> state : message.states
                                    .entrySet())
                            {
                                EntityPlayer currentPlayer = (EntityPlayer) cPlayer;

                                UUID uuid = state.getKey();

                                if (uuid != null
                                        && uuid.equals(currentPlayer
                                                .getUniqueID()))
                                {
                                    TFPlayerData data = TFPlayerData
                                            .getData(currentPlayer);

                                    data.vehicle = state.getValue()[0];
                                    data.stealthForce = state.getValue()[1];
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