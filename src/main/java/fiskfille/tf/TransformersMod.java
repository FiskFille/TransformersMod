package fiskfille.tf;

import fiskfille.tf.common.CommonProxy;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.sound.TFSounds;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
@Mod(modid = TransformersMod.MODID, name = "Transformers Mod", version = TransformersMod.VERSION)
public class TransformersMod
{
    public static final String MODID = "transformers";
    public static final String VERSION = "${version}";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Mod.Instance(TransformersMod.MODID)
    public static TransformersMod INSTANCE;

    @SidedProxy(serverSide = "fiskfille.tf.common.CommonProxy", clientSide = "fiskfille.tf.client.ClientProxy")
    public static CommonProxy PROXY;

    public static final CreativeTabs TRANSFORMERS_TAB = new CreativeTabs("transformers")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(TFBlocks.TRANSFORMIUM_ORE);
        }
    };

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        PROXY.onPreInit();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        PROXY.onInit();
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event)
    {
        PROXY.onPostInit();
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent<Block> event)
    {
        TFBlocks.register();
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent<Item> event)
    {
        TFItems.register();
    }

    @SubscribeEvent
    public static void onRegisterSounds(RegistryEvent<SoundEvent> event)
    {
        TFSounds.register();
    }

    @SubscribeEvent
    public static void onRegisterModels(ModelRegistryEvent event)
    {
        PROXY.onRegisterModels();
    }
}
