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
import fiskfille.transformersmod.achievement.TFAchievements;
import fiskfille.transformersmod.block.TFBlocks;
import fiskfille.transformersmod.config.TFConfig;
import fiskfille.transformersmod.donator.Donators;
import fiskfille.transformersmod.entity.TFEntities;
import fiskfille.transformersmod.event.TFEvents;
import fiskfille.transformersmod.generator.OreWorldGenerator;
import fiskfille.transformersmod.item.TFItems;
import fiskfille.transformersmod.packet.TFPacketPipeline;
import fiskfille.transformersmod.packet.TFPackets;
import fiskfille.transformersmod.proxy.CommonProxy;
import fiskfille.transformersmod.recipe.TFRecipes;
import fiskfille.transformersmod.tab.CreativeTabTransformers;
import fiskfille.transformersmod.transformer.TransformerCloudtrap;
import fiskfille.transformersmod.transformer.TransformerPurge;
import fiskfille.transformersmod.transformer.TransformerSkystrike;
import fiskfille.transformersmod.transformer.TransformerSubwoofer;
import fiskfille.transformersmod.transformer.TransformerVurp;
import fiskfille.transformersmod.transformer.base.Transformer;
import fiskfille.transformersmod.update.Update;
import fiskfille.transformersmod.update.UpdateChecker;

@Mod(modid = TransformersMod.modid, name = "Transformers Mod", version = TransformersMod.version, guiFactory = "fiskfille.transformersmod.gui.TFGuiFactory")
public class TransformersMod
{
	@Instance(TransformersMod.modid)
	public static TransformersMod instance;
	
	public static Configuration configFile;
	
	public static final String modid = "transformers";
	public static final String version = "0.5.0";
	
	public static TFPacketPipeline packetPipeline;
	
	@SidedProxy(clientSide = "fiskfille.transformersmod.proxy.ClientProxy", serverSide = "fiskfille.transformersmod.proxy.CommonProxy")
	public static CommonProxy proxy;

	public TFConfig config = new TFConfig();
	public TFItems items = new TFItems();
	public TFBlocks blocks = new TFBlocks();
	
	public static CreativeTabs transformersTab = new CreativeTabTransformers();
	
	public static Method setSizeMethod;
	
	public static Update latestUpdate;
	
	public static Transformer transformerPurge = new TransformerPurge("Purge");
	public static Transformer transformerSkystrike = new TransformerSkystrike("Skystrike");
	public static Transformer transformerCloudtrap = new TransformerCloudtrap("Cloudtrap");
	public static Transformer transformerVurp = new TransformerVurp("Vurp");
	public static Transformer transformerSubwoofer = new TransformerSubwoofer("Subwoofer");
	
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
		
		TransformersAPI.registerTransformer(transformerCloudtrap);
		TransformersAPI.registerTransformer(transformerPurge);
		TransformersAPI.registerTransformer(transformerSkystrike);
		TransformersAPI.registerTransformer(transformerSubwoofer);
		TransformersAPI.registerTransformer(transformerVurp);
		
		items.register();
		blocks.register();
		
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