package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;
import cpw.mods.fml.common.Optional;
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
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTileHelper;

@SideOnly(Side.CLIENT)
@Optional.Interface(iface = "codechicken.nei.api.INEIGuiHandler", modid = "NotEnoughItems")
public class GuiGroundBridge extends GuiContainerTF implements INEIGuiHandler
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

    private GuiHoverFieldEnergy fieldEnergy;

    public DimensionalCoords tileCoords;
    public TileDataControlPanel data;

    public GuiGroundBridge(InventoryPlayer inventoryPlayer, InventoryGroundBridge inventoryGroundBridge, DimensionalCoords coords)
    {
        super(new ContainerGroundBridge(inventoryPlayer, inventoryGroundBridge));
        inventory = inventoryGroundBridge;
        tileCoords = coords;
        data = (TileDataControlPanel) TFTileHelper.getTileData(coords);
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

        buttonList.add(fieldEnergy = new GuiHoverFieldEnergy(x + xSize + 2, y + 2, 16, 86, data.storage));
        buttonList.add(buttonDeactivate = new GuiButtonFlat(0, x + 37, y + 66, 64, I18n.format("ground_bridge_remote.ui.deactivate")));
        buttonList.add(buttonActivate = new GuiButtonFlat(1, x + 105, y + 66, 64, I18n.format("ground_bridge_remote.ui.activate")));
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

        fieldEnergy.update(data.storage);
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
            buttonDirection.displayString = I18n.format("direction." + directions[data.direction % directions.length] + ".short");

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

        TFRenderHelper.setupRenderItemIntoGUI();

        for (int i = 0; i < data.upgrades.size(); ++i)
        {
            DataCore core = data.upgrades.get(i);

            if (core != null)
            {
                TFRenderHelper.renderItemIntoGUI(116 + i * 18, 28, new ItemStack(TFItems.dataCore, 1, core.index));
            }
        }

        TFRenderHelper.finishRenderItemIntoGUI();

//        RenderHelper.enableGUIStandardItemLighting();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        drawTexturedModalRect(x + xSize + 2, y + 2, 192, 0, 16, 86);
        float energy = data.getEnergy();

        if (energy > 0)
        {
            float f = energy / data.getMaxEnergy();
            drawTexturedModalRect(x + xSize + 6, y + 6 + Math.round(78 * (1 - f)), 208, Math.round(78 * (1 - f)), 8, Math.round(78 * f));
        }

        for (int i = 0; i < data.errors.size(); ++i)
        {
            boolean flag = new Rectangle(x + xSize + 20, y + 10 + i * 17, 16, 16).contains(mouseX, mouseY);
            drawTexturedModalRect(x + xSize + 20, y + 10 + i * 17, 176, flag ? 16 : 0, 16, 16);
        }

        for (GuiTextField coordinateField : coordinateFields)
        {
            coordinateField.drawTextBox();
        }

        dimensionField.drawTextBox();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        for (int i = 0; i < data.upgrades.size(); ++i)
        {
            DataCore core = data.upgrades.get(i);

            if (core != null && new Rectangle(x + 115 + i * 18, y + 27, 18, 18).contains(mouseX, mouseY))
            {
                drawHoveringText(Arrays.asList(core.getTranslatedName()), mouseX, mouseY, fontRendererObj);
            }
        }

        for (int i = 0; i < data.errors.size(); ++i)
        {
            ErrorContainer container = data.errors.get(i);

            if (new Rectangle(x + xSize + 20, y + 10 + i * 17, 16, 16).contains(mouseX, mouseY))
            {
                List<String> list = fontRendererObj.listFormattedStringToWidth(container.translate(), 200);

                for (int j = 0; j < list.size(); ++j)
                {
                    list.set(j, EnumChatFormatting.RED + list.get(j));
                }

                drawHoveringText(list, mouseX, mouseY, fontRendererObj);
            }
        }
    }

    @Override
    public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility)
    {
        if (width - xSize < 107)
        {
            currentVisibility.showWidgets = false;
        }
        else
        {
            currentVisibility.showWidgets = true;
        }

        if (guiLeft < 58)
        {
            currentVisibility.showStateButtons = false;
        }

        return currentVisibility;
    }

    @Override
    public Iterable<Integer> getItemSpawnSlots(GuiContainer gui, ItemStack item)
    {
        return null;
    }

    @Override
    public List<TaggedInventoryArea> getInventoryAreas(GuiContainer gui)
    {
        return Collections.emptyList();
    }

    @Override
    public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button)
    {
        return false;
    }

    @Override
    public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h)
    {
        Rectangle slot = new Rectangle(x, y, w, h);
        Rectangle bounds = new Rectangle(guiLeft + xSize, guiTop, 18, 88);
        
        slot.grow(4, 4);
        
        if (!data.errors.isEmpty())
        {
            bounds.width += 18;
        }
        
        if (slot.intersects(bounds))
        {
            return true;
        }
        
        return false;
    }
}
