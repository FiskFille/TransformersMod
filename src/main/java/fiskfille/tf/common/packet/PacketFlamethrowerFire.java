package fiskfille.tf.common.packet;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;
import fiskfille.tf.common.entity.EntityMiniMissile;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketFlamethrowerFire implements IMessage
{
	public int id;

	public PacketFlamethrowerFire()
	{

	}

	public PacketFlamethrowerFire(EntityPlayer player)
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

    public static class Handler implements IMessageHandler<PacketFlamethrowerFire, IMessage>
    {
        public IMessage onMessage(PacketFlamethrowerFire message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity fromEntity = player.worldObj.getEntityByID(message.id);

                if (fromEntity instanceof EntityPlayer)
                {
                    EntityPlayer from = (EntityPlayer) fromEntity;
                    Transformer transformer = TFHelper.getTransformer(player);

                    if (transformer instanceof TransformerCloudtrap)
                    {
                    	
                    }
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                if (player != null)
                {
                    Transformer transformer = TFHelper.getTransformer(player);

                    if (transformer != null)
                    {
                        if (transformer instanceof TransformerCloudtrap)
                        {
                            boolean isCreative = player.capabilities.isCreativeMode;
                            boolean hasAmmo = isCreative || player.inventory.hasItem(TFItems.energonCrystalPiece);

                            if (hasAmmo)
                            {
                                TFPacketManager.networkWrapper.sendToAll(new PacketFlamethrowerFire(player));

                                World world = player.worldObj;
                                EntityFlamethrowerFire entity = new EntityFlamethrowerFire(world, player);
                                world.spawnEntityInWorld(entity);
                            }
                        }
                    }
                }
            }

            return null;
        }
    }
}