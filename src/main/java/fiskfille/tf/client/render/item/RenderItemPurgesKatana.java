package fiskfille.tf.client.render.item;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.item.ModelPurgesKatana;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderItemPurgesKatana implements IItemRenderer
{
    private ModelPurgesKatana model = new ModelPurgesKatana();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return type != ItemRenderType.ENTITY && type != ItemRenderType.INVENTORY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/purge/purge.png"));

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.FIRST_PERSON_MAP)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(0, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(210, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.7F, 0.2F, -0.0F);

            model.render();
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(5, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-5, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(215, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.715F, 0.265F, -0.07F);

            model.render();
            GL11.glPopMatrix();
        }
    }
}