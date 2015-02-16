package fiskfille.tf.model.player;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.item.armor.ITransformerArmor;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;

@SideOnly(Side.CLIENT)
public class ModelPlayerTF extends Biped
{
	public ModelPlayerTF()
	{
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
}