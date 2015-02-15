package fiskfille.tf.common.packet;

import fiskfille.tf.common.packet.base.AbstractPacket;
import fiskfille.tf.common.packet.base.TFPacketManager;
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

public class PacketTransformersAction extends AbstractPacket<PacketTransformersAction>
{
	public int id;
	private PlayerInteractEvent.Action action;

	public PacketTransformersAction()
	{

	}

	public PacketTransformersAction(EntityPlayer player, PlayerInteractEvent.Action a)
	{
		id = player.getEntityId();
		action = a;
	}

    public void handleClientSide(PacketTransformersAction message, EntityPlayer player)
    {
        Entity fromEntity = player.worldObj.getEntityByID(message.id);

        if (fromEntity instanceof EntityPlayer)
        {
            EntityPlayer from = (EntityPlayer) fromEntity;

            if (message.action == Action.RIGHT_CLICK_AIR || message.action == Action.RIGHT_CLICK_BLOCK)
            {
                Transformer transformer = TFHelper.getTransformer(player);

                if (transformer != null)
                {
                    String shootSound = transformer.getShootSound();
                    if (shootSound != null && TFDataManager.isInVehicleMode(from)) from.worldObj.playSound(from.posX, from.posY - (double)from.yOffset, from.posZ, shootSound, transformer.getShootVolume(), 1, false);
                }
            }
        }
    }

    public void handleServerSide(PacketTransformersAction message, EntityPlayer player)
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
            if (message.action == Action.RIGHT_CLICK_AIR || message.action == Action.RIGHT_CLICK_BLOCK)
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
                            TFPacketManager.networkWrapper.sendToAll(this);

                            Entity entity = transformer.getShootEntity(player);
                            entity.posY--;
                            world.spawnEntityInWorld(entity);

                            if (!isCreative) player.inventory.consumeInventoryItem(shootItem);
                        }
                    }
                }
            }
        }
    }

    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();

        int index  = buf.readInt();
        if (index == -1) index = 0;
        action = PlayerInteractEvent.Action.values()[index];
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
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

        buf.writeInt(index);
    }
}
