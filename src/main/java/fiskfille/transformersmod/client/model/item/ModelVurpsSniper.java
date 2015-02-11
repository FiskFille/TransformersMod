package fiskfille.transformersmod.client.model.item;

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
    public ModelRenderer scopeholdA;
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
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.upperPiece1 = new ModelRenderer(this, 42, 17);
        this.upperPiece1.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.upperPiece1.addBox(-0.5F, -0.5F, -9.0F, 1, 1, 9, 0.0F);
        this.ammo = new ModelRenderer(this, 0, 16);
        this.ammo.setRotationPoint(0.0F, -0.2F, -3.0F);
        this.ammo.addBox(-0.5F, -0.7F, 0.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(ammo, -0.5410520681182421F, 0.0F, 0.0F);
        this.scopeholdA = new ModelRenderer(this, 24, 5);
        this.scopeholdA.setRotationPoint(1.5F, -2.0F, 3.0F);
        this.scopeholdA.addBox(-0.5F, -1.5F, -3.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(scopeholdA, 0.0F, 0.0F, -0.6981317007977318F);
        this.bottomPiece1 = new ModelRenderer(this, 0, 17);
        this.bottomPiece1.setRotationPoint(-1.0F, -0.3F, 0.0F);
        this.bottomPiece1.addBox(-0.5F, -0.5F, -9.0F, 1, 1, 9, 0.0F);
        this.setRotateAngle(bottomPiece1, 0.0F, 0.0F, 1.0471975511965976F);
        this.monitorB = new ModelRenderer(this, 0, 11);
        this.monitorB.setRotationPoint(0.0F, -4.3F, -4.2F);
        this.monitorB.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(monitorB, -0.08936085770210968F, 0.0F, 0.0F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(0.0F, -0.45F, 3.0F);
        this.handle.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(handle, 0.4553564018453205F, 0.0F, 0.0F);
        this.monitorA = new ModelRenderer(this, 12, 11);
        this.monitorA.setRotationPoint(0.0F, -4.05F, -1.4F);
        this.monitorA.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.backPiece2 = new ModelRenderer(this, 11, 0);
        this.backPiece2.setRotationPoint(0.0F, -1.4F, 8.0F);
        this.backPiece2.addBox(-0.5F, -1.0F, 0.0F, 1, 3, 2, 0.0F);
        this.scopeholdB = new ModelRenderer(this, 24, 5);
        this.scopeholdB.setRotationPoint(1.5F, -2.0F, 3.0F);
        this.scopeholdB.addBox(-0.5F, -1.5F, -6.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(scopeholdB, 0.0F, 0.0F, -0.6981317007977318F);
        this.bodypart2 = new ModelRenderer(this, 11, 17);
        this.bodypart2.setRotationPoint(0.0F, -0.6F, -4.8F);
        this.bodypart2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(bodypart2, 0.0F, 0.0F, 0.7853981633974483F);
        this.scope = new ModelRenderer(this, 24, 8);
        this.scope.setRotationPoint(1.5F, -2.0F, 3.0F);
        this.scope.addBox(-0.5F, -0.5F, -7.0F, 1, 1, 7, 0.0F);
        this.setRotateAngle(scope, 0.0F, 0.0F, -0.6981317007977318F);
        this.middlePiece = new ModelRenderer(this, 0, 0);
        this.middlePiece.setRotationPoint(0.0F, -1.0F, 5.0F);
        this.middlePiece.addBox(-1.0F, -1.0F, -7.0F, 2, 2, 7, 0.0F);
        this.muzzle = new ModelRenderer(this, 26, 0);
        this.muzzle.setRotationPoint(0.0F, -1.5F, -2.0F);
        this.muzzle.addBox(-1.0F, -1.0F, -16.0F, 2, 3, 2, 0.0F);
        this.backPiece1 = new ModelRenderer(this, 14, 2);
        this.backPiece1.setRotationPoint(0.0F, -1.5F, 5.0F);
        this.backPiece1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.bottomPiece2 = new ModelRenderer(this, 0, 17);
        this.bottomPiece2.setRotationPoint(1.0F, -0.3F, 0.0F);
        this.bottomPiece2.addBox(-0.5F, -0.5F, -9.0F, 1, 1, 9, 0.0F);
        this.setRotateAngle(bottomPiece2, 0.0F, 0.0F, -1.0471975511965976F);
        this.barrellower = new ModelRenderer(this, 11, 5);
        this.barrellower.setRotationPoint(0.0F, -1.5F, -2.0F);
        this.barrellower.addBox(-0.5F, 0.8F, -14.0F, 1, 1, 11, 0.0F);
        this.barrel = new ModelRenderer(this, 6, 0);
        this.barrel.setRotationPoint(0.0F, -1.5F, -2.0F);
        this.barrel.addBox(-0.5F, -0.5F, -17.0F, 1, 1, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
    {
        this.upperPiece1.render(f5);
        this.ammo.render(f5);
        this.scopeholdA.render(f5);
        this.bottomPiece1.render(f5);
        this.monitorB.render(f5);
        this.handle.render(f5);
        this.monitorA.render(f5);
        this.backPiece2.render(f5);
        this.scopeholdB.render(f5);
        this.bodypart2.render(f5);
        this.scope.render(f5);
        this.middlePiece.render(f5);
        this.muzzle.render(f5);
        this.backPiece1.render(f5);
        this.bottomPiece2.render(f5);
        this.barrellower.render(f5);
        this.barrel.render(f5);
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

	public void render()
	{
		float f5 = 0.0625F;
		
		ammo.render(f5);
		backPiece1.render(f5);
		backPiece2.render(f5);
		barrel.render(f5);
		barrellower.render(f5);
		bodypart2.render(f5);
		bottomPiece1.render(f5);
		bottomPiece2.render(f5);
		handle.render(f5);
		middlePiece.render(f5);
		monitorA.render(f5);
		monitorB.render(f5);
		muzzle.render(f5);
		scope.render(f5);
		scopeholdA.render(f5);
		scopeholdB.render(f5);
		upperPiece1.render(f5);
	}
}
