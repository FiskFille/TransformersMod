package fiskfille.tf.client.model.transformer.vehicle;

import net.minecraft.client.model.ModelRenderer;

public class ModelCloudtrapVehicle extends ModelVehicleBase
{
    ModelRenderer vehicleBody;
    ModelRenderer vehicleFrontPiece2;
    ModelRenderer vehicleFrontPiece3;
    ModelRenderer vehicleRightEngine;
    ModelRenderer vehicleLeftEngine;
    ModelRenderer vehicleRightFrontWing;
    ModelRenderer vehicleLeftFrontWing;
    ModelRenderer vehicleMiddleWing;
    ModelRenderer vehicleRightWing;
    ModelRenderer vehicleLeftWing;
    ModelRenderer vehicleMiddlePiece;
    ModelRenderer vehicleBackPiece;
    ModelRenderer vehicleBackFin1;
    ModelRenderer vehicleBackFin2;
    ModelRenderer vehicleBackFin3;
    ModelRenderer vehicleRightBackWing;
    ModelRenderer vehicleLeftBackWing;
    ModelRenderer vehicleCockpit;
    ModelRenderer vehicleFrontPiece1;

    public ModelCloudtrapVehicle()
    {
        textureWidth = 64;
        textureHeight = 128;

        vehicleBody = new ModelRenderer(this, 0, 98);
        vehicleBody.addBox(-4.0F, 0.0F, -2.0F, 0, 0, 0);
        vehicleBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleCockpit = new ModelRenderer(this, 0, 64);
        vehicleCockpit.setRotationPoint(0.0F, -1.5F, -11.0F);
        vehicleCockpit.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 6);
        vehicleLeftEngine = new ModelRenderer(this, 0, 78);
        vehicleLeftEngine.setRotationPoint(2.5F, 0.0F, 11.5F);
        vehicleLeftEngine.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4);
        vehicleMiddlePiece = new ModelRenderer(this, 20, 109);
        vehicleMiddlePiece.setRotationPoint(0.0F, 0.0F, 1.0F);
        vehicleMiddlePiece.addBox(-1.5F, -1.5F, -5.0F, 3, 3, 14);
        vehicleBackFin2 = new ModelRenderer(this, 22, 76);
        vehicleBackFin2.setRotationPoint(4.0F, -0.35F, 10.0F);
        vehicleBackFin2.addBox(-0.5F, -4.0F, -1.0F, 1, 4, 3);
        setRotateAngle(vehicleBackFin2, -0.6108652381980153F, 0.0F,
                0.3490658503988659F);
        vehicleBackPiece = new ModelRenderer(this, 0, 101);
        vehicleBackPiece.setRotationPoint(0.0F, 0.0F, 8.6F);
        vehicleBackPiece.addBox(-11.5F, -1.0F, 0.0F, 23, 2, 3);
        vehicleMiddleWing = new ModelRenderer(this, 0, 86);
        vehicleMiddleWing.setRotationPoint(0.0F, 0.0F, -1.4F);
        vehicleMiddleWing.addBox(-6.5F, -1.0F, 0.0F, 13, 2, 13);
        vehicleRightEngine = new ModelRenderer(this, 0, 78);
        vehicleRightEngine.setRotationPoint(-2.5F, 0.0F, 11.5F);
        vehicleRightEngine.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4);
        vehicleBackFin3 = new ModelRenderer(this, 25, 78);
        vehicleBackFin3.setRotationPoint(0.0F, 0.0F, 11.5F);
        vehicleBackFin3.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 5);
        vehicleFrontPiece1 = new ModelRenderer(this, 0, 72);
        vehicleFrontPiece1.setRotationPoint(0.0F, 0.0F, -4.0F);
        vehicleFrontPiece1.addBox(-2.5F, -1.0F, -12.0F, 5, 2, 12);
        vehicleRightBackWing = new ModelRenderer(this, 34, 80);
        vehicleRightBackWing.setRotationPoint(-4.4F, 0.0F, 10.8F);
        vehicleRightBackWing.addBox(-2.0F, -0.5F, 0.0F, 4, 1, 5);
        setRotateAngle(vehicleRightBackWing, 0.0F, -0.3839724354387525F, 0.0F);
        vehicleLeftFrontWing = new ModelRenderer(this, 40, 116);
        vehicleLeftFrontWing.setRotationPoint(1.0F, 0.0F, -4.0F);
        vehicleLeftFrontWing.addBox(-2.0F, -0.5F, 0.0F, 3, 1, 6);
        setRotateAngle(vehicleLeftFrontWing, 0.0F, 0.9599310885968813F, 0.0F);
        vehicleFrontPiece3 = new ModelRenderer(this, 0, 72);
        vehicleFrontPiece3.setRotationPoint(0.0F, 0.0F, -18.0F);
        vehicleFrontPiece3.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3);
        vehicleFrontPiece2 = new ModelRenderer(this, 0, 106);
        vehicleFrontPiece2.setRotationPoint(0.0F, 0.0F, -14.0F);
        vehicleFrontPiece2.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 14);
        vehicleLeftBackWing = new ModelRenderer(this, 34, 80);
        vehicleLeftBackWing.setRotationPoint(4.4F, 0.0F, 10.8F);
        vehicleLeftBackWing.addBox(-2.0F, -0.5F, 0.0F, 4, 1, 5);
        setRotateAngle(vehicleLeftBackWing, 0.0F, 0.3839724354387525F, 0.0F);
        vehicleBackFin1 = new ModelRenderer(this, 22, 76);
        vehicleBackFin1.setRotationPoint(-4.0F, -0.35F, 10.0F);
        vehicleBackFin1.addBox(-0.5F, -4.0F, -1.0F, 1, 4, 3);
        setRotateAngle(vehicleBackFin1, -0.6108652381980153F, 0.0F,
                -0.3490658503988659F);
        vehicleRightFrontWing = new ModelRenderer(this, 40, 116);
        vehicleRightFrontWing.setRotationPoint(-1.0F, 0.0F, -4.0F);
        vehicleRightFrontWing.addBox(-1.0F, -0.5F, 0.0F, 3, 1, 6);
        setRotateAngle(vehicleRightFrontWing, 0.0F, -0.9599310885968813F, 0.0F);
        vehicleRightWing = new ModelRenderer(this, 40, 86);
        vehicleRightWing.setRotationPoint(-5.8F, 0.0F, 3.0F);
        vehicleRightWing.addBox(-8.0F, -0.5F, -1.0F, 8, 1, 4);
        setRotateAngle(vehicleRightWing, 0.0F, 0.9075712110370513F, 0.0F);
        vehicleLeftWing = new ModelRenderer(this, 40, 86);
        vehicleLeftWing.setRotationPoint(5.8F, 0.0F, 3.0F);
        vehicleLeftWing.addBox(0.0F, -0.5F, -1.0F, 8, 1, 4);
        setRotateAngle(vehicleLeftWing, 0.0F, -0.9075712110370513F, 0.0F);

        this.addChildTo(vehicleBackFin1, vehicleBody);
        this.addChildTo(vehicleBackFin2, vehicleBody);
        this.addChildTo(vehicleBackFin3, vehicleBody);
        this.addChildTo(vehicleBackPiece, vehicleBody);
        this.addChildTo(vehicleCockpit, vehicleBody);
        this.addChildTo(vehicleFrontPiece1, vehicleBody);
        this.addChildTo(vehicleFrontPiece2, vehicleBody);
        this.addChildTo(vehicleFrontPiece3, vehicleBody);
        this.addChildTo(vehicleLeftBackWing, vehicleBody);
        this.addChildTo(vehicleLeftEngine, vehicleBody);
        this.addChildTo(vehicleLeftFrontWing, vehicleBody);
        this.addChildTo(vehicleLeftWing, vehicleBody);
        this.addChildTo(vehicleMiddlePiece, vehicleBody);
        this.addChildTo(vehicleMiddleWing, vehicleBody);
        this.addChildTo(vehicleRightBackWing, vehicleBody);
        this.addChildTo(vehicleRightEngine, vehicleBody);
        this.addChildTo(vehicleRightFrontWing, vehicleBody);
        this.addChildTo(vehicleRightWing, vehicleBody);
        vehicleBody.offsetY = 1.25F;
    }

    public void render()
    {
        vehicleBody.render(0.0625F);
        vehicleBody.offsetY = 1.25F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y,
            float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}