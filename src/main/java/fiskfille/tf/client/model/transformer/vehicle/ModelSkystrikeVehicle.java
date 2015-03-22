package fiskfille.tf.client.model.transformer.vehicle;

import net.minecraft.client.model.ModelRenderer;

public class ModelSkystrikeVehicle extends ModelVehicleBase
{
    public ModelRenderer shape131;
    public ModelRenderer shape132;
    public ModelRenderer shape133;
    public ModelRenderer shape134;
    public ModelRenderer shape136;
    public ModelRenderer shape137;
    public ModelRenderer shape138;
    public ModelRenderer shape139;
    public ModelRenderer shape140;
    public ModelRenderer shape141;
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;
    public ModelRenderer shape10;
    public ModelRenderer shape11;
    public ModelRenderer shape12;
    public ModelRenderer shape13;
    public ModelRenderer shape14;
    public ModelRenderer shape15;
    public ModelRenderer shape16;
    public ModelRenderer shape130;
    public ModelRenderer shape30;
    public ModelRenderer shape31;
    public ModelRenderer shape32;
    public ModelRenderer shape33;
    public ModelRenderer shape35;
    public ModelRenderer shape56;
    public ModelRenderer shape58;
    public ModelRenderer shape59;
    public ModelRenderer shape64;
    public ModelRenderer shape66;
    public ModelRenderer shape67;
    public ModelRenderer shape40;
    public ModelRenderer shape44;
    public ModelRenderer shape41;
    public ModelRenderer shape43;
    public ModelRenderer shape49;
    public ModelRenderer shape68;
    public ModelRenderer shape69;
    public ModelRenderer shape70;
    public ModelRenderer shape75;
    public ModelRenderer shape77;
    public ModelRenderer shape78;
    public ModelRenderer vehicleBody;

