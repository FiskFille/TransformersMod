package fiskfille.tf.client.model.transformer.vehicle;

import net.minecraft.client.model.ModelRenderer;

public class ModelPurgeVehicle extends ModelVehicleBase
{
    public ModelRenderer vehicleTread1;
    public ModelRenderer vehicleTread2;
    public ModelRenderer vehicleFoot1;
    public ModelRenderer vehicleFoot2;
    public ModelRenderer vehicleBody;
    public ModelRenderer vehicleTrackcover2;
    public ModelRenderer vehicleTrackcover1;
    public ModelRenderer vehicleRearPlate;
    public ModelRenderer vehicleBodyFront;
    public ModelRenderer vehicleBack2;
    public ModelRenderer vehicleBack1;
    public ModelRenderer vehicleTurret;
    public ModelRenderer vehicleBodyplate;
    public ModelRenderer vehicleTurretFront;
    public ModelRenderer vehicleTurretSights;
    public ModelRenderer vehicleGun;
    public ModelRenderer vehicleTurretRear;
    public ModelRenderer vehicleMissileLauncher;
    
    public ModelPurgeVehicle()
    {
        textureWidth = 128;
        textureHeight = 128;
        
        this.vehicleTread2 = new ModelRenderer(this, 0, 64);
        this.vehicleTread2.setRotationPoint(4.1F, 0.0F, 4.0F);
        this.vehicleTread2.addBox(0.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
        this.vehicleBack2 = new ModelRenderer(this, 23, 102);
        this.vehicleBack2.setRotationPoint(2.5F, -6.5F, 3.8F);
        this.vehicleBack2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        this.vehicleBodyFront = new ModelRenderer(this, 39, 83);
        this.vehicleBodyFront.setRotationPoint(0.0F, -5.0F, -5.5F);
        this.vehicleBodyFront.addBox(-4.0F, 0.0F, -3.0F, 8, 4, 3, 0.0F);
        this.setRotation(vehicleBodyFront, 0.21467549799530256F, 0.0F, 0.0F);
        this.vehicleTurretFront = new ModelRenderer(this, 45, 97);
        this.vehicleTurretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleTurretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
        this.setRotation(vehicleTurretFront, -0.4974188368183839F, 0.0F, 0.0F);
        this.vehicleFoot1 = new ModelRenderer(this, 0, 64);
        this.vehicleFoot1.setRotationPoint(-5.0F, -1.5F, -9.2F);
        this.vehicleFoot1.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
        this.setRotation(vehicleFoot1, -0.03490658503988659F, -0.0F, 0.03490658503988659F);
        this.vehicleBodyplate = new ModelRenderer(this, 0, 86);
        this.vehicleBodyplate.setRotationPoint(-4.0F, -5.0F, -5.5F);
        this.vehicleBodyplate.addBox(0.0F, 0.0F, 0.0F, 8, 1, 14, 0.0F);
        this.vehicleRearPlate = new ModelRenderer(this, 0, 71);
        this.vehicleRearPlate.setRotationPoint(0.0F, -5.0F, 8.5F);
        this.vehicleRearPlate.addBox(-3.0F, 0.0F, 0.0F, 6, 5, 1, 0.0F);
        this.setRotation(vehicleRearPlate, -0.3839724354387525F, 0.0F, 0.0F);
        this.vehicleTurretSights = new ModelRenderer(this, 40, 65);
        this.vehicleTurretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.vehicleTurretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
        this.setRotation(vehicleTurretSights, 0.017453292519943295F, 0.0F, 0.0F);
        this.vehicleBody = new ModelRenderer(this, 64, 64);
        this.vehicleBody.setRotationPoint(0.0F, 22.5F, 0.5F);
        this.vehicleBody.addBox(-5.0F, -4.0F, -7.5F, 10, 4, 15, 0.0F);
        this.vehicleTread1 = new ModelRenderer(this, 0, 64);
        this.vehicleTread1.setRotationPoint(-4.1F, 0.0F, 4.0F);
        this.vehicleTread1.addBox(-2.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
        this.vehicleTurretRear = new ModelRenderer(this, 46, 90);
        this.vehicleTurretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleTurretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
        this.setRotation(vehicleTurretRear, 0.22689280275926282F, 0.0F, 0.0F);
        this.vehicleTurret = new ModelRenderer(this, 21, 64);
        this.vehicleTurret.setRotationPoint(0.0F, -6.0F, -2.5F);
        this.vehicleTurret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
        this.vehicleTrackcover2 = new ModelRenderer(this, 0, 101);
        this.vehicleTrackcover2.setRotationPoint(3.5F, -5.5F, -10.2F);
        this.vehicleTrackcover2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
        this.vehicleFoot2 = new ModelRenderer(this, 0, 64);
        this.vehicleFoot2.mirror = true;
        this.vehicleFoot2.setRotationPoint(5.0F, -1.5F, -9.2F);
        this.vehicleFoot2.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
        this.setRotation(vehicleFoot2, -0.03490658503988659F, -0.0F, -0.03490658503988659F);
        this.vehicleMissileLauncher = new ModelRenderer(this, 30, 90);
        this.vehicleMissileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleMissileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
        this.setRotation(vehicleMissileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
        this.vehicleBack1 = new ModelRenderer(this, 23, 102);
        this.vehicleBack1.setRotationPoint(-2.5F, -6.5F, 3.8F);
        this.vehicleBack1.addBox(-4.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        this.vehicleTrackcover1 = new ModelRenderer(this, 0, 101);
        this.vehicleTrackcover1.setRotationPoint(-3.5F, -5.5F, -10.2F);
        this.vehicleTrackcover1.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
        this.vehicleGun = new ModelRenderer(this, 38, 69);
        this.vehicleGun.setRotationPoint(0.0F, -1.1F, -3.0F);
        this.vehicleGun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
        this.vehicleBody.addChild(this.vehicleTread2);
        this.vehicleBody.addChild(this.vehicleBack2);
        this.vehicleBody.addChild(this.vehicleBodyFront);
        this.vehicleTurret.addChild(this.vehicleTurretFront);
        this.vehicleBody.addChild(this.vehicleFoot1);
        this.vehicleBody.addChild(this.vehicleBodyplate);
        this.vehicleBody.addChild(this.vehicleRearPlate);
        this.vehicleTurret.addChild(this.vehicleTurretSights);
        this.vehicleBody.addChild(this.vehicleTread1);
        this.vehicleTurret.addChild(this.vehicleTurretRear);
        this.vehicleBody.addChild(this.vehicleTurret);
        this.vehicleBody.addChild(this.vehicleTrackcover2);
        this.vehicleBody.addChild(this.vehicleFoot2);
        this.vehicleTurretRear.addChild(this.vehicleMissileLauncher);
        this.vehicleBody.addChild(this.vehicleBack1);
        this.vehicleBody.addChild(this.vehicleTrackcover1);
        this.vehicleTurret.addChild(this.vehicleGun);
    }
    
    public void render()
    {
        this.vehicleTurret.rotationPointZ = -2;
        this.vehicleBody.render(0.0625F);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}