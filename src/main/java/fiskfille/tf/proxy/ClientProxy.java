package fiskfille.tf.proxy;

import java.lang.reflect.Field;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.entity.EntityMissile;
import fiskfille.tf.entity.EntityTankShell;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.keybinds.TFKeyBinds;
import fiskfille.tf.model.player.ModelBipedTF;
import fiskfille.tf.model.transformer.ModelChildBase;
import fiskfille.tf.model.transformer.ModelCloudtrap;
import fiskfille.tf.model.transformer.ModelPurge;
import fiskfille.tf.model.transformer.ModelSkystrike;
import fiskfille.tf.model.transformer.ModelSubwoofer;
import fiskfille.tf.model.transformer.ModelVurp;
import fiskfille.tf.render.entity.RenderCustomPlayer;
import fiskfille.tf.render.entity.RenderMissile;
import fiskfille.tf.render.entity.RenderTankShell;
import fiskfille.tf.render.item.RenderItemDisplayVehicle;
import fiskfille.tf.render.item.RenderItemFlamethrower;
import fiskfille.tf.render.item.RenderItemPurgesKatana;
import fiskfille.tf.render.item.RenderItemSkystrikesCrossbow;
import fiskfille.tf.render.tileentity.RenderCrystal;
import fiskfille.tf.render.tileentity.RenderDisplayPillar;
import fiskfille.tf.tick.ClientTickHandler;
import fiskfille.tf.tileentity.TileEntityCrystal;
import fiskfille.tf.tileentity.TileEntityDisplayPillar;

public class ClientProxy extends CommonProxy
{
	private static ModelSkystrike modelSkystrike = new ModelSkystrike();
	private static ModelPurge modelPurge = new ModelPurge();
	private static ModelVurp modelVurp = new ModelVurp();
	private static ModelSubwoofer modelSubwoofer = new ModelSubwoofer();
	private static ModelCloudtrap modelCloudTrap = new ModelCloudtrap();
	public static ModelBiped modelBipedMain = new ModelBiped();
	
	public static Field camRollField;
	
	public static ModelChildBase.Biped getVurp()
	{
		return modelVurp;
	}
	
	public static ModelChildBase.Biped getSubwoofer()
	{
		return modelSubwoofer;
	}
	
	public static ModelChildBase.Biped getSkystrike()
	{
		return modelSkystrike;
	}

	public static ModelChildBase.Biped getCloudtrap()
	{
		return modelCloudTrap;
	}
	
	public static ModelChildBase.Biped getPurge()
	{
		return modelPurge;
	}
	
	@Override
	public void registerRenderInformation()
	{
		RenderCustomPlayer renderCustomPlayer = new RenderCustomPlayer();
		renderCustomPlayer.setRenderManager(RenderManager.instance);
		RenderManager.instance.entityRenderMap.put(EntityPlayer.class, renderCustomPlayer);
		
		int i = 0;
		for (Field curField : EntityRenderer.class.getDeclaredFields())
		{
			if (curField.getType() == float.class)
			{
				if (++i == 15)
				{
					camRollField = curField;
					curField.setAccessible(true);
				}
			}
		}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTankShell.class, new RenderTankShell());
		RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderMissile());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayPillar.class, new RenderDisplayPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new RenderCrystal());
		
		MinecraftForgeClient.registerItemRenderer(TFItems.purgesKatana, new RenderItemPurgesKatana());
		MinecraftForgeClient.registerItemRenderer(TFItems.skystrikesCrossbow, new RenderItemSkystrikesCrossbow());
		
		MinecraftForgeClient.registerItemRenderer(TFItems.displayVehicle, new RenderItemDisplayVehicle());
		MinecraftForgeClient.registerItemRenderer(TFItems.flamethrower, new RenderItemFlamethrower());
	}
	
	@Override
	public void registerTickHandler()
	{
		tickHandler = new ClientTickHandler();
	}
	
	@Override
	public void registerKeyBinds()
	{
		TFKeyBinds.register();
	}
	
	public ModelBiped getArmorModel(String string)
	{
		if (string.equalsIgnoreCase("Skystrike"))
		{
			return getSkystrike();
		}
		else if (string.equalsIgnoreCase("Purge"))
		{
			return getPurge();
		}
		else if (string.equalsIgnoreCase("Cloudtrap"))
		{
			return getCloudtrap();
		}
		else if (string.equalsIgnoreCase("Vurp"))
		{
			return getVurp();
		}
		else if (string.equalsIgnoreCase("Subwoofer"))
		{
			return getSubwoofer();
		}
		return null;
	}
}