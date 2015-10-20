package fiskfille.tf.client.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

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
        textureWidth = 64;
        textureHeight = 64;
        upperflap2 = new ModelRenderer(this, 0, 24);
        upperflap2.setRotationPoint(0.0F, -1.0F, 0.0F);
        upperflap2.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 1, 0.0F);
        setRotateAngle(upperflap2, 0.7330382858376184F, 0.0F, 0.0F);
        vent3 = new ModelRenderer(this, 0, 8);
        vent3.setRotationPoint(-3.0F, -1.0F, 0.4F);
        vent3.addBox(-1.6F, 0.0F, -0.3F, 2, 2, 1, 0.0F);
        setRotateAngle(vent3, 0.0F, -0.20943951023931953F, 0.0F);
        weaponbase = new ModelRenderer(this, 0, 0);
        weaponbase.setRotationPoint(0.0F, 0.0F, 0.0F);
        weaponbase.addBox(-1.2F, -1.0F, -3.1F, 2, 2, 1, 0.0F);
        lowerflap1 = new ModelRenderer(this, 0, 11);
        lowerflap1.setRotationPoint(-0.2F, 1.5F, -3.0F);
        lowerflap1.addBox(-1.0F, -1.2F, 0.0F, 2, 2, 1, 0.0F);
        setRotateAngle(lowerflap1, 0.8726646259971648F, 0.0F, 15.707963267948966F);
        vent2 = new ModelRenderer(this, 0, 8);
        vent2.setRotationPoint(-3.0F, -1.0F, 2.2F);
        vent2.addBox(-1.6F, 0.0F, -0.3F, 2, 2, 1, 0.0F);
        setRotateAngle(vent2, 0.0F, -0.20943951023931953F, 0.0F);
        lowerflap2 = new ModelRenderer(this, 0, 24);
        lowerflap2.setRotationPoint(0.0F, -1.0F, 0.0F);
        lowerflap2.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 1, 0.0F);
        setRotateAngle(lowerflap2, 0.7330382858376184F, 0.0F, 0.0F);
        wepbody2 = new ModelRenderer(this, 0, 18);
        wepbody2.setRotationPoint(-1.7F, -1.5F, -3.0F);
        wepbody2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        cable1 = new ModelRenderer(this, 12, 0);
        cable1.setRotationPoint(0.4F, 3.0F, -1.5F);
        cable1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 5, 0.0F);
        setRotateAngle(cable1, 0.03490658503988659F, -0.06981317007977318F, -0.15707963267948966F);
        wepbody3 = new ModelRenderer(this, 10, 8);
        wepbody3.setRotationPoint(0.0F, -2.4F, -2.0F);
        wepbody3.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 5, 0.0F);
        setRotateAngle(wepbody3, 0.0F, 0.0F, -0.3141592653589793F);
        wepbody1 = new ModelRenderer(this, 0, 8);
        wepbody1.setRotationPoint(0.0F, -2.0F, 0.0F);
        wepbody1.addBox(-0.5F, 0.0F, -2.0F, 2, 4, 6, 0.0F);
        wepbody4 = new ModelRenderer(this, 10, 8);
        wepbody4.setRotationPoint(0.0F, 2.4F, -2.0F);
        wepbody4.addBox(-3.0F, -1.0F, 0.0F, 3, 1, 5, 0.0F);
        setRotateAngle(wepbody4, 0.0F, 0.0F, 0.3141592653589793F);
        upperflap1 = new ModelRenderer(this, 0, 11);
        upperflap1.setRotationPoint(-0.2F, -1.5F, -3.0F);
        upperflap1.addBox(-1.0F, -1.2F, 0.0F, 2, 2, 1, 0.0F);
        setRotateAngle(upperflap1, 0.8726646259971648F, 0.0F, 0.0F);
        vent1 = new ModelRenderer(this, 0, 8);
        vent1.setRotationPoint(-3.0F, -1.0F, -1.6F);
        vent1.addBox(-1.6F, 0.0F, -0.3F, 2, 2, 1, 0.0F);
        setRotateAngle(vent1, 0.0F, -0.20943951023931953F, 0.0F);
        cable2 = new ModelRenderer(this, 12, 0);
        cable2.setRotationPoint(0.4F, -3.0F, -1.5F);
        cable2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
        setRotateAngle(cable2, -0.03490658503988659F, -0.06981317007977318F, 0.15707963267948966F);
        wepbody5 = new ModelRenderer(this, 0, 0);
        wepbody5.setRotationPoint(-3.0F, -1.5F, -2.0F);
        wepbody5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 5, 0.0F);
        wepbody6 = new ModelRenderer(this, 0, 0);
        wepbody6.setRotationPoint(1.5F, -1.5F, -2.0F);
        wepbody6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 5, 0.0F);
        upperflap1.addChild(upperflap2);
        weaponbase.addChild(vent3);
        weaponbase.addChild(lowerflap1);
        weaponbase.addChild(vent2);
        lowerflap1.addChild(lowerflap2);
        weaponbase.addChild(wepbody2);
        weaponbase.addChild(cable1);
        weaponbase.addChild(wepbody3);
        weaponbase.addChild(wepbody1);
        weaponbase.addChild(wepbody4);
        weaponbase.addChild(upperflap1);
        weaponbase.addChild(vent1);
        weaponbase.addChild(cable2);
        weaponbase.addChild(wepbody5);
        weaponbase.addChild(wepbody6);
    }

    public void render()
    {
        float f5 = 0.0625f;
        weaponbase.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}