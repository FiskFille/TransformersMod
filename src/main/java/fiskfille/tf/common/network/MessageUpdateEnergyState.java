package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.power.IEnergyContainer;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.helper.TFEnergyHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class MessageUpdateEnergyState implements IMessage
{
    private int x;
    private int y;
    private int z;
    private Set<ChunkCoordinates> receivers = new HashSet<ChunkCoordinates>();
    private Set<ChunkCoordinates> transmitters = new HashSet<ChunkCoordinates>();

    private boolean container;
    private float energy = -1.0F;
    private float energyUsage = 0.0F;

    public MessageUpdateEnergyState()
    {

    }

    public MessageUpdateEnergyState(TileEntity tile)
    {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;

        TransmissionHandler transmissionHandler = TFEnergyHelper.getTransmissionHandler(tile);
        ReceiverHandler receiverHandler = TFEnergyHelper.getReceiverHandler(tile);

        if (transmissionHandler != null)
        {
            this.receivers = transmissionHandler.getReceiverCoords();
        }

        if (receiverHandler != null)
        {
            this.transmitters = receiverHandler.getTransmitterCoords();
        }

        if (tile instanceof IEnergyContainer)
        {
            IEnergyContainer container = (IEnergyContainer) tile;
            this.energy = container.getEnergy();
            this.energyUsage = container.getEnergyUsage();
            this.container = true;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        container = buf.readBoolean();

        if (container)
        {
            energy = buf.readFloat();
            energyUsage = buf.readFloat();
        }

        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();

        int receiverCount = buf.readInt();

        for (int i = 0; i < receiverCount; i++)
        {
            receivers.add(new ChunkCoordinates(buf.readInt(), buf.readInt(), buf.readInt()));
        }

        int receivingCount = buf.readInt();

        for (int i = 0; i < receivingCount; i++)
        {
            transmitters.add(new ChunkCoordinates(buf.readInt(), buf.readInt(), buf.readInt()));
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(container);

        if (container)
        {
            buf.writeFloat(energy);
            buf.writeFloat(energyUsage);
        }

        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

        buf.writeInt(receivers.size());

        for (ChunkCoordinates coords : receivers)
        {
            buf.writeInt(coords.posX);
            buf.writeInt(coords.posY);
            buf.writeInt(coords.posZ);
        }

        buf.writeInt(transmitters.size());

        for (ChunkCoordinates coords : transmitters)
        {
            buf.writeInt(coords.posX);
            buf.writeInt(coords.posY);
            buf.writeInt(coords.posZ);
        }
    }

    public static class Handler implements IMessageHandler<MessageUpdateEnergyState, IMessage>
    {
        @Override
        public IMessage onMessage(MessageUpdateEnergyState message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                World world = player.worldObj;

                TileEntity tile = world.getTileEntity(message.x, message.y, message.z);

                TransmissionHandler transmissionHandler = TFEnergyHelper.getTransmissionHandler(tile);
                ReceiverHandler receiverHandler = TFEnergyHelper.getReceiverHandler(tile);

                if (transmissionHandler != null)
                {
                    transmissionHandler.reset(world, message.receivers);
                }

                if (receiverHandler != null)
                {
                    receiverHandler.reset(world, message.transmitters);
                }

                if (message.container && tile instanceof IEnergyContainer)
                {
                    IEnergyContainer container = (IEnergyContainer) tile;
                    container.setEnergy(message.energy);
                    container.setEnergyUsage(message.energyUsage);
                }
            }

            return null;
        }
    }
}
