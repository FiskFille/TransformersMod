package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelBoxBreakable extends ModelBox
{
    private PositionTextureVertex[] vertexPositions;
    private TexturedQuad[] quadList;

    public ModelRendererBreakable model;

    public ModelBoxBreakable(ModelRendererBreakable modelRenderer, int textureX, int textureY, float minX, float minY, float minZ, int width, int height, int depth, float mcScale)
    {
        super(modelRenderer, textureX, textureY, minX, minY, minZ, width, height, depth, mcScale);
        this.model = modelRenderer;
        this.vertexPositions = new PositionTextureVertex[8];
        this.quadList = new TexturedQuad[6];

        int textureWidth = 16;
        int textureHeight = 16;
        float maxX = minX + width;
        float maxY = minY + height;
        float maxZ = minZ + depth;
        minX -= mcScale;
        minY -= mcScale;
        minZ -= mcScale;
        maxX += mcScale;
        maxY += mcScale;
        maxZ += mcScale;

        PositionTextureVertex vertex7 = new PositionTextureVertex(minX, minY, minZ, 0, 0);
        PositionTextureVertex vertex = new PositionTextureVertex(maxX, minY, minZ, 0, 8);
        PositionTextureVertex vertex1 = new PositionTextureVertex(maxX, maxY, minZ, 8, 8);
        PositionTextureVertex vertex2 = new PositionTextureVertex(minX, maxY, minZ, 8, 0);
        PositionTextureVertex vertex3 = new PositionTextureVertex(minX, minY, maxZ, 0, 0);
        PositionTextureVertex vertex4 = new PositionTextureVertex(maxX, minY, maxZ, 0, 8);
        PositionTextureVertex vertex5 = new PositionTextureVertex(maxX, maxY, maxZ, 8, 8);
        PositionTextureVertex vertex6 = new PositionTextureVertex(minX, maxY, maxZ, 8, 0);
        this.vertexPositions[0] = vertex7;
        this.vertexPositions[1] = vertex;
        this.vertexPositions[2] = vertex1;
        this.vertexPositions[3] = vertex2;
        this.vertexPositions[4] = vertex3;
        this.vertexPositions[5] = vertex4;
        this.vertexPositions[6] = vertex5;
        this.vertexPositions[7] = vertex6;
        this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { vertex4, vertex, vertex1, vertex5 }, textureX + depth + width, textureY + depth, textureX + depth + width + depth, textureY + depth + height, textureWidth, textureHeight);
        this.quadList[1] = new TexturedQuad(new PositionTextureVertex[] { vertex7, vertex3, vertex6, vertex2 }, textureX, textureY + depth, textureX + depth, textureY + depth + height, textureWidth, textureHeight);
        this.quadList[2] = new TexturedQuad(new PositionTextureVertex[] { vertex4, vertex3, vertex7, vertex }, textureX + depth, textureY, textureX + depth + width, textureY + depth, textureWidth, textureHeight);
        this.quadList[3] = new TexturedQuad(new PositionTextureVertex[] { vertex1, vertex2, vertex6, vertex5 }, textureX + depth + width, textureY + depth, textureX + depth + width + width, textureY, textureWidth, textureHeight);
        this.quadList[4] = new TexturedQuad(new PositionTextureVertex[] { vertex, vertex7, vertex2, vertex1 }, textureX + depth, textureY + depth, textureX + depth + width, textureY + depth + height, textureWidth, textureHeight);
        this.quadList[5] = new TexturedQuad(new PositionTextureVertex[] { vertex3, vertex4, vertex5, vertex6 }, textureX + depth + width + depth, textureY + depth, textureX + depth + width + depth + width, textureY + depth + height, textureWidth, textureHeight);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(VertexBuffer builder, float scale)
    {
        if (this.model.breaking)
        {
            for (TexturedQuad quad : this.quadList)
            {
                quad.draw(builder, scale);
            }
        }
        else
        {
            super.render(builder, scale);
        }
    }
}
