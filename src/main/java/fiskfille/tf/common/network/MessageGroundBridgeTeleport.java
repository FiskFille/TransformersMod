package fiskfille.tf.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.common.block.BlockGroundBridgeTeleporter;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;

public class MessageGroundBridgeTeleport implements IMessage
{
    private int id;
    private int x, y, z;

    public MessageGroundBridgeTeleport()
    {
    }

    public MessageGroundBridgeTeleport(Entity entity, TileEntityGroundBridgeTeleporter tile)
    {
        id = entity.getEntityId();
        x = tile.xCoord;
        y = tile.yCoord;
        z = tile.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    public static class Handler implements IMessageHandler<MessageGroundBridgeTeleport, IMessage>
    {
        @Override
        public IMessage onMessage(MessageGroundBridgeTeleport message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.id);

                if (entity != null && entity.worldObj.getTileEntity(message.x, message.y, message.z) instanceof TileEntityGroundBridgeTeleporter)
                {
                    TileEntityGroundBridgeTeleporter teleporter = (TileEntityGroundBridgeTeleporter)entity.worldObj.getTileEntity(message.x, message.y, message.z);
                    BlockGroundBridgeTeleporter.doTeleportClient(entity, teleporter);
                }
            }

            return null;
        }
    }
}
