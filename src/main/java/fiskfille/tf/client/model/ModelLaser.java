package fiskfille.tf.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelLaser extends ModelBase
{
    public ModelRenderer laserInner;
    public ModelRenderer laserOuter;

    public ModelLaser()
    {
        textureWidth = 64;
        textureHeight = 16;
        laserInner = new ModelRenderer(this, 0, 0);
        laserInner.setRotationPoint(0.0F, 0.0F, 0.0F);
        laserInner.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 7);
        laserOuter = new ModelRenderer(this, 17, 0);
        laserOuter.setRotationPoint(0.0F, 0.0F, 0.0F);
        laserOuter.addBox(-1.0F, -1.0F, -4.0F, 2, 2, 8);
        laserInner.addChild(laserOuter);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        GL11.glPushMatrix();

        laserInner.render(f5);

        GL11.glPopMatrix();
    }
}
