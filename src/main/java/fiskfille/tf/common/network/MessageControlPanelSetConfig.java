package fiskfille.tf.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;

public class MessageControlPanelSetConfig implements IMessage
{
    private DimensionalCoords coordinates;
    private DimensionalCoords configuration;

    public MessageControlPanelSetConfig()
    {

    }

    public MessageControlPanelSetConfig(DimensionalCoords coords, DimensionalCoords dest)
    {
        this.coordinates = coords;
        this.configuration = dest;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        coordinates = new DimensionalCoords().fromBytes(buf);
        configuration = new DimensionalCoords().fromBytes(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        coordinates.toBytes(buf);
        configuration.toBytes(buf);
    }

    public static class Handler implements IMessageHandler<MessageControlPanelSetConfig, IMessage>
    {
        @Override
        public IMessage onMessage(MessageControlPanelSetConfig message, MessageContext ctx)
        {
            if (ctx.side.isServer())
            {
                DimensionalCoords coords = message.coordinates;
                World world = MinecraftServer.getServer().worldServerForDimension(coords.dimension);

                if (world != null)
                {
                    TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(coords.posX, coords.posY, coords.posZ);

                    if (tile != null)
                    {
                        tile.setSwitchesTo(message.configuration);
                        tile.markBlockForUpdate();
                    }
                }
            }

            return null;
        }
    }
}
