package fiskfille.tf.common.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiOverlay;
import fiskfille.tf.client.tick.TickHandler;
import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.MessageBroadcastState;
import fiskfille.tf.common.network.MessageSendFlying;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.donator.Donators;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.update.Update;

public class CommonEventHandler
{
    public static double prevMove;
    
    private List<EntityPlayer> playersNotSunc = new ArrayList<EntityPlayer>();
    
    private boolean displayedUpdates;
    
    private long lastTime;
    private double lastX;
    private double lastY;
    private double lastZ;
    
    private Map<EntityPlayer, Boolean> prevFlying = new HashMap<EntityPlayer, Boolean>();
    
    @SubscribeEvent
    public void onTransform(PlayerTransformEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        
        if(TFConfig.canTransform(event.transformer))
        {
            if (!event.transformed)
            {
                IAttributeInstance entityAttribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
                
                Transformer transformer = TFHelper.getTransformer(player);
                
                if (prevMove != 0)
                {
                    entityAttribute.setBaseValue(prevMove);
                }
            }
        }
        else
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onHit(LivingAttackEvent event)
    {
        EntityLivingBase entityLiving = event.entityLiving;
        Entity cause = event.source.getEntity();
        
        if (cause instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) cause;
            
            if (TFDataManager.isInVehicleMode(player) && !event.source.isProjectile())
            {
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void onSmelt(ItemSmeltedEvent event)
    {
        if (event.smelting.getItem() == TFItems.transformium)
        {
            event.player.addStat(TFAchievements.transformium, 1);
        }
    }
    
    @SubscribeEvent
    public void onEntityLoad(EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            event.entity.registerExtendedProperties(TFPlayerData.IDENTIFIER, new TFPlayerData());
        }
    }
    
    @SubscribeEvent
    public void onPlayerBreakBlock(BlockEvent.BreakEvent event)
    {
        if (TFDataManager.isInVehicleMode(event.getPlayer()))
        {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void startTracking(StartTracking event)
    {
        EntityPlayer player = event.entityPlayer;
        
        if (player != null)
        {
            if (!player.worldObj.isRemote)
            {
                if (event.target instanceof EntityPlayer)
                {
                    EntityPlayer beingTracked = (EntityPlayer) event.target;
                    
                    EntityPlayerMP playerMP = (EntityPlayerMP) player;
                    EntityPlayerMP beingTrackedMP = (EntityPlayerMP) beingTracked;
                    
                    TFNetworkManager.networkWrapper.sendTo(new MessageBroadcastState(player), beingTrackedMP);
                    TFNetworkManager.networkWrapper.sendTo(new MessageBroadcastState(beingTracked), playerMP);
                    
                    TFNetworkManager.networkWrapper.sendTo(new MessageSendFlying(beingTracked, beingTracked.capabilities.isFlying), playerMP);
                    TFNetworkManager.networkWrapper.sendTo(new MessageSendFlying(player, player.capabilities.isFlying), beingTrackedMP);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent event)
    {
        if (TFDataManager.isInVehicleMode(event.entityPlayer))
        {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void formatName(NameFormat event)
    {
        if (Donators.isDonator(event.entityPlayer))
        {
            event.displayname = EnumChatFormatting.BOLD + "" + EnumChatFormatting.GOLD + "[Donator] " + event.displayname;
        }
    }
    
    @SubscribeEvent
    public void onSpawn(EntityJoinWorldEvent event)
    {
        Entity entity = event.entity;
        World world = entity.worldObj;
        
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            player.addStat(TFAchievements.transformersMod, 1);
            
            if (!world.isRemote)
            {
                Donators.loadDonators();
                playersNotSunc.add(player);
            }
            else
            {
                boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
                
                TFDataManager.setTransformationTimer(player, inVehicleMode ? 0 : 20);
                TFDataManager.setStealthModeTimer(player, TFDataManager.isInStealthMode(player) ? 0 : 5);
                
                if (!inVehicleMode)
                {
                    TickHandler.prevViewBobbing = Minecraft.getMinecraft().gameSettings.viewBobbing;
                }
                
                if (!displayedUpdates && TFConfig.checkForUpdates)
                {
                    Update update = TransformersMod.latestUpdate;
                    
                    if (update.isAvailable())
                    {
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Version " + update.getVersion() + " is now available!"));
                        player.addChatMessage(new ChatComponentText(""));
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + "What's New: "));
                        
                        String[] updates = update.getUpdateLog().split(Pattern.quote("(newline)"));
                        
                        for (String updatePart : updates)
                        {
                            EnumChatFormatting colour = EnumChatFormatting.RED;
                            
                            if (updatePart.trim().startsWith("*"))
                            {
                                colour = EnumChatFormatting.GOLD;
                            }
                            else if (updatePart.trim().startsWith("+"))
                            {
                                colour = EnumChatFormatting.GREEN;
                            }
                            
                            player.addChatMessage(new ChatComponentText(colour + updatePart));
                        }
                        
                        player.addChatMessage(new ChatComponentText(""));
                    }
                    
                    if (Donators.isDonator(player))
                    {
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + StatCollector.translateToLocal("misc.donate.thanks") + Donators.getDonationAmount(player).toString().replaceAll(Pattern.quote("$"), "") + "!"));
                        player.addStat(TFAchievements.donate, 1);
                    }
                    
                    displayedUpdates = true;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            
            Transformer transformer = TFHelper.getTransformer(player);
            
            if (transformer != null)
            {
                transformer.onJump(player);
                
                if (!transformer.canJumpAsVehicle(player) && TFDataManager.isInVehicleMode(player) && TFDataManager.getTransformationTimer(player) < 10)
                {
                    player.motionY = 0D;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            
            Transformer transformer = TFHelper.getTransformer(player);
            
            float yOffset = transformer != null ? transformer.getCameraYOffset(player) : 0;
            
            boolean vehicleMode = TFDataManager.isInVehicleMode(player);
            
            if (transformer != null)
            {
                transformer.tick(player, TFDataManager.getTransformationTimer(player));
            }
            
            if (player.worldObj.isRemote)
            {
                if (player == Minecraft.getMinecraft().thePlayer)
                {
                    if (!vehicleMode)
                    {
                        float defaultEyeHeight = player.getDefaultEyeHeight();
                        
                        if (player.eyeHeight != defaultEyeHeight)
                        {
                            player.eyeHeight = defaultEyeHeight;
                        }
                    }
                    else
                    {
                        player.eyeHeight = yOffset + 0.22F;
                    }
                }
            }
            else
            {
                if (player.capabilities != null)
                {
                    Boolean isFlying = prevFlying.get(player);
                    
                    boolean capabilitiesFlying = player.capabilities.isFlying;
                    
                    if (isFlying != null)
                    {
                        if (isFlying != capabilitiesFlying)
                        {
                            TFNetworkManager.networkWrapper.sendToDimension(new MessageSendFlying(player, capabilitiesFlying), player.dimension);
                            
                            prevFlying.put(player, capabilitiesFlying);
                        }
                    }
                    else
                    {
                        TFNetworkManager.networkWrapper.sendToDimension(new MessageSendFlying(player, capabilitiesFlying), player.dimension);
                        prevFlying.put(player, capabilitiesFlying);
                    }
                }
            }
            
            // TODO: Re-implement player resizing for version 0.6
            //			try 
            //			{
            //				if (vehicleMode && yOffset != 0)
            //				{
            //					TransformersMod.setSizeMethod.invoke(player, 0.6F, -yOffset - 0.6F);
            //				}
            //				else
            //				{
            //					TransformersMod.setSizeMethod.invoke(player, 0.6F, 1.8F);
            //				}
            //			} 
            //			catch (IllegalAccessException e)
            //			{
            //				e.printStackTrace();
            //			} 
            //			catch (IllegalArgumentException e) 
            //			{
            //				e.printStackTrace();
            //			} 
            //			catch (InvocationTargetException e)
            //			{
            //				e.printStackTrace();
            //			}
            
            if (!player.worldObj.isRemote)
            {
                if (playersNotSunc.size() > 0 && playersNotSunc.contains(player))
                {
                    TFDataManager.updatePlayerWithServerInfo(player);
                    playersNotSunc.remove(player);
                }
            }
            else
            {
                long time = System.currentTimeMillis();
                
                long timeDiff = time - lastTime;
                
                if (timeDiff >= 500)
                {
                    double diffX = (player.posX - lastX);
                    double diffY = (player.posY - lastY);
                    double diffZ = (player.posZ - lastZ);
                    
                    GuiOverlay.speed = (double) (Math.sqrt((diffX * diffX) + (diffY * diffY) + (diffZ * diffZ)) * ((((double) 60) * 60) * 2) / 1000);
                    
                    lastX = player.posX;
                    lastY = player.posY;
                    lastZ = player.posZ;
                    
                    lastTime = time;
                }
            }
            
            if (!(TFDataManager.getTransformationTimer(player) <= 20))
            {
                lastX = player.posX;
                lastY = player.posY;
                lastZ = player.posZ;
            }
        }
    }
    
    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            
            Transformer transformer = TFHelper.getTransformer(player);
            
            if (transformer != null)
            {
                float newDist = transformer.fall(player, event.distance);
                
                if (newDist <= 0)
                {
                    event.setCanceled(true);
                }
                else
                {
                    event.distance = newDist;
                }
            }
        }
    }
}