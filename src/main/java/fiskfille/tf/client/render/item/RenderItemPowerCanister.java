package fiskfille.tf.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.item.ModelPowerCanister;
import fiskfille.tf.common.energon.power.IEnergyContainerItem;
import fiskfille.tf.common.item.ItemPowerCanister;
import fiskfille.tf.common.item.TFItems;
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
                    TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
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
                renderQuad(tessellator, 2, 13, 12, 1, 0x003838);
                renderQuad(tessellator, 2, 13, filled * 12, 1, 0x005151, shade << 8 | shade);
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
            Vec3 vec3 = Vec3.createVectorHelper(0, 0.0625F * 8.5F, 0);
            TFRenderHelper.renderEnergyStatic(vec3, vec3.addVector(0, -0.0625F * 7, 0), 1F / (128 + 256 * (1 - energy / max)), energy / max, 16, itemstack.hashCode());
        }

        GL11.glDisable(GL11.GL_CULL_FACE);
        mc.getTextureManager().bindTexture(new ResourceLocation(TransformersMod.modid, String.format("textures/models/tiles/power_canister_%s.png", container.tiers[Math.min(itemstack.getItemDamage(), container.tiers.length - 1)])));
        modelCanister.render();
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    private void renderQuad(Tessellator tessellator, float x, float y, float width, float height, int color1, int color2)
    {
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_I(color1);
        tessellator.addVertex(x, y, 0);
        tessellator.addVertex(x, y + height, 0);
        tessellator.setColorOpaque_I(color2);
        tessellator.addVertex(x + width, y + height, 0);
        tessellator.addVertex(x + width, y, 0);
        tessellator.draw();
    }
    
    private void renderQuad(Tessellator tessellator, float x, float y, float width, float height, int color)
    {
        renderQuad(tessellator, x, y, width, height, color, color);
    }
}
