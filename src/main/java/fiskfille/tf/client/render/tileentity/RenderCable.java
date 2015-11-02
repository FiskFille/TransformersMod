package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.tileentity.ModelControlPanel;
import fiskfille.tf.common.tileentity.TileEntityCable;

public class RenderCable extends TileEntitySpecialRenderer
{
    private Minecraft mc = Minecraft.getMinecraft();
    private ModelControlPanel model = new ModelControlPanel();

    public void render(TileEntityCable tileentity, double x, double y, double z, float partialTicks)
    {
        int metadata = 0;

        if (tileentity.getWorldObj() != null)
        {
            metadata = tileentity.getBlockMetadata();
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        adjustRotation(metadata);
        
        
        ItemRenderer itemRenderer = new ItemRenderer(mc);
        GL11.glTranslatef(0, 1, 0);
        GL11.glScalef(1F, -1F, -1F);
        GL11.glRotatef(90, 1.0F, 0.0F, 0.0F);
        itemRenderer.renderItem(null, new ItemStack(Blocks.fence), 0);
        

        GL11.glPopMatrix();
    }
    
    public void adjustRotation(int metadata)
    {
    	if (metadata == 0)
    	{
    		GL11.glTranslatef(0, 1, -1);
    		GL11.glRotatef(90, 1, 0, 0);
    	}
    	else if (metadata == 1)
    	{
    		GL11.glTranslatef(0, 1, 1);
    		GL11.glRotatef(-90, 1, 0, 0);
    	}
    	else
    	{
    		GL11.glRotatef(90 * metadata, 0, 1, 0);
    		
    		if (metadata == 4 || metadata == 3)
    		{
    			GL11.glRotatef(90, 0, 1, 0);
    		}
    		else if (metadata == 5)
    		{
    			GL11.glRotatef(180, 0, 1, 0);
    		}
    	}
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityCable) tileentity, d, d1, d2, f);
    }
}
