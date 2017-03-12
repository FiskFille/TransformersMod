package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelEnergyPort;
import fiskfille.tf.common.tileentity.TileEntityEnergyPort;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderEnergyPort extends TileEntitySpecialRenderer
{
    private ResourceLocation texture = new ResourceLocation(TransformersMod.modid, "textures/models/tiles/energy_port.png");
    private ResourceLocation textureOff = new ResourceLocation(TransformersMod.modid, "textures/models/tiles/energy_port_overlay_off.png");
    private ResourceLocation textureOn = new ResourceLocation(TransformersMod.modid, "textures/models/tiles/energy_port_overlay_on.png");
    private ModelEnergyPort model = new ModelEnergyPort();

    public void render(TileEntityEnergyPort tile, double x, double y, double z, float partialTicks)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        adjustRotation(tile, x, y, z, partialTicks);
        bindTexture(texture);
        model.setBreaking(false);
        model.render();
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        if (tile.getWorldObj() != null && tile.getEnergy() > 0)
        {
            bindTexture(textureOn);
            GL11.glDisable(GL11.GL_LIGHTING);
            TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
            model.render();
            TFRenderHelper.resetLighting();
            GL11.glEnable(GL11.GL_LIGHTING);
        }
        else
        {
            bindTexture(textureOff);
            model.render();
        }

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
                model.render();
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glPopMatrix();
            }
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
    
    public void adjustRotation(TileEntityEnergyPort tile, double x, double y, double z, float partialTicks)
    {
        int metadata = 0;
        
        if (tile.getWorldObj() != null)
        {
            metadata = tile.getBlockMetadata();
        }
        
        ForgeDirection dir = ForgeDirection.getOrientation(metadata);
        
        if (dir == ForgeDirection.UP)
        {
            GL11.glTranslatef(0, 1, 0);
            GL11.glRotatef(180, 0, 0, 1);
        }
        else if (dir == ForgeDirection.DOWN)
        {
            GL11.glTranslatef(0, -1, 0);
        }
        else
        {
            int[] rotations = {0, 2, 3, 1};
            
            GL11.glRotatef(90 * rotations[metadata - 2], 0, 1, 0);
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glTranslatef(0, -1, 0);
        }
        
        GL11.glTranslatef(0, -0.00103125F, 0);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityEnergyPort) tileentity, d, d1, d2, f);
    }
}
