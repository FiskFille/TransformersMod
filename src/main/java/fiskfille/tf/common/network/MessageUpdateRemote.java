package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.groundbridge.RemoteData;
import io.netty.buffer.ByteBuf;

public class MessageUpdateRemote implements IMessage
{
    private RemoteData data;
    private boolean open;

    public MessageUpdateRemote()
    {
    }

    public MessageUpdateRemote(RemoteData data, boolean open)
    {
        this.data = data;
        this.open = open;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        open = buf.readBoolean();
        data = new RemoteData(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(open);
        data.serialize(buf);
    }

    public static class Handler implements IMessageHandler<MessageUpdateRemote, IMessage>
    {
        @Override
        public IMessage onMessage(MessageUpdateRemote message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                TransformersMod.proxy.updateRemote(message.data, message.open);
            }

            return null;
        }
    }
}