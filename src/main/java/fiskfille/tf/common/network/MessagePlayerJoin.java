package fiskfille.tf.common.network;

import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;

public class MessagePlayerJoin extends MessageSyncBase
{
    private Map<Transformer, Boolean> canTransform;

    public MessagePlayerJoin()
    {
    }

    public MessagePlayerJoin(EntityPlayer player)
    {
        super(player);
        canTransform = TFConfig.canTransform;

        if (canTransform.isEmpty())
        {
            for (Transformer transformer : TransformersAPI.getTransformers())
            {
                transformer.getName();
                canTransform.put(transformer, true);
            }
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        canTransform = Maps.newHashMap();

        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            canTransform.put(transformer, buf.readBoolean());
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);

        for (Entry<Transformer, Boolean> transformable : canTransform.entrySet())
        {
            buf.writeBoolean(transformable.getValue());
        }
    }

    public static class Handler implements IMessageHandler<MessagePlayerJoin, IMessage>
    {
        @Override
        public IMessage onMessage(MessagePlayerJoin message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();

                for (Entry<TFData, Object> e : message.playerData.entrySet())
                {
                    e.getKey().setWithoutNotify(player, e.getValue());
                }

                if (message.canTransform != null)
                {
                    TFConfig.canTransform = message.canTransform;
                }
            }

            return null;
        }
    }
}
