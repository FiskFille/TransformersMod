package fiskfille.tf.common.transformer.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import fiskfille.tf.client.model.transformer.TFModelRegistry;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.common.playerdata.TFDataManager;

public abstract class Transformer
{
    private String name;

    public abstract Item getHelmet();

    public abstract Item getChestplate();

    public abstract Item getLeggings();

    public abstract Item getBoots();

    public Biped getModel()
    {
        return TFModelRegistry.getModel(this);
    }

    public Transformer(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void robotTick(EntityPlayer player)
    {
    }

    public void transformationTick(EntityPlayer player, int timer)
    {
    }

    public void vehicleTick(EntityPlayer player)
    {
    }

    public boolean canZoom(EntityPlayer player)
    {
        return false;
    }

    public float getZoomAmount(EntityPlayer player)
    {
        return 0.1F;
    }

    public float getCameraYOffset(EntityPlayer player)
    {
        return -1;
    }

    public float getVehicleCameraYOffset(EntityPlayer player)
    {
        return -1;
    }

    public boolean canJumpAsVehicle(EntityPlayer player)
    {
        return false;
    }

    public boolean hasStealthForce(EntityPlayer player)
    {
        return false;
    }

    public void updateMovement(EntityPlayer player)
    {
    }

    public Item getShootItem()
    {
        return null;
    }

    public Entity getShootEntity(EntityPlayer player)
    {
        return null;
    }

    public String getShootSound()
    {
        return null;
    }

    public float getShootVolume()
    {
        return 1;
    }

    public int getShots()
    {
        return 4;
    }

    public boolean canShoot(EntityPlayer player)
    {
        return false;
    }

    public boolean hasJetpack()
    {
        return false;
    }

    public void onJump(EntityPlayer player)
    {
    }

    public float getThirdPersonDistance(EntityPlayer player)
    {
        return 4.0F - (2.0F - (float) TFDataManager
                .getTransformationTimer(player) / 10);
    }

    public void doNitroParticles(EntityPlayer player)
    {
    }

    public float fall(EntityPlayer player, float distance)
    {
        return distance;
    }
}
