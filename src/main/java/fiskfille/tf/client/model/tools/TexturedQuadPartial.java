package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class TexturedQuadPartial
{
    public PositionTextureVertex[] vertexPositions;
    public int nVertices;
    private boolean invertNormal;

    public TexturedQuadPartial(PositionTextureVertex[] vertices)
    {
        this.vertexPositions = vertices;
        this.nVertices = vertices.length;
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
        PositionTextureVertex[] vertex = new PositionTextureVertex[this.vertexPositions.length];

        for (int i = 0; i < this.vertexPositions.length; ++i)
        {
            vertex[i] = this.vertexPositions[this.vertexPositions.length - i - 1];
        }

        this.vertexPositions = vertex;
    }

    public void draw(VertexBuffer builder, double scale)
    {
        Vec3d vec3d = this.vertexPositions[1].vector3D.subtractReverse(this.vertexPositions[0].vector3D);
        Vec3d vec3d1 = this.vertexPositions[1].vector3D.subtractReverse(this.vertexPositions[2].vector3D);
        Vec3d normal = vec3d1.crossProduct(vec3d).normalize();

        float normalX = (float) normal.x;
        float normalY = (float) normal.y;
        float normalZ = (float) normal.z;

        if (this.invertNormal)
        {
            normalX = -normalX;
            normalY = -normalY;
            normalZ = -normalZ;
        }

        builder.begin(GL11.GL_QUADS, DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL);

        for (int i = 0; i < 4; ++i)
        {
            PositionTextureVertex vertex = this.vertexPositions[i];
            builder.pos(vertex.vector3D.x * scale, vertex.vector3D.y * scale, vertex.vector3D.z * scale).tex(vertex.texturePositionX, vertex.texturePositionY).normal(normalX, normalY, normalZ).endVertex();
        }

        Tessellator.getInstance().draw();
    }
}
