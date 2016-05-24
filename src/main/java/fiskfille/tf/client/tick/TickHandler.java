package fiskfille.tf.client.tick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.proxy.CommonProxy;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

public class TickHandler
{
    public static int time = 0;

    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event)
    {
        EntityPlayer player = mc.thePlayer;

        int altMode = TFDataManager.getAltMode(player);

        int transformationTimer = TFDataManager.getTransformationTimer(player);

        Transformer transformer = TFHelper.getTransformer(player);

        if (mc.currentScreen == null && TFHelper.isPlayerTransformer(player) && player.ridingEntity == null)
        {
            KeyBinding[] keys = new KeyBinding[] { TFKeyBinds.keyBindingTransform1, TFKeyBinds.keyBindingTransform2 };

            for (int keyAlt = 0; keyAlt < keys.length; keyAlt++)
            {
                if (keyAlt < transformer.getAltModeCount())
                {
                    KeyBinding key = keys[keyAlt];

                    if (key.getIsKeyPressed())
                    {
                        if (keyAlt == altMode && transformationTimer == 0)
                        {
                            if (TFDataManager.setAltMode(player, -1))
                            {
                                player.playSound(TransformersMod.modid + ":transform_robot", 1.0F, 1.0F);
                            }
                        }
                        else if (altMode == -1 && transformationTimer == 20)
                        {
                            if (TFDataManager.setAltMode(player, keyAlt))
                            {
                                VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

                                if (transformedPlayer != null)
                                {
                                    transformedPlayer.setLandingTimer(20);
                                }

                                player.playSound(TransformersMod.modid + ":transform_vehicle", 1.0F, 1.0F);
                            }
                        }
                    }
                }
            }
        }

        if (TFKeyBinds.keyBindingStealthMode.getIsKeyPressed())
        {
            if (transformer != null)
            {
                if (TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) == 0 && mc.currentScreen == null && transformer.hasStealthForce(player, altMode))
                {
                    int stealthModeTimer = TFDataManager.getStealthModeTimer(player);

                    if (TFDataManager.isInStealthMode(player) && stealthModeTimer == 0)
                    {
                        TFDataManager.setInStealthMode(player, false);
                        player.playSound(TransformersMod.modid + ":transform_stealth_in", 1.0F, 1.25F);
                    }
                    else if (!TFDataManager.isInStealthMode(player) && stealthModeTimer == 5)
                    {
                        TFDataManager.setInStealthMode(player, true);
                        player.playSound(TransformersMod.modid + ":transform_stealth", 1.0F, 1.25F);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event)
    {
        ++time;
        EntityPlayer player = event.player;

        if (player.worldObj.isRemote)
        {
            if (time % 2 == 0)
            {
                CommonProxy.tickHandler.onPlayerTick(player);
            }

            CommonProxy.tickHandler.handleTransformation(player);
        }
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        switch (event.phase)
        {
            case START:
            {
                CommonProxy.tickHandler.onTickStart();
                break;
            }
            case END:
            {
                CommonProxy.tickHandler.onTickEnd();
                break;
            }
        }
    }
}
