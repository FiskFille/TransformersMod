package fiskfille.tf;

import fiskfille.tf.common.CommonProxy;
import fiskfille.tf.common.block.TFBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TransformersMod.MODID, name = "Transformers Mod", version = TransformersMod.VERSION)
public class TransformersMod
{
    public static final String MODID = "transformers";
    public static final String VERSION = "${version}";

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
}
