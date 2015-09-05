package fiskfille.tf.client.displayable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.tileentity.ModelCrystal;

public class DisplayableEnergonCrystal extends Displayable
{
    private ModelCrystal model = new ModelCrystal();

    @Override
    public void render(ItemStack itemstack)
    {
        renderTag("x" + itemstack.stackSize, 0, 0.1F, 0);

        GL11.glPushMatrix();
        float f = 0.75F;
        float f1 = MathHelper.sin(mc.thePlayer.ticksExisted / 15.0F) * 0.07F;
        GL11.glRotatef(mc.thePlayer.ticksExisted * 0.75F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0, -0.3F + f1, 0);
        GL11.glScalef(f, f, f);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(0.0F, 0.5F, 1.0F, 0.5F);
        model.render();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
}
