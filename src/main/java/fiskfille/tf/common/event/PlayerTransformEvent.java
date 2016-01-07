package fiskfille.tf.common.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

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
