package fiskfille.tf.common.fluid;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.google.common.collect.Maps;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFTextureHelper;

public class FluidEnergon extends Fluid
{
    public FluidEnergon(String fluidName)
    {
        super(fluidName);
    }

    @Override
    public IIcon getStillIcon()
    {
        return TFTextureHelper.energonStillIcon;
    }

    @Override
    public IIcon getFlowingIcon()
    {
        return TFTextureHelper.energonFlowingIcon;
    }

    @Override
    public int getColor(FluidStack stack)
    {
        return getLiquidColor(stack);
    }

    public static void refreshNBT(FluidStack stack)
    {
        if (stack.tag == null)
        {
            stack.tag = new NBTTagCompound();
        }
    }

    public static FluidStack create(ItemStack crystal)
    {
        IEnergon energon = (IEnergon) (crystal.getItem() instanceof ItemBlock ? Block.getBlockFromItem(crystal.getItem()) : crystal.getItem());

        return create(energon.getEnergonType(), energon.getMass());
    }

    public static FluidStack create(Energon energon, int amount)
    {
        FluidStack stack = new FluidStack(TFFluids.energon, amount);
        setRatio(stack, energon.getId(), 1);

        return stack;
    }

    public static void merge(FluidStack stack1, FluidStack stack2, int amount)
    {
        Map<String, Float> ratios1 = getRatios(stack1);
        Map<String, Float> ratios2 = getRatios(stack2);

        if (stack1.amount == 0)
        {
            ratios1 = ratios2;
        }
        else
        {
            for (Map.Entry<String, Float> e : ratios1.entrySet())
            {
                float f = (float) amount / stack1.amount;
                e.setValue(e.getValue() * (1 - f) + ratios2.get(e.getKey()) * f);
            }
        }

        setRatios(stack1, ratios1);
    }

    public static void setRatios(FluidStack stack, Map<String, Float> ratios)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        for (Energon energon : TransformersAPI.getEnergonTypes())
        {
            if (!ratios.containsKey(energon.getId()))
            {
                ratios.put(energon.getId(), 0.0F);
            }
        }

        for (Map.Entry<String, Float> e : ratios.entrySet())
        {
            nbttagcompound.setFloat(e.getKey(), e.getValue());
        }

        refreshNBT(stack);
        stack.tag.setTag("Ratio", nbttagcompound);

        calculateLiquidColor(stack);
    }

    public static Map<String, Float> getRatios(FluidStack stack)
    {
        refreshNBT(stack);
        Map<String, Float> map = Maps.newHashMap();

        NBTTagCompound nbttagcompound = stack.tag.getCompoundTag("Ratio");

        for (Energon energon : TransformersAPI.getEnergonTypes())
        {
            map.put(energon.getId(), nbttagcompound.getFloat(energon.getId()));
        }

        return map;
    }

    public static void setRatio(FluidStack stack, String name, float ratio)
    {
        refreshNBT(stack);
        NBTTagCompound nbttagcompound = stack.tag.getCompoundTag("Ratio");
        nbttagcompound.setFloat(name, ratio);
        stack.tag.setTag("Ratio", nbttagcompound);

        calculateLiquidColor(stack);
    }

    public static float getRatio(FluidStack stack, String name)
    {
        refreshNBT(stack);
        Maps.newHashMap();
        NBTTagCompound nbttagcompound = stack.tag.getCompoundTag("Ratio");

        if (nbttagcompound.hasKey(name))
        {
            return nbttagcompound.getFloat(name);
        }

        return 0;
    }

    public static int calculateLiquidColor(FluidStack stack)
    {
        Map<String, Float> ratios = getRatios(stack);
        int liquidColor = -1;

        for (Map.Entry<String, Float> e : ratios.entrySet())
        {
            Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());

            if (energon != null)
            {
                if (liquidColor == -1)
                {
                    liquidColor = energon.getColor();
                }
                else
                {
                    liquidColor = TFHelper.blend(liquidColor, energon.getColor(), e.getValue());
                }
            }
        }

        setLiquidColor(stack, liquidColor);

        return liquidColor;
    }

    public static void setLiquidColor(FluidStack stack, int color)
    {
        refreshNBT(stack);
        stack.tag.setInteger("Color", color);
    }

    public static int getLiquidColor(FluidStack stack)
    {
        return stack.tag != null && stack.tag.hasKey("Color") ? stack.tag.getInteger("Color") : -1;
    }
}
