package fiskfille.tf.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;

public class MessageControlPanelSetConfig implements IMessage
{
    private int x;
    private int y;
    private int z;
    private DimensionalCoords configuration;

    public MessageControlPanelSetConfig()
    {

    }

    public MessageControlPanelSetConfig(int x, int y, int z, DimensionalCoords coords)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.configuration = coords;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        configuration = new DimensionalCoords(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(configuration.posX);
        buf.writeInt(configuration.posY);
        buf.writeInt(configuration.posZ);
        buf.writeInt(configuration.dimension);
    }

    public static class Handler implements IMessageHandler<MessageControlPanelSetConfig, IMessage>
    {
        @Override
        public IMessage onMessage(MessageControlPanelSetConfig message, MessageContext ctx)
        {
            EntityPlayer clientPlayer = ctx.side.isClient() ? TransformersMod.proxy.getPlayer() : ctx.getServerHandler().playerEntity;
            EntityPlayer player = null;
            World world = clientPlayer.worldObj;

            TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(message.x, message.y, message.z);

            if (tile != null)
            {
                tile.setSwitchesTo(message.configuration);

                if (ctx.side.isServer())
                {
                    TFNetworkManager.networkWrapper.sendToAll(new MessageControlPanelSetConfig(tile.xCoord, tile.yCoord, tile.zCoord, message.configuration));
                }
            }

            return null;
        }
    }
}
