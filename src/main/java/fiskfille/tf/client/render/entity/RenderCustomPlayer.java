package fiskfille.tf.client.render.entity;

import java.util.UUID;

import javax.vecmath.Vector3f;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.player.ModelPlayerTF;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.TFModelRegistry;
import fiskfille.tf.client.model.transformer.TransformerModel;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

@SideOnly(Side.CLIENT)
public class RenderCustomPlayer extends RenderPlayer
{
    public RenderCustomPlayer()
    {
        super();
        this.mainModel = new ModelPlayerTF();
        this.modelBipedMain = (ModelBiped) this.mainModel;
    }
    
    protected void renderEquippedItems(AbstractClientPlayer player, float partialTicks)
    {
        net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre event = new net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre(player, this, partialTicks);
        
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
            return;
        
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        
        super.renderArrowsStuckInEntity(player, partialTicks);
        
        ItemStack helmetStack = player.inventory.armorItemInSlot(3);
        
        if (helmetStack != null && event.renderHelmet)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);
            float scale;
            
            Item helmet = helmetStack.getItem();
            
            if (helmet instanceof ItemBlock)
            {
                net.minecraftforge.client.IItemRenderer customRenderer = net.minecraftforge.client.MinecraftForgeClient.getItemRenderer(helmetStack, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
                boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, helmetStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
                
                if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(helmet).getRenderType()))
                {
                    scale = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(scale, -scale, -scale);
                }
                
