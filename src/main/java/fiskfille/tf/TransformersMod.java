package fiskfille.tf;

import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.tf.asm.TFLoadingPlugin;
import net.minecraft.creativetab.CreativeTabs;
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
import fiskfille.tf.common.chunk.TFLoadingCallback;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.proxy.CommonProxy;
import fiskfille.tf.common.tab.CreativeTabTransformers;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.web.WebHelper;
import fiskfille.tf.web.update.Update;
import fiskfille.tf.web.update.UpdateChecker;

@Mod(modid = TransformersMod.modid, name = "Transformers Mod", version = TransformersMod.version, guiFactory = "fiskfille.tf.client.gui.TFGuiFactory")
public class TransformersMod
{
    public static final String modid = "transformers";
    public static final String version = "${version}";

    @Instance(TransformersMod.modid)
    public static TransformersMod instance;

    @SidedProxy(clientSide = "fiskfille.tf.common.proxy.ClientProxy", serverSide = "fiskfille.tf.common.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabTransformers = new CreativeTabTransformers();
    public static Update latestUpdate;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        if (!TFLoadingPlugin.loaded)
        {
            System.out.println("TransformersMod coremod not added! -Dfml.coreMods.load=fiskfille.tf.asm.TFLoadingPlugin");
            FMLCommonHandler.instance().exitJava(0, false);
        }

        try
        {
            WebHelper.readPastebin("Kyct1Dvz");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        TransformerManager.register();

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        TFConfig.load(config);

        if (config.hasChanged())
        {
            config.save();
        }

        if (TFConfig.checkForUpdates)
        {
            UpdateChecker updateChecker = new UpdateChecker();
            updateChecker.handleUpdates();
        }

        TFNetworkManager.registerPackets();
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();

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
