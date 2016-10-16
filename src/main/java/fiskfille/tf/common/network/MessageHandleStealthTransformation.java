package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFDataManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class MessageHandleStealthTransformation implements IMessage
{
    public int id;
    private boolean stealthForce;

    public MessageHandleStealthTransformation()
    {

    }

    public MessageHandleStealthTransformation(EntityPlayer player, boolean mode)
    {
        id = player.getEntityId();
        stealthForce = mode;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        stealthForce = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(stealthForce);
    }

    public static class Handler implements IMessageHandler<MessageHandleStealthTransformation, IMessage>
    {
        @Override
        public IMessage onMessage(MessageHandleStealthTransformation message, MessageContext ctx)
        {
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
                    TFDataManager.setStealthModeTimer(from, message.stealthForce ? 5 : 0);

                    from.worldObj.playSound(from.posX, from.posY - from.yOffset, from.posZ, TransformersMod.modid + ":transform_stealth" + (message.stealthForce ? "" : "_in"), 1, 1.25f, false);
                    TFDataManager.setInStealthModeWithoutNotify(from, message.stealthForce);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                EntityPlayer from = null;

                for (World world : MinecraftServer.getServer().worldServers)
                {
                    Entity entity = world.getEntityByID(message.id);

                    if (entity instanceof EntityPlayer)
                    {
                        from = (EntityPlayer) entity;
                        break;
                    }
                }

                if (from != null)
                {
                    TFDataManager.setInStealthMode(player, message.stealthForce);
                }
            }

            return null;
        }
    }
}