package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.power.IEnergyContainer;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.energon.power.ReceivingHandler;
import fiskfille.tf.helper.TFEnergyHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class MessageUpdateReceivers implements IMessage
{
    private int x;
    private int y;
    private int z;
    private Set<ChunkCoordinates> receivers = new HashSet<ChunkCoordinates>();
    private Set<ChunkCoordinates> receiving = new HashSet<ChunkCoordinates>();
    private float energy = -1.0F;

    public MessageUpdateReceivers()
    {

    }

    public MessageUpdateReceivers(TileEntity tile)
    {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;

        ReceiverHandler receiverHandler = TFEnergyHelper.getReceiverHandler(tile);
        ReceivingHandler receivingHandler = TFEnergyHelper.getReceivingHandler(tile);

        if (receiverHandler != null)
        {
            this.receivers = receiverHandler.getReceiverCoords();
        }

        if (receivingHandler != null)
        {
            this.receiving = receivingHandler.getReceivingCoords();
        }

        if (tile instanceof IEnergyContainer)
        {
            this.energy = ((IEnergyContainer) tile).getEnergy();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        energy = buf.readFloat();

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
            receiving.add(new ChunkCoordinates(buf.readInt(), buf.readInt(), buf.readInt()));
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeFloat(energy);

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

        buf.writeInt(receiving.size());

        for (ChunkCoordinates coords : receiving)
        {
            buf.writeInt(coords.posX);
            buf.writeInt(coords.posY);
            buf.writeInt(coords.posZ);
        }
    }

    public static class Handler implements IMessageHandler<MessageUpdateReceivers, IMessage>
    {
        @Override
        public IMessage onMessage(MessageUpdateReceivers message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                World world = player.worldObj;

                TileEntity tile = world.getTileEntity(message.x, message.y, message.z);

                ReceiverHandler receiverHandler = TFEnergyHelper.getReceiverHandler(tile);
                ReceivingHandler receivingHandler = TFEnergyHelper.getReceivingHandler(tile);

                if (receiverHandler != null)
                {
                    receiverHandler.reset(world, message.receivers);
                }

                if (receivingHandler != null)
                {
                    receivingHandler.reset(message.receiving);
                }

                if (message.energy >= 0.0F && tile instanceof IEnergyContainer)
                {
                    ((IEnergyContainer) tile).setEnergy(message.energy);
                }
            }

            return null;
        }
    }
}
