package fiskfille.tf.client.render.tileentity;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

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
        
        boolean flag = true;
        int x1 = tileentity.xCoord;
        int y1 = tileentity.yCoord;
        int z1 = tileentity.zCoord;
        List<TileEntityCable> list = getCablesConnectingTo(tileentity);
        
        if (!list.isEmpty())
        {
        	TileEntityCable cable = list.get(0);
        	World world = tileentity.getWorldObj();
        	int dir = getDirection(cable);
        	int dir1 = getDirection(tileentity);
        	
        	if (dir != dir1)
        	{
        		if (dir == 0/* && world.getTileEntity(x1, y1 + (metadata == 0 ? -1 : 1), z1) == cable*/)
        		{
//        			if (world.getTileEntity(x1, y1, z1 - 1) == cable)
//        			{
//        				GL11.glRotatef(180, 0, 1, 0);
//        			}
//        			else if (world.getTileEntity(x1 - 1, y1, z1) == cable)
//        			{
//        				GL11.glRotatef(180, 0, 1, 0);
//        			}
        			
//        			GL11.glRotatef(180, 0, 1, 0);
        			
        			GL11.glTranslatef(-1, 1, 0);
        			GL11.glRotatef(-90, 0, 0, 1);
        			
        			
        		}
        		else if (metadata > 1 && dir == 1 && world.getTileEntity(x1, y1, z1 + (metadata == 4 ? -1 : 1)) == cable)
        		{
        			modelTurned.support2_1.rotateAngleY -= (float)Math.PI / 2;
        			modelTurned.cable2.rotateAngleY -= (float)Math.PI;
        		}
        		else if (metadata > 1 && dir == 2 && world.getTileEntity(x1 + (metadata == 3 ? -1 : 1), y1, z1) == cable)
        		{
        			modelTurned.support2_1.rotateAngleY -= (float)Math.PI / 2;
        			modelTurned.cable2.rotateAngleY -= (float)Math.PI;
        		}
        		
        		if (metadata == 0)
        		{
        			if (dir == 1)
        			{
        				if (world.getTileEntity(x1, y1, z1 + 1) == cable)
        				{
        					GL11.glTranslatef(-1, 1, 0);
        					GL11.glRotatef(-90, 0, 0, 1);
        				}
        				else if (world.getTileEntity(x1, y1, z1 - 1) == cable)
        				{
        					GL11.glTranslatef(1, 1, 0);
        					GL11.glRotatef(90, 0, 0, 1);
        				}
        			}
        			else if (dir == 2 && world.getTileEntity(x1 - 1, y1, z1) == cable)
        			{
        				GL11.glRotatef(90, 0, 1, 0);
        			}
        			
//        			GL11.glTranslatef(5, 0, 0);
        		}
        		
        		modelTurned.render(false);
        		flag = false;
        	}
        }
        
        if (flag)
        {
        	modelStraight.render(false);
        }
        
        
        
        
        
        
////        TileEntityCable cable = tileentity.getCable(tileentity.xCoord + Facing.offsetsXForSide[metadata], tileentity.yCoord + Facing.offsetsYForSide[metadata], tileentity.zCoord + Facing.offsetsZForSide[metadata]);
//        
//        boolean flag = true;
//        int x1 = tileentity.xCoord;
//        int y1 = tileentity.yCoord;
//        int z1 = tileentity.zCoord;
////        boolean cablePosX = isCableAtConnectingTo(x1 + 1, y1, z1, tileentity);
////        boolean cableNegX = isCableAtConnectingTo(x1 - 1, y1, z1, tileentity);
////        boolean cablePosZ = isCableAtConnectingTo(x1, y1, z1 + 1, tileentity);
////        boolean cableNegZ = isCableAtConnectingTo(x1, y1, z1 - 1, tileentity);
//        
//        if (isCableAtConnectingTo(x1, y1, z1, tileentity.getCable(x1 - 1, y1, z1)))
//        {
//        	modelTurned.render(true);
//        	flag = false;
//        }
//        
//        if (flag)
//        {
//        	modelStraight.render(true);
//        }
        
        

        GL11.glPopMatrix();
    }
    
    public boolean isCableAtConnectingTo(int x, int y, int z, TileEntityCable tileentity)
    {
    	if (tileentity != null)
    	{
    		TileEntityCable cable = tileentity.getCable(x, y, z);
        	
        	if (cable != null)
        	{
        		int i = cable.getBlockMetadata();
        		TileEntityCable cable1 = cable.getCable(x + Facing.offsetsXForSide[i], y + Facing.offsetsYForSide[i], z + Facing.offsetsZForSide[i]);
        		    		
        		if (cable1 != null && cable1.xCoord == tileentity.xCoord && cable1.yCoord == tileentity.yCoord && cable1.zCoord == tileentity.zCoord)
        		{
        			return true;
        		}
        	}
    	}
    	
    	return false;
    }
    
    public List<TileEntityCable> getCablesConnectingTo(TileEntityCable tileentity)
    {
    	List<TileEntityCable> list = Lists.newArrayList();
    	int x = tileentity.xCoord;
        int y = tileentity.yCoord;
        int z = tileentity.zCoord;
    	
        if (isCableAtConnectingTo(x + 1, y, z, tileentity)) {list.add(tileentity.getCable(x + 1, y, z));}
        if (isCableAtConnectingTo(x - 1, y, z, tileentity)) {list.add(tileentity.getCable(x - 1, y, z));}
        if (isCableAtConnectingTo(x, y + 1, z, tileentity)) {list.add(tileentity.getCable(x, y + 1, z));}
        if (isCableAtConnectingTo(x, y - 1, z, tileentity)) {list.add(tileentity.getCable(x, y - 1, z));}
        if (isCableAtConnectingTo(x, y, z + 1, tileentity)) {list.add(tileentity.getCable(x, y, z + 1));}
        if (isCableAtConnectingTo(x, y, z - 1, tileentity)) {list.add(tileentity.getCable(x, y, z - 1));}
        
    	return list;
    }
    
    public int getDirection(TileEntityCable tileentity)
    {
    	return tileentity.getBlockMetadata() / 2;
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
