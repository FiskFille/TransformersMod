package fiskfille.tf.common.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.tf.TFReflection;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiOverlay;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.player.ModelBipedPartial;
import fiskfille.tf.client.model.tools.ModelBoxPartial;
import fiskfille.tf.client.model.tools.ModelRendererPartial;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.client.render.entity.player.RenderPlayerHand;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.item.ItemHandler;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFFluidRenderHelper;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;
import fiskfille.tf.helper.TFTextureHelper;

public class ClientEventHandler
{
    private final Minecraft mc = Minecraft.getMinecraft();

    public RenderPlayerHand renderHandInstance;
    public boolean prevViewBobbing = mc.gameSettings.viewBobbing;

    private Map<EntityPlayer, Item> prevHelm = new HashMap<EntityPlayer, Item>();
    private Map<EntityPlayer, Item> prevChest = new HashMap<EntityPlayer, Item>();
    private Map<EntityPlayer, Item> prevLegs = new HashMap<EntityPlayer, Item>();
    private Map<EntityPlayer, Item> prevBoots = new HashMap<EntityPlayer, Item>();

    private double lastX;
    private double lastY;
    private double lastZ;

    public ClientEventHandler()
    {
        renderHandInstance = new RenderPlayerHand();
        renderHandInstance.setRenderManager(RenderManager.instance);
    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event)
    {
        EntityPlayer player = mc.thePlayer;
        Transformer transformer = TFHelper.getTransformer(player);

        int altMode = TFData.ALT_MODE.get(player);
        float transformationTimer = TFHelper.getTransformationTimer(player);

        if (mc.currentScreen == null && player.ridingEntity == null)
        {
            if (TFHelper.isPlayerTransformer(player))
            {
                KeyBinding[] keys = new KeyBinding[] {TFKeyBinds.keyBindingTransform1};

                for (int keyAlt = 0; keyAlt < keys.length; ++keyAlt)
                {
                    if (keyAlt < transformer.getAltModeCount())
                    {
                        KeyBinding key = keys[keyAlt];

                        if (key.getIsKeyPressed())
                        {
                            if (keyAlt == altMode && transformationTimer == 1)
                            {
                                TFData.ALT_MODE.set(player, -1);
                            }
                            else if (altMode == -1 && transformationTimer == 0)
                            {
                                TFData.ALT_MODE.set(player, keyAlt);
                            }
                        }
                    }
                }

                if (TFKeyBinds.keyBindingStealthMode.getIsKeyPressed())
                {
                    if (transformationTimer == 1 && transformer.hasStealthForce(player, altMode))
                    {
                        float stealthModeTimer = TFHelper.getStealthModeTimer(player);

                        if (TFData.STEALTH_FORCE.get(player) && stealthModeTimer == 1)
                        {
                            TFData.STEALTH_FORCE.set(player, false);
                        }
                        else if (!TFData.STEALTH_FORCE.get(player) && stealthModeTimer == 0)
                        {
                            TFData.STEALTH_FORCE.set(player, true);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equals(TransformersMod.modid))
        {
            TFConfig.load(TFConfig.configFile);
            TFConfig.configFile.save();
        }
    }

    @SubscribeEvent
    public void onTransform(PlayerTransformEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        Transformer transformer = event.transformer;

        if (player == mc.thePlayer)
        {
            boolean isTransformed = event.altMode != -1;

            if (transformer == null || transformer.disableViewBobbing(player, event.altMode))
            {
                if (isTransformed)
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

//            if (isTransformed)
//            {
//                TutorialHandler.openTutorial(player, transformer);
//            }
//            else
//            {
//                TutorialHandler.currentTutorial = null;
//            }
        }
    }

    @SubscribeEvent
    public void onPlaySound(PlaySoundAtEntityEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;

            if (event.name.startsWith("step.") && TFHelper.isFullyTransformed(player))
            {
                Transformer transformer = TFHelper.getTransformer(player);

                int altMode = TFData.ALT_MODE.get(player);

                if (transformer != null && transformer.disableStepSounds(player, altMode))
                {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event)
    {
        if (!ItemHandler.hasInit)
        {
            ItemHandler.init();
        }
        
        if (event.map.getTextureType() == 0)
        {
            TFFluidRenderHelper.onTextureStitch(event.map);
            TFTextureHelper.onTextureStitch(event.map);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderHand(RenderHandEvent event)
    {
        EntityClientPlayerMP player = mc.thePlayer;
        Object renderObj = RenderManager.instance.entityRenderMap.get(player.getClass());

        if (renderObj != null && TFHelper.getTransformerFromArmor(player, 2) != null)
        {
            event.setCanceled(true);
            Render render = RenderManager.instance.getEntityRenderObject(player);

            GL11.glPushMatrix();
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
            renderHandInstance.setParent(render);

            RenderManager.instance.entityRenderMap.put(player.getClass(), renderHandInstance);
            TFReflection.renderHand(mc.entityRenderer, event.partialTicks, 0);
            RenderManager.instance.entityRenderMap.put(player.getClass(), render);
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public void onRenderPlayerSpecialsPre(RenderPlayerEvent.Specials.Pre event)
    {
        AbstractClientPlayer player = (AbstractClientPlayer) event.entityPlayer;

        if (TFHelper.getTransformationTimer(player) >= 0.5F)
        {
            event.setCanceled(true);
            return;
        }

        if (!event.isCanceled())
        {
            if (TFHelper.getTransformerFromArmor(player, 2) != null)
            {
                event.setCanceled(true);
                ModelBiped modelBipedMain = event.renderer.modelBipedMain;

                if (modelBipedMain != null)
                {
                    float partialTicks = event.partialRenderTick;

                    Transformer transformer = TFHelper.getTransformerFromArmor(player, 2);
                    RenderManager renderManager = RenderManager.instance;

                    GL11.glColor3f(1.0F, 1.0F, 1.0F);

                    // renderArrowsStuckInEntity(player, partialTicks); TODO

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
                            boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, helmetStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D);

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
                            GL11.glTranslatef(0.375F * (j * 2 - 1), 0.0F, 0.0F);
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
                        renderManager.renderEngine.bindTexture(player.getLocationCape());
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
                        double d3 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * partialTicks);
                        double d4 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * partialTicks);
                        double d0 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * partialTicks);
                        f4 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
                        double d1 = MathHelper.sin(f4 * (float) Math.PI / 180.0F);
                        double d2 = -MathHelper.cos(f4 * (float) Math.PI / 180.0F);
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
                        boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, heldItemStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D);

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
                                f12 = (i >> 16 & 255) / 255.0F;
                                f3 = (i >> 8 & 255) / 255.0F;
                                f4 = (i & 255) / 255.0F;
                                GL11.glColor4f(f12, f3, f4, 1.0F);
                                renderManager.itemRenderer.renderItem(player, heldItemStack, k);
                            }
                        }
                        else
                        {
                            k = heldItem.getColorFromItemStack(heldItemStack, 0);
                            float f11 = (k >> 16 & 255) / 255.0F;
                            f12 = (k >> 8 & 255) / 255.0F;
                            f3 = (k & 255) / 255.0F;
                            GL11.glColor4f(f11, f12, f3, 1.0F);
                            renderManager.itemRenderer.renderItem(player, heldItemStack, 0);
                        }

                        GL11.glPopMatrix();
                    }

                    MinecraftForge.EVENT_BUS.post(new RenderPlayerEvent.Specials.Post(player, event.renderer, event.partialRenderTick));
                }
            }

            if (!player.isInvisibleToPlayer(mc.thePlayer) && (TFHelper.getTransformerFromArmor(player, 0) == null && TFHelper.getTransformerFromArmor(player, 1) != null || TFHelper.getTransformerFromArmor(player, 1) == null && TFHelper.getTransformerFromArmor(player, 0) != null))
            {
                ModelBipedPartial model = TFModelHelper.modelBipedPartial;
                ModelRenderer[] bipedLegs = {model.bipedLeftLeg, model.bipedRightLeg};
                ModelRenderer[] bipedLegs2 = {event.renderer.modelBipedMain.bipedLeftLeg, event.renderer.modelBipedMain.bipedRightLeg};

                if (TFHelper.getTransformerFromArmor(player, 1) != null)
                {
                    TransformerModel tfModel = TFModelRegistry.getModel(TFHelper.getTransformerFromArmor(player, 1));

                    if (tfModel != null)
                    {
                        mc.getTextureManager().bindTexture(TFTextureHelper.getSkin(player.getCommandSenderName()));

                        for (int i = 0; i < bipedLegs.length; ++i)
                        {
                            ModelRendererPartial modelRenderer = (ModelRendererPartial) bipedLegs[i];
                            ModelBoxPartial modelBox = modelRenderer.getBox();
                            AxisAlignedBB aabb = modelBox.getBounds();

                            aabb.maxY = 12;
                            aabb.minY = aabb.maxY - tfModel.getFootHeight();
                            modelBox.setBounds(aabb);

                            GL11.glPushMatrix();
                            modelRenderer.setRotationPoint(bipedLegs2[i].rotationPointX, bipedLegs2[i].rotationPointY, bipedLegs2[i].rotationPointZ);
                            modelRenderer.setRotationAngles(bipedLegs2[i].rotateAngleX, bipedLegs2[i].rotateAngleY, bipedLegs2[i].rotateAngleZ);
                            modelRenderer.render(0.0625F);
                            GL11.glPopMatrix();
                        }
                    }
                }
                else if (TFHelper.getTransformerFromArmor(player, 0) != null)
                {
                    TransformerModel tfModel = TFModelRegistry.getModel(TFHelper.getTransformerFromArmor(player, 0));

                    if (tfModel != null)
                    {
                        mc.getTextureManager().bindTexture(TFTextureHelper.getSkin(player.getCommandSenderName()));

                        for (int i = 0; i < bipedLegs.length; ++i)
                        {
                            ModelRendererPartial modelRenderer = (ModelRendererPartial) bipedLegs[i];
                            ModelBoxPartial modelBox = modelRenderer.getBox();
                            AxisAlignedBB aabb = modelBox.getBounds();

                            aabb.minY = 0;
                            aabb.maxY = 12 - tfModel.getFootHeight();
                            modelBox.setBounds(aabb);

                            GL11.glPushMatrix();
                            modelRenderer.setRotationPoint(bipedLegs2[i].rotationPointX, bipedLegs2[i].rotationPointY, bipedLegs2[i].rotationPointZ);
                            modelRenderer.setRotationAngles(bipedLegs2[i].rotateAngleX, bipedLegs2[i].rotateAngleY, bipedLegs2[i].rotateAngleZ);
                            modelRenderer.render(0.0625F);
                            GL11.glPopMatrix();
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderPlayerSpecialsPost(RenderPlayerEvent.Specials.Post event)
    {
        EntityPlayer player = event.entityPlayer;
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

        if (boots != prevBoots.get(player))
        {
            prevBoots.put(player, boots);
            armorChanged = true;
        }

        if (chest != prevChest.get(player))
        {
            prevChest.put(player, chest);
            armorChanged = true;
        }

        if (legs != prevLegs.get(player))
        {
            prevLegs.put(player, legs);
            armorChanged = true;
        }

        if (helm != prevHelm.get(player))
        {
            prevHelm.put(player, helm);
            armorChanged = true;
        }

        if (armorChanged)
        {
            offsets.headOffsetX = 0;
            offsets.headOffsetY = 0;
            offsets.headOffsetZ = 0;
        }
    }

    @SubscribeEvent
    public void onRenderPlayerPre(RenderPlayerEvent.Pre event)
    {
        EntityPlayer player = event.entityPlayer;
        ModelBiped biped = event.renderer.modelBipedMain;

        if (biped != null)
        {
            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) != null;
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) != null;
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) != null;
            boolean wearingFeet = TFHelper.getTransformerFromArmor(player, 0) != null;

            biped.bipedHead.showModel = !wearingHead;
            biped.bipedHeadwear.showModel = !wearingHead;
            biped.bipedEars.showModel = !wearingHead;

            biped.bipedBody.showModel = !wearingChest;
            biped.bipedRightArm.showModel = !wearingChest;
            biped.bipedLeftArm.showModel = !wearingChest;

            biped.bipedLeftLeg.showModel = !wearingLegs && !wearingFeet;
            biped.bipedRightLeg.showModel = !wearingLegs && !wearingFeet;

            for (int i = 0; i < 4; ++i)
            {
                if (TFHelper.getTransformerFromArmor(player, i) != null)
                {
                    TransformerModel tfModel = TFModelRegistry.getModel(TFHelper.getTransformerFromArmor(player, i));

                    if (tfModel != null)
                    {
                        tfModel.getHead().showModel = !biped.bipedHead.showModel;

                        for (ModelRendererTF modelRenderer : tfModel.getLegs())
                        {
                            modelRenderer.showModel = !biped.bipedLeftLeg.showModel;
                        }

                        for (ModelRendererTF modelRenderer : tfModel.getFeet())
                        {
                            modelRenderer.showModel = wearingFeet;
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderPlayerPost(RenderPlayerEvent.Post event)
    {
        ModelBiped modelBipedMain = event.renderer.modelBipedMain;

        if (modelBipedMain != null)
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
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase == Phase.END)
        {
            EntityPlayer player = event.player;

            if (mc.thePlayer == player)
            {
                double diffX = player.posX - lastX;
                double diffY = player.posY - lastY;
                double diffZ = player.posZ - lastZ;

                double blocksMoved = Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);

                GuiOverlay.prevSpeed = GuiOverlay.speed;
                GuiOverlay.speed = blocksMoved * 20.0 * 60.0 * 60.0 / 1000.0;

                lastX = player.posX;
                lastY = player.posY;
                lastZ = player.posZ;
            }
        }
    }

    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event)
    {
        EntityPlayerSP player = event.entity;
        Transformer transformer = TFHelper.getTransformer(player);

        float nitro = TFData.NITRO.get(player);
        boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
        boolean nitroPressed = mc.gameSettings.keyBindSprint.getIsKeyPressed() || TFKeyBinds.keyBindingNitro.getIsKeyPressed();

        int altMode = TFData.ALT_MODE.get(player);

        if (TFHelper.isFullyTransformed(player))
        {
            if ((transformer == null || transformer.canUseNitro(player, altMode)) && nitro > 0 && moveForward && nitroPressed)
            {
                event.newfov = 1.3F;
            }
        }
        else
        {
            ItemStack itemstack = player.getHeldItem();

            if (TFDataManager.getZoomTimer(player) > 0 && TFHelper.isPlayerVurp(player) && itemstack != null && itemstack.getItem() == TFItems.vurpsSniper && mc.gameSettings.thirdPersonView == 0)
            {
                event.newfov = 1.0F - (float) TFDataManager.getZoomTimer(player) / 10;
            }
        }

        if (TFHelper.getTransformationTimer(player) > 0 && !(nitro > 0 && moveForward && nitroPressed && !TFHelper.isInStealthMode(player)))
        {
            event.newfov = 1.0F;
        }
    }

//    @SubscribeEvent(priority = EventPriority.HIGHEST)
//    public void onItemToolTip(ItemTooltipEvent event)
//    {
//        ItemStack itemstack = event.itemStack;
//        String s = itemstack.getUnlocalizedName() + ".desc";
//
//        if (!s.equals(StatCollector.translateToLocal(s)))
//        {
//            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
//            {
//                s = StatCollector.translateToLocal(s);
//
//                if (s.startsWith("\\l"))
//                {
//                    s = parseLineDividing(s, 200).substring(2);
//                }
//
//                for (String s1 : parseDescVariables(s).split("\\\\n"))
//                {
//                    s1 = parseDescSpoilers(s1);
//
//                    Collections.addAll(event.toolTip, parseDescVariables(s1).split("\\\\sn"));
//                }
//            }
//            else
//            {
//                event.toolTip.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item-info.prompt"));
//            }
//        }
//    }
//
//    public String parseLineDividing(String s, int width)
//    {
//        s = trimStringNewline(s);
//        s = splitString(s, width);
//        return s;
//    }
//
//    private String trimStringNewline(String s)
//    {
//        while (s != null && s.endsWith("\\n"))
//        {
//            s = s.substring(0, s.length() - 1);
//        }
//
//        return s;
//    }
//
//    private String splitString(String s, int width)
//    {
//        List list = mc.fontRenderer.listFormattedStringToWidth(s, width);
//        String s2 = "";
//
//        for (Object aList : list)
//        {
//            String s1 = (String) aList;
//            s2 += s1 + "\\n";
//        }
//
//        return s2;
//    }
//
//    public String parseDescVariables(String s)
//    {
//        {
//            String[] keys = {"KEYBIND_ZOOM", "LIST_DISPLAYABLES"};
//            String[] values = {GameSettings.getKeyDisplayString(TFKeyBinds.keyBindingZoom.getKeyCode()), getDisplayableItemsList()};
//
//            for (int i = 0; i < keys.length; ++i)
//            {
//                s = s.replace("VAR_" + keys[i], values[i]);
//            }
//        }
//
//        String[] keys = {"BLACK", "DARK_BLUE", "DARK_GREEN", "DARK_AQUA", "DARK_RED", "DARK_PURPLE", "GOLD", "GRAY", "DARK_GRAY", "BLUE", "GREEN", "AQUA", "RED", "LIGHT_PURPLE", "YELLOW", "WHITE", "OBFUSCATED", "BOLD", "STRIKETHROUGH", "UNDERLINE", "ITALIC", "RESET"};
//        char[] values = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'k', 'l', 'm', 'n', 'o', 'r'};
//
//        for (int i = 0; i < keys.length; ++i)
//        {
//            s = s.replace("<FORMAT_" + keys[i] + ">", "\u00A7" + values[i]);
//        }
//
//        return s;
//    }
//
//    public String parseDescSpoilers(String s)
//    {
//        int varKey = 0;
//        String varName = "";
//        String varContent = "";
//
//        if (s.startsWith("[") && s.endsWith("]"))
//        {
//            s = s.substring(1, s.length() - 1);
//            String[] astring = s.split(",");
//
//            for (String s1 : astring)
//            {
//                String[] astring1 = s1.split("=");
//                String key = astring1[0];
//                String value = astring1[1];
//
//                if (key.equals("key"))
//                {
//                    varKey = Integer.valueOf(value);
//                }
//                else if (key.equals("name"))
//                {
//                    varName = parseDescVariables(value.substring(1, value.length() - 1));
//                }
//                else if (key.equals("content"))
//                {
//                    varContent = parseDescVariables(value.substring(1, value.length() - 1));
//                }
//            }
//
//            if (Keyboard.isKeyDown(varKey))
//            {
//                s = varContent;
//            }
//            else
//            {
//                s = varName;
//            }
//        }
//
//        return s;
//    }
//
//    public String getDisplayableItemsList()
//    {
//        String s = "";
//        String prefix = "   ";
//
//        for (Map.Entry<Item, Displayable> e : TransformersAPI.getDisplayables().entrySet())
//        {
//            Item item = e.getKey();
//
//            if (item.getHasSubtypes())
//            {
//                List<ItemStack> list = Lists.newArrayList();
//                item.getSubItems(item, item.getCreativeTab(), list);
//
//                for (ItemStack itemstack : list)
//                {
//                    s += prefix + itemstack.getDisplayName() + "\\sn";
//                }
//            }
//            else
//            {
//                ItemStack itemstack = new ItemStack(item);
//                s += prefix + itemstack.getDisplayName() + "\\sn";
//            }
//        }
//
//        return s;
//    }
}
