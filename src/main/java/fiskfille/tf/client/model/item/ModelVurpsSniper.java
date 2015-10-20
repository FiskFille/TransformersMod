package fiskfille.tf.client.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVurpsSniper extends ModelBase
{
    public ModelRenderer middlePiece;
    public ModelRenderer handle;
    public ModelRenderer backPiece1;
    public ModelRenderer backPiece2;
    public ModelRenderer bottomPiece1;
    public ModelRenderer upperPiece1;
    public ModelRenderer bottomPiece2;
    public ModelRenderer muzzle;
    public ModelRenderer scopeholda;
    public ModelRenderer bodypart2;
    public ModelRenderer barrellower;
    public ModelRenderer barrel;
    public ModelRenderer monitorA;
    public ModelRenderer monitorB;
    public ModelRenderer scope;
    public ModelRenderer scopeholdB;
    public ModelRenderer ammo;

    public ModelVurpsSniper()
    {
        textureWidth = 64;
        textureHeight = 32;
        backPiece2 = new ModelRenderer(this, 11, 0);
        backPiece2.setRotationPoint(0.0F, -1.4F, 8.0F);
        backPiece2.addBox(-0.5F, -1.0F, -0.5F, 1, 3, 2);
        bodypart2 = new ModelRenderer(this, 11, 17);
        bodypart2.setRotationPoint(0.0F, -0.6F, -4.8F);
        bodypart2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4);
        setRotateAngle(bodypart2, 0.0F, 0.0F, 0.7853981633974483F);
        monitorA = new ModelRenderer(this, 12, 11);
        monitorA.setRotationPoint(0.0F, -4.05F, -1.4F);
        monitorA.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 3);
        muzzle = new ModelRenderer(this, 26, 0);
        muzzle.setRotationPoint(0.0F, -1.5F, -2.0F);
        muzzle.addBox(-1.0F, -1.0F, -11.0F, 2, 3, 2);
        scopeholdB = new ModelRenderer(this, 24, 5);
        scopeholdB.setRotationPoint(1.5F, -2.0F, 3.0F);
        scopeholdB.addBox(-0.5F, -1.5F, -6.0F, 1, 1, 1);
        setRotateAngle(scopeholdB, 0.0F, 0.0F, -0.6981317007977318F);
        bottomPiece1 = new ModelRenderer(this, 0, 17);
        bottomPiece1.setRotationPoint(-1.0F, -0.3F, 0.0F);
        bottomPiece1.addBox(-0.5F, -0.5F, -9.0F, 1, 1, 9);
        setRotateAngle(bottomPiece1, 0.0F, 0.0F, 1.0471975511965976F);
        scope = new ModelRenderer(this, 24, 8);
        scope.setRotationPoint(1.5F, -2.0F, 3.0F);
        scope.addBox(-0.5F, -0.5F, -7.0F, 1, 1, 7);
        setRotateAngle(scope, 0.0F, 0.0F, -0.6981317007977318F);
        barrellower = new ModelRenderer(this, 11, 5);
        barrellower.setRotationPoint(0.0F, -1.5F, -2.0F);
        barrellower.addBox(-0.5F, 0.8F, -10.0F, 1, 1, 11);
        upperPiece1 = new ModelRenderer(this, 42, 17);
        upperPiece1.setRotationPoint(0.0F, -2.0F, 0.0F);
        upperPiece1.addBox(-0.5F, -0.5F, -9.0F, 1, 1, 9);
        scopeholda = new ModelRenderer(this, 24, 5);
        scopeholda.setRotationPoint(1.5F, -2.0F, 3.0F);
        scopeholda.addBox(-0.5F, -1.5F, -3.0F, 1, 1, 1);
        setRotateAngle(scopeholda, 0.0F, 0.0F, -0.6981317007977318F);
        ammo = new ModelRenderer(this, 0, 16);
        ammo.setRotationPoint(0.0F, 0.3F, -3.0F);
        ammo.addBox(-0.5F, -0.7F, 0.0F, 1, 3, 2);
        setRotateAngle(ammo, -0.2617993877991494F, 0.0F, 0.0F);
        barrel = new ModelRenderer(this, 6, 0);
        barrel.setRotationPoint(0.0F, -1.5F, -3.0F);
        barrel.addBox(-0.5F, -0.5F, -11.0F, 1, 1, 16);
        monitorB = new ModelRenderer(this, 0, 11);
        monitorB.setRotationPoint(0.0F, -4.3F, -4.2F);
        monitorB.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 3);
        setRotateAngle(monitorB, -0.08936085770210968F, 0.0F, 0.0F);
        middlePiece = new ModelRenderer(this, 0, 0);
        middlePiece.setRotationPoint(0.0F, -1.0F, 5.0F);
        middlePiece.addBox(-1.0F, -1.0F, -7.0F, 2, 2, 7);
        bottomPiece2 = new ModelRenderer(this, 0, 17);
        bottomPiece2.setRotationPoint(1.0F, -0.3F, 0.0F);
        bottomPiece2.addBox(-0.5F, -0.5F, -9.0F, 1, 1, 9);
        setRotateAngle(bottomPiece2, 0.0F, 0.0F, -1.0471975511965976F);
        handle = new ModelRenderer(this, 0, 0);
        handle.setRotationPoint(0.0F, -0.45F, 3.0F);
        handle.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2);
        setRotateAngle(handle, 0.4553564018453205F, 0.0F, 0.0F);
        backPiece1 = new ModelRenderer(this, 14, 2);
        backPiece1.setRotationPoint(0.0F, -1.4F, 5.0F);
        backPiece1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        backPiece2.render(f5);
        bodypart2.render(f5);
        monitorA.render(f5);
        muzzle.render(f5);
        scopeholdB.render(f5);
        bottomPiece1.render(f5);
        scope.render(f5);
        barrellower.render(f5);
        upperPiece1.render(f5);
        scopeholda.render(f5);
        ammo.render(f5);
        barrel.render(f5);
        monitorB.render(f5);
        middlePiece.render(f5);
        bottomPiece2.render(f5);
        handle.render(f5);
        backPiece1.render(f5);
    }

    public void render()
    {
        float f5 = 0.0625F;

        backPiece2.render(f5);
        bodypart2.render(f5);
        monitorA.render(f5);
        muzzle.render(f5);
        scopeholdB.render(f5);
        bottomPiece1.render(f5);
        scope.render(f5);
        barrellower.render(f5);
        upperPiece1.render(f5);
        scopeholda.render(f5);
        ammo.render(f5);
        barrel.render(f5);
        monitorB.render(f5);
        middlePiece.render(f5);
        bottomPiece2.render(f5);
        handle.render(f5);
        backPiece1.render(f5);
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
