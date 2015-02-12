package fiskfille.tf.common.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.packet.base.TransformersPacket;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class PacketTransformersAction extends TransformersPacket
{
	public int id;
	private PlayerInteractEvent.Action action;

	public PacketTransformersAction()
	{

	}

	public PacketTransformersAction(EntityPlayer player, PlayerInteractEvent.Action action)
	{
		this.id = player.getEntityId();
		this.action = action;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(id);
		int index = -1;

		int cID = 0;
		for (Action cAction : PlayerInteractEvent.Action.values()) 
		{
			if (cAction == action)
			{
				index = cID;
				break;
			}
			cID++;
		}

		buffer.writeInt(index);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		this.id = buffer.readInt();
		this.action = PlayerInteractEvent.Action.values()[buffer.readInt()];
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		Entity fromEntity = player.worldObj.getEntityByID(id);
		
		if (fromEntity instanceof EntityPlayer)
		{
			EntityPlayer from = (EntityPlayer) fromEntity;

			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
			{
				Transformer transformer = TFHelper.getTransformer(player);
				
				if (transformer != null)
				{
					String shootSound = transformer.getShootSound();
					
					if (shootSound != null && TFDataManager.isInVehicleMode(from))
					{
						from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, shootSound, transformer.getShootVolume(), 1, false);
//						from.rotationPitch -= 2;
					} 
				}
			}
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		EntityPlayer from = null;

		for (World world : MinecraftServer.getServer().worldServers)
		{
			Entity entity = world.getEntityByID(id);

			if (entity instanceof EntityPlayer)
			{
				from = (EntityPlayer) entity;
				break;
			}
		}

		if (from != null)
		{
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
			{
				Transformer transformer = TFHelper.getTransformer(player);
				
				if (transformer != null)
				{
					if (transformer.canShoot(player) && TFDataManager.isInVehicleMode(from))
					{
						Item shootItem = transformer.getShootItem();
						
						boolean isCreative = from.capabilities.isCreativeMode;
						boolean hasAmmo = isCreative || from.inventory.hasItem(shootItem);
						
						if (hasAmmo)
						{
							World world = from.worldObj;
							
							TFPacketManager.packetPipeline.sendToAll(this);
							
							Entity entity = transformer.getShootEntity(player);
							entity.posY--;
							world.spawnEntityInWorld(entity);

							if (!isCreative)
							{
								player.inventory.consumeInventoryItem(shootItem);
							}
						}
					} 
				}
			}
		}
	}
}
