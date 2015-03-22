package fiskfille.tf.client.tick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.common.event.PlayerTransformEvent;
import fiskfille.tf.common.item.ItemVurpsSniper;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import fiskfille.tf.common.proxy.ClientProxy;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.common.transformer.base.TransformerCar;
import fiskfille.tf.common.transformer.base.TransformerTruck;
import fiskfille.tf.helper.TFHelper;

public class TickHandler
{
    public static int time = 0;
    
    public static boolean prevViewBobbing;
    
    private Minecraft mc = Minecraft.getMinecraft();
    
    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event)
    {
        EntityPlayer player = mc.thePlayer;
        ItemStack itemstack = player.getHeldItem();
        
        boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
        int transformationTimer = TFDataManager.getTransformationTimer(player);
        
        if (TFKeyBinds.keyBindingTransform.getIsKeyPressed() && mc.currentScreen == null && (TFHelper.isPlayerTransformer(player)) && player.ridingEntity == null)
        {
            GameSettings gameSettings = mc.gameSettings;
            
            if (inVehicleMode && transformationTimer == 0)
            {
                TFDataManager.setInVehicleMode(player, false);
                gameSettings.viewBobbing = prevViewBobbing;
                player.playSound(TransformersMod.modid + ":transform_robot", 1.0F, 1.0F);
                TFPlayerData.getData(player).stealthForce = false;
            }
            else if (!inVehicleMode && transformationTimer == 20)
            {
                TFDataManager.setInVehicleMode(player, true);
                prevViewBobbing = gameSettings.viewBobbing;
                gameSettings.viewBobbing = false;
                player.playSound(TransformersMod.modid + ":transform_vehicle", 1.0F, 1.0F);
                TFPlayerData.getData(player).stealthForce = false;
                TFMotionManager.resetPlayer(player);
            }
            
            EntityRenderer entityRenderer = mc.entityRenderer;
            
            try
            {
                float camRoll = ClientProxy.camRollField.getFloat(entityRenderer);
                ClientProxy.camRollField.set(entityRenderer, 0);
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        
        if (TFKeyBinds.keyBindingStealthMode.getIsKeyPressed())
        {
            Transformer transformer = TFHelper.getTransformer(player);
            
            if (transformer != null)
            {
                if (inVehicleMode && mc.currentScreen == null && transformer.hasStealthForce(player))
                {
                    int stealthModeTimer = TFDataManager.getStealthModeTimer(player);
                    
                    if (TFDataManager.isInStealthMode(player) && stealthModeTimer == 0)
                    {
                        TFDataManager.setInStealthMode(player, false);
                        player.playSound(TransformersMod.modid + ":transform_robot", 1.0F, 1.5F);
                    }
                    else if (!TFDataManager.isInStealthMode(player) && stealthModeTimer == 5)
                    {
                        TFDataManager.setInStealthMode(player, true);
                        player.playSound(TransformersMod.modid + ":transform_vehicle", 1.0F, 1.5F);
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
        boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
        int transformationTimer = TFDataManager.getTransformationTimer(player);
        
        if (player.worldObj.isRemote)
        {
            if (time % 2 == 0)
            {
                TransformersMod.proxy.tickHandler.onPlayerTick(player);
            }
            
            TransformersMod.proxy.tickHandler.handleTransformation(player);
        }
        
        if (TFDataManager.getZoomTimer(player) > 7)
        {
            ItemStack heldItem = player.getHeldItem();
            
            if (heldItem != null && heldItem.getItem() instanceof ItemVurpsSniper)
            {
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1, 0));
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        switch (event.phase)
        {
            case START:
            {
                TransformersMod.proxy.tickHandler.onTickStart();
                break;
            }
            case END:
            {
                TransformersMod.proxy.tickHandler.onTickEnd();
                break;
            }
        }
    }
}