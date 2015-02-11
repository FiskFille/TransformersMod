package fiskfille.transformersmod.client.keybinds;

import java.lang.reflect.Field;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class TFKeyBinds
{
	public static TFKeyBinding keyBindingTransform = new TFKeyBinding("Transform", Keyboard.KEY_C);
	public static TFKeyBinding keyBindingNitro = new TFKeyBinding("Nitro Boost", Keyboard.KEY_X);
	public static TFKeyBinding keyBindingBrake = new TFKeyBinding("Brake", Keyboard.KEY_Z);
	public static TFKeyBinding keyBindingZoom = new TFKeyBinding("Aim", Keyboard.KEY_B);
	public static TFKeyBinding keyBindingStealthMode = new TFKeyBinding("Stealth Mode", Keyboard.KEY_V);
	public static TFKeyBinding keyBindingVehicleFirstPerson = new TFKeyBinding("Vehicle First Person", Keyboard.KEY_G);

	public static void register()
	{
		for (Field field : TFKeyBinds.class.getDeclaredFields())
		{
			if(field.getType() == TFKeyBinding.class)
			{
				try 
				{
					ClientRegistry.registerKeyBinding((TFKeyBinding) field.get(null));
				} 
				catch (IllegalArgumentException e)
				{
					e.printStackTrace();
				}
				catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
