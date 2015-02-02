package fiskfille.tf.packet;

import fiskfille.tf.TFHelper;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.achievement.TFAchievements;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.entity.EntityMissile;
import fiskfille.tf.entity.EntityTankShell;
import fiskfille.tf.item.TFItems;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
			if(cAction == action)
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
		if(fromEntity instanceof EntityPlayer)
		{
			EntityPlayer from = (EntityPlayer) fromEntity;

			if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
			{
				if(TFHelper.isPlayerTank(from) && TFDataManager.isInVehicleMode(from))
				{
					from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, TransformersMod.modid + ":tankfire", 1, 1, false);
					from.rotationPitch -= 2;
				} 
			}
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		TransformersMod.packetPipeline.sendToAll(this);
		
		EntityPlayer from = null;

		for (World world : MinecraftServer.getServer().worldServers)
		{
			Entity entity = world.getEntityByID(id);

			if(entity instanceof EntityPlayer)
			{
				from = (EntityPlayer) entity;
				break;
			}
		}

		if(from != null)
		{
			if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
			{
				if(TFHelper.isPlayerTank(from) && TFDataManager.isInVehicleMode(from))
				{
					boolean isCreative = from.capabilities.isCreativeMode;
					boolean hasShell = isCreative || from.inventory.hasItem(TFItems.tankShell);
					
					if(hasShell)
					{
						World world = from.worldObj;
						EntityTankShell shell = new EntityTankShell(world, from, 3);
						shell.posY--;
						world.spawnEntityInWorld(shell);

						if(!isCreative)
						{
							player.inventory.consumeInventoryItem(TFItems.tankShell);
						}
					}
				} 
				else 
				{
					boolean stealthMode = TFDataManager.isInStealthMode(player);
					if((TFHelper.isPlayerJet(from) || stealthMode) && TFDataManager.isInVehicleMode(from))
					{
						boolean isCreative = from.capabilities.isCreativeMode;
						boolean hasMissile = isCreative || from.inventory.hasItem(TFItems.missile);

						if(hasMissile)
						{
							World world = from.worldObj;
							EntityMissile missile = new EntityMissile(world, from, 3, TFConfig.allowMissileExplosions, stealthMode);

							if(stealthMode)
							{
								missile.posY-=2;
							}
							else
							{
								missile.posY--;
							}

							world.spawnEntityInWorld(missile);

							player.addStat(TFAchievements.firstMissile, 1);

							if(!isCreative)
							{
								player.inventory.consumeInventoryItem(TFItems.missile);
							}
						}
					}
				}
			}
		}
	}
}
