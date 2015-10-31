package fiskfille.tf.common.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import fiskfille.tf.TransformersMod;
import net.minecraft.entity.Entity;

public class TFEntities
{
    public static void registerEntities()
    {
        registerEntity(EntityTankShell.class, "tank_shell", 20, 10, true);
        registerEntity(EntityMissile.class, "missile", 20, 10, true);
        registerEntity(EntityLaser.class, "laser", 20, 10, true);
        registerEntity(EntityTransformiumSeed.class, "transformium_seed", 20, 10, true);
        registerEntity(EntityFlamethrowerFire.class, "flamethrower_fire", 20, 10, true);
        registerEntity(EntityBassCharge.class, "bass_charge", 20, 10, true);
        registerEntity(EntityLaserBeam.class, "laser_beam", 20, 10, true);
        registerEntityWithEgg(EntityTransformer.class, "transformer", 80, 1, true, 0, 0);
    }

    private static void registerEntityWithEgg(Class<? extends Entity> entityClass, String name, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, int primary, int secondary)
    {
        name = "tf_" + name;

        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, name, id, primary, secondary);
        EntityRegistry.registerModEntity(entityClass, name, id, TransformersMod.instance, trackingRange, updateFrequency, sendVelocityUpdates);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange, int updateFrequency, boolean sendVelocityUpdates)
    {
        name = "tf_" + name;

        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, name, id);
        EntityRegistry.registerModEntity(entityClass, name, id, TransformersMod.instance, trackingRange, updateFrequency, sendVelocityUpdates);
    }
}
