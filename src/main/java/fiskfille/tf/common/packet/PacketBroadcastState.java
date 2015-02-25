package fiskfille.tf.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;

public class PacketBroadcastState implements IMessage
{
	private int id;
	private boolean stealth;
	private boolean vehicle;
	
	public PacketBroadcastState()
	{
		
	}
	
	public PacketBroadcastState(EntityPlayer player)
	{
		id = player.getEntityId();
		stealth = TFDataManager.isInStealthMode(player);
		vehicle = TFDataManager.isInVehicleMode(player);
	}

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        stealth = buf.readBoolean();
        vehicle = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(stealth);
        buf.writeBoolean(vehicle);
    }

    public static class Handler implements IMessageHandler<PacketBroadcastState, IMessage>
    {
        public IMessage onMessage(PacketBroadcastState message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity lookupEntity = player.worldObj.getEntityByID(message.id);

                if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
                {
                    EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

                    TFPlayerData playerData = TFPlayerData.getData(lookupPlayer);
                    playerData.vehicle = message.vehicle;
                    TFDataManager.setTransformationTimer(lookupPlayer, message.vehicle ? 0 : 10);
                    playerData.stealthForce = message.stealth;
                    TFDataManager.setStealthModeTimer(lookupPlayer, message.stealth ? 0 : 5);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                TFPacketManager.networkWrapper.sendToDimension(new PacketBroadcastState(player), player.dimension);
                TFPlayerData playerData = TFPlayerData.getData(player);
                playerData.vehicle = message.vehicle;
                playerData.stealthForce = message.stealth;
            }

            return null;
        }
    }
}
