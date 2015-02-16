package fiskfille.tf.common.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketBroadcastStealthState implements IMessage
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

    public static class Handler implements IMessageHandler<PacketBroadcastStealthState, IMessage>
    {
        public IMessage onMessage(PacketBroadcastStealthState message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity lookupEntity = player.worldObj.getEntityByID(message.id);

                if (lookupEntity instanceof EntityPlayer && player != lookupEntity)
                {
                    EntityPlayer lookupPlayer = (EntityPlayer) lookupEntity;

                    TFPlayerData playerData = TFPlayerData.getData(lookupPlayer);
                    playerData.stealthForce = message.stealth;
                    TFDataManager.setStealthModeTimer(lookupPlayer, message.stealth ? 0 : 5);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                TFPacketManager.networkWrapper.sendToDimension(new PacketBroadcastStealthState(player), player.dimension);
                TFPlayerData playerData = TFPlayerData.getData(player);
                playerData.stealthForce = message.stealth;
            }

            return null;
        }
    }
}
