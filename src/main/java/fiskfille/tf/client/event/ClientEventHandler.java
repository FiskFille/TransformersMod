package fiskfille.tf.client.event;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.client.render.entity.CustomEntityRenderer;
import fiskfille.tf.common.event.PlayerTransformEvent;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public class ClientEventHandler
{
    private final Minecraft mc = Minecraft.getMinecraft();
    private EntityRenderer renderer, prevRenderer;
    
    public static boolean prevViewBobbing;
    
    private Item prevHelm, prevChest, prevLegs, prevBoots;
    
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equals(TransformersMod.modid))
        {
            TransformersMod.config.load(TransformersMod.configFile);
            
            TransformersMod.configFile.save();
        }
    }
    
    @SubscribeEvent
    public void onTransform(PlayerTransformEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        
        if (player == mc.thePlayer)
        {
            if (event.transformed)
            {
                GameSettings gameSettings = mc.gameSettings;
                prevViewBobbing = gameSettings.viewBobbing;
                gameSettings.viewBobbing = false;
            }
            else
            {
                mc.gameSettings.viewBobbing = prevViewBobbing;
            }
        }
    }
    
    @SubscribeEvent
    public void onPlaySound(PlaySoundAtEntityEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            if (event.name.startsWith("step.") && TFDataManager.isInVehicleMode((EntityPlayer) event.entity))
            {
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderPlayerSpecialsPre(RenderPlayerEvent.Specials.Pre event)
    {
        AbstractClientPlayer player = (AbstractClientPlayer) event.entityPlayer;
        
        if (TFDataManager.getTransformationTimer(player) < 10)
        {
            event.setCanceled(true);
        }
        
        if(!event.isCanceled())
        {
            if (TFHelper.getTransformerFromArmor(player, 2) != null)
            {
                event.setCanceled(true);
                
                ModelBiped modelBipedMain = TFModelHelper.modelBipedMain;
                
                if(modelBipedMain != null)
                {
                    float partialTicks = event.partialRenderTick;
                    
                    Transformer transformer = TFHelper.getTransformerFromArmor(player, 2);
                    RenderManager renderManager = RenderManager.instance;
                    
                    GL11.glColor3f(1.0F, 1.0F, 1.0F);
                    
                    //                    renderArrowsStuckInEntity(player, partialTicks); TODO
                    
                    ItemStack helmetStack = player.inventory.armorItemInSlot(3);
                    
                    if (helmetStack != null && event.renderHelmet)
                    {
                        GL11.glPushMatrix();
                        
                        modelBipedMain.bipedHead.postRender(0.0625F);
                        float scale;
                        
                        Item helmet = helmetStack.getItem();
                        
                        if (helmet instanceof ItemBlock)
                        {
                            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(helmetStack, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
                            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, helmetStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
                            
                            if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(helmet).getRenderType()))
                            {
                                scale = 0.625F;
                                GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                                GL11.glScalef(scale, -scale, -scale);
                            }
                            
                            renderManager.itemRenderer.renderItem(player, helmetStack, 0);
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
                        renderManager.renderEngine.bindTexture(player.getLocationSkin());
                        
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
                            modelBipedMain.renderEars(0.0625F);
                            GL11.glPopMatrix();
                        }
                    }
                    
                    boolean hasCape = player.func_152122_n();
                    hasCape = event.renderCape && hasCape;
                    float f4;
                    
                    if (hasCape && !player.isInvisible() && !player.getHideCape())
                    {
                        renderManager.renderEngine.bindTexture(player.getLocationSkin());
                        GL11.glPushMatrix();
                        
                        TransformerModel model = TFModelRegistry.getModel(transformer);
                        
                        if (model != null)
                        {
                            model.renderCape(player);
                            
                            ModelRenderer backside = model.getBody();
                            
                            if (backside != null)
                            {
                                if (backside instanceof MowzieModelRenderer)
                                {
                                    MowzieModelRenderer backsideMowzie = (MowzieModelRenderer) backside;
                                    backsideMowzie.postRenderParentChain(0.0625F);
                                }
                                else
                                {
                                    backside.postRender(0.0625F);
                                }
                            }
                            else
                            {
                                modelBipedMain.bipedBody.postRender(0.0625F);
                            }
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
                        
                        GL11.glRotatef(6.0F + f6 / 2.0F + f5, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(f7 / 2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-f7 / 2.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                        modelBipedMain.renderCloak(0.0625F);
                        GL11.glPopMatrix();
                    }
                    
                    ItemStack heldItemStack = player.inventory.getCurrentItem();
                    
                    if (heldItemStack != null && event.renderItem)
                    {
                        GL11.glPushMatrix();
                        
                        TransformerModel model = TFModelRegistry.getModel(transformer);
                        
                        if (model != null)
                        {
                            model.renderItem(player, heldItemStack);
                            ModelRenderer lowerArm = model.getLowerArm();
                            
                            if (lowerArm != null)
                            {
                                if (lowerArm instanceof MowzieModelRenderer)
                                {
                                    MowzieModelRenderer arm = (MowzieModelRenderer) lowerArm;
                                    arm.postRenderParentChain(0.0625F);
                                }
                                else
                                {
                                    lowerArm.postRender(0.0625F);
                                }
                            }
                            else
                            {
                                modelBipedMain.bipedRightArm.postRender(0.0625F);
                                GL11.glTranslatef(0F, 0.1F, -0.1F);
                            }
                        }
                        else
                        {
                            modelBipedMain.bipedRightArm.postRender(0.0625F);
                            GL11.glTranslatef(0F, 0.1F, -0.1F);
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
                        
                        IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(heldItemStack, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
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
                            
                            if (model != null)
                            {
                                GL11.glTranslatef(0.0F, -0.15F, -0.1F);
                            }
                            
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
                                renderManager.itemRenderer.renderItem(player, heldItemStack, k);
                            }
                        }
                        else
                        {
                            k = heldItem.getColorFromItemStack(heldItemStack, 0);
                            float f11 = (float) (k >> 16 & 255) / 255.0F;
                            f12 = (float) (k >> 8 & 255) / 255.0F;
                            f3 = (float) (k & 255) / 255.0F;
                            GL11.glColor4f(f11, f12, f3, 1.0F);
                            renderManager.itemRenderer.renderItem(player, heldItemStack, 0);
                        }
                        
                        GL11.glPopMatrix();
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderPlayerPost(RenderPlayerEvent.Specials.Post event)
    {
        //After rendered everything
        
        EntityPlayer player = mc.thePlayer;
        
        ModelOffset offsets = TFModelHelper.getOffsets(player);
        
        ItemStack bootsStack = player.getCurrentArmor(0);
        ItemStack legsStack = player.getCurrentArmor(1);
        ItemStack chestStack = player.getCurrentArmor(2);
        ItemStack helmStack = player.getCurrentArmor(3);
        
        Item boots = bootsStack != null ? bootsStack.getItem() : null; 
        Item legs = legsStack != null ? legsStack.getItem() : null; 
        Item chest = chestStack != null ? chestStack.getItem() : null; 
        Item helm = helmStack != null ? helmStack.getItem() : null; 
        
        boolean armorChanged = false;
        
        if(boots != prevBoots)
        {
            prevBoots = boots;
            armorChanged = true;
        }
        if(chest != prevChest)
        {
            prevChest = chest;
            armorChanged = true;
        }
        if(legs != prevLegs)
        {
            prevLegs = legs;
            armorChanged = true;
        }
        if(helm != prevHelm)
        {
            prevHelm = helm;
            armorChanged = true;
        }
        
        if(armorChanged)
        {
            offsets.headOffsetX = 0;
            offsets.headOffsetY = 0;
            offsets.headOffsetZ = 0;
        }
    }
    
    @SubscribeEvent
    public void onRenderPlayerPre(RenderPlayerEvent.Pre event)
    {      
        ModelBiped modelBipedMain = event.renderer.modelBipedMain;
        TFModelHelper.modelBipedMain = modelBipedMain;
        
        EntityPlayer player = event.entityPlayer;
        Transformer transformer = TFHelper.getTransformer(player);
        boolean isClientPlayer = mc.thePlayer == player;
        float cameraYOffset = 0;
        
        if(modelBipedMain != null)
        {
            ItemStack helm = player.getCurrentArmor(3);
            boolean wearingTransformerHelm = helm != null && helm.getItem() instanceof ItemTransformerArmor;
            ItemStack chest = player.getCurrentArmor(2);
            boolean wearingTransformerChest = chest != null && chest.getItem() instanceof ItemTransformerArmor;
            ItemStack pants = player.getCurrentArmor(1);
            boolean wearingTransformerPants = pants != null && pants.getItem() instanceof ItemTransformerArmor;
            
            modelBipedMain.bipedHead.showModel = !wearingTransformerHelm;
            modelBipedMain.bipedHeadwear.showModel = !wearingTransformerHelm;
            modelBipedMain.bipedEars.showModel = !wearingTransformerHelm;
            
            modelBipedMain.bipedBody.showModel = !wearingTransformerChest;
            modelBipedMain.bipedRightArm.showModel = !wearingTransformerChest;
            modelBipedMain.bipedLeftArm.showModel = !wearingTransformerChest;
            
            modelBipedMain.bipedLeftLeg.showModel = !wearingTransformerPants;
            modelBipedMain.bipedRightLeg.showModel = !wearingTransformerPants;
        }
        
        if (transformer != null)
        {
            cameraYOffset = transformer.getCameraYOffset(player);
        }
        
        if (isClientPlayer && cameraYOffset != 0)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0, -CustomEntityRenderer.getOffsetY(player), 0);
        }
        
        // This prevents the player from sinking into the ground when sneaking in vehicle mode
        if (player.isSneaking() && TFDataManager.getTransformationTimer(player) < 20)
        {
            if (isClientPlayer)
            {
                GL11.glTranslatef(0, 0.08F, 0);
            }
            else
            {
                GL11.glTranslatef(0, 0.125F, 0);
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Post event)
    {
        EntityPlayer player = event.entityPlayer;
        Transformer transformer = TFHelper.getTransformer(player);
        boolean isClientPlayer = mc.thePlayer == player;
        
        ModelBiped modelBipedMain = event.renderer.modelBipedMain;
        
        if(modelBipedMain != null)
        {
            modelBipedMain.bipedHead.showModel = true;
            modelBipedMain.bipedHeadwear.showModel = true;
            modelBipedMain.bipedEars.showModel = true;
            
            modelBipedMain.bipedBody.showModel = true;
            modelBipedMain.bipedRightArm.showModel = true;
            modelBipedMain.bipedLeftArm.showModel = true;
            
            modelBipedMain.bipedLeftLeg.showModel = true;
            modelBipedMain.bipedRightLeg.showModel = true;
        }
        
        if (transformer != null)
        {
            if (isClientPlayer && transformer.getCameraYOffset(player) != 0.0F)
            {
                GL11.glPopMatrix();
            }
        }
    }
    
    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        
        if (mc.theWorld != null)
        {
            if (event.phase == TickEvent.Phase.START)
            {
                EntityClientPlayerMP player = mc.thePlayer;
                
                Transformer transformer = TFHelper.getTransformer(player);
                
                if (transformer != null)
                {
                    if (transformer.getCameraYOffset(player) != 0.0F)
                    {
                        if (renderer == null)
                        {
                            renderer = new CustomEntityRenderer(mc);
                        }
                        
                        if (mc.entityRenderer != renderer)
                        {
                            prevRenderer = mc.entityRenderer;
                            mc.entityRenderer = renderer;
                        }
                    }
                    else if (prevRenderer != null && mc.entityRenderer != prevRenderer)
                    {
                        mc.entityRenderer = prevRenderer;
                    }
                }
                else if (prevRenderer != null && mc.entityRenderer != prevRenderer)
                {
                    mc.entityRenderer = prevRenderer;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event)
    {
        EntityPlayerSP player = event.entity;
        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
        
        int nitro = transformedPlayer == null ? 0 : transformedPlayer.getNitro();
        boolean moveForward = Minecraft.getMinecraft().gameSettings.keyBindForward.getIsKeyPressed();
        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed();
        
        if (TFDataManager.isInVehicleMode(player))
        {
            if (nitro > 0 && moveForward && nitroPressed && !TFDataManager.isInStealthMode(player))
            {
                event.newfov = 1.3F;
            }
        }
        else
        {
            ItemStack itemstack = player.getHeldItem();
            
            if (TFDataManager.getZoomTimer(player) > 0 && TFHelper.isPlayerVurp(player) && itemstack != null && itemstack.getItem() == TFItems.vurpsSniper && this.mc.gameSettings.thirdPersonView == 0)
            {
                event.newfov = 1.0F - (float) TFDataManager.getZoomTimer(player) / 10;
            }
        }
        
        if (TFDataManager.getTransformationTimer(player) < 20 && !(nitro > 0 && moveForward && nitroPressed && !TFDataManager.isInStealthMode(player)))
        {
            event.newfov = 1.0F;
        }
    }
    
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event)
    {
        
    }
    //    TODO: Expand upon and re-implement this for 0.6.0 
    //    @SubscribeEvent
    //    public void onItemToolTip(ItemTooltipEvent event)
    //    {
    //        String s = "tooltip." + event.itemStack.getUnlocalizedName();
    //        
    //        if (!s.equals(StatCollector.translateToLocal(s)))
    //        {
    //            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
    //            {
    //                event.toolTip.add(StatCollector.translateToLocal(s));
    //            }
    //            else
    //            {
    //                event.toolTip.add(EnumChatFormatting.BLUE + "Hold SHIFT for info.");
    //            }
    //        }
    //    }
}