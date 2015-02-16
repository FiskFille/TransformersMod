package fiskfille.tf.client.model.player;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
<<<<<<< HEAD:src/main/java/fiskfille/tf/model/player/ModelBipedTF.java
import fiskfille.tf.item.TFItems;
import fiskfille.tf.item.armor.ITransformerArmor;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;

@SideOnly(Side.CLIENT)
public class ModelBipedTF extends Biped
=======
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;

@SideOnly(Side.CLIENT)
public class ModelPlayerTF extends ModelBiped
>>>>>>> parent of 52b2aa7... Revert "fix it, gegs":src/main/java/fiskfille/tf/client/model/player/ModelPlayerTF.java
{
	public ModelPlayerTF()
	{
<<<<<<< HEAD:src/main/java/fiskfille/tf/model/player/ModelBipedTF.java
		super();
	}
	
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float f5)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, f5, entity);
        
        if (entity instanceof EntityPlayer)
        {
        	EntityPlayer player = (EntityPlayer)entity;
        	
        	ItemStack helm = player.getCurrentArmor(3);
    		boolean wearingTransformerHelm = helm != null ? helm.getItem() instanceof ITransformerArmor : false;
    		ItemStack chest = player.getCurrentArmor(2);
    		boolean wearingTransformerChest = chest != null ? chest.getItem() instanceof ITransformerArmor : false;
    		ItemStack pants = player.getCurrentArmor(1);
    		boolean wearingTransformerPants = pants != null ? pants.getItem() instanceof ITransformerArmor : false;
            
            if (this.isChild)
            {
                float f6 = 2.0F;
                GL11.glPushMatrix();
                GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
                GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
                if (!wearingTransformerHelm) {this.bipedHead.render(f5);}
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
                GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
                if (!wearingTransformerChest) {this.bipedRightArm.render(f5);}
				if (!wearingTransformerChest) {this.bipedLeftArm.render(f5);}
				if (!wearingTransformerPants) {this.bipedRightLeg.render(f5);}
                if (!wearingTransformerPants) {this.bipedLeftLeg.render(f5);}
                if (!wearingTransformerHelm) {this.bipedHeadwear.render(f5);}
                GL11.glPopMatrix();
            }
            else
            {
            	if (!wearingTransformerHelm) {this.bipedHead.render(f5);}
            	if (!wearingTransformerChest) {this.bipedBody.render(f5);}
        		if (!wearingTransformerChest) {this.bipedRightArm.render(f5);}
				if (!wearingTransformerChest) {this.bipedLeftArm.render(f5);}
				if (!wearingTransformerPants) {this.bipedRightLeg.render(f5);}
                if (!wearingTransformerPants) {this.bipedLeftLeg.render(f5);}
                if (!wearingTransformerHelm) {this.bipedHeadwear.render(f5);}
            }
        }
        
    }
=======
		this(0.0F);
	}

	public ModelPlayerTF(float modelScale)
	{
		this(modelScale, 0.0F, 64, 32);
	}

	public ModelPlayerTF(float scaleFactor, float p_i1149_2_, int textureWidth, int textureHeight)
	{
		super(scaleFactor, p_i1149_2_, textureWidth, textureHeight);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void adjustPlayerVisibility(EntityPlayer player)
	{
		ItemStack helm = player.getCurrentArmor(3);
		boolean wearingTransformerHelm = helm != null && helm.getItem() instanceof ItemTransformerArmor;
		ItemStack chest = player.getCurrentArmor(2);
		boolean wearingTransformerChest = chest != null && chest.getItem() instanceof ItemTransformerArmor;
		ItemStack pants = player.getCurrentArmor(1);
		boolean wearingTransformerPants = pants != null && pants.getItem() instanceof ItemTransformerArmor;
		
		this.bipedHead.offsetY = wearingTransformerHelm ? 256 : 0;
		this.bipedHeadwear.offsetY = wearingTransformerHelm ? 256 : 0;
		this.bipedBody.offsetY = wearingTransformerChest ? 256 : 0;
		this.bipedRightArm.offsetY = wearingTransformerChest ? 256 : 0;
		this.bipedLeftArm.offsetY = wearingTransformerChest ? 256 : 0;
		this.bipedRightLeg.offsetY = wearingTransformerPants ? 256 : 0;
		this.bipedLeftLeg.offsetY = wearingTransformerPants ? 256 : 0;
	}
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			ItemStack itemstack = player.getHeldItem();
			
			if (itemstack != null && itemstack.getItem() == TFItems.vurpsSniper)
			{
	    		this.setRotation(this.bipedRightArm, -1.3F, bipedHead.rotateAngleY - 0.45F, 0.0F);
	    		this.setRotation(this.bipedLeftArm, -1.2F, bipedHead.rotateAngleY + 0.4F, 0.0F);
	    		this.bipedLeftArm.setRotationPoint(3.0F, 3.0F, -2.5F);
	    	}
	    	else
	    	{
	    		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
	    		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
	    	}
			
			adjustPlayerVisibility(player);
		}
	}
>>>>>>> parent of 52b2aa7... Revert "fix it, gegs":src/main/java/fiskfille/tf/client/model/player/ModelPlayerTF.java
}