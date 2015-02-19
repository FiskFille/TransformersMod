package fiskfille.tf.main.misc;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.entity.EntityLaser;
import fiskfille.tf.entity.EntityMissile;
import fiskfille.tf.main.TFItems;
import fiskfille.tf.model.ModelPurge;
import fiskfille.tf.model.ModelSkystrike;
import fiskfille.tf.model.ModelSubwoofer;
import fiskfille.tf.model.ModelVurp;
import fiskfille.tf.render.entity.RenderLaser;
import fiskfille.tf.render.item.RenderItemDisplayVehicle;
import fiskfille.tf.render.item.RenderItemPurgesKatana;
import fiskfille.tf.render.item.RenderItemSkystrikesCrossbow;
import fiskfille.tf.render.tileentity.RenderCrystal;
import fiskfille.tf.render.tileentity.RenderDisplayPillar;
import fiskfille.tf.tileentity.TileEntityCrystal;
import fiskfille.tf.tileentity.TileEntityDisplayPillar;

public class ClientProxy extends CommonProxy
{
	public static ModelSkystrike modelSkystrike = new ModelSkystrike();
	public static ModelPurge modelPurge = new ModelPurge();
	public static ModelVurp modelVurp = new ModelVurp();
	public static ModelSubwoofer modelSubwoofer = new ModelSubwoofer();
//	public static ModelSubwoofer modelCloudTrap = new ModelSubwoofer();
	public static ModelBiped modelBipedMain = new ModelBiped();
	
	public static KeyBinding keyBindingTransform = new KeyBinding("Transform", Keyboard.KEY_C, "Transformers");
	public static KeyBinding keyBindingNitro = new KeyBinding("Nitro Boost", Keyboard.KEY_X, "Transformers");
	public static KeyBinding keyBindingBrake = new KeyBinding("Brake", Keyboard.KEY_Z, "Transformers");
	
	public static Map<String, Float> camRoll = new HashMap<String, Float>();
	
	@Override
	public void registerRenderInformation()
	{
//		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderCustomPlayer());
		
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderSnowball(Items.snowball, 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayPillar.class, new RenderDisplayPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new RenderCrystal());
		
		
		MinecraftForgeClient.registerItemRenderer(TFItems.purgesKatana, new RenderItemPurgesKatana());
		MinecraftForgeClient.registerItemRenderer(TFItems.skystrikesCrossbow, new RenderItemSkystrikesCrossbow());
		
		MinecraftForgeClient.registerItemRenderer(TFItems.displayVehicle, new RenderItemDisplayVehicle());
	}
	
	@Override
	public void registerTickHandler()
	{
		tickHandler = new ClientTickHandler();
	}
	
	@Override
	public void registerKeyBinds()
	{
		ClientRegistry.registerKeyBinding(ClientProxy.keyBindingBrake);
		ClientRegistry.registerKeyBinding(ClientProxy.keyBindingNitro);
		ClientRegistry.registerKeyBinding(ClientProxy.keyBindingTransform);
	}
	
	public ModelBiped getArmorModel(String string)
	{
		if (string.equalsIgnoreCase("Skystrike"))
		{
			return modelSkystrike;
		}
		if (string.equalsIgnoreCase("Purge"))
		{
			return modelPurge;
		}
		if (string.equalsIgnoreCase("Vurp"))
		{
			return modelVurp;
		}
		if (string.equalsIgnoreCase("Subwoofer"))
		{
			return modelSubwoofer;
		}
//		if (string.equalsIgnoreCase("Cloudtrap"))
//		{
//			return modelCloudtrap;
//		}
		return null;
	}
}