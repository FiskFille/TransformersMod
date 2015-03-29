package fiskfille.tf.client.render.entity;

import javax.vecmath.Vector3f;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.client.model.transformer.ModelTransformer;
import fiskfille.tf.client.model.transformer.TFModelRegistry;
import fiskfille.tf.client.model.transformer.TransformerModel;
import fiskfille.tf.common.entity.EntityTransformer;

public class RenderTransformer extends RendererLivingEntity
{
    public RenderTransformer()
    {
        super(new ModelTransformer(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation(TransformersMod.modid + ":textures/models/purge/purge.png");
    }
    
    @Override
    public void doRender(EntityLivingBase entity, double x, double y, double z, float yaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, yaw, partialTicks);
    }
    
    protected void renderEquippedItems(EntityTransformer transformer, float partialTicks)
    {
        if(transformer.getTransformationTimer() > 10)
        {
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            
            ItemStack heldItemStack = transformer.getHeldItem();
            
            float f4;
            
            if (heldItemStack != null)
            {
                GL11.glPushMatrix();
                
                TransformerModel model = TFModelRegistry.getModel(TransformerManager.transformerPurge);
                
                if(model != null && model.lowerArm != null)
                {
                    if(model.lowerArm instanceof MowzieModelRenderer)
                    {
                        MowzieModelRenderer arm = (MowzieModelRenderer) model.lowerArm;
                        arm.postRenderParentChain(0.0625F);
                    }
                    else
                    {
                        model.lowerArm.postRender(0.0625F);
                    }
                    
                    Vector3f itemOffset = model.itemOffset;
                    GL11.glTranslatef(itemOffset.x, itemOffset.y, itemOffset.z);
                }
                
                GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
                
                net.minecraftforge.client.IItemRenderer customRenderer = net.minecraftforge.client.MinecraftForgeClient.getItemRenderer(heldItemStack, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
                boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, heldItemStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
                
                Item heldItem = heldItemStack.getItem();
                
                float scale;
                
                if (is3D || heldItem instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(heldItem).getRenderType()))
                {
                    scale = 0.5F;
                    GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                    scale *= 0.75F;
                    GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(-scale, -scale, scale);
                }
                else if (heldItem == Items.bow)
                {
                    scale = 0.625F;
                    GL11.glTranslatef(-0.01F, 0.05F, 0.4F);
                    GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(scale, -scale, scale);
                    GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                }
                else if (heldItem.isFull3D())
                {
                    scale = 0.625F;
                    
                    if (customRenderer != null)
                    {
                        GL11.glTranslatef(0, -0.1F, 0);
                    }
                    
                    GL11.glTranslatef(0F, -0.1F, -0.05F);
                    
                    if (heldItem.shouldRotateAroundWhenRendering())
                    {
                        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                    }
                    
                    GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                    GL11.glScalef(scale, -scale, scale);
                    GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                }
                else
                {
                    scale = 0.375F;
                    GL11.glTranslatef(0.2F, 0.1F, -0.15F);
                    GL11.glScalef(scale, scale, scale);
                    GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                }
                
                float f3;
                int k;
                float f12;
                
                if (heldItem.requiresMultipleRenderPasses())
                {
                    for (k = 0; k < heldItem.getRenderPasses(heldItemStack.getItemDamage()); ++k)
                    {
                        int i = heldItem.getColorFromItemStack(heldItemStack, k);
                        f12 = (float) (i >> 16 & 255) / 255.0F;
                        f3 = (float) (i >> 8 & 255) / 255.0F;
                        f4 = (float) (i & 255) / 255.0F;
                        GL11.glColor4f(f12, f3, f4, 1.0F);
                        this.renderManager.itemRenderer.renderItem(transformer, heldItemStack, k);
                    }
                }
                else
                {
                    k = heldItem.getColorFromItemStack(heldItemStack, 0);
                    float f11 = (float) (k >> 16 & 255) / 255.0F;
                    f12 = (float) (k >> 8 & 255) / 255.0F;
                    f3 = (float) (k & 255) / 255.0F;
                    GL11.glColor4f(f11, f12, f3, 1.0F);
                    this.renderManager.itemRenderer.renderItem(transformer, heldItemStack, 0);
                }
                
                GL11.glPopMatrix();
            }
        }
    }
}
