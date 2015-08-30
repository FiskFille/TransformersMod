package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.tileentity.ModelCrystal;
import fiskfille.tf.common.tileentity.TileEntityCrystal;

public class RenderCrystal extends TileEntitySpecialRenderer
{
    private ModelCrystal model;
    private ItemRenderer itemRenderer;
    
    public RenderCrystal()
    {
        model = new ModelCrystal();
        itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
    }
    
    public void renderAModelAt(TileEntityCrystal tile, double x, double y, double z, float partialTicks)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        adjustRotation(tile, x, y, z, partialTicks);
        
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(0.0F, 0.5F, 1.0F, 0.5F);
        
        char c0 = 61680;
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
        
        model.render();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
    
    public void adjustRotation(TileEntityCrystal tile, double x, double y, double z, float partialTicks)
    {
        int rot = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
                
        if (rot == 2)
        {
            GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
        }
        else if (rot == 3)
        {
            GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        }
        else if (rot == 4)
        {
            GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);
        }
        else if (rot == 6)
        {
            GL11.glTranslatef(0.0F, 2.0F, 0.0F);
            GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
        }

        if (rot != 5 && rot != 6)
        {
            GL11.glTranslatef(0.625F, 0.3F, 0.0F);
            GL11.glRotatef(55, 0.0F, 0.0F, 1.0F);
        }
        
        
//        GL11.glRotatef(((tile.xCoord + tile.yCoord + tile.zCoord) * 2) % 360, 0.0F, 1.0F, 0.0F);
    }
    
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTicks)
    {
        renderAModelAt((TileEntityCrystal) tile, x, y, z, partialTicks);
    }
}