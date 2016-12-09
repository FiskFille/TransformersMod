package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModelBoxPartial extends ModelBox
{
    private PositionTextureVertex[] vertexPositions;
    private TexturedQuadPartial[] quadList;
    
    protected AxisAlignedBB bounds;
    protected float width;
    protected float height;
    protected float depth;
    protected float scale;
    protected int textureX;
    protected int textureY;
    
    public ModelRendererPartial model;
    
    public ModelBoxPartial(ModelRendererPartial modelRenderer, int texX, int texY, float x, float y, float z, int w, int h, int d, float mcScale)
    {
        super(modelRenderer, texX, texY, x, y, z, w, h, d, mcScale);
        model = modelRenderer;
        textureX = texX;
        textureY = texY;
        width = w;
        height = h;
        depth = d;
        scale = mcScale;
    }
    
    public boolean setBounds(AxisAlignedBB aabb)
    {
        boolean init = bounds == null;
        
        if (init || aabb.minX != bounds.minX || aabb.minY != bounds.minY || aabb.minZ != bounds.minZ || aabb.maxX != bounds.maxX || aabb.maxY != bounds.maxY || aabb.maxZ != bounds.maxZ)
        {
            bounds = aabb;
            width = (float) (aabb.maxX - aabb.minX);
            height = (float) (aabb.maxY - aabb.minY);
            depth = (float) (aabb.maxZ - aabb.minZ);
            
            calculateQuads();
            
            return true;
        }
        
        return false;
    }
    
    public void calculateQuads()
    {
        vertexPositions = new PositionTextureVertex[8];
        quadList = new TexturedQuadPartial[6];
        float x1 = (float) bounds.minX;
        float y1 = (float) bounds.minY;
        float z1 = (float) bounds.minZ;
        float x2 = x1 + width;
        float y2 = y1 + height;
        float z2 = z1 + depth;
        x1 -= scale;
        y1 -= scale;
        z1 -= scale;
        x2 += scale;
        y2 += scale;
        z2 += scale;

        if (model.mirror)
        {
            float prevX2 = x2;
            x2 = x1;
            x1 = prevX2;
        }

        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(x1, y1, z1, 0, 0);
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(x2, y1, z1, 0, 8);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(x2, y2, z1, 8, 8);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(x1, y2, z1, 8, 0);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(x1, y1, z2, 0, 0);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(x2, y1, z2, 0, 8);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(x2, y2, z2, 8, 8);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(x1, y2, z2, 8, 0);
        vertexPositions[0] = positiontexturevertex7;
        vertexPositions[1] = positiontexturevertex;
        vertexPositions[2] = positiontexturevertex1;
        vertexPositions[3] = positiontexturevertex2;
        vertexPositions[4] = positiontexturevertex3;
        vertexPositions[5] = positiontexturevertex4;
        vertexPositions[6] = positiontexturevertex5;
        vertexPositions[7] = positiontexturevertex6;
        quadList[0] = new TexturedQuadPartial(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, textureX + depth + width, textureY + depth, textureX + depth + width + depth, textureY + depth + height, model.textureWidth, model.textureHeight);
        quadList[1] = new TexturedQuadPartial(new PositionTextureVertex[] {positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, textureX, textureY + depth, textureX + depth, textureY + depth + height, model.textureWidth, model.textureHeight);
        quadList[2] = new TexturedQuadPartial(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, textureX + depth, textureY, textureX + depth + width, textureY + depth, model.textureWidth, model.textureHeight);
        quadList[3] = new TexturedQuadPartial(new PositionTextureVertex[] {positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, textureX + depth + width, textureY + depth, textureX + depth + width + width, textureY, model.textureWidth, model.textureHeight);
        quadList[4] = new TexturedQuadPartial(new PositionTextureVertex[] {positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, textureX + depth, textureY + depth, textureX + depth + width, textureY + depth + height, model.textureWidth, model.textureHeight);
        quadList[5] = new TexturedQuadPartial(new PositionTextureVertex[] {positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, textureX + depth + width + depth, textureY + depth, textureX + depth + width + depth + width, textureY + depth + height, model.textureWidth, model.textureHeight);

        if (model.mirror)
        {
            for (int i = 0; i < quadList.length; ++i)
            {
                quadList[i].flipFace();
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void render(Tessellator tessellator, float f)
    {
        for (int i = 0; i < quadList.length; ++i)
        {
            quadList[i].draw(tessellator, f);
        }
    }
}
