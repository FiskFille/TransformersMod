package fiskfille.tf.main;

import net.minecraftforge.common.config.Configuration;

public class TFConfig
{
	public static boolean firstPersonAfterTransformation;
	
	public void load(Configuration config)
	{
		firstPersonAfterTransformation = config.getBoolean("First-person switch", "Options", false, "If true, you will switch to first-person mode after transforming from vehicle to robot mode.");
	}
}