package fiskfille.tf.client.event;

import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.helper.TFRenderHelper;
import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class ClientEventHandler
{
    public static final Minecraft MC = Minecraft.getMinecraft();

    public static float renderTick;

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderLivingEvent.Pre<EntityPlayer> event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            ModelBiped biped = (ModelBiped) event.getRenderer().getMainModel();

            if (biped != null)
            {
                boolean wearingHead = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.HEAD) != null;
                boolean wearingChest = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.CHEST) != null;
                boolean wearingLegs = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.LEGS) != null;
                boolean wearingFeet = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.FEET) != null;

                biped.bipedHead.showModel = !wearingHead;
                biped.bipedHeadwear.showModel = !wearingHead;

                biped.bipedBody.showModel = !wearingChest;
                biped.bipedRightArm.showModel = !wearingChest;
                biped.bipedLeftArm.showModel = !wearingChest;

                biped.bipedLeftLeg.showModel = !wearingLegs && !wearingFeet;
                biped.bipedRightLeg.showModel = !wearingLegs && !wearingFeet;

                for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
                {
                    if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR && TFHelper.getTransformerFromArmor(player, slot) != null)
                    {
                        TransformerModel tfModel = TFModelRegistry.getModel(TFHelper.getTransformerFromArmor(player, slot));

                        if (tfModel != null)
                        {
                            tfModel.getHead().showModel = !biped.bipedHead.showModel;

                            for (ModelRendererTF modelRenderer : tfModel.getLegs())
                            {
                                modelRenderer.showModel = !biped.bipedLeftLeg.showModel;
                            }

                            for (ModelRendererTF modelRenderer : tfModel.getFeet())
                            {
                                modelRenderer.showModel = wearingFeet;
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRenderPlayerPost(RenderPlayerEvent.Post event)
    {
        ModelBiped modelBipedMain = event.getRenderer().getMainModel();

        if (modelBipedMain != null)
        {
            modelBipedMain.bipedHead.showModel = true;
            modelBipedMain.bipedHeadwear.showModel = true;

            modelBipedMain.bipedBody.showModel = true;
            modelBipedMain.bipedRightArm.showModel = true;
            modelBipedMain.bipedLeftArm.showModel = true;

            modelBipedMain.bipedLeftLeg.showModel = true;
            modelBipedMain.bipedRightLeg.showModel = true;
        }
    }

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event)
    {
        renderTick = event.renderTickTime;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        EntityPlayer player = event.player;
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFData.ALT_MODE.get(player);
        float transformationTimer = TFHelper.getTransformationTimer(player);
        TFHelper.getStealthModeTimer(player);

        if (event.phase == TickEvent.Phase.END)
        {
//            TutorialHandler.tick(player);

            if (transformer != null)
            {
                if (transformationTimer >= 0.5F)
                {
                    transformer.updateMovement(player, altMode);

                    if (TFData.BOOSTING.get(player) && TFData.NITRO.get(player) > 0)
                    {
                        if (TFHelper.isFullyTransformed(player))
                        {
                            transformer.doNitroParticles(player, altMode);
                        }
                    }
                }

                if (player == MC.player)
                {
                    if (transformer.overrideFirstPerson(player, altMode))
                    {
                        GameSettings gameSettings = MC.gameSettings;

                        if (transformationTimer >= 0.5F)
                        {
                            if (TFKeyBinds.VIEW_FRONT.isKeyDown() && MC.currentScreen == null)
                            {
                                gameSettings.thirdPersonView = 2;
                            }
                            else if (TFKeyBinds.VEHICLE_FIRST_PERSON.isKeyDown())
                            {
                                gameSettings.thirdPersonView = 0;
                            }
                            else
                            {
                                gameSettings.thirdPersonView = 1;
                            }
                        }

                        boolean useNitro = false;

                        if (transformationTimer >= 0.5F && transformer.canUseNitro(player, altMode))
                        {
                            useNitro = gameSettings.keyBindForward.isKeyDown() && (gameSettings.keyBindSprint.isKeyDown());
                        }

                        TFData.BOOSTING.set(player, useNitro);

                        if (TFData.TRANSFORM_PROGRESS.get(player) == 0 && TFData.PREV_TRANSFORM_PROGRESS.get(player) > 0)
                        {
//                            if (TFConfig.firstPersonAfterTransformation) TODO: Config
//                            {
//                                gameSettings.thirdPersonView = 0;
//                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START)
        {
            if (MC.world != null)
            {
                for (EntityPlayer player : MC.world.playerEntities)
                {
                    TFRenderHelper.updateMotionY(player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event)
    {
        EntityPlayer player = MC.player;
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFData.ALT_MODE.get(player);
        float transformationTimer = TFHelper.getTransformationTimer(player);

        if (MC.currentScreen == null && player.getRidingEntity() == null)
        {
            if (TFHelper.isTransformer(player))
            {
                KeyBinding[] keys = new KeyBinding[] { TFKeyBinds.TRANSFORM_1 };

                for (int keyAlt = 0; keyAlt < keys.length; ++keyAlt)
                {
                    if (keyAlt < transformer.getAltModeCount())
                    {
                        KeyBinding key = keys[keyAlt];

                        if (key.isPressed())
                        {
                            if (keyAlt == altMode && transformationTimer == 1)
                            {
                                TFData.ALT_MODE.set(player, -1);
                            }
                            else if (altMode == -1 && transformationTimer == 0)
                            {
                                TFData.ALT_MODE.set(player, keyAlt);
                            }
                        }
                    }
                }

                if (TFKeyBinds.STEALTH_MODE.isPressed())
                {
                    if (transformationTimer == 1 && transformer.hasStealthForce(player, altMode))
                    {
                        float stealthModeTimer = TFHelper.getStealthModeTimer(player);

                        if (TFData.STEALTH_FORCE.get(player) && stealthModeTimer == 1)
                        {
                            TFData.STEALTH_FORCE.set(player, false);
                        }
                        else if (!TFData.STEALTH_FORCE.get(player) && stealthModeTimer == 0)
                        {
                            TFData.STEALTH_FORCE.set(player, true);
                        }
                    }
                }
            }
        }
    }
}
