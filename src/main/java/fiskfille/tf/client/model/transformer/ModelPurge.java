package fiskfille.tf.client.model.transformer;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public class ModelPurge extends MowzieModelBase
{
    public MowzieModelRenderer upperLegR;
    public MowzieModelRenderer upperLegPanel1;
    public MowzieModelRenderer lowerLegRight;
    public MowzieModelRenderer lowerLegPanel1;
    public MowzieModelRenderer footCylinder1;
    public MowzieModelRenderer foot1;
    public MowzieModelRenderer waist;
    public MowzieModelRenderer stomach;
    public MowzieModelRenderer hipSlab1;
    public MowzieModelRenderer hipSlab2;
    public MowzieModelRenderer chest;
    public MowzieModelRenderer backKibble;
    public MowzieModelRenderer chestSlab1;
    public MowzieModelRenderer chestSlab2;
    public MowzieModelRenderer treadRight;
    public MowzieModelRenderer treadLeft;
    public MowzieModelRenderer turret;
    public MowzieModelRenderer gun;
    public MowzieModelRenderer turretFront;
    public MowzieModelRenderer turretSights;
    public MowzieModelRenderer turretRear;
    public MowzieModelRenderer missileLauncher;
    public MowzieModelRenderer hipPanelRight;
    public MowzieModelRenderer hipPanelLeft;
    public MowzieModelRenderer upperArmL;
    public MowzieModelRenderer shoulderPad2;
    public MowzieModelRenderer lowerArm2;
    public MowzieModelRenderer lowerArmPanel2;
    public MowzieModelRenderer upperArmR;
    public MowzieModelRenderer shoulderPadRight;
    public MowzieModelRenderer lowerArm1;
    public MowzieModelRenderer lowerArmPanel1;
    public MowzieModelRenderer head;
    public MowzieModelRenderer helmetRight;
    public MowzieModelRenderer helmetLeft;
    public MowzieModelRenderer helmetTop;
    public MowzieModelRenderer helmetBack;
    public MowzieModelRenderer horn1;
    public MowzieModelRenderer horn2;
    public MowzieModelRenderer centerHorn;
    public MowzieModelRenderer upperLegL;
    public MowzieModelRenderer upperLegPanel2;
    public MowzieModelRenderer lowerLegLeft;
    public MowzieModelRenderer lowerLegPanel2;
    public MowzieModelRenderer footCylinder2;
    public MowzieModelRenderer foot2;

    public ModelRenderer vehicleTreadRight;
    public ModelRenderer vehicleTreadLeft;
    public ModelRenderer vehicleFootRight;
    public ModelRenderer vehicleFootLeft;
    public ModelRenderer vehicleBody;
    public ModelRenderer vehicleTrackCoverLeft;
    public ModelRenderer vehicleTrackCoverRight;
    public ModelRenderer vehicleRearPlate;
    public ModelRenderer vehicleBodyFront;
    public ModelRenderer vehicleBackLeft;
    public ModelRenderer vehicleBackRight;
    public ModelRenderer vehicleTurret;
    public ModelRenderer vehicleBodyplate;
    public ModelRenderer vehicleTurretFront;
    public ModelRenderer vehicleTurretSights;
    public ModelRenderer vehicleGun;
    public ModelRenderer vehicleTurretRear;
    public ModelRenderer vehicleMissileLauncher;

    public ModelPurge()
    {
        textureWidth = 128;
        textureHeight = 128;

        shoulderPad2 = new MowzieModelRenderer(this, 23, 102);
        shoulderPad2.setRotationPoint(-0.5F, 1.0F, 0.0F);
        shoulderPad2.addBox(0.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
        head = new MowzieModelRenderer(this, 0, 0);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 4, 0.0F);
        upperLegL = new MowzieModelRenderer(this, 0, 16);
        upperLegL.setRotationPoint(1.9F, 12.0F, 0.0F);
        upperLegL.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
        this.setRotation(upperLegL, 0.0F, -0.0F, -0.05235987755982988F);
        turret = new MowzieModelRenderer(this, 21, 64);
        turret.setRotationPoint(0.0F, -8.0F, 2.0F);
        turret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
        this.setRotation(turret, 0.7853981633974483F, 3.141592653589793F, 0.0F);
        waist = new MowzieModelRenderer(this, 10, 16);
        waist.setRotationPoint(0.0F, 12.0F, 0.0F);
        waist.addBox(-3.0F, -3.0F, -1.5F, 6, 3, 3, 0.0F);
        helmetBack = new MowzieModelRenderer(this, 27, 9);
        helmetBack.setRotationPoint(0.0F, -4.0F, 1.5F);
        helmetBack.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 1, 0.0F);
        this.setRotation(helmetBack, 0.12217304855585097F, -0.0F, 0.0F);
        centerHorn = new MowzieModelRenderer(this, 23, 9);
        centerHorn.setRotationPoint(0.0F, -4.5F, -2.0F);
        centerHorn.addBox(-0.5F, -4.0F, -1.0F, 1, 4, 1, 0.0F);
        footCylinder2 = new MowzieModelRenderer(this, 8, 31);
        footCylinder2.setRotationPoint(0.0F, 5.0F, 1.0F);
        footCylinder2.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotation(footCylinder2, 0.0F, -0.0F, 0.05235987755982988F);
        treadRight = new MowzieModelRenderer(this, 46, 0);
        treadRight.setRotationPoint(-3.0F, -4.0F, 2.5F);
        treadRight.addBox(-2.0F, -13.0F, -1.5F, 2, 17, 3, 0.0F);
        this.setRotation(treadRight, 0.17453292519943295F, -0.0F, -0.08726646259971647F);
        chestSlab1 = new MowzieModelRenderer(this, 24, 35);
        chestSlab1.setRotationPoint(0.0F, 1.0F, -1.1F);
        chestSlab1.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotation(chestSlab1, 0.0F, -0.0F, 0.5235987755982988F);
        lowerLegPanel2 = new MowzieModelRenderer(this, 0, 37);
        lowerLegPanel2.mirror = true;
        lowerLegPanel2.setRotationPoint(0.0F, 5.0F, -1.5F);
        lowerLegPanel2.addBox(-1.5F, -4.0F, -0.5F, 3, 4, 1, 0.0F);
        this.setRotation(lowerLegPanel2, 0.008726646259971648F, -0.17453292519943295F, 0.0F);
        upperArmL = new MowzieModelRenderer(this, 36, 21);
        upperArmL.mirror = true;
        upperArmL.setRotationPoint(4.0F, 1.0F, 0.0F);
        upperArmL.addBox(0.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
        this.setRotation(upperArmL, 0.0F, -0.0F, -0.13962633907794952F);
        gun = new MowzieModelRenderer(this, 38, 69);
        gun.setRotationPoint(0.0F, -1.1F, -3.0F);
        gun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
        this.setRotation(gun, 0.4363323129985824F, 0.0F, 0.0F);
        turretFront = new MowzieModelRenderer(this, 45, 97);
        turretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        turretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
        this.setRotation(turretFront, -0.4974188368183839F, 0.0F, 0.0F);
        helmetLeft = new MowzieModelRenderer(this, 28, 0);
        helmetLeft.mirror = true;
        helmetLeft.setRotationPoint(3.0F, -0.5F, 0.0F);
        helmetLeft.addBox(0.0F, -4.0F, -2.5F, 1, 4, 5, 0.0F);
        this.setRotation(helmetLeft, 0.07438152130324906F, -0.15803570099691838F, -0.4422247463038985F);
        horn1 = new MowzieModelRenderer(this, 13, 9);
        horn1.setRotationPoint(0.0F, -4.0F, -2.0F);
        horn1.addBox(0.0F, 0.0F, -1.0F, 4, 1, 1, 0.0F);
        this.setRotation(horn1, 0.0F, -0.0F, -2.268928050994873F);
        lowerArm1 = new MowzieModelRenderer(this, 36, 30);
        lowerArm1.setRotationPoint(-1.2F, 4.6F, 0.0F);
        lowerArm1.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotation(lowerArm1, -0.19198621771937624F, -0.13962634015954636F, -0.13962634015954636F);
        upperLegPanel2 = new MowzieModelRenderer(this, 0, 31);
        upperLegPanel2.mirror = true;
        upperLegPanel2.setRotationPoint(0.0F, 5.5F, -1.5F);
        upperLegPanel2.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1, 0.0F);
        this.setRotation(upperLegPanel2, 0.008726646259971648F, -0.17453292519943295F, 0.0F);
        upperLegR = new MowzieModelRenderer(this, 0, 16);
        upperLegR.setRotationPoint(-1.9F, 12.0F, 0.0F);
        upperLegR.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
        this.setRotation(upperLegR, 0.0F, -0.0F, 0.05235987755982988F);
        turretSights = new MowzieModelRenderer(this, 40, 65);
        turretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
        turretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
        this.setRotation(turretSights, 0.017453292519943295F, 0.0F, 0.0F);
        treadLeft = new MowzieModelRenderer(this, 46, 0);
        treadLeft.mirror = true;
        treadLeft.setRotationPoint(3.0F, -4.0F, 2.5F);
        treadLeft.addBox(0.0F, -13.0F, -1.5F, 2, 17, 3, 0.0F);
        this.setRotation(treadLeft, 0.17453292519943295F, -0.0F, 0.08726646259971647F);
        chest = new MowzieModelRenderer(this, 0, 42);
        chest.setRotationPoint(0.0F, -4.0F, 0.0F);
        chest.addBox(-4.0F, -5.0F, -2.0F, 8, 5, 4, 0.0F);
        lowerLegLeft = new MowzieModelRenderer(this, 0, 16);
        lowerLegLeft.setRotationPoint(0.0F, 5.0F, 0.0F);
        lowerLegLeft.addBox(-1.0F, 0.0F, -1.5F, 2, 7, 3, 0.0F);
        foot1 = new MowzieModelRenderer(this, 8, 35);
        foot1.setRotationPoint(0.0F, 5.0F, 1.0F);
        foot1.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
        this.setRotation(foot1, 0.3490658503988659F, -0.0F, -0.05235987755982988F);
        chestSlab2 = new MowzieModelRenderer(this, 24, 35);
        chestSlab2.mirror = true;
        chestSlab2.setRotationPoint(0.0F, 1.0F, -1.1F);
        chestSlab2.addBox(0.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotation(chestSlab2, 0.0F, -0.0F, -0.5235987755982988F);
        missileLauncher = new MowzieModelRenderer(this, 30, 90);
        missileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
        missileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
        this.setRotation(missileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
        backKibble = new MowzieModelRenderer(this, 24, 39);
        backKibble.setRotationPoint(0.0F, -1.0F, 0.0F);
        backKibble.addBox(-3.0F, -6.0F, -0.5F, 6, 6, 5, 0.0F);
        this.setRotation(backKibble, -0.3490658503988659F, -0.0F, 0.0F);
        upperArmR = new MowzieModelRenderer(this, 36, 21);
        upperArmR.setRotationPoint(-4.0F, 1.0F, 0.0F);
        upperArmR.addBox(-2.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
        this.setRotation(upperArmR, 0.0F, 0.0F, 0.13962634015954636F);
        turretRear = new MowzieModelRenderer(this, 46, 90);
        turretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
        turretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
        this.setRotation(turretRear, 0.22689280275926282F, 0.0F, 0.0F);
        footCylinder1 = new MowzieModelRenderer(this, 8, 31);
        footCylinder1.setRotationPoint(0.0F, 5.0F, 1.0F);
        footCylinder1.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotation(footCylinder1, 0.0F, -0.0F, -0.05235987755982988F);
        helmetTop = new MowzieModelRenderer(this, 0, 8);
        helmetTop.setRotationPoint(0.0F, -4.0F, 0.0F);
        helmetTop.addBox(-2.0F, -2.0F, -2.5F, 4, 2, 5, 0.0F);
        foot2 = new MowzieModelRenderer(this, 8, 35);
        foot2.mirror = true;
        foot2.setRotationPoint(0.0F, 5.0F, 1.0F);
        foot2.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
        this.setRotation(foot2, 0.3490658503988659F, -0.0F, 0.03490658503988659F);
        lowerLegRight = new MowzieModelRenderer(this, 0, 16);
        lowerLegRight.setRotationPoint(0.0F, 5.0F, 0.0F);
        lowerLegRight.addBox(-1.0F, 0.0F, -1.5F, 2, 7, 3, 0.0F);
        lowerArmPanel1 = new MowzieModelRenderer(this, 44, 30);
        lowerArmPanel1.setRotationPoint(0.3F, 3.0F, 0.0F);
        lowerArmPanel1.addBox(-2.0F, -2.0F, -1.5F, 2, 4, 3, 0.0F);
        lowerLegPanel1 = new MowzieModelRenderer(this, 0, 37);
        lowerLegPanel1.setRotationPoint(0.0F, 5.0F, -1.5F);
        lowerLegPanel1.addBox(-1.5F, -4.0F, -0.5F, 3, 4, 1, 0.0F);
        this.setRotation(lowerLegPanel1, 0.008726646259971648F, 0.17453292519943295F, 0.0F);
        hipSlab2 = new MowzieModelRenderer(this, 18, 22);
        hipSlab2.mirror = true;
        hipSlab2.setRotationPoint(0.0F, 0.0F, 0.0F);
        hipSlab2.addBox(0.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
        this.setRotation(hipSlab2, 0.0F, -0.0F, -0.3490658503988659F);
        lowerArmPanel2 = new MowzieModelRenderer(this, 44, 30);
        lowerArmPanel2.mirror = true;
        lowerArmPanel2.setRotationPoint(-0.3F, 3.0F, 0.0F);
        lowerArmPanel2.addBox(0.0F, -2.0F, -1.5F, 2, 4, 3, 0.0F);
        stomach = new MowzieModelRenderer(this, 20, 28);
        stomach.setRotationPoint(0.0F, -3.0F, 0.0F);
        stomach.addBox(-2.5F, -4.0F, -1.5F, 5, 4, 3, 0.0F);
        lowerArm2 = new MowzieModelRenderer(this, 36, 30);
        lowerArm2.mirror = true;
        lowerArm2.setRotationPoint(1.2F, 4.6F, 0.0F);
        lowerArm2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotation(lowerArm2, -0.19198621771937624F, 0.13962634015954636F, 0.13962634015954636F);
        hipSlab1 = new MowzieModelRenderer(this, 18, 22);
        hipSlab1.setRotationPoint(0.0F, 0.0F, 0.0F);
        hipSlab1.addBox(-4.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
        this.setRotation(hipSlab1, 0.0F, 0.0F, 0.3490658503988659F);
        upperLegPanel1 = new MowzieModelRenderer(this, 0, 31);
        upperLegPanel1.setRotationPoint(0.0F, 5.5F, -1.5F);
        upperLegPanel1.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1, 0.0F);
        this.setRotation(upperLegPanel1, 0.008726646259971648F, 0.17453292519943295F, 0.0F);
        hipPanelRight = new MowzieModelRenderer(this, 10, 22);
        hipPanelRight.mirror = true;
        hipPanelRight.setRotationPoint(-3.3F, 1.3F, 0.0F);
        hipPanelRight.addBox(-0.5F, -1.0F, -1.5F, 1, 6, 3, 0.0F);
        this.setRotation(hipPanelRight, 0.0F, -0.0F, -0.03490658503988659F);
        helmetRight = new MowzieModelRenderer(this, 28, 0);
        helmetRight.setRotationPoint(-3.0F, -0.5F, 0.0F);
        helmetRight.addBox(-1.0F, -4.0F, -2.5F, 1, 4, 5, 0.0F);
        this.setRotation(helmetRight, 0.07438152130324906F, 0.15803570099691838F, 0.4422247463038985F);
        shoulderPadRight = new MowzieModelRenderer(this, 23, 102);
        shoulderPadRight.setRotationPoint(0.5F, 1.0F, 0.0F);
        shoulderPadRight.addBox(-4.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
        horn2 = new MowzieModelRenderer(this, 13, 9);
        horn2.setRotationPoint(0.0F, -4.0F, -2.0F);
        horn2.addBox(0.0F, -1.0F, -1.0F, 4, 1, 1, 0.0F);
        this.setRotation(horn2, 0.0F, -0.0F, -0.8726646304130553F);
        hipPanelLeft = new MowzieModelRenderer(this, 10, 22);
        hipPanelLeft.mirror = true;
        hipPanelLeft.setRotationPoint(3.3F, 1.3F, 0.0F);
        hipPanelLeft.addBox(-0.5F, -1.0F, -1.5F, 1, 6, 3, 0.0F);
        this.setRotation(hipPanelLeft, 0.0F, -0.0F, -0.03490658503988659F);
        upperArmL.addChild(shoulderPad2);
        backKibble.addChild(turret);
        head.addChild(helmetBack);
        head.addChild(centerHorn);
        lowerLegLeft.addChild(footCylinder2);
        backKibble.addChild(treadRight);
        chest.addChild(chestSlab1);
        lowerLegLeft.addChild(lowerLegPanel2);
        turret.addChild(gun);
        turret.addChild(turretFront);
        head.addChild(helmetLeft);
        head.addChild(horn1);
        upperArmR.addChild(lowerArm1);
        upperLegL.addChild(upperLegPanel2);
        turret.addChild(turretSights);
        backKibble.addChild(treadLeft);
        stomach.addChild(chest);
        upperLegL.addChild(lowerLegLeft);
        lowerLegRight.addChild(foot1);
        chest.addChild(chestSlab2);
        turretRear.addChild(missileLauncher);
        chest.addChild(backKibble);
        turret.addChild(turretRear);
        lowerLegRight.addChild(footCylinder1);
        head.addChild(helmetTop);
        lowerLegLeft.addChild(foot2);
        upperLegR.addChild(lowerLegRight);
        lowerArm1.addChild(lowerArmPanel1);
        lowerLegRight.addChild(lowerLegPanel1);
        waist.addChild(hipSlab2);
        lowerArm2.addChild(lowerArmPanel2);
        waist.addChild(stomach);
        upperArmL.addChild(lowerArm2);
        waist.addChild(hipSlab1);
        upperLegR.addChild(upperLegPanel1);
        hipSlab1.addChild(hipPanelRight);
        head.addChild(helmetRight);
        upperArmR.addChild(shoulderPadRight);
        head.addChild(horn2);
        hipSlab2.addChild(hipPanelLeft);
        chest.addChild(head);
        chest.addChild(upperArmR);
        chest.addChild(upperArmL);
        waist.addChild(upperLegR);
        waist.addChild(upperLegL);

        vehicleTreadLeft = new ModelRenderer(this, 0, 64);
        vehicleTreadLeft.setRotationPoint(4.1F, 0.0F, 4.0F);
        vehicleTreadLeft.addBox(0.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
        vehicleBackLeft = new ModelRenderer(this, 23, 102);
        vehicleBackLeft.setRotationPoint(2.5F, -6.5F, 3.8F);
        vehicleBackLeft.addBox(0.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        vehicleBodyFront = new ModelRenderer(this, 39, 83);
        vehicleBodyFront.setRotationPoint(0.0F, -5.0F, -5.5F);
        vehicleBodyFront.addBox(-4.0F, 0.0F, -3.0F, 8, 4, 3, 0.0F);
        this.setRotation(vehicleBodyFront, 0.21467549799530256F, 0.0F, 0.0F);
        vehicleTurretFront = new ModelRenderer(this, 45, 97);
        vehicleTurretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleTurretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
        this.setRotation(vehicleTurretFront, -0.4974188368183839F, 0.0F, 0.0F);
        vehicleFootRight = new ModelRenderer(this, 0, 64);
        vehicleFootRight.setRotationPoint(-5.0F, -1.5F, -9.2F);
        vehicleFootRight.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
        this.setRotation(vehicleFootRight, -0.03490658503988659F, -0.0F, 0.03490658503988659F);
        vehicleBodyplate = new ModelRenderer(this, 0, 86);
        vehicleBodyplate.setRotationPoint(-4.0F, -5.0F, -5.5F);
        vehicleBodyplate.addBox(0.0F, 0.0F, 0.0F, 8, 1, 14, 0.0F);
        vehicleRearPlate = new ModelRenderer(this, 0, 71);
        vehicleRearPlate.setRotationPoint(0.0F, -5.0F, 8.5F);
        vehicleRearPlate.addBox(-3.0F, 0.0F, 0.0F, 6, 5, 1, 0.0F);
        this.setRotation(vehicleRearPlate, -0.3839724354387525F, 0.0F, 0.0F);
        vehicleTurretSights = new ModelRenderer(this, 40, 65);
        vehicleTurretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
        vehicleTurretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
        this.setRotation(vehicleTurretSights, 0.017453292519943295F, 0.0F, 0.0F);
        vehicleBody = new ModelRenderer(this, 64, 64);
        vehicleBody.setRotationPoint(0.0F, 22.5F, 0.5F);
        vehicleBody.addBox(-5.0F, -4.0F, -7.5F, 10, 4, 15, 0.0F);
        vehicleTreadRight = new ModelRenderer(this, 0, 64);
        vehicleTreadRight.setRotationPoint(-4.1F, 0.0F, 4.0F);
        vehicleTreadRight.addBox(-2.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
        vehicleTurretRear = new ModelRenderer(this, 46, 90);
        vehicleTurretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleTurretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
        this.setRotation(vehicleTurretRear, 0.22689280275926282F, 0.0F, 0.0F);
        vehicleTurret = new ModelRenderer(this, 21, 64);
        vehicleTurret.setRotationPoint(0.0F, -6.0F, -2.5F);
        vehicleTurret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
        vehicleTrackCoverLeft = new ModelRenderer(this, 0, 101);
        vehicleTrackCoverLeft.setRotationPoint(3.5F, -5.5F, -10.2F);
        vehicleTrackCoverLeft.addBox(0.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
        vehicleFootLeft = new ModelRenderer(this, 0, 64);
        vehicleFootLeft.mirror = true;
        vehicleFootLeft.setRotationPoint(5.0F, -1.5F, -9.2F);
        vehicleFootLeft.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
        this.setRotation(vehicleFootLeft, -0.03490658503988659F, -0.0F, -0.03490658503988659F);
        vehicleMissileLauncher = new ModelRenderer(this, 30, 90);
        vehicleMissileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleMissileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
        this.setRotation(vehicleMissileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
        vehicleBackRight = new ModelRenderer(this, 23, 102);
        vehicleBackRight.setRotationPoint(-2.5F, -6.5F, 3.8F);
        vehicleBackRight.addBox(-4.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        vehicleTrackCoverRight = new ModelRenderer(this, 0, 101);
        vehicleTrackCoverRight.setRotationPoint(-3.5F, -5.5F, -10.2F);
        vehicleTrackCoverRight.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
        vehicleGun = new ModelRenderer(this, 38, 69);
        vehicleGun.setRotationPoint(0.0F, -1.1F, -3.0F);
        vehicleGun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
        vehicleBody.addChild(vehicleTreadLeft);
        vehicleBody.addChild(vehicleBackLeft);
        vehicleBody.addChild(vehicleBodyFront);
        vehicleTurret.addChild(vehicleTurretFront);
        vehicleBody.addChild(vehicleFootRight);
        vehicleBody.addChild(vehicleBodyplate);
        vehicleBody.addChild(vehicleRearPlate);
        vehicleTurret.addChild(vehicleTurretSights);
        vehicleBody.addChild(vehicleTreadRight);
        vehicleTurret.addChild(vehicleTurretRear);
        vehicleBody.addChild(vehicleTurret);
        vehicleBody.addChild(vehicleTrackCoverLeft);
        vehicleBody.addChild(vehicleFootLeft);
        vehicleTurretRear.addChild(vehicleMissileLauncher);
        vehicleBody.addChild(vehicleBackRight);
        vehicleBody.addChild(vehicleTrackCoverRight);
        vehicleTurret.addChild(vehicleGun);

        //parts = new MowzieModelRenderer[]{upperLegL, lowerLeg2, foot2, footCylinder2, lowerLegPanel2, upperLegPanel2, upperLegR, lowerLeg1, foot1, footCylinder1, lowerLegPanel1, upperLegPanel1, waist, stomach, hipSlab1, hipSlab2, chest, backKibble, chestSlab1, chestSlab2, tread1, tread2, turret, gun, turretFront, turretSights, turretRear, missileLauncher, hipPanel1, hipPanel2, upperArmL, shoulderPad2, lowerArm2, lowerArmPanel2, upperArmR, shoulderPad1, lowerArm1, lowerArmPanel1, head, helmetRight, helmetLeft, helmetTop, helmetBack, horn1, horn2, centerHorn};
        setInitPose();
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerPurge;
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerPurge;
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerPurge;

            if (TFDataManager.getTransformationTimer(player) == 0)
            {
                vehicleBody.render(f5);
            }
            else
            {
                if (!wearingChest)
                {
                    if (wearingHead)
                    {
                        head.render(f5);
                    }

                    if (wearingLegs)
                    {
                        upperLegL.render(f5);
                        upperLegR.render(f5);
                    }
                }
                else
                {
                    waist.render(f5);
                }
            }
        }
    }

    private void setRotation(MowzieModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        vehicleTurret.rotationPointZ = -2;

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            setToInitPose();

            ModelOffset offsets = TFModelHelper.getOffsets(player);

            head.rotationPointX += offsets.headOffsetX;
            head.rotationPointY += offsets.headOffsetY;
            head.rotationPointZ += offsets.headOffsetZ;

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerPurge;
            Transformer transformerChest = TFHelper.getTransformerFromArmor(player, 2);
            boolean wearingChest = transformerChest instanceof TransformerPurge;
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerPurge;

            float globalSpeed = 1;
            float globalDegree = 0.8F;

            if (wearingChest || wearingHead && !wearingLegs)
            {
                upperLegR.rotationPointY = 0;
                upperLegL.rotationPointY = 0;
            }

            if (!wearingChest && wearingHead)
            {
                if (transformerChest instanceof TransformerSkystrike)
                {
                    head.rotationPointY -= 1;
                }
            }

            upperArmR.rotationPointX += 4.5F;
            upperArmL.rotationPointX -= 4.5F;

            //Walk animation
            upperArmR.rotationPointX -= 5;
            upperArmL.rotationPointX += 5;
            upperArmR.rotationPointY -= 5;
            upperArmL.rotationPointY -= 5;
            backKibble.rotationPointY += 5;

            waist.rotationPointY += 1;

            head.showModel = wearingHead;
            upperLegR.showModel = wearingLegs;
            upperLegL.showModel = wearingLegs;

            boolean sneaking = entity.isSneaking();

            if (sneaking)
            {
                globalDegree = 1.5F;
                globalSpeed = 1.5F;
            }

            head.rotationPointY -= 5.5F;
            if (!wearingChest)
            {
                head.rotationPointY += 5.5F;
            }

            if (wearingHead)
            {
                faceTarget(head, 1, par4, par5);
            }

            int backwardInverter = 1;
            if (((EntityPlayer) entity).moveForward < 0)
            {
                backwardInverter = -1;
                globalDegree = 0.5F;
            }

            boolean fullSuit = wearingHead && wearingLegs && wearingChest;

            applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArm1, lowerArm2);
            applyDefaultHittingAnimation(upperArmR, upperArmL, head, chest, lowerArm1, lowerArm2);

            if (aimedBow)
            {
                upperArmR.rotateAngleY += -0.1F + head.rotateAngleY;
                upperArmL.rotateAngleY += 0.1F + head.rotateAngleY + 0.4F;
                upperArmR.rotateAngleX += -((float) Math.PI / 2F) + head.rotateAngleX;
                upperArmL.rotateAngleX += -((float) Math.PI / 2F) + head.rotateAngleX;
                upperArmR.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
                upperArmL.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
                upperArmR.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
                upperArmL.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
            }

            if (isRiding)
            {
                upperArmR.rotateAngleX -= (float) Math.PI / 5F;
                upperArmL.rotateAngleX -= (float) Math.PI / 5F;
                upperLegR.rotateAngleX -= (float) Math.PI * 2F / 5F;
                upperLegL.rotateAngleX -= (float) Math.PI * 2F / 5F;

                upperLegR.rotateAngleY += (float) Math.PI / 10F;
                upperLegL.rotateAngleY -= (float) Math.PI / 10F;
            }

            if (fullSuit)
            {
                boolean playerOnGround = onGround(player);

                if (playerOnGround || player.capabilities.isFlying)
                {
                    //New pose!
                    upperLegR.rotateAngleY += 0.2;
                    upperLegL.rotateAngleY -= 0.2;
                    upperLegR.rotateAngleX -= 0.4;
                    upperLegL.rotateAngleX -= 0.4;
                    lowerLegRight.rotateAngleX += 0.6;
                    lowerLegLeft.rotateAngleX += 0.6;
                    foot1.rotateAngleX -= 0.3;
                    foot2.rotateAngleX -= 0.3;

                    waist.rotateAngleX += 0.1;

                    head.rotateAngleX += 0.1;

                    bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, par1, par2);
                    waist.rotationPointY += 1.2 * par2;
                    walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
                    walk(chest, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
                    swing(chest, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0, 0, par1, par2);
                    swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0, 0, par1, par2);
                    walk(head, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * par2 * backwardInverter, par1, par2);

                    swing(head, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, par1, par2);
                    head.rotationPointX += 0.6 * globalDegree * par2 * Math.cos(par1 * 0.5F * globalSpeed);

                    swing(upperLegR, 0.5F * globalSpeed, 0F * globalDegree, false, 0, -0.15F, par1, par2);
                    swing(upperLegL, 0.5F * globalSpeed, 0F * globalDegree, false, 0, 0.15F, par1, par2);
                    walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, 0, 0, par1, par2);
                    walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, 0, 0, par1, par2);
                    walk(lowerLegRight, 0.5F * globalSpeed, 1.2F * globalDegree, false, -2.2F * backwardInverter, 0.6F, par1, par2);
                    walk(lowerLegLeft, 0.5F * globalSpeed, 1.2F * globalDegree, true, -2.2F * backwardInverter, 0.6F, par1, par2);
                    walk(upperArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * par2 * backwardInverter, par1, par2);
                    walk(upperArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * par2 * backwardInverter, par1, par2);
                    walk(lowerArm1, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * par2, par1, par2);
                    walk(lowerArm2, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * par2, par1, par2);

                    flap(hipPanelRight, 1F * globalSpeed, 0.2F * globalDegree, false, -1, 0, par1, par2);
                    flap(hipPanelLeft, 1F * globalSpeed, 0.2F * globalDegree, true, -1, 0, par1, par2);
                    walk(gun, 1F * globalSpeed, -0.3F * globalDegree, false, -1, 0, par1, par2);

                    //Idle animation
                    int ticksExisted = entity.ticksExisted;

                    walk(stomach, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
                    walk(chest, 0.08F, 0.15F, false, 1, 0, ticksExisted, 1F);
                    walk(head, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
                    walk(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
                    walk(upperArmL, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);

                    flap(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
                    flap(upperArmL, 0.08F, 0.05F, false, 1, 0, ticksExisted, 1F);
                    walk(lowerArm1, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
                    walk(lowerArm2, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);

                    if (sneaking)
                    {
                        waist.rotateAngleX -= 0.1F;
                        stomach.rotateAngleX += 0.5;
                        head.rotateAngleX -= 0.5;
                        upperLegR.rotateAngleX -= 0.7;
                        upperLegL.rotateAngleX -= 0.7;
                        upperLegR.rotateAngleY += 0.2;
                        upperLegL.rotateAngleY -= 0.2;
                        lowerLegRight.rotateAngleX += 1.1;
                        lowerLegLeft.rotateAngleX += 1.1;
                        foot1.rotateAngleX -= 0.5;
                        foot2.rotateAngleX -= 0.5;
                        foot1.rotationPointY += 2;
                        foot2.rotationPointY += 2;
                        upperArmR.rotateAngleX -= 0.5;
                        upperArmL.rotateAngleX -= 0.5;
                        upperArmR.rotateAngleZ += 0.5;
                        upperArmL.rotateAngleZ -= 0.5;
                        lowerArm1.rotateAngleZ -= 0.5;
                        lowerArm2.rotateAngleZ += 0.5;
                    }
                }
                else
                {
                    double motionY = entity.posY - entity.prevPosY;
                    float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
                    float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));

                    waist.rotateAngleX += 0.2 * par2 * backwardInverter;

                    stomach.rotateAngleX += 0.2 * upwardPose;
                    chest.rotateAngleX -= 0.4 * upwardPose;
                    head.rotateAngleX += 0.6 * upwardPose;

                    upperArmR.rotateAngleX += 0.1 * upwardPose;
                    upperArmL.rotateAngleX += 0.1 * upwardPose;
                    upperArmR.rotateAngleZ -= 0.1 * upwardPose;
                    upperArmL.rotateAngleZ += 0.1 * upwardPose;
                    lowerArm1.rotateAngleX += 0.2 * upwardPose;
                    lowerArm2.rotateAngleX += 0.2 * upwardPose;

                    upperLegR.rotateAngleX += 0.2 * upwardPose;
                    upperLegL.rotateAngleX -= 1 * upwardPose;
                    lowerLegRight.rotateAngleX += 0.3 * upwardPose;
                    lowerLegLeft.rotateAngleX += 1.5 * upwardPose;

                    walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, 0, 0, par1, par2);
                    walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, 0, 0, par1, par2);
                    walk(lowerLegRight, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, -2.2F * backwardInverter, 0F, par1, par2);
                    walk(lowerLegLeft, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, -2.2F * backwardInverter, 0F, par1, par2);
                    waist.rotateAngleX -= 0.2 * downwardPose;
                    stomach.rotateAngleX += 0.3 * downwardPose;
                    chest.rotateAngleX += 0.3 * downwardPose;
                    head.rotateAngleX += 0.3 * downwardPose;
                    upperLegR.rotateAngleX -= 1.2 * downwardPose;
                    upperLegL.rotateAngleX -= 0.2 * downwardPose;
                    lowerLegRight.rotateAngleX += 2 * downwardPose;
                    lowerLegLeft.rotateAngleX += 0.5 * downwardPose;
                    upperArmR.rotateAngleZ += 1 * downwardPose;
                    upperArmL.rotateAngleZ -= 1 * downwardPose;
                    lowerArm1.rotateAngleX -= 1 * downwardPose;
                    lowerArm2.rotateAngleX -= 1 * downwardPose;
                }
            }
            else
            {
                upperArmL.rotateAngleX += MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 / 2;
                upperArmR.rotateAngleX += MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2 / 2;

                lowerArm2.rotateAngleX += MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 / 4;
                lowerArm1.rotateAngleX += MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2 / 4;

                upperLegR.rotateAngleX += MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 / 2;
                upperLegL.rotateAngleX += MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2 / 2;

                waist.rotationPointY -= 0.8F;

                if (isSneak)
                {
                    waist.rotateAngleX += 0.4F;
                    waist.rotationPointZ += 4F;
                    waist.rotationPointY -= 1;

                    upperArmR.rotateAngleX -= 0.1F;
                    upperArmL.rotateAngleX -= 0.1F;

                    if (wearingChest)
                    {
                        head.rotateAngleX -= 0.4F;
                        upperLegR.rotateAngleX -= 0.4F;
                        upperLegL.rotateAngleX -= 0.4F;
                    }
                    else
                    {
                        upperLegL.rotationPointZ += 5F;
                        upperLegL.rotationPointY -= 0.8F;
                        upperLegR.rotationPointZ += 5F;
                        upperLegR.rotationPointY -= 0.8F;
                    }
                }
            }

            float transformProgress = (float) (20 - TFDataManager.getTransformationTimer(player)) / 20;
            float transformProgressSin = MathHelper.sin(1.57079632679F * transformProgress) * MathHelper.sin(1.57079632679F * transformProgress);

            head.rotateAngleX += Math.PI * transformProgressSin;
            waist.rotateAngleX += Math.PI / 2 * transformProgressSin;
            waist.rotateAngleY += (float) (Math.PI * transformProgressSin);
            waist.rotationPointZ += -12 * transformProgressSin;
            waist.rotationPointY += 5 * transformProgressSin;
            treadRight.rotateAngleX -= Math.PI * transformProgressSin;
            treadLeft.rotateAngleX -= Math.PI * transformProgressSin;
            treadRight.rotationPointZ -= 7 * transformProgressSin;
            treadLeft.rotationPointZ -= 7 * transformProgressSin;
            shoulderPadRight.rotateAngleX += Math.PI / 2 * transformProgressSin;
            shoulderPad2.rotateAngleX += Math.PI / 2 * transformProgressSin;
            shoulderPadRight.rotationPointY -= 5 * transformProgressSin;
            shoulderPad2.rotationPointY -= 5 * transformProgressSin;
            upperArmR.rotationPointY += 5 * transformProgressSin;
            upperArmL.rotationPointY += 5 * transformProgressSin;
            backKibble.rotationPointZ -= 2 * transformProgressSin;
            treadRight.rotationPointZ += 2 * transformProgressSin;
            treadLeft.rotationPointZ += 2 * transformProgressSin;
            turret.rotationPointZ += 2 * transformProgressSin;
            turret.rotateAngleX += 0.5 * transformProgressSin;
            gun.rotateAngleX -= 0.5 * transformProgressSin;
            upperLegR.rotateAngleX -= Math.PI * transformProgressSin;
            upperLegL.rotateAngleX -= Math.PI * transformProgressSin;
            lowerLegRight.rotateAngleX += Math.PI * transformProgressSin;
            lowerLegLeft.rotateAngleX += Math.PI * transformProgressSin;
            hipPanelRight.rotateAngleZ -= Math.PI / 2 * transformProgressSin;
            hipPanelLeft.rotateAngleZ += Math.PI / 2 * transformProgressSin;

            if (TFDataManager.getTransformationTimer(player) == 0)
            {
                float xRotation = par5 / (180F / (float) Math.PI);

                vehicleGun.rotateAngleX = par5 < 0 ? xRotation : 0;
                vehicleTurret.rotateAngleY = par4 / (180F / (float) Math.PI);
            }
            else
            {
                treadRight.showModel = !(wearingChest && player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.purgesKatana);
            }
        }
    }
}