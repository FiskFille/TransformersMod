package fiskfille.tf.common.groundbridge;

import net.minecraft.util.StatCollector;

public enum GroundBridgeError
{
    INVALID_COORDS, NOT_ENOUGH_SPACE, NOT_ENOUGH_ENERGY, NO_PORTAL_LINKED, PORTAL_OBSTRUCTED, OUT_OF_BOUNDS;

    public Object[] arguments;

    public String translate()
    {
        return StatCollector.translateToLocalFormatted("ground_bridge.error." + name().toLowerCase(), arguments);
    }
}
