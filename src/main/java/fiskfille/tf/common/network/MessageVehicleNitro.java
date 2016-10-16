package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.common.network.base.TFNetworkManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class MessageVehicleNitro implements IMessage
{
    private int id;
    private boolean nitroOn;

    public MessageVehicleNitro()
    {

    }

    public MessageVehicleNitro(EntityPlayer player, boolean n)
    {
        id = player.getEntityId();
        nitroOn = n;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        nitroOn = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(nitroOn);
    }

    public static class Handler implements IMessageHandler<MessageVehicleNitro, IMessage>
    {
        @Override
        public IMessage onMessage(MessageVehicleNitro message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity entity = player.worldObj.getEntityByID(message.id);

                if (entity instanceof EntityPlayer)
                {
                    EntityPlayer fromPlayer = (EntityPlayer) entity;
                    if (fromPlayer != player)
                    {
                        NitroParticleHandler.setNitro(fromPlayer, message.nitroOn);
                    }
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                TFNetworkManager.networkWrapper.sendToDimension(new MessageVehicleNitro(player, message.nitroOn), player.dimension);
            }

            return null;
        }
    }
}
