package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.network.base.TFNetworkManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTileTrigger implements IMessage
{
    private int id;
    private int x;
    private int y;
    private int z;
    private int action;

    public MessageTileTrigger()
    {

    }

    public MessageTileTrigger(EntityPlayer player, int x, int y, int z, int action)
    {
        this.id = player != null ? player.getEntityId() : 0;
        this.x = x;
        this.y = y;
        this.z = z;
        this.action = action;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        action = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(action);
    }

    public static class Handler implements IMessageHandler<MessageTileTrigger, IMessage>
    {
        @Override
        public IMessage onMessage(MessageTileTrigger message, MessageContext ctx)
        {
            EntityPlayer clientPlayer = ctx.side.isClient() ? TransformersMod.proxy.getPlayer() : ctx.getServerHandler().playerEntity;
            EntityPlayer player = null;

            if (clientPlayer.worldObj.getEntityByID(message.id) instanceof EntityPlayer)
            {
                player = (EntityPlayer) clientPlayer.worldObj.getEntityByID(message.id);
            }

            if (clientPlayer.worldObj.getTileEntity(message.x, message.y, message.z) instanceof ITileDataCallback)
            {
                ITileDataCallback callback = (ITileDataCallback) clientPlayer.worldObj.getTileEntity(message.x, message.y, message.z);
                callback.receive(player, message.action);

                if (ctx.side.isServer())
                {
                    TFNetworkManager.networkWrapper.sendToAll(new MessageTileTrigger(player, message.x, message.y, message.z, message.action));
                }
            }

            return null;
        }
    }

    public static interface ITileDataCallback
    {
        /**
         * Called when a tile gets triggered
         * 
         * @param player The player who triggered it, or null if none exists
         * @param action The trigger type
         */
        void receive(EntityPlayer player, int action);
    }
}
