package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MessagePlayerJoin implements IMessage
{
    private Map<Transformer, Boolean> canTransform;

    private int altMode;
    private boolean stealthForce;

    public MessagePlayerJoin()
    {
    }

    public MessagePlayerJoin(int altMode, boolean stealthForce, Map<Transformer, Boolean> t)
    {
        canTransform = t;
        this.altMode = altMode;
        this.stealthForce = stealthForce;
    }

    public void fromBytes(ByteBuf buf)
    {
        altMode = buf.readByte();
        stealthForce = buf.readBoolean();

        canTransform = new HashMap<Transformer, Boolean>();

        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            canTransform.put(transformer, buf.readBoolean());
        }
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(altMode);
        buf.writeBoolean(stealthForce);

        for (Entry<Transformer, Boolean> transformable : canTransform.entrySet())
        {
            buf.writeBoolean(transformable.getValue());
        }
    }

    public static class Handler implements IMessageHandler<MessagePlayerJoin, IMessage>
    {
        public IMessage onMessage(MessagePlayerJoin message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();

                TFDataManager.setAltModeWithoutNotify(player, message.altMode);
                TFDataManager.setInStealthModeWithoutNotify(player, message.stealthForce);

                TFDataManager.setTransformationTimer(player, (message.altMode != -1) ? 0 : 20);
                TFDataManager.setStealthModeTimer(player, message.stealthForce ? 0 : 5);

                if (message.canTransform != null)
                {
                    TFConfig.canTransform = message.canTransform;
                }
            }

            return null;
        }
    }
}