package fiskfille.tf.common.energon;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

import com.google.common.collect.Maps;

public abstract class Energon
{
    public static final int CRYSTAL_SHARD = 144;
    public static final int CRYSTAL_FULL = CRYSTAL_SHARD * 4;
    public static final int CRYSTAL_BLOCK = CRYSTAL_SHARD * 9;
    
    public abstract Block getCrystal();

    public abstract Item getCrystalPiece();

    public abstract int getColor();

    public abstract String getId();
    
    public float getEnergyValue()
    {
    	return 1.0F;
    }
    
    public String getTranslatedName()
    {
    	return StatCollector.translateToLocal("energon." + getId());
    }
    
    public static Map<String, Integer> createContentMap(Object... args)
    {
    	Map<String, Integer> map = Maps.newHashMap();
    	int i = 0;

        for (Object object : args)
        {
            if (object instanceof Integer)
            {
                i = (Integer) object;
            }
            else if (object instanceof Energon)
            {
                map.put(((Energon) object).getId(), i);
            }
        }

        return map;
    }
}
