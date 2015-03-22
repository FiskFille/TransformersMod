package fiskfille.tf.client.model.transformer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.helper.TFModelHelper;

public class ModelChildBase
{
    public static class Base extends ModelBase
    {
        public float pi = (float) Math.PI;
        
        protected void addChildTo(ModelRenderer child, ModelRenderer parent)
        {
            child.rotationPointX -= parent.rotationPointX;
            child.rotationPointY -= parent.rotationPointY;
            child.rotationPointZ -= parent.rotationPointZ;
            
            child.rotateAngleX -= parent.rotateAngleX;
            child.rotateAngleY -= parent.rotateAngleY;
            child.rotateAngleZ -= parent.rotateAngleZ;
            parent.addChild(child);
        }
        
        protected void addChildToWithoutPoint(ModelRenderer child, ModelRenderer parent)
        {
            child.rotateAngleX -= parent.rotateAngleX;
            child.rotateAngleY -= parent.rotateAngleY;
            child.rotateAngleZ -= parent.rotateAngleZ;
            parent.addChild(child);
        }
    }
    
    public static class Biped extends ModelBiped
    {
        public float pi = (float) Math.PI;
        
        protected void addChildTo(ModelRenderer child, ModelRenderer parent)
        {
            child.rotationPointX -= parent.rotationPointX;
            child.rotationPointY -= parent.rotationPointY;
            child.rotationPointZ -= parent.rotationPointZ;
            
            child.rotateAngleX -= parent.rotateAngleX;
            child.rotateAngleY -= parent.rotateAngleY;
            child.rotateAngleZ -= parent.rotateAngleZ;
            
            parent.addChild(child);
        }
        
        protected void addChildToWithoutPoint(ModelRenderer child, ModelRenderer parent)
        {
            child.rotateAngleX -= parent.rotateAngleX;
            child.rotateAngleY -= parent.rotateAngleY;
            child.rotateAngleZ -= parent.rotateAngleZ;
            parent.addChild(child);
        }
        
        protected void setRotation(ModelRenderer model, float x, float y, float z)
        {
            model.rotateAngleX = x;
            model.rotateAngleY = y;
            model.rotateAngleZ = z;
        }
        
        protected void setPos(ModelRenderer model, float x, float y, float z)
        {
            model.rotationPointX = x;
            model.rotationPointY = y;
            model.rotationPointZ = z;
        }
        
        public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
        {
            super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
            
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                ItemStack itemstack = player.getHeldItem();
                
                if (TFDataManager.getTransformationTimer(player) == 20)
                {
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
                }
            }
        }
    }
}