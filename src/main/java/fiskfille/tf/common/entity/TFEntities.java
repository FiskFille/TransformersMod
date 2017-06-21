package fiskfille.tf.common.entity;

import fiskfille.tf.TransformersMod;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class TFEntities
{
    private static int nextId;

    public static void register()
    {
        registerEntity(EntityTankShell.class, "tank_shell", 20, 10, true);
        registerEntity(EntityMissile.class, "missile", 20, 10, true);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange, int updateFrequency, boolean sendVelocityUpdates)
    {
        ResourceLocation identifier = new ResourceLocation(TransformersMod.MODID, name);
        EntityRegistry.registerModEntity(identifier, entityClass, name, nextId++, TransformersMod.INSTANCE, trackingRange, updateFrequency, sendVelocityUpdates);
    }
}
