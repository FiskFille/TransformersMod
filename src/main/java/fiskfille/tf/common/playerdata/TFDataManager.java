package fiskfille.tf.common.playerdata;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.event.PlayerTransformEvent;
import fiskfille.tf.common.network.MessageHandleStealthTransformation;
import fiskfille.tf.common.network.MessageHandleTransformation;
import fiskfille.tf.common.network.MessagePlayerJoin;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;

/**
 * @author FiskFille, gegy1000
 */
public class TFDataManager
{
    private static Map<EntityPlayer, Integer> transformationTimerClient = new HashMap<EntityPlayer, Integer>();
    private static Map<EntityPlayer, Integer> stealthModeTimerClient = new HashMap<EntityPlayer, Integer>();
    private static Map<EntityPlayer, Integer> zoomTimerClient = new HashMap<EntityPlayer, Integer>();
    
    /**
     * Used to set whether the player is in vehicle mode or not. Keep Note that this will notify other clients / the server depending which side you are currently on.
     */
    public static boolean setInVehicleMode(EntityPlayer player, boolean vehicleMode)
    {
        TFPlayerData data = TFPlayerData.getData(player);
        
        if (vehicleMode != data.vehicle)
        {
            if (!MinecraftForge.EVENT_BUS.post(new PlayerTransformEvent(player, TFHelper.getTransformer(player), vehicleMode, data.stealthForce)))
            {
                player.triggerAchievement(TFAchievements.transform);
                
                if (!vehicleMode)
                {
                    data.stealthForce = false;
                }
                
                if (player.worldObj.isRemote)
                {
                    TFNetworkManager.networkWrapper.sendToServer(new MessageHandleTransformation(player, vehicleMode));
                }
                else
                {
                    TFNetworkManager.networkWrapper.sendToDimension(new MessageHandleTransformation(player, vehicleMode), player.dimension);
                }
                
                data.vehicle = vehicleMode;
                
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Used to set whether the player is in stealth force or not. Keep Note that this will notify other clients / the server depending which side you are currently on.
     */
    public static boolean setInStealthMode(EntityPlayer player, boolean stealthMode)
    {
        if (stealthMode != TFPlayerData.getData(player).stealthForce)
        {
            if (isInVehicleMode(player))
            {
                if (player.worldObj.isRemote)
                {
                    TFNetworkManager.networkWrapper.sendToServer(new MessageHandleStealthTransformation(player, stealthMode));
                }
                else
                {
                    TFNetworkManager.networkWrapper.sendToDimension(new MessageHandleStealthTransformation(player, stealthMode), player.dimension);
                }
                
                TFPlayerData.getData(player).stealthForce = stealthMode;
                
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Used to set whether the player is in stealth force or not. This will not notify any other players.
     */
    public static void setInStealthModeWithoutNotify(EntityPlayer player, boolean stealthMode)
    {
        if (stealthMode != TFPlayerData.getData(player).stealthForce)
        {
            if (isInVehicleMode(player))
            {
                TFPlayerData.getData(player).stealthForce = stealthMode;
            }
        }
    }
    
    /**
     * Used to set whether the player is in vehicle mode or not. This will not notify any other players.
     */
    public static void setInVehicleModeWithoutNotify(EntityPlayer player, boolean vehicleMode)
    {
        TFPlayerData data = TFPlayerData.getData(player);
        
        if (vehicleMode != data.vehicle)
        {
            if (!MinecraftForge.EVENT_BUS.post(new PlayerTransformEvent(player, TFHelper.getTransformer(player), vehicleMode, data.stealthForce)))
            {
                player.triggerAchievement(TFAchievements.transform);
                
                if (!vehicleMode)
                {
                    data.stealthForce = false;
                }
                
                data.vehicle = vehicleMode;
            }
        }
    }
    
    /**
     * Sets the transformation timer for the specified player.
     */
    public static void setTransformationTimer(EntityPlayer player, int timer)
    {
        transformationTimerClient.put(player, timer);
    }
    
    /**
     * Sets the stealth force timer for the specified player.
     */
    public static void setStealthModeTimer(EntityPlayer player, int timer)
    {
        stealthModeTimerClient.put(player, timer);
    }
    
    /**
     * Sets the Vurp's Sniper zoom for the specified player. 
     */
    public static void setZoomTimer(EntityPlayer player, int timer)
    {
        zoomTimerClient.put(player, timer);
    }
    
    /**
     * @returns whether the specified player is currently in vehicle mode.
     */
    public static boolean isInVehicleMode(EntityPlayer player)
    {
        return TFPlayerData.getData(player).vehicle && TFHelper.isPlayerTransformer(player);
    }
    
    /**
     * @returns whether the specified player is currently in stealth force.
     */
    public static boolean isInStealthMode(EntityPlayer player)
    {
        Transformer transformer = TFHelper.getTransformer(player);
        
        if (transformer != null)
        {
            return transformer.hasStealthForce(player) && isInVehicleMode(player) && TFPlayerData.getData(player).stealthForce;
        }
        
        return false;
    }
    
    /**
     * @returns the current frame of the transformation animation for the specified player. (0 = Fully Transformed : 20 = Normal Robot Mode)
     */
    public static int getTransformationTimer(EntityPlayer player)
    {
        Integer timer = transformationTimerClient.get(player);
        
        return timer != null ? timer : 20;
    }
    
    /**
     * @returns the current frame of the stealth force animation for the specified player. (0 = Full Stealth Force : 5 = Normal Vehicle Mode)
     */
    public static int getStealthModeTimer(EntityPlayer player)
    {
        Integer timer = stealthModeTimerClient.get(player);
        
        return timer != null ? timer : 5;
    }
    
    /**
     * @returns the current frame of the zoom animation for the specified player.
     */
    public static int getZoomTimer(EntityPlayer player)
    {
        Integer timer = zoomTimerClient.get(player);
        
        return timer != null ? timer : 0;
    }
    
    /**
     * Tells the player about certain settings set on the server. Call only from server side.
     */
    public static void updatePlayerWithServerInfo(EntityPlayer player)
    {
        if (!player.worldObj.isRemote)
        {
            TFNetworkManager.networkWrapper.sendTo(new MessagePlayerJoin(isInVehicleMode(player), isInStealthMode(player), TFConfig.canTransform), (EntityPlayerMP) player);
        }
    }
}