package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class MessageSendFlying implements IMessage
{
    private int id;
    private boolean flying;

    public MessageSendFlying()
    {

    }

    public MessageSendFlying(EntityPlayer player, boolean f)
    {
        id = player.getEntityId();
        flying = f;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        flying = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(flying);
    }

    public static class Handler implements IMessageHandler<MessageSendFlying, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSendFlying message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                EntityPlayer from = null;
                Entity entity = player.worldObj.getEntityByID(message.id);

                if (entity instanceof EntityPlayer)
                {
                    from = (EntityPlayer) entity;
                }

                if (from != null && from != player)
                {
                    from.capabilities.isFlying = message.flying;
                }
            }

            return null;
        }
    }
}