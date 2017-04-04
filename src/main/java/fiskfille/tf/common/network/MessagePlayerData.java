package fiskfille.tf.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

public class MessagePlayerData implements IMessage
{
    public int id;
    private TFData<?> type;
    private Object value;

    public MessagePlayerData()
    {

    }

    public MessagePlayerData(EntityPlayer player, TFData<?> data, Object obj)
    {
        id = player.getEntityId();
        type = data;
        value = obj;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        String id = ByteBufUtils.readUTF8String(buf);

        for (TFData<?> data : TFData.VALUES)
        {
            if (data.id.equals(id))
            {
                type = data;
                break;
            }
        }

        value = type.readDataFromNBT(ByteBufUtils.readTag(buf));
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        ByteBufUtils.writeUTF8String(buf, type.id);
        ByteBufUtils.writeTag(buf, type.writeDataToNBT(new NBTTagCompound(), value));
    }

    public static class Handler implements IMessageHandler<MessagePlayerData, IMessage>
    {
        @Override
        public IMessage onMessage(MessagePlayerData message, MessageContext ctx)
        {
            TFData type = message.type;
            Object value = message.value;

            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity entity = player.worldObj.getEntityByID(message.id);

                if (entity instanceof EntityPlayer)
                {
                    type.setWithoutNotify((EntityPlayer) entity, value);

                    if (type == TFData.ALT_MODE)
                    {
                        Transformer transformer = TFHelper.getTransformer((EntityPlayer) entity);
                        
                        if (transformer != null)
                        {
                            entity.worldObj.playSound(entity.posX, entity.posY - entity.yOffset, entity.posZ, transformer.getTransformationSound((Integer) value), 1, 1, false);
                        }
                    }
                    else if (type == TFData.STEALTH_FORCE)
                    {
                        entity.worldObj.playSound(entity.posX, entity.posY - entity.yOffset, entity.posZ, TransformersMod.modid + ":transform_stealth" + ((Boolean) value ? "" : "_in"), 1, 1.25F, false);
                    }
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
                        type.set((EntityPlayer) entity, value);
                    }
                }
            }

            return null;
        }
    }
}
