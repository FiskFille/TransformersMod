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
    
    public ModelBoxBreakable(ModelRendererBreakable modelRenderer, int p_i1171_2_, int p_i1171_3_, float p_i1171_4_, float p_i1171_5_, float p_i1171_6_, int p_i1171_7_, int p_i1171_8_, int p_i1171_9_, float p_i1171_10_)
    {
        super(modelRenderer, p_i1171_2_, p_i1171_3_, p_i1171_4_, p_i1171_5_, p_i1171_6_, p_i1171_7_, p_i1171_8_, p_i1171_9_, p_i1171_10_);
        model = modelRenderer;
        
        int textureWidth = 16;
        int textureHeight = 16;
        vertexPositions = new PositionTextureVertex[8];
        quadList = new TexturedQuad[6];
        float f4 = p_i1171_4_ + (float)p_i1171_7_;
        float f5 = p_i1171_5_ + (float)p_i1171_8_;
        float f6 = p_i1171_6_ + (float)p_i1171_9_;
        p_i1171_4_ -= p_i1171_10_;
        p_i1171_5_ -= p_i1171_10_;
        p_i1171_6_ -= p_i1171_10_;
        f4 += p_i1171_10_;
        f5 += p_i1171_10_;
        f6 += p_i1171_10_;

        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(p_i1171_4_, p_i1171_5_, p_i1171_6_, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f4, p_i1171_5_, p_i1171_6_, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, f5, p_i1171_6_, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(p_i1171_4_, f5, p_i1171_6_, 8.0F, 0.0F);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(p_i1171_4_, p_i1171_5_, f6, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f4, p_i1171_5_, f6, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, f5, f6, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(p_i1171_4_, f5, f6, 8.0F, 0.0F);
        vertexPositions[0] = positiontexturevertex7;
        vertexPositions[1] = positiontexturevertex;
        vertexPositions[2] = positiontexturevertex1;
        vertexPositions[3] = positiontexturevertex2;
        vertexPositions[4] = positiontexturevertex3;
        vertexPositions[5] = positiontexturevertex4;
        vertexPositions[6] = positiontexturevertex5;
        vertexPositions[7] = positiontexturevertex6;
        quadList[0] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, p_i1171_2_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_ + p_i1171_9_, p_i1171_3_ + p_i1171_9_ + p_i1171_8_, textureWidth, textureHeight);
        quadList[1] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, p_i1171_2_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_, p_i1171_3_ + p_i1171_9_ + p_i1171_8_, textureWidth, textureHeight);
        quadList[2] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, p_i1171_2_ + p_i1171_9_, p_i1171_3_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_, textureWidth, textureHeight);
        quadList[3] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, p_i1171_2_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_ + p_i1171_7_, p_i1171_3_, textureWidth, textureHeight);
        quadList[4] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, p_i1171_2_ + p_i1171_9_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_ + p_i1171_8_, textureWidth, textureHeight);
        quadList[5] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, p_i1171_2_ + p_i1171_9_ + p_i1171_7_ + p_i1171_9_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_ + p_i1171_8_, textureWidth, textureHeight);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
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
