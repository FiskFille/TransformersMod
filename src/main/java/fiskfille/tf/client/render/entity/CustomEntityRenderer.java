package fiskfille.tf.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

public class CustomEntityRenderer extends EntityRenderer
{
    private final Minecraft mc;

    public CustomEntityRenderer(Minecraft mc)
    {
        super(mc, mc.getResourceManager());
        this.mc = mc;
    }

    @Override
    public void updateCameraAndRender(float partialTick)
    {
        EntityPlayer player = mc.thePlayer;

        if (player == null || player.isPlayerSleeping())
        {
            super.updateCameraAndRender(partialTick);
            return;
        }

        player.yOffset -= getOffsetY(player); // TODO
        super.updateCameraAndRender(partialTick);
        player.yOffset = 1.62F;
    }

    public static float getOffsetY(EntityPlayer player)
    {
    	Transformer transformer = TFHelper.getTransformer(player);
        return getCameraOffset(player, transformer) + TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) / 20;
    }
    
    public static float getCameraOffset(EntityPlayer player, Transformer transformer)
    {
        if (transformer != null)
        {
            int altMode = TFDataManager.getAltMode(player);

            if (TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) > 10)
            {
                return transformer.getCameraYOffset(player, altMode);
            }
            else
            {
                return transformer.getVehicleCameraYOffset(player, altMode);
            }
        }
        else
        {
            return -1;
        }
    }
}
