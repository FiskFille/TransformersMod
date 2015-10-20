package fiskfille.tf.client.particle;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

public class NitroParticleHandler
{
    private static Map<EntityPlayer, Boolean> nitroOnMap = new HashMap<EntityPlayer, Boolean>();

    public static void doNitroParticles(EntityPlayer player)
    {
        boolean nitro = getNitro(player);

        if (nitro)
        {
            if (TFDataManager.isInVehicleMode(player))
            {
                Transformer transformer = TFHelper.getTransformer(player);

                if (transformer != null)
                {
                    transformer.doNitroParticles(player);
                }
            }
        }
    }

    public static void setNitro(EntityPlayer player, boolean nitro)
    {
        nitroOnMap.put(player, nitro);
    }

    public static boolean getNitro(EntityPlayer player)
    {
        boolean nitro = false;

        Boolean nitroObj = nitroOnMap.get(player);

        if (nitroObj == null)
        {
            nitroOnMap.put(player, false);
            nitroObj = false;
        }

        nitro = nitroObj;

        if (player == Minecraft.getMinecraft().thePlayer)
        {
            nitro = TFMotionManager.getTransformerPlayer(player).isBoosting();
        }

        return nitro;
    }
}
