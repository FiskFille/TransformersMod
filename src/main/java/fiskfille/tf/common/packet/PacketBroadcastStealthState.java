package fiskfille.tf.common.packet;

import fiskfille.tf.common.packet.base.AbstractPacket;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketBroadcastStealthState extends AbstractPacket<PacketBroadcastStealthState>
{
	private int id;
	private boolean stealth;
	
	public PacketBroadcastStealthState()
	{
		
	}
	
	public PacketBroadcastStealthState(EntityPlayer player)
	{
		id = player.getEntityId();
		stealth = TFDataManager.isInStealthMode(player);
	}

    public void handleClientSide(PacketBroadcastStealthState message, EntityPlayer player)
    {
        Entity lookupEntity = player.worldObj.getEntityByID(message.id);

        if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
        {
            EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

            TFPlayerData playerData = TFPlayerData.getData(lookupPlayer);
            playerData.stealthForce = message.stealth;
            TFDataManager.setStealthModeTimer(lookupPlayer, message.stealth ? 0 : 5);
        }
    }

    public void handleServerSide(PacketBroadcastStealthState message, EntityPlayer player)
    {
        TFPacketManager.networkWrapper.sendToDimension(this, player.dimension);
        TFPlayerData playerData = TFPlayerData.getData(player);
        playerData.stealthForce = message.stealth;
    }

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        stealth = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(stealth);
    }
}
