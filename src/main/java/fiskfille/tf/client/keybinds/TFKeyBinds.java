package fiskfille.tf.client.keybinds;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class TFKeyBinds
{
    public static final TFKeyBinding TRANSFORM_1 = new TFKeyBinding("key.transform1", Keyboard.KEY_C);
    public static final TFKeyBinding STEALTH_MODE = new TFKeyBinding("key.stealth_force", Keyboard.KEY_V);
    public static final TFKeyBinding ZOOM = new TFKeyBinding("key.aim", Keyboard.KEY_B);
    public static final TFKeyBinding BRAKE = new TFKeyBinding("key.brake", Keyboard.KEY_Z);
    public static final TFKeyBinding VEHICLE_FIRST_PERSON = new TFKeyBinding("key.first_person", Keyboard.KEY_G);
    public static final TFKeyBinding VIEW_FRONT = new TFKeyBinding("key.rear_view", Keyboard.KEY_R);

    public static void register()
    {
        ClientRegistry.registerKeyBinding(TRANSFORM_1);
        ClientRegistry.registerKeyBinding(STEALTH_MODE);
        ClientRegistry.registerKeyBinding(ZOOM);
        ClientRegistry.registerKeyBinding(BRAKE);
        ClientRegistry.registerKeyBinding(VEHICLE_FIRST_PERSON);
        ClientRegistry.registerKeyBinding(VIEW_FRONT);
    }
}
