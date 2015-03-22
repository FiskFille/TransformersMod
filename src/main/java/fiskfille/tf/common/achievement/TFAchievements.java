package fiskfille.tf.common.achievement;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;

public class TFAchievements 
{
	public static Achievement transformersMod = (new Achievement("achievement.transformers_mod", "transformers_mod", 0, 0, TFBlocks.energonCrystal, null)).initIndependentStat().registerStat();
	public static Achievement transformium = (new Achievement("achievement.transformium", "transformium", 2, -2, TFItems.transformium, transformersMod)).registerStat();
	public static Achievement transformer = new Achievement("achievement.transformer", "transformer", 4, -2, TFItems.skystrikeHelmet, transformium).registerStat();
	public static Achievement transform = new Achievement("achievement.transform", "transform", 4, -4, TFItems.carWheel, transformer).setSpecial().registerStat();
	public static Achievement firstMissile = new Achievement("achievement.shoot_missile", "shoot_missile", 4, 0, TFItems.missile, transformer).registerStat();
	public static Achievement donate = new Achievement("achievement.tf.donate", "tf.donate", -3, -1, Items.emerald, transformersMod).setSpecial().registerStat();
	public static Achievement tracks = new Achievement("achievement.tf.tracks", "tf.tracks", 2, 1, TFItems.tankTracks, transformersMod).registerStat();
	public static Achievement purge = new Achievement("achievement.tf.purge", "tf.purge", 3, 3, TFItems.purgeHelmet, tracks).registerStat();
	public static Achievement detonateSeed = new Achievement("achievement.tf.detonateSeed", "tf.detonateSeed", -1, -4, TFBlocks.transformiumSeed, transformium).setSpecial().registerStat();

	public static AchievementPage transformersPage = new AchievementPage("Transformers", transformersMod, transformium, firstMissile, transformer, transform, donate, tracks, purge, detonateSeed);
	
	public static void register()
	{
		AchievementPage.registerAchievementPage(transformersPage);
	}
}
