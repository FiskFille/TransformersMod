package fiskfille.tf.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.fluid.FluidTankTF;
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

    public static boolean isFullyTransformed(EntityPlayer player)
    {
        return isPlayerTransformer(player) && getTransformationTimer(player) == 1;
    }

    public static boolean isInRobotMode(EntityPlayer player)
    {
        return isPlayerTransformer(player) && getTransformationTimer(player) == 0;
    }

    public static boolean isInStealthMode(EntityPlayer player)
    {
        Transformer transformer = TFHelper.getTransformer(player);
        int altMode = TFData.ALT_MODE.get(player);

        if (altMode == -1)
        {
            altMode = TFData.PREV_ALT_MODE.get(player);
        }

        return transformer != null && transformer.hasStealthForce(player, altMode) && altMode != -1 && getStealthModeTimer(player) > 0;
    }

    public static float getTransformationTimer(EntityPlayer player)
    {
        return median(TFData.TRANSFORM_PROGRESS.get(player), TFData.PREV_TRANSFORM_PROGRESS.get(player), TransformersMod.proxy.getRenderTick());
    }

    public static float getStealthModeTimer(EntityPlayer player)
    {
        return median(TFData.STEALTH_FORCE_PROGRESS.get(player), TFData.PREV_STEALTH_FORCE_PROGRESS.get(player), TransformersMod.proxy.getRenderTick());
    }

    public static void applyFluidUsage(FluidTankTF tank)
    {
        FluidStack fluidStack = tank.getFluid();
        int usage = tank.getUsage();

        if (fluidStack != null)
        {
            fluidStack.amount += usage;

            if (fluidStack.amount < 0)
            {
                fluidStack.amount = 0;
            }
            else if (fluidStack.amount > tank.getCapacity())
            {
                fluidStack.amount = tank.getCapacity();
            }
        }
    }

    public static float median(float curr, float prev, float partialTicks)
    {
        return prev + (curr - prev) * partialTicks;
    }

    public static double median(double curr, double prev, float partialTicks)
    {
        return prev + (curr - prev) * partialTicks;
    }

    public static float getWidth(EntityPlayer player)
    {
        return 0.6F;
    }

    public static float getHeight(EntityPlayer player)
    {
        return 1.8F + getCameraYOffset(player);
    }

    public static float getScale(EntityPlayer player)
    {
        return 1;
    }

    public static float getCameraYOffset(EntityPlayer player)
    {
        Transformer transformer = TFHelper.getTransformer(player);

        if (transformer != null)
        {
            int altMode = TFData.ALT_MODE.get(player);

            return TFHelper.median(transformer.getVehicleHeightOffset(player, altMode), transformer.getHeightOffset(player, altMode), TFHelper.getTransformationTimer(player));
        }

        return 0;
    }

    public static boolean shouldOverrideScale(EntityPlayer player)
    {
        if (getTransformer(player) == null && TFData.PREV_TRANSFORMER.get(player) != null)
        {
            return true;
        }

        return !player.isEntityAlive() || (getTransformer(player) != null || TFData.PREV_TRANSFORMER.get(player) != null) && (getHeight(player) != player.height || getWidth(player) != player.width);
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

        int aA = a >> 24 & 0xff;
        int aR = (a & 0xff0000) >> 16;
        int aG = (a & 0xff00) >> 8;
        int aB = a & 0xff;

        int bA = b >> 24 & 0xff;
        int bR = (b & 0xff0000) >> 16;
        int bG = (b & 0xff00) >> 8;
        int bB = b & 0xff;

        int A = (int) (aA * iRatio + bA * ratio);
        int R = (int) (aR * iRatio + bR * ratio);
        int G = (int) (aG * iRatio + bG * ratio);
        int B = (int) (aB * iRatio + bB * ratio);

        return A << 24 | R << 16 | G << 8 | B;
    }
}
