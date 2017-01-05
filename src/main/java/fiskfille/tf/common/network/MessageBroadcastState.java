package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.network.base.TFNetworkManager;
import io.netty.buffer.ByteBuf;

import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageBroadcastState extends MessageSyncBase
{
    private int id;

    public MessageBroadcastState()
    {

    }

    public MessageBroadcastState(EntityPlayer player)
    {
        super(player);
        id = player.getEntityId();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeInt(id);
    }

    public static class Handler implements IMessageHandler<MessageBroadcastState, IMessage>
    {
        @Override
        public IMessage onMessage(MessageBroadcastState message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity lookupEntity = player.worldObj.getEntityByID(message.id);

                if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
                {
                    EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

                    for (Entry<TFData, Object> e : message.playerData.entrySet())
                    {
                        e.getKey().setWithoutNotify(lookupPlayer, e.getValue());
                    }
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;

                for (Entry<TFData, Object> e : message.playerData.entrySet())
                {
                    e.getKey().setWithoutNotify(player, e.getValue());
                }

                TFNetworkManager.networkWrapper.sendToDimension(new MessageBroadcastState(player), player.dimension);
            }

            return null;
        }
    }
}
