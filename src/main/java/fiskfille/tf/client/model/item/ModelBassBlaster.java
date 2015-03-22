package fiskfille.tf.client.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBassBlaster extends ModelBase
{
    public ModelRenderer weaponbase;
    public ModelRenderer wepbody1;
    public ModelRenderer wepbody2;
    public ModelRenderer wepbody3;
    public ModelRenderer wepbody4;
    public ModelRenderer wepbody5;
    public ModelRenderer lowerflap1;
    public ModelRenderer upperflap1;
    public ModelRenderer vent1;
    public ModelRenderer vent2;
    public ModelRenderer vent3;
    public ModelRenderer wepbody6;
    public ModelRenderer cable1;
    public ModelRenderer cable2;
    public ModelRenderer lowerflap2;
    public ModelRenderer upperflap2;

    public ModelBassBlaster()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.upperflap2 = new ModelRenderer(this, 0, 24);
        this.upperflap2.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.upperflap2.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 1, 0.0F);
        this.setRotateAngle(upperflap2, 0.7330382858376184F, 0.0F, 0.0F);
        this.vent3 = new ModelRenderer(this, 0, 8);
        this.vent3.setRotationPoint(-3.0F, -1.0F, 0.4F);
        this.vent3.addBox(-1.6F, 0.0F, -0.3F, 2, 2, 1, 0.0F);
        this.setRotateAngle(vent3, 0.0F, -0.20943951023931953F, 0.0F);
        this.weaponbase = new ModelRenderer(this, 0, 0);
        this.weaponbase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.weaponbase.addBox(-1.2F, -1.0F, -3.1F, 2, 2, 1, 0.0F);
        this.lowerflap1 = new ModelRenderer(this, 0, 11);
        this.lowerflap1.setRotationPoint(-0.2F, 1.5F, -3.0F);
        this.lowerflap1.addBox(-1.0F, -1.2F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(lowerflap1, 0.8726646259971648F, 0.0F, 15.707963267948966F);
        this.vent2 = new ModelRenderer(this, 0, 8);
        this.vent2.setRotationPoint(-3.0F, -1.0F, 2.2F);
        this.vent2.addBox(-1.6F, 0.0F, -0.3F, 2, 2, 1, 0.0F);
        this.setRotateAngle(vent2, 0.0F, -0.20943951023931953F, 0.0F);
        this.lowerflap2 = new ModelRenderer(this, 0, 24);
        this.lowerflap2.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.lowerflap2.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 1, 0.0F);
        this.setRotateAngle(lowerflap2, 0.7330382858376184F, 0.0F, 0.0F);
        this.wepbody2 = new ModelRenderer(this, 0, 18);
        this.wepbody2.setRotationPoint(-1.7F, -1.5F, -3.0F);
        this.wepbody2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.cable1 = new ModelRenderer(this, 12, 0);
        this.cable1.setRotationPoint(0.4F, 3.0F, -1.5F);
        this.cable1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 5, 0.0F);
        this.setRotateAngle(cable1, 0.03490658503988659F, -0.06981317007977318F, -0.15707963267948966F);
        this.wepbody3 = new ModelRenderer(this, 10, 8);
        this.wepbody3.setRotationPoint(0.0F, -2.4F, -2.0F);
        this.wepbody3.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 5, 0.0F);
        this.setRotateAngle(wepbody3, 0.0F, 0.0F, -0.3141592653589793F);
        this.wepbody1 = new ModelRenderer(this, 0, 8);
        this.wepbody1.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.wepbody1.addBox(-0.5F, 0.0F, -2.0F, 2, 4, 6, 0.0F);
        this.wepbody4 = new ModelRenderer(this, 10, 8);
        this.wepbody4.setRotationPoint(0.0F, 2.4F, -2.0F);
        this.wepbody4.addBox(-3.0F, -1.0F, 0.0F, 3, 1, 5, 0.0F);
        this.setRotateAngle(wepbody4, 0.0F, 0.0F, 0.3141592653589793F);
        this.upperflap1 = new ModelRenderer(this, 0, 11);
        this.upperflap1.setRotationPoint(-0.2F, -1.5F, -3.0F);
        this.upperflap1.addBox(-1.0F, -1.2F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(upperflap1, 0.8726646259971648F, 0.0F, 0.0F);
        this.vent1 = new ModelRenderer(this, 0, 8);
        this.vent1.setRotationPoint(-3.0F, -1.0F, -1.6F);
        this.vent1.addBox(-1.6F, 0.0F, -0.3F, 2, 2, 1, 0.0F);
        this.setRotateAngle(vent1, 0.0F, -0.20943951023931953F, 0.0F);
        this.cable2 = new ModelRenderer(this, 12, 0);
        this.cable2.setRotationPoint(0.4F, -3.0F, -1.5F);
        this.cable2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
        this.setRotateAngle(cable2, -0.03490658503988659F, -0.06981317007977318F, 0.15707963267948966F);
        this.wepbody5 = new ModelRenderer(this, 0, 0);
        this.wepbody5.setRotationPoint(-3.0F, -1.5F, -2.0F);
        this.wepbody5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 5, 0.0F);
        this.wepbody6 = new ModelRenderer(this, 0, 0);
        this.wepbody6.setRotationPoint(1.5F, -1.5F, -2.0F);
        this.wepbody6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 5, 0.0F);
        this.upperflap1.addChild(this.upperflap2);
        this.weaponbase.addChild(this.vent3);
        this.weaponbase.addChild(this.lowerflap1);
        this.weaponbase.addChild(this.vent2);
        this.lowerflap1.addChild(this.lowerflap2);
        this.weaponbase.addChild(this.wepbody2);
        this.weaponbase.addChild(this.cable1);
        this.weaponbase.addChild(this.wepbody3);
        this.weaponbase.addChild(this.wepbody1);
        this.weaponbase.addChild(this.wepbody4);
        this.weaponbase.addChild(this.upperflap1);
        this.weaponbase.addChild(this.vent1);
        this.weaponbase.addChild(this.cable2);
        this.weaponbase.addChild(this.wepbody5);
        this.weaponbase.addChild(this.wepbody6);
    }
    
    public void render()
    { 
    	float f5 = 0.0625f;
        this.weaponbase.render(f5);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}