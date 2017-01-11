package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MessageCloseRemote implements IMessage
{
    private int x;
    private int y;
    private int z;
    private int dimension;

    public MessageCloseRemote()
    {
    }

    public MessageCloseRemote(int x, int y, int z, int dimension)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        dimension = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(dimension);
    }

    public static class Handler implements IMessageHandler<MessageCloseRemote, IMessage>
    {
        @Override
        public IMessage onMessage(MessageCloseRemote message, MessageContext ctx)
        {
            if (ctx.side.isServer())
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;

                World world = MinecraftServer.getServer().worldServerForDimension(message.dimension);

                TileEntity tile = world.getTileEntity(message.x, message.y, message.z);

                if (tile instanceof TileEntityControlPanel)
                {
                    TileEntityControlPanel controlPanel = (TileEntityControlPanel) tile;

                    controlPanel.controllingPlayers.remove(player);
                }
            }

            return null;
        }
    }
}