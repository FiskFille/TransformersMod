package fiskfille.tf.common.network;

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
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTransformersAction implements IMessage
{
    public int id;
    private PlayerInteractEvent.Action action;
    
    public MessageTransformersAction()
    {
        
    }
    
    public MessageTransformersAction(EntityPlayer player, PlayerInteractEvent.Action a)
    {
        id = player.getEntityId();
        action = a;
    }
    
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        
        int index = buf.readInt();
        if (index == -1)
            index = 0;
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
    
    public static class Handler implements IMessageHandler<MessageTransformersAction, IMessage>
    {
        public IMessage onMessage(MessageTransformersAction message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity fromEntity = player.worldObj.getEntityByID(message.id);
                
                if (fromEntity instanceof EntityPlayer)
                {
                    EntityPlayer from = (EntityPlayer) fromEntity;
                    
                    if (message.action == Action.RIGHT_CLICK_AIR || message.action == Action.RIGHT_CLICK_BLOCK)
                    {
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
                                    TFNetworkManager.networkWrapper.sendToAll(new MessageTransformersAction(player, message.action));
                                    
                                    Entity entity = transformer.getShootEntity(player);
                                    entity.posY--;
                                    world.spawnEntityInWorld(entity);
                                    
                                    if (!isCreative)
                                        player.inventory.consumeInventoryItem(shootItem);
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
