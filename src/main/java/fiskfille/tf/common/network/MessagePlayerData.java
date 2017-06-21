package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.transformer.base.Transformer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
        this.id = player.getEntityId();
        this.type = data;
        this.value = obj;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.id = buf.readInt();
        String id = ByteBufUtils.readUTF8String(buf);

        for (TFData<?> data : TFData.VALUES)
        {
            if (data.id.equals(id))
            {
                this.type = data;
                break;
            }
        }

        this.value = this.type.readDataFromNBT(ByteBufUtils.readTag(buf));
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.id);
        ByteBufUtils.writeUTF8String(buf, this.type.id);
        ByteBufUtils.writeTag(buf, this.type.writeDataToNBT(new NBTTagCompound(), this.value));
    }

    public static class Handler implements IMessageHandler<MessagePlayerData, IMessage>
    {
        @Override
        public IMessage onMessage(MessagePlayerData message, MessageContext ctx)
        {
            TransformersMod.PROXY.schedule(() ->
            {
                EntityPlayer player = TransformersMod.PROXY.getPlayer(ctx);

                TFData type = message.type;
                Object value = message.value;

                if (ctx.side.isClient())
                {
                    Entity entity = player.world.getEntityByID(message.id);

                    if (entity instanceof EntityPlayer)
                    {
                        type.setWithoutNotify((EntityPlayer) entity, value);

                        Transformer transformer = TFHelper.getTransformer((EntityPlayer) entity);

                        if (transformer != null)
                        {
                            if (type == TFData.ALT_MODE)
                            {
                                SoundEvent sound = transformer.getTransformationSound((Integer) value);
                                entity.world.playSound(entity.posX, entity.posY, entity.posZ, sound, SoundCategory.PLAYERS, 1, 1, false);
                            }
                            else if (type == TFData.STEALTH_FORCE)
                            {
                                SoundEvent sound = transformer.getStealthTransformationSound((Boolean) value);
                                entity.world.playSound(entity.posX, entity.posY, entity.posZ, sound, SoundCategory.PLAYERS, 1, 1.25F, false);
                            }
                        }
                    }
                }
                else
                {
                    type.set(player, value);
                }
            }, ctx);

            return null;
        }
    }
}
