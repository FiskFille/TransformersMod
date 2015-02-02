package fiskfille.tf.config;

import net.minecraftforge.common.config.Configuration;

public class TFConfig
{
	public static boolean firstPersonAfterTransformation;
	public static boolean purgeDashTop;
	public static boolean allowMissileExplosions;
	public static boolean rollWithJet;
	public static boolean allowTankShellExplosions;
	
	public void load(Configuration config)
	{
		firstPersonAfterTransformation = config.getBoolean("First-person Switch", "Options", false, "If true, you will switch to first-person mode after transforming from vehicle to robot mode.");
		purgeDashTop = config.getBoolean("Purge Dash Bar At Top Of Screen", "Options", false, "If true, the purge dash bar will appear at the top of the screen instead of in the middle.");
		allowMissileExplosions = config.getBoolean("Allow Missile Explosions", "Options", true, "If false, missiles won't damage the terrain.");
		allowTankShellExplosions = config.getBoolean("Allow Tank Shell Explosions", "Options", false, "If false, tank shells won't damage the terrain.");
		rollWithJet = config.getBoolean("Roll With jet", "Options", false, "If true, when the jet rolls, your camera will roll with it.");
	}
}