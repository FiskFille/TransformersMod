package fiskfille.tf.common.achievement;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class TFAchievements 
{
	public static Achievement transformers = (new Achievement("achievement.transformers_mod", "transformers_mod", 0, 0, TFBlocks.energonCrystal, null)).initIndependentStat().registerStat();
	public static Achievement transformium = (new Achievement("achievement.transformium", "transformium", 2, -2, TFItems.transformium, transformers)).registerStat();
	public static Achievement transformer = new Achievement("achievement.transformer", "transformer", 4, -2, TFItems.skystrikeHelmet, transformium).registerStat();
	public static Achievement transform = new Achievement("achievement.transform", "transform", 4, -4, TFItems.carWheel, transformer).setSpecial().registerStat();
	public static Achievement firstMissile = new Achievement("achievement.shoot_missile", "shoot_missile", 4, 0, TFItems.missile, transformer).registerStat();
	public static Achievement donate = new Achievement("achievement.tf.donate", "tf.donate", 0, 2, Items.emerald, transformers).setSpecial().registerStat();
	
	public static AchievementPage transformersPage = new AchievementPage("Transformers", transformers, transformium, firstMissile, transformer, transform, donate);
	
	public static void register()
	{
		AchievementPage.registerAchievementPage(transformersPage);
	}
}
