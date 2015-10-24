package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.transformer.cloudtrap.CloudtrapJetpackManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class MessageCloudtrapJetpack implements IMessage
{
    private int id;
    private boolean jetpacking;

    public MessageCloudtrapJetpack()
    {

    }

    public MessageCloudtrapJetpack(EntityPlayer player, boolean j)
    {
        id = player.getEntityId();
        jetpacking = j;
    }

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        jetpacking = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(jetpacking);
    }

    public static class Handler implements IMessageHandler<MessageCloudtrapJetpack, IMessage>
    {
        public IMessage onMessage(MessageCloudtrapJetpack message, MessageContext ctx)
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
                    CloudtrapJetpackManager.cloudtrapJetpacking.put(from, message.jetpacking);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;

                TFNetworkManager.networkWrapper.sendToDimension(new MessageCloudtrapJetpack(player, message.jetpacking), player.dimension);
            }

            return null;
        }
    }
}