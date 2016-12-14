package fiskfille.tf.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.opengl.GL11;

public class GuiIconFlat extends GuiButton
{
    public IButtonRenderCallback callback;

    public GuiIconFlat(int id, int x, int y, IButtonRenderCallback renderCallback)
    {
        super(id, x, y, 20, 20, "");
        callback = renderCallback;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(GuiButtonFlat.buttonTextures);

            GL11.glColor4f(1, 1, 1, 1);
            field_146123_n = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
            int hoverState = getHoverState(field_146123_n);

            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            mouseDragged(mc, mouseX, mouseY);
            int color = 0x373737;

            if (!enabled)
            {
                color = 0x7F7F7F;
            }

            drawTexturedModalRect(xPosition, yPosition, 210, hoverState * height, width, height);
            
            GL11.glPushMatrix();
            GL11.glTranslatef(xPosition, yPosition, 0);
            callback.render(this, mouseX, mouseY);
            GL11.glPopMatrix();
        }
    }

    public static interface IButtonRenderCallback
    {
        void render(GuiButton button, int mouseX, int mouseY);
    }
}
