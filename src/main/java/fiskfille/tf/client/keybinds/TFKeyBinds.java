package fiskfille.tf.client.keybinds;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class TFKeyBinds
{
    public static TFKeyBinding keyBindingTransform1 = new TFKeyBinding("key.transform1", Keyboard.KEY_C);
    public static TFKeyBinding keyBindingStealthMode = new TFKeyBinding("key.stealth_force", Keyboard.KEY_V);
    public static TFKeyBinding keyBindingZoom = new TFKeyBinding("key.aim", Keyboard.KEY_B);
    public static TFKeyBinding keyBindingBrake = new TFKeyBinding("key.brake", Keyboard.KEY_Z);
    public static TFKeyBinding keyBindingVehicleFirstPerson = new TFKeyBinding("key.first_person", Keyboard.KEY_G);
    public static TFKeyBinding keyBindingViewFront = new TFKeyBinding("key.rear_view", Keyboard.KEY_R);

    public static void register()
    {
        ClientRegistry.registerKeyBinding(keyBindingTransform1);
        ClientRegistry.registerKeyBinding(keyBindingStealthMode);
        ClientRegistry.registerKeyBinding(keyBindingZoom);
        ClientRegistry.registerKeyBinding(keyBindingBrake);
        ClientRegistry.registerKeyBinding(keyBindingVehicleFirstPerson);
        ClientRegistry.registerKeyBinding(keyBindingViewFront);
    }
}
