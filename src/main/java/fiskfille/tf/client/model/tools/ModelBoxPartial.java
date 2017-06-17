package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelBoxPartial extends ModelBox
{
    private PositionTextureVertex[] vertexPositions;
    private TexturedQuadPartial[] quadList;

    public float posX1;
    public float posY1;
    public float posZ1;
    public float posX2;
    public float posY2;
    public float posZ2;

    protected float width;
    protected float height;
    protected float depth;
    protected float scale;
    protected float textureX;
    protected float textureY;

    public ModelRendererPartial model;

    public ModelBoxPartial(ModelRendererPartial modelRenderer, int texX, int texY, float x, float y, float z, int w, int h, int d, float mcScale)
    {
        super(modelRenderer, texX, texY, x, y, z, w, h, d, mcScale);
        this.model = modelRenderer;
        this.textureX = texX;
        this.textureY = texY;
        this.scale = mcScale;

        this.setBounds(new AxisAlignedBB(x, y, z, x + w, y + h, z + d));
    }

    public AxisAlignedBB getBounds()
    {
        return new AxisAlignedBB(this.posX1, this.posY1, this.posZ1, this.posX2, this.posY2, this.posZ2);
    }

    public boolean setBounds(AxisAlignedBB aabb)
    {
        this.width = (float) (aabb.maxX - aabb.minX);
        this.height = (float) (aabb.maxY - aabb.minY);
        this.depth = (float) (aabb.maxZ - aabb.minZ);
        this.posX1 = (float) aabb.minX;
        this.posY1 = (float) aabb.minY;
        this.posZ1 = (float) aabb.minZ;
        this.posX2 = this.posX1 + this.width;
        this.posY2 = this.posY1 + this.height;
        this.posZ2 = this.posZ1 + this.depth;
        this.model.compiled = false;

        this.calculateQuads();

        return true;
    }

    public void calculateQuads()
    {
        this.vertexPositions = new PositionTextureVertex[8];
        this.quadList = new TexturedQuadPartial[6];
        float x1 = this.posX1;
        float y1 = this.posY1;
        float z1 = this.posZ1;
        float x2 = this.posX2;
        float y2 = this.posY2;
        float z2 = this.posZ2;
        x1 -= this.scale;
        y1 -= this.scale;
        z1 -= this.scale;
        x2 += this.scale;
        y2 += this.scale;
        z2 += this.scale;

        if (this.model.mirror)
        {
            float prevX2 = x2;
            x2 = x1;
            x1 = prevX2;
        }

        float offset = this.posY1 - super.posY1;
        PositionTextureVertex vertex1 = new PositionTextureVertex(x1, y1, z1, 0, 0);
        PositionTextureVertex vertex2 = new PositionTextureVertex(x2, y1, z1, 0, 8);
        PositionTextureVertex vertex3 = new PositionTextureVertex(x2, y2, z1, 8, 8);
        PositionTextureVertex vertex4 = new PositionTextureVertex(x1, y2, z1, 8, 0);
        PositionTextureVertex vertex5 = new PositionTextureVertex(x1, y1, z2, 0, 0);
        PositionTextureVertex vertex6 = new PositionTextureVertex(x2, y1, z2, 0, 8);
        PositionTextureVertex vertex7 = new PositionTextureVertex(x2, y2, z2, 8, 8);
        PositionTextureVertex vertex8 = new PositionTextureVertex(x1, y2, z2, 8, 0);
        this.vertexPositions[0] = vertex1;
        this.vertexPositions[1] = vertex2;
        this.vertexPositions[2] = vertex3;
        this.vertexPositions[3] = vertex4;
        this.vertexPositions[4] = vertex5;
        this.vertexPositions[5] = vertex6;
        this.vertexPositions[6] = vertex7;
        this.vertexPositions[7] = vertex8;
        this.quadList[0] = new TexturedQuadPartial(new PositionTextureVertex[] { vertex6, vertex2, vertex3, vertex7 }, this.textureX + this.depth + this.width, this.textureY + this.depth + offset, this.textureX + this.depth + this.width + this.depth, this.textureY + this.depth + offset + this.height, this.model.textureWidth, this.model.textureHeight);
        this.quadList[1] = new TexturedQuadPartial(new PositionTextureVertex[] { vertex1, vertex5, vertex8, vertex4 }, this.textureX, this.textureY + this.depth + offset, this.textureX + this.depth, this.textureY + this.depth + offset + this.height, this.model.textureWidth, this.model.textureHeight);
        this.quadList[2] = new TexturedQuadPartial(new PositionTextureVertex[] { vertex6, vertex5, vertex1, vertex2 }, this.textureX + this.depth, this.textureY, this.textureX + this.depth + this.width, this.textureY + this.depth, this.model.textureWidth, this.model.textureHeight);
        this.quadList[3] = new TexturedQuadPartial(new PositionTextureVertex[] { vertex3, vertex4, vertex8, vertex7 }, this.textureX + this.depth + this.width, this.textureY + this.depth, this.textureX + this.depth + this.width + this.width, this.textureY, this.model.textureWidth, this.model.textureHeight);
        this.quadList[4] = new TexturedQuadPartial(new PositionTextureVertex[] { vertex2, vertex1, vertex4, vertex3 }, this.textureX + this.depth, this.textureY + this.depth + offset, this.textureX + this.depth + this.width, this.textureY + this.depth + offset + this.height, this.model.textureWidth, this.model.textureHeight);
        this.quadList[5] = new TexturedQuadPartial(new PositionTextureVertex[] { vertex5, vertex6, vertex7, vertex8 }, this.textureX + this.depth + this.width + this.depth, this.textureY + this.depth + offset, this.textureX + this.depth + this.width + this.depth + this.width, this.textureY + this.depth + offset + this.height, this.model.textureWidth, this.model.textureHeight);

        if (this.model.mirror)
        {
            for (TexturedQuadPartial quad : this.quadList)
            {
                quad.flipFace();
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(VertexBuffer builder, float scale)
    {
        for (TexturedQuadPartial quad : this.quadList)
        {
            quad.draw(builder, scale);
        }
    }
}
