package fiskfille.tf.common.network;

import io.netty.buffer.ByteBuf;

import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.data.TFPlayerData;
import fiskfille.tf.common.network.base.TFNetworkManager;

public class MessageSetPlayerData extends MessageSyncBase
{
    public int id;

    public MessageSetPlayerData()
    {

    }

    public MessageSetPlayerData(EntityPlayer player, Map<TFData, Object> data)
    {
        super(player);
        id = player.getEntityId();
        playerData = data;
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
        super.fromBytes(buf);
        buf.writeInt(id);
    }

    public static class Handler implements IMessageHandler<MessageSetPlayerData, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSetPlayerData message, MessageContext ctx)
        {
            Map<TFData, Object> playerData = message.playerData;

            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity entity = player.worldObj.getEntityByID(message.id);

                if (entity instanceof EntityPlayer)
                {
                    TFPlayerData.getData((EntityPlayer) entity).data = playerData;
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;

                if (player != null)
                {
                    Entity entity = player.worldObj.getEntityByID(message.id);

                    if (entity instanceof EntityPlayer)
                    {
                        TFPlayerData.getData((EntityPlayer) entity).data = playerData;
                        TFNetworkManager.networkWrapper.sendToDimension(new MessageSetPlayerData((EntityPlayer) entity, playerData), player.dimension);
                    }
                }
            }

            return null;
        }
    }
}
