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

        vehicleTread2 = new ModelRenderer(this, 0, 64);
        vehicleTread2.setRotationPoint(4.1F, 0.0F, 4.0F);
        vehicleTread2.addBox(0.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
        vehicleBack2 = new ModelRenderer(this, 23, 102);
        vehicleBack2.setRotationPoint(2.5F, -6.5F, 3.8F);
        vehicleBack2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        vehicleBodyFront = new ModelRenderer(this, 39, 83);
        vehicleBodyFront.setRotationPoint(0.0F, -5.0F, -5.5F);
        vehicleBodyFront.addBox(-4.0F, 0.0F, -3.0F, 8, 4, 3, 0.0F);
        setRotation(vehicleBodyFront, 0.21467549799530256F, 0.0F, 0.0F);
        vehicleTurretFront = new ModelRenderer(this, 45, 97);
        vehicleTurretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleTurretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
        setRotation(vehicleTurretFront, -0.4974188368183839F, 0.0F, 0.0F);
        vehicleFoot1 = new ModelRenderer(this, 0, 64);
        vehicleFoot1.setRotationPoint(-5.0F, -1.5F, -9.2F);
        vehicleFoot1.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
        setRotation(vehicleFoot1, -0.03490658503988659F, -0.0F, 0.03490658503988659F);
        vehicleBodyplate = new ModelRenderer(this, 0, 86);
        vehicleBodyplate.setRotationPoint(-4.0F, -5.0F, -5.5F);
        vehicleBodyplate.addBox(0.0F, 0.0F, 0.0F, 8, 1, 14, 0.0F);
        vehicleRearPlate = new ModelRenderer(this, 0, 71);
        vehicleRearPlate.setRotationPoint(0.0F, -5.0F, 8.5F);
        vehicleRearPlate.addBox(-3.0F, 0.0F, 0.0F, 6, 5, 1, 0.0F);
        setRotation(vehicleRearPlate, -0.3839724354387525F, 0.0F, 0.0F);
        vehicleTurretSights = new ModelRenderer(this, 40, 65);
        vehicleTurretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
        vehicleTurretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
        setRotation(vehicleTurretSights, 0.017453292519943295F, 0.0F, 0.0F);
        vehicleBody = new ModelRenderer(this, 64, 64);
        vehicleBody.setRotationPoint(0.0F, 22.5F, 0.5F);
        vehicleBody.addBox(-5.0F, -4.0F, -7.5F, 10, 4, 15, 0.0F);
        vehicleTread1 = new ModelRenderer(this, 0, 64);
        vehicleTread1.setRotationPoint(-4.1F, 0.0F, 4.0F);
        vehicleTread1.addBox(-2.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
        vehicleTurretRear = new ModelRenderer(this, 46, 90);
        vehicleTurretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleTurretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
        setRotation(vehicleTurretRear, 0.22689280275926282F, 0.0F, 0.0F);
        vehicleTurret = new ModelRenderer(this, 21, 64);
        vehicleTurret.setRotationPoint(0.0F, -6.0F, -2.5F);
        vehicleTurret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
        vehicleTrackcover2 = new ModelRenderer(this, 0, 101);
        vehicleTrackcover2.setRotationPoint(3.5F, -5.5F, -10.2F);
        vehicleTrackcover2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
        vehicleFoot2 = new ModelRenderer(this, 0, 64);
        vehicleFoot2.mirror = true;
        vehicleFoot2.setRotationPoint(5.0F, -1.5F, -9.2F);
        vehicleFoot2.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
        setRotation(vehicleFoot2, -0.03490658503988659F, -0.0F, -0.03490658503988659F);
        vehicleMissileLauncher = new ModelRenderer(this, 30, 90);
        vehicleMissileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleMissileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
        setRotation(vehicleMissileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
        vehicleBack1 = new ModelRenderer(this, 23, 102);
        vehicleBack1.setRotationPoint(-2.5F, -6.5F, 3.8F);
        vehicleBack1.addBox(-4.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        vehicleTrackcover1 = new ModelRenderer(this, 0, 101);
        vehicleTrackcover1.setRotationPoint(-3.5F, -5.5F, -10.2F);
        vehicleTrackcover1.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
        vehicleGun = new ModelRenderer(this, 38, 69);
        vehicleGun.setRotationPoint(0.0F, -1.1F, -3.0F);
        vehicleGun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
        vehicleBody.addChild(vehicleTread2);
        vehicleBody.addChild(vehicleBack2);
        vehicleBody.addChild(vehicleBodyFront);
        vehicleTurret.addChild(vehicleTurretFront);
        vehicleBody.addChild(vehicleFoot1);
        vehicleBody.addChild(vehicleBodyplate);
        vehicleBody.addChild(vehicleRearPlate);
        vehicleTurret.addChild(vehicleTurretSights);
        vehicleBody.addChild(vehicleTread1);
        vehicleTurret.addChild(vehicleTurretRear);
        vehicleBody.addChild(vehicleTurret);
        vehicleBody.addChild(vehicleTrackcover2);
        vehicleBody.addChild(vehicleFoot2);
        vehicleTurretRear.addChild(vehicleMissileLauncher);
        vehicleBody.addChild(vehicleBack1);
        vehicleBody.addChild(vehicleTrackcover1);
        vehicleTurret.addChild(vehicleGun);
    }

    public void render()
    {
        vehicleTurret.rotationPointZ = -2;
        vehicleBody.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}