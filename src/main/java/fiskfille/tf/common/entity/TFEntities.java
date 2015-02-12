package fiskfille.tf.common.entity;

import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;
import fiskfille.tf.TransformersMod;

public class TFEntities 
{
	public static void registerEntities()
	{
//		EntityRegistry.registerModEntity(EntityLaser.class, "laser", 305, MainClass.modid, 1, 10, true);
		registerEntity(EntityMiniMissile.class, "tf_tank_shell", 20, 10, true);
		registerEntity(EntityMissile.class, "tf_missile", 20, 10, true);
		registerEntity(EntityMiniMissile.class, "tf_mini_missile", 20, 10, true);
	}
	
	private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange, int updateFrequency, boolean sendVelocityUpdates)
	{
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, name, id);
		EntityRegistry.registerModEntity(entityClass, name, id, TransformersMod.instance, trackingRange, updateFrequency, sendVelocityUpdates);
	}
}
