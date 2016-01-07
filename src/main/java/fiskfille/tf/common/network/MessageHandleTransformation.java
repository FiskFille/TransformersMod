package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFDataManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class MessageHandleTransformation implements IMessage
{
    public int id;
    private byte mode;

    public MessageHandleTransformation()
    {

    }

    public MessageHandleTransformation(EntityPlayer player, int altMode)
    {
        id = player.getEntityId();
        mode = (byte) altMode;
    }

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        mode = buf.readByte();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeByte(mode);
    }

    public static class Handler implements IMessageHandler<MessageHandleTransformation, IMessage>
    {
        public IMessage onMessage(MessageHandleTransformation message, MessageContext ctx)
        {
            byte altMode = message.mode;

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
                    TFDataManager.setAltModeWithoutNotify(from, altMode);
                    boolean isTransformed = altMode != -1;

                    TFDataManager.setTransformationTimer(from, isTransformed ? 20 : 0);

                    String suffix = isTransformed ? "vehicle" : "robot";
                    from.worldObj.playSound(from.posX, from.posY - from.yOffset, from.posZ, TransformersMod.modid + ":transform_" + suffix, 1, 1, false);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;

                if (player != null)
                {
                    TFDataManager.setAltMode(player, altMode);
                }
            }

            return null;
        }
    }
}