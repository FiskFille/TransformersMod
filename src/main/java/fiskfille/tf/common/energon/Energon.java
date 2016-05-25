package fiskfille.tf.common.energon;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

import com.google.common.collect.Maps;

public abstract class Energon
{
    public abstract Block getCrystal();

    public abstract Item getCrystalPiece();

    public abstract int getColor();

    public abstract String getId();
    
    public String getTranslatedName()
    {
    	return StatCollector.translateToLocal("energon." + getId());
    }
    
    public static Map<String, Float> createContentMap(Object... args)
    {
    	Map<String, Float> map = Maps.newHashMap();
    	float f = 0;
    	
    	for (int i = 0; i < args.length; ++i)
    	{
    		Object object = args[i];
    		
    		if (object instanceof Float)
    		{
    			f = (Float)object;
    		}
    		else if (object instanceof Double)
    		{
    			double d = (Double)object;
    			f = (float)d;
    		}
    		else if (object instanceof Integer)
    		{
    			f = (Integer)object;
    		}
    		else if (object instanceof Energon)
    		{
    			map.put(((Energon)object).getId(), f);
    		}
    	}
    	
    	return map;
    }
}
