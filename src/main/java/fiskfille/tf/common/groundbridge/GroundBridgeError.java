package fiskfille.tf.common.groundbridge;

import net.minecraft.util.StatCollector;

public enum GroundBridgeError
{
    INVALID_COORDS("invalid_coords"), NOT_ENOUGH_SPACE("not_enough_space"), NO_PORTAL_LINKED("no_portal_linked"), PORTAL_OBSTRUCTED("portal_obstructed"), OUT_OF_BOUNDS("out_of_bounds", 0);

    public String displayKey;
    public Object[] arguments;

    GroundBridgeError(String key, Object... args)
    {
        displayKey = key;
        arguments = args;
    }

    public String translate()
    {
        return StatCollector.translateToLocalFormatted("ground_bridge.error." + displayKey, arguments);
    }
}
