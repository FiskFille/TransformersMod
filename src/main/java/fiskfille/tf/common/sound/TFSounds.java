package fiskfille.tf.common.sound;

import fiskfille.tf.TransformersMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TFSounds
{
    public static final SoundEvent TRANSFORM_ROBOT = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "transform_robot"));
    public static final SoundEvent TRANSFORM_VEHICLE = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "transform_vehicle"));

    public static final SoundEvent TRANSFORM_STEALTH = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "transform_stealth"));
    public static final SoundEvent TRANSFORM_STEALTH_IN = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "transform_stealth_in"));

    public static void register()
    {
        GameRegistry.register(TRANSFORM_ROBOT);
        GameRegistry.register(TRANSFORM_VEHICLE);

        GameRegistry.register(TRANSFORM_STEALTH);
        GameRegistry.register(TRANSFORM_STEALTH_IN);
    }

    private static SoundEvent create(ResourceLocation identifier)
    {
        return new SoundEvent(identifier).setRegistryName(identifier);
    }
}
