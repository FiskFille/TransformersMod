package fiskfille.tf.client.displayable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class Displayable
{
    protected Minecraft mc = Minecraft.getMinecraft();

    public abstract void render(ItemStack itemstack);

    protected void bindTexture(ResourceLocation resourcelocation)
    {
        mc.getTextureManager().bindTexture(resourcelocation);
    }

    protected void renderTag(String s, float x, float y, float z)
    {
        RenderManager renderManager = RenderManager.instance;
        FontRenderer fontrenderer = renderManager.getFontRenderer();
        float f2 = -0.02F;
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(mc.thePlayer.rotationYaw + 180, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-mc.thePlayer.rotationPitch, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-f2, -f2, f2);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tessellator.startDrawingQuads();
        int i = fontrenderer.getStringWidth(s) / 2;
        tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        tessellator.addVertex(-i - 1, -1.0D, 0.0D);
        tessellator.addVertex(-i - 1, 8.0D, 0.0D);
        tessellator.addVertex(i + 1, 8.0D, 0.0D);
        tessellator.addVertex(i + 1, -1.0D, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, -1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public float getBlockHeight(ItemStack itemstack)
    {
        return 1.0F;
    }
}
