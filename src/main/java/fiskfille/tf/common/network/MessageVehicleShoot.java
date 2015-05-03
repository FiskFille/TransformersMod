package fiskfille.tf.common.network;

import org.lwjgl.input.Mouse;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageVehicleShoot implements IMessage
{
	public int id;

	public MessageVehicleShoot()
	{

	}

	public MessageVehicleShoot(EntityPlayer player)
	{
		id = player.getEntityId();
	}

	public void fromBytes(ByteBuf buf)
	{
		id = buf.readInt();
	}

	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(id);
	}

	public static class Handler implements IMessageHandler<MessageVehicleShoot, IMessage>
	{
		public IMessage onMessage(MessageVehicleShoot message, MessageContext ctx)
		{
			if (ctx.side.isClient())
			{
				EntityPlayer player = TransformersMod.proxy.getPlayer();
				Entity fromEntity = player.worldObj.getEntityByID(message.id);

				if (fromEntity instanceof EntityPlayer)
				{
					EntityPlayer from = (EntityPlayer) fromEntity;

					Transformer transformer = TFHelper.getTransformer(from);

					if (transformer != null)
					{
						String shootSound = transformer.getShootSound();

						if (shootSound != null)
						{
							from.worldObj.playSound(from.posX, from.posY - (double) from.yOffset, from.posZ, shootSound, transformer.getShootVolume(), 1, false);
						}
					}
				}
			}
			else
			{
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

				if (from != null)
				{
					Transformer transformer = TFHelper.getTransformer(from);

					if (transformer != null)
					{
						if (transformer.canShoot(from) && TFDataManager.isInVehicleMode(from))
						{
							Item shootItem = transformer.getShootItem();
							boolean isCreative = from.capabilities.isCreativeMode;
							boolean hasAmmo = isCreative || from.inventory.hasItem(shootItem);

							if (hasAmmo)
							{
								World world = from.worldObj;

								if (transformer.getShootSound() != null)
								{
									TFNetworkManager.networkWrapper.sendToAllAround(new MessageVehicleShoot(from), new TargetPoint(from.dimension, from.posX, from.posY, from.posZ, 32));
								}
								
								Entity entity = transformer.getShootEntity(from);
								entity.posY--;
								world.spawnEntityInWorld(entity);
								
								if (!isCreative)
								{
									from.inventory.consumeInventoryItem(shootItem);
								}
							}
						}
					}
				}
			}

			return null;
		}
	}
}
