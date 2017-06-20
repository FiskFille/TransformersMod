package fiskfille.tf.common.config;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
@Config(modid = TransformersMod.MODID, category = "")
public class TFConfig
{
    @Config.Name("options")
    @Config.LangKey("config.transformers.options")
    public static Options options = new Options();

    @Config.Name("aesthetic")
    @Config.LangKey("config.transformers.aesthetic")
    public static Aesthetic aesthetic = new Aesthetic();

    @Config.Name("projectiles")
    @Config.LangKey("config.transformers.projectiles")
    public static Projectiles projectiles = new Projectiles();

    @Config.Name("restrictions")
    @Config.LangKey("config.transformers.restrictions")
    public static Restrictions restrictions = new Restrictions();

    private static Restrictions globalRestrictions = restrictions;

    public static Restrictions getGlobalRestrictions()
    {
        return globalRestrictions;
    }

    public static class Options
    {
        @Config.Name("groundBridgeMinRange")
        @Config.Comment("If false, the 'Invalid Coords' Ground Bridge error will be ignored.")
        @Config.LangKey("config.transformers.groundBridgeMinRange")
        public boolean groundBridgeMinRange = true;

        @Config.Name("controlPanelMaxRange")
        @Config.Comment("The maximum distance the portal frame can be from the Ground Bridge Control Panel")
        @Config.LangKey("config.transformers.controlPanelMaxRange")
        @Config.RangeInt(min = 0)
        public int controlPanelMaxRange = 20;

        @Config.Name("firstPersonSwitch")
        @Config.Comment("If true, you will revert to first-person after transforming back to robot mode.")
        @Config.LangKey("config.transformers.firstPersonSwitch")
        public boolean firstPersonSwitch = false;
    }

    public static class Aesthetic
    {
        @Config.Name("purgeDashTop")
        @Config.Comment("If true, Purge's dash bar will appear at the top of the screen instead of the middle.")
        @Config.LangKey("config.transformers.purgeDashTop")
        public boolean purgeDashTop = false;

        @Config.Name("useMiles")
        @Config.Comment("If true, Purge's dash bar will appear at the top of the screen instead of the middle.")
        @Config.LangKey("config.transformers.useMiles")
        public boolean useMiles = false;

        @Config.Name("oldPortalRender")
        @Config.Comment("If true, the old rendering for the Ground Bridge portal will be used.")
        @Config.LangKey("config.transformers.oldPortalRender")
        public boolean oldPortalRender = false;
    }

    public static class Projectiles
    {
        @Config.Name("allowMissileExplosions")
        @Config.Comment("If false, missiles will not damage terrain.")
        @Config.LangKey("config.transformers.allowMissileExplosions")
        public boolean allowMissileExplosions = true;

        @Config.Name("allowTankShellExplosions")
        @Config.Comment("If false, tank shells won't damage terrain.")
        @Config.LangKey("config.transformers.allowTankShellExplosions")
        public boolean allowTankShellExplosions = false;
    }

    public static class Restrictions
    {
        @Config.Name("transformBlacklist")
        @Config.Comment("A list of blacklisted transformer identifiers. (For example: \"tranformers:skystrike\")")
        @Config.LangKey("config.transformers.transformBlacklist")
        @Config.RequiresWorldRestart
        public String[] transformBlacklist = new String[] { "transformers:example" };

        public boolean canTransform(Transformer transformer)
        {
            for (String blacklisted : this.transformBlacklist)
            {
                if (transformer.getIdentifier().equals(new ResourceLocation(blacklisted)))
                {
                    return false;
                }
            }

            return true;
        }
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(TransformersMod.MODID))
        {
            ConfigManager.sync(TransformersMod.MODID, Config.Type.INSTANCE);
        }
    }
}
