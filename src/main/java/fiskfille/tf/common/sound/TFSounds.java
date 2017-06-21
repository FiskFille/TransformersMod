package fiskfille.tf.common.sound;

import fiskfille.tf.TransformersMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class TFSounds
{
    public static final SoundEvent TRANSFORM_ROBOT = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "transform_robot"));
    public static final SoundEvent TRANSFORM_VEHICLE = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "transform_vehicle"));

    public static final SoundEvent TRANSFORM_STEALTH = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "transform_stealth"));
    public static final SoundEvent TRANSFORM_STEALTH_IN = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "transform_stealth_in"));

    public static final SoundEvent TANK_FIRE = TFSounds.create(new ResourceLocation(TransformersMod.MODID, "tankfire"));

    @SubscribeEvent
    public static void register(RegistryEvent<SoundEvent> event)
    {
        GameRegistry.register(TRANSFORM_ROBOT);
        GameRegistry.register(TRANSFORM_VEHICLE);

        GameRegistry.register(TRANSFORM_STEALTH);
        GameRegistry.register(TRANSFORM_STEALTH_IN);

        GameRegistry.register(TANK_FIRE);
    }

    private static SoundEvent create(ResourceLocation identifier)
    {
        return new SoundEvent(identifier).setRegistryName(identifier);
    }
}
