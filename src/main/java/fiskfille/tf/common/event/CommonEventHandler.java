package fiskfille.tf.common.event;

import fiskfille.tf.common.capability.TFCapabilities;
import fiskfille.tf.common.capability.TFEntityData;
import fiskfille.tf.common.capability.TFPlayerData;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.network.MessageSendFlying;
import fiskfille.tf.common.network.TFNetworkManager;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber
public class CommonEventHandler
{
    private static final Set<EntityPlayer> SYNC_QUEUE = new HashSet<>();

    private static final Map<EntityPlayer, Boolean> PREV_FLYING = new HashMap<>();

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        Entity entity = event.getObject();

        if (entity instanceof EntityPlayer)
        {
            event.addCapability(TFCapabilities.PLAYER_DATA_ID, new TFPlayerData.Implementation());
        }

        if (entity instanceof EntityLivingBase)
        {
            event.addCapability(TFCapabilities.ENTITY_DATA_ID, new TFEntityData.Implementation());
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        TFEntityData data = event.getEntity().getCapability(TFCapabilities.ENTITY_DATA_CAP, null);

        if (data != null)
        {
            data.decrementGroundBridgeCooldown();
        }

        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            if (!player.world.isRemote)
            {
                if (player.capabilities != null)
                {
                    Boolean isFlying = PREV_FLYING.get(player);
                    boolean capabilitiesFlying = player.capabilities.isFlying;

                    if (isFlying != null)
                    {
                        if (isFlying != capabilitiesFlying)
                        {
                            TFNetworkManager.WRAPPER.sendToDimension(new MessageSendFlying(player, capabilitiesFlying), player.dimension);

                            PREV_FLYING.put(player, capabilitiesFlying);
                        }
                    }
                    else
                    {
                        TFNetworkManager.WRAPPER.sendToDimension(new MessageSendFlying(player, capabilitiesFlying), player.dimension);
                        PREV_FLYING.put(player, capabilitiesFlying);
                    }
                }
            }

            if (!player.world.isRemote)
            {
                if (SYNC_QUEUE.contains(player))
                {
                    TFDataManager.updatePlayerWithServerInfo(player);
                    SYNC_QUEUE.remove(player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        EntityPlayer player = event.player;
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFData.ALT_MODE.get(player);
        float transformationTimer = TFHelper.getTransformationTimer(player);
        TFHelper.getStealthModeTimer(player);

        if (event.phase == TickEvent.Phase.START)
        {
            TFData.PREV_TRANSFORM_PROGRESS.setWithoutNotify(player, TFData.TRANSFORM_PROGRESS.get(player));
            TFData.PREV_STEALTH_FORCE_PROGRESS.setWithoutNotify(player, TFData.STEALTH_FORCE_PROGRESS.get(player));
            TFData.PREV_NITRO.setWithoutNotify(player, TFData.NITRO.get(player));

            UUID speedModifierUUID = UUID.fromString("FAD34EEA-6DF0-4C93-A0CB-0D4C654209CF");
            IAttributeInstance speedAttribute = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
            AttributeModifier speedModifier = new AttributeModifier(speedModifierUUID, "TF Speed modifier", -1, 1).setSaved(false);

            if (speedAttribute.getModifier(speedModifierUUID) != null)
            {
                speedAttribute.removeModifier(speedModifier);
                player.stepHeight = 0.5F;
            }

            if (transformationTimer == 1)
            {
                speedAttribute.applyModifier(speedModifier);
                player.stepHeight = 1.0F;
            }

            Transformer prevArmor = null;

            for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
            {
                if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
                {
                    ItemStack armor = player.getItemStackFromSlot(slot);

                    if (!armor.isEmpty() && armor.getItem() instanceof ItemTransformerArmor)
                    {
                        ItemTransformerArmor tfArmor = (ItemTransformerArmor) armor.getItem();

                        if (prevArmor == null)
                        {
                            prevArmor = tfArmor.getTransformer();
                        }
                        else if (prevArmor != tfArmor.getTransformer())
                        {
                            player.setItemStackToSlot(slot, ItemStack.EMPTY);

                            if (!player.inventory.addItemStackToInventory(armor))
                            {
                                player.dropItem(armor, false);
                            }
                        }
                    }
                }
            }
        }
        else
        {
            /*if (TFHelper.shouldOverrideScale(player)) TODO: Scale
            {
                float scale = TFHelper.getScale(player);
                float width = TFHelper.getWidth(player) * scale;
                float height = TFHelper.getHeight(player) * scale;

                if (transformer == null && TFData.PREV_TRANSFORMER.get(player) != null)
                {
                    scale = 1;
                    width = 0.6F;
                    height = 1.8F;
                }

                TFReflection.setSize(player, width, height);

                scale = height / 1.8F;
                player.eyeHeight = (scale - 1) * 1.62F + player.getDefaultEyeHeight() * scale - player.getDefaultEyeHeight() * (scale - 1);
            }*/

            if (transformer != null)
            {
                transformer.tick(player, transformationTimer);

                if (transformationTimer >= 0.5F)
                {
                    if (transformer.canUseNitro(player, altMode) || TFHelper.isInStealthMode(player))
                    {
                        player.setSprinting(false);
                    }
                }
                else
                {
                    TFData.FORWARD_VELOCITY.setWithoutNotify(player, 0.0D);
                }
            }

            int transformTicks = 10;
            int stealthTicks = 5;
            int nitroTicks = 200;

            if (TFData.ALT_MODE.get(player) == -1 && TFHelper.isInStealthMode(player))
            {
                stealthTicks *= 0.75F;
            }

            if (TFData.ALT_MODE.get(player) != -1)
            {
                TFData.TRANSFORM_PROGRESS.incrWithoutNotify(player, 1.0F / transformTicks);
            }
            else if (!TFHelper.isInStealthMode(player))
            {
                TFData.TRANSFORM_PROGRESS.incrWithoutNotify(player, -1.0F / transformTicks);
            }

            TFData.TRANSFORM_PROGRESS.clampWithoutNotify(player, 0.0F, 1.0F);

            if (TFData.STEALTH_FORCE.get(player))
            {
                TFData.STEALTH_FORCE_PROGRESS.incrWithoutNotify(player, 1.0F / stealthTicks);
            }
            else
            {
                TFData.STEALTH_FORCE_PROGRESS.incrWithoutNotify(player, -1.0F / stealthTicks);
            }

            TFData.STEALTH_FORCE_PROGRESS.clampWithoutNotify(player, 0.0F, 1.0F);

            if (!player.capabilities.isCreativeMode && TFData.BOOSTING.get(player) && TFData.NITRO.get(player) > 0)
            {
                TFData.NITRO.incrWithoutNotify(player, -1.0F / nitroTicks);
            }
            else if (!TFData.BOOSTING.get(player) || player.capabilities.isCreativeMode)
            {
                TFData.NITRO.incrWithoutNotify(player, 1.0F / nitroTicks);
            }

            TFData.NITRO.clampWithoutNotify(player, 0.0F, 1.0F);
            TFData.PREV_TRANSFORMER.setWithoutNotify(player, TFHelper.getTransformer(player));
        }
    }

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();
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
}
