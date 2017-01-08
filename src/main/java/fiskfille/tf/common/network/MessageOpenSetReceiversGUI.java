package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageOpenSetReceiversGUI implements IMessage
{
    private ChunkCoordinates coordinates;
    private List<ChunkCoordinates> grandparents;

    public MessageOpenSetReceiversGUI()
    {
    }

    public MessageOpenSetReceiversGUI(ChunkCoordinates coordinates, List<ChunkCoordinates> grandparents)
    {
        this.coordinates = coordinates;
        this.grandparents = grandparents;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        coordinates = readCoordinates(buf);

        int coordCount = buf.readByte() & 0xFF;

        grandparents = new ArrayList<ChunkCoordinates>();

        for (int j = 0; j < coordCount; j++)
        {
            grandparents.add(readCoordinates(buf));
        }
    }

    private ChunkCoordinates readCoordinates(ByteBuf buf)
    {
        return new ChunkCoordinates(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        writeCoordinates(coordinates, buf);
        buf.writeByte(grandparents.size() & 0xFF);

        for (ChunkCoordinates coordinates : grandparents)
        {
            writeCoordinates(coordinates, buf);
        }
    }

    private void writeCoordinates(ChunkCoordinates coordinates, ByteBuf buf)
    {
        buf.writeInt(coordinates.posX);
        buf.writeInt(coordinates.posY);
        buf.writeInt(coordinates.posZ);
    }

    public static class Handler implements IMessageHandler<MessageOpenSetReceiversGUI, IMessage>
    {
        @Override
        public IMessage onMessage(MessageOpenSetReceiversGUI message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                World world = player.worldObj;

                ChunkCoordinates coordinates = message.coordinates;
                TileEntity tile = world.getTileEntity(coordinates.posX, coordinates.posY, coordinates.posZ);

                if (tile instanceof IEnergyTransmitter && (!(tile instanceof ISidedInventory) || ((ISidedInventory) tile).isUseableByPlayer(player)))
                {
                    TransformersMod.proxy.openSetReceivers(world, player, tile, message.grandparents);
                }
            }

            return null;
        }
    }
}
