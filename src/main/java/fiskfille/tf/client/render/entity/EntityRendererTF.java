package fiskfille.tf.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.helper.TFHelper;

public class EntityRendererTF extends EntityRenderer
{
    private final Minecraft mc;

    public EntityRendererTF(Minecraft mc)
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

        float scale = TFHelper.getHeight(player) / 1.8F;

        player.yOffset = -(scale - 1) * 1.62F + 1.62F;
        super.updateCameraAndRender(partialTick);
        player.yOffset = 1.62F;
    }
}
