package fiskfille.tf.common.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityMiniMissile;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.packet.base.AbstractPacket;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class PacketVurpSniperShoot extends AbstractPacket<PacketVurpSniperShoot>
{
	public int id;

	public PacketVurpSniperShoot()
	{

	}

	public PacketVurpSniperShoot(EntityPlayer player)
	{
		id = player.getEntityId();
	}

	public void handleClientSide(PacketVurpSniperShoot message, EntityPlayer player)
    {
        Entity fromEntity = player.worldObj.getEntityByID(message.id);

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

    public void handleServerSide(PacketVurpSniperShoot message, EntityPlayer player)
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
                        TFPacketManager.networkWrapper.sendToAll(this);

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

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
    }
}
