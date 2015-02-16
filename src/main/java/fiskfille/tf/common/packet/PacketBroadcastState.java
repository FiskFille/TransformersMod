package fiskfille.tf.common.packet;

import fiskfille.tf.common.packet.base.AbstractPacket;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketBroadcastState extends AbstractPacket<PacketBroadcastState>
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

    public void handleClientSide(PacketBroadcastState message, EntityPlayer player)
    {
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

    public void handleServerSide(PacketBroadcastState message, EntityPlayer player)
    {
        TFPacketManager.networkWrapper.sendToDimension(this, player.dimension);
        TFPlayerData playerData = TFPlayerData.getData(player);
        playerData.vehicle = message.vehicle;
        playerData.stealthForce = message.stealth;
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
}