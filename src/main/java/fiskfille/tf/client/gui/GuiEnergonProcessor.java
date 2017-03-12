package fiskfille.tf.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.helper.TFFluidRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiEnergonProcessor extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/energon_processor.png");
    private TileEntityEnergonProcessor tileentity;
    
    private GuiHoverFieldFluid fieldFluid;

    public GuiEnergonProcessor(InventoryPlayer inventoryPlayer, TileEntityEnergonProcessor tile)
    {
        super(new ContainerEnergonProcessor(inventoryPlayer, tile));
        tileentity = tile;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        buttonList.add(fieldFluid = new GuiHoverFieldFluid(x + 77, y + 17, 52, 52, tileentity.data.tank));
        buttonList.add(new GuiButtonConfigRedstone(1, x + xSize - 18, y + 5, tileentity));
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        fieldFluid.update(tileentity.data.tank);
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 1)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, -tileentity.io.length - 2));
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = I18n.format(tileentity.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(guiTextures);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (tileentity.burnTime > 0)
        {
            int i = (int) (tileentity.burnTime * 0.12F);
            drawTexturedModalRect(x + 47, y + 35, 176, 14, i, 17);
        }

        if (tileentity.fillTime > 0)
        {
            int i = (int) (tileentity.fillTime * 0.13F);
            drawTexturedModalRect(x + 135, y + 36, 176, 31, i, 12);
        }

        if (tileentity.powerTime > 0)
        {
            int i = tileentity.powerTime * 13 / tileentity.currentMaxPowerTime;
            drawTexturedModalRect(x + 25, y + 48 - i, 176, 12 - i, 14, i + 2);
        }
        
        GL11.glEnable(GL11.GL_BLEND);
        TFFluidRenderHelper.renderIntoGUI(tileentity.getTank(), x + 79, y + 19, 48, 48, zLevel);
        GL11.glDisable(GL11.GL_BLEND);
        
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x + 77, y + 17, 204, 0, 52, 52);
    }
}
