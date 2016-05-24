package fiskfille.tf.common.groundbridge;

import net.minecraft.util.StatCollector;

public enum EnumError
{
    INVALID_COORDS("invalid_coords"),
    NOT_ENOUGH_SPACE("not_enough_space"),
    NO_PORTAL_LINKED("no_portal_linked"),
    PORTAL_OBSTRUCTED("portal_obstructed"),
    OUT_OF_BOUNDS("out_of_bounds");

    public String displayKey;

    private EnumError(String displayKey)
    {
        this.displayKey = displayKey;
    }
    
    public String translate(Object... args)
    {
    	if (this == OUT_OF_BOUNDS)
    	{
    		return StatCollector.translateToLocalFormatted("ground_bridge.error." + displayKey, args[0]);
    	}
    	
    	return StatCollector.translateToLocal("ground_bridge.error." + displayKey);
    }
}
