package fiskfille.tf.client.model.player;

import java.util.Random;

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
import net.ilexiconn.llibrary.client.render.IArrowStuckExtension;
import net.ilexiconn.llibrary.client.render.IFirstPersonExtension;
import net.ilexiconn.llibrary.client.render.IModelExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class PlayerModelOverride implements IModelExtension, IFirstPersonExtension, IArrowStuckExtension
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
    public boolean preRenderFirstPerson(EntityPlayer player, ModelBiped modelBiped)
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
                
                return true;
            }
        }
        
        return false;
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

    @Override
    public void postRenderArrowsStuckInEntity(EntityLivingBase entity, float arg1)
    {
    }

    @Override
    public boolean preRenderArrowsStuckInEntity(EntityLivingBase entity, float partialTicks)
    {
        GL11.glPushMatrix();
        
        Transformer transformer = TFHelper.getTransformerFromArmor((EntityPlayer) entity, 2); //It's a player >:)
        
        TransformerModel model = TFModelRegistry.getModel(transformer);
        
        Biped tfModel = null;
        
        if (model != null)
        {
            tfModel = model.getMainModel();
        }
        
        int arrowCount = entity.getArrowCountInEntity();
        
        if (arrowCount > 0)
        {
            EntityArrow arrow = new EntityArrow(entity.worldObj, entity.posX, entity.posY, entity.posZ);
            Random random = new Random((long) entity.getEntityId());
            RenderHelper.disableStandardItemLighting();
            
            for (int currentArrow = 0; currentArrow < arrowCount; ++currentArrow)
            {
                GL11.glPushMatrix();
                
                ModelRenderer embeddedIn = null;
                
                if (tfModel != null)
                {
                    embeddedIn = tfModel.getRandomModelBox(random);
                    
                    if (!(embeddedIn instanceof MowzieModelRenderer))
                    {
                        embeddedIn = model.getBody();
                    }
                }
                else
                {
                    embeddedIn = TFModelHelper.modelBipedMain.getRandomModelBox(random);
                }
                
                ModelBox modelbox = (ModelBox) embeddedIn.cubeList.get(random.nextInt(embeddedIn.cubeList.size()));
                
                if (embeddedIn instanceof MowzieModelRenderer)
                {
                    MowzieModelRenderer mowzieModel = (MowzieModelRenderer) embeddedIn;
                    
                    mowzieModel.postRenderParentChain(0.0625F);
                }
                else
                {
                    embeddedIn.postRender(0.0625F);
                }
                
                float f1 = random.nextFloat();
                float f2 = random.nextFloat();
                float f3 = random.nextFloat();
                float posX = (modelbox.posX1 + (modelbox.posX2 - modelbox.posX1) * f1) / 16.0F;
                float posY = (modelbox.posY1 + (modelbox.posY2 - modelbox.posY1) * f2) / 16.0F;
                float posZ = (modelbox.posZ1 + (modelbox.posZ2 - modelbox.posZ1) * f3) / 16.0F;
                GL11.glTranslatef(posX, posY, posZ);
                f1 = f1 * 2.0F - 1.0F;
                f2 = f2 * 2.0F - 1.0F;
                f3 = f3 * 2.0F - 1.0F;
                f1 *= -1.0F;
                f2 *= -1.0F;
                f3 *= -1.0F;
                float f7 = MathHelper.sqrt_float(f1 * f1 + f3 * f3);
                arrow.prevRotationYaw = arrow.rotationYaw = (float) (Math.atan2((double) f1, (double) f3) * 180.0D / Math.PI);
                arrow.prevRotationPitch = arrow.rotationPitch = (float) (Math.atan2((double) f2, (double) f7) * 180.0D / Math.PI);
                RenderManager.instance.renderEntityWithPosYaw(arrow, 0, 0, 0, 0, partialTicks);
                GL11.glPopMatrix();
            }
            
            RenderHelper.enableStandardItemLighting();
        }
        
        GL11.glPopMatrix();
        
        return true;
    }
}
