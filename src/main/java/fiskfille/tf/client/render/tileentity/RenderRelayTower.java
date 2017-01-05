package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelRelayTorch;
import fiskfille.tf.client.model.tileentity.ModelRelayTower;
import fiskfille.tf.common.tileentity.TileEntityRelayTorch;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderRelayTower extends TileEntitySpecialRenderer
{
    private ModelRelayTower modelTower = new ModelRelayTower();
    private ModelRelayTower modelTorch = new ModelRelayTorch();

    public void render(TileEntityRelayTower tower, double x, double y, double z, float partialTicks)
    {
        World world = tower.getWorldObj();
        int metadata = 0;

        if (world != null)
        {
            metadata = tower.getBlockMetadata();
        }

        if (tower.isValid(metadata))
        {
            ModelRelayTower model = getModel(tower);

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1, -1F, -1F);

            if (tower instanceof TileEntityRelayTorch && world != null)
            {
                int[] aint = {0, 2, 1, 3};

                if (metadata > 0 && metadata < 5)
                {
                    GL11.glRotatef(aint[metadata - 1] * 90, 0, 1, 0);
                }

                if (metadata == 6)
                {
                    GL11.glTranslatef(0, 2, 0);
                    GL11.glRotatef(180, 0, 0, 1);
                }

                if (metadata != 5 && metadata != 6)
                {
                    GL11.glTranslatef(1, 1, 0);
                    GL11.glRotatef(90, 0, 0, 1);
                }
            }
            else
            {
                GL11.glRotatef(metadata * 90, 0, 1, 0);
            }

            bindTexture(new ResourceLocation(TransformersMod.modid, String.format("textures/models/tiles/relay_%s.png", tower instanceof TileEntityRelayTorch ? "torch" : "tower")));
            model.setBreaking(false);
            model.render(tower, partialTicks);

            bindTexture(new ResourceLocation(TransformersMod.modid, String.format("textures/models/tiles/relay_%s_lights.png", tower instanceof TileEntityRelayTorch ? "torch" : "tower")));
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            TFRenderHelper.setLighting(61680);
            model.render(tower, partialTicks);
            TFRenderHelper.resetLighting();
            GL11.glEnable(GL11.GL_LIGHTING);

            if (world != null)
            {
                int progress = TFRenderHelper.getBlockDestroyProgress(world, tower.xCoord, tower.yCoord, tower.zCoord);

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
                    model.render(tower, partialTicks);
                    GL11.glDisable(GL11.GL_ALPHA_TEST);
                    GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glPopMatrix();
                }
            }

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();

            if (world != null)
            {
                TFRenderHelper.renderEnergyTransmissions(tower, x, y, z, partialTicks);
            }
        }
    }

    public ModelRelayTower getModel(TileEntityRelayTower tower)
    {
        if (tower instanceof TileEntityRelayTorch)
        {
            return modelTorch;
        }

        return modelTower;
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityRelayTower) tileentity, d, d1, d2, f);
    }
}
