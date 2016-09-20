package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSetReceivers implements IMessage
{
    private int x;
    private int y;
    private int z;
    private List<ChunkCoordinates> receivers = Lists.newArrayList();

    public MessageSetReceivers()
    {

    }

    public MessageSetReceivers(int x, int y, int z, List<ChunkCoordinates> receivers)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.receivers = receivers;
    }

    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        int i = buf.readInt();
        
        for (int j = 0; j < i; ++j)
        {
        	ChunkCoordinates coords = new ChunkCoordinates(buf.readInt(), buf.readInt(), buf.readInt());
        	receivers.add(coords);
        }
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(receivers.size());
        
        for (int i = 0; i < receivers.size(); ++i)
        {
        	ChunkCoordinates coords = receivers.get(i);
        	buf.writeInt(coords.posX);
        	buf.writeInt(coords.posY);
        	buf.writeInt(coords.posZ);
        }
    }

    public static class Handler implements IMessageHandler<MessageSetReceivers, IMessage>
    {
        public IMessage onMessage(MessageSetReceivers message, MessageContext ctx)
        {
            EntityPlayer clientPlayer = ctx.side.isClient() ? TransformersMod.proxy.getPlayer() : ctx.getServerHandler().playerEntity;
            World world = clientPlayer.worldObj;

            TileEntity tile = world.getTileEntity(message.x, message.y, message.z);
            List<ChunkCoordinates> list = message.receivers;
            
            if (tile instanceof IEnergyTransmitter)
            {
            	IEnergyTransmitter transmitter = (IEnergyTransmitter)tile;
            	ReceiverHandler handler = transmitter.getReceiverHandler();
            	
            	handler.receiverCoords = list;
            }

            return null;
        }
    }
}
