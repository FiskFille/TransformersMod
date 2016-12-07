package fiskfille.tf.common.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class TFMaterial extends Material
{
    public static final TFMaterial energon = new TFMaterial(MapColor.diamondColor).setBurning();
    public static final TFMaterial display = new TFMaterial(MapColor.stoneColor);

    protected boolean isTranslucent;

    public TFMaterial(MapColor color)
    {
        super(color);
    }

    protected TFMaterial setTranslucent()
    {
        isTranslucent = true;
        return this;
    }

    public boolean isOpaque()
    {
        return isTranslucent ? false : blocksMovement();
    }

    protected TFMaterial setRequiresTool()
    {
        return (TFMaterial) super.setRequiresTool();
    }

    protected TFMaterial setBurning()
    {
        return (TFMaterial) super.setBurning();
    }

    protected TFMaterial setNoPushMobility()
    {
        return (TFMaterial) super.setNoPushMobility();
    }

    protected TFMaterial setImmovableMobility()
    {
        return (TFMaterial) super.setImmovableMobility();
    }

    protected TFMaterial setAdventureModeExempt()
    {
        return (TFMaterial) super.setAdventureModeExempt();
    }
}
