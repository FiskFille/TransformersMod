package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelEnergyColumn;
import fiskfille.tf.client.render.item.RenderItemPowerCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tileentity.TileEntityColumn;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderColumn extends TileEntitySpecialRenderer
{
    private ModelEnergyColumn model = new ModelEnergyColumn();

    public void render(TileEntityColumn tile, double x, double y, double z, float partialTicks)
    {
        World world = tile.getWorldObj();
        int metadata = 0;

        if (world != null)
        {
            metadata = tile.getBlockMetadata();
        }

        if (metadata < 4)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1, -1F, -1F);
            GL11.glRotatef(metadata * 90, 0, 1, 0);

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/energy_column.png"));
            model.setBreaking(false);
            model.render(tile);

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/energy_column_lights.png"));
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
            model.render(tile);

            Tessellator tessellator = Tessellator.instance;
            float f = 0.001F;
            float texX = 30;
            float texY = 31;
            float texWidth = 7;
            float width = texWidth * 0.0625F;

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(width / 2, -(0.5F + f), -width / 2, (texX + texWidth) * (1F / 128), (texY + texWidth) * (1F / 64));
            tessellator.addVertexWithUV(width / 2, -(0.5F + f), width / 2, (texX + texWidth) * (1F / 128), texY * (1F / 64));
            tessellator.addVertexWithUV(-width / 2, -(0.5F + f), width / 2, texX * (1F / 128), texY * (1F / 64));
            tessellator.addVertexWithUV(-width / 2, -(0.5F + f), -width / 2, texX * (1F / 128), (texY + texWidth) * (1F / 64));
            texX += texWidth;
            tessellator.addVertexWithUV(width / 2, 1.5F + f, -width / 2, (texX + texWidth) * (1F / 128), (texY + texWidth) * (1F / 64));
            tessellator.addVertexWithUV(-width / 2, 1.5F + f, -width / 2, texX * (1F / 128), (texY + texWidth) * (1F / 64));
            tessellator.addVertexWithUV(-width / 2, 1.5F + f, width / 2, texX * (1F / 128), texY * (1F / 64));
            tessellator.addVertexWithUV(width / 2, 1.5F + f, width / 2, (texX + texWidth) * (1F / 128), texY * (1F / 64));
            tessellator.draw();
            TFRenderHelper.resetLighting();
            GL11.glEnable(GL11.GL_LIGHTING);

            if (world != null)
            {
                int progress = TFRenderHelper.getBlockDestroyProgress(world, tile.xCoord, tile.yCoord, tile.zCoord);

                if (progress >= 0)
                {
                    OpenGlHelper.glBlendFunc(774, 768, 1, 0);
                    bindTexture(new ResourceLocation(String.format("textures/blocks/destroy_stage_%s.png", progress)));
                    GL11.glColor4f(1, 1, 1, 0.5F);
                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    model.setBreaking(true);
                    model.render(tile);
                    GL11.glDisable(GL11.GL_ALPHA_TEST);
                    GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glPopMatrix();
                }
            }

            GL11.glDisable(GL11.GL_BLEND);

            for (int i = 0; i < tile.getSizeInventory(); ++i)
            {
                ItemStack itemstack = tile.getStackInSlot(i);

                if (itemstack != null && itemstack.getItem() == TFItems.powerCanister)
                {
                    GL11.glPushMatrix();
                    GL11.glRotatef((i + 2) * 360F / tile.getSizeInventory(), 0, 1, 0);
                    GL11.glTranslatef(0.0625F * 7, 0.0625F * 12, 0);

                    RenderItemPowerCanister.renderCanister(itemstack);
                    GL11.glPopMatrix();
                }
            }

            GL11.glPopMatrix();
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityColumn) tileentity, d, d1, d2, f);
    }
}
