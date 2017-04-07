package fiskfille.tf.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTextureHelper;

public class RenderItemArmor implements IItemRenderer
{
    private Minecraft mc = Minecraft.getMinecraft();

    private Transformer transformer;
    private int armorPiece;

    public RenderItemArmor(Transformer transformer, int armorPiece)
    {
        this.armorPiece = armorPiece;
        this.transformer = transformer;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        TransformerModel tfModel = TFModelRegistry.getModel(transformer);
        ModelTransformerBase model = tfModel.getItemInventoryModel();

        switch (armorPiece)
        {
        case 0:
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            break;
        case 1:
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            break;
        case 2:
            GL11.glScalef(1, 1, 1);
            break;
        case 3:
            GL11.glScalef(1, 1, 1);
            break;
        }

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (type == ItemRenderType.INVENTORY)
        {
            RenderHelper.enableGUIStandardItemLighting();
        }

        if (TFArmorDyeHelper.isDyed(item))
        {
            float[] primaryColor = TFRenderHelper.hexToRGB(TFArmorDyeHelper.getPrimaryColor(item));
            float[] secondaryColor = TFRenderHelper.hexToRGB(TFArmorDyeHelper.getSecondaryColor(item));

            GL11.glColor4f(primaryColor[0], primaryColor[1], primaryColor[2], 1);
            mc.getTextureManager().bindTexture(tfModel.getTexture(null, "_primary"));
            renderArmor(type, model);

            GL11.glColor4f(secondaryColor[0], secondaryColor[1], secondaryColor[2], 1);
            mc.getTextureManager().bindTexture(tfModel.getTexture(null, "_secondary"));
            renderArmor(type, model);

            GL11.glColor4f(1, 1, 1, 1);
            mc.getTextureManager().bindTexture(tfModel.getTexture(null, "_base"));
            renderArmor(type, model);
        }
        else
        {
            mc.getTextureManager().bindTexture(tfModel.getTexture(null, ""));
        }

        renderArmor(type, model);

        if (tfModel.hasLightsLayer())
        {
            TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
            mc.getTextureManager().bindTexture(tfModel.getTexture(null, "_lights"));
            renderArmor(type, model);
            TFRenderHelper.resetLighting();
        }

        if (item.hasEffect(0))
        {
            mc.getTextureManager().bindTexture(TFTextureHelper.RES_ITEM_GLINT);
            GL11.glColor4f(0.5F, 0.5F, 0.5F, 1);
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDepthMask(false);

            for (int i = 0; i < 2; ++i)
            {
                float f1 = (Minecraft.getSystemTime() % (3000F + i * 1873) / (3000 + i * 1873) * 256) / 64;
                float f2 = 0.33333334F;
                
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glColor4f(0.5F, 0.25F, 0.8F, 1);
                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                GL11.glScalef(f2, f2, f2);
                GL11.glRotatef(30 - i * 60, 0, 0, 1);
                GL11.glTranslatef(0, f1, 0);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                renderArmor(type, model);
            }

            GL11.glColor4f(1, 1, 1, 1);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glDepthMask(true);
            GL11.glLoadIdentity();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
        }
        
        GL11.glDisable(GL11.GL_BLEND);
    }

    private void renderGlint(int p_77018_1_, int x, int y, int width, int height)
    {
        float zLevel = 0;

        for (int j1 = 0; j1 < 2; ++j1)
        {
            OpenGlHelper.glBlendFunc(772, 1, 0, 0);
            float f = 0.00390625F;
            float f1 = 0.00390625F;
            float f2 = Minecraft.getSystemTime() % (3000 + j1 * 1873) / (3000 + j1 * 1873) * 256;
            float f3 = 0;
            Tessellator tessellator = Tessellator.instance;
            float f4 = 4;

            if (j1 == 1)
            {
                f4 = -1;
            }

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(x, y + height, zLevel, (f2 + height * f4) * f, (f3 + height) * f1);
            tessellator.addVertexWithUV(x + width, y + height, zLevel, (f2 + width + height * f4) * f, (f3 + height) * f1);
            tessellator.addVertexWithUV(x + width, y, zLevel, (f2 + width) * f, f3 * f1);
            tessellator.addVertexWithUV(x, y, zLevel, f2 * f, f3 * f1);
            tessellator.draw();
        }
    }

