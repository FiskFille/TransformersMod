package fiskfille.tf.common.event;

import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class PlayerTransformEvent extends PlayerEvent
{
    public final int altMode;
    public final boolean stealthForce;
    public final Transformer transformer;

    public PlayerTransformEvent(EntityPlayer player, Transformer transformer, int altMode, boolean stealthForce)
    {
        super(player);
        this.altMode = altMode;
        this.stealthForce = stealthForce;
        this.transformer = transformer;
    }
}
