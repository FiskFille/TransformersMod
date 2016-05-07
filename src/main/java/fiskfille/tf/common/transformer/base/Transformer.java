package fiskfille.tf.common.transformer.base;

import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.client.tutorial.EnumTutorialType;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.config.TFConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

/**
 * @author gegy1000, FiskFille
 */
public abstract class Transformer
{
    private String name;

    public abstract Item getHelmet();

    public abstract Item getChestplate();

    public abstract Item getLeggings();

    public abstract Item getBoots();

    /**
     * Override to specify whether this Transformer can use nitro.
     *
     * @param player The player trying to use nitro.
     * @returns whether the player can use nitro.
     */
    public boolean canUseNitro(EntityPlayer player, int altMode)
    {
        return true;
    }

    /**
     * @returns the model to use for this Transformer.
     */
    public TransformerModel getModel()
    {
        return TFModelRegistry.getModel(this);
    }

    public Transformer(String name)
    {
        this.name = name;
    }

    /**
     * @returns the name of this Transformer.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Called every tick while wearing the armor.
     *
     * @param player The player wearing the armor.
     * @param timer  The transformation timer.
     */
    public void tick(EntityPlayer player, int timer)
    {
    }

    public boolean canZoom(EntityPlayer player)
    {
        return false;
    }

    public float getZoomAmount(EntityPlayer player, int altMode)
    {
        return 0.1F;
    }

    public float getCameraYOffset(EntityPlayer player, int altMode)
    {
        return -1;
    }

    public float getVehicleCameraYOffset(EntityPlayer player, int altMode)
    {
        return -1;
    }

    public boolean canJumpAsVehicle(EntityPlayer player, int altMode)
    {
        return false;
    }

    public boolean canTransform(EntityPlayer player)
    {
        return TFConfig.canTransform(this);
    }

    public boolean hasStealthForce(EntityPlayer player, int altMode)
    {
        return false;
    }

    public abstract void updateMovement(EntityPlayer player, int altMode);

    public Item getShootItem(int altMode)
    {
        return null;
    }

    public Entity getShootEntity(EntityPlayer playe, int altModer)
    {
        return null;
    }

    public String getShootSound(int altMode)
    {
        return null;
    }

    public float getShootVolume(int altMode)
    {
        return 1;
    }

    public int getShots(int altMode)
    {
        return 4;
    }

    public boolean canShoot(EntityPlayer player, int altMode)
    {
        return false;
    }

    public boolean onJump(EntityPlayer player)
    {
        return true;
    }

    public float getThirdPersonDistance(EntityPlayer player, int altMode)
    {
        return 2.0F - -(float) TFDataManager.getTransformationTimer(player) / 10;
    }

    /**
     * Called every tick while using nitro on the client side, used to make nitro particles.
     *
     * @param player The player making the particles
     */
    public void doNitroParticles(EntityPlayer player, int altMode)
    {
    }

    /**
     * Called when this transformer hits the ground.
     *
     * @param player   The player who is falling.
     * @param distance The distance fell.
     * @return The damage to take.
     */
    public float fall(EntityPlayer player, float distance, int altMode)
    {
        return distance;
    }

    public boolean hasRapidFire(int altMode)
    {
        return false;
    }

    public boolean disableViewBobbing(EntityPlayer player, int altMode)
    {
        return true;
    }

    public boolean disableStepSounds(EntityPlayer player, int altMode)
    {
        return true;
    }

    public EnumTutorialType getTutorialType(int altMode)
    {
        return null;
    }

    public boolean overrideFirstPerson(EntityPlayer player, int altMode)
    {
        return true;
    }

    public boolean renderSpeedAndNitro(EntityPlayer player, int altMode)
    {
        return true;
    }

    /**
     * @param player The player
     * @return Whether this Transformer can interact with the world while in vehicle mode, i.e.
     * break blocks, damage entities, etc.
     */
    public boolean canInteractInVehicleMode(EntityPlayer player, int altMode)
    {
        return true;
    }

    public int getAltModeCount()
    {
        return 1;
    }
}
