package fiskfille.tf.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * ModelMissile - gegy1000
 * Created using Tabula 4.0.0
 */
public class ModelMissile extends ModelBase 
{
    public ModelRenderer Centre;
    public ModelRenderer BottomLeft;
    public ModelRenderer TopRight;
    public ModelRenderer TopLeft;
    public ModelRenderer BottomRight;
    public ModelRenderer Nosecone1;
    public ModelRenderer Nosecone2;
    public ModelRenderer Diagonal1;
    public ModelRenderer Diagonal2;

    public ModelMissile() 
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Centre = new ModelRenderer(this, 0, 20);
        this.Centre.setRotationPoint(0.0F, 22.0F, -5.0F);
        this.Centre.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 11);
        this.Nosecone2 = new ModelRenderer(this, 22, 0);
        this.Nosecone2.setRotationPoint(0.0F, 22.0F, -5.5F);
        this.Nosecone2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.setRotateAngle(Nosecone2, 0.0F, 0.0F, 0.7853981633974483F);
        this.Diagonal1 = new ModelRenderer(this, 25, 20);
        this.Diagonal1.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.Diagonal1.addBox(-0.5F, -1.0F, -5.0F, 1, 2, 10);
        this.setRotateAngle(Diagonal1, 0.0F, 0.0F, -0.7853981633974483F);
        this.Centre.addChild(this.Diagonal1);
        this.BottomRight = new ModelRenderer(this, 0, 0);
        this.BottomRight.setRotationPoint(-0.7F, 23.0F, 5.0F);
        this.BottomRight.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5);
        this.setRotateAngle(BottomRight, 0.0F, -0.17453292519943295F, -0.7853981633974483F);
        this.Diagonal2 = new ModelRenderer(this, 25, 20);
        this.Diagonal2.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.Diagonal2.addBox(-0.5F, -1.0F, -5.0F, 1, 2, 10);
        this.setRotateAngle(Diagonal2, 0.0F, 0.0F, 0.7853981633974483F);
        this.Centre.addChild(this.Diagonal2);
        this.TopRight = new ModelRenderer(this, 0, 0);
        this.TopRight.setRotationPoint(-0.7F, 21.1F, 5.0F);
        this.TopRight.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5);
        this.setRotateAngle(TopRight, 0.17453292519943295F, -0.17453292519943295F, -0.7853981633974483F);
        this.Nosecone1 = new ModelRenderer(this, 14, 0);
        this.Nosecone1.setRotationPoint(0.0F, 22.0F, -5.1F);
        this.Nosecone1.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1);
        this.setRotateAngle(Nosecone1, 0.0F, 0.0F, 0.7853981633974483F);
        this.BottomLeft = new ModelRenderer(this, 0, 0);
        this.BottomLeft.setRotationPoint(0.7F, 23.0F, 5.0F);
        this.BottomLeft.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5);
        this.setRotateAngle(BottomLeft, 0.0F, 0.17453292519943295F, 0.7853981633974483F);
        this.TopLeft = new ModelRenderer(this, 0, 0);
        this.TopLeft.setRotationPoint(0.7F, 21.1F, 5.0F);
        this.TopLeft.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5);
        this.setRotateAngle(TopLeft, 0.17453292519943295F, 0.17453292519943295F, 0.7853981633974483F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
    { 
        GL11.glPushMatrix();
        GL11.glTranslatef(this.Nosecone2.offsetX, this.Nosecone2.offsetY, this.Nosecone2.offsetZ);
        GL11.glTranslatef(this.Nosecone2.rotationPointX * f5, this.Nosecone2.rotationPointY * f5, this.Nosecone2.rotationPointZ * f5);
        GL11.glScaled(0.6D, 0.6D, 0.6D);
        GL11.glTranslatef(-this.Nosecone2.offsetX, -this.Nosecone2.offsetY, -this.Nosecone2.offsetZ);
        GL11.glTranslatef(-this.Nosecone2.rotationPointX * f5, -this.Nosecone2.rotationPointY * f5, -this.Nosecone2.rotationPointZ * f5);
        this.Nosecone2.render(f5);
        GL11.glPopMatrix();
        this.Centre.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.BottomRight.offsetX, this.BottomRight.offsetY, this.BottomRight.offsetZ);
        GL11.glTranslatef(this.BottomRight.rotationPointX * f5, this.BottomRight.rotationPointY * f5, this.BottomRight.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.BottomRight.offsetX, -this.BottomRight.offsetY, -this.BottomRight.offsetZ);
        GL11.glTranslatef(-this.BottomRight.rotationPointX * f5, -this.BottomRight.rotationPointY * f5, -this.BottomRight.rotationPointZ * f5);
        this.BottomRight.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.TopRight.offsetX, this.TopRight.offsetY, this.TopRight.offsetZ);
        GL11.glTranslatef(this.TopRight.rotationPointX * f5, this.TopRight.rotationPointY * f5, this.TopRight.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.TopRight.offsetX, -this.TopRight.offsetY, -this.TopRight.offsetZ);
        GL11.glTranslatef(-this.TopRight.rotationPointX * f5, -this.TopRight.rotationPointY * f5, -this.TopRight.rotationPointZ * f5);
        this.TopRight.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.Nosecone1.offsetX, this.Nosecone1.offsetY, this.Nosecone1.offsetZ);
        GL11.glTranslatef(this.Nosecone1.rotationPointX * f5, this.Nosecone1.rotationPointY * f5, this.Nosecone1.rotationPointZ * f5);
        GL11.glScaled(0.6D, 0.6D, 0.6D);
        GL11.glTranslatef(-this.Nosecone1.offsetX, -this.Nosecone1.offsetY, -this.Nosecone1.offsetZ);
        GL11.glTranslatef(-this.Nosecone1.rotationPointX * f5, -this.Nosecone1.rotationPointY * f5, -this.Nosecone1.rotationPointZ * f5);
        this.Nosecone1.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.BottomLeft.offsetX, this.BottomLeft.offsetY, this.BottomLeft.offsetZ);
        GL11.glTranslatef(this.BottomLeft.rotationPointX * f5, this.BottomLeft.rotationPointY * f5, this.BottomLeft.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.BottomLeft.offsetX, -this.BottomLeft.offsetY, -this.BottomLeft.offsetZ);
        GL11.glTranslatef(-this.BottomLeft.rotationPointX * f5, -this.BottomLeft.rotationPointY * f5, -this.BottomLeft.rotationPointZ * f5);
        this.BottomLeft.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.TopLeft.offsetX, this.TopLeft.offsetY, this.TopLeft.offsetZ);
        GL11.glTranslatef(this.TopLeft.rotationPointX * f5, this.TopLeft.rotationPointY * f5, this.TopLeft.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.TopLeft.offsetX, -this.TopLeft.offsetY, -this.TopLeft.offsetZ);
        GL11.glTranslatef(-this.TopLeft.rotationPointX * f5, -this.TopLeft.rotationPointY * f5, -this.TopLeft.rotationPointZ * f5);
        this.TopLeft.render(f5);
        GL11.glPopMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
