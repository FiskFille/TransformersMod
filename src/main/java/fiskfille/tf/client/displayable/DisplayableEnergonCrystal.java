package fiskfille.tf.client.displayable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.tileentity.ModelCrystal;
import fiskfille.tf.common.block.BlockEnergonCrystal;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.tick.ClientTickHandler;
import fiskfille.tf.helper.TFRenderHelper;

public class DisplayableEnergonCrystal extends Displayable
{
    private ModelCrystal model = new ModelCrystal();

    @Override
    public void render(ItemStack itemstack)
    {
        TFRenderHelper.renderTag(StatCollector.translateToLocalFormatted("tile.display_pedestal.amount", itemstack.stackSize), 0, 0.1F, 0);

        BlockEnergonCrystal block = (BlockEnergonCrystal) Block.getBlockFromItem(itemstack.getItem());
        Energon energon = block.getEnergonType();

        GL11.glPushMatrix();
        float f = 0.75F;
        float f1 = MathHelper.sin((mc.thePlayer.ticksExisted + ClientTickHandler.renderTick) / 15.0F) * 0.07F;
        GL11.glRotatef((mc.thePlayer.ticksExisted + ClientTickHandler.renderTick) * 0.75F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0, -0.3F + f1, 0);
        GL11.glScalef(f, f, f);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float[] rgb = TFRenderHelper.hexToRGB(energon.getColor());
        GL11.glColor4f(rgb[0], rgb[1], rgb[2], 0.5F);

        TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
        model.render();
        TFRenderHelper.resetLighting();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
}
