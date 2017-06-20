package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.capability.TFCapabilities;
import fiskfille.tf.common.capability.TFPlayerData;
import fiskfille.tf.common.data.TFData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Map;

public class MessageSetPlayerData extends MessageSyncBase
{
    public int id;

    public MessageSetPlayerData()
    {

    }

    public MessageSetPlayerData(EntityPlayer player, Map<TFData, Object> data)
    {
        super(player);
        this.id = player.getEntityId();
        this.playerData = data;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeInt(this.id);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        this.id = buf.readInt();
    }

    public static class Handler implements IMessageHandler<MessageSetPlayerData, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSetPlayerData message, MessageContext ctx)
        {
            TransformersMod.PROXY.schedule(() ->
            {
                EntityPlayer player = TransformersMod.PROXY.getPlayer(ctx);
                TFPlayerData data = player.getCapability(TFCapabilities.PLAYER_DATA_CAP, null);

                if (ctx.side.isClient())
                {
                    Entity entity = player.world.getEntityByID(message.id);

                    if (entity instanceof EntityPlayer)
                    {
                        data.setData(message.playerData);
                    }
                }
                else
                {
                    data.setData(message.playerData);
                    TFNetworkManager.WRAPPER.sendToDimension(new MessageSetPlayerData(player, data.getData()), player.dimension);
                }
            }, ctx);

            return null;
        }
    }
}
