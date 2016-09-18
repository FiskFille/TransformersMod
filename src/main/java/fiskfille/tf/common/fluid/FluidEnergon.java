package fiskfille.tf.common.fluid;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import scala.actors.threadpool.Arrays;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.helper.TFRenderHelper;

public class FluidEnergon extends Fluid
{
	public FluidEnergon(String fluidName)
	{
		super(fluidName);
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
	
	public static void setContents(FluidStack stack, Map<String, Integer> contents)
    {
//		for (Map.Entry<String, Integer> e : contents.entrySet())
//		{
//			e.setValue(Math.round(stack.amount * TFHelper.getPercentOf(e.getKey(), contents)));
//		}
        
        refreshNBT(stack);
        stack.tag.setString("Contents", contents.toString());
        calculateLiquidColor(stack, contents);
    }
	
	public static void addContents(FluidStack stack, Map<String, Integer> contents)
    {
		refreshNBT(stack);
		
		if (stack.tag.hasKey("Contents"))
		{
			Map<String, Integer> map = readMapFromString(stack.tag.getString("Contents"));
			
			for (Map.Entry<String, Integer> e : contents.entrySet())
			{
				map.put(e.getKey(), (map.containsKey(e.getKey()) ? map.get(e.getKey()) : 0) + e.getValue());
			}
			
			List<Entry<String, Integer>> list = Arrays.asList(map.entrySet().toArray());
			List<String> list1 = Lists.newArrayList();
			
			for (Entry<String, Integer> e : list)
			{
				list1.add(e.getKey() + ":" + e.getValue());
			}
			
			Collections.sort(list1);
			map.clear();
			
			for (String s : list1)
			{
				String[] astring = s.split(":");
				map.put(astring[0], Integer.valueOf(astring[1]));
			}
			
			setContents(stack, map);
		}
		else
		{
			setContents(stack, contents);
		}
    }
	
	public static int calculateLiquidColor(FluidStack stack, Map<String, Integer> contents)
	{
		int liquidAmount = 0;
		
		for (Map.Entry<String, Integer> e : contents.entrySet())
        {
			liquidAmount += e.getValue();
        }
		
		float percentMultiplier = 1.0F / liquidAmount;
		int liquidColor = -1;
		
        for (Map.Entry<String, Integer> e : contents.entrySet())
        {
            Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
            float percent = e.getValue() * percentMultiplier;

            if (energon != null)
            {
            	if (liquidColor == -1)
        		{
        			liquidColor = energon.getColor();
        		}
            	else
            	{
            		liquidColor = TFRenderHelper.blend(liquidColor, energon.getColor(), percent);
            	}
            }
        }
        
        setLiquidColor(stack, liquidColor);
		
		return liquidColor;
	}

    public static Map<String, Integer> getContents(FluidStack stack)
    {
    	if (stack.tag == null)
    	{
    		return Maps.newHashMap();
    	}
    	
        Map map = readMapFromString(stack.tag.getString("Contents"));
        return (Map<String, Integer>) (map == null ? Maps.newHashMap() : map);
    }

    public static Map readMapFromString(String mapString)
    {
        mapString = mapString.replace("{", "").replace("}", "");
        String[] entries = mapString.split(", ");
        Map map = Maps.newHashMap();

        for (String entry : entries)
        {
            String[] keyValue = entry.split("=");

            if (keyValue.length == 2)
            {
                String key = keyValue[0];
                String value = keyValue[1];
                map.put(key, Integer.valueOf(value));
            }
        }

        return map;
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
