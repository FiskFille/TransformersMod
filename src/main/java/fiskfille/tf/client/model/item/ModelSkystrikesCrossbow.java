package fiskfille.tf.client.model.item;

import fiskfille.tf.client.model.transformer.ModelChildBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModelSkystrikesCrossbow extends ModelChildBase.Base
{
    ModelRenderer handle;
    ModelRenderer bottomEdge1;
    ModelRenderer bottomEdge2;
    ModelRenderer upperEdge1;
    ModelRenderer upperEdge2;
    ModelRenderer missile1;
    ModelRenderer missile2;
    ModelRenderer missile3;
    ModelRenderer missile4;
    ModelRenderer missile5;
    ModelRenderer missile6;
    ModelRenderer missile7;

    public ModelSkystrikesCrossbow()
    {
        textureWidth = 64;
        textureHeight = 64;

        handle = new ModelRenderer(this, 0, 0);
        handle.addBox(-1F, -1F, -9F, 2, 2, 15);
        handle.setRotationPoint(0F, -1F, 0F);
        handle.setTextureSize(64, 64);
        handle.mirror = true;
        setRotation(handle, 0F, 0F, 0F);
        bottomEdge1 = new ModelRenderer(this, 19, 7);
        bottomEdge1.addBox(-1F, 0F, -1F, 2, 5, 2);
        bottomEdge1.setRotationPoint(0F, -0.4F, -8F);
        bottomEdge1.setTextureSize(64, 64);
        bottomEdge1.mirror = true;
        setRotation(bottomEdge1, 0.4014257F, 0F, 0F);
        bottomEdge2 = new ModelRenderer(this, 19, 7);
        bottomEdge2.addBox(-1F, 0F, -1F, 2, 5, 2);
        bottomEdge2.setRotationPoint(0F, 3.8F, -6.5F);
        bottomEdge2.setTextureSize(64, 64);
        bottomEdge2.mirror = true;
        setRotation(bottomEdge2, 1.047198F, 0F, 0F);
        upperEdge1 = new ModelRenderer(this, 19, 0);
        upperEdge1.addBox(-1F, -5F, -1F, 2, 5, 2);
        upperEdge1.setRotationPoint(0F, -1.6F, -8F);
        upperEdge1.setTextureSize(64, 64);
        upperEdge1.mirror = true;
        setRotation(upperEdge1, -0.4014257F, 0F, 0F);
        upperEdge2 = new ModelRenderer(this, 27, 7);
        upperEdge2.addBox(-1F, -5F, -1F, 2, 5, 2);
        upperEdge2.setRotationPoint(0F, -5.8F, -6.5F);
        upperEdge2.setTextureSize(64, 64);
        upperEdge2.mirror = true;
        setRotation(upperEdge2, -1.047198F, 0F, 0F);
        missile1 = new ModelRenderer(this, 0, 0);
        missile1.addBox(-0.5F, -0.5F, -6F, 1, 1, 6);
        missile1.setRotationPoint(-1.5F, -1F, -1F);
        missile1.setTextureSize(64, 64);
        missile1.mirror = true;
        setRotation(missile1, 0F, 0F, 0F);
        missile2 = new ModelRenderer(this, 0, 0);
        missile2.addBox(-0.5F, -0.5F, -6F, 1, 1, 6);
        missile2.setRotationPoint(1.5F, -1F, -1F);
        missile2.setTextureSize(64, 64);
        missile2.mirror = true;
        setRotation(missile2, 0F, 0F, 0F);
        missile3 = new ModelRenderer(this, 0, 0);
        missile3.addBox(-0.5F, -0.5F, -6F, 1, 1, 6);
        missile3.setRotationPoint(0F, -1F, -5F);
        missile3.setTextureSize(64, 64);
        missile3.mirror = true;
        setRotation(missile3, 0F, 0F, 0F);
        missile4 = new ModelRenderer(this, 0, 0);
        missile4.addBox(-0.5F, -0.5F, -6F, 1, 1, 6);
        missile4.setRotationPoint(0F, 2F, -4F);
        missile4.setTextureSize(64, 64);
        missile4.mirror = true;
        setRotation(missile4, 0F, 0F, 0F);
        missile5 = new ModelRenderer(this, 0, 0);
        missile5.addBox(-0.5F, -0.5F, -6F, 1, 1, 6);
        missile5.setRotationPoint(0F, 5F, -2F);
        missile5.setTextureSize(64, 64);
        missile5.mirror = true;
        setRotation(missile5, 0F, 0F, 0F);
        missile6 = new ModelRenderer(this, 0, 0);
        missile6.addBox(-0.5F, -0.5F, -6F, 1, 1, 6);
        missile6.setRotationPoint(0F, -4F, -4F);
        missile6.setTextureSize(64, 64);
        missile6.mirror = true;
        setRotation(missile6, 0F, 0F, 0F);
        missile7 = new ModelRenderer(this, 0, 0);
        missile7.addBox(-0.5F, -0.5F, -6F, 1, 1, 6);
        missile7.setRotationPoint(0F, -7F, -2F);
        missile7.setTextureSize(64, 64);
        missile7.mirror = true;
        setRotation(missile7, 0F, 0F, 0F);

        addChildTo(bottomEdge1, handle);
        addChildTo(bottomEdge2, handle);
        addChildTo(upperEdge1, handle);
        addChildTo(upperEdge2, handle);
        addChildTo(missile1, handle);
        addChildTo(missile2, handle);
        addChildTo(missile3, handle);
        addChildTo(missile4, handle);
        addChildTo(missile5, handle);
        addChildTo(missile6, handle);
        addChildTo(missile7, handle);
    }

    public void render()
    {
        handle.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack itemstack = player.getHeldItem();

            if (itemstack != null)
            {
                boolean flag = itemstack.hasTagCompound() && itemstack.getTagCompound().getBoolean("blueMode");
                float pidb2 = pi / 2;
                if (!flag && handle.rotateAngleZ > 0.0F)
                {
                    handle.rotateAngleZ -= pidb2 / 10;
                }
                if (flag && handle.rotateAngleZ < pidb2)
                {
                    handle.rotateAngleZ += pidb2 / 10;
                }
            }
        }
    }
}