package fiskfille.tf.client.render.entity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class CustomEntityRenderer extends EntityRenderer
{
    private final Minecraft mc;
    private static Map<EntityPlayer, Float> offsetY = new HashMap<EntityPlayer, Float>();
    
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
        
        Float offsetForPlayer = offsetY.get(player);
        
        if (offsetForPlayer == null)
        {
            offsetForPlayer = 1.62F;
            offsetY.put(player, 1.62F);
        }
        
        player.yOffset -= offsetForPlayer;
        super.updateCameraAndRender(partialTick);
        player.yOffset = 1.62F;
    }
    
    @Override
    public void getMouseOver(float partialTick)
    {
        super.getMouseOver(partialTick);
    }
    
    public static void setOffsetY(EntityPlayer player, float f)
    {
        offsetY.put(player, f);
    }
    
    public static float getOffsetY(EntityPlayer entityPlayer)
    {
        return offsetY != null ? offsetY.get(entityPlayer) : null;
    }
}
