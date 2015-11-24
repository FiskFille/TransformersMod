package fiskfille.tf.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCable
{
	public static class Straight extends ModelBase
	{
		public ModelRenderer cable1;
	    public ModelRenderer cable2;
	    public ModelRenderer cableCover1;
	    public ModelRenderer cableCover2;
	    public ModelRenderer cableCover3;
	    public ModelRenderer support1_1;
	    public ModelRenderer support1_2;
	    public ModelRenderer support1_3;
	    public ModelRenderer support2_1;
	    public ModelRenderer support2_2;
	    public ModelRenderer support2_3;
	    public ModelRenderer support3_1;
	    public ModelRenderer support3_2;
	    public ModelRenderer support3_3;

	    public Straight()
	    {
	        this.textureWidth = 32;
	        this.textureHeight = 32;
	        this.cable2 = new ModelRenderer(this, 0, 20);
	        this.cable2.setRotationPoint(0.0F, 0.0F, 8.0F);
	        this.cable2.addBox(-0.5F, -0.5F, 0.0F, 4, 4, 8, 0.0F);
	        this.cable1 = new ModelRenderer(this, 0, 20);
	        this.cable1.setRotationPoint(-1.5F, 16.0F, -8.0F);
	        this.cable1.addBox(-0.5F, -0.5F, 0.0F, 4, 4, 8, 0.0F);
	        this.support2_3 = new ModelRenderer(this, 0, 0);
	        this.support2_3.mirror = true;
	        this.support2_3.setRotationPoint(6.5F, -4.8F, 1.0F);
	        this.support2_3.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support2_3, 0.0F, 0.0F, -0.296705972839036F);
	        this.support2_1 = new ModelRenderer(this, 0, 16);
	        this.support2_1.setRotationPoint(-1.5F, 8.0F, -0.5F);
	        this.support2_1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
	        this.cableCover3 = new ModelRenderer(this, 16, 21);
	        this.cableCover3.setRotationPoint(-1.0F, -1.0F, 12.0F);
	        this.cableCover3.addBox(0.0F, 0.0F, 0.0F, 5, 5, 2, 0.0F);
	        this.cableCover1 = new ModelRenderer(this, 16, 21);
	        this.cableCover1.setRotationPoint(-1.0F, -1.0F, 2.0F);
	        this.cableCover1.addBox(0.0F, 0.0F, 0.0F, 5, 5, 2, 0.0F);
	        this.support2_2 = new ModelRenderer(this, 0, 0);
	        this.support2_2.setRotationPoint(1.5F, -4.8F, 1.0F);
	        this.support2_2.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support2_2, 0.0F, 0.0F, 0.296705972839036F);
	        this.support3_1 = new ModelRenderer(this, 0, 16);
	        this.support3_1.setRotationPoint(-1.5F, 8.0F, -0.5F);
	        this.support3_1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
	        this.support1_2 = new ModelRenderer(this, 0, 0);
	        this.support1_2.setRotationPoint(1.5F, -4.8F, 1.0F);
	        this.support1_2.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support1_2, 0.0F, 0.0F, 0.296705972839036F);
	        this.support3_3 = new ModelRenderer(this, 0, 0);
	        this.support3_3.setRotationPoint(1.5F, -4.8F, 1.0F);
	        this.support3_3.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support3_3, 0.0F, 0.0F, 0.296705972839036F);
	        this.cableCover2 = new ModelRenderer(this, 16, 21);
	        this.cableCover2.setRotationPoint(-1.0F, -1.0F, 7.0F);
	        this.cableCover2.addBox(0.0F, 0.0F, 0.0F, 5, 5, 2, 0.0F);
	        this.support3_2 = new ModelRenderer(this, 0, 0);
	        this.support3_2.mirror = true;
	        this.support3_2.setRotationPoint(6.5F, -4.8F, 1.0F);
	        this.support3_2.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support3_2, 0.0F, 0.0F, -0.296705972839036F);
	        this.support1_3 = new ModelRenderer(this, 0, 0);
	        this.support1_3.mirror = true;
	        this.support1_3.setRotationPoint(6.5F, -4.8F, 1.0F);
	        this.support1_3.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support1_3, 0.0F, 0.0F, -0.296705972839036F);
	        this.support1_1 = new ModelRenderer(this, 0, 16);
	        this.support1_1.setRotationPoint(-1.5F, 8.0F, -0.5F);
	        this.support1_1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
	        this.cable1.addChild(this.cable2);
	        this.support2_1.addChild(this.support2_3);
	        this.cableCover2.addChild(this.support2_1);
	        this.cable1.addChild(this.cableCover3);
	        this.cable1.addChild(this.cableCover1);
	        this.support2_1.addChild(this.support2_2);
	        this.cableCover3.addChild(this.support3_1);
	        this.support1_1.addChild(this.support1_2);
	        this.support3_1.addChild(this.support3_3);
	        this.cable1.addChild(this.cableCover2);
	        this.support3_1.addChild(this.support3_2);
	        this.support1_1.addChild(this.support1_3);
	        this.cableCover1.addChild(this.support1_1);
	    }

	    public void render(boolean renderSupports)
	    {
	    	this.support1_1.showModel = renderSupports;
	    	this.support2_1.showModel = renderSupports;
	    	this.support3_1.showModel = renderSupports;
	        this.cable1.render(0.0625F);
	    }
	    
	    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	    {
	        modelRenderer.rotateAngleX = x;
	        modelRenderer.rotateAngleY = y;
	        modelRenderer.rotateAngleZ = z;
	    }
	}
	
	public static class Turned extends ModelBase
	{
		public ModelRenderer cable1;
	    public ModelRenderer cable2;
	    public ModelRenderer cableCover1;
	    public ModelRenderer cableCover2;
	    public ModelRenderer cableCover3;
	    public ModelRenderer support1_1;
	    public ModelRenderer support1_2;
	    public ModelRenderer support1_3;
	    public ModelRenderer support2_1;
	    public ModelRenderer support2_2;
	    public ModelRenderer support2_3;
	    public ModelRenderer support3_1;
	    public ModelRenderer support3_2;
	    public ModelRenderer support3_3;

	    public Turned()
	    {
	        this.textureWidth = 32;
	        this.textureHeight = 32;
	        this.cable1 = new ModelRenderer(this, 0, 20);
	        this.cable1.setRotationPoint(-1.5F, 16.0F, -8.0F);
	        this.cable1.addBox(-0.5F, -0.5F, 0.0F, 4, 4, 8, 0.0F);
	        this.support2_3 = new ModelRenderer(this, 0, 0);
	        this.support2_3.mirror = true;
	        this.support2_3.setRotationPoint(6.5F, -4.8F, 1.0F);
	        this.support2_3.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support2_3, 0.0F, 0.0F, -0.296705972839036F);
	        this.support2_2 = new ModelRenderer(this, 0, 0);
	        this.support2_2.setRotationPoint(1.5F, -4.8F, 1.0F);
	        this.support2_2.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support2_2, 0.0F, 0.0F, 0.296705972839036F);
	        this.support2_1 = new ModelRenderer(this, 0, 16);
	        this.support2_1.setRotationPoint(-1.5F, 8.0F, -0.5F);
	        this.support2_1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
	        this.support3_2 = new ModelRenderer(this, 0, 0);
	        this.support3_2.mirror = true;
	        this.support3_2.setRotationPoint(6.5F, -4.8F, -0.5F);
	        this.support3_2.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support3_2, 0.0F, 0.0F, -0.296705972839036F);
	        this.support3_1 = new ModelRenderer(this, 0, 16);
	        this.support3_1.setRotationPoint(-0.5F, 8.0F, 5.5F);
	        this.support3_1.addBox(0.0F, 0.0F, -1.5F, 8, 1, 3, 0.0F);
	        this.setRotateAngle(support3_1, 0.0F, 0.7853981633974483F, 0.0F);
	        this.support3_3 = new ModelRenderer(this, 0, 0);
	        this.support3_3.setRotationPoint(1.5F, -4.8F, -0.5F);
	        this.support3_3.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support3_3, 0.0F, 0.0F, 0.296705972839036F);
	        this.cableCover2 = new ModelRenderer(this, 16, 21);
	        this.cableCover2.setRotationPoint(-1.0F, -1.0F, 2.0F);
	        this.cableCover2.addBox(0.0F, 0.0F, 0.0F, 5, 5, 2, 0.0F);
	        this.cable2 = new ModelRenderer(this, 0, 20);
	        this.cable2.setRotationPoint(0.0F, 0.0F, 10.0F);
	        this.cable2.addBox(0.0F, -0.5F, 1.5F, 4, 4, 8, 0.0F);
	        this.setRotateAngle(cable2, 0.0F, 1.5707963267948966F, 0.0F);
	        this.cableCover1 = new ModelRenderer(this, 16, 21);
	        this.cableCover1.setRotationPoint(-0.5F, -1.0F, 5.5F);
	        this.cableCover1.addBox(0.0F, 0.0F, 0.0F, 5, 5, 2, 0.0F);
	        this.cableCover3 = new ModelRenderer(this, 2, 4);
	        this.cableCover3.setRotationPoint(-1.0F, -1.0F, 5.5F);
	        this.cableCover3.addBox(0.0F, 0.0F, 0.0F, 5, 5, 5, 0.0F);
	        this.support1_1 = new ModelRenderer(this, 0, 16);
	        this.support1_1.setRotationPoint(-1.5F, 8.0F, -0.5F);
	        this.support1_1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
	        this.support1_3 = new ModelRenderer(this, 0, 0);
	        this.support1_3.setRotationPoint(1.5F, -4.8F, 1.0F);
	        this.support1_3.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support1_3, 0.0F, 0.0F, 0.296705972839036F);
	        this.support1_2 = new ModelRenderer(this, 0, 0);
	        this.support1_2.mirror = true;
	        this.support1_2.setRotationPoint(6.5F, -4.8F, 1.0F);
	        this.support1_2.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
	        this.setRotateAngle(support1_2, 0.0F, 0.0F, -0.296705972839036F);
	        this.support2_1.addChild(this.support2_3);
	        this.support2_1.addChild(this.support2_2);
	        this.cableCover2.addChild(this.support2_1);
	        this.support3_1.addChild(this.support3_2);
	        this.cableCover3.addChild(this.support3_1);
	        this.support3_1.addChild(this.support3_3);
	        this.cable1.addChild(this.cableCover2);
	        this.cable1.addChild(this.cable2);
	        this.cable2.addChild(this.cableCover1);
	        this.cable1.addChild(this.cableCover3);
	        this.cableCover1.addChild(this.support1_1);
	        this.support1_1.addChild(this.support1_3);
	        this.support1_1.addChild(this.support1_2);
	    }

	    public void render(boolean renderSupports)
	    {
	    	this.support1_1.showModel = renderSupports;
	    	this.support2_1.showModel = renderSupports;
	    	this.support3_1.showModel = renderSupports;
	        this.cable1.render(0.0625F);
	    }
	    
	    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	    {
	        modelRenderer.rotateAngleX = x;
	        modelRenderer.rotateAngleY = y;
	        modelRenderer.rotateAngleZ = z;
	    }
	}
}
