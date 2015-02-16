package fiskfille.tf.common.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PacketHandleStealthTransformation implements IMessage
{
	public int id;
	private boolean transformed;

	public PacketHandleStealthTransformation()
	{

	}

	public PacketHandleStealthTransformation(EntityPlayer player, boolean mode)
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

    public static class Handler implements IMessageHandler<PacketHandleStealthTransformation, IMessage>
    {
        public IMessage onMessage(PacketHandleStealthTransformation message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                EntityPlayer from = null;
                Entity entity = player.worldObj.getEntityByID(message.id);

                if (entity instanceof EntityPlayer) from = (EntityPlayer) entity;

                if (from != null && from != TransformersMod.proxy.getPlayer())
                {
                    TFPlayerData playerData = TFPlayerData.getData(from);
                    TFDataManager.setStealthModeTimer(from, message.transformed ? 5 : 0);

                    String suffix = message.transformed ? "vehicle" : "robot";
                    from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, TransformersMod.modid + ":transform_" + suffix, 1, 1.5f, false);
                    playerData.stealthForce = message.transformed;
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                EntityPlayer from = null;

                for (World world : MinecraftServer.getServer().worldServers)
                {
                    Entity entity = world.getEntityByID(message.id);

                    if (entity instanceof EntityPlayer)
                    {
                        from = (EntityPlayer) entity;
                        break;
                    }
                }

                if (from != null) TFDataManager.setInStealthMode(player, message.transformed);
            }

            return null;
        }
    }
}