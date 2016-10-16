package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.network.base.TFNetworkManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class MessageBroadcastState implements IMessage
{
    private int id;
    private boolean stealth;
    private int altMode;

    public MessageBroadcastState()
    {

    }

    public MessageBroadcastState(EntityPlayer player)
    {
        id = player.getEntityId();
        stealth = TFDataManager.isInStealthMode(player);
        altMode = TFDataManager.getAltMode(player);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        stealth = buf.readBoolean();
        altMode = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(stealth);
        buf.writeByte(altMode);
    }

    public static class Handler implements IMessageHandler<MessageBroadcastState, IMessage>
    {
        @Override
        public IMessage onMessage(MessageBroadcastState message, MessageContext ctx)
        {
            int altMode = message.altMode;
            boolean stealth = message.stealth;
            boolean isTransformed = altMode != -1;

            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity lookupEntity = player.worldObj.getEntityByID(message.id);

                if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
                {
                    EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

                    TFDataManager.setAltModeWithoutNotify(lookupPlayer, altMode);
                    TFDataManager.setTransformationTimer(lookupPlayer, isTransformed ? 0 : 20);
                    TFDataManager.setInStealthModeWithoutNotify(lookupPlayer, stealth);
                    TFDataManager.setStealthModeTimer(lookupPlayer, stealth ? 0 : 5);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                TFNetworkManager.networkWrapper.sendToDimension(new MessageBroadcastState(player), player.dimension);
                TFDataManager.setAltModeWithoutNotify(player, altMode);
                TFDataManager.setInStealthModeWithoutNotify(player, stealth);
            }

            return null;
        }
    }
}
