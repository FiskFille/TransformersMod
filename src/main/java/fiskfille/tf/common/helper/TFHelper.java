package fiskfille.tf.common.helper;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.item.ItemTransformerArmor;
import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author FiskFille, gegy1000
 */
public class TFHelper
{
    public static boolean isTransformer(EntityLivingBase entity)
    {
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        return isTransformer(head, chest, legs, feet);
    }

    public static boolean isTransformer(ItemStack... stacks)
    {
        ItemStack current = stacks[0];

        for (int i = 1; i < stacks.length; ++i)
        {
            ItemStack previous = stacks[i];

            if (getTransformerFromArmor(current) == null || getTransformerFromArmor(current) != getTransformerFromArmor(previous))
            {
                return false;
            }

            current = previous;
        }

        return true;
    }

    public static Transformer getTransformer(EntityLivingBase entity)
    {
        if (entity != null && isTransformer(entity))
        {
            return getTransformerFromArmor(entity, EntityEquipmentSlot.CHEST);
        }

        return null;
    }

    public static Transformer getTransformer(ItemStack... stacks)
    {
        if (isTransformer(stacks))
        {
            return getTransformerFromArmor(stacks[0]);
        }

        return null;
    }

    public static Transformer getTransformerFromArmor(EntityLivingBase entity, EntityEquipmentSlot slot)
    {
        return getTransformerFromArmor(entity.getItemStackFromSlot(slot));
    }

    public static Transformer getTransformerFromArmor(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            Item item = stack.getItem();

            if (item instanceof ItemTransformerArmor)
            {
                return ((ItemTransformerArmor) item).getTransformer();
            }
        }

        return null;
    }

    public static boolean isFullyTransformed(EntityPlayer player)
    {
        return isTransformer(player) && getTransformationTimer(player) == 1;
    }

    public static boolean isInRobotMode(EntityPlayer player)
    {
        return isTransformer(player) && getTransformationTimer(player) == 0;
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
        return median(TFData.TRANSFORM_PROGRESS.get(player), TFData.PREV_TRANSFORM_PROGRESS.get(player), TransformersMod.PROXY.getRenderTick());
    }

    public static float getStealthModeTimer(EntityPlayer player)
    {
        return median(TFData.STEALTH_FORCE_PROGRESS.get(player), TFData.PREV_STEALTH_FORCE_PROGRESS.get(player), TransformersMod.PROXY.getRenderTick());
    }

    /*public static void applyFluidUsage(FluidTankTF tank)
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
    }*/

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
        Transformer transformer = getTransformer(player);
        return transformer == null && TFData.PREV_TRANSFORMER.get(player) != null || !player.isEntityAlive() || (transformer != null || TFData.PREV_TRANSFORMER.get(player) != null) && (getHeight(player) != player.height || getWidth(player) != player.width);
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
