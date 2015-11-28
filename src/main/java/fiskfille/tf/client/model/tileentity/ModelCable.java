package fiskfille.tf.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;

public class ModelCable
{
	public static class Straight extends MowzieModelBase
	{
		public MowzieModelRenderer cable1;
		public MowzieModelRenderer cable2;
		public MowzieModelRenderer cableCover1;
		public MowzieModelRenderer cableCover2;
		public MowzieModelRenderer cableCover3;
		public MowzieModelRenderer support1_1;
		public MowzieModelRenderer support1_2;
		public MowzieModelRenderer support1_3;
		public MowzieModelRenderer support2_1;
		public MowzieModelRenderer support2_2;
		public MowzieModelRenderer support2_3;
		public MowzieModelRenderer support3_1;
		public MowzieModelRenderer support3_2;
		public MowzieModelRenderer support3_3;

		public Straight()
		{
			this.textureWidth = 32;
			this.textureHeight = 32;
			this.support2_3 = new MowzieModelRenderer(this, 0, 0);
			this.support2_3.mirror = true;
			this.support2_3.setRotationPoint(3.03F, -0.2F, 0.0F);
			this.support2_3.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support2_3, 0.0F, 0.0F, -0.296705972839036F);
			this.cable1 = new MowzieModelRenderer(this, 0, 20);
			this.cable1.setRotationPoint(0.0F, 16.0F, 0.0F);
			this.cable1.addBox(-2.0F, -2.0F, -8.0F, 4, 4, 8, 0.0F);
			this.cable2 = new MowzieModelRenderer(this, 0, 20);
			this.cable2.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.cable2.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 8, 0.0F);
			this.support3_1 = new MowzieModelRenderer(this, 0, 16);
			this.support3_1.setRotationPoint(0.0F, 7.5F, 0.0F);
			this.support3_1.addBox(-4.0F, -0.5F, -1.5F, 8, 1, 3, 0.0F);
			this.cableCover1 = new MowzieModelRenderer(this, 16, 21);
			this.cableCover1.setRotationPoint(0.0F, 0.0F, -5.25F);
			this.cableCover1.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
			this.support2_2 = new MowzieModelRenderer(this, 0, 0);
			this.support2_2.setRotationPoint(-3.03F, -0.2F, 0.0F);
			this.support2_2.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support2_2, 0.0F, 0.0F, 0.296705972839036F);
			this.support1_2 = new MowzieModelRenderer(this, 0, 0);
			this.support1_2.setRotationPoint(-3.03F, -0.2F, 0.0F);
			this.support1_2.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support1_2, 0.0F, 0.0F, 0.296705972839036F);
			this.cableCover2 = new MowzieModelRenderer(this, 16, 21);
			this.cableCover2.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.cableCover2.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
			this.cableCover3 = new MowzieModelRenderer(this, 16, 21);
			this.cableCover3.setRotationPoint(0.0F, 0.0F, 5.25F);
			this.cableCover3.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
			this.support1_1 = new MowzieModelRenderer(this, 0, 16);
			this.support1_1.setRotationPoint(0.0F, 7.5F, 0.0F);
			this.support1_1.addBox(-4.0F, -0.5F, -1.5F, 8, 1, 3, 0.0F);
			this.support3_2 = new MowzieModelRenderer(this, 0, 0);
			this.support3_2.setRotationPoint(-3.03F, -0.2F, 0.0F);
			this.support3_2.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support3_2, 0.0F, 0.0F, 0.296705972839036F);
			this.support2_1 = new MowzieModelRenderer(this, 0, 16);
			this.support2_1.setRotationPoint(0.0F, 7.5F, 0.0F);
			this.support2_1.addBox(-4.0F, -0.5F, -1.5F, 8, 1, 3, 0.0F);
			this.support1_3 = new MowzieModelRenderer(this, 0, 0);
			this.support1_3.mirror = true;
			this.support1_3.setRotationPoint(3.03F, -0.2F, 0.0F);
			this.support1_3.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support1_3, 0.0F, 0.0F, -0.296705972839036F);
			this.support3_3 = new MowzieModelRenderer(this, 0, 0);
			this.support3_3.mirror = true;
			this.support3_3.setRotationPoint(3.03F, -0.2F, 0.0F);
			this.support3_3.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support3_3, 0.0F, 0.0F, -0.296705972839036F);
			this.support2_1.addChild(this.support2_3);
			this.cable1.addChild(this.cable2);
			this.cableCover3.addChild(this.support3_1);
			this.cable1.addChild(this.cableCover1);
			this.support2_1.addChild(this.support2_2);
			this.support1_1.addChild(this.support1_2);
			this.cable1.addChild(this.cableCover2);
			this.cable2.addChild(this.cableCover3);
			this.cableCover1.addChild(this.support1_1);
			this.support3_1.addChild(this.support3_2);
			this.cableCover2.addChild(this.support2_1);
			this.support1_1.addChild(this.support1_3);
			this.support3_1.addChild(this.support3_3);
			setInitPose();
		}

		public void render(boolean renderSupports)
		{
			this.support1_1.showModel = renderSupports;
			this.support2_1.showModel = renderSupports;
			this.support3_1.showModel = renderSupports;
			this.cable1.render(0.0625F);
			setToInitPose();
		}

		public void setRotateAngle(MowzieModelRenderer modelRenderer, float x, float y, float z)
		{
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}

	public static class Turned extends MowzieModelBase
	{
		public MowzieModelRenderer cable1;
		public MowzieModelRenderer cable2;
		public MowzieModelRenderer cableCover1;
		public MowzieModelRenderer cableCover2;
		public MowzieModelRenderer cableCover3;
		public MowzieModelRenderer support1_1;
		public MowzieModelRenderer support1_2;
		public MowzieModelRenderer support1_3;
		public MowzieModelRenderer support2_1;
		public MowzieModelRenderer support2_2;
		public MowzieModelRenderer support2_3;
		public MowzieModelRenderer support3_1;
		public MowzieModelRenderer support3_2;
		public MowzieModelRenderer support3_3;

		public Turned()
		{
			this.textureWidth = 32;
			this.textureHeight = 32;
			this.support1_2 = new MowzieModelRenderer(this, 0, 0);
			this.support1_2.mirror = true;
			this.support1_2.setRotationPoint(3.03F, -0.2F, 0.0F);
			this.support1_2.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support1_2, 0.0F, 0.0F, -0.296705972839036F);
			this.support3_2 = new MowzieModelRenderer(this, 0, 0);
			this.support3_2.mirror = true;
			this.support3_2.setRotationPoint(3.03F, -0.2F, 0.0F);
			this.support3_2.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support3_2, 0.0F, 0.0F, -0.296705972839036F);
			this.support3_1 = new MowzieModelRenderer(this, 0, 16);
			this.support3_1.setRotationPoint(0.0F, 7.5F, 0.0F);
			this.support3_1.addBox(-4.0F, -0.5F, -1.5F, 8, 1, 3, 0.0F);
			this.cableCover1 = new MowzieModelRenderer(this, 16, 21);
			this.cableCover1.setRotationPoint(0.0F, 0.0F, -5.25F);
			this.cableCover1.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
			this.support2_2 = new MowzieModelRenderer(this, 0, 0);
			this.support2_2.mirror = true;
			this.support2_2.setRotationPoint(3.03F, -0.2F, 0.0F);
			this.support2_2.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support2_2, 0.0F, 0.0F, -0.296705972839036F);
			this.support2_3 = new MowzieModelRenderer(this, 0, 0);
			this.support2_3.setRotationPoint(-3.03F, -0.2F, 0.0F);
			this.support2_3.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support2_3, 0.0F, 0.0F, 0.296705972839036F);
			this.cableCover3 = new MowzieModelRenderer(this, 16, 21);
			this.cableCover3.setRotationPoint(0.0F, 0.0F, 5.25F);
			this.cableCover3.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
			this.support1_3 = new MowzieModelRenderer(this, 0, 0);
			this.support1_3.setRotationPoint(-3.03F, -0.2F, 0.0F);
			this.support1_3.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support1_3, 0.0F, 0.0F, 0.296705972839036F);
			this.support3_3 = new MowzieModelRenderer(this, 0, 0);
			this.support3_3.setRotationPoint(-3.03F, -0.2F, 0.0F);
			this.support3_3.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
			this.setRotateAngle(support3_3, 0.0F, 0.0F, 0.296705972839036F);
			this.cableCover2 = new MowzieModelRenderer(this, 2, 4);
			this.cableCover2.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.cableCover2.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
			this.cable2 = new MowzieModelRenderer(this, 0, 20);
			this.cable2.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.cable2.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 8, 0.0F);
			this.setRotateAngle(cable2, 0.0F, 1.5707963267948966F, 0.0F);
			this.support2_1 = new MowzieModelRenderer(this, 0, 16);
			this.support2_1.setRotationPoint(0.0F, 7.5F, 0.0F);
			this.support2_1.addBox(-4.0F, -0.5F, -1.5F, 8, 1, 3, 0.0F);
			this.setRotateAngle(support2_1, 0.0F, 0.7853981633974483F, 0.0F);
			this.cable1 = new MowzieModelRenderer(this, 0, 20);
			this.cable1.setRotationPoint(0.0F, 16.0F, 0.0F);
			this.cable1.addBox(-2.0F, -2.0F, -8.0F, 4, 4, 8, 0.0F);
			this.support1_1 = new MowzieModelRenderer(this, 0, 16);
			this.support1_1.setRotationPoint(0.0F, 7.5F, 0.0F);
			this.support1_1.addBox(-4.0F, -0.5F, -1.5F, 8, 1, 3, 0.0F);
			this.support1_1.addChild(this.support1_2);
			this.support3_1.addChild(this.support3_2);
			this.cableCover3.addChild(this.support3_1);
			this.cable1.addChild(this.cableCover1);
			this.support2_1.addChild(this.support2_2);
			this.support2_1.addChild(this.support2_3);
			this.cable2.addChild(this.cableCover3);
			this.support1_1.addChild(this.support1_3);
			this.support3_1.addChild(this.support3_3);
			this.cable1.addChild(this.cableCover2);
			this.cable1.addChild(this.cable2);
			this.cableCover2.addChild(this.support2_1);
			this.cableCover1.addChild(this.support1_1);
			setInitPose();
		}

		public void render(boolean renderSupports)
		{
			this.support1_1.showModel = renderSupports;
			this.support2_1.showModel = renderSupports;
			this.support3_1.showModel = renderSupports;
			this.cable1.render(0.0625F);
			setToInitPose();
		}

		public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
		{
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
