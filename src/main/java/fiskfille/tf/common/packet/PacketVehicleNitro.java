package fiskfille.tf.common.packet;

import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.common.packet.base.AbstractPacket;
import fiskfille.tf.common.packet.base.TFPacketManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketVehicleNitro extends AbstractPacket<PacketVehicleNitro>
{
	private int id;
	private boolean nitroOn;
	
	public PacketVehicleNitro()
	{
		
	}
	
	public PacketVehicleNitro(EntityPlayer player, boolean n)
	{
		id = player.getEntityId();
		nitroOn = n;
	}

    public void handleClientSide(PacketVehicleNitro message, EntityPlayer player)
    {
        Entity entity = player.worldObj.getEntityByID(message.id);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer fromPlayer = (EntityPlayer) entity;
            if (fromPlayer != player) NitroParticleHandler.setNitro(fromPlayer, message.nitroOn);
        }
    }

    public void handleServerSide(PacketVehicleNitro message, EntityPlayer player)
    {
        TFPacketManager.networkWrapper.sendToDimension(this, player.dimension);
    }

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        nitroOn = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(nitroOn);
    }
}
