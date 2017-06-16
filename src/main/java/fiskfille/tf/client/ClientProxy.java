package fiskfille.tf.client;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.CommonProxy;
import fiskfille.tf.common.api.item.RegisterItemModel;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void onPreInit()
    {
        super.onPreInit();

        for (Block block : TFBlocks.REGISTERED_BLOCKS)
        {
            String name = block.getUnlocalizedName().substring("tile.".length());

            if (block instanceof RegisterItemModel)
            {
                this.registerModel(block, ((RegisterItemModel) block).getResource(name), "inventory");
            }

            if (block.getCreativeTabToDisplayOn() == null)
            {
                block.setCreativeTab(TransformersMod.TRANSFORMERS_TAB);
            }
        }

        for (Item item : TFItems.REGISTERED_ITEMS)
        {
            String name = item.getUnlocalizedName().substring("item.".length());

            if (item instanceof RegisterItemModel)
            {
                this.registerModel(item, ((RegisterItemModel) item).getResource(name), "inventory");
            }

            if (item.getCreativeTab() == null)
            {
                item.setCreativeTab(TransformersMod.TRANSFORMERS_TAB);
            }
        }
    }

    @Override
    public void onInit()
    {
        super.onInit();
    }

    @Override
    public void onPostInit()
    {
        super.onPostInit();
    }

    public void registerModel(Item item, String path, String type)
    {
        this.registerModel(item, 0, path, type);
    }

    public void registerModel(Item item, int meta, String path, String type)
    {
        ModelResourceLocation resource = new ModelResourceLocation(TransformersMod.MODID + ":" + path, type);
        ModelLoader.setCustomModelResourceLocation(item, meta, resource);
    }

    public void registerModel(Block block, int meta, String path, String type)
    {
        this.registerModel(Item.getItemFromBlock(block), meta, path, type);
    }

    public void registerModel(Block block, final String path, final String type)
    {
        this.registerModel(block, 0, path, type);
    }
}
