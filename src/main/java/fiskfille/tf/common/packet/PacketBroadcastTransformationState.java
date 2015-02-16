package fiskfille.tf.common.packet;

import fiskfille.tf.common.packet.base.AbstractPacket;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketBroadcastTransformationState extends AbstractPacket<PacketBroadcastTransformationState>
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

    public void handleClientSide(PacketBroadcastTransformationState message, EntityPlayer player)
    {
        Entity lookupEntity = player.worldObj.getEntityByID(message.id);

        if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
        {
            EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

            TFPlayerData playerData = TFPlayerData.getData(lookupPlayer);
            playerData.vehicle = message.vehicle;
            TFDataManager.setTransformationTimer(lookupPlayer, message.vehicle ? 0 : 10);
        }
    }

    public void handleServerSide(PacketBroadcastTransformationState message, EntityPlayer player)
    {
        TFPacketManager.networkWrapper.sendToDimension(this, player.dimension);
        TFPlayerData playerData = TFPlayerData.getData(player);
        playerData.vehicle = message.vehicle;
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
}