    public ModelSkystrikeVehicle()
    {
        textureWidth = 128;
        textureHeight = 128;

        this.shape59 = new ModelRenderer(this, 75, 55);
        this.shape59.mirror = true;
        this.shape59.setRotationPoint(-4.5F, 13.5F, 3.9F);
        this.shape59.addBox(-2.0F, 1.0F, 0.5F, 2, 3, 3, 0.0F);
        this.shape139 = new ModelRenderer(this, 55, 0);
        this.shape139.setRotationPoint(4.0F, -3.0F, 7.0F);
        this.shape139.addBox(0.0F, 0.0F, 0.0F, 3, 4, 5, 0.0F);
        this.shape16 = new ModelRenderer(this, 0, 49);
        this.shape16.setRotationPoint(-2.0F, 7.1F, 3.0F);
        this.shape16.addBox(0.0F, -0.4F, -6.0F, 4, 2, 4, 0.0F);
        this.setRotation(shape16, -1.0018189906447452F, 0.0F, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 84);
        this.shape8.setRotationPoint(-1.5F, 4.0F, -9.0F);
        this.shape8.addBox(0.0F, -0.2F, -0.6F, 3, 4, 2, 0.0F);
        this.setRotation(shape8, 1.879719604397893F, 0.0F, 0.0F);
        this.shape12 = new ModelRenderer(this, 12, 76);
        this.shape12.setRotationPoint(4.0F, 0.0F, 0.0F);
        this.shape12.addBox(-2.5F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotation(shape12, 0.9948376736367678F, 0.0F, 0.0F);
        this.shape56 = new ModelRenderer(this, 63, 49);
        this.shape56.setRotationPoint(0.0F, -4.5F, 20.0F);
        this.shape56.addBox(-3.0F, -0.5F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotation(shape56, -1.5707963267948966F, 0.0F, 0.0F);
        this.shape75 = new ModelRenderer(this, 73, 73);
        this.shape75.setRotationPoint(1.3F, 14.7F, 5.6F);
        this.shape75.addBox(0.5F, -1.0F, -0.1F, 2, 8, 1, 0.0F);
        this.shape41 = new ModelRenderer(this, 36, 68);
        this.shape41.setRotationPoint(0.0F, -3.0F, -2.5F);
        this.shape41.addBox(-1.0F, -0.2F, 0.0F, 1, 4, 4, 0.0F);
        this.setRotation(shape41, 0.0F, 0.0F, 1.3055062804917585F);
        this.shape78 = new ModelRenderer(this, 73, 76);
        this.shape78.mirror = true;
        this.shape78.setRotationPoint(0.5F, 9.5F, 3.9F);
        this.shape78.addBox(0.0F, 2.6F, -27.0F, 1, 1, 6, 0.0F);
        this.setRotation(shape78, 1.5707963267948966F, 0.0F, 0.0F);
        this.shape35 = new ModelRenderer(this, 36, 76);
        this.shape35.mirror = true;
        this.shape35.setRotationPoint(2.0F, 1.0F, -2.0F);
        this.shape35.addBox(1.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.setRotation(shape35, 0.0F, 0.008726646259971648F, 0.0F);
        this.shape137 = new ModelRenderer(this, 37, 0);
        this.shape137.setRotationPoint(0.0F, -3.0F, 19.5F);
        this.shape137.addBox(-2.0F, -2.9F, -9.3F, 4, 3, 10, 0.0F);
        this.setRotation(shape137, 0.41887902047863906F, 0.0F, 0.0F);
        this.shape68 = new ModelRenderer(this, 63, 49);
        this.shape68.mirror = true;
        this.shape68.setRotationPoint(0.0F, -4.5F, 20.0F);
        this.shape68.addBox(0.0F, -0.5F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotation(shape68, -1.5707963267948966F, 0.0F, 0.0F);
        this.shape31 = new ModelRenderer(this, 36, 60);
        this.shape31.mirror = true;
        this.shape31.setRotationPoint(10.0F, 6.0F, -1.8F);
        this.shape31.addBox(-7.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
        this.setRotation(shape31, -1.5707963267948966F, -0.07853981633974483F,
                0.5061454830783556F);
        this.shape66 = new ModelRenderer(this, 73, 76);
        this.shape66.mirror = true;
        this.shape66.setRotationPoint(0.5F, 9.5F, 3.9F);
        this.shape66.addBox(-6.5F, 1.6F, -14.0F, 1, 1, 6, 0.0F);
        this.setRotation(shape66, 1.5707963267948966F, 0.0F, 0.0F);
        this.shape136 = new ModelRenderer(this, 0, 15);
        this.shape136.setRotationPoint(-3.5F, -5.0F, 5.0F);
        this.shape136.addBox(0.0F, 0.0F, 0.0F, 7, 6, 6, 0.0F);
        this.shape77 = new ModelRenderer(this, 73, 76);
        this.shape77.mirror = true;
        this.shape77.setRotationPoint(0.5F, 9.5F, 3.9F);
        this.shape77.addBox(4.5F, 1.6F, -14.0F, 1, 1, 6, 0.0F);
        this.setRotation(shape77, 1.5707963267948966F, 0.0F, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 90);
        this.shape10.setRotationPoint(-4.0F, 7.0F, 3.0F);
        this.shape10.addBox(0.5F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
        this.setRotation(shape10, -0.9948376736367678F, 0.0F, 0.0F);
        this.shape13 = new ModelRenderer(this, 8, 87);
        this.shape13.setRotationPoint(-1.5F, 0.0F, 0.0F);
        this.shape13.addBox(0.0F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
        this.setRotation(shape13, 0.9948376736367678F, 0.0F, 0.0F);
        this.shape32 = new ModelRenderer(this, 36, 65);
        this.shape32.setRotationPoint(11.0F, 11.6F, -1.5F);
        this.shape32.addBox(-9.0F, -1.0F, -0.4F, 6, 1, 2, 0.0F);
        this.setRotation(shape32, -1.5707963267948966F, -0.07853981633974483F,
                0.5061454830783556F);
        this.shape49 = new ModelRenderer(this, 36, 60);
        this.shape49.setRotationPoint(-10.0F, 6.0F, -1.8F);
        this.shape49.addBox(-1.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
        this.setRotation(shape49, -1.5707963267948966F, 0.07853981633974483F,
                -0.5061454830783556F);
        this.shape138 = new ModelRenderer(this, 55, 0);
        this.shape138.mirror = true;
        this.shape138.setRotationPoint(-4.0F, -3.0F, 7.0F);
        this.shape138.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 5, 0.0F);
        this.shape70 = new ModelRenderer(this, 75, 55);
        this.shape70.mirror = true;
        this.shape70.setRotationPoint(4.5F, 13.5F, 3.9F);
        this.shape70.addBox(0.0F, 1.0F, 0.5F, 2, 3, 3, 0.0F);
        this.shape69 = new ModelRenderer(this, 63, 58);
        this.shape69.mirror = true;
        this.shape69.setRotationPoint(0.0F, 5.5F, -1.5F);
        this.shape69.addBox(-0.1F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.setRotation(shape69, 0.2617993877991494F, 0.0F, 0.0F);
        this.shape131 = new ModelRenderer(this, 0, 28);
        this.shape131.setRotationPoint(-3.5F, -3.5F, -4.0F);
        this.shape131.addBox(0.0F, 0.6F, -11.0F, 3, 5, 13, 0.0F);
        this.setRotation(shape131, 0.06981317007977318F, -0.13962634015954636F,
                0.0F);
        this.shape44 = new ModelRenderer(this, 36, 65);
        this.shape44.setRotationPoint(-11.0F, 11.6F, -1.5F);
        this.shape44.addBox(3.0F, -1.0F, -0.4F, 6, 1, 2, 0.0F);
        this.setRotation(shape44, -1.5707963267948966F, 0.07853981633974483F,
                -0.5061454830783556F);
        this.shape43 = new ModelRenderer(this, 36, 76);
        this.shape43.setRotationPoint(-2.0F, 1.0F, -2.0F);
        this.shape43.addBox(-2.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.setRotation(shape43, 0.0F, -0.008726646259971648F, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 55);
        this.shape2.mirror = true;
        this.shape2.setRotationPoint(-2.5F, 1.5F, 9.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
        this.setRotation(shape2, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape64 = new ModelRenderer(this, 73, 73);
        this.shape64.mirror = true;
        this.shape64.setRotationPoint(-1.3F, 14.7F, 5.6F);
        this.shape64.addBox(-2.5F, -1.0F, -0.1F, 2, 8, 1, 0.0F);
        this.shape141 = new ModelRenderer(this, 0, 0);
        this.shape141.mirror = true;
        this.shape141.setRotationPoint(3.0F, -6.0F, 15.0F);
        this.shape141.addBox(-1.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
        this.setRotation(shape141, 0.03490658503988659F, -0.15707963267948966F,
                -0.7853981633974483F);
        this.shape6 = new ModelRenderer(this, 0, 76);
        this.shape6.setRotationPoint(-2.0F, 3.5F, -5.6F);
        this.shape6.addBox(0.0F, 0.3F, 0.0F, 4, 6, 2, 0.0F);
        this.setRotation(shape6, 1.8203784098300857F, 0.0F, 0.0F);
        this.shape67 = new ModelRenderer(this, 73, 76);
        this.shape67.mirror = true;
        this.shape67.setRotationPoint(0.5F, 9.5F, 3.9F);
        this.shape67.addBox(-2.0F, 2.6F, -27.0F, 1, 1, 6, 0.0F);
        this.setRotation(shape67, 1.5707963267948966F, 0.0F, 0.0F);
        this.shape140 = new ModelRenderer(this, 0, 0);
        this.shape140.setRotationPoint(-3.0F, -6.0F, 15.0F);
        this.shape140.addBox(-5.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
        this.setRotation(shape140, 0.03490658503988659F, 0.15707963267948966F,
                0.7853981633974483F);
        this.shape133 = new ModelRenderer(this, 19, 15);
        this.shape133.setRotationPoint(-2.0F, -2.2F, -15.0F);
        this.shape133.addBox(0.0F, 0.0F, 0.0F, 4, 5, 21, 0.0F);
        this.shape132 = new ModelRenderer(this, 0, 28);
        this.shape132.mirror = true;
        this.shape132.setRotationPoint(3.5F, -3.5F, -4.0F);
        this.shape132.addBox(-3.0F, 0.6F, -11.0F, 3, 5, 13, 0.0F);
        this.setRotation(shape132, 0.06981317007977318F, 0.13962634015954636F,
                0.0F);
        this.shape7 = new ModelRenderer(this, 12, 76);
        this.shape7.mirror = true;
        this.shape7.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.shape7.addBox(0.5F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotation(shape7, 0.9948376736367678F, 0.0F, 0.0F);
        this.shape30 = new ModelRenderer(this, 36, 51);
        this.shape30.setRotationPoint(3.5F, -1.5F, -1.0F);
        this.shape30.addBox(0.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
        this.setRotation(shape30, 1.5707963267948966F, 0.0F, 0.0F);
        this.shape33 = new ModelRenderer(this, 36, 68);
        this.shape33.mirror = true;
        this.shape33.setRotationPoint(0.0F, -3.0F, -2.5F);
        this.shape33.addBox(0.0F, -0.2F, 0.0F, 1, 4, 4, 0.0F);
        this.setRotation(shape33, 0.0F, 0.0F, -1.3055062804917585F);
        this.shape40 = new ModelRenderer(this, 36, 51);
        this.shape40.mirror = true;
        this.shape40.setRotationPoint(-3.5F, -1.5F, -1.0F);
        this.shape40.addBox(-4.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
        this.setRotation(shape40, 1.5707963267948966F, 0.0F, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 55);
        this.shape3.setRotationPoint(2.5F, 1.5F, 9.0F);
        this.shape3.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
        this.setRotation(shape3, 0.0F, 1.5707963267948966F, 0.0F);
        this.shape15 = new ModelRenderer(this, 12, 93);
        this.shape15.setRotationPoint(-2.0F, 0.0F, 0.0F);
        this.shape15.addBox(0.0F, 0.2F, -3.3F, 4, 2, 3, 0.0F);
        this.setRotation(shape15, 0.9826203688728075F, 0.0F, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 64);
        this.shape1.setRotationPoint(0.0F, -5.0F, -2.0F);
        this.shape1.addBox(-4.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
        this.shape11 = new ModelRenderer(this, 0, 90);
        this.shape11.mirror = true;
        this.shape11.setRotationPoint(4.0F, 7.0F, 3.0F);
        this.shape11.addBox(-2.5F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
        this.setRotation(shape11, -0.9948376736367678F, 0.0F, 0.0F);
        this.shape14 = new ModelRenderer(this, 0, 97);
        this.shape14.setRotationPoint(-1.5F, 7.0F, 3.0F);
        this.shape14.addBox(0.0F, 0.3F, -5.8F, 3, 2, 4, 0.0F);
        this.setRotation(shape14, -0.9948376736367678F, 0.0F, 0.0F);
        this.shape58 = new ModelRenderer(this, 63, 58);
        this.shape58.setRotationPoint(0.0F, 5.5F, -1.5F);
        this.shape58.addBox(-2.9F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.setRotation(shape58, 0.2617993877991494F, 0.0F, 0.0F);
        this.shape134 = new ModelRenderer(this, 0, 7);
        this.shape134.setRotationPoint(0.0F, 3.5F, 0.0F);
        this.shape134.addBox(-7.5F, -3.0F, 0.0F, 15, 1, 7, 0.0F);
        this.shape4 = new ModelRenderer(this, 12, 53);
        this.shape4.setRotationPoint(-3.0F, 7.0F, 0.0F);
        this.shape4.addBox(0.0F, -0.1F, -0.1F, 6, 7, 4, 0.0F);
        this.setRotation(shape4, 1.5707963267948966F, 0.0F, 0.0F);
        this.shape130 = new ModelRenderer(this, 0, 7);
        this.shape130.setRotationPoint(0.0F, 8.5F, 2.0F);
        this.shape130.addBox(-7.5F, -7.0F, 0.0F, 15, 1, 7, 0.0F);
        this.shape56.addChild(this.shape59);
        this.shape1.addChild(this.shape16);
        this.shape1.addChild(this.shape8);
        this.shape1.addChild(this.shape12);
        this.shape68.addChild(this.shape75);
        this.shape40.addChild(this.shape41);
        this.shape68.addChild(this.shape78);
        this.shape30.addChild(this.shape35);
        this.shape30.addChild(this.shape31);
        this.shape56.addChild(this.shape66);
        this.shape68.addChild(this.shape77);
        this.shape1.addChild(this.shape10);
        this.shape1.addChild(this.shape13);
        this.shape30.addChild(this.shape32);
        this.shape40.addChild(this.shape49);
        this.shape68.addChild(this.shape70);
        this.shape68.addChild(this.shape69);
        this.shape40.addChild(this.shape44);
        this.shape40.addChild(this.shape43);
        this.shape1.addChild(this.shape2);
        this.shape56.addChild(this.shape64);
        this.shape1.addChild(this.shape6);
        this.shape56.addChild(this.shape67);
        this.shape1.addChild(this.shape7);
        this.shape30.addChild(this.shape33);
        this.shape1.addChild(this.shape3);
        this.shape1.addChild(this.shape15);
        this.shape1.addChild(this.shape11);
        this.shape1.addChild(this.shape14);
        this.shape56.addChild(this.shape58);
        this.shape1.addChild(this.shape4);
        this.shape1.addChild(this.shape130);
        vehicleBody = new ModelRenderer(this, 0, 0);
        vehicleBody.addBox(-4.0F, 0.0F, -2.0F, 0, 0, 0);
        vehicleBody.setRotationPoint(0.0F, 0.0F, 0.0F);

        // try
        // {
        // for (Field field : getClass().getDeclaredFields())
        // {
        // field.setAccessible(true);
        // Object value = field.get(this);
        //
        // if (value != null && value instanceof ModelRenderer)
        // {
        // ModelRenderer modelRenderer = (ModelRenderer)value;
        // this.addChildTo(modelRenderer, vehichleBody);
        // }
        // }
        // }
        // catch (Exception e)
        // {
        // e.printStackTrace();
        // }

        // this.addChildTo(shape1, vehichleBody);
        // this.addChildTo(shape2, vehichleBody);
        // this.addChildTo(shape3, vehichleBody);
        // this.addChildTo(shape4, vehichleBody);
        // this.addChildTo(shape6, vehichleBody);
        // this.addChildTo(shape7, vehichleBody);
        // this.addChildTo(shape8, vehichleBody);
        // this.addChildTo(shape10, vehichleBody);
        // this.addChildTo(shape11, vehichleBody);
        // this.addChildTo(shape12, vehichleBody);
        // this.addChildTo(shape13, vehichleBody);
        // this.addChildTo(shape14, vehichleBody);
        // this.addChildTo(shape15, vehichleBody);
        // this.addChildTo(shape16, vehichleBody);
        // this.addChildTo(shape30, vehichleBody);
        // this.addChildTo(shape31, vehichleBody);
        // this.addChildTo(shape32, vehichleBody);
        // this.addChildTo(shape33, vehichleBody);
        // this.addChildTo(shape35, vehichleBody);
        // this.addChildTo(shape40, vehichleBody);
        // this.addChildTo(shape41, vehichleBody);
        // this.addChildTo(shape43, vehichleBody);
        // this.addChildTo(shape44, vehichleBody);
        // this.addChildTo(shape49, vehichleBody);
        // this.addChildTo(shape56, vehichleBody);
        // this.addChildTo(shape58, vehichleBody);
        // this.addChildTo(shape59, vehichleBody);
        // this.addChildTo(shape64, vehichleBody);
        // this.addChildTo(shape66, vehichleBody);
        // this.addChildTo(shape67, vehichleBody);
        // this.addChildTo(shape68, vehichleBody);
        // this.addChildTo(shape69, vehichleBody);
        // this.addChildTo(shape70, vehichleBody);
        // this.addChildTo(shape75, vehichleBody);
        // this.addChildTo(shape77, vehichleBody);
        // this.addChildTo(shape78, vehichleBody);

        this.addChildTo(shape1, vehicleBody);
        this.addChildTo(shape30, vehicleBody);
        this.addChildTo(shape56, vehicleBody);
        this.addChildTo(shape40, vehicleBody);
        this.addChildTo(shape68, vehicleBody);
        this.addChildTo(shape130, vehicleBody);
        this.addChildTo(shape131, vehicleBody);
        this.addChildTo(shape132, vehicleBody);
        this.addChildTo(shape133, vehicleBody);
        this.addChildTo(shape134, vehicleBody);
        this.addChildTo(shape136, vehicleBody);
        this.addChildTo(shape137, vehicleBody);
        this.addChildTo(shape138, vehicleBody);
        this.addChildTo(shape139, vehicleBody);
        this.addChildTo(shape140, vehicleBody);
        this.addChildTo(shape141, vehicleBody);

        vehicleBody.offsetY = 1.2F;
    }

    public void render()
    {
        vehicleBody.render(0.0625F);
        vehicleBody.offsetY = 1.2F;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}