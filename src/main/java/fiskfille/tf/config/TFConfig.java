package fiskfille.tf.config;

import java.util.Map;

import net.minecraftforge.common.config.Configuration;

import com.google.common.collect.Maps;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFConfig
{
    private static final String CATEGORY_GENERAL = "Options";
    private static final String CATEGORY_AESTHETIC = "Aesthetic";
    private static final String CATEGORY_PROJECTILES = "Projectiles";
    private static final String CATEGORY_TRANSFORMATION = "Transformation";
    
    public static boolean firstPersonAfterTransformation;
    public static boolean purgeDashTop;
    public static boolean allowMissileExplosions;
    public static boolean allowTankShellExplosions;
    public static boolean useMiles;
    public static boolean checkForUpdates;
    public static boolean groundBridgeMinRange;
    public static boolean oldPortalRender;
    public static int controlPanelMaxRange;

    public static Map<Transformer, Boolean> canTransform = Maps.newHashMap();

    public static Configuration configFile;

    public static void load(Configuration config)
    {
        configFile = config;

        checkForUpdates = config.getBoolean("Check For Updates", CATEGORY_GENERAL, true, "If false, the mod will not check for updates.");
        groundBridgeMinRange = config.getBoolean("Ground Bridge Min Range", CATEGORY_GENERAL, true, "If false, the 'Invalid Coords' Ground Bridge error will be discarded.");
        controlPanelMaxRange = config.getInt("Control Panel Max Range", CATEGORY_GENERAL, 20, 0, Integer.MAX_VALUE, "The maximum distance the portal frame can be from the Ground Bridge Control Panel.");
        
        purgeDashTop = config.getBoolean("Show Purge-Dash At Top Of Screen", CATEGORY_AESTHETIC, false, "If true, Purge's Dash Bar will appear at the top of the screen instead of in the middle of it.");
        useMiles = config.getBoolean("Use Miles For Speed-Measurement", CATEGORY_AESTHETIC, false, "If true, miles will be used instead of kilometers when measuring speed.");
        oldPortalRender = config.getBoolean("Old Portal Rendering", CATEGORY_AESTHETIC, false, "If true, the old rendering for the Ground Bridge portal will be used.");
        
        allowMissileExplosions = config.getBoolean("Allow Missile Explosions", CATEGORY_PROJECTILES, true, "If false, missiles won't damage the terrain.");
        allowTankShellExplosions = config.getBoolean("Allow Tank Shell Explosions", CATEGORY_PROJECTILES, false, "If false, tank shells won't damage the terrain.");

        firstPersonAfterTransformation = config.getBoolean("First-person Switch", CATEGORY_TRANSFORMATION, false, "If true, you will switch to first-person mode after transforming from vehicle to robot mode.");

        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            String name = transformer.getName();
            canTransform.put(transformer, config.getBoolean("Can Transform As " + name, CATEGORY_TRANSFORMATION, true, "If false, you will not be able to transform as " + name + ". Useful for servers."));
        }
    }

    public static Boolean canTransform(Transformer transformer)
    {
        return transformer != null ? TFConfig.canTransform.get(transformer) : true;
    }
}
