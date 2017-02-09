package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerGroundBridge;
import fiskfille.tf.common.container.InventoryGroundBridge;
import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.data.tile.TileDataControlPanel;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.groundbridge.GroundBridgeError.ErrorContainer;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.MessageControlPanelSetConfig;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFDimensionHelper;
import fiskfille.tf.helper.TFFormatHelper;
import fiskfille.tf.helper.TFTileHelper;

@SideOnly(Side.CLIENT)
public class GuiGroundBridge extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/ground_bridge.png");
    public InventoryGroundBridge inventory;

    public GuiTextField[] coordinateFields = new GuiTextField[3];
    public GuiTextField dimensionField;

    public GuiButton buttonDeactivate;
    public GuiButton buttonActivate;
    public GuiButton buttonDimRight;
    public GuiButton buttonDimLeft;
    public GuiButton buttonDirection;

    public DimensionalCoords tileCoords;
    public TileDataControlPanel data;

    public GuiGroundBridge(InventoryPlayer inventoryPlayer, InventoryGroundBridge inventoryGroundBridge, DimensionalCoords coords)
    {
        super(new ContainerGroundBridge(inventoryPlayer, inventoryGroundBridge));
        this.inventory = inventoryGroundBridge;
        this.tileCoords = coords;
        this.data = (TileDataControlPanel) TFTileHelper.getTileData(coords);
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        if (data == null)
        {
            return;
        }

        buttonList.add(buttonDeactivate = new GuiButtonFlat(0, x + 37, y + 66, 64, StatCollector.translateToLocal("ground_bridge_remote.ui.deactivate")));
        buttonList.add(buttonActivate = new GuiButtonFlat(1, x + 105, y + 66, 64, StatCollector.translateToLocal("ground_bridge_remote.ui.activate")));
        buttonList.add(buttonDimLeft = new GuiButtonFlat(2, x + 37, y + 49, 13, "<"));
        buttonList.add(buttonDimRight = new GuiButtonFlat(3, x + 64, y + 49, 13, ">"));
        buttonList.add(buttonDirection = new GuiButtonFlat(4, x + 156, y + 49, 13, ""));

        Keyboard.enableRepeatEvents(true);
        int[] destination = data.destination.toArray();

        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i] = new GuiTextFieldFlat(fontRendererObj, x + 7 + 55 * i, y + 7, 52);
            coordinateFields[i].setMaxStringLength(20);
            coordinateFields[i].setText(destination[i] + "");
        }

        dimensionField = new GuiTextFieldFlat(fontRendererObj, x + 81, y + 49, 71);

        updateButtons();
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        TileData newData = TFTileHelper.getTileData(tileCoords);

        if (newData instanceof TileDataControlPanel)
        {
            data = (TileDataControlPanel) newData;
        }
        else
        {
            mc.thePlayer.closeScreen();
            return;
        }

        int[] newDestination = data.destination.toArray();

        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i].updateCursorCounter();

            try
            {
                newDestination[i] = Integer.valueOf(coordinateFields[i].getText());
            }
            catch (Exception e)
            {
            }
        }

        if (inventory.getStackInSlot(0) != null)
        {
            int[] originalDestination = data.destination.toArray();

            for (int i = 0; i < coordinateFields.length; ++i)
            {
                coordinateFields[i].setText(originalDestination[i] + "");
            }
        }
        else if (!data.activationLeverState)
        {
            new DimensionalCoords();
            DimensionalCoords coords = DimensionalCoords.fromArray(newDestination);

            if (!data.destination.equals(coords))
            {
                coords.set(newDestination[0], newDestination[1], newDestination[2], newDestination[3]);
                data.destination.set(coords);

                for (int i = 0; i < coordinateFields.length; ++i)
                {
                    coordinateFields[i].setText(newDestination[i] + "");
                }

                TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanelSetConfig(data.getCoords(), coords));
            }
        }

        updateButtons();
    }

    public void updateButtons()
    {
        boolean activationLeverState = data.activationLeverState;
        boolean canEditCoords = data != null && !activationLeverState && inventory.getStackInSlot(0) == null;

        for (GuiTextField coordinateField : coordinateFields)
        {
            coordinateField.setEnabled(canEditCoords);
        }

        buttonActivate.enabled = data != null && !activationLeverState && data.errors.isEmpty();
        buttonDeactivate.enabled = data != null && activationLeverState;
        buttonDimLeft.enabled = buttonDimRight.enabled = canEditCoords && data.hasUpgrade(DataCore.spaceBridge);
        buttonDirection.enabled = data != null && !activationLeverState;
        dimensionField.setEnabled(canEditCoords && data.hasUpgrade(DataCore.spaceBridge));

        if (data != null)
        {
            String[] directions = {"north", "east", "south", "west"};
            buttonDirection.displayString = StatCollector.translateToLocal("ground_bridge.direction." + directions[data.direction % directions.length]);

            dimensionField.setText(TFDimensionHelper.getDimensionName(data.destination.dimension));
            dimensionField.setCursorPositionZero();
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0 || id == 1)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(tileCoords, mc.thePlayer, 14));
        }
        else if (id == 2 || id == 3)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(tileCoords, mc.thePlayer, 16 + id));
        }
        else if (id == 4)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(tileCoords, mc.thePlayer, 13));
        }

        updateButtons();
    }

    @Override
    protected void keyTyped(char c, int key)
    {
        super.keyTyped(c, key);

        for (GuiTextField coordinateField : coordinateFields)
        {
            coordinateField.textboxKeyTyped(c, key);
            String s = "";

            for (int k = 0; k < coordinateField.getText().length(); ++k)
            {
                char c1 = coordinateField.getText().charAt(k);

                if (Character.isDigit(c1) || k == 0 && c1 == '-')
                {
                    s += c1;
                }
            }

            if (!s.equals(coordinateField.getText()))
            {
                coordinateField.setText(s);
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        for (GuiTextField coordinateField : coordinateFields)
        {
            coordinateField.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        if (data == null)
        {
            return;
        }

        String destDimension = String.valueOf(data.destination.dimension);
        fontRendererObj.drawString(destDimension, (buttonDimLeft.xPosition + buttonDimRight.xPosition + buttonDimRight.width - fontRendererObj.getStringWidth(destDimension)) / 2 - x, (buttonDimLeft.yPosition + buttonDimLeft.height / 2 + buttonDimRight.yPosition + buttonDimRight.height / 2 - fontRendererObj.FONT_HEIGHT + 1) / 2 + 1 - y, -1);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);

        for (int i = 0; i < data.upgrades.size(); ++i)
        {
            DataCore core = data.upgrades.get(i);
            
            if (core != null)
            {
                itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), new ItemStack(TFItems.dataCore, 1, core.index), 116 + i * 18, 28);
            }
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1, 1, 1, 1);

        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(xSize + 2, 2, 192, 0, 16, 86);

        float energy = data.getEnergy();

        if (energy > 0)
        {
            float f = energy / data.getMaxEnergy();
            drawTexturedModalRect(xSize + 6, 6 + Math.round(78 * (1 - f)), 208, Math.round(78 * (1 - f)), 16, Math.round(78 * f));
        }

        for (int i = 0; i < data.errors.size(); ++i)
        {
            boolean flag = new Rectangle(xSize + 20, 10 + i * 17, 16, 16).contains(mouseX - x, mouseY - y);
            drawTexturedModalRect(xSize + 20, 10 + i * 17, 176, flag ? 16 : 0, 16, 16);
        }

        

        GL11.glPushMatrix();
        GL11.glTranslatef(-x, -y, 0);
        
        for (int i = 0; i < data.upgrades.size(); ++i)
        {
            DataCore core = data.upgrades.get(i);
            
            if (core != null && new Rectangle(115 + i * 18, 27, 18, 18).contains(mouseX - x, mouseY - y))
            {
                drawHoveringText(Arrays.asList(core.getTranslatedName()), mouseX, mouseY, fontRendererObj);
            }
        }

        if (new Rectangle(xSize + 2, 2, 16, 86).contains(mouseX - x, mouseY - y))
        {
            float usage = data.getEnergyUsage();
            String prefix = usage > 0 ? EnumChatFormatting.GREEN + "+" : usage < 0 ? EnumChatFormatting.RED + "-" : EnumChatFormatting.GRAY.toString();
            
            drawHoveringText(Arrays.asList(StatCollector.translateToLocalFormatted("gui.emb.storage", TFFormatHelper.formatNumber(data.getEnergy()), TFFormatHelper.formatNumber(data.getMaxEnergy())), prefix + StatCollector.translateToLocalFormatted("gui.emb.rate", TFFormatHelper.formatNumber(Math.abs(usage)) + EnumChatFormatting.GRAY)), mouseX, mouseY, fontRendererObj);
        }

        for (int i = 0; i < data.errors.size(); ++i)
        {
            ErrorContainer container = data.errors.get(i);

            if (new Rectangle(xSize + 20, 10 + i * 17, 16, 16).contains(mouseX - x, mouseY - y))
            {
                List<String> list = fontRendererObj.listFormattedStringToWidth(container.translate(), 200);

                for (int j = 0; j < list.size(); ++j)
                {
                    list.set(j, EnumChatFormatting.RED + list.get(j));
                }

                drawHoveringText(list, mouseX, mouseY, fontRendererObj);
            }
        }

        GL11.glPopMatrix();
        RenderHelper.enableGUIStandardItemLighting();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        for (GuiTextField coordinateField : coordinateFields)
        {
            coordinateField.drawTextBox();
        }

        dimensionField.drawTextBox();
    }
}
