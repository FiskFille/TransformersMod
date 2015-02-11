package fiskfille.transformersmod.common.packet;

import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.common.entity.EntityMiniMissile;
import fiskfille.transformersmod.common.item.TFItems;
import fiskfille.transformersmod.common.packet.base.TFPacketManager;
import fiskfille.transformersmod.common.packet.base.TransformersPacket;
import fiskfille.transformersmod.common.playerdata.TFDataManager;
import fiskfille.transformersmod.common.transformer.TransformerVurp;
import fiskfille.transformersmod.common.transformer.base.Transformer;
import fiskfille.transformersmod.config.TFConfig;
import fiskfille.transformersmod.helper.TFHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class PacketVurpSniperShoot extends TransformersPacket
{
	public int id;

	public PacketVurpSniperShoot()
	{

	}

	public PacketVurpSniperShoot(EntityPlayer player)
	{
		this.id = player.getEntityId();
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(id);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		this.id = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		Entity fromEntity = player.worldObj.getEntityByID(id);

		if (fromEntity instanceof EntityPlayer)
		{
			EntityPlayer from = (EntityPlayer) fromEntity;

			Transformer transformer = TFHelper.getTransformer(player);

			if (transformer instanceof TransformerVurp)
			{
				String shootSound = TransformersMod.modid + ":missile.shoot";
				
				from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, shootSound, transformer.getShootVolume(), 1, false);
			}
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		if (player != null)
		{
			Transformer transformer = TFHelper.getTransformer(player);

			if (transformer != null)
			{
				if (transformer instanceof TransformerVurp)
				{
					boolean isCreative = player.capabilities.isCreativeMode;
					boolean hasAmmo = isCreative || player.inventory.hasItem(TFItems.miniMissile);

					if (hasAmmo)
					{
						TFPacketManager.packetPipeline.sendToAll(this);
						
						World world = player.worldObj;
						EntityMiniMissile entity = new EntityMiniMissile(world, player, 30, TFConfig.allowMissileExplosions);
						entity.setDamage(0.01D);
						--entity.posY;
						world.spawnEntityInWorld(entity);

						if (!isCreative)
						{
							player.inventory.consumeInventoryItem(TFItems.miniMissile);
						}
					}
				} 
			}
		}
	}
}
