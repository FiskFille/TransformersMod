package fiskfille.tf.client.model.player;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;
import net.ilexiconn.llibrary.client.render.IModelExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PlayerModelOverride implements IModelExtension
{
    @Override
    public void init(ModelBase model)
    {
    }
    
    @Override
    public void postRender(EntityPlayer player, ModelBase model, float partialTicks)
    {
    }
    
    @Override
    public void postRenderFirstPerson(EntityPlayer player, ModelBiped model)
    {
    }
    
    @Override
    public void preRender(EntityPlayer player, ModelBase model, float partialTicks)
    {
        ModelOffset offsets = TFModelHelper.getOffsets(player);
        
        ModelBiped modelBiped = (ModelBiped) model;
        
        modelBiped.bipedHead.rotationPointY = offsets.headOffsetY;
        modelBiped.bipedHeadwear.rotationPointY = offsets.headOffsetY;
        modelBiped.bipedHead.rotationPointX = offsets.headOffsetX;
        modelBiped.bipedHeadwear.rotationPointX = offsets.headOffsetX;
        modelBiped.bipedHead.rotationPointZ = offsets.headOffsetZ;
        modelBiped.bipedHeadwear.rotationPointZ = offsets.headOffsetZ;
    }
    
    @Override
    public void preRenderFirstPerson(EntityPlayer player, ModelBiped modelBiped)
    {
        if (TFDataManager.getTransformationTimer(player) == 20)
        {
            Transformer transformer = TFHelper.getTransformerFromArmor(player, 2);
            
            if (transformer != null)
            {
                TransformerModel model = TFModelRegistry.getModel(transformer);
                ModelRenderer upperArm = model.getUpperArm();
                ResourceLocation resourcelocation = new ResourceLocation(transformer.getChestplate().getArmorTexture(player.getCurrentArmor(2), player, 3, ""));
                
                float f = 1.0F;
                GL11.glColor3f(f, f, f);
                Minecraft.getMinecraft().getTextureManager().bindTexture(resourcelocation);
                
                Biped mainModel = model.getMainModel();
                
                if (upperArm == null)
                {
                    upperArm = mainModel.bipedRightArm;
                }
                
                mainModel.onGround = 0.0F;
                mainModel.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
                
                if (mainModel instanceof MowzieModelBase)
                {
                    ((MowzieModelBase) mainModel).setToInitPose();
                }
                else if (model.getUpperArm() != null)
                {
                    keepPartAndChildrenStill(upperArm);
                }
                
                float scale = 1.2F;
                GL11.glScalef(scale, scale, scale);
                
                model.renderFirstPersonArm(player);
                GL11.glTranslatef(0.0f, 0.35f, 0.0f);
                
                modelBiped.bipedRightArm.showModel = false;
                
                if (upperArm instanceof MowzieModelRenderer)
                {
                    ((MowzieModelRenderer) upperArm).render(0.0625F);
                }
                else
                {
                    upperArm.render(0.0625F);
                }
            }
            else
            {
                modelBiped.bipedRightArm.showModel = true;
            }
        }
    }
    
    private void keepPartAndChildrenStill(ModelRenderer renderer)
    {
        if (renderer instanceof MowzieModelRenderer)
        {
            renderer.rotateAngleX = 0;
            renderer.rotateAngleY = 0;
            renderer.rotateAngleZ = 0;
            
            if (renderer.childModels != null)
            {
                for (Object child : renderer.childModels)
                {
                    keepPartAndChildrenStill((ModelRenderer) child);
                }
            }
        }
    }
    
    @Override
    public void setRotationAngles(ModelBiped model, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, Entity entity)
    {
        EntityPlayer player = (EntityPlayer) entity;
        
        ItemStack itemstack = player.getHeldItem();
        
        if (TFDataManager.getTransformationTimer(player) == 20)
        {
            if (itemstack != null && itemstack.getItem() == TFItems.vurpsSniper)
            {
                this.setRotation(model.bipedRightArm, -1.3F, model.bipedHead.rotateAngleY - 0.45F, 0.0F);
                this.setRotation(model.bipedLeftArm, -1.2F, model.bipedHead.rotateAngleY + 0.4F, 0.0F);
                model.bipedLeftArm.setRotationPoint(3.0F, 3.0F, -2.5F);
            }
            else
            {
                model.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
                model.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
            }
        }
    }
    
    protected void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
