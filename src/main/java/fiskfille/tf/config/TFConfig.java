package fiskfille.tf.config;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;
import fiskfille.tf.TransformerManager;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.event.PlayerTransformEvent;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFConfig
{
    public static boolean firstPersonAfterTransformation;
    public static boolean purgeDashTop;
    public static boolean allowMissileExplosions;
    public static boolean allowTankShellExplosions;
    public static boolean useMiles;
    public static boolean checkForUpdates;
    
    public static Map<Transformer, Boolean> canTransform = new HashMap<Transformer, Boolean>();
    
    private Configuration config;
    
    public void load(Configuration config)
    {
        this.config = config;
        
        checkForUpdates = getBoolean("Check For Updates", true, "If false, the Transformers Mod will not check for updates.");
        useMiles = getBoolean("Use Miles For Speed-Measurement", false, "If true, miles will be used instead of kilometers when measuring speed.");
        
        purgeDashTop = getAestheticBoolean("Show Purge-Dash At Top Of Screen", false, "If true, Purge's Dash Bar will appear at the top of the screen instead of in the middle of it.");
        
        allowMissileExplosions = getProjectileBoolean("Allow Missile Explosions", true, "If false, missiles won't damage the terrain.");
        allowTankShellExplosions = getProjectileBoolean("Allow Tank Shell Explosions", false, "If false, tank shells won't damage the terrain.");
        
        firstPersonAfterTransformation = getTransformationBoolean("First-person Switch", false, "If true, you will switch to first-person mode after transforming from vehicle to robot mode.");
        
        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            String name = transformer.getName();
            canTransform.put(transformer, getTransformationBoolean("Can Transform As " + name, true, "If false, you will not be able to transform as " + name + ". Useful for servers."));
        }
    }
    
    private boolean getAestheticBoolean(String name, boolean defualt, String desc)
    {
        return config.getBoolean(name, "Aesthetic", defualt, desc);
    }
    
    private boolean getProjectileBoolean(String name, boolean defualt, String desc)
    {
        return config.getBoolean(name, "Projectiles", defualt, desc);
    }
    
    private boolean getTransformationBoolean(String name, boolean defualt, String desc)
    {
        return config.getBoolean(name, "Transformation", defualt, desc);
    }
    
    private boolean getBoolean(String name, boolean defualt, String desc)
    {
        return config.getBoolean(name, "Options", defualt, desc);
    }
    
    public static Boolean canTransform(Transformer transformer)
    {
        return transformer != null ? TFConfig.canTransform.get(transformer) : true;
    }
}