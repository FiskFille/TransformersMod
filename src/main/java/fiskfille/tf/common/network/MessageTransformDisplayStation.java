package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MessageTransformDisplayStation implements IMessage
{
    private int x;
    private int y;
    private int z;

    public MessageTransformDisplayStation()
    {

    }

    public MessageTransformDisplayStation(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    public static class Handler implements IMessageHandler<MessageTransformDisplayStation, IMessage>
    {
        public IMessage onMessage(MessageTransformDisplayStation message, MessageContext ctx)
        {
            EntityPlayer player = ctx.side.isClient() ? TransformersMod.proxy.getPlayer() : ctx.getServerHandler().playerEntity;
            World world = player.worldObj;

            TileEntityDisplayStation tileentity = (TileEntityDisplayStation) world.getTileEntity(message.x, message.y, message.z);

            if (tileentity != null)
            {
                if (tileentity.transform())
                {
                    world.markBlockForUpdate(message.x, message.y, message.z);
                }
            }

            return null;
        }
    }
}