                this.renderManager.itemRenderer.renderItem(player, helmetStack, 0);
            }
            else if (helmet == Items.skull)
            {
                scale = 1.0625F;
                GL11.glScalef(scale, -scale, -scale);
                GameProfile gameprofile = null;
                
                if (helmetStack.hasTagCompound())
                {
                    NBTTagCompound itemTag = helmetStack.getTagCompound();
                    
                    if (itemTag.hasKey("SkullOwner", 10))
                    {
                        gameprofile = NBTUtil.func_152459_a(itemTag.getCompoundTag("SkullOwner"));
                    }
                    else if (itemTag.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(itemTag.getString("SkullOwner")))
                    {
                        gameprofile = new GameProfile((UUID) null, itemTag.getString("SkullOwner"));
                    }
                }
                
                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, helmetStack.getItemDamage(), gameprofile);
            }
            
            GL11.glPopMatrix();
        }
        
        float f2;
        
        boolean hasSkin = player.func_152123_o();
        
        if (player.getCommandSenderName().equals("deadmau5") && hasSkin)
        {
            this.bindTexture(player.getLocationSkin());
            
            for (int j = 0; j < 2; ++j)
            {
                float f9 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTicks - (player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks);
                float f10 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTicks;
                GL11.glPushMatrix();
                GL11.glRotatef(f9, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(f10, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float) (j * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-f10, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-f9, 0.0F, 1.0F, 0.0F);
                f2 = 1.3333334F;
                GL11.glScalef(f2, f2, f2);
                this.modelBipedMain.renderEars(0.0625F);
                GL11.glPopMatrix();
            }
        }
        
        boolean hasCape = player.func_152122_n();
        hasCape = event.renderCape && hasCape;
        float f4;
        
        Transformer transformer = TFHelper.getTransformerFromArmor(player, 2);
        
        if (hasCape && !player.isInvisible() && !player.getHideCape())
        {
            this.bindTexture(player.getLocationCape());
            GL11.glPushMatrix();
            
            TransformerModel model = TFModelRegistry.getModel(transformer);
            
            if(model != null && model.backside != null)
            {
                if(model.backside instanceof MowzieModelRenderer)
                {
                    MowzieModelRenderer backside = (MowzieModelRenderer) model.backside;
                    backside.postRenderParentChain(0.0625F);
                }
                else
                {
                    model.backside.postRender(0.0625F);
                }
                
                Vector3f capeOffset = model.capeOffset;
                GL11.glTranslatef(capeOffset.x, capeOffset.y, capeOffset.z);
            }
            else
            {
                modelBipedMain.bipedBody.postRender(0.0625F);
            }
            
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double d3 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * (double) partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double) partialTicks);
            double d4 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * (double) partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double) partialTicks);
            double d0 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * (double) partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double) partialTicks);
            f4 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
            double d1 = (double) MathHelper.sin(f4 * (float) Math.PI / 180.0F);
            double d2 = (double) (-MathHelper.cos(f4 * (float) Math.PI / 180.0F));
            float f5 = (float) d4 * 10.0F;
            
            if (f5 < -6.0F)
            {
                f5 = -6.0F;
            }
            
            if (f5 > 32.0F)
            {
                f5 = 32.0F;
            }
            
            float f6 = (float) (d3 * d1 + d0 * d2) * 100.0F;
            float f7 = (float) (d3 * d2 - d0 * d1) * 100.0F;
            
            if (f6 < 0.0F)
            {
                f6 = 0.0F;
            }
            
            float f8 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
            f5 += MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f8;
            
            if (player.isSneaking())
            {
                f5 += 25.0F;
            }
            
            GL11.glRotatef(6.0F + f6 / 2.0F + f5, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f7 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-f7 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            this.modelBipedMain.renderCloak(0.0625F);
            GL11.glPopMatrix();
        }
        
        ItemStack heldItemStack = player.inventory.getCurrentItem();
        
        if (heldItemStack != null && event.renderItem)
        {
            GL11.glPushMatrix();
            
            TransformerModel model = TFModelRegistry.getModel(transformer);
            
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
            else
            {
                modelBipedMain.bipedRightArm.postRender(0.0625F);
                GL11.glTranslatef(0F, 0.1F, -0.05F);
            }
            
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            
            if (player.fishEntity != null)
            {
                heldItemStack = new ItemStack(Items.stick);
            }
            
            EnumAction action = null;
            
            if (player.getItemInUseCount() > 0)
            {
                action = heldItemStack.getItemUseAction();
            }
            
            net.minecraftforge.client.IItemRenderer customRenderer = net.minecraftforge.client.MinecraftForgeClient.getItemRenderer(heldItemStack, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, heldItemStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
            
            Item heldItem = heldItemStack.getItem();
            
            if (is3D || heldItem instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(heldItem).getRenderType()))
            {
                f2 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f2 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f2, -f2, f2);
            }
            else if (heldItem == Items.bow)
            {
                f2 = 0.625F;
                GL11.glTranslatef(-0.01F, 0.05F, 0.4F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (heldItem.isFull3D())
            {
                f2 = 0.625F;
                
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
                
                if (player.getItemInUseCount() > 0 && action == EnumAction.block)
                {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }
                
                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f2 = 0.375F;
                GL11.glTranslatef(0.2F, 0.1F, -0.15F);
                GL11.glScalef(f2, f2, f2);
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
                    this.renderManager.itemRenderer.renderItem(player, heldItemStack, k);
                }
            }
            else
            {
                k = heldItem.getColorFromItemStack(heldItemStack, 0);
                float f11 = (float) (k >> 16 & 255) / 255.0F;
                f12 = (float) (k >> 8 & 255) / 255.0F;
                f3 = (float) (k & 255) / 255.0F;
                GL11.glColor4f(f11, f12, f3, 1.0F);
                this.renderManager.itemRenderer.renderItem(player, heldItemStack, 0);
            }
            
            GL11.glPopMatrix();
        }
        
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Specials.Post(player, this, partialTicks));
    }
    
    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        if (TFDataManager.getTransformationTimer(player) == 20)
        {
            ItemStack currentArmor = player.getCurrentArmor(2);
            ItemTransformerArmor transformerArmor = (ItemTransformerArmor) (currentArmor != null && currentArmor.getItem() instanceof ItemTransformerArmor ? currentArmor.getItem() : null);
            
            if (transformerArmor != null)
            {
                Transformer transformer = transformerArmor.getTransformer();
                
                TransformerModel model = TFModelRegistry.getModel(transformer);
                
                ModelRenderer upperArm = model.upperArm;
                
                ResourceLocation resourcelocation = new ResourceLocation(transformer.getChestplate().getArmorTexture(currentArmor, player, 3, ""));
                
                float f = 1.0F;
                GL11.glColor3f(f, f, f);
                Minecraft.getMinecraft().getTextureManager().bindTexture(resourcelocation);
                
                Biped mainModel = model.mainModel;
                
                if(upperArm == null)
                {
                    upperArm = mainModel.bipedRightArm;
                }
                
                mainModel.onGround = 0.0F;
                
                mainModel.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
                
                if(mainModel instanceof MowzieModelBase)
                {
                   ((MowzieModelBase) mainModel).setToInitPose();
                }
                else if(model.upperArm != null)
                {
                    keepPartAndChildrenStill(upperArm);
                }
                
                float scale = 1.2F;
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslatef(0.0f, 0.35f, 0.0f);
                
                if(upperArm instanceof MowzieModelRenderer)
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
                super.renderFirstPersonArm(player);
            }
        }
    }
    
    private void keepPartAndChildrenStill(ModelRenderer renderer)
    {
        if(renderer instanceof MowzieModelRenderer)
        {
            renderer.rotateAngleX = 0;
            renderer.rotateAngleY = 0;
            renderer.rotateAngleZ = 0;
            
            if(renderer.childModels != null)
            {
                for (Object child : renderer.childModels)
                {
                    keepPartAndChildrenStill((ModelRenderer) child);
                }
            }
        }
    }
}