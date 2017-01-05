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
        model = modelRenderer;
        textureX = texX;
        textureY = texY;
        scale = mcScale;

        setBounds(AxisAlignedBB.getBoundingBox(x, y, z, x + w, y + h, z + d));
    }

    public AxisAlignedBB getBounds()
    {
        return AxisAlignedBB.getBoundingBox(posX1, posY1, posZ1, posX2, posY2, posZ2);
    }

    public boolean setBounds(AxisAlignedBB aabb)
    {
//        if (aabb.minX != posX1 || aabb.minY != posY1 || aabb.minZ != posZ1 || aabb.maxX != posX2 || aabb.maxY != posY2 || aabb.maxZ != posZ2)
        {
            width = (float) (aabb.maxX - aabb.minX);
            height = (float) (aabb.maxY - aabb.minY);
            depth = (float) (aabb.maxZ - aabb.minZ);
            posX1 = (float) aabb.minX;
            posY1 = (float) aabb.minY;
            posZ1 = (float) aabb.minZ;
            posX2 = posX1 + width;
            posY2 = posY1 + height;
            posZ2 = posZ1 + depth;
            model.compiled = false;

            calculateQuads();

            return true;
        }

//        return false;
    }

    public void calculateQuads()
    {
        vertexPositions = new PositionTextureVertex[8];
        quadList = new TexturedQuadPartial[6];
        float x1 = posX1;
        float y1 = posY1;
        float z1 = posZ1;
        float x2 = posX2;
        float y2 = posY2;
        float z2 = posZ2;
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

        float offset = posY1 - super.posY1;
        PositionTextureVertex vertex1 = new PositionTextureVertex(x1, y1, z1, 0, 0);
        PositionTextureVertex vertex2 = new PositionTextureVertex(x2, y1, z1, 0, 8);
        PositionTextureVertex vertex3 = new PositionTextureVertex(x2, y2, z1, 8, 8);
        PositionTextureVertex vertex4 = new PositionTextureVertex(x1, y2, z1, 8, 0);
        PositionTextureVertex vertex5 = new PositionTextureVertex(x1, y1, z2, 0, 0);
        PositionTextureVertex vertex6 = new PositionTextureVertex(x2, y1, z2, 0, 8);
        PositionTextureVertex vertex7 = new PositionTextureVertex(x2, y2, z2, 8, 8);
        PositionTextureVertex vertex8 = new PositionTextureVertex(x1, y2, z2, 8, 0);
        vertexPositions[0] = vertex1;
        vertexPositions[1] = vertex2;
        vertexPositions[2] = vertex3;
        vertexPositions[3] = vertex4;
        vertexPositions[4] = vertex5;
        vertexPositions[5] = vertex6;
        vertexPositions[6] = vertex7;
        vertexPositions[7] = vertex8;
        quadList[0] = new TexturedQuadPartial(new PositionTextureVertex[] {vertex6, vertex2, vertex3, vertex7}, textureX + depth + width, textureY + depth + offset, textureX + depth + width + depth, textureY + depth + offset + height, model.textureWidth, model.textureHeight);
        quadList[1] = new TexturedQuadPartial(new PositionTextureVertex[] {vertex1, vertex5, vertex8, vertex4}, textureX, textureY + depth + offset, textureX + depth, textureY + depth + offset + height, model.textureWidth, model.textureHeight);
        quadList[2] = new TexturedQuadPartial(new PositionTextureVertex[] {vertex6, vertex5, vertex1, vertex2}, textureX + depth, textureY, textureX + depth + width, textureY + depth, model.textureWidth, model.textureHeight);
        quadList[3] = new TexturedQuadPartial(new PositionTextureVertex[] {vertex3, vertex4, vertex8, vertex7}, textureX + depth + width, textureY + depth, textureX + depth + width + width, textureY, model.textureWidth, model.textureHeight);
        quadList[4] = new TexturedQuadPartial(new PositionTextureVertex[] {vertex2, vertex1, vertex4, vertex3}, textureX + depth, textureY + depth + offset, textureX + depth + width, textureY + depth + offset + height, model.textureWidth, model.textureHeight);
        quadList[5] = new TexturedQuadPartial(new PositionTextureVertex[] {vertex5, vertex6, vertex7, vertex8}, textureX + depth + width + depth, textureY + depth + offset, textureX + depth + width + depth + width, textureY + depth + offset + height, model.textureWidth, model.textureHeight);

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
