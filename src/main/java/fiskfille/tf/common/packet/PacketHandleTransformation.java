package fiskfille.tf.common.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.event.PlayerTransformEvent;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketHandleTransformation implements IMessage
{
	public int id;
	private boolean transformed;

	public PacketHandleTransformation()
	{

	}

	public PacketHandleTransformation(EntityPlayer player, boolean mode)
	{
		id = player.getEntityId();
		transformed = mode;
	}

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        transformed = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(transformed);
    }

    public static class Handler implements IMessageHandler<PacketHandleTransformation, IMessage>
    {
        public IMessage onMessage(PacketHandleTransformation message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                EntityPlayer from = null;
                Entity entity = player.worldObj.getEntityByID(message.id);

                if (entity instanceof EntityPlayer) from = (EntityPlayer) entity;

                if (from != null && from != Minecraft.getMinecraft().thePlayer)
                {
                    TFPlayerData playerData = TFPlayerData.getData(from);

                    MinecraftForge.EVENT_BUS.post(new PlayerTransformEvent(player, message.transformed, playerData.stealthForce));
                    playerData.stealthForce = false;
                    TFDataManager.setTransformationTimer(from, message.transformed ? 20 : 0);

                    String suffix = message.transformed ? "vehicle" : "robot";
                    from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, TransformersMod.modid + ":transform_" + suffix, 1, 1, false);
                    playerData.vehicle = message.transformed;
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                if (player != null)
                {
                    TFDataManager.setInVehicleMode(player, message.transformed);
                    TFPacketManager.networkWrapper.sendToDimension(new PacketBroadcastState(player), player.dimension);
                }
            }

            return null;
        }
    }
}