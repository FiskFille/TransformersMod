package fiskfille.tf.common.achievement;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;

public class TFAchievements
{
    public static Achievement transformersMod = (new Achievement("achievement.tf.transformers_mod", "tf.transformers_mod", 0, 0, TFBlocks.energonCrystal, null)).initIndependentStat().registerStat();
    public static Achievement transformium = (new Achievement("achievement.tf.transformium", "tf.transformium", 2, -2, TFItems.transformium, transformersMod)).registerStat();
    public static Achievement transform = new Achievement("achievement.tf.transform", "tf.transform", 4, -2, TFItems.carWheel, transformium).registerStat();
    public static Achievement firstMissile = new Achievement("achievement.tf.shoot_missile", "tf.shoot_missile", 4, 0, TFItems.missile, transform).registerStat();
    public static Achievement donate = new Achievement("achievement.tf.donate", "tf.donate", -3, -1, Items.emerald, transformersMod).setSpecial().registerStat();
    public static Achievement detonateSeed = new Achievement("achievement.tf.detonateSeed", "tf.detonateSeed", -1, -4, TFBlocks.transformiumSeed, transformium).setSpecial().registerStat();
    public static Achievement tracks = new Achievement("achievement.tf.tracks", "tf.tracks", -5, -3, TFItems.tankTracks, null).registerStat();
    public static Achievement skystrike = new Achievement("achievement.tf.skystrike", "tf.skystrike", -7, -5, TFItems.skystrikeHelmet, null).setSpecial().registerStat();
    public static Achievement purge = new Achievement("achievement.tf.purge", "tf.purge", -7, -4, TFItems.purgeHelmet, tracks).setSpecial().registerStat();
    public static Achievement vurp = new Achievement("achievement.tf.vurp", "tf.vurp", -7, -3, TFItems.vurpHelmet, null).setSpecial().registerStat();
    public static Achievement subwoofer = new Achievement("achievement.tf.subwoofer", "tf.subwoofer", -7, -2, TFItems.subwooferHelmet, null).setSpecial().registerStat();
    public static Achievement sharpshooter = new Achievement("achievement.tf.sharpshooter", "tf.sharpshooter", 6, 1, TFItems.vurpsSniper, firstMissile).setSpecial().registerStat();
    
    public static AchievementPage transformersPage = new AchievementPage("Transformers", transformersMod, transformium, firstMissile, transform, donate, tracks, skystrike, purge, vurp, subwoofer, detonateSeed, sharpshooter);
    
    public static void register()
    {
        AchievementPage.registerAchievementPage(transformersPage);
    }
}
