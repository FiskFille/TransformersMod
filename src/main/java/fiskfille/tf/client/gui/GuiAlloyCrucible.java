package fiskfille.tf.client.gui;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiIconFlat.IButtonRenderCallback;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.ContainerAlloyCrucible;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible.EnumSmeltingMode;

@SideOnly(Side.CLIENT)
public class GuiAlloyCrucible extends GuiContainerTF implements IButtonRenderCallback
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/alloy_crucible.png");
    private TileEntityAlloyCrucible tileentity;
    
    private GuiHoverFieldEnergy fieldEnergy;

    public GuiAlloyCrucible(InventoryPlayer inventoryPlayer, TileEntityAlloyCrucible tile)
    {
        super(new ContainerAlloyCrucible(inventoryPlayer, tile));
        tileentity = tile;
        ySize = 170;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        buttonList.add(fieldEnergy = new GuiHoverFieldEnergy(x + 49, y + 19, 16, 52, tileentity.data.storage));
        buttonList.add(new GuiIconFlat(0, x + 151, y + 5, this));
        buttonList.add(new GuiButtonConfigSides(1, x + xSize - 18, y + 27));
        buttonList.add(new GuiButtonConfigRedstone(2, x + xSize - 18, y + 42, tileentity));
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        fieldEnergy.update(tileentity.data.storage);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, 0));
        }
        else if (id == 1)
        {
            mc.displayGuiScreen(new GuiConfigSides(mc.thePlayer.inventory, this, tileentity));
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, -tileentity.io.length - 1));
        }
        else if (id == 2)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, -tileentity.io.length - 2));
        }
    }

    @Override
    public void render(GuiButton button, int mouseX, int mouseY)
    {
        int id = button.id;

        if (id == 0)
        {
            EnumSmeltingMode mode = tileentity.smeltingMode;
            IIcon alloyIcon = TFBlocks.alloyCrucible.getIcon(2, 2);
            IIcon furnaceIcon = Blocks.furnace.getIcon(2, 2);

            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

            switch (mode)
            {
            case ALLOY:
                drawTexturedModelRectFromIcon(2, 2, alloyIcon, 16, 16);
                break;
            case FURNACE:
                drawTexturedModelRectFromIcon(2, 2, furnaceIcon, 16, 16);
                break;
            default:
                Tessellator tessellator = Tessellator.instance;
                IIcon icon = alloyIcon;

                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(2, 18, zLevel, icon.getMinU(), icon.getMaxV());
                tessellator.addVertexWithUV(10, 18, zLevel, icon.getInterpolatedU(8), icon.getMaxV());
                tessellator.addVertexWithUV(10, 2, zLevel, icon.getInterpolatedU(8), icon.getMinV());
                tessellator.addVertexWithUV(2, 2, zLevel, icon.getMinU(), icon.getMinV());
                icon = furnaceIcon;
                tessellator.addVertexWithUV(10, 18, zLevel, icon.getInterpolatedU(8), icon.getMaxV());
                tessellator.addVertexWithUV(18, 18, zLevel, icon.getMaxU(), icon.getMaxV());
                tessellator.addVertexWithUV(18, 2, zLevel, icon.getMaxU(), icon.getMinV());
                tessellator.addVertexWithUV(10, 2, zLevel, icon.getInterpolatedU(8), icon.getMinV());
                tessellator.draw();
                break;
            }
        }
    }
    
    @Override
    public List<String> getHoverText(GuiButton button)
    {
        return Arrays.asList(I18n.format("gui.alloy_crucible.mode"), EnumChatFormatting.GRAY + I18n.format(String.format("gui.alloy_crucible.mode.%s", tileentity.smeltingMode.name().toLowerCase(Locale.ENGLISH))));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = I18n.format(tileentity.getInventoryName());
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

        if (tileentity.getEnergy() > 0)
        {
            float f = tileentity.getEnergy() / tileentity.getMaxEnergy();
            drawTexturedModalRect(x + 49, y + 19 + Math.round(52 * (1 - f)), 176, Math.round(52 * (1 - f)), 16, Math.round(52 * f));
        }

        if (tileentity.smeltTime > 0)
        {
            int progress = tileentity.getSmeltProgressScaled(14);
            drawTexturedModalRect(x + 107, y + 49 - progress + 15, 192, 12 - progress, 14, progress);
        }
    }
}
