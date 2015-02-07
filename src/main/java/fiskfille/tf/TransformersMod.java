package fiskfille.tf;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.achievement.TFAchievements;
import fiskfille.tf.block.TFBlocks;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.donator.Donators;
import fiskfille.tf.entity.TFEntities;
import fiskfille.tf.event.TFEvents;
import fiskfille.tf.generator.OreWorldGenerator;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.misc.CreativeTabTransformers;
import fiskfille.tf.packet.TFPacketPipeline;
import fiskfille.tf.packet.TFPackets;
import fiskfille.tf.proxy.CommonProxy;
import fiskfille.tf.recipe.TFRecipes;
import fiskfille.tf.transformer.*;
import fiskfille.tf.transformer.base.Transformer;
import fiskfille.tf.updatechecker.Update;
import fiskfille.tf.updatechecker.UpdateChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.config.Configuration;

import java.lang.reflect.Method;

@Mod(modid = TransformersMod.modid, name = "Transformers Mod", version = TransformersMod.version, guiFactory = "fiskfille.tf.gui.TFGuiFactory")
public class TransformersMod
{
	@Instance(TransformersMod.modid)
	public static TransformersMod instance;
	
	public static Configuration configFile;
	
	public static final String modid = "transformers";
	public static final String version = "${version}";
	
	public static TFPacketPipeline packetPipeline;
	
	@SidedProxy(clientSide = "fiskfille.tf.proxy.ClientProxy", serverSide = "fiskfille.tf.proxy.CommonProxy")
	public static CommonProxy proxy;

	public TFConfig config = new TFConfig();
	public TFItems items = new TFItems();
	public TFBlocks blocks = new TFBlocks();
	
	public static CreativeTabs transformersTab = new CreativeTabTransformers();
	
	public static Method setSizeMethod;
	
	public static Update latestUpdate;
	
	public static Transformer transformerPurge = new TransformerPurge();
	public static Transformer transformerSkystrike = new TransformerSkystrike();
	public static Transformer transformerCloudtrap = new TransformerCloudtrap();
	public static Transformer transformerVurp = new TransformerVurp();
	public static Transformer transformerSubwoofer = new TransformerSubwoofer();
	
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
		
		items.load();
		blocks.load();
		
		TFAchievements.register();
		
		TFRecipes.registerRecipes();
		
		TFEntities.registerEntities();
		
		GameRegistry.registerWorldGenerator(new OreWorldGenerator(), 0);
	
		proxy.registerRenderInformation();
		proxy.registerKeyBinds();
		proxy.registerTickHandler();
		
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
		TFPackets.registerPackets();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		packetPipeline.initialize();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		packetPipeline.postInitialize();
	}
}