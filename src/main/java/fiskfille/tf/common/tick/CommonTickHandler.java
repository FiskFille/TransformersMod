package fiskfille.tf.common.tick;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.tf.TFReflection;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.data.TFPlayerData;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

public class CommonTickHandler
{
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event)
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
            IAttributeInstance speedAttribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
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
            
            for (int i = 0; i < 4; ++i)
            {
                ItemStack armor = player.getEquipmentInSlot(1 + i);
                
                if (armor != null && armor.getItem() instanceof ItemTransformerArmor)
                {
                    ItemTransformerArmor tfArmor = (ItemTransformerArmor) armor.getItem();
                    
                    if (prevArmor == null)
                    {
                        prevArmor = tfArmor.getTransformer();
                    }
                    else if (prevArmor != tfArmor.getTransformer())
                    {
                        player.setCurrentItemOrArmor(1 + i, null);
                        
                        if (!player.inventory.addItemStackToInventory(armor))
                        {
                            player.dropPlayerItemWithRandomChoice(armor, false);
                        }
                    }
                }
            }
        }
        else
        {
            TFPlayerData.getData(player).onUpdate();

            if (TFHelper.shouldOverrideScale(player))
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
            }

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
}
