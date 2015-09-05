package fiskfille.tf.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.common.transformer.base.Transformer;

public class RenderItemDisplayVehicle implements IItemRenderer
{
    public TransformerModel getModelFromMetadata(int metadata)
    {
        Transformer transformer = TransformersAPI.getTransformers().get(metadata);

        if (transformer != null)
        {
            return TFModelRegistry.getModel(transformer);
        }

        return null;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return type != ItemRenderType.FIRST_PERSON_MAP;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        TransformerModel model = getModelFromMetadata(item.getItemDamage());
        Minecraft.getMinecraft().renderEngine.bindTexture(model.getTexture(null));
        ModelVehicleBase vehicleModel = model.getVehicleModel();

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(210, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(10, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.7F, -2.1F, 0.2F);

            float scale = 1.0F;
            GL11.glScalef(scale, scale, scale);
            vehicleModel.render();
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-45, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-45, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.3F, -0.9F, -0.2F);

            float scale = 0.7F;
            GL11.glScalef(scale, scale, scale);
            vehicleModel.render();
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.INVENTORY)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);

            float scale = 1.0F;
            GL11.glScalef(scale, scale, scale);
            vehicleModel.render();
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.ENTITY)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -0.5F, 0.0F);

            float scale = 0.5F;
            GL11.glScalef(scale, scale, scale);
            vehicleModel.render();
            GL11.glPopMatrix();
        }
    }
}