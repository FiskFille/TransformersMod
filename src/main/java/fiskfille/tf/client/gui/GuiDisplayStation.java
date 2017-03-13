package fiskfille.tf.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.component.Component;
import fiskfille.tf.common.component.IComponent;
import fiskfille.tf.common.container.ContainerDisplayStation;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.MessageTransformDisplayStation;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiDisplayStation extends GuiContainer
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/display_station.png");
    private TileEntityDisplayStation tileentity;

    @Override
    public void initGui()
    {
        super.initGui();
        buttonList.add(new GuiButton(0, width / 2 - 50, height / 2 - 28, 70, 20, I18n.format("gui.display_station.transform")));
        buttonList.add(new GuiButtonAlt(1, width / 2 + 43, height / 2 - 73, 12, 12, ">"));
        buttonList.add(new GuiButtonAlt(2, width / 2 + 43, height / 2 - 73 + 18, 12, 12, ">"));

        ((GuiButton) buttonList.get(1)).enabled = getComponent(0) != null && getComponent(0).canLoad(tileentity, 0);
        ((GuiButton) buttonList.get(2)).enabled = getComponent(1) != null && getComponent(1).canLoad(tileentity, 1);
    }

    public GuiDisplayStation(InventoryPlayer inventoryPlayer, TileEntityDisplayStation tile)
    {
        super(new ContainerDisplayStation(inventoryPlayer, tile));
        tileentity = tile;
        ySize = 186;
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        ((GuiButton) buttonList.get(1)).enabled = getComponent(0) != null && getComponent(0).canLoad(tileentity, 0);
        ((GuiButton) buttonList.get(2)).enabled = getComponent(1) != null && getComponent(1).canLoad(tileentity, 1);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTransformDisplayStation(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord));
            TFNetworkManager.networkWrapper.sendToAll(new MessageTransformDisplayStation(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord));
        }
        else if (id == 1 || id == 2)
        {
            Component component = getComponent(id - 1);

            if (component != null)
            {
                component.load(tileentity, id - 1, mc.thePlayer);
            }
        }
    }

    public Component getComponent(int slot)
    {
        ItemStack itemstack = tileentity.getStackInSlot(4 + slot);

        if (itemstack != null && itemstack.getItem() instanceof IComponent)
        {
            IComponent icomponent = (IComponent) itemstack.getItem();
            return icomponent.getComponent();
        }

        return null;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = tileentity.hasCustomInventoryName() ? tileentity.getInventoryName() : I18n.format(tileentity.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        List<Transformer> list = Lists.newArrayList();
        
        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemstack = tileentity.getStackInSlot(i);

            if (itemstack != null)
            {
                Transformer transformer = TFHelper.getTransformerFromArmor(itemstack);
                
                if (transformer != null && !list.contains(transformer))
                {
                    list.add(transformer);
                }
            }
        }
        
        Transformer transformer = TransformersAPI.getTransformers().get((mc.thePlayer.ticksExisted / 20) % TransformersAPI.getTransformers().size());
        
        if (list.size() == 1)
        {
            transformer = list.get(0);
        }
        
        if (transformer != null)
        {
            Item[] items = {transformer.getHelmet(), transformer.getChestplate(), transformer.getLeggings(), transformer.getBoots()};
            boolean prevColor = itemRender.renderWithColor;
            
            TFRenderHelper.setupRenderItemIntoGUI();
            GL11.glColor4f(0.6F, 0.6F, 0.6F, 0.25F);
            itemRender.renderWithColor = false;
            
            for (int i = 0; i < 4; ++i)
            {
                if (tileentity.getStackInSlot(i) == null)
                {
                    itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), new ItemStack(items[i], 1, 0), x + 13, y + 18 + i * 18);
                }
            }
            
            if (tileentity.getStackInSlot(6) == null)
            {
                ItemStack itemstack = new ItemStack(TFItems.displayVehicle, 1, TransformersAPI.getTransformers().indexOf(transformer));
                itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, x + 75, y + 45);
            }
            
            itemRender.renderWithColor = prevColor;
            GL11.glColor4f(1, 1, 1, 1);
            TFRenderHelper.finishRenderItemIntoGUI();
        }
    }
}
