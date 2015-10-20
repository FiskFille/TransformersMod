package fiskfille.tf.helper;

import java.awt.Color;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;

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
        float r = (float)((hex & 0xFF0000) >> 16) / 255F;
        float g = (float)((hex & 0xFF00) >> 8) / 255F;
        float b = (float)(hex & 0xFF) / 255F;
        return new float[] {r, g, b};
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

        int A = (int)((aA * iRatio) + (bA * ratio));
        int R = (int)((aR * iRatio) + (bR * ratio));
        int G = (int)((aG * iRatio) + (bG * ratio));
        int B = (int)((aB * iRatio) + (bB * ratio));
        
        return A << 24 | R << 16 | G << 8 | B;
    }
    
    public static Color blend(Color c0, Color c1)
    {
        double totalAlpha = c0.getAlpha() + c1.getAlpha();
        double weight0 = c0.getAlpha() / totalAlpha;
        double weight1 = c1.getAlpha() / totalAlpha;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
        double a = Math.max(c0.getAlpha(), c1.getAlpha());

        return new Color((int)r, (int)g, (int)b, (int)a);
    }
    
    public static int blend(int color1, int color2)
    {
        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        int k;
        float f;
        float f1;
        int blendColor;
        
        
        
        
        if (color1 != 0xffffff)
        {
            f = (float)(color1 >> 16 & 255) / 255.0F;
            f1 = (float)(color1 >> 8 & 255) / 255.0F;
            float f2 = (float)(color1 & 255) / 255.0F;
            i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
            aint[0] = (int)((float)aint[0] + f * 255.0F);
            aint[1] = (int)((float)aint[1] + f1 * 255.0F);
            aint[2] = (int)((float)aint[2] + f2 * 255.0F);
            ++j;
        }
        
        
        Color c = new Color(color2);
        float[] afloat = {c.getRed(), c.getGreen(), c.getBlue()};
        int j1 = (int)(afloat[0] * 255.0F);
        int k1 = (int)(afloat[1] * 255.0F);
        blendColor = (int)(afloat[2] * 255.0F);
        i += Math.max(j1, Math.max(k1, blendColor));
        aint[0] += j1;
        aint[1] += k1;
        aint[2] += blendColor;
        ++j;
        
        
        
        k = aint[0] / j;
        int i1 = aint[1] / j;
        color1 = aint[2] / j;
        f = (float)i / (float)j;
        f1 = (float)Math.max(k, Math.max(i1, color1));
        k = (int)((float)k * f / f1);
        i1 = (int)((float)i1 * f / f1);
        color1 = (int)((float)color1 * f / f1);
        blendColor = (k << 8) + i1;
        blendColor = (blendColor << 8) + color1;
        return blendColor;
    }
}
