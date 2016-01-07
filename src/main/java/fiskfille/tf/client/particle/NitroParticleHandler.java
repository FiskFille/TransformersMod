package fiskfille.tf.client.particle;

import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.Map;

public class NitroParticleHandler
{
    private static Map<EntityPlayer, Boolean> nitroOnMap = new HashMap<EntityPlayer, Boolean>();

    public static void doNitroParticles(EntityPlayer player)
    {
        boolean nitro = getNitro(player);

        if (nitro)
        {
            int altMode = TFDataManager.getAltMode(player);

            if (TFDataManager.isTransformed(player))
            {
                Transformer transformer = TFHelper.getTransformer(player);

                if (transformer != null)
                {
                    transformer.doNitroParticles(player, altMode);
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
        Boolean nitroObj = nitroOnMap.get(player);

        if (nitroObj == null)
        {
            nitroOnMap.put(player, false);
            nitroObj = false;
        }

        boolean nitro = nitroObj;

        if (player == Minecraft.getMinecraft().thePlayer)
        {
            nitro = TFMotionManager.getTransformerPlayer(player).isBoosting();
        }

        return nitro;
    }
}
