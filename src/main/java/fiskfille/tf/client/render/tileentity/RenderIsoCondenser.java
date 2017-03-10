package fiskfille.tf.client.render.tileentity;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelIsoCondenser;
import fiskfille.tf.common.tileentity.TileEntityIsoCondenser;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderIsoCondenser extends TileEntitySpecialRenderer
{
    private ResourceLocation texture = new ResourceLocation(TransformersMod.modid, "textures/models/tiles/isotopic_condenser.png");
    private ResourceLocation textureLights = new ResourceLocation(TransformersMod.modid, "textures/models/tiles/isotopic_condenser_lights.png");
    private ModelIsoCondenser model = new ModelIsoCondenser();

    public void render(TileEntityIsoCondenser tile, double x, double y, double z, float partialTicks)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        bindTexture(texture);
        model.setBreaking(false);
        model.render(tile);

        bindTexture(textureLights);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
        model.render(tile);
        TFRenderHelper.resetLighting();
        GL11.glEnable(GL11.GL_LIGHTING);

        if (tile.getWorldObj() != null)
        {
            int progress = TFRenderHelper.getBlockDestroyProgress(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord);

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
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        for (Map.Entry<ForgeDirection, Block> e : tile.providers.entrySet())
        {
            ForgeDirection dir = e.getKey();
            Block block = e.getValue();
            float f = tile.animationTimer.get(dir) == null ? 0 : tile.animationTimer.get(dir);

            if (!block.isOpaqueCube() && f == 1)
            {
                block.setBlockBoundsBasedOnState(tile.getWorldObj(), tile.xCoord + dir.offsetX, tile.yCoord, tile.zCoord + dir.offsetZ);

                Vec3 src = Vec3.createVectorHelper(-dir.offsetX * 0.5F, 0.2F, -dir.offsetZ * 0.5F);
                Vec3 dst = Vec3.createVectorHelper(-dir.offsetX + 0.5F - (block.getBlockBoundsMinX() + block.getBlockBoundsMaxX()) / 2, 0.5F - (block.getBlockBoundsMinY() + block.getBlockBoundsMaxY()) / 2, -dir.offsetZ + 0.5F - (block.getBlockBoundsMinZ() + block.getBlockBoundsMaxZ()) / 2);
                TFRenderHelper.renderEnergyStatic(src, dst, 1F / 64, 0.5F, 8, e.getKey().hashCode());
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityIsoCondenser) tileentity, d, d1, d2, f);
    }
}
