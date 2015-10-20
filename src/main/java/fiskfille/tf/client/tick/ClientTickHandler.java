package fiskfille.tf.client.tick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.client.render.entity.CustomEntityRenderer;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.network.MessageVehicleNitro;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;

public class ClientTickHandler
{
    private Minecraft mc = Minecraft.getMinecraft();

    public void onPlayerTick(EntityPlayer player)
    {
        ItemStack heldItem = player.getHeldItem();
        Transformer transformer = TFHelper.getTransformer(player);

        boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
        int transformationTimer = TFDataManager.getTransformationTimer(player);
        int stealthModeTimer = TFDataManager.getStealthModeTimer(player);

        if (stealthModeTimer < 5 && !TFDataManager.isInStealthMode(player))
        {
            TFDataManager.setStealthModeTimer(player, stealthModeTimer + 1);
        }
        else if (stealthModeTimer > 0 && TFDataManager.isInStealthMode(player))
        {
            TFDataManager.setStealthModeTimer(player, stealthModeTimer - 1);
        }

        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

        if (inVehicleMode && transformationTimer < 10)
        {
            if (transformer.canUseNitro(player) || TFDataManager.isInStealthMode(player))
            {
                player.setSprinting(false);
            }

            if (player == mc.thePlayer)
            {
                if (transformer.overrideFirstPerson(player))
                {
                    GameSettings gameSettings = mc.gameSettings;

                    if (TFKeyBinds.keyBindingViewFront.getIsKeyPressed() && mc.currentScreen == null)
                    {
                        gameSettings.thirdPersonView = 2;
                    }
                    else if (TFKeyBinds.keyBindingVehicleFirstPerson.getIsKeyPressed())
                    {
                        gameSettings.thirdPersonView = 0;
                    }
                    else
                    {
                        gameSettings.thirdPersonView = 3;
                    }
                }

                if (transformer != null)
                {
                    transformer.updateMovement(player);

                    if (transformedPlayer != null)
                    {
                        int nitro = transformedPlayer.getNitro();

                        boolean prevNitro = TFMotionManager.prevNitro;

                        boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
                        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();

                        if (nitro > 0 && nitroPressed && moveForward && player == Minecraft.getMinecraft().thePlayer && transformer.canUseNitro(player))
                        {
                            if (!player.capabilities.isCreativeMode)
                            {
                                --nitro;
                            }

                            if (!prevNitro)
                            {
                                TFNetworkManager.networkWrapper.sendToServer(new MessageVehicleNitro(player, true));
                                TFMotionManager.prevNitro = true;
                            }

                            transformedPlayer.setBoosting(true);
                        }
                        else
                        {
                            if (prevNitro)
                            {
                                TFNetworkManager.networkWrapper.sendToServer(new MessageVehicleNitro(player, false));
                                TFMotionManager.prevNitro = false;
                            }

                            transformedPlayer.setBoosting(false);
                        }

                        transformedPlayer.setNitro(nitro);
                    }
                }
            }

            NitroParticleHandler.doNitroParticles(player);
        }
        else
        {
            player.stepHeight = 0.5F;
        }

        int nitro = transformedPlayer == null ? 0 : transformedPlayer.getNitro();
        boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();

        if (nitro < 160 && (player.capabilities.isCreativeMode || !(nitroPressed && !TFDataManager.isInStealthMode(player) && moveForward && inVehicleMode && transformationTimer < 10)))
        {
            ++nitro;
        }

        TFMotionManager.setNitro(player, nitro);

        if (transformer == null && TFPlayerData.getData(player).vehicle)
        {
            TFDataManager.setInVehicleMode(player, false);
        }
    }

    private float getCameraOffset(EntityPlayer player, Transformer transformer)
    {
        if (transformer != null)
        {
            if (TFDataManager.getTransformationTimer(player) > 10)
            {
                return transformer.getCameraYOffset(player);
            }
            else
            {
                return transformer.getVehicleCameraYOffset(player);
            }
        }
        else
        {
            return -1;
        }
    }

    public void handleTransformation(EntityPlayer player)
    {
        Transformer transformer = TFHelper.getTransformer(player);

        int transformationTimer = TFDataManager.getTransformationTimer(player);
        boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

        float offsetY = getCameraOffset(player, transformer) + (float) transformationTimer / 20;

        CustomEntityRenderer.setOffsetY(player, offsetY);

        if (transformationTimer < 20 && !inVehicleMode)
        {
            transformationTimer++;

            TFDataManager.setTransformationTimer(player, transformationTimer);

            TFMotionManager.setForwardVelocity(player, 0.0D);

            if (transformationTimer == 19)
            {
                if (mc.thePlayer == player)
                {
                    mc.gameSettings.thirdPersonView = TFConfig.firstPersonAfterTransformation ? 0 : mc.gameSettings.thirdPersonView;
                }
            }
        }
        else if (transformationTimer > 0 && inVehicleMode)
        {
            transformationTimer--;

            TFDataManager.setTransformationTimer(player, transformationTimer);
        }
    }

    public void onTickEnd()
    {
        EntityPlayer player = mc.thePlayer;

        if (player != null)
        {
            Transformer transformer = TFHelper.getTransformer(player);

            if (transformer != null && player.ridingEntity == null)
            {
                float thirdPersonDistance = 2.0F - -(float) TFDataManager.getTransformationTimer(player) / 10;

                if (transformer != null && transformer.canZoom(player) && TFDataManager.isInVehicleMode(player) && TFKeyBinds.keyBindingZoom.getIsKeyPressed() && !TFKeyBinds.keyBindingViewFront.getIsKeyPressed())
                {
                    thirdPersonDistance = transformer.getZoomAmount(player);
                }
                else if (transformer != null)
                {
                    thirdPersonDistance = transformer.getThirdPersonDistance(player);
                }

                ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.entityRenderer, thirdPersonDistance, new String[] { "thirdPersonDistance", "E", "field_78490_B" });


                //            VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
                //            if (transformedPlayer != null)
                    //            {
                    //                float f = player.rotationYaw;
                    //                //                ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.entityRenderer, transformedPlayer.getJetRoll() / 5, new String[] {"camRoll"});
                //            }
            }
        }
    }

    public void onTickStart()
    {

    }
}