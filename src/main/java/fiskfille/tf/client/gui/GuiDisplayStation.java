package fiskfille.tf.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
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
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiDisplayStation extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/display_station.png");
    private TileEntityDisplayStation tileentity;

    public GuiDisplayStation(InventoryPlayer inventoryPlayer, TileEntityDisplayStation tile)
    {
        super(new ContainerDisplayStation(inventoryPlayer, tile));
        tileentity = tile;
        ySize = 186;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        buttonList.add(new GuiButtonTransform(0, x + 140, y + 83));
        
        for (int i = 0; i < 2; ++i)
        {
            GuiButton button = new GuiButtonComponent(i + 1, x + 140, y + 17 + i * 18);
            button.enabled = getComponent(i) != null && getComponent(i).canLoad(tileentity, i);
            
            buttonList.add(button);
        }
        
        ((GuiButton) buttonList.get(0)).enabled = tileentity.canTransform();
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        ((GuiButton) buttonList.get(0)).enabled = tileentity.canTransform();
        
        for (int i = 0; i < 2; ++i)
        {
            ((GuiButton) buttonList.get(i + 1)).enabled = getComponent(i) != null && getComponent(i).canLoad(tileentity, i);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, 0));
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
        GuiInventory.func_147046_a(x + 63, y + 85, 30, x + 63 - mouseX, y + 85 - 50 - mouseY, mc.thePlayer);
        
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
                itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, x + 144, y + 63);
            }
            
            itemRender.renderWithColor = prevColor;
            GL11.glColor4f(1, 1, 1, 1);
            TFRenderHelper.finishRenderItemIntoGUI();
        }
    }
}
