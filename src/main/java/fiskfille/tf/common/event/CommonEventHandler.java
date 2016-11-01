package fiskfille.tf.common.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.event.world.BlockEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.data.TFEntityData;
import fiskfille.tf.common.data.TFPlayerData;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.TFSubItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.network.MessageBroadcastState;
import fiskfille.tf.common.network.MessageSendFlying;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.recipe.AssemblyTableCraftingManager;
import fiskfille.tf.common.recipe.TFRecipes;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.web.update.Update;

public class CommonEventHandler
{
    private List<EntityPlayer> playersNotSunc = new ArrayList<EntityPlayer>();

    private boolean displayedUpdates;

    private Map<EntityPlayer, Boolean> prevFlying = new HashMap<EntityPlayer, Boolean>();

    @SubscribeEvent
    public void onTransform(PlayerTransformEvent event)
    {
        EntityPlayer player = event.entityPlayer;

        Transformer transformer = event.transformer;

        if (transformer != null && event.altMode == -1)
        {
            TFMotionManager.getTransformerPlayer(player).setLandingTimer(0);
        }

        if (transformer == null || transformer.canTransform(player))
        {
            //            if (!event.transformed)
            //            {
            //                IAttributeInstance entityAttribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            //                
            //                entityAttribute.setBaseValue(1);
            //            }
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
            Transformer transformer = TFHelper.getTransformer(player);

            int altMode = TFDataManager.getAltMode(player);

            if (TFDataManager.isTransformed(player) && !event.source.isProjectile() && (transformer == null || transformer.canInteractInVehicleMode(player, altMode)))
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
    public void onCraft(ItemCraftedEvent event)
    {
        if (TFSubItems.matches(event.crafting, TFSubItems.tank_track))
        {
            event.player.addStat(TFAchievements.tracks, 1);
        }
    }

    @SubscribeEvent
    public void onEntityLoad(EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            event.entity.registerExtendedProperties(TFPlayerData.IDENTIFIER, new TFPlayerData());
        }

        event.entity.registerExtendedProperties(TFEntityData.IDENTIFIER, new TFEntityData());
    }

    @SubscribeEvent
    public void onPlayerBreakBlock(BlockEvent.BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFDataManager.getAltMode(player);

        if (TFDataManager.isTransformed(player) && (transformer == null || transformer.canInteractInVehicleMode(player, altMode)))
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
        EntityPlayer player = event.entityPlayer;
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFDataManager.getAltMode(player);

        if (TFDataManager.isTransformed(player) && (transformer == null || transformer.canInteractInVehicleMode(player, altMode)))
        {
            event.setCanceled(true);
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
                playersNotSunc.add(player);
            }
            else
            {
                boolean inVehicleMode = TFDataManager.isTransformed(player);

                if (!inVehicleMode && TransformersMod.proxy.getPlayer() == player) //Should also move to ClientEventHandler
                {
                    ClientEventHandler.prevViewBobbing = Minecraft.getMinecraft().gameSettings.viewBobbing;
                }

                if (!displayedUpdates && TFConfig.checkForUpdates)
                {
                    Update update = TransformersMod.latestUpdate;

                    if (update != null && update.isAvailable())
                    {
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "TransformersMod version " + update.getVersion() + " is now available!"));
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
                            else if (updatePart.trim().startsWith("-"))
                            {
                                colour = EnumChatFormatting.RED;
                            }

                            player.addChatMessage(new ChatComponentText(colour + updatePart));
                        }

                        player.addChatMessage(new ChatComponentText(""));
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
                int altMode = TFDataManager.getAltMode(player);

                if (!transformer.onJump(player) || (!transformer.canJumpAsVehicle(player, altMode) && TFDataManager.isTransformed(player) && TFDataManager.getTransformationTimer(player) < 10))
                {
                    player.motionY = 0;
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event)
    {
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent event)
    {
        TFEntityData.getData(event.entity).onUpdate();
        
        if (Keyboard.isKeyDown(Keyboard.KEY_I)) // TODO: Remove
        {
        	AssemblyTableCraftingManager.getInstance().getRecipeList().clear();
        	CraftingManager.getInstance().getRecipeList().clear();
        	TFRecipes.registerRecipes();
        }

        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            Transformer transformer = TFHelper.getTransformer(player);

            float yOffset = transformer != null ? transformer.getCameraYOffset(player, TFDataManager.getAltMode(player)) : 0;
            boolean vehicleMode = TFDataManager.isTransformed(player);

            TFDataManager.setPrevTransformationTimer(player, TFDataManager.getTransformationTimer(player));
            TFDataManager.setPrevStealthModeTimer(player, TFDataManager.getStealthModeTimer(player));

            if (transformer != null)
            {
                transformer.tick(player, TFDataManager.getTransformationTimer(player));
            }

            TFPlayerData.getData(player).onUpdate();

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

            // TODO-TF: Re-implement player resizing for version 0.6
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
                float newDist = transformer.fall(player, event.distance, TFDataManager.getAltMode(player));

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