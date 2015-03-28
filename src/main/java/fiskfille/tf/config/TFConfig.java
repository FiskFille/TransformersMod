package fiskfille.tf.config;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;
import fiskfille.tf.TransformerManager;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFConfig
{
    public static boolean firstPersonAfterTransformation;
    public static boolean purgeDashTop;
    public static boolean allowMissileExplosions;
    public static boolean rollWithJet;
    public static boolean allowTankShellExplosions;
    public static boolean useMiles;
    public static boolean checkForUpdates;
    
    public static Map<Transformer, Boolean> canTransform = new HashMap<Transformer, Boolean>();
    
    private Configuration config;
    
    public void load(Configuration config)
    {
        this.config = config;
        
        checkForUpdates = getBoolean("Check For Updates", true, "If false, the Transformers Mod will not check for updates.");
        firstPersonAfterTransformation = getBoolean("First-person Switch", false, "If true, you will switch to first-person mode after transforming from vehicle to robot mode.");
        purgeDashTop = getBoolean("Purge Dash Bar At Top Of Screen", false, "If true, the purge dash bar will appear at the top of the screen instead of in the middle.");
        allowMissileExplosions = getBoolean("Allow Missile Explosions", true, "If false, missiles won't damage the terrain.");
        rollWithJet = getBoolean("Roll With jet", false, "If true, when the jet rolls, your camera will roll with it.");
        allowTankShellExplosions = getBoolean("Allow Tank Shell Explosions", false, "If false, tank shells won't damage the terrain.");
        useMiles = getBoolean("Use Miles For Speed-Measurement", false, "If true, miles will be used instead of kilometers when measuring speed.");
   
        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            String name = transformer.getName();
            canTransform.put(transformer, getBoolean("Can Transform As " + name, true, "If false, you will not be able to transform as " + name + ". Useful for servers."));
        }
    }
    
    private boolean getBoolean(String name, boolean defualt, String desc)
    {
        return config.getBoolean(name, "Options", defualt, desc);
    }
}