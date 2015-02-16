package fiskfille.tf.common.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.common.packet.base.TFPacketManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketVehicleNitro implements IMessage
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

    public static class Handler implements IMessageHandler<PacketVehicleNitro, IMessage>
    {
        public IMessage onMessage(PacketVehicleNitro message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity entity = player.worldObj.getEntityByID(message.id);

                if (entity instanceof EntityPlayer)
                {
                    EntityPlayer fromPlayer = (EntityPlayer) entity;
                    if (fromPlayer != player) NitroParticleHandler.setNitro(fromPlayer, message.nitroOn);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                TFPacketManager.networkWrapper.sendToDimension(new PacketVehicleNitro(player, message.nitroOn), player.dimension);
            }

            return null;
        }
    }
}
