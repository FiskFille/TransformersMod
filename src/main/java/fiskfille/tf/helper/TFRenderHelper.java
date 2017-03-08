package fiskfille.tf.helper;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverEntry;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.tick.ClientTickHandler;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFRenderHelper
{
    private static Minecraft mc = Minecraft.getMinecraft();
    private static RenderItem itemRender = new RenderItem();

    private static float lastBrightnessX;
    private static float lastBrightnessY;
    private static final Map<EntityPlayer, Double> previousMotionY = new WeakHashMap<EntityPlayer, Double>();

    public static void setLighting(int lighting)
    {
        storeLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lighting % 65536 / 255.0F, lighting / 65536 / 255.0F);
    }

    public static void storeLighting()
    {
        lastBrightnessX = OpenGlHelper.lastBrightnessX;
        lastBrightnessY = OpenGlHelper.lastBrightnessY;
    }

    public static void resetLighting()
    {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
    }

    public static float[] hexToRGB(int hex)
    {
        float r = ((hex & 0xFF0000) >> 16) / 255F;
        float g = ((hex & 0xFF00) >> 8) / 255F;
        float b = (hex & 0xFF) / 255F;
        return new float[] {r, g, b};
    }

    public static void setupRenderLayers(ItemStack itemstack, ModelRenderer model, boolean hasLightsLayer)
    {
        if (itemstack != null && itemstack.getItem() instanceof ItemTransformerArmor)
        {
            Transformer transformer = ((ItemTransformerArmor) itemstack.getItem()).getTransformer();
            TransformerModel tfModel = TFModelRegistry.getModel(transformer);

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            if (TFArmorDyeHelper.isDyed(itemstack))
            {
                float[] afloat = hexToRGB(TFArmorDyeHelper.getPrimaryColor(itemstack));

                GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_primary.png"));
                model.render(0.0625F);

                afloat = hexToRGB(TFArmorDyeHelper.getSecondaryColor(itemstack));
                GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_secondary.png"));
                model.render(0.0625F);

                GL11.glColor4f(1, 1, 1, 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_base.png"));
            }

            model.render(0.0625F);

            if (hasLightsLayer)
            {
                setLighting(61680);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
                model.render(0.0625F);
                TFRenderHelper.resetLighting();
            }

            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    public static void startGlScissor(int x, int y, int width, int height)
    {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution reso = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        double scaleW = mc.displayWidth / reso.getScaledWidth_double();
        double scaleH = mc.displayHeight / reso.getScaledHeight_double();

        if (width <= 0 || height <= 0)
        {
            return;
        }
        if (x < 0)
        {
            x = 0;
        }
        if (y < 0)
        {
            y = 0;
        }

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) Math.floor(x * scaleW), (int) Math.floor(mc.displayHeight - (y + height) * scaleH), (int) Math.floor((x + width) * scaleW) - (int) Math.floor(x * scaleW), (int) Math.floor(mc.displayHeight - y * scaleH) - (int) Math.floor(mc.displayHeight - (y + height) * scaleH));
    }

    public static void endGlScissor()
    {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static double getMotionY(EntityPlayer player)
    {
        double current = player == mc.thePlayer ? player.motionY : player.posY - player.prevPosY;
        double previous = TFRenderHelper.previousMotionY.containsKey(player) ? TFRenderHelper.previousMotionY.get(player) : 0.0;

        return TFHelper.median(current, previous, ClientTickHandler.renderTick);
    }

    public static void updateMotionY(EntityPlayer player)
    {
        TFRenderHelper.previousMotionY.put(player, player == mc.thePlayer ? player.motionY : player.posY - player.prevPosY);
    }

    public static void renderTag(String s, float x, float y, float z)
    {
        RenderManager renderManager = RenderManager.instance;
        FontRenderer fontrenderer = renderManager.getFontRenderer();
        float f2 = -0.02F;
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(mc.thePlayer.rotationYaw + 180, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-mc.thePlayer.rotationPitch, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-f2, -f2, f2);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tessellator.startDrawingQuads();
        int i = fontrenderer.getStringWidth(s) / 2;
        tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        tessellator.addVertex(-i - 1, -1.0D, 0.0D);
        tessellator.addVertex(-i - 1, 8.0D, 0.0D);
        tessellator.addVertex(i + 1, 8.0D, 0.0D);
        tessellator.addVertex(i + 1, -1.0D, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, -1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public static void faceVec(Vec3 src, Vec3 dst)
    {
        double d0 = dst.xCoord - src.xCoord;
        double d1 = dst.yCoord - src.yCoord;
        double d2 = dst.zCoord - src.zCoord;
        double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        float yaw = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) -(Math.atan2(d1, d3) * 180.0D / Math.PI);
        GL11.glRotated(-yaw, 0, 1, 0);
        GL11.glRotated(pitch, 1, 0, 0);
    }

    public static void renderEnergyTransmissions(TileEntity transmitterTile, double x, double y, double z, float partialTicks)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        TFRenderHelper.setLighting(61680);

        IEnergyTransmitter transmitter = (IEnergyTransmitter) transmitterTile;
        TransmissionHandler transmissionHandler = transmitter.getTransmissionHandler();
        
        Vec3 outOffset = transmitter.getEnergyOutputOffset();
        boolean flag = false;
        
        if (transmitterTile instanceof TileEntityRelayTower)
        {
            TileEntityRelayTower relay = (TileEntityRelayTower) transmitterTile;
            flag = relay.data.isPowered;
        }
        else
        {
            flag = transmitter.getEnergy() > 0;
        }
        
//        if (flag) // TODO: Fix beams not rendered by relays if there isn't a transmitter in ancestry
        {
            for (ReceiverEntry entry : transmissionHandler.getReceivers())
            {
                TileEntity receiverTile = entry.getTile();
                IEnergyReceiver receiver = entry.getReceiver();
                DimensionalCoords coords = entry.getCoords();

                if (receiverTile == null)
                {
                    continue;
                }
                
                Vec3 src = outOffset.addVector(transmitterTile.xCoord + 0.5F, transmitterTile.yCoord + 0.5F, transmitterTile.zCoord + 0.5F);
                Vec3 dst = receiver.getEnergyInputOffset().addVector(coords.posX + 0.5F, coords.posY + 0.5F, coords.posZ + 0.5F);

                double d = 1F / dst.distanceTo(src);
                src = Vec3.createVectorHelper(src.xCoord + (dst.xCoord - src.xCoord) * d, src.yCoord + (dst.yCoord - src.yCoord) * d, src.zCoord + (dst.zCoord - src.zCoord) * d);
                MovingObjectPosition mop = TFEnergyHelper.rayTraceBlocks(transmitterTile.getWorldObj(), src, dst);

                if (mop != null)
                {
                    dst = mop.hitVec;
                }

                double x1 = 0.5F + outOffset.xCoord;
                double y1 = 0.5F + outOffset.yCoord;
                double z1 = 0.5F + outOffset.zCoord;
                double deltaX = dst.xCoord - transmitterTile.xCoord;
                double deltaY = dst.yCoord - transmitterTile.yCoord;
                double deltaZ = dst.zCoord - transmitterTile.zCoord;

                src = Vec3.createVectorHelper(x1, y1, z1);
                dst = Vec3.createVectorHelper(deltaX, deltaY, deltaZ);

                int primary = 0x57ABAF;
                int secondary = 0x7BF2F8;
                int parentPrimary = primary;
                int parentSecondary = secondary;
                
                if (!entry.canReach())
                {
                    primary = 0xAF5B57;
                    secondary = 0xF8817B;
                }
                else if (!(receiverTile instanceof IEnergyTransmitter) && receiver.getEnergy() >= receiver.getMaxEnergy())
                {
                    primary = 0x62AF57;
                    secondary = 0x8AF87B;
                }

                GL11.glPushMatrix();
                GL11.glTranslated(x + x1, y + y1, z + z1);
                renderEnergyBeam(src, dst, primary, secondary, parentPrimary, parentSecondary);
                GL11.glPopMatrix();
            }
        }

        TFRenderHelper.resetLighting();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    public static void renderEnergyBeam(Vec3 src, Vec3 dst, int primaryColor, int secondaryColor, int primaryParentColor, int secondaryParentColor)
    {
        Tessellator tessellator = Tessellator.instance;
        float partialTicks = ClientTickHandler.renderTick;
        float[] primary = hexToRGB(primaryColor);
        float[] secondary = hexToRGB(secondaryColor);
        float[] parentPrimary = hexToRGB(primaryParentColor);
        float[] parentSecondary = hexToRGB(primaryParentColor);

        double width = 1F / 16;
        double length = src.distanceTo(dst);
        int segments = MathHelper.floor_double(length * 8);

        TFRenderHelper.faceVec(src, dst);

        for (int i = 0; i < segments; ++i)
        {
            double segmentLength = length / segments;
            double start = i * segmentLength;
            double end = i * segmentLength + segmentLength;
            float f = (float) Math.cos(i / (segments * 0.15625F) - (mc.thePlayer.ticksExisted + partialTicks) / 5);
            float f1 = 1 - f;
            float f2 = Math.min((float) i / segments * 3, 1);
            float f3 = 1 - f2;

            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F((primary[0] * f + secondary[0] * f1) * f2 + (parentPrimary[0] * f + parentSecondary[0] * f1) * f3, (primary[1] * f + secondary[1] * f1) * f2 + (parentPrimary[1] * f + parentSecondary[1] * f1) * f3, (primary[2] * f + secondary[2] * f1) * f2 + (parentPrimary[2] * f + parentSecondary[2] * f1) * f3, 1);

            tessellator.addVertex(width, width, end);
            tessellator.addVertex(width, width, start);
            tessellator.addVertex(-width, width, start);
            tessellator.addVertex(-width, width, end);
            tessellator.addVertex(-width, -width, start);
            tessellator.addVertex(width, -width, start);
            tessellator.addVertex(width, -width, end);
            tessellator.addVertex(-width, -width, end);
            tessellator.addVertex(-width, width, start);
            tessellator.addVertex(-width, -width, start);
            tessellator.addVertex(-width, -width, end);
            tessellator.addVertex(-width, width, end);
            tessellator.addVertex(width, -width, end);
            tessellator.addVertex(width, -width, start);
            tessellator.addVertex(width, width, start);
            tessellator.addVertex(width, width, end);

            if (i == segments - 1)
            {
                tessellator.addVertex(width, -width, end);
                tessellator.addVertex(width, width, end);
                tessellator.addVertex(-width, width, end);
                tessellator.addVertex(-width, -width, end);
            }
            else if (i == 0)
            {
                tessellator.addVertex(-width, width, start);
                tessellator.addVertex(width, width, start);
                tessellator.addVertex(width, -width, start);
                tessellator.addVertex(-width, -width, start);
            }

            tessellator.draw();
        }
    }

    public static void renderEnergyStatic(Vec3 src, Vec3 dst, double width, float intensity)
    {
        Tessellator tessellator = Tessellator.instance;

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        TFRenderHelper.setLighting(61680);

        GL11.glTranslated(-src.xCoord, -src.yCoord, -src.zCoord);
        faceVec(dst, src);
        GL11.glRotatef(90, 1, 0, 0);

        float[] primary = TFRenderHelper.hexToRGB(0x57ABAF);
        float[] secondary = TFRenderHelper.hexToRGB(0x7BF2F8);
        double length = src.distanceTo(dst);
        int segments = 16;

        Random rand = new Random(8964355487L + mc.thePlayer.ticksExisted * 100000);
        Random randPrev = new Random(8964355487L + (mc.thePlayer.ticksExisted - 1) * 100000);

        src = Vec3.createVectorHelper(0, 0, 0);

        for (int i = 0; i < segments; ++i)
        {
            float f = (float) i / segments;
            dst = Vec3.createVectorHelper(0, (i + 1) * length / segments, 0);

            if (i < segments - 1)
            {
                float angle = (float) Math.toRadians(90 * intensity) * (1 - f);
                dst.rotateAroundX((TFHelper.median(rand.nextFloat(), randPrev.nextFloat(), ClientTickHandler.renderTick) - 0.5F) * 2 * angle);
                dst.rotateAroundY((TFHelper.median(rand.nextFloat(), randPrev.nextFloat(), ClientTickHandler.renderTick) - 0.5F) * 2 * angle);
                dst.rotateAroundZ((TFHelper.median(rand.nextFloat(), randPrev.nextFloat(), ClientTickHandler.renderTick) - 0.5F) * 2 * angle);
            }
            else
            {
                dst = Vec3.createVectorHelper(0, length, 0);
            }

            dst.xCoord = MathHelper.clamp_double(dst.xCoord, -0.0625F * 1.25F, 0.0625F * 1.25F);
            dst.zCoord = MathHelper.clamp_double(dst.zCoord, -0.0625F * 1.25F, 0.0625F * 1.25F);
            dst.yCoord = MathHelper.clamp_double(dst.yCoord, 0, length);

            double segmentLength = src.distanceTo(dst);
            float f1 = (float) Math.cos(i / (segments * 0.15625F));
            float f2 = 1 - f1;

            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F(primary[0] * f1 + secondary[0] * f2, primary[1] * f1 + secondary[1] * f2, primary[2] * f1 + secondary[2] * f2, 1);
            tessellator.addVertex(width, width, segmentLength);
            tessellator.addVertex(width, width, 0);
            tessellator.addVertex(-width, width, 0);
            tessellator.addVertex(-width, width, segmentLength);
            tessellator.addVertex(-width, -width, 0);
            tessellator.addVertex(width, -width, 0);
            tessellator.addVertex(width, -width, segmentLength);
            tessellator.addVertex(-width, -width, segmentLength);
            tessellator.addVertex(-width, width, 0);
            tessellator.addVertex(-width, -width, 0);
            tessellator.addVertex(-width, -width, segmentLength);
            tessellator.addVertex(-width, width, segmentLength);
            tessellator.addVertex(width, -width, segmentLength);
            tessellator.addVertex(width, -width, 0);
            tessellator.addVertex(width, width, 0);
            tessellator.addVertex(width, width, segmentLength);
            tessellator.addVertex(width, -width, segmentLength);
            tessellator.addVertex(width, width, segmentLength);
            tessellator.addVertex(-width, width, segmentLength);
            tessellator.addVertex(-width, -width, segmentLength);
            tessellator.addVertex(-width, width, 0);
            tessellator.addVertex(width, width, 0);
            tessellator.addVertex(width, -width, 0);
            tessellator.addVertex(-width, -width, 0);

            GL11.glPushMatrix();
            GL11.glTranslated(src.xCoord, src.yCoord, src.zCoord);
            TFRenderHelper.faceVec(src, dst);
            tessellator.draw();
            GL11.glPopMatrix();
            src = dst;
        }

        TFRenderHelper.resetLighting();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    public static int getBlockDestroyProgress(World world, int x, int y, int z)
    {
        Map damagedBlocks = ObfuscationReflectionHelper.getPrivateValue(RenderGlobal.class, mc.renderGlobal, "damagedBlocks", "field_72738_E", "O");

        if (!damagedBlocks.isEmpty())
        {
            Iterator iterator = damagedBlocks.values().iterator();

            while (iterator.hasNext())
            {
                DestroyBlockProgress progress = (DestroyBlockProgress) iterator.next();

                int metadata = world.getBlockMetadata(progress.getPartialBlockX(), progress.getPartialBlockY(), progress.getPartialBlockZ());
                int[] offsets = TFTileHelper.getTileBaseOffsets(world.getTileEntity(x, y, z), metadata);

                if (x == progress.getPartialBlockX() + offsets[0] && y == progress.getPartialBlockY() + offsets[1] && z == progress.getPartialBlockZ() + offsets[2])
                {
                    return progress.getPartialBlockDamage();
                }
            }
        }

        return -1;
    }

    public static void renderBlock(Block block, IIcon icon, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
    }

    public static void renderBlock(Block block, int meta, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
        tessellator.draw();
    }

    public static void renderBlockAllFaces(RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon)
    {
        renderer.renderFaceYNeg(block, x, y, z, icon);
        renderer.renderFaceYPos(block, x, y, z, icon);
        renderer.renderFaceZNeg(block, x, y, z, icon);
        renderer.renderFaceZPos(block, x, y, z, icon);
        renderer.renderFaceXNeg(block, x, y, z, icon);
        renderer.renderFaceXPos(block, x, y, z, icon);
    }

    public static boolean shouldOverrideView(EntityPlayer player)
    {
        return TFHelper.getHeight(player) != 1.8F || TFHelper.getScale(player) != 1;
    }

    public static boolean shouldOverrideThirdPersonDistance(EntityPlayer player)
    {
        return player.ridingEntity == null && (TFHelper.getTransformer(player) != null || TFData.PREV_TRANSFORMER.get(player) != null);
    }

    public static void renderItemIntoGUI(int x, int y, ItemStack itemstack)
    {
        if (itemstack != null)
        {
            FontRenderer font = itemstack.getItem().getFontRenderer(itemstack);

            if (font == null)
            {
                font = mc.fontRenderer;
            }

            itemRender.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), itemstack, x, y);

            if (itemstack.stackSize > 1)
            {
                itemRender.renderItemOverlayIntoGUI(font, mc.getTextureManager(), itemstack, x, y, itemstack.stackSize + "");
            }
        }
    }

    public static void setupRenderItemIntoGUI()
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
    }

    public static void finishRenderItemIntoGUI()
    {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1, 1, 1, 1);
    }
}
