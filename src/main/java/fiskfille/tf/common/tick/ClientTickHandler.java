package fiskfille.tf.common.tick;

import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import fiskfille.tf.TFReflection;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.render.entity.EntityRendererTF;
import fiskfille.tf.client.tutorial.TutorialHandler;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTileHelper;

public class ClientTickHandler
{
    private Minecraft mc = Minecraft.getMinecraft();

    private EntityRenderer renderer, prevRenderer;
    public static float renderTick;

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event)
    {
        EntityPlayer player = event.player;
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFData.ALT_MODE.get(player);
        float transformationTimer = TFHelper.getTransformationTimer(player);
        TFHelper.getStealthModeTimer(player);

        if (event.phase == TickEvent.Phase.END)
        {
            TutorialHandler.tick(player);

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

                if (player == mc.thePlayer)
                {
                    if (transformer.overrideFirstPerson(player, altMode))
                    {
                        GameSettings gameSettings = mc.gameSettings;

                        if (transformationTimer >= 0.5F)
                        {
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
                                gameSettings.thirdPersonView = 1;
                            }
                        }

                        boolean useNitro = false;

                        if (transformationTimer >= 0.5F && transformer.canUseNitro(player, altMode))
                        {
                            useNitro = gameSettings.keyBindForward.getIsKeyPressed() && (gameSettings.keyBindSprint.getIsKeyPressed() || TFKeyBinds.keyBindingNitro.getIsKeyPressed());
                        }

                        TFData.BOOSTING.set(player, useNitro);

                        if (transformationTimer == 0 && TFData.PREV_TRANSFORM_PROGRESS.get(player) > 0)
                        {
                            if (TFConfig.firstPersonAfterTransformation)
                            {
                                gameSettings.thirdPersonView = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START)
        {
            if (mc.theWorld != null)
            {
                for (EntityPlayer player : (List<EntityPlayer>) mc.theWorld.playerEntities)
                {
                    TFRenderHelper.updateMotionY(player);
                }

                if (!mc.theWorld.isRemote && mc.isGamePaused())
                {
                    for (Map.Entry<DimensionalCoords, TileData> e : TFTileHelper.getTileData().entrySet())
                    {
                        e.getValue().clientTick();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event)
    {
        World world = mc.theWorld;
        renderTick = event.renderTickTime;

        if (world != null)
        {
            if (event.phase == TickEvent.Phase.START)
            {
                EntityClientPlayerMP player = mc.thePlayer;

                if (TFRenderHelper.shouldOverrideView(player))
                {
                    if (renderer == null)
                    {
                        renderer = new EntityRendererTF(mc);
                    }

                    if (mc.entityRenderer != renderer)
                    {
                        prevRenderer = mc.entityRenderer;
                        mc.entityRenderer = renderer;
                    }
                }
                else if (prevRenderer != null && mc.entityRenderer == renderer)
                {
                    mc.entityRenderer = prevRenderer;
                }
            }

            if (mc.thePlayer != null)
            {
                EntityPlayer player = mc.thePlayer;
                Transformer transformer = TFHelper.getTransformer(player);

                if (TFRenderHelper.shouldOverrideThirdPersonDistance(player))
                {
                    if (transformer != null)
                    {
                        float thirdPersonDistance = 4 - TFHelper.getTransformationTimer(player) * 2;
                        int altMode = TFData.ALT_MODE.get(player);

                        if (transformer != null && transformer.canZoom(player) && TFHelper.isFullyTransformed(player) && TFKeyBinds.keyBindingZoom.getIsKeyPressed() && !TFKeyBinds.keyBindingViewFront.getIsKeyPressed())
                        {
                            thirdPersonDistance = transformer.getZoomAmount(player, altMode);
                        }
                        else if (transformer != null)
                        {
                            thirdPersonDistance = transformer.getThirdPersonDistance(player, altMode);
                        }

                        TFReflection.setField(TFReflection.thirdPersonDistanceField, mc.entityRenderer, thirdPersonDistance);
                    }
                }
            }
        }
    }
}
