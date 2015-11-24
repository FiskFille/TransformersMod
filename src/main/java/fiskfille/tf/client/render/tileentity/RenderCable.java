package fiskfille.tf.client.render.tileentity;

import net.minecraft.block.BlockHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelCable;
import fiskfille.tf.common.tileentity.TileEntityCable;

public class RenderCable extends TileEntitySpecialRenderer
{
    private Minecraft mc = Minecraft.getMinecraft();
    private ModelCable.Straight modelStraight = new ModelCable.Straight();
    private ModelCable.Turned modelTurned = new ModelCable.Turned();

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
        
        bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/cable.png"));
        
        
//        int i = BlockHopper.getDirectionFromMetadata(metadata);
//        TileEntityCable cable = tileentity.getCable(tileentity.xCoord + Facing.offsetsXForSide[i], tileentity.yCoord + Facing.offsetsYForSide[i], tileentity.zCoord + Facing.offsetsZForSide[i]);
        
        boolean flag = true;
        int x1 = tileentity.xCoord;
        int y1 = tileentity.yCoord;
        int z1 = tileentity.zCoord;
//        boolean cablePosX = isCableAtConnectingTo(x1 + 1, y1, z1, tileentity);
//        boolean cableNegX = isCableAtConnectingTo(x1 - 1, y1, z1, tileentity);
//        boolean cablePosZ = isCableAtConnectingTo(x1, y1, z1 + 1, tileentity);
//        boolean cableNegZ = isCableAtConnectingTo(x1, y1, z1 - 1, tileentity);
        
        if (isCableAtConnectingTo(x1, y1, z1, tileentity.getCable(x1 - 1, y1, z1)))
        {
        	modelTurned.render(true);
        	flag = false;
        }
        
        if (flag)
        {
        	modelStraight.render(true);
        }
        
        

        GL11.glPopMatrix();
    }
    
    public boolean isCableAtConnectingTo(int x, int y, int z, TileEntityCable tileentity)
    {
    	if (tileentity != null)
    	{
    		TileEntityCable cable = tileentity.getCable(x, y, z);
        	
        	if (cable != null)
        	{
        		int i = BlockHopper.getDirectionFromMetadata(cable.getBlockMetadata());
        		TileEntityCable cable1 = cable.getCable(x + Facing.offsetsXForSide[i], y + Facing.offsetsYForSide[i], z + Facing.offsetsZForSide[i]);
        		    		
        		if (cable1 != null && cable1.xCoord == tileentity.xCoord && cable1.yCoord == tileentity.yCoord && cable1.zCoord == tileentity.zCoord)
        		{
        			return true;
        		}
        	}
    	}
    	
    	return false;
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
