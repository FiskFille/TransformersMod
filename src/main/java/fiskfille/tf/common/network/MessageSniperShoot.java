package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityLaser;
import fiskfille.tf.common.item.ItemVurpsSniper;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.TransformerVurp;
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

public class MessageSniperShoot implements IMessage
{
    public int id;
    public boolean consume;
    
    public MessageSniperShoot()
    {
        
    }
    
    public MessageSniperShoot(EntityPlayer player, boolean consume)
    {
        this.id = player.getEntityId();
        this.consume = consume;
    }
    
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        consume = buf.readBoolean();
    }
    
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(consume);
    }
    
    public static class Handler implements IMessageHandler<MessageSniperShoot, IMessage>
    {
        public IMessage onMessage(MessageSniperShoot message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                //				EntityPlayer player = TransformersMod.proxy.getPlayer(); TODO-TF laser sound
                //				Entity fromEntity = player.worldObj.getEntityByID(message.id);
                //
                //				if (fromEntity instanceof EntityPlayer)
                //				{
                //					EntityPlayer from = (EntityPlayer) fromEntity;
                //
                //					Transformer transformer = TFHelper.getTransformer(from);
                //
                //					if (transformer != null)
                //					{
                //						String shootSound = transformer.getShootSound();
                //
                //						if (shootSound != null)
                //						{
                //							from.worldObj.playSound(from.posX, from.posY - (double) from.yOffset, from.posZ, shootSound, transformer.getShootVolume(), 1, false);
                //						}
                //					}
                //				}
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
                    
                    if (transformer instanceof TransformerVurp && from.getHeldItem() != null && from.getHeldItem().getItem() instanceof ItemVurpsSniper)
                    {
                        if (!TFDataManager.isInVehicleMode(from))
                        {
                            Item shootItem = TFItems.energonCrystalPiece;
                            boolean isCreative = from.capabilities.isCreativeMode;
                            boolean consumeItems = isCreative || from.inventory.hasItem(shootItem) && message.consume;
                            
                            World world = from.worldObj;
                            
                            Entity entity = new EntityLaser(world, from);
                            entity.posY--;
                            
                            world.spawnEntityInWorld(entity);
                            
                            if (consumeItems)
                            {
                                if (!isCreative && message.consume)
                                    from.inventory.consumeInventoryItem(shootItem);
                            }
                        }
                    }
                }
            }
            
            return null;
        }
    }
}
