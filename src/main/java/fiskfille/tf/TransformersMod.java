package fiskfille.tf;

import java.lang.reflect.Method;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.client.displayable.TFDisplayableManager;
import fiskfille.tf.client.gui.GuiHandlerTF;
import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.chunk.TFLoadingCallback;
import fiskfille.tf.common.energon.TFEnergonManager;
import fiskfille.tf.common.entity.TFEntities;
import fiskfille.tf.common.event.TFEvents;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.proxy.CommonProxy;
import fiskfille.tf.common.recipe.TFRecipes;
import fiskfille.tf.common.tab.CreativeTabTransformers;
import fiskfille.tf.common.worldgen.OreWorldGenerator;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.web.WebHelper;
import fiskfille.tf.web.update.Update;
import fiskfille.tf.web.update.UpdateChecker;

@Mod(modid = TransformersMod.modid, name = "Transformers Mod", version = TransformersMod.version, guiFactory = "fiskfille.tf.client.gui.TFGuiFactory")
public class TransformersMod
{
    @Instance(TransformersMod.modid)
    public static TransformersMod instance;

    public static Configuration configFile;

    public static final String modid = "transformers";
    public static final String version = "${version}";

    @SidedProxy(clientSide = "fiskfille.tf.common.proxy.ClientProxy", serverSide = "fiskfille.tf.common.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static TFConfig config = new TFConfig();

    public static CreativeTabs tabTransformers = new CreativeTabTransformers();

    public static Method setSizeMethod;
    public static Update latestUpdate;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        try
        {
            WebHelper.readPastebin("fzntR5Vr");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        TransformerManager.register();

        configFile = new Configuration(event.getSuggestedConfigurationFile());
        configFile.load();
        config.load(configFile);

        if (configFile.hasChanged())
        {
            configFile.save();
        }

        if (TFConfig.checkForUpdates)
        {
            UpdateChecker updateChecker = new UpdateChecker();
            updateChecker.handleUpdates();
        }

        TFEnergonManager.registerEnergonTypes();
        TFItems.register();
        TFBlocks.register();
        TFAchievements.register();
        TFRecipes.registerRecipes();
        TFEntities.registerEntities();

        GameRegistry.registerWorldGenerator(new OreWorldGenerator(), 0);

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerTF());

        proxy.preInit();
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
        TFNetworkManager.registerPackets();
        TFDisplayableManager.registerDisplayables();
        TFFluids.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if (Loader.isModLoaded("Waila"))
        {
            FMLInterModComms.sendMessage("Waila", "register", "fiskfille.tf.waila.WailaRegistrar.wailaCallback");
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        ForgeChunkManager.setForcedChunkLoadingCallback(this, new TFLoadingCallback());
    }

    @EventHandler
    public void missingMappings(FMLMissingMappingsEvent event)
    {
        for (MissingMapping mapping : event.get())
        {
            remap(mapping, "transformium", TFItems.transformiumFragment);
            remap(mapping, "energon_crystal_piece", TFItems.energonCrystalShard);
            remap(mapping, "red_energon_crystal_piece", TFItems.redEnergonCrystalShard);
        }
    }

    private void remap(MissingMapping mapping, String name, Item item)
    {
        if (mapping.name.equals(modid + ":" + name))
        {
            mapping.remap(item);
        }
    }
}
