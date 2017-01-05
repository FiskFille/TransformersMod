package fiskfille.tf.common.data;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import fiskfille.tf.common.network.MessagePlayerJoin;
import fiskfille.tf.common.network.base.TFNetworkManager;

/**
 * @author FiskFille, gegy1000
 */
public class TFDataManager
{
    private static Map<EntityPlayer, Integer> zoomTimerClient = new HashMap<EntityPlayer, Integer>();

    public static int getAltForTransformAnimation(EntityPlayer player)
    {
        int alt = TFData.ALT_MODE.get(player);

        if (alt != -1)
        {
            return alt;
        }
        else
        {
            return TFData.PREV_ALT_MODE.get(player);
        }
    }

    /**
     * Sets Vurp's Sniper zoom for the specified player.
     */
    public static void setZoomTimer(EntityPlayer player, int timer)
    {
        zoomTimerClient.put(player, timer);
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
            TFNetworkManager.networkWrapper.sendTo(new MessagePlayerJoin(player), (EntityPlayerMP) player);
        }
    }
}
