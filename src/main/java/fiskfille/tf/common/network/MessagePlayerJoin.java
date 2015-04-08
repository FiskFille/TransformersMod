package fiskfille.tf.common.network;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.playerdata.TFPlayerData;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayerJoin implements IMessage
{
	private Map<Transformer, Boolean> canTransform;

	public MessagePlayerJoin()
	{
	}

	public MessagePlayerJoin(Map<Transformer, Boolean> t)
	{
		canTransform = t;
	}

	public void fromBytes(ByteBuf buf)
	{
		canTransform = new HashMap<Transformer, Boolean>();

		for (Transformer transformer : TransformersAPI.getTransformers())
			canTransform.put(transformer, buf.readBoolean());
	}

	public void toBytes(ByteBuf buf)
	{
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
				if(message.canTransform != null)
				{
					TFConfig.canTransform = message.canTransform;
				}
			}

			return null;
		}
	}
}