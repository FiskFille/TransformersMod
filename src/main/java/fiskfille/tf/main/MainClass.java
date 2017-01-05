package fiskfille.tf.main;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
//Gegy is not as good as Fisk ;)
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.entity.EntityLaser;
import fiskfille.tf.generator.OreWorldGenerator;
import fiskfille.tf.gui.GuiHandlerTF;
import fiskfille.tf.gui.GuiOverlay;
import fiskfille.tf.main.misc.CommonProxy;
import fiskfille.tf.main.misc.CreativeTabTransformers;
import fiskfille.tf.main.misc.TickHandler;
import fiskfille.tf.server.TFPacketPipeline;

@Mod(modid = MainClass.modid, name = "Transformers Mod", version = MainClass.version, guiFactory = "fiskfille.tf.gui.TFGuiFactory")
public class MainClass
{
	@Instance(MainClass.modid)
	public static MainClass instance;
	public static Configuration configFile;
	public static final String modid = "transformers";
	public static final String version = "0.4";
	
	public static TFPacketPipeline packetPipeline;
	
	@SidedProxy(clientSide = "fiskfille.tf.main.misc.ClientProxy", serverSide = "fiskfille.tf.main.misc.CommonProxy")
	public static CommonProxy proxy;
	public TFConfig config = new TFConfig();
	public TFItems items = new TFItems();
	public TFBlocks blocks = new TFBlocks();
	public static CreativeTabs tabTransformers = new CreativeTabTransformers();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		configFile.load();
		config.load(configFile);
		configFile.save();
		
		items.load(config);
		blocks.load(config);
		TFRecipes.load();
		
//		EntityRegistry.registerModEntity(EntityLaser.class, "laser", 305, MainClass.modid, 1, 10, true);
		
		
		GameRegistry.registerWorldGenerator(new OreWorldGenerator(), 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerTF());
		proxy.registerRenderInformation();
		
		proxy.registerKeyBinds();
		proxy.registerTickHandler();
		
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
		FMLCommonHandler.instance().bus().register(new CommonEventHandler());
		FMLCommonHandler.instance().bus().register(new TickHandler());
		
		if (event.getSide().isClient())
		{
			MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
			FMLCommonHandler.instance().bus().register(new ClientEventHandler());
			MinecraftForge.EVENT_BUS.register(new GuiOverlay(Minecraft.getMinecraft()));
		}
		
		packetPipeline = new TFPacketPipeline();
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