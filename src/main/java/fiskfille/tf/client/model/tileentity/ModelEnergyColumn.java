package fiskfille.tf.client.model.tileentity;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.tools.ModelRendererBreakable;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tileentity.TileEntityColumn;

public class ModelEnergyColumn extends MowzieModelBase
{
    public MowzieModelRenderer columnBase1;
    public MowzieModelRenderer top1;
    public MowzieModelRenderer bottom1;
    public MowzieModelRenderer connector1;
    public MowzieModelRenderer columnBase2;
    public MowzieModelRenderer columnBase3;
    public MowzieModelRenderer columnBase4;
    public MowzieModelRenderer columnBase5;
    public MowzieModelRenderer columnBase6;
    public MowzieModelRenderer panel;
    public MowzieModelRenderer columnBase7;
    public MowzieModelRenderer columnBase8;
    public MowzieModelRenderer top2;
    public MowzieModelRenderer top3;
    public MowzieModelRenderer top4;
    public MowzieModelRenderer top5;
    public MowzieModelRenderer top6;
    public MowzieModelRenderer top7;
    public MowzieModelRenderer top8;
    public MowzieModelRenderer bottom2;
    public MowzieModelRenderer bottom3;
    public MowzieModelRenderer bottom4;
    public MowzieModelRenderer bottom5;
    public MowzieModelRenderer bottom6;
    public MowzieModelRenderer bottom7;
    public MowzieModelRenderer bottom8;
    public MowzieModelRenderer connector2;
    public MowzieModelRenderer connector3;
    public MowzieModelRenderer connector4;
    public MowzieModelRenderer connector5;

    public ModelEnergyColumn()
    {
        textureWidth = 128;
        textureHeight = 64;
        top1 = new ModelRendererBreakable(this, 0, 36);
        top1.setRotationPoint(0.0F, -8.0F, 0.0F);
        top1.addBox(-1.5F, 0.0F, -0.38F, 3, 1, 4, 0.0F);
        top7 = new ModelRendererBreakable(this, 0, 36);
        top7.setRotationPoint(0.0F, 0.0F, 0.0F);
        top7.addBox(-1.5F, 0.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(top7, 0.0F, 0.7853981633974483F, 0.0F);
        connector5 = new ModelRendererBreakable(this, 18, 41);
        connector5.setRotationPoint(0.0F, -3.0F, 0.0F);
        connector5.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F);
        setRotateAngle(connector5, -0.3490658503988659F, 0.0F, 0.0F);
        connector4 = new ModelRendererBreakable(this, 22, 36);
        connector4.setRotationPoint(0.0F, -3.0F, 0.0F);
        connector4.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F);
        setRotateAngle(connector4, -0.3490658503988659F, 0.0F, 0.0F);
        bottom3 = new ModelRendererBreakable(this, 0, 31);
        bottom3.setRotationPoint(0.0F, 0.0F, 0.0F);
        bottom3.addBox(-1.5F, -1.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(bottom3, 0.0F, 0.7853981633974483F, 0.0F);
        bottom2 = new ModelRendererBreakable(this, 0, 31);
        bottom2.setRotationPoint(0.0F, 0.0F, 0.0F);
        bottom2.addBox(-1.5F, -1.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(bottom2, 0.0F, 0.7853981633974483F, 0.0F);
        connector1 = new ModelRendererBreakable(this, 0, 41);
        connector1.setRotationPoint(0.0F, 16.0F, 0.0F);
        connector1.addBox(-1.0F, -4.0F, -8.0F, 2, 4, 2, 0.0F);
        setRotateAngle(connector1, 0.0F, 0.5235987755982988F, 0.0F);
        columnBase5 = new ModelRendererBreakable(this, 40, 0);
        columnBase5.setRotationPoint(0.0F, 0.0F, 0.0F);
        columnBase5.addBox(-2.0F, -31.0F, 3.83F, 4, 30, 1, 0.0F);
        setRotateAngle(columnBase5, 0.0F, 0.7853981633974483F, 0.0F);
        top5 = new ModelRendererBreakable(this, 0, 36);
        top5.setRotationPoint(0.0F, 0.0F, 0.0F);
        top5.addBox(-1.5F, 0.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(top5, 0.0F, 0.7853981633974483F, 0.0F);
        bottom6 = new ModelRendererBreakable(this, 0, 31);
        bottom6.setRotationPoint(0.0F, 0.0F, 0.0F);
        bottom6.addBox(-1.5F, -1.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(bottom6, 0.0F, 0.7853981633974483F, 0.0F);
        top3 = new ModelRendererBreakable(this, 0, 36);
        top3.setRotationPoint(0.0F, 0.0F, 0.0F);
        top3.addBox(-1.5F, 0.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(top3, 0.0F, 0.7853981633974483F, 0.0F);
        connector3 = new ModelRendererBreakable(this, 22, 31);
        connector3.setRotationPoint(0.0F, -14.0F, -8.0F);
        connector3.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F);
        setRotateAngle(connector3, -0.17453292519943295F, 0.0F, 0.0F);
        connector2 = new ModelRendererBreakable(this, 14, 31);
        connector2.setRotationPoint(0.0F, 0.0F, -8.0F);
        connector2.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        setRotateAngle(connector2, 0.6632251157578453F, 0.0F, 0.0F);
        top8 = new ModelRendererBreakable(this, 0, 36);
        top8.setRotationPoint(0.0F, 0.0F, 0.0F);
        top8.addBox(-1.5F, 0.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(top8, 0.0F, 0.7853981633974483F, 0.0F);
        top4 = new ModelRendererBreakable(this, 0, 36);
        top4.setRotationPoint(0.0F, 0.0F, 0.0F);
        top4.addBox(-1.5F, 0.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(top4, 0.0F, 0.7853981633974483F, 0.0F);
        bottom7 = new ModelRendererBreakable(this, 0, 31);
        bottom7.setRotationPoint(0.0F, 0.0F, 0.0F);
        bottom7.addBox(-1.5F, -1.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(bottom7, 0.0F, 0.7853981633974483F, 0.0F);
        columnBase3 = new ModelRendererBreakable(this, 20, 0);
        columnBase3.setRotationPoint(0.0F, 0.0F, 0.0F);
        columnBase3.addBox(-2.0F, -31.0F, 3.83F, 4, 30, 1, 0.0F);
        setRotateAngle(columnBase3, 0.0F, 0.7853981633974483F, 0.0F);
        columnBase6 = new ModelRendererBreakable(this, 50, 0);
        columnBase6.setRotationPoint(0.0F, 0.0F, 0.0F);
        columnBase6.addBox(-2.0F, -31.0F, 3.83F, 4, 30, 1, 0.0F);
        setRotateAngle(columnBase6, 0.0F, 0.7853981633974483F, 0.0F);
        columnBase8 = new ModelRendererBreakable(this, 70, 0);
        columnBase8.setRotationPoint(0.0F, 0.0F, 0.0F);
        columnBase8.addBox(-2.0F, -31.0F, 3.83F, 4, 30, 1, 0.0F);
        setRotateAngle(columnBase8, 0.0F, 0.7853981633974483F, 0.0F);
        top6 = new ModelRendererBreakable(this, 0, 36);
        top6.setRotationPoint(0.0F, 0.0F, 0.0F);
        top6.addBox(-1.5F, 0.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(top6, 0.0F, 0.7853981633974483F, 0.0F);
        bottom5 = new ModelRendererBreakable(this, 0, 31);
        bottom5.setRotationPoint(0.0F, 0.0F, 0.0F);
        bottom5.addBox(-1.5F, -1.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(bottom5, 0.0F, 0.7853981633974483F, 0.0F);
        columnBase1 = new ModelRendererBreakable(this, 0, 0);
        columnBase1.setRotationPoint(0.0F, 24.0F, 0.0F);
        columnBase1.addBox(-2.0F, -31.0F, 3.83F, 4, 30, 1, 0.0F);
        columnBase7 = new ModelRendererBreakable(this, 60, 0);
        columnBase7.setRotationPoint(0.0F, 0.0F, 0.0F);
        columnBase7.addBox(-2.0F, -31.0F, 3.83F, 4, 30, 1, 0.0F);
        setRotateAngle(columnBase7, 0.0F, 0.7853981633974483F, 0.0F);
        top2 = new ModelRendererBreakable(this, 0, 36);
        top2.setRotationPoint(0.0F, 0.0F, 0.0F);
        top2.addBox(-1.5F, 0.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(top2, 0.0F, 0.7853981633974483F, 0.0F);
        bottom1 = new ModelRendererBreakable(this, 0, 31);
        bottom1.setRotationPoint(0.0F, 24.0F, 0.0F);
        bottom1.addBox(-1.5F, -1.0F, -0.38F, 3, 1, 4, 0.0F);
        columnBase2 = new ModelRendererBreakable(this, 10, 0);
        columnBase2.setRotationPoint(0.0F, 0.0F, 0.0F);
        columnBase2.addBox(-2.0F, -31.0F, 3.83F, 4, 30, 1, 0.0F);
        setRotateAngle(columnBase2, 0.0F, 0.7853981633974483F, 0.0F);
        panel = new ModelRendererBreakable(this, 8, 41);
        panel.setRotationPoint(0.0F, -17.0F, 4.3F);
        panel.addBox(-2.0F, -3.0F, 0.0F, 4, 6, 1, 0.0F);
        bottom8 = new ModelRendererBreakable(this, 0, 31);
        bottom8.setRotationPoint(0.0F, 0.0F, 0.0F);
        bottom8.addBox(-1.5F, -1.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(bottom8, 0.0F, 0.7853981633974483F, 0.0F);
        bottom4 = new ModelRendererBreakable(this, 0, 31);
        bottom4.setRotationPoint(0.0F, 0.0F, 0.0F);
        bottom4.addBox(-1.5F, -1.0F, -0.38F, 3, 1, 4, 0.0F);
        setRotateAngle(bottom4, 0.0F, 0.7853981633974483F, 0.0F);
        columnBase4 = new ModelRendererBreakable(this, 30, 0);
        columnBase4.setRotationPoint(0.0F, 0.0F, 0.0F);
        columnBase4.addBox(-2.0F, -31.0F, 3.83F, 4, 30, 1, 0.0F);
        setRotateAngle(columnBase4, 0.0F, 0.7853981633974483F, 0.0F);
        top6.addChild(top7);
        connector4.addChild(connector5);
        connector3.addChild(connector4);
        bottom2.addChild(bottom3);
        bottom1.addChild(bottom2);
        columnBase4.addChild(columnBase5);
        top4.addChild(top5);
        bottom5.addChild(bottom6);
        top2.addChild(top3);
        connector1.addChild(connector3);
        connector1.addChild(connector2);
        top7.addChild(top8);
        top3.addChild(top4);
        bottom6.addChild(bottom7);
        columnBase2.addChild(columnBase3);
        columnBase5.addChild(columnBase6);
        columnBase7.addChild(columnBase8);
        top5.addChild(top6);
        bottom4.addChild(bottom5);
        columnBase6.addChild(columnBase7);
        top1.addChild(top2);
        columnBase1.addChild(columnBase2);
        columnBase5.addChild(panel);
        bottom7.addChild(bottom8);
        bottom3.addChild(bottom4);
        columnBase3.addChild(columnBase4);

        setInitPose();
    }

    public void render(TileEntityColumn tile)
    {
        setToInitPose();

        GL11.glPushMatrix();
        GL11.glTranslatef(top1.offsetX, top1.offsetY, top1.offsetZ);
        GL11.glTranslatef(top1.rotationPointX * 0.0625F, top1.rotationPointY * 0.0625F, top1.rotationPointZ * 0.0625F);
        GL11.glScaled(1.2D, 1.2D, 1.2D);
        GL11.glTranslatef(-top1.offsetX, -top1.offsetY, -top1.offsetZ);
        GL11.glTranslatef(-top1.rotationPointX * 0.0625F, -top1.rotationPointY * 0.0625F, -top1.rotationPointZ * 0.0625F);
        top1.render(0.0625F);
        GL11.glPopMatrix();
        columnBase1.render(0.0625F);
        GL11.glPushMatrix();
        GL11.glTranslatef(bottom1.offsetX, bottom1.offsetY, bottom1.offsetZ);
        GL11.glTranslatef(bottom1.rotationPointX * 0.0625F, bottom1.rotationPointY * 0.0625F, bottom1.rotationPointZ * 0.0625F);
        GL11.glScaled(1.2D, 1.2D, 1.2D);
        GL11.glTranslatef(-bottom1.offsetX, -bottom1.offsetY, -bottom1.offsetZ);
        GL11.glTranslatef(-bottom1.rotationPointX * 0.0625F, -bottom1.rotationPointY * 0.0625F, -bottom1.rotationPointZ * 0.0625F);
        bottom1.render(0.0625F);
        GL11.glPopMatrix();

        for (int i = 0; i < tile.getSizeInventory(); ++i)
        {
            if (tile.getStackInSlot(i) != null && tile.getStackInSlot(i).getItem() == TFItems.powerCanister)
            {
                connector1.rotateAngleY = (float) Math.toRadians((i + 0.5F) * 360F / tile.getSizeInventory());
                connector1.render(0.0625F);
            }
        }
    }
}
