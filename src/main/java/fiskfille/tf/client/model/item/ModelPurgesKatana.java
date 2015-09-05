package fiskfille.tf.client.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelPurgesKatana extends ModelBase
{
    ModelRenderer handle;
    ModelRenderer blade;
    ModelRenderer frontTip;
    ModelRenderer backTip;
    ModelRenderer wheel1;
    ModelRenderer wheel2;
    ModelRenderer wheel3;
    ModelRenderer wheel4;
    ModelRenderer wheel5;
    ModelRenderer wheel6;

    public ModelPurgesKatana()
    {
        textureWidth = 16;
        textureHeight = 32;

        handle = new ModelRenderer(this, 0, 17);
        handle.addBox(-0.5F, -5F, -0.5F, 1, 5, 1);
        handle.setRotationPoint(0F, 1F, 0F);
        handle.setTextureSize(16, 32);
        handle.mirror = true;
        setRotation(handle, 0F, 1.570796F, 0F);
        blade = new ModelRenderer(this, 0, 0);
        blade.addBox(-0.5F, -14F, -1.5F, 1, 14, 3);
        blade.setRotationPoint(0F, -4F, 0F);
        blade.setTextureSize(16, 32);
        blade.mirror = true;
        setRotation(blade, 0F, 1.570796F, 0F);
        frontTip = new ModelRenderer(this, 8, 0);
        frontTip.addBox(-0.5F, -1F, -1F, 1, 2, 2);
        frontTip.setRotationPoint(0F, -17.9F, 0F);
        frontTip.setTextureSize(16, 32);
        frontTip.mirror = true;
        setRotation(frontTip, -0.8552113F, 1.570796F, 0F);
        backTip = new ModelRenderer(this, 8, 4);
        backTip.addBox(-0.5F, -1.4F, 0.4F, 1, 2, 1);
        backTip.setRotationPoint(0F, -17.9F, 0F);
        backTip.setTextureSize(16, 32);
        backTip.mirror = true;
        setRotation(backTip, 0.1919862F, 1.570796F, 0F);
        wheel1 = new ModelRenderer(this, 8, 7);
        wheel1.addBox(-1F, -1F, -1F, 2, 2, 2);
        wheel1.setRotationPoint(0F, -4F, 0F);
        wheel1.setTextureSize(16, 32);
        wheel1.mirror = true;
        setRotation(wheel1, 0F, 1.570796F, 0F);
        wheel2 = new ModelRenderer(this, 8, 7);
        wheel2.addBox(-1F, -1F, -1F, 2, 2, 2);
        wheel2.setRotationPoint(0F, -6.5F, 0F);
        wheel2.setTextureSize(16, 32);
        wheel2.mirror = true;
        setRotation(wheel2, 0F, 1.570796F, 0F);
        wheel3 = new ModelRenderer(this, 8, 7);
        wheel3.addBox(-1F, -1F, -1F, 2, 2, 2);
        wheel3.setRotationPoint(0F, -9F, 0F);
        wheel3.setTextureSize(16, 32);
        wheel3.mirror = true;
        setRotation(wheel3, 0F, 1.570796F, 0F);
        wheel4 = new ModelRenderer(this, 8, 7);
        wheel4.addBox(-1F, -1F, -1F, 2, 2, 2);
        wheel4.setRotationPoint(0F, -11.5F, 0F);
        wheel4.setTextureSize(16, 32);
        wheel4.mirror = true;
        setRotation(wheel4, 0F, 1.570796F, 0F);
        wheel5 = new ModelRenderer(this, 8, 7);
        wheel5.addBox(-1F, -1F, -1F, 2, 2, 2);
        wheel5.setRotationPoint(0F, -14F, 0F);
        wheel5.setTextureSize(16, 32);
        wheel5.mirror = true;
        setRotation(wheel5, 0F, 1.570796F, 0F);
        wheel6 = new ModelRenderer(this, 8, 7);
        wheel6.addBox(-1F, -1F, -1F, 2, 2, 2);
        wheel6.setRotationPoint(0F, -16.5F, 0F);
        wheel6.setTextureSize(16, 32);
        wheel6.mirror = true;
        setRotation(wheel6, 0F, 1.570796F, 0F);
    }

    public void render()
    {
        float f = 1.0F;
        float f1 = f * 0.75F;
        GL11.glScalef(f, f, f);
        renderBlade(0.0625F);

        float f2 = 0.65F;
        GL11.glScalef(f, f, f2);
        renderWheels(0.0625F);
    }

    public void renderBlade(float f5)
    {
        handle.render(f5);
        blade.render(f5);
        frontTip.render(f5);
        backTip.render(f5);
    }

    public void renderWheels(float f5)
    {
        wheel1.render(f5);
        wheel2.render(f5);
        wheel3.render(f5);
        wheel4.render(f5);
        wheel5.render(f5);
        wheel6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}