package fiskfille.tf.helper;

import java.util.HashMap;
import java.util.Map;

import javax.vecmath.Vector4d;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.fluid.FluidTankTF;

public final class TFFluidRenderHelper
{
    private static Minecraft mc = Minecraft.getMinecraft();
    
    public static final int DISPLAY_STAGES = 100;
    private static Map<Fluid, int[]> flowingRenderCache = new HashMap<Fluid, int[]>();
    private static Map<Fluid, int[]> stillRenderCache = new HashMap<Fluid, int[]>();
    private static final RenderInfo liquidBlock = new RenderInfo();

    public static void onTextureStitch(TextureMap map)
    {
        for (int[] aint : flowingRenderCache.values())
        {
            for (int i : aint)
            {
                GL11.glDeleteLists(i, 1);
            }
        }

        flowingRenderCache.clear();

        for (int[] aint : stillRenderCache.values())
        {
            for (int i : aint)
            {
                GL11.glDeleteLists(i, 1);
            }
        }

        stillRenderCache.clear();
    }

    public static IIcon getFluidTexture(FluidStack fluidStack, boolean flowing)
    {
        if (fluidStack == null)
        {
            return null;
        }

        return getFluidTexture(fluidStack.getFluid(), flowing);
    }

    public static IIcon getFluidTexture(Fluid fluid, boolean flowing)
    {
        if (fluid == null)
        {
            return null;
        }

        IIcon icon = flowing ? fluid.getFlowingIcon() : fluid.getStillIcon();

        if (icon == null)
        {
            icon = ((TextureMap) mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
        }

        return icon;
    }

    public static void setColorForFluidStack(FluidStack fluidstack)
    {
        if (fluidstack == null)
        {
            return;
        }

        int color = fluidstack.getFluid().getColor(fluidstack);
        float[] afloat = TFRenderHelper.hexToRGB(color);
        GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
    }

    public static int[] getFluidDisplayLists(RenderBlocks renderBlocks, FluidStack fluidStack, World world, boolean flowing)
    {
        if (fluidStack == null)
        {
            return null;
        }

        Fluid fluid = fluidStack.getFluid();

        if (fluid == null)
        {
            return null;
        }

        Map<Fluid, int[]> cache = flowing ? flowingRenderCache : stillRenderCache;
        int[] diplayLists = cache.get(fluid);

        if (diplayLists != null)
        {
            return diplayLists;
        }

        diplayLists = new int[DISPLAY_STAGES];

        if (fluid.getBlock() != null)
        {
            liquidBlock.baseBlock = fluid.getBlock();
            liquidBlock.texture = getFluidTexture(fluidStack, flowing);
        }
        else
        {
            liquidBlock.baseBlock = Blocks.water;
            liquidBlock.texture = getFluidTexture(fluidStack, flowing);
        }

        cache.put(fluid, diplayLists);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        for (int stage = 0; stage < DISPLAY_STAGES; ++stage)
        {
            diplayLists[stage] = GLAllocation.generateDisplayLists(1);
            GL11.glNewList(diplayLists[stage], 4864 /* GL_COMPILE */);

            liquidBlock.minX = 0;
            liquidBlock.minY = 0;
            liquidBlock.minZ = 0;
            liquidBlock.maxX = 1;
            liquidBlock.maxY = (float) stage / (DISPLAY_STAGES - 1);
            liquidBlock.maxZ = 1;

            liquidBlock.renderBlock(renderBlocks);
            GL11.glEndList();
        }

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);

        return diplayLists;
    }
    
    public static void renderIntoGUI(FluidTankTF tank, int x, int y, int width, int height, float zLevel)
    {
        FluidStack stack = tank.getFluid();

        if (stack != null && stack.amount > 0)
        {
            Tessellator tessellator = Tessellator.instance;
            IIcon icon = stack.getFluid().getStillIcon();
            float f = (float) stack.amount / tank.getCapacity();
            
            Vector4d pos = new Vector4d(x, y, width, height);
            Vector4d tex = new Vector4d(icon.getMinU(), icon.getInterpolatedV(16 * (1 - f)), icon.getInterpolatedU(16 * (float) width / height), icon.getMaxV());
            pos.y += pos.w * (1 - f);
            pos.w *= f;
            pos.z += pos.x;
            pos.w += pos.y;
            
            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            TFFluidRenderHelper.setColorForFluidStack(stack);
            tessellator.startDrawingQuads();
            tessellator.setTranslation(0, 0, zLevel);
            tessellator.addVertexWithUV(pos.x, pos.w, 0, tex.x, tex.w);
            tessellator.addVertexWithUV(pos.z, pos.w, 0, tex.z, tex.w);
            tessellator.addVertexWithUV(pos.z, pos.y, 0, tex.z, tex.y);
            tessellator.addVertexWithUV(pos.x, pos.y, 0, tex.x, tex.y);
            tessellator.setTranslation(0, 0, 0);
            GL11.glEnable(GL11.GL_BLEND);
            tessellator.draw();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1, 1, 1, 1);
        }
    }
}
