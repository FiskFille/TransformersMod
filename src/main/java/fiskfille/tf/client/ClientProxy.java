package fiskfille.tf.client;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.gui.GuiOverlay;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.render.entity.RenderMissile;
import fiskfille.tf.client.render.entity.RenderTankShell;
import fiskfille.tf.common.CommonProxy;
import fiskfille.tf.common.api.item.RegisterItemModel;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.entity.EntityTankShell;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    private static final Minecraft MC = Minecraft.getMinecraft();

    @Override
    public void onPreInit()
    {
        super.onPreInit();

        TFKeyBinds.register();
        TFModelRegistry.register();

        RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, RenderMissile::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTankShell.class, RenderTankShell::new);

        MinecraftForge.EVENT_BUS.register(new GuiOverlay());
    }

    @Override
    public void onRegisterModels()
    {
        super.onRegisterModels();

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

    @Override
    public float getRenderTick()
    {
        return ClientEventHandler.renderTick;
    }

    @Override
    public void schedule(Runnable runnable, MessageContext ctx)
    {
        if (ctx.side.isClient())
        {
            MC.addScheduledTask(runnable);
        }
        else
        {
            super.schedule(runnable, ctx);
        }
    }

    @Override
    public EntityPlayer getPlayer(MessageContext ctx)
    {
        if (ctx.side.isClient())
        {
            return MC.player;
        }

        return super.getPlayer(ctx);
    }
}
