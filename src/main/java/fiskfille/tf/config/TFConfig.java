package fiskfille.tf.config;

import java.util.Map;

import net.minecraftforge.common.config.Configuration;

import com.google.common.collect.Maps;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFConfig
{
    public static boolean firstPersonAfterTransformation;
    public static boolean purgeDashTop;
    public static boolean allowMissileExplosions;
    public static boolean allowTankShellExplosions;
    public static boolean useMiles;
    public static boolean checkForUpdates;
    public static boolean groundBridgeMinRange;
    public static boolean oldPortalRender;

    public static Map<Transformer, Boolean> canTransform = Maps.newHashMap();

    public static Configuration configFile;

    public static void load(Configuration config)
    {
        configFile = config;

        checkForUpdates = getBoolean("Check For Updates", true, "If false, the mod will not check for updates.");
        groundBridgeMinRange = getBoolean("Ground Bridge Min Range", true, "If false, the 'Invalid Coords' Ground Bridge error will be discarded.");

        purgeDashTop = getAestheticBoolean("Show Purge-Dash At Top Of Screen", false, "If true, Purge's Dash Bar will appear at the top of the screen instead of in the middle of it.");
        useMiles = getAestheticBoolean("Use Miles For Speed-Measurement", false, "If true, miles will be used instead of kilometers when measuring speed.");
        oldPortalRender = getAestheticBoolean("Old Portal Rendering", false, "If true, the old rendering for the Ground Bridge portal will be used.");
        
        allowMissileExplosions = getProjectileBoolean("Allow Missile Explosions", true, "If false, missiles won't damage the terrain.");
        allowTankShellExplosions = getProjectileBoolean("Allow Tank Shell Explosions", false, "If false, tank shells won't damage the terrain.");

        firstPersonAfterTransformation = getTransformationBoolean("First-person Switch", false, "If true, you will switch to first-person mode after transforming from vehicle to robot mode.");

        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            String name = transformer.getName();
            canTransform.put(transformer, getTransformationBoolean("Can Transform As " + name, true, "If false, you will not be able to transform as " + name + ". Useful for servers."));
        }
    }

    private static boolean getAestheticBoolean(String name, boolean defualt, String desc)
    {
        return configFile.getBoolean(name, "Aesthetic", defualt, desc);
    }

    private static boolean getProjectileBoolean(String name, boolean defualt, String desc)
    {
        return configFile.getBoolean(name, "Projectiles", defualt, desc);
    }

    private static boolean getTransformationBoolean(String name, boolean defualt, String desc)
    {
        return configFile.getBoolean(name, "Transformation", defualt, desc);
    }

    private static boolean getBoolean(String name, boolean defualt, String desc)
    {
        return configFile.getBoolean(name, "Options", defualt, desc);
    }

    public static Boolean canTransform(Transformer transformer)
    {
        return transformer != null ? TFConfig.canTransform.get(transformer) : true;
    }
}
