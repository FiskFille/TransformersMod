package fiskfille.tf.client.displayable;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;
import fiskfille.tf.helper.TFRenderHelper;

public class DisplayableTransformiumSeed extends Displayable
{
    @Override
    public void render(ItemStack itemstack)
    {
        TFRenderHelper.renderTag(StatCollector.translateToLocalFormatted("tile.display_pillar.amount", itemstack.stackSize), 0, 0.05F, 0);

        GL11.glPushMatrix();
        float f = 0.5F;
        float f1 = MathHelper.sin(mc.thePlayer.ticksExisted / 15.0F) * 0.07F;
        GL11.glRotatef(mc.thePlayer.ticksExisted * 0.75F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0, 0.6F + f1, 0);
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glScalef(f, f, f);

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityTransformiumSeed(), -0.5F, -0.5F, -0.5F, 0.0F);
        GL11.glPopMatrix();
    }
}
