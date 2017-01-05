package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tessellator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModelBoxBreakable extends ModelBox
{
    private PositionTextureVertex[] vertexPositions;
    private TexturedQuad[] quadList;

    public ModelRendererBreakable model;

    public ModelBoxBreakable(ModelRendererBreakable modelRenderer, int textureX, int textureY, float x, float y, float z, int width, int height, int depth, float mcScale)
    {
        super(modelRenderer, textureX, textureY, x, y, z, width, height, depth, mcScale);
        model = modelRenderer;
        vertexPositions = new PositionTextureVertex[8];
        quadList = new TexturedQuad[6];

        int textureWidth = 16;
        int textureHeight = 16;
        float x2 = x + width;
        float y2 = y + height;
        float z2 = z + depth;
        x -= mcScale;
        y -= mcScale;
        z -= mcScale;
        x2 += mcScale;
        y2 += mcScale;
        z2 += mcScale;

        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(x, y, z, 0, 0);
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(x2, y, z, 0, 8);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(x2, y2, z, 8, 8);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(x, y2, z, 8, 0);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(x, y, z2, 0, 0);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(x2, y, z2, 0, 8);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(x2, y2, z2, 8, 8);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(x, y2, z2, 8, 0);
        vertexPositions[0] = positiontexturevertex7;
        vertexPositions[1] = positiontexturevertex;
        vertexPositions[2] = positiontexturevertex1;
        vertexPositions[3] = positiontexturevertex2;
        vertexPositions[4] = positiontexturevertex3;
        vertexPositions[5] = positiontexturevertex4;
        vertexPositions[6] = positiontexturevertex5;
        vertexPositions[7] = positiontexturevertex6;
        quadList[0] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, textureX + depth + width, textureY + depth, textureX + depth + width + depth, textureY + depth + height, textureWidth, textureHeight);
        quadList[1] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, textureX, textureY + depth, textureX + depth, textureY + depth + height, textureWidth, textureHeight);
        quadList[2] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, textureX + depth, textureY, textureX + depth + width, textureY + depth, textureWidth, textureHeight);
        quadList[3] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, textureX + depth + width, textureY + depth, textureX + depth + width + width, textureY, textureWidth, textureHeight);
        quadList[4] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, textureX + depth, textureY + depth, textureX + depth + width, textureY + depth + height, textureWidth, textureHeight);
        quadList[5] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, textureX + depth + width + depth, textureY + depth, textureX + depth + width + depth + width, textureY + depth + height, textureWidth, textureHeight);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(Tessellator tessellator, float f)
    {
        if (model.breaking)
        {
            for (int i = 0; i < quadList.length; ++i)
            {
                quadList[i].draw(tessellator, f);
            }
        }
        else
        {
            super.render(tessellator, f);
        }
    }
}
