package fiskfille.tf.common.helper;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.Map;
import java.util.WeakHashMap;

public class TFRenderHelper
{
    private static final Minecraft MC = Minecraft.getMinecraft();

    private static float lastBrightnessX;
    private static float lastBrightnessY;

    private static final Map<EntityPlayer, Double> PREVIOUS_MOTION_Y = new WeakHashMap<>();

    public static final int LIGHTING_LUMINOUS = 0xF0F0;

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
        return new float[] { r, g, b };
    }

    public static void setupRenderLayers(Entity entity, ItemStack stack, ModelRenderer model)
    {
        if (!stack.isEmpty() && stack.getItem() instanceof ItemTransformerArmor)
        {
            Transformer transformer = ((ItemTransformerArmor) stack.getItem()).getTransformer();
            TransformerModel tfModel = TFModelRegistry.getModel(transformer);

            if (TFTextureHelper.isBoundTexture(TFTextureHelper.RES_ITEM_GLINT))
            {
                model.render(0.0625F);
            }
            else
            {
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                if (TFArmorDyeHelper.isDyed(stack))
                {
                    float[] primaryColor = TFRenderHelper.hexToRGB(TFArmorDyeHelper.getPrimaryColor(stack));
                    float[] secondaryColor = TFRenderHelper.hexToRGB(TFArmorDyeHelper.getSecondaryColor(stack));

                    GlStateManager.color(primaryColor[0], primaryColor[1], primaryColor[2], 1);
                    MC.getTextureManager().bindTexture(tfModel.getTexture(entity, "_primary"));
                    model.render(0.0625F);

                    GlStateManager.color(secondaryColor[0], secondaryColor[1], secondaryColor[2], 1);
                    MC.getTextureManager().bindTexture(tfModel.getTexture(entity, "_secondary"));
                    model.render(0.0625F);

                    GlStateManager.color(1, 1, 1, 1);
                    MC.getTextureManager().bindTexture(tfModel.getTexture(entity, "_base"));
                }
                else
                {
                    MC.getTextureManager().bindTexture(tfModel.getTexture(entity, ""));
                }

                model.render(0.0625F);

                if (tfModel.hasLightsLayer())
                {
                    setLighting(LIGHTING_LUMINOUS);
                    MC.getTextureManager().bindTexture(tfModel.getTexture(entity, "_lights"));
                    model.render(0.0625F);
                    resetLighting();
                }

                GlStateManager.disableBlend();
            }
        }
    }

    public static void startGlScissor(int x, int y, int width, int height)
    {
        ScaledResolution resolution = new ScaledResolution(MC);

        double scaleW = MC.displayWidth / resolution.getScaledWidth_double();
        double scaleH = MC.displayHeight / resolution.getScaledHeight_double();

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
        GL11.glScissor((int) Math.floor(x * scaleW), (int) Math.floor(MC.displayHeight - (y + height) * scaleH), (int) Math.floor((x + width) * scaleW) - (int) Math.floor(x * scaleW), (int) Math.floor(MC.displayHeight - y * scaleH) - (int) Math.floor(MC.displayHeight - (y + height) * scaleH));
    }

    public static void endGlScissor()
    {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static double getMotionY(EntityPlayer player)
    {
        double current = player == MC.player ? player.motionY : player.posY - player.prevPosY;
        double previous = PREVIOUS_MOTION_Y.getOrDefault(player, 0.0);

        return TFHelper.median(current, previous, TransformersMod.PROXY.getRenderTick());
    }

    public static void updateMotionY(EntityPlayer player)
    {
        PREVIOUS_MOTION_Y.put(player, player == MC.player ? player.motionY : player.posY - player.prevPosY);
    }

    /*public static void renderTag(String s, float x, float y, float z)
    {
        RenderManager renderManager = RenderManager.instance;
        FontRenderer fontrenderer = renderManager.getFontRenderer();
        float f2 = -0.02F;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(MC.thePlayer.rotationYaw + 180, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-MC.thePlayer.rotationPitch, 1.0F, 0.0F, 0.0F);
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
        GlStateManager.popMatrix();
    }*/

    /*public static void faceVec(Vec3d src, Vec3d dst)
    {
        double deltaX = dst.x - src.x;
        double deltaY = dst.y - src.y;
        double deltaZ = dst.z - src.z;
        double delta = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        float yaw = (float) (Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) -(Math.atan2(deltaY, delta) * 180.0D / Math.PI);
        GlStateManager.rotate(-yaw, 0, 1, 0);
        GlStateManager.rotate(pitch, 1, 0, 0);
    }

    public static void renderEnergyTransmissions(TileEntity transmitterTile, double x, double y, double z, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        setLighting(LIGHTING_LUMINOUS);

        IEnergyTransmitter transmitter = (IEnergyTransmitter) transmitterTile;
        TransmissionHandler transmissionHandler = transmitter.getTransmissionHandler();
        boolean renderBeams = false;

        if (transmitterTile instanceof TileEntityRelayTower)
        {
            TileEntityRelayTower relay = (TileEntityRelayTower) transmitterTile;
            renderBeams = relay.data.isPowered;
        }
        else
        {
            renderBeams = transmitter.getEnergy() > 0;
        }

        if (transmitterTile instanceof TileEntityMachine)
        {
            TileEntityMachine machine = (TileEntityMachine) transmitterTile;
            renderBeams &= machine.canActivate();
        }

        if (renderBeams)
        {
            for (ReceiverEntry entry : transmissionHandler.getReceivers())
            {
                boolean invertCurrent = transmitterTile instanceof TileEntityRelayTower && ((TileEntityRelayTower) transmitterTile).data.invertCurrent.contains(entry.getCoords());
                boolean canReach = entry.canReach();

                if (entry.getTile() == null)
                {
                    continue;
                }

                TileEntity tile = transmitterTile;
                Vec3d srcOffset1 = transmitter.getEnergyOutputOffset();
                Vec3d dstOffset1 = entry.getReceiver().getEnergyInputOffset();
                Vec3d srcOffset;
                Vec3d dstOffset;

                if (tile instanceof ITransmitterRender)
                {
                    srcOffset1 = ((ITransmitterRender) tile).getRenderOutputOffset();
                }

                if (entry.getTile() instanceof IReceiverRender)
                {
                    dstOffset1 = ((IReceiverRender) entry.getTile()).getRenderInputOffset();
                }

                if (invertCurrent)
                {
                    tile = entry.getTile();
                    entry = new ReceiverEntry(transmitterTile);
                    entry.setCanReach(canReach);

                    srcOffset = dstOffset1.addVector(0, 0, 0);
                    dstOffset = srcOffset1.addVector(0, 0, 0);
                }
                else
                {
                    srcOffset = srcOffset1.addVector(0, 0, 0);
                    dstOffset = dstOffset1.addVector(0, 0, 0);
                }

                IEnergyReceiver receiver = entry.getReceiver();
                DimensionalCoords coords = entry.getCoords();
                Vec3d src = srcOffset.addVector(tile.getPos().getX() + 0.5F, tile.getPos().getY() + 0.5F, tile.getPos().getZ() + 0.5F);
                Vec3d dst = dstOffset.addVector(coords.x + 0.5F, coords.y + 0.5F, coords.z + 0.5F);

                if (!canReach)
                {
                    double d = 1F / dst.distanceTo(src);
                    src = Vec3.createVectorHelper(src.x + (dst.x - src.x) * d, src.y + (dst.y - src.y) * d, src.z + (dst.z - src.z) * d);
                    MovingObjectPosition mop = TFEnergyHelper.rayTraceBlocks(tile.getWorldObj(), src, dst);

                    if (mop != null)
                    {
                        dst = mop.hitVec;
                    }
                }

                double x1 = 0.5F + srcOffset.x;
                double y1 = 0.5F + srcOffset.y;
                double z1 = 0.5F + srcOffset.z;
                double deltaX = dst.x - tile.x;
                double deltaY = dst.y - tile.y;
                double deltaZ = dst.z - tile.z;

                src = new Vec3d(x1, y1, z1);
                dst = new Vec3d(deltaX, deltaY, deltaZ);

                int primary = 0x57ABAF;
                int secondary = 0x7BF2F8;
                int parentPrimary = primary;
                int parentSecondary = secondary;

                if (!canReach)
                {
                    primary = 0xAF5B57;
                    secondary = 0xF8817B;
                }
//                else if (!(receiverTile instanceof IEnergyTransmitter) && receiver.getEnergy() >= receiver.getMaxEnergy())
//                {
//                    primary = 0x62AF57;
//                    secondary = 0x8AF87B;
//                }

                GlStateManager.pushMatrix();
                GlStateManager.translate(x + x1, y + y1, z + z1);

                if (invertCurrent)
                {
                    GlStateManager.translate((tile.x - coords.posX), (tile.y - coords.posY), (tile.z - coords.posZ));
                }

                renderEnergyBeam(src, dst, primary, secondary, parentPrimary, parentSecondary);
                GlStateManager.popMatrix();
            }
        }

        resetLighting();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GlStateManager.popMatrix();
    }

    public static void renderEnergyBeam(Vec3d src, Vec3d dst, int primaryColor, int secondaryColor, int primaryParentColor, int secondaryParentColor)
    {
        Tessellator tessellator = Tessellator.getInstance();
        float partialTicks = ClientTickHandler.renderTick;
        float[] primary = hexToRGB(primaryColor);
        float[] secondary = hexToRGB(secondaryColor);
        float[] parentPrimary = hexToRGB(primaryParentColor);
        float[] parentSecondary = hexToRGB(secondaryParentColor);

        double width = 1F / 16;
        double length = src.distanceTo(dst);
        int segments = MathHelper.floor(length * 8);

        faceVec(src, dst);

        for (int i = 0; i < segments; ++i)
        {
            double segmentLength = length / segments;
            double start = i * segmentLength;
            double end = (i + 1) * segmentLength;
            float f = (float) Math.cos(i / (segments * 0.15625F) - (MC.player.ticksExisted + partialTicks) / 5);
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

    public static void renderEnergyStatic(Vec3d src, Vec3d dst, double width, float intensity, int segments, long seed)
    {
        Tessellator tessellator = Tessellator.getInstance();

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        setLighting(LIGHTING_LUMINOUS);

        GlStateManager.translate(-src.x, -src.y, -src.z);
        faceVec(dst, src);
        GlStateManager.rotate(90, 1, 0, 0);

        float[] primary = hexToRGB(0x57ABAF);
        float[] secondary = hexToRGB(0x7BF2F8);
        double length = src.distanceTo(dst);

        Random rand = new Random(seed + MC.player.ticksExisted * 10);
        Random randPrev = new Random(seed + (MC.player.ticksExisted - 1) * 10);

        src = new Vec3d(0, 0, 0);

        for (int i = 0; i < segments; ++i)
        {
            float f = (float) i / segments;
            dst = new Vec3d(0, (i + 1) * length / segments, 0);

            if (i < segments - 1)
            {
                float angle = (float) Math.toRadians(90 * intensity) * (1 - f);
                dst.rotatePitch((TFHelper.median(rand.nextFloat(), randPrev.nextFloat(), ClientTickHandler.renderTick) - 0.5F) * 2 * angle);
                dst.rotateYaw((TFHelper.median(rand.nextFloat(), randPrev.nextFloat(), ClientTickHandler.renderTick) - 0.5F) * 2 * angle);
                dst.rotateAroundZ((TFHelper.median(rand.nextFloat(), randPrev.nextFloat(), ClientTickHandler.renderTick) - 0.5F) * 2 * angle);
            }
            else
            {
                dst = Vec3.createVectorHelper(0, length, 0);
            }

            dst.x = MathHelper.clamp(dst.x, -0.0625F * 1.25F, 0.0625F * 1.25F);
            dst.z = MathHelper.clamp(dst.z, -0.0625F * 1.25F, 0.0625F * 1.25F);
            dst.y = MathHelper.clamp(dst.y, 0, length);

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

            GlStateManager.pushMatrix();
            GlStateManager.translate(src.x, src.y, src.z);
            faceVec(src, dst);
            tessellator.draw();
            GlStateManager.popMatrix();
            src = dst;
        }

        resetLighting();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GlStateManager.popMatrix();
    }

    public static int getBlockDestroyProgress(World world, int x, int y, int z)
    {
        Map<Integer, DestroyBlockProgress> damagedBlocks = ObfuscationReflectionHelper.getPrivateValue(RenderGlobal.class, MC.renderGlobal, "damagedBlocks", "field_72738_E", "O");

        if (!damagedBlocks.isEmpty())
        {
            for (DestroyBlockProgress progress : damagedBlocks.values())
            {
                IBlockState state = world.getBlockState(progress.getPosition());
                int[] offsets = TFTileHelper.getTileBaseOffsets(world.getTileEntity(x, y, z), state);

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
    }*/

    public static boolean shouldOverrideView(EntityPlayer player)
    {
        return TFHelper.getHeight(player) != 1.8F || TFHelper.getScale(player) != 1;
    }

    public static boolean shouldOverrideThirdPersonDistance(EntityPlayer player)
    {
        return player.getRidingEntity() == null && (TFHelper.getTransformer(player) != null || TFData.PREV_TRANSFORMER.get(player) != null);
    }

    /*public static void renderItemIntoGUI(int x, int y, ItemStack itemstack)
    {
        if (itemstack != null)
        {
            FontRenderer font = itemstack.getItem().getFontRenderer(itemstack);

            if (font == null)
            {
                font = MC.fontRenderer;
            }

            itemRender.renderItemAndEffectIntoGUI(font, MC.getTextureManager(), itemstack, x, y);

            if (itemstack.stackSize > 1)
            {
                itemRender.renderItemOverlayIntoGUI(font, MC.getTextureManager(), itemstack, x, y, itemstack.stackSize + "");
            }
        }
    }*/

    public static void setupRenderItemIntoGUI()
    {
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        RenderHelper.enableGUIStandardItemLighting();
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
