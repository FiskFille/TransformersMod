package fiskfille.tf.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFRenderHelper
{
    public static float[] hexToRGB(int hex)
    {
        float r = (float) ((hex & 0xFF0000) >> 16) / 255F;
        float g = (float) ((hex & 0xFF00) >> 8) / 255F;
        float b = (float) (hex & 0xFF) / 255F;
        return new float[]{r, g, b};
    }

    public static int blend(int a, int b, float ratio)
    {
        if (ratio > 1.0F)
        {
            ratio = 1.0F;
        }
        else if (ratio < 0.0F)
        {
            ratio = 0.0F;
        }

        float iRatio = 1.0F - ratio;

        int aA = (a >> 24 & 0xff);
        int aR = ((a & 0xff0000) >> 16);
        int aG = ((a & 0xff00) >> 8);
        int aB = (a & 0xff);

        int bA = (b >> 24 & 0xff);
        int bR = ((b & 0xff0000) >> 16);
        int bG = ((b & 0xff00) >> 8);
        int bB = (b & 0xff);

        int A = (int) ((aA * iRatio) + (bA * ratio));
        int R = (int) ((aR * iRatio) + (bR * ratio));
        int G = (int) ((aG * iRatio) + (bG * ratio));
        int B = (int) ((aB * iRatio) + (bB * ratio));

        return A << 24 | R << 16 | G << 8 | B;
    }

    public static void setLighting(int c0)
    {
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);
    }

    public static void setupRenderLayers(ItemStack itemstack, ModelRenderer model, boolean hasLightsLayer)
    {
        Minecraft mc = Minecraft.getMinecraft();

        if (itemstack != null && itemstack.getItem() instanceof ItemTransformerArmor)
        {
            Transformer transformer = ((ItemTransformerArmor) itemstack.getItem()).getTransformer();
            TransformerModel tfModel = TFModelRegistry.getModel(transformer);

            if (TFArmorDyeHelper.isDyed(itemstack))
            {
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
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
                model.render(0.0625F);

                if (hasLightsLayer)
                {
                    setLighting(61680);
                    mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
                    model.render(0.0625F);
                }
            }
            else
            {
                model.render(0.0625F);

                if (hasLightsLayer)
                {
                    setLighting(61680);
                    mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
                    model.render(0.0625F);
                }
            }
        }
    }
    
    public static float median(float curr, float prev, float partialTicks)
	{
        return prev + (curr - prev) * partialTicks;
	}
}
