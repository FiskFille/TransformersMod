package fiskfille.tf.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverEntry;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;

public class MessageConnectReceiver implements IMessage
{
    private DimensionalCoords transmitterCoords;
    private DimensionalCoords receiverCoords;

    public MessageConnectReceiver()
    {

    }

    public MessageConnectReceiver(DimensionalCoords coords, DimensionalCoords coords1)
    {
        transmitterCoords = coords;
        receiverCoords = coords1;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        transmitterCoords = new DimensionalCoords().fromBytes(buf);
        receiverCoords = new DimensionalCoords().fromBytes(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        transmitterCoords.toBytes(buf);
        receiverCoords.toBytes(buf);
    }

    public static class Handler implements IMessageHandler<MessageConnectReceiver, IMessage>
    {
        @Override
        public IMessage onMessage(MessageConnectReceiver message, MessageContext ctx)
        {
            if (ctx.side.isServer())
            {
                DimensionalCoords coords = message.transmitterCoords;
                DimensionalCoords coords1 = message.receiverCoords;
                World world = MinecraftServer.getServer().worldServerForDimension(coords.dimension);

                if (world != null)
                {
                    TileEntity transmitterTile = world.getTileEntity(coords.posX, coords.posY, coords.posZ);
                    TileEntity receiverTile = world.getTileEntity(coords1.posX, coords1.posY, coords1.posZ);

                    if (transmitterTile instanceof IEnergyTransmitter && receiverTile instanceof IEnergyReceiver)
                    {
                        IEnergyTransmitter transmitter = (IEnergyTransmitter) transmitterTile;
                        IEnergyReceiver receiver = (IEnergyReceiver) receiverTile;
                        
                        TransmissionHandler handler = transmitter.getTransmissionHandler();
                        
                        if (handler.getReceiver(coords1) != null)
                        {
                            handler.remove(new ReceiverEntry(receiverTile));
                        }
                        else
                        {
                            handler.add(new ReceiverEntry(receiverTile));
                        }
                    }
                }
            }

            return null;
        }
    }
}
