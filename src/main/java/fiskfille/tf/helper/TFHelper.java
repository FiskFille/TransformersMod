package fiskfille.tf.helper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.base.Transformer;

/**
 * @author FiskFille, gegy1000
 */
public class TFHelper
{
    public static boolean isTransformer(EntityLivingBase entity)
    {
        return isTransformer(new ItemStack[] {entity.getEquipmentInSlot(4), entity.getEquipmentInSlot(3), entity.getEquipmentInSlot(2), entity.getEquipmentInSlot(1)});
    }

    public static boolean isTransformer(ItemStack... itemstacks)
    {
        ItemStack itemstack = itemstacks[0];
        
        for (int i = 1; i < itemstacks.length; ++i)
        {
            if (getTransformerFromArmor(itemstack) == null || getTransformerFromArmor(itemstack) != getTransformerFromArmor(itemstacks[i]))
            {
                return false;
            }
            
            itemstack = itemstacks[i];
        }
        
        return true;
        
        
//        Transformer helmet = getTransformerFromArmor(itemstacks[0]);
//        Transformer chest = getTransformerFromArmor(itemstacks[1]);
//        Transformer legs = getTransformerFromArmor(itemstacks[2]);
//        Transformer feet = getTransformerFromArmor(itemstacks[3]);
//
//        if ((helmet == null || helmet != chest) && chest != null && chest.getHelmet() == null)
//        {
//            return itemstacks[0] == null && chest == legs && legs == feet;
//        }
//
//        return helmet != null && helmet == chest && chest == legs && legs == feet;
    }

    public static Transformer getTransformer(EntityLivingBase entity)
    {
        if (entity != null && isTransformer(entity))
        {
            return getTransformerFromArmor(entity, 0);
        }

        return null;
    }

    public static Transformer getTransformer(ItemStack... itemstacks)
    {
        if (isTransformer(itemstacks))
        {
            return getTransformerFromArmor(itemstacks[0]);
        }

        return null;
    }

    public static Transformer getTransformerFromArmor(EntityLivingBase entity, int slot)
    {
        return getTransformerFromArmor(entity.getEquipmentInSlot(slot + 1));
    }

    public static Transformer getTransformerFromArmor(ItemStack itemstack)
    {
        if (itemstack != null)
        {
            Item item = itemstack.getItem();

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
