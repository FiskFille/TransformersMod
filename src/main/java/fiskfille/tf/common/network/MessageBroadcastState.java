package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Map;

public class MessageBroadcastState extends MessageSyncBase
{
    private int id;

    public MessageBroadcastState()
    {

    }

    public MessageBroadcastState(EntityPlayer player)
    {
        super(player);
        this.id = player.getEntityId();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        this.id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeInt(this.id);
    }

    public static class Handler implements IMessageHandler<MessageBroadcastState, IMessage>
    {
        @Override
        public IMessage onMessage(MessageBroadcastState message, MessageContext ctx)
        {
            TransformersMod.PROXY.schedule(() ->
            {
                EntityPlayer player = TransformersMod.PROXY.getPlayer(ctx);

                if (ctx.side.isClient())
                {
                    Entity lookupEntity = player.world.getEntityByID(message.id);

                    if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
                    {
                        EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

                        for (Map.Entry<TFData, Object> e : message.playerData.entrySet())
                        {
                            e.getKey().setWithoutNotify(lookupPlayer, e.getValue());
                        }
                    }
                }
                else
                {
                    for (Map.Entry<TFData, Object> e : message.playerData.entrySet())
                    {
                        e.getKey().setWithoutNotify(player, e.getValue());
                    }

                    TFNetworkManager.WRAPPER.sendToDimension(new MessageBroadcastState(player), player.dimension);
                }
            }, ctx);

            return null;
        }
    }
}
