package fiskfille.tf;

import java.lang.reflect.Method;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.entity.TFEntities;
import fiskfille.tf.common.event.TFEvents;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.proxy.CommonProxy;
import fiskfille.tf.common.recipe.TFRecipes;
import fiskfille.tf.common.tab.CreativeTabTransformers;
import fiskfille.tf.common.worldgen.OreWorldGenerator;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.donator.Donators;
import fiskfille.tf.update.Update;
import fiskfille.tf.update.UpdateChecker;

//Gegy is better than Fisk! :P

@Mod(modid = TransformersMod.modid, name = "Transformers Mod", version = TransformersMod.version, guiFactory = "fiskfille.tf.client.gui.TFGuiFactory")
public class TransformersMod
{
	@Instance(TransformersMod.modid)
	public static TransformersMod instance;
	
	public static Configuration configFile;
	
	public static final String modid = "transformers";
	public static final String version = "0.5.0";
	
	@SidedProxy(clientSide = "fiskfille.tf.common.proxy.ClientProxy", serverSide = "fiskfille.tf.common.proxy.CommonProxy")
	public static CommonProxy proxy;

	public TFConfig config = new TFConfig();
	public TFItems items = new TFItems();
	public TFBlocks blocks = new TFBlocks();
	
	public static CreativeTabs tabTransformers = new CreativeTabTransformers();
	
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
}