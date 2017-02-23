package fiskfille.tf.client.render.item;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.item.ModelPowerCanister;
import fiskfille.tf.common.energon.power.IEnergyContainerItem;
import fiskfille.tf.common.item.ItemPowerCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tick.ClientTickHandler;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderItemPowerCanister implements IItemRenderer
{
    private static ModelPowerCanister modelCanister = new ModelPowerCanister();

    private static Minecraft mc = Minecraft.getMinecraft();
    private static RenderItem renderItem = new RenderItem();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return type != ItemRenderType.INVENTORY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemstack, Object... data)
    {
        if (type == ItemRenderType.INVENTORY)
        {
            renderItem.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, 0, 0, true);

            if (itemstack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) itemstack.getItem();
                float energy = container.getEnergyStored(itemstack);
                float max = container.getEnergyCapacity(itemstack);
                float filled = energy / max;
                
                if (energy > 0)
                {
                    boolean flag = renderItem.renderWithColor;   
                    TFRenderHelper.setLighting(61680);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glColor4f(1, 1, 1, filled);
                    renderItem.renderWithColor = false;
                    renderItem.renderIcon(0, 0, TFItems.powerCanister.getIconFromDamageForRenderPass(0, 1), 16, 16);
                    renderItem.renderWithColor = flag;
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    TFRenderHelper.resetLighting();
                }
                
                int shade = Math.round(255);
                
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glShadeModel(GL11.GL_SMOOTH);
                Tessellator tessellator = Tessellator.instance;
                renderQuad(tessellator, 2, 13, 13, 2, 0);
                renderQuad(tessellator, 2, 13, 12, 1, 0x003F3F);
                renderQuad(tessellator, 2, 13, Math.round(filled * 12), 1, 0, shade << 8 | shade);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glColor4f(1, 1, 1, 1);
            }
        }
        else
        {
            GL11.glPushMatrix();
            float scale = 2;

            if (type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.EQUIPPED)
            {
                GL11.glTranslatef(0.5F, 0, 0.5F);
            }
            else if (type == ItemRenderType.ENTITY)
            {
                scale *= 0.5F;
                GL11.glTranslatef(0, -0.275F, 0);
            }

            GL11.glScalef(scale, -scale, -scale);
            renderCanister(itemstack);
            GL11.glPopMatrix();
        }
    }

    public static void renderCanister(ItemStack itemstack)
    {
        ItemPowerCanister container = (ItemPowerCanister) itemstack.getItem();
        float energy = container.getEnergyStored(itemstack);
        float max = container.getEnergyCapacity(itemstack);

        if (energy > 0)
        {
            Tessellator tessellator = Tessellator.instance;

            GL11.glPushMatrix();
            GL11.glTranslatef(0, -0.0625F * 8.5F, 0);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            TFRenderHelper.setLighting(61680);

            float[] primary = TFRenderHelper.hexToRGB(0x57ABAF);
            float[] secondary = TFRenderHelper.hexToRGB(0x7BF2F8);
            double length = 0.0625F * 7;
            double width = 1F / (128 + 256 * (1 - energy / max));
            int segments = 16;

            Random rand = new Random(8964355487L + mc.thePlayer.ticksExisted * 100000);
            Random randPrev = new Random(8964355487L + (mc.thePlayer.ticksExisted - 1) * 100000);

            for (int i = 0; i < 1; ++i)
            {
                Vec3 src = Vec3.createVectorHelper(0, 0, 0);

                for (int j = 0; j < segments; ++j)
                {
                    float f = (float) j / segments;
                    Vec3 dst = Vec3.createVectorHelper(0, (j + 1) * length / segments, 0);

                    if (j < segments - 1)
                    {
                        float angle = (float) Math.toRadians(90 * energy / max) * (1 - f);
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
                    float f1 = (float) Math.cos(j / (segments * 0.15625F));
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
            }

            TFRenderHelper.resetLighting();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }

        GL11.glDisable(GL11.GL_CULL_FACE);
        mc.getTextureManager().bindTexture(new ResourceLocation(TransformersMod.modid, String.format("textures/models/tiles/power_canister_%s.png", container.tiers[Math.min(itemstack.getItemDamage(), container.tiers.length - 1)])));
        modelCanister.render();
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    private void renderQuad(Tessellator tessellator, int x, int y, int width, int height, int color1, int color2)
    {
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_I(color1);
        tessellator.addVertex(x, y, 0.0D);
        tessellator.addVertex(x, y + height, 0.0D);
        tessellator.setColorOpaque_I(color2);
        tessellator.addVertex(x + width, y + height, 0.0D);
        tessellator.addVertex(x + width, y, 0.0D);
        tessellator.draw();
    }
    
    private void renderQuad(Tessellator tessellator, int x, int y, int width, int height, int color)
    {
        renderQuad(tessellator, x, y, width, height, color, color);
    }
}
