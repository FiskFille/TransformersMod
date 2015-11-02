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
    private boolean vehicle;

    public MessageBroadcastState()
    {

    }

    public MessageBroadcastState(EntityPlayer player)
    {
        id = player.getEntityId();
        stealth = TFDataManager.isInStealthMode(player);
        vehicle = TFDataManager.isInVehicleMode(player);
    }

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        stealth = buf.readBoolean();
        vehicle = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(stealth);
        buf.writeBoolean(vehicle);
    }

    public static class Handler implements IMessageHandler<MessageBroadcastState, IMessage>
    {
        public IMessage onMessage(MessageBroadcastState message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity lookupEntity = player.worldObj.getEntityByID(message.id);

                if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
                {
                    EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

                    TFDataManager.setInVehicleModeWithoutNotify(lookupPlayer, message.vehicle);
                    TFDataManager.setTransformationTimer(lookupPlayer, message.vehicle ? 0 : 20);
                    TFDataManager.setInStealthModeWithoutNotify(lookupPlayer, message.stealth);
                    TFDataManager.setStealthModeTimer(lookupPlayer, message.stealth ? 0 : 5);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                TFNetworkManager.networkWrapper.sendToDimension(new MessageBroadcastState(player), player.dimension);
                TFDataManager.setInVehicleModeWithoutNotify(player, message.vehicle);
                TFDataManager.setInStealthModeWithoutNotify(player, message.stealth);
            }

            return null;
        }
    }
}
