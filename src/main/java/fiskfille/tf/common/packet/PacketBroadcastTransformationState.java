package fiskfille.tf.common.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketBroadcastTransformationState implements IMessage
{
	private int id;
	private boolean vehicle;
	
	public PacketBroadcastTransformationState()
	{
		
	}
	
	public PacketBroadcastTransformationState(EntityPlayer player)
	{
		id = player.getEntityId();
		vehicle = TFDataManager.isInVehicleMode(player);
	}

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        vehicle = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(vehicle);
    }

    public static class Handler implements IMessageHandler<PacketBroadcastTransformationState, IMessage>
    {
        public IMessage onMessage(PacketBroadcastTransformationState message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                Entity lookupEntity = player.worldObj.getEntityByID(message.id);

                if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
                {
                    EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

                    TFPlayerData playerData = TFPlayerData.getData(lookupPlayer);
                    playerData.vehicle = message.vehicle;
                    TFDataManager.setTransformationTimer(lookupPlayer, message.vehicle ? 0 : 10);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                TFPacketManager.networkWrapper.sendToDimension(new PacketBroadcastTransformationState(player), player.dimension);
                TFPlayerData playerData = TFPlayerData.getData(player);
                playerData.vehicle = message.vehicle;
            }

            return null;
        }
    }
}
