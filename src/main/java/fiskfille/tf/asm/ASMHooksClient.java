package fiskfille.tf.asm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.helper.TFHelper;

public class ASMHooksClient
{
    private static Minecraft mc = Minecraft.getMinecraft();

    public static int getBrightnessForRender(Entity entity)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            float scale = TFHelper.getHeight(player) / 1.8F;

            return MathHelper.floor_double(entity.boundingBox.minY + scale * 1.62F);
        }

        double d0 = (entity.boundingBox.maxY - entity.boundingBox.minY) * 0.66D;
        return MathHelper.floor_double(entity.posY - entity.yOffset + d0);
    }

    public static void applyPlayerRenderTranslation(RenderPlayer render, AbstractClientPlayer player, double x, double y, double z)
    {
        if (player == mc.thePlayer)
        {
            GL11.glTranslatef(0, player.yOffset - 1.62F, 0);
        }

        GL11.glTranslatef((float) x, (float) y, (float) z);
    }

    public static double getScaledSneakOffset(EntityPlayer player)
    {
        return 0.125D;
    }

    public static float getViewBobbingMultiplier(float partialTicks)
    {
        EntityPlayer player = mc.thePlayer;

        if (mc.gameSettings.thirdPersonView == 0)
        {
            return 1.0F;
        }
        else
        {
            return ASMHooks.getEntityScale(player);
        }
    }

    public static float getInventoryPlayerScale(int scale, EntityLivingBase entity)
    {
        return scale / ASMHooks.getModifiedEntityScale(entity);
    }

    public static float getInventoryPlayerOffset(int offset, int originalScale, EntityLivingBase entity)
    {
        return offset;
    }

    public static float getInventoryPlayerOffsetPost(int offset, int originalScale, EntityLivingBase entity)
    {
        return entity.yOffset - 1.62F;
    }
}
