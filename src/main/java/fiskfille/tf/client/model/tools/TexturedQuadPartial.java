package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

public class TexturedQuadPartial
{
    public PositionTextureVertex[] vertexPositions;
    public int nVertices;
    private boolean invertNormal;

    public TexturedQuadPartial(PositionTextureVertex[] vertices) 
    {
        vertexPositions = vertices;
        nVertices = vertices.length;
    }

    public TexturedQuadPartial(PositionTextureVertex[] vertices, double f, double f1, double f2, double f3, double width, double height) 
    {
        this(vertices);
        double f4 = 0.0F / width;
        double f5 = 0.0F / height;
        vertices[0] = vertices[0].setTexturePosition((float) ((float) f2 / width - f4), (float) ((float) f1 / height + f5));
        vertices[1] = vertices[1].setTexturePosition((float) ((float) f / width + f4), (float) ((float) f1 / height + f5));
        vertices[2] = vertices[2].setTexturePosition((float) ((float) f / width + f4), (float) ((float) f3 / height - f5));
        vertices[3] = vertices[3].setTexturePosition((float) ((float) f2 / width - f4), (float) ((float) f3 / height - f5));
    }

    public void flipFace() 
    {
        PositionTextureVertex[] vertex = new PositionTextureVertex[vertexPositions.length];

        for (int i = 0; i < vertexPositions.length; ++i) 
        {
            vertex[i] = vertexPositions[vertexPositions.length - i - 1];
        }

        vertexPositions = vertex;
    }

    public void draw(Tessellator tessellator, double f) 
    {
        Vec3 vec3 = vertexPositions[1].vector3D.subtract(vertexPositions[0].vector3D);
        Vec3 vec31 = vertexPositions[1].vector3D.subtract(vertexPositions[2].vector3D);
        Vec3 vec32 = vec31.crossProduct(vec3).normalize();
        tessellator.startDrawingQuads();

        if (invertNormal) 
        {
            tessellator.setNormal(-(float) vec32.xCoord, -(float) vec32.yCoord, -(float) vec32.zCoord);
        }
        else
        {
            tessellator.setNormal((float) vec32.xCoord, (float) vec32.yCoord, (float) vec32.zCoord);
        }

        for (int i = 0; i < 4; ++i) 
        {
            PositionTextureVertex vertex = vertexPositions[i];
            tessellator.addVertexWithUV((double) vertex.vector3D.xCoord * f, (double) vertex.vector3D.yCoord * f, (double) vertex.vector3D.zCoord * f, vertex.texturePositionX, vertex.texturePositionY);
        }

        tessellator.draw();
    }
}
