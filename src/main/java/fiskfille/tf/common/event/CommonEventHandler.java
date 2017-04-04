package fiskfille.tf.common.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.data.TFEntityData;
import fiskfille.tf.common.data.TFPlayerData;
import fiskfille.tf.common.data.TFWorldData;
import fiskfille.tf.common.item.ItemHandler;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.TFSubItems;
import fiskfille.tf.common.network.MessageBroadcastState;
import fiskfille.tf.common.network.MessageSendFlying;
import fiskfille.tf.common.network.base.TFNetworkManager;
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
    public void onHit(LivingAttackEvent event)
    {
        Entity cause = event.source.getEntity();

        if (cause instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) cause;
            Transformer transformer = TFHelper.getTransformer(player);

            int altMode = TFData.ALT_MODE.get(player);

            if (TFHelper.isFullyTransformed(player) && !event.source.isProjectile() && (transformer == null || transformer.canInteractInVehicleMode(player, altMode)))
            {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onSmelt(ItemSmeltedEvent event)
    {
        if (event.smelting.getItem() == TFItems.transformiumFragment)
        {
            event.player.addStat(TFAchievements.transformium, 1);
        }
    }

    @SubscribeEvent
    public void onCraft(ItemCraftedEvent event)
    {
        if (ItemHandler.matches(event.crafting, TFSubItems.tank_track))
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
    public void onBlockBreak(BlockEvent.BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFData.ALT_MODE.get(player);

        if (TFHelper.isFullyTransformed(player) && (transformer == null || transformer.canInteractInVehicleMode(player, altMode)))
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
    public void onClonePlayer(PlayerEvent.Clone event)
    {
        TFPlayerData.getData(event.entityPlayer).copy(TFPlayerData.getData(event.original));
    }

    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFData.ALT_MODE.get(player);

        if (TFHelper.isFullyTransformed(player) && (transformer == null || transformer.canInteractInVehicleMode(player, altMode)))
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
                TFHelper.isFullyTransformed(player);

//                if (!inVehicleMode && TransformersMod.proxy.getPlayer() == player) // TODO: Should also move to ClientEventHandler
//                {
//                    ClientEventHandler.prevViewBobbing = Minecraft.getMinecraft().gameSettings.viewBobbing;
//                }

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
                int altMode = TFData.ALT_MODE.get(player);

                if (!transformer.onJump(player) || !transformer.canJumpAsVehicle(player, altMode) && TFHelper.getTransformationTimer(player) >= 0.5F)
                {
                    player.motionY = 0;
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event)
    {
//        TFChunkManager.clearCache();
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        World world = event.world;

        if (!world.isRemote)
        {
            TFWorldData.load(world);
        }
        
        ItemHandler.load(world);
    }
    
    @SubscribeEvent
    public void onItemStitchPost(ItemStitchEvent.Post event)
    {
        TFRecipes.register();
        TFAchievements.register();
    }
    
    @SubscribeEvent
    public void onItemHandlerInit(ItemHandlerEvent.Init event)
    {
        event.registerItemHandler(TransformersMod.modid, TFSubItems.class);
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent event)
    {
        TFEntityData.getData(event.entity).onUpdate();

        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;

            if (!player.worldObj.isRemote)
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
                float newDist = transformer.fall(player, event.distance, TFData.ALT_MODE.get(player));

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

    @SubscribeEvent
    public void onTick(TickEvent event)
    {
        if (event.type == TickEvent.Type.CLIENT || event.type == TickEvent.Type.SERVER)
        {
            TransformersMod.proxy.runTasks();
        }
    }
}
