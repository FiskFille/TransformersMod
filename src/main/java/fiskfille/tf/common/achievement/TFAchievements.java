package fiskfille.tf.common.achievement;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fiskfille.tf.asm.TFTranslator;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.TFSubItems;

public class TFAchievements
{
    public static final Achievement transformersMod = new TFAchievement("transformers_mod", 0, 0, null).initIndependentStat().registerStat();
    public static final Achievement transformium = new TFAchievement("transformium", 2, -2, transformersMod).registerStat();
    public static final Achievement transform = new TFAchievement("transform", 4, -2, transformium).registerStat();
    public static final Achievement firstMissile = new TFAchievement("shoot_missile", 4, 0, transform).registerStat();
    public static final Achievement donate = new TFAchievement("donate", -3, -1, transformersMod).setSpecial().registerStat();
    public static final Achievement detonateSeed = new TFAchievement("detonateSeed", -1, -4, transformium).setSpecial().registerStat();
    public static final Achievement tracks = new TFAchievement("tracks", -5, -3, null).registerStat();
    public static final Achievement skystrike = new TFAchievement("skystrike", -7, -5, null).setSpecial().registerStat();
    public static final Achievement purge = new TFAchievement("purge", -7, -4, tracks).setSpecial().registerStat();
    public static final Achievement vurp = new TFAchievement("vurp", -7, -3, null).setSpecial().registerStat();
    public static final Achievement subwoofer = new TFAchievement("subwoofer", -7, -2, null).setSpecial().registerStat();
    public static final Achievement sharpshooter = new TFAchievement("sharpshooter", 6, 1, firstMissile).setSpecial().registerStat();

    public static final List<Achievement> achievements = Lists.newArrayList();
    private static final Map<Achievement, ItemStack> displayItems = Maps.newHashMap();
    private static boolean init = false;
    
    public static void register()
    {
        if (!init)
        {
            init = true;
            
            for (Field field : TFAchievements.class.getFields())
            {
                if (field.getType().getName().equals(Achievement.class.getName()))
                {
                    try
                    {
                        achievements.add((Achievement) field.get(null));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        setItem(transformersMod, TFBlocks.energonCrystal);
        setItem(transformium, TFItems.transformiumFragment);
        setItem(transform, TFSubItems.wheel);
        setItem(firstMissile, TFItems.missile);
        setItem(donate, Items.emerald);
        setItem(detonateSeed, TFBlocks.transformiumSeed);
        setItem(tracks, TFSubItems.tank_track);
        setItem(skystrike, TFSubItems.skystrike_torso_base);
        setItem(purge, TFSubItems.purge_torso_base);
        setItem(vurp, TFSubItems.vurp_torso_base);
        setItem(subwoofer, TFSubItems.subwoofer_torso_base);
        setItem(sharpshooter, TFItems.vurpsSniper);
        
        try
        {
            Field itemField = Achievement.class.getField(TFTranslator.getMappedName("field_75990_d", "theItemStack"));
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(itemField, itemField.getModifiers() & ~Modifier.FINAL);
            
            for (Achievement achievement : achievements)
            {
                itemField.set(achievement, displayItems.get(achievement));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        if (AchievementPage.getAchievementPage("Transformers") == null)
        {
            AchievementPage.registerAchievementPage(new AchievementPage("Transformers", achievements.toArray(new Achievement[achievements.size()])));
        }
    }
    
    private static void setItem(Achievement achievement, ItemStack itemstack)
    {
        displayItems.put(achievement, itemstack);
    }
    
    private static void setItem(Achievement achievement, ItemStack[] aitemstack)
    {
        setItem(achievement, aitemstack[1]);
    }
    
    private static void setItem(Achievement achievement, Item item)
    {
        setItem(achievement, new ItemStack(item));
    }
    
    private static void setItem(Achievement achievement, Block block)
    {
        setItem(achievement, new ItemStack(block));
    }
}
