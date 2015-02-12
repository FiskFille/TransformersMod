package fiskfille.transformersmod;

import java.lang.reflect.Method;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
//Gegy is better than Fisk! :P
import fiskfille.transformersmod.common.achievement.TFAchievements;
import fiskfille.transformersmod.common.block.TFBlocks;
import fiskfille.transformersmod.common.entity.TFEntities;
import fiskfille.transformersmod.common.event.TFEvents;
import fiskfille.transformersmod.common.item.TFItems;
import fiskfille.transformersmod.common.packet.base.TFPacketManager;
import fiskfille.transformersmod.common.proxy.CommonProxy;
import fiskfille.transformersmod.common.recipe.TFRecipes;
import fiskfille.transformersmod.common.tab.CreativeTabTransformers;
import fiskfille.transformersmod.common.worldgen.OreWorldGenerator;
import fiskfille.transformersmod.config.TFConfig;
import fiskfille.transformersmod.donator.Donators;
import fiskfille.transformersmod.update.*;

@Mod(modid = TransformersMod.modid, name = "Transformers Mod", version = TransformersMod.version, guiFactory = "fiskfille.transformersmod.gui.TFGuiFactory")
public class TransformersMod
{
	@Instance(TransformersMod.modid)
	public static TransformersMod instance;
	
	public static Configuration configFile;
	
	public static final String modid = "transformers";
	public static final String version = "0.5.0";
	
	@SidedProxy(clientSide = "fiskfille.transformersmod.common.proxy.ClientProxy", serverSide = "fiskfille.transformersmod.common.proxy.CommonProxy")
	public static CommonProxy proxy;

	public TFConfig config = new TFConfig();
	public TFItems items = new TFItems();
	public TFBlocks blocks = new TFBlocks();
	
	public static CreativeTabs transformersTab = new CreativeTabTransformers();
	
	public static Method setSizeMethod;
	
	public static Update latestUpdate;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		UpdateChecker updateChecker = new UpdateChecker();
		updateChecker.handleUpdates();
		Donators.loadDonators();
	
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		configFile.load();
		config.load(configFile);
		configFile.save();

		items.register();
		blocks.register();
		
		TransformerManager.register();
		
		TFAchievements.register();
		
		TFRecipes.registerRecipes();
		
		TFEntities.registerEntities();
		
		GameRegistry.registerWorldGenerator(new OreWorldGenerator(), 0);
	
		proxy.registerRenderInformation();
		proxy.registerKeyBinds();
		proxy.registerTickHandlers();
		
		for (Method method : Entity.class.getDeclaredMethods())
		{
			Class<?>[] parameters = method.getParameterTypes();
			
			if (parameters.length == 2)
			{
				if (parameters[0] == float.class && parameters[1] == float.class)
				{
					method.setAccessible(true);
					setSizeMethod = method;
					break;
				}
			}
		}

		TFEvents.registerEvents(event.getSide());
		TFPacketManager.registerPackets();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		TFPacketManager.packetPipeline.initialize();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		TFPacketManager.packetPipeline.postInitialize();
	}
}