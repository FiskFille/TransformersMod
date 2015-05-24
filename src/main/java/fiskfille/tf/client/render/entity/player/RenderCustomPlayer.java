//package fiskfille.tf.client.render.entity.player;
//
//import java.util.Random;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.model.ModelBox;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.client.renderer.RenderHelper;
//import net.minecraft.client.renderer.entity.RenderPlayer;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.projectile.EntityArrow;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.ResourceLocation;
//
//import org.lwjgl.opengl.GL11;
//
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//import fiskfille.tf.client.model.player.ModelBipedTF;
//import fiskfille.tf.client.model.tools.MowzieModelBase;
//import fiskfille.tf.client.model.tools.MowzieModelRenderer;
//import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
//import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
//import fiskfille.tf.client.model.transformer.definition.TransformerModel;
//import fiskfille.tf.common.playerdata.TFDataManager;
//import fiskfille.tf.common.transformer.base.Transformer;
//import fiskfille.tf.helper.TFHelper;
//import fiskfille.tf.helper.TFModelHelper;
//
//@SideOnly(Side.CLIENT)
//public class RenderCustomPlayer extends RenderPlayer
//{
//    public RenderCustomPlayer() TODO LLIB
//    {
//        super();
//        this.mainModel = new ModelBipedTF();
//        this.modelBipedMain = (ModelBiped) this.mainModel;
//        TFModelHelper.modelBipedMain = this.modelBipedMain;
//    }
//    /**
//     * renders arrows the Entity has been attacked with, attached to it
//     */
//    protected void renderArrowsStuckInEntity(EntityLivingBase entity, float partialTicks)
//    {
//        GL11.glPushMatrix();
//        
//        Transformer transformer = TFHelper.getTransformerFromArmor((EntityPlayer) entity, 2); //It's a player >:)
//        
//        TransformerModel model = TFModelRegistry.getModel(transformer);
//        
//        Biped tfModel = null;
//        
//        if (model != null)
//        {
//            tfModel = model.getMainModel();
//        }
//        
//        int arrowCount = entity.getArrowCountInEntity();
//        
//        if (arrowCount > 0)
//        {
//            EntityArrow arrow = new EntityArrow(entity.worldObj, entity.posX, entity.posY, entity.posZ);
//            Random random = new Random((long) entity.getEntityId());
//            RenderHelper.disableStandardItemLighting();
//            
//            for (int currentArrow = 0; currentArrow < arrowCount; ++currentArrow)
//            {
//                GL11.glPushMatrix();
//                
//                ModelRenderer embeddedIn = null;
//                
//                if (tfModel != null)
//                {
//                    embeddedIn = tfModel.getRandomModelBox(random);
//                    
//                    if (!(embeddedIn instanceof MowzieModelRenderer))
//                    {
//                        embeddedIn = model.getBody();
//                    }
//                }
//                else
//                {
//                    embeddedIn = this.mainModel.getRandomModelBox(random);
//                }
//                
//                ModelBox modelbox = (ModelBox) embeddedIn.cubeList.get(random.nextInt(embeddedIn.cubeList.size()));
//                
//                if (embeddedIn instanceof MowzieModelRenderer)
//                {
//                    MowzieModelRenderer mowzieModel = (MowzieModelRenderer) embeddedIn;
//                    
//                    mowzieModel.postRenderParentChain(0.0625F);
//                }
//                else
//                {
//                    embeddedIn.postRender(0.0625F);
//                }
//                
//                float f1 = random.nextFloat();
//                float f2 = random.nextFloat();
//                float f3 = random.nextFloat();
//                float posX = (modelbox.posX1 + (modelbox.posX2 - modelbox.posX1) * f1) / 16.0F;
//                float posY = (modelbox.posY1 + (modelbox.posY2 - modelbox.posY1) * f2) / 16.0F;
//                float posZ = (modelbox.posZ1 + (modelbox.posZ2 - modelbox.posZ1) * f3) / 16.0F;
//                GL11.glTranslatef(posX, posY, posZ);
//                f1 = f1 * 2.0F - 1.0F;
//                f2 = f2 * 2.0F - 1.0F;
//                f3 = f3 * 2.0F - 1.0F;
//                f1 *= -1.0F;
//                f2 *= -1.0F;
//                f3 *= -1.0F;
//                float f7 = MathHelper.sqrt_float(f1 * f1 + f3 * f3);
//                arrow.prevRotationYaw = arrow.rotationYaw = (float) (Math.atan2((double) f1, (double) f3) * 180.0D / Math.PI);
//                arrow.prevRotationPitch = arrow.rotationPitch = (float) (Math.atan2((double) f2, (double) f7) * 180.0D / Math.PI);
//                this.renderManager.renderEntityWithPosYaw(arrow, 0, 0, 0, 0, partialTicks);
//                GL11.glPopMatrix();
//            }
//            
//            RenderHelper.enableStandardItemLighting();
//        }
//        
//        GL11.glPopMatrix();
//    }
//}