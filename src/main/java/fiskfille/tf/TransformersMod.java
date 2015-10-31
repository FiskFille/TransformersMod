package fiskfille.tf;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
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
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.proxy.CommonProxy;
import fiskfille.tf.common.recipe.TFRecipes;
import fiskfille.tf.common.tab.CreativeTabTransformers;
import fiskfille.tf.common.worldgen.OreWorldGenerator;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.web.WebHelper;
import fiskfille.tf.web.donator.Donators;
import fiskfille.tf.web.update.Update;
import fiskfille.tf.web.update.UpdateChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.config.Configuration;

import java.lang.reflect.Method;

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
    public TFItems items = new TFItems();
    public TFBlocks blocks = new TFBlocks();

    public static CreativeTabs tabTransformers = new CreativeTabTransformers();

    public static Method setSizeMethod;

    public static Update latestUpdate;

    public static Ticket ticket;

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
            Donators.loadDonators();
        }

        items.register();
        blocks.register();

        TFAchievements.register();
        TFRecipes.registerRecipes();
        TFEntities.registerEntities();
        TFEnergonManager.registerDefaultEnergonTypes();

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

        ForgeChunkManager.setForcedChunkLoadingCallback(this, new TFLoadingCallback());
//        ticket = ForgeChunkManager.requestTicket(this, world, type)
    }
}