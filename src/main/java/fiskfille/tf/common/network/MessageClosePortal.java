package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class MessageClosePortal implements IMessage
{
    private int x;
    private int y;
    private int z;

    public MessageClosePortal()
    {
    }

    public MessageClosePortal(int x, int y, int z)
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

    public static class Handler implements IMessageHandler<MessageClosePortal, IMessage>
    {
        public IMessage onMessage(MessageClosePortal message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                TileEntity tile = player.worldObj.getTileEntity(message.x, message.y, message.z);

                if (tile instanceof TileEntityGroundBridgeTeleporter)
                {
                    ((TileEntityGroundBridgeTeleporter) tile).clientClosing = true;
                }
            }

            return null;
        }
    }
}
