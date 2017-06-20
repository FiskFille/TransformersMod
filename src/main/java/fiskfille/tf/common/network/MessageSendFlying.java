package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSendFlying implements IMessage
{
    private int id;
    private boolean flying;

    public MessageSendFlying()
    {

    }

    public MessageSendFlying(EntityPlayer player, boolean flying)
    {
        this.id = player.getEntityId();
        this.flying = flying;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.id);
        buf.writeBoolean(this.flying);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.id = buf.readInt();
        this.flying = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<MessageSendFlying, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSendFlying message, MessageContext ctx)
        {
            TransformersMod.PROXY.schedule(() ->
            {
                if (ctx.side.isClient())
                {
                    EntityPlayer player = TransformersMod.PROXY.getPlayer(ctx);
                    EntityPlayer from = null;
                    Entity entity = player.world.getEntityByID(message.id);

                    if (entity instanceof EntityPlayer)
                    {
                        from = (EntityPlayer) entity;
                    }

                    if (from != null && from != player)
                    {
                        from.capabilities.isFlying = message.flying;
                    }
                }
            }, ctx);

            return null;
        }
    }
}
