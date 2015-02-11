package fiskfille.transformersmod.proxy;

import java.lang.reflect.Field;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.client.model.transformer.ModelCloudtrap;
import fiskfille.transformersmod.client.model.transformer.ModelPurge;
import fiskfille.transformersmod.client.model.transformer.ModelSkystrike;
import fiskfille.transformersmod.client.model.transformer.ModelSubwoofer;
import fiskfille.transformersmod.client.model.transformer.ModelVurp;
import fiskfille.transformersmod.client.model.transformer.TFModelRegistry;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelSkystrikeVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelSubwooferVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelVurpVehicle;
import fiskfille.transformersmod.client.render.entity.RenderCustomPlayer;
import fiskfille.transformersmod.client.render.entity.RenderMiniMissile;
import fiskfille.transformersmod.client.render.entity.RenderMissile;
import fiskfille.transformersmod.client.render.entity.RenderTankShell;
import fiskfille.transformersmod.client.render.item.RenderItemDisplayVehicle;
import fiskfille.transformersmod.client.render.item.RenderItemFlamethrower;
import fiskfille.transformersmod.client.render.item.RenderItemPurgesKatana;
import fiskfille.transformersmod.client.render.item.RenderItemSkystrikesCrossbow;
import fiskfille.transformersmod.client.render.item.RenderItemVurpsSniper;
import fiskfille.transformersmod.client.render.tileentity.RenderCrystal;
import fiskfille.transformersmod.client.render.tileentity.RenderDisplayPillar;
import fiskfille.transformersmod.entity.EntityMiniMissile;
import fiskfille.transformersmod.entity.EntityMissile;
import fiskfille.transformersmod.entity.EntityTankShell;
import fiskfille.transformersmod.item.TFItems;
import fiskfille.transformersmod.keybinds.TFKeyBinds;
import fiskfille.transformersmod.tick.ClientTickHandler;
import fiskfille.transformersmod.tileentity.TileEntityCrystal;
import fiskfille.transformersmod.tileentity.TileEntityDisplayPillar;

public class ClientProxy extends CommonProxy
{
	public static Field camRollField;
	
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
		RenderingRegistry.registerEntityRenderingHandler(EntityMiniMissile.class, new RenderMiniMissile());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayPillar.class, new RenderDisplayPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new RenderCrystal());
		
		MinecraftForgeClient.registerItemRenderer(TFItems.purgesKatana, new RenderItemPurgesKatana());
		MinecraftForgeClient.registerItemRenderer(TFItems.skystrikesCrossbow, new RenderItemSkystrikesCrossbow());
		
		MinecraftForgeClient.registerItemRenderer(TFItems.displayVehicle, new RenderItemDisplayVehicle());
		MinecraftForgeClient.registerItemRenderer(TFItems.flamethrower, new RenderItemFlamethrower());
		MinecraftForgeClient.registerItemRenderer(TFItems.vurpsSniper, new RenderItemVurpsSniper());
		
		TFModelRegistry.registerModel(TransformersMod.transformerCloudtrap, new ModelCloudtrap(), new ModelCloudtrapVehicle());
		TFModelRegistry.registerModel(TransformersMod.transformerPurge, new ModelPurge(), new ModelPurgeVehicle());
		TFModelRegistry.registerModel(TransformersMod.transformerSkystrike, new ModelSkystrike(), new ModelSkystrikeVehicle());
		TFModelRegistry.registerModel(TransformersMod.transformerSubwoofer, new ModelSubwoofer(), new ModelSubwooferVehicle());
		TFModelRegistry.registerModel(TransformersMod.transformerVurp, new ModelVurp(), new ModelVurpVehicle());
	}
	
	@Override
	public void registerTickHandlers()
	{
		tickHandler = new ClientTickHandler();
	}
	
	@Override
	public void registerKeyBinds()
	{
		TFKeyBinds.register();
	}
}