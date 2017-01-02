package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.helper.TFFluidRenderHelper;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderEnergonTank extends TileEntitySpecialRenderer
{
    private RenderBlocks renderBlocks = RenderBlocks.getInstance();

    public void render(TileEntityEnergonTank tile, double x, double y, double z, float partialTicks)
    {
        World world = tile.getWorldObj();
        FluidStack stack = tile.tank.getFluid();
        
        if (stack == null || stack.getFluid() == null || stack.amount <= 0)
        {
            return;
        }
        
        int color = stack.getFluid().getColor(stack);
        int[] displayList = TFFluidRenderHelper.getFluidDisplayLists(renderBlocks, stack, tile.getWorldObj(), false);
        
        if (displayList == null)
        {
            return;
        }

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        bindTexture(TextureMap.locationBlocksTexture);
        float[] afloat = TFRenderHelper.hexToRGB(color);
        GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);

        float scale = 0.99F;
        float scaleY = scale;
        float scaleOffset = 0;
        
        if (world != null)
        {
            TileEntityEnergonTank tileBase = TFHelper.getTileBase(tile);
            boolean connectAbove = false;
            boolean connectBelow = false;
            
            if (tileBase == TFHelper.getTileBase(world.getTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord)))
            {
                connectAbove = true;
            }
            
            if (tileBase == TFHelper.getTileBase(world.getTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord)))
            {
                connectBelow = true;
            }
            
            if (connectAbove && connectBelow)
            {
                scaleY = 1;
            }
            else if (connectAbove)
            {
                float diff = (1 - scaleY) / 2;
                scaleY += diff;
                scaleOffset = 0.5F;
            }
            else if (connectBelow)
            {
                float diff = (1 - scaleY) / 2;
                scaleY += diff;
                scaleOffset = -0.5F;
            }
        }
        
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glTranslatef(0.5F, 0.5F + scaleOffset, 0.5F);
        GL11.glScalef(scale, scaleY, scale);
        GL11.glTranslatef(-0.5F, -0.5F - scaleOffset, -0.5F);

        int dl = (int) ((float) stack.amount / tile.tank.getCapacity() * (TFFluidRenderHelper.DISPLAY_STAGES - 1));
        GL11.glCallList(displayList[MathHelper.clamp_int(dl, 0, TFFluidRenderHelper.DISPLAY_STAGES - 1)]);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    @Override
    public void func_147496_a(World world)
    {
        renderBlocks = new RenderBlocks(world);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityEnergonTank) tileentity, d, d1, d2, f);
    }
}
