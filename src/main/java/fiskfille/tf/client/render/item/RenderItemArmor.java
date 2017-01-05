package fiskfille.tf.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderItemArmor implements IItemRenderer
{
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
        return type != ItemRenderType.FIRST_PERSON_MAP;
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

        Minecraft.getMinecraft().getTextureManager().bindTexture(tfModel.getTexture(null));
        renderArmor(type, model);

        if (TFArmorDyeHelper.isDyed(item))
        {
            float[] primaryColor = TFRenderHelper.hexToRGB(TFArmorDyeHelper.getPrimaryColor(item));
            float[] secondaryColor = TFRenderHelper.hexToRGB(TFArmorDyeHelper.getSecondaryColor(item));

            GL11.glColor4f(primaryColor[0], primaryColor[1], primaryColor[2], 1);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_primary.png"));
            renderArmor(type, model);

            GL11.glColor4f(secondaryColor[0], secondaryColor[1], secondaryColor[2], 1);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_secondary.png"));
            renderArmor(type, model);
        }

        GL11.glDisable(GL11.GL_BLEND);
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