    private void renderArmor(ItemRenderType type, ModelTransformerBase model)
    {
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glRotatef(210, 0, 1, 0);
            GL11.glRotatef(10, 0, 0, 1);
            GL11.glTranslatef(-0.9F, -1, 0.2F);

            if (armorPiece == 0)
            {
                GL11.glTranslatef(0.5F, 0.5F, 0.1F);
            }
            else if (armorPiece == 1)
            {
                GL11.glTranslatef(-0.2F, -0.4F, 0);
            }
            else if (armorPiece == 2)
            {
                GL11.glTranslatef(0, -0.2F, 0);
            }

            float scale = 1;
            GL11.glScalef(scale, scale, scale);
            model.renderArmorPiece(armorPiece);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glRotatef(-45, 0, 1, 0);
            GL11.glRotatef(-45, 0, 0, 1);
            GL11.glTranslatef(0.5F, -0.5F, 0);

            if (armorPiece == 0)
            {
                GL11.glTranslatef(-0.25F, 0.6F, -0.2F);
            }
            else if (armorPiece == 1)
            {
                GL11.glTranslatef(-0.1F, 0.1F, -0.4F);
            }
            else if (armorPiece == 2)
            {
                GL11.glTranslatef(0.1F, 0.3F, -0.35F);
                GL11.glRotatef(35, 0, 0, 1);
            }
            else if (armorPiece == 3)
            {
                GL11.glTranslatef(-0.05F, 0.3F, -0.3F);
                GL11.glRotatef(35, 0, 0, 1);
            }

            float scale = 0.7F;
            GL11.glScalef(scale, scale, scale);
            model.renderArmorPiece(armorPiece);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.INVENTORY)
        {
            GL11.glPushMatrix();
            GL11.glScalef(10, 10, 10);
            GL11.glTranslatef(0.5F, 0.5F, 1);
            GL11.glScalef(1, 1, -1);
            GL11.glRotatef(210, 1, 0, 0);
            GL11.glRotatef(45, 0, 1, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glRotatef(45, 0, 1, 0);
            GL11.glRotatef(150, 1, 0, 0);

            if (armorPiece == 0)
            {
                float scale = 2;
                GL11.glTranslatef(0.03125F, 0, 0);
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslatef(0, 0.125F, 0);
            }
            else if (armorPiece == 1)
            {
                float scale = 2;
                GL11.glTranslatef(0.5F, -0.1F, 0);
                GL11.glScalef(scale, scale, scale);
            }
            else if (armorPiece == 2)
            {
                float scale = 2;
                GL11.glTranslatef(0.325F, -0.6F, 0);
                GL11.glScalef(scale, scale, scale);
            }
            else if (armorPiece == 3)
            {
                float scale = 2;
                GL11.glTranslatef(0.3125F, 0, 0);
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslatef(0, -0.125F, 0);
            }

            model.renderArmorPiece(armorPiece);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.ENTITY)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glTranslatef(0, -1, 0.1F);

            if (armorPiece == 0)
            {
                GL11.glTranslatef(0, 1.125F, 0);
            }
            else if (armorPiece == 1)
            {
                GL11.glTranslatef(0, 0.6F, -0.2F);
            }
            else if (armorPiece == 2)
            {
                GL11.glTranslatef(0, 0.55F, -0.1F);
            }
            else if (armorPiece == 3)
            {
                GL11.glTranslatef(0, 0.7F, -0.1F);
            }

            float scale = 1;
            GL11.glScalef(scale, scale, scale);
            model.renderArmorPiece(armorPiece);
            GL11.glPopMatrix();
        }
    }
}
