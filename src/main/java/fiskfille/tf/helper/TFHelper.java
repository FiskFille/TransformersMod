package fiskfille.tf.helper;

import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author FiskFille, gegy1000
 */
public class TFHelper
{
    /**
     * @returns whether the player is wearing the 'Cloudtrap' set.
     */
    public static boolean isPlayerCloudtrap(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerCloudtrap;
    }

    /**
     * @returns whether the player is wearing the 'Skystrike' set.
     */
    public static boolean isPlayerSkystrike(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerSkystrike;
    }

    /**
     * @returns whether the player is wearing the 'Purge' set.
     */
    public static boolean isPlayerPurge(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerPurge;
    }

    /**
     * @returns whether the player is wearing the 'Vurp' set.
     */
    public static boolean isPlayerVurp(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerVurp;
    }

    /**
     * @returns whether the player is wearing the 'Subwoofer' set.
     */
    public static boolean isPlayerSubwoofer(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerSubwoofer;
    }

    /**
     * @returns whether the player is wearing a full Transformer set.
     */
    public static boolean isPlayerTransformer(EntityPlayer player)
    {
        Transformer helmetTransformer = getTransformerFromArmor(player, 3);
        Transformer chestTransformer = getTransformerFromArmor(player, 2);
        Transformer legsTransformer = getTransformerFromArmor(player, 1);
        Transformer feetTransformer = getTransformerFromArmor(player, 0);

        return helmetTransformer != null && helmetTransformer == chestTransformer && chestTransformer == legsTransformer && legsTransformer == feetTransformer;
    }

    /**
     * @returns the Transformer that the player currently has fully equipped, null when not wearing a full set.
     */
    public static Transformer getTransformer(EntityPlayer player)
    {
        if (player != null && isPlayerTransformer(player))
        {
            return getTransformerFromArmor(player, 0);
        }

        return null;
    }

    /**
     * @returns the Transformer that the player is wearing in the specified slot.
     */
    public static Transformer getTransformerFromArmor(EntityPlayer player, int slot)
    {
        ItemStack currentArmorStack = player.getCurrentArmor(slot);

        if (currentArmorStack != null)
        {
            Item currentArmor = currentArmorStack.getItem();

            if (currentArmor instanceof ItemTransformerArmor)
            {
                return ((ItemTransformerArmor) currentArmor).getTransformer();
            }
        }

        return null;
    }

    /**
     * @returns the Transformer for the specific armor ItemStack.
     */
    public static Transformer getTransformerFromArmor(ItemStack itemstack)
    {
        if (itemstack != null)
        {
            Item currentArmor = itemstack.getItem();

            if (currentArmor instanceof ItemTransformerArmor)
            {
                return ((ItemTransformerArmor) currentArmor).getTransformer();
            }
        }

        return null;
    }

    /**
     * @param hex The color code to be converted
     * @returns an integer array containing the RGB for specified hexadecimal
     */
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
                float[] afloat = TFHelper.hexToRGB(TFArmorDyeHelper.getPrimaryColor(itemstack));

                GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_primary.png"));
                model.render(0.0625F);

                afloat = TFHelper.hexToRGB(TFArmorDyeHelper.getSecondaryColor(itemstack));
                GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_secondary.png"));
                model.render(0.0625F);

                GL11.glColor4f(1, 1, 1, 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_base.png"));
                model.render(0.0625F);

                if (hasLightsLayer)
                {
                    TFHelper.setLighting(61680);
                    mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
                    model.render(0.0625F);
                }
            }
            else
            {
                model.render(0.0625F);

                if (hasLightsLayer)
                {
                    TFHelper.setLighting(61680);
                    mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
                    model.render(0.0625F);
                }
            }
        }
    }
}
