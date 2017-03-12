package fiskfille.tf.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerTransmitter;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import fiskfille.tf.helper.TFFluidRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiTransmitter extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/transmitter.png");
    private TileEntityTransmitter tileentity;

    private GuiHoverFieldEnergy fieldEnergy;
    private GuiHoverFieldFluid fieldFluid;
    
    public GuiTransmitter(InventoryPlayer inventoryPlayer, TileEntityTransmitter tile)
    {
        super(new ContainerTransmitter(inventoryPlayer, tile));
        tileentity = tile;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        buttonList.add(fieldEnergy = new GuiHoverFieldEnergy(x + 107, y + 17, 16, 52, tileentity.data.storage));
        buttonList.add(fieldFluid = new GuiHoverFieldFluid(x + 77, y + 17, 20, 52, tileentity.data.tank));
        buttonList.add(new GuiButtonConfigSides(0, x + xSize - 18, y + 5));
        buttonList.add(new GuiButtonConfigRedstone(1, x + xSize - 18, y + 20, tileentity));
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        fieldEnergy.update(tileentity.data.storage);
        fieldFluid.update(tileentity.data.tank);
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            mc.displayGuiScreen(new GuiConfigSides(mc.thePlayer.inventory, this, tileentity));
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, -tileentity.io.length - 1));
        }
        else if (id == 1)
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
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (tileentity.getEnergy() > 0)
        {
            float f = tileentity.getEnergy() / tileentity.getMaxEnergy();
            drawTexturedModalRect(x + 107, y + 17 + Math.round(52 * (1 - f)), 196, Math.round(52 * (1 - f)), 16, Math.round(52 * f));
        }
        
        GL11.glEnable(GL11.GL_BLEND);
        TFFluidRenderHelper.renderIntoGUI(tileentity.getTank(), x + 80, y + 19, 16, 48, zLevel);
        GL11.glDisable(GL11.GL_BLEND);

        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x + 78, y + 17, 176, 0, 20, 52);
    }
}
