package fiskfille.tf.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelMissile extends ModelBase
{
    public ModelRenderer centre;
    public ModelRenderer bottomL;
    public ModelRenderer topR;
    public ModelRenderer topL;
    public ModelRenderer bottomR;
    public ModelRenderer nosecone1;
    public ModelRenderer nosecone2;
    public ModelRenderer diagonal1;
    public ModelRenderer digonal2;
    
    public ModelMissile()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        this.centre = new ModelRenderer(this, 0, 20);
        this.centre.setRotationPoint(0.0F, 22.0F, -5.0F);
        this.centre.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 11);
        this.nosecone2 = new ModelRenderer(this, 22, 0);
        this.nosecone2.setRotationPoint(0.0F, 22.0F, -5.5F);
        this.nosecone2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.setRotateAngle(nosecone2, 0.0F, 0.0F, 0.7853981633974483F);
        this.diagonal1 = new ModelRenderer(this, 25, 20);
        this.diagonal1.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.diagonal1.addBox(-0.5F, -1.0F, -5.0F, 1, 2, 10);
        this.setRotateAngle(diagonal1, 0.0F, 0.0F, -0.7853981633974483F);
        this.centre.addChild(this.diagonal1);
        this.bottomR = new ModelRenderer(this, 0, 0);
        this.bottomR.setRotationPoint(-0.7F, 23.0F, 5.0F);
        this.bottomR.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5);
        this.setRotateAngle(bottomR, 0.0F, -0.17453292519943295F, -0.7853981633974483F);
        this.digonal2 = new ModelRenderer(this, 25, 20);
        this.digonal2.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.digonal2.addBox(-0.5F, -1.0F, -5.0F, 1, 2, 10);
        this.setRotateAngle(digonal2, 0.0F, 0.0F, 0.7853981633974483F);
        this.centre.addChild(this.digonal2);
        this.topR = new ModelRenderer(this, 0, 0);
        this.topR.setRotationPoint(-0.7F, 21.1F, 5.0F);
        this.topR.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5);
        this.setRotateAngle(topR, 0.17453292519943295F, -0.17453292519943295F, -0.7853981633974483F);
        this.nosecone1 = new ModelRenderer(this, 14, 0);
        this.nosecone1.setRotationPoint(0.0F, 22.0F, -5.1F);
        this.nosecone1.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1);
        this.setRotateAngle(nosecone1, 0.0F, 0.0F, 0.7853981633974483F);
        this.bottomL = new ModelRenderer(this, 0, 0);
        this.bottomL.setRotationPoint(0.7F, 23.0F, 5.0F);
        this.bottomL.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5);
        this.setRotateAngle(bottomL, 0.0F, 0.17453292519943295F, 0.7853981633974483F);
        this.topL = new ModelRenderer(this, 0, 0);
        this.topL.setRotationPoint(0.7F, 21.1F, 5.0F);
        this.topL.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5);
        this.setRotateAngle(topL, 0.17453292519943295F, 0.17453292519943295F, 0.7853981633974483F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(this.nosecone2.offsetX, this.nosecone2.offsetY, this.nosecone2.offsetZ);
        GL11.glTranslatef(this.nosecone2.rotationPointX * f5, this.nosecone2.rotationPointY * f5, this.nosecone2.rotationPointZ * f5);
        GL11.glScaled(0.6D, 0.6D, 0.6D);
        GL11.glTranslatef(-this.nosecone2.offsetX, -this.nosecone2.offsetY, -this.nosecone2.offsetZ);
        GL11.glTranslatef(-this.nosecone2.rotationPointX * f5, -this.nosecone2.rotationPointY * f5, -this.nosecone2.rotationPointZ * f5);
        this.nosecone2.render(f5);
        GL11.glPopMatrix();
        this.centre.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.bottomR.offsetX, this.bottomR.offsetY, this.bottomR.offsetZ);
        GL11.glTranslatef(this.bottomR.rotationPointX * f5, this.bottomR.rotationPointY * f5, this.bottomR.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.bottomR.offsetX, -this.bottomR.offsetY, -this.bottomR.offsetZ);
        GL11.glTranslatef(-this.bottomR.rotationPointX * f5, -this.bottomR.rotationPointY * f5, -this.bottomR.rotationPointZ * f5);
        this.bottomR.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.topR.offsetX, this.topR.offsetY, this.topR.offsetZ);
        GL11.glTranslatef(this.topR.rotationPointX * f5, this.topR.rotationPointY * f5, this.topR.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.topR.offsetX, -this.topR.offsetY, -this.topR.offsetZ);
        GL11.glTranslatef(-this.topR.rotationPointX * f5, -this.topR.rotationPointY * f5, -this.topR.rotationPointZ * f5);
        this.topR.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.nosecone1.offsetX, this.nosecone1.offsetY, this.nosecone1.offsetZ);
        GL11.glTranslatef(this.nosecone1.rotationPointX * f5, this.nosecone1.rotationPointY * f5, this.nosecone1.rotationPointZ * f5);
        GL11.glScaled(0.6D, 0.6D, 0.6D);
        GL11.glTranslatef(-this.nosecone1.offsetX, -this.nosecone1.offsetY, -this.nosecone1.offsetZ);
        GL11.glTranslatef(-this.nosecone1.rotationPointX * f5, -this.nosecone1.rotationPointY * f5, -this.nosecone1.rotationPointZ * f5);
        this.nosecone1.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.bottomL.offsetX, this.bottomL.offsetY, this.bottomL.offsetZ);
        GL11.glTranslatef(this.bottomL.rotationPointX * f5, this.bottomL.rotationPointY * f5, this.bottomL.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.bottomL.offsetX, -this.bottomL.offsetY, -this.bottomL.offsetZ);
        GL11.glTranslatef(-this.bottomL.rotationPointX * f5, -this.bottomL.rotationPointY * f5, -this.bottomL.rotationPointZ * f5);
        this.bottomL.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.topL.offsetX, this.topL.offsetY, this.topL.offsetZ);
        GL11.glTranslatef(this.topL.rotationPointX * f5, this.topL.rotationPointY * f5, this.topL.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.topL.offsetX, -this.topL.offsetY, -this.topL.offsetZ);
        GL11.glTranslatef(-this.topL.rotationPointX * f5, -this.topL.rotationPointY * f5, -this.topL.rotationPointZ * f5);
        this.topL.render(f5);
        GL11.glPopMatrix();
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
