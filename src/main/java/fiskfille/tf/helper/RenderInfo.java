package fiskfille.tf.helper;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RenderInfo
{
    public double minX = 0.0F;
    public double minY = 0.0F;
    public double minZ = 0.0F;
    public double maxX = 1.0F;
    public double maxY = 1.0F;
    public double maxZ = 1.0F;
    public Block baseBlock = Blocks.sand;
    public IIcon texture = null;
    public IIcon[] textureArray = null;
    public boolean[] renderSide = new boolean[] {true, true, true, true, true, true};
    public int light = -1;
    public int brightness = -1;

    public RenderInfo()
    {
    }

    public RenderInfo(Block template, IIcon[] texture)
    {
        this();
        this.baseBlock = template;
        this.textureArray = texture;
    }

    public RenderInfo(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
    {
        this();
        setBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public void setSkyBlockLight(World world, int x, int y, int z, int light)
    {
        this.brightness = world.getSkyBlockTypeBrightness(EnumSkyBlock.Sky, x, y, z) << 16 | light;
    }

    public float getBlockBrightness(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return baseBlock.getMixedBrightnessForBlock(iblockaccess, i, j, k);
    }

    public final void setBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)
    {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public final void setRenderSingleSide(int side)
    {
        Arrays.fill(renderSide, false);
        renderSide[side] = true;
    }

    public final void setRenderAllSides()
    {
        Arrays.fill(renderSide, true);
    }

    public void rotate()
    {
        double temp = minX;
        minX = minZ;
        minZ = temp;

        temp = maxX;
        maxX = maxZ;
        maxZ = temp;
    }

    public void reverseX()
    {
        double temp = minX;
        minX = 1 - maxX;
        maxX = 1 - temp;
    }

    public void reverseZ()
    {
        double temp = minZ;
        minZ = 1 - maxZ;
        maxZ = 1 - temp;
    }

    public IIcon getBlockTextureFromSide(int i)
    {
        if (texture != null)
        {
            return texture;
        }

        int index = i;

        if (textureArray == null || textureArray.length == 0)
        {
            return baseBlock.getBlockTextureFromSide(index);
        }
        else
        {
            if (index >= textureArray.length)
            {
                index = 0;
            }

            return textureArray[index];
        }
    }

    public void renderBlock(RenderBlocks renderBlocks)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();

        renderBlocks.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);

        if (light != -1)
        {
            tessellator.setBrightness(light << 20 | light << 4);
        }
        else if (brightness != -1)
        {
            tessellator.setBrightness(brightness << 4);
        }

        if (renderSide[0])
        {
            tessellator.setNormal(0, -1, 0);
            renderBlocks.renderFaceYNeg(baseBlock, 0, 0, 0, getBlockTextureFromSide(0));
        }
        if (renderSide[1])
        {
            tessellator.setNormal(0, 1, 0);
            renderBlocks.renderFaceYPos(baseBlock, 0, 0, 0, getBlockTextureFromSide(1));
        }
        if (renderSide[2])
        {
            tessellator.setNormal(0, 0, -1);
            renderBlocks.renderFaceZNeg(baseBlock, 0, 0, 0, getBlockTextureFromSide(2));
        }
        if (renderSide[3])
        {
            tessellator.setNormal(0, 0, 1);
            renderBlocks.renderFaceZPos(baseBlock, 0, 0, 0, getBlockTextureFromSide(3));
        }
        if (renderSide[4])
        {
            tessellator.setNormal(-1, 0, 0);
            renderBlocks.renderFaceXNeg(baseBlock, 0, 0, 0, getBlockTextureFromSide(4));
        }
        if (renderSide[5])
        {
            tessellator.setNormal(1, 0, 0);
            renderBlocks.renderFaceXPos(baseBlock, 0, 0, 0, getBlockTextureFromSide(5));
        }

        tessellator.draw();
    }
}
