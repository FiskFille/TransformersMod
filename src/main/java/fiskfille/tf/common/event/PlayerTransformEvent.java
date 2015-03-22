package fiskfille.tf.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerTransformEvent extends PlayerEvent
{
    public final boolean transformed;
    public final boolean stealthForce;
    
    public PlayerTransformEvent(EntityPlayer player, boolean transformed, boolean stealthForce)
    {
        super(player);
        this.transformed = transformed;
        this.stealthForce = stealthForce;
    }
}
