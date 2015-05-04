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
                if (TFDataManager.setInVehicleMode(player, false))
                {
                    player.playSound(TransformersMod.modid + ":transform_robot", 1.0F, 1.0F);
                }
            }
            else if (!inVehicleMode && transformationTimer == 20)
            {
                if (TFDataManager.setInVehicleMode(player, true))
                {
                    player.playSound(TransformersMod.modid + ":transform_vehicle", 1.0F, 1.0F);
                }
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
                if (TFDataManager.getTransformationTimer(player) == 0 && mc.currentScreen == null && transformer.hasStealthForce(player))
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