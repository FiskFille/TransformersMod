package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageOpenGuiSimple implements IMessage
{
	public int id;
	private int modGuiId;
	private int x;
	private int y;
	private int z;

	public MessageOpenGuiSimple()
	{

	}

	public MessageOpenGuiSimple(EntityPlayer player, int modGuiId, int x, int y, int z)
	{
		this.id = player.getEntityId();
		this.modGuiId = modGuiId;
		this.x = x;
		this.y = y;
		this.z = z;
	}

    @Override
    public void fromBytes(ByteBuf buf)
    {
		id = buf.readInt();
		modGuiId = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

    @Override
    public void toBytes(ByteBuf buf)
    {
		buf.writeInt(id);
		buf.writeInt(modGuiId);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	public static class Handler implements IMessageHandler<MessageOpenGuiSimple, IMessage>
	{
        @Override
        public IMessage onMessage(MessageOpenGuiSimple message, MessageContext ctx)
        {
			if (ctx.side.isClient())
			{
				EntityPlayer player = TransformersMod.proxy.getPlayer();
				Entity entity = player.worldObj.getEntityByID(message.id);

				if (entity instanceof EntityPlayer)
				{
					((EntityPlayer)entity).openGui(TransformersMod.instance, message.modGuiId, ((EntityPlayer)entity).worldObj, message.x, message.y, message.z);
				}
			}
			else
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;

				if (player != null)
				{
					Entity entity = player.worldObj.getEntityByID(message.id);

					if (entity instanceof EntityPlayer)
					{
						((EntityPlayer)entity).openGui(TransformersMod.instance, message.modGuiId, ((EntityPlayer)entity).worldObj, message.x, message.y, message.z);
					}
				}
			}

			return null;
		}
	}
}
