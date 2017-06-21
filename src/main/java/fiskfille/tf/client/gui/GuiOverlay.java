package fiskfille.tf.client.gui;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.config.TFConfig;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.event.TFShootHandler;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class GuiOverlay extends Gui
{
    private static final Minecraft MC = Minecraft.getMinecraft();

    public static final ResourceLocation MOD_ICONS = new ResourceLocation(TransformersMod.MODID, "textures/gui/mod_icons.png");
    public static final ResourceLocation SNIPER_SCOPE = new ResourceLocation(TransformersMod.MODID, "textures/misc/sniper_scope.png");

    public static double prevSpeed;
    public static double speed;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRender(RenderGameOverlayEvent.Pre event)
    {
        if (!event.isCanceled())
        {
            int width = event.getResolution().getScaledWidth();
            int height = event.getResolution().getScaledHeight();
            EntityPlayer player = MC.player;

            if (event.getType() == ElementType.HOTBAR)
            {
                Transformer transformer = TFHelper.getTransformer(player);
                boolean flag = transformer == null || transformer.renderSpeedAndNitro(player, TFData.ALT_MODE.get(player));

                if (flag)
                {
                    this.renderNitroAndSpeed(event, width, height, player);
                }

//                this.renderKatanaDash(event, width, height, player); TODO
                this.renderShotsLeft(event, width, height, player);
//                this.renderLaserCharge(event, width, height, player);
//                this.renderTutorial(event, width, height, player);
            }
        }
    }

    /*public void renderLaserCharge(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
    {
        int altMode = TFData.ALT_MODE.get(player);

        ItemStack heldItem = player.getHeldItem();
        Transformer transformer = TFHelper.getTransformer(player);
        boolean hasSniper = !heldItem.isEmpty() && heldItem.getItem() instanceof ItemVurpsSniper && TFHelper.isInRobotMode(player);

        if (transformer instanceof TransformerVurp && (hasSniper || transformer.canShoot(player, altMode)))
        {
            float stealthModeTimer = TFHelper.getStealthModeTimer(player);

            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(0F, 0F, 0F, 0.3F);

            int offset = Math.round((1 - Math.min(TFHelper.getTransformationTimer(player), stealthModeTimer)) * 210);
            int x = 6 - offset;

            if (hasSniper)
            {
                x = 6;
            }

            int y = 3;

            if (!hasSniper)
            {
                y = 30;
            }

            // Charge Outline
            this.drawTexturedModalRect(x + 17, y, 0, 0, 102, 12);

            if (hasSniper)
            {
                GlStateManager.color(0.0F, 1.0F, 1.0F, 0.5F);
            }
            else
            {
                GlStateManager.color(1F, 0F, 0F, 0.5F);
            }

            // Charge Bar
            drawTexturedModalRect(x + 18, y + 1, 0, 0, TFShootHandler.laserCharge * 2, 10);

            GlStateManager.color(0F, 0F, 0F, 0.2F);
            this.drawTexturedModalRect(x - 1, y, 0, 0, 16, 16);
            this.drawTexturedModalRect(x, y + 1, 0, 0, 14, 14);
            GlStateManager.enableTexture2D();

            this.drawCenteredString(MC.fontRenderer, I18n.format("stats.laser_charge.name"), x + 50 + 18, y + 2, 0xffffff);

            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.disableBlend();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableColorMaterial();
            RENDER_ITEM.renderItemIntoGUI(new ItemStack(transformer.getShootItem(altMode)), x - 1, y);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();

            float scale = 0.5F;
            GlStateManager.pushMatrix();
            GlStateManager.scale(scale, scale, scale);
            this.drawString(MC.fontRenderer, I18n.format("stats.ammo.name", I18n.format(transformer.getShootItem(altMode).getUnlocalizedName() + ".name")), (int) ((x - 1) / scale), (int) ((y + 17) / scale), 0xffffff);
            GlStateManager.popMatrix();
        }
    }*/

    public void renderNitroAndSpeed(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
    {
        float transformationTimer = TFHelper.getTransformationTimer(player);

        if (transformationTimer > 0)
        {
            float nitro = TFHelper.median(TFData.NITRO.get(player), TFData.PREV_NITRO.get(player), event.getPartialTicks());
            double speed = TFHelper.median(GuiOverlay.speed, GuiOverlay.prevSpeed, event.getPartialTicks());
            int offset = Math.round((1 - transformationTimer) * 210);

            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(0F, 0F, 0F, 0.3F);
            // Speed Outline
            this.drawTexturedModalRect(5 - offset, 3, 0, 0, 202, 12);

            // Nitro Outline
            this.drawTexturedModalRect(5 - offset, 16, 0, 0, 202, 12);
            GlStateManager.color(0.0F, 1.0F, 1.0F, 0.5F);
            // Nitro Bar
            this.drawTexturedModalRect(6 - offset, 4, 0, 0, Math.round(nitro * 200), 10);
            GlStateManager.color(1F, 0F, 0F, 0.5F);
            // Speed Bar
            this.drawTexturedModalRect(6 - offset, 17, 0, 0, speed > 200 ? 200 : (int) speed, 10);
            GlStateManager.enableTexture2D();

            this.drawCenteredString(MC.fontRenderer, I18n.format("stats.nitro.name"), 106 - offset, 5, 0xffffff);
            this.drawCenteredString(MC.fontRenderer, (int) (TFConfig.aesthetic.useMiles ? speed * 0.621371192 : speed) + (TFConfig.aesthetic.useMiles ? " mph" : " km/h"), 106 - offset, 18, 0xffffff);
        }
    }

    public void renderShotsLeft(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
    {
        Transformer transformer = TFHelper.getTransformer(player);
        int altMode = TFData.ALT_MODE.get(player);

        if (transformer != null/* && !(transformer instanceof TransformerVurp)*/)
        {
            float transformationTimer = TFHelper.getTransformationTimer(player);
            float stealthModeTimer = TFHelper.getStealthModeTimer(player);

            if (transformationTimer > 0 && transformer.canShoot(player, altMode))
            {
                float f = transformationTimer;

                if (transformer.hasStealthForce(player, altMode))
                {
                    f = stealthModeTimer;
                }

                int offset = Math.round((1 - Math.min(transformationTimer, f)) * 210);
                int y = 30;
                int x = 6;
                int j = 20 - TFShootHandler.shootCooldown;
                double d = j * 2.5;
                String shotsLeft = "" + TFShootHandler.shotsLeft;

                if (TFShootHandler.shotsLeft <= 0)
                {
                    shotsLeft = TextFormatting.RED + shotsLeft;
                }

                this.drawString(MC.fontRenderer, I18n.format("stats.shots_left.name") + ": " + shotsLeft, x + 19 - offset, 32, 0xffffff);

                GlStateManager.disableTexture2D();
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GlStateManager.color(0F, 0F, 0F, 0.15F);
                this.drawTexturedModalRect(x + 94 - offset, y, 0, 0, 52, 12);
                GlStateManager.color(0F, 0F, 0F, 0.2F);
                this.drawTexturedModalRect(x - 1 - offset, y - 1, 0, 0, 16, 16);
                this.drawTexturedModalRect(x - offset, y, 0, 0, 14, 14);
                GlStateManager.color(1F, 0F, 0F, 0.25F);
                this.drawTexturedModalRect(x + 95 - offset, y + 1, 0, 0, (int) d, 10);
                GlStateManager.enableTexture2D();

                GlStateManager.disableLighting();
                GlStateManager.enableRescaleNormal();
                GlStateManager.enableColorMaterial();
                GlStateManager.enableLighting();
                MC.getRenderItem().renderItemIntoGUI(new ItemStack(transformer.getShootItem(altMode)), x - 1 - offset, y - 1);
                GlStateManager.disableLighting();
                GlStateManager.depthMask(true);
                GlStateManager.enableDepth();

                float scale = 0.5F;
                GlStateManager.pushMatrix();
                GlStateManager.translate(x - 1 - offset, y + 16, 0);
                GlStateManager.scale(scale, scale, scale);
                this.drawString(MC.fontRenderer, I18n.format("stats.ammo.name", I18n.format(transformer.getShootItem(altMode).getUnlocalizedName() + ".name")), 0, 0, 0xffffff);
                GlStateManager.popMatrix();
            }
        }
        /*else if (transformer instanceof TransformerVurp)
        {
            ItemStack heldItem = player.getHeldItem();

            if (heldItem != null)
            {
                float transformationTimer = TFHelper.getTransformationTimer(player);

                if (transformationTimer == 0 && heldItem.getItem() == TFItems.vurpsSniper)
                {
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GlStateManager.color(0F, 0F, 0F, 0.15F);

                    if (MC.gameSettings.thirdPersonView == 0 && heldItem.getItem() == TFItems.vurpsSniper && TFDataManager.getZoomTimer(player) > 7)
                    {
                        GlStateManager.disableDepth();
                        GlStateManager.depthMask(false);
                        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        GlStateManager.disableAlpha();
                        MC.getTextureManager().bindTexture(SNIPER_SCOPE);
                        Tessellator tessellator = Tessellator.getInstance();
                        VertexBuffer builder = tessellator.getBuffer();
                        builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
                        builder.pos(0.0, height, -90.0).tex(0.0, 1.0).endVertex();
                        builder.pos(width, height, -90.0).tex(1.0, 1.0).endVertex();
                        builder.pos(width, 0.0, -90.0).tex(1.0, 0.0).endVertex();
                        builder.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
                        tessellator.draw();
                        GlStateManager.depthMask(true);
                        GlStateManager.enableDepth();
                        GlStateManager.enableAlpha();
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    }
                }
            }
        }*/
    }

    /*public void renderKatanaDash(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
    {
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.purgesKatana && !TFHelper.isFullyTransformed(player) && TFHelper.getTransformer(player) instanceof TransformerPurge)
        {
            if (player.isUsingItem())
            {
                int x = width / 2 - 26;
                int j = TFItems.purgesKatana.getMaxItemUseDuration(player.getHeldItem()) - player.getItemInUseCount();
                double d = (double) j / 10;

                if (d > 2.0D)
                {
                    d = 2.0D;
                }

                GlStateManager.disableTexture2D();
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GlStateManager.color(0F, 0F, 0F, 0.15F);

                int y;

                if (TFConfig.aesthetic.purgeDashTop)
                {
                    y = 5;
                }
                else
                {
                    y = height / 2 + 9;
                }

                this.drawTexturedModalRect(x, y, 0, 0, 52, 12);
                GlStateManager.color(1F, 0F, 0F, 0.25F);
                this.drawTexturedModalRect(x + 1, y + 1, 0, 0, (int) (d * 25), 10);

                GlStateManager.enableTexture2D();
            }
        }
    }

    public void renderTutorial(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
    {
        if (TutorialHandler.currentTutorial != null)
        {
            TutorialHandler.currentTutorial.ticker.render(event, width, height, player);
        }

        if (TutorialHandler.completedTutorial != null)
        {
            MC.getTextureManager().bindTexture(new ResourceLocation("textures/gui/achievement/achievement_background.png"));
            int i = (TutorialHandler.animationTimer > 90 ? (100 - TutorialHandler.animationTimer) * 3.2F : TutorialHandler.animationTimer < 10 ? TutorialHandler.animationTimer * 3.2F : 32) - 32;
            String s = TutorialHandler.completedTutorial.name();

            this.drawTexturedModalRect(width - 160, i, 96, 202, 160, 32);
            MC.fontRenderer.drawString("Tutorial Completed!", width - 130, i + 7, 0xffff00);
            MC.fontRenderer.drawString(s.substring(0, 1) + s.substring(1, s.length()).toLowerCase(), width - 130, i + 18, 0xffffff);

            ItemStack stack = ItemStack.EMPTY;

            for (int j = 0; j < TransformersAPI.getTransformers().size(); ++j)
            {
                Transformer transformer = TransformersAPI.getTransformers().get(j);

                int altMode = TFData.ALT_MODE.get(player);

                if (transformer.getTutorialType(altMode) == TutorialHandler.completedTutorial && stack.isEmpty())
                {
                    stack = new ItemStack(TFItems.displayVehicle, 1, j);
                }
            }

            if (!stack.isEmpty())
            {
                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.enableRescaleNormal();
                GlStateManager.enableColorMaterial();
                RENDER_ITEM.renderItemAndEffectIntoGUI(stack, width - 152, i + 8);
                GlStateManager.disableLighting();
                GlStateManager.depthMask(true);
                GlStateManager.enableDepth();
            }
        }
    }*/

    public void renderCrossbowAmmo(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
    {
    }
}
