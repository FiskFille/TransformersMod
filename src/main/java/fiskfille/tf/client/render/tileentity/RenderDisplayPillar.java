package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.displayable.Displayable;
import fiskfille.tf.client.model.tileentity.ModelDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderDisplayPillar extends TileEntitySpecialRenderer
{
    private ResourceLocation texture = new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/display_pillar.png");
    private ModelDisplayPillar model = new ModelDisplayPillar();

    public void renderModelAt(TileEntityDisplayPillar displayPillar, double x, double y, double z, float partialTicks)
    {
        ItemStack displayItem = displayPillar.getDisplayItem();
        
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);

        if (displayItem != null)
        {
            Displayable displayable = TransformersAPI.getDisplayableFor(displayItem.getItem());

            if (displayable != null)
            {
                GL11.glPushMatrix();
                displayable.render(displayItem);
                GL11.glPopMatrix();
            }
        }
        
        bindTexture(texture);
        model.setBreaking(false);
        model.render();

        if (displayPillar.getWorldObj() != null)
        {
            int progress = TFRenderHelper.getBlockDestroyProgress(displayPillar.getWorldObj(), displayPillar.xCoord, displayPillar.yCoord, displayPillar.zCoord);

            if (progress >= 0)
            {
                OpenGlHelper.glBlendFunc(774, 768, 1, 0);
                bindTexture(new ResourceLocation(String.format("textures/blocks/destroy_stage_%s.png", progress)));
                GL11.glColor4f(1, 1, 1, 0.5F);
                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                model.setBreaking(true);
                model.render();
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glPopMatrix();
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTicks)
    {
        renderModelAt((TileEntityDisplayPillar) tileentity, x, y, z, partialTicks);
    }
}
