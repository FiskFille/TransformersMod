package fiskfille.tf.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerGroundBridge;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageControlPanel;
import fiskfille.tf.common.network.MessageControlPanelSetConfig;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFHelper;

@SideOnly(Side.CLIENT)
public class GuiGroundBridge extends GuiContainer
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/ground_bridge.png");
    public TileEntityControlPanel controlPanel;

    public GuiTextField[] coordinateFields = new GuiTextField[3];
    public GuiTextField dimensionField;

    public GuiButton buttonDeactivate;
    public GuiButton buttonActivate;
    public GuiButton buttonDimRight;
    public GuiButton buttonDimLeft;
    public GuiButton buttonDirection;

    public GuiGroundBridge(InventoryPlayer inventoryPlayer, TileEntityControlPanel tile)
    {
        super(new ContainerGroundBridge(inventoryPlayer, tile));
        controlPanel = tile;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        buttonList.add(buttonDeactivate = new GuiButtonFlat(0, x + 37, y + 66, 64, StatCollector.translateToLocal("ground_bridge_remote.ui.deactivate")));
        buttonList.add(buttonActivate = new GuiButtonFlat(1, x + 105, y + 66, 64, StatCollector.translateToLocal("ground_bridge_remote.ui.activate")));
        buttonList.add(buttonDimLeft = new GuiButtonFlat(2, x + 37, y + 49, 13, "<"));
        buttonList.add(buttonDimRight = new GuiButtonFlat(3, x + 64, y + 49, 13, ">"));
        buttonList.add(buttonDirection = new GuiButtonFlat(4, x + 156, y + 49, 13, ""));

        Keyboard.enableRepeatEvents(true);
        int[] aint = {controlPanel.destX, controlPanel.prevDestY, controlPanel.destZ};

        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i] = new GuiTextFieldFlat(fontRendererObj, x + 7 + 55 * i, y + 7, 52);
            coordinateFields[i].setMaxStringLength(20);
            coordinateFields[i].setText(aint[i] + "");
        }

        dimensionField = new GuiTextFieldFlat(fontRendererObj, x + 81, y + 49, 71);

        updateButtons();
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        DimensionalCoords coords = new DimensionalCoords();
        int[] aint = {controlPanel.destX, controlPanel.prevDestY, controlPanel.destZ};
        int[] aint1 = new int[aint.length];

        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i].updateCursorCounter();

            int j = aint[i];

            try
            {
                j = Integer.valueOf(coordinateFields[i].getText());
            }
            catch (Exception e)
            {
            }

            aint1[i] = j;
        }

        if (controlPanel != null)
        {
            if (controlPanel.csdInput.getStackInSlot(0) != null)
            {
                int[] aint2 = {controlPanel.destX, controlPanel.prevDestY, controlPanel.destZ};

                for (int i = 0; i < coordinateFields.length; ++i)
                {
                    coordinateFields[i].setText(aint2[i] + "");
                }
            }
            else if (!controlPanel.activationLeverState)
            {
                boolean flag = false;

                for (int i = 0; i < aint.length; ++i)
                {
                    if (aint[i] != aint1[i])
                    {
                        flag = true;
                    }
                }

                if (flag)
                {
                    coords.set(aint1[0], aint1[1], aint1[2], controlPanel.destDimIndex);
                    controlPanel.setSwitchesTo(coords);

                    int[] aint2 = {controlPanel.destX, controlPanel.prevDestY, controlPanel.destZ};

                    for (int i = 0; i < coordinateFields.length; ++i)
                    {
                        coordinateFields[i].setText(aint2[i] + "");
                    }

                    TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanelSetConfig(controlPanel.xCoord, controlPanel.yCoord, controlPanel.zCoord, coords));
                }
            }
        }

        updateButtons();
    }

    public void updateButtons()
    {
        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i].setEnabled(controlPanel != null && !controlPanel.activationLeverState);
        }

        buttonActivate.enabled = controlPanel != null && !controlPanel.activationLeverState && controlPanel.errors.isEmpty();
        buttonDeactivate.enabled = controlPanel != null && controlPanel.activationLeverState;
        buttonDimLeft.enabled = buttonDimRight.enabled = controlPanel != null && !controlPanel.activationLeverState && controlPanel.hasUpgrade(DataCore.spaceBridge);
        buttonDirection.enabled = controlPanel != null && !controlPanel.activationLeverState;
        dimensionField.setEnabled(controlPanel != null && !controlPanel.activationLeverState && controlPanel.hasUpgrade(DataCore.spaceBridge));

        if (controlPanel != null)
        {
            String[] astring = {"north", "east", "south", "west"};
            buttonDirection.displayString = StatCollector.translateToLocal("ground_bridge.direction." + astring[controlPanel.portalDirection % astring.length]);

            dimensionField.setText(TFHelper.getDimensionName(controlPanel.getDestDimensionID()));
            dimensionField.setCursorPositionZero();
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;
        int dimension = controlPanel.getWorldObj().provider.dimensionId;

        if (id == 0 || id == 1)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(mc.thePlayer, controlPanel.xCoord, controlPanel.yCoord, controlPanel.zCoord, dimension, 14));
        }
        else if (id == 2 || id == 3)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(mc.thePlayer, controlPanel.xCoord, controlPanel.yCoord, controlPanel.zCoord, dimension, 16 + id));
        }
        else if (id == 4)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(mc.thePlayer, controlPanel.xCoord, controlPanel.yCoord, controlPanel.zCoord, dimension, 13));
        }

        updateButtons();
    }

    @Override
    protected void keyTyped(char c, int key)
    {
        super.keyTyped(c, key);

        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i].textboxKeyTyped(c, key);

            String s = "";

            for (int k = 0; k < coordinateFields[i].getText().length(); ++k)
            {
                char c1 = coordinateFields[i].getText().charAt(k);

                if (Character.isDigit(c1) || k == 0 && c1 == '-')
                {
                    s += c1;
                }
            }

            if (!s.equals(coordinateFields[i].getText()))
            {
                coordinateFields[i].setText(s);
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i].mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i].drawTextBox();
        }

        dimensionField.drawTextBox();

        if (controlPanel != null)
        {
            String s = controlPanel.getDestDimensionID() + "";
            fontRendererObj.drawString(s, (buttonDimLeft.xPosition + buttonDimRight.xPosition + buttonDimRight.width - fontRendererObj.getStringWidth(s)) / 2, (buttonDimLeft.yPosition + buttonDimLeft.height / 2 + buttonDimRight.yPosition + buttonDimRight.height / 2 - fontRendererObj.FONT_HEIGHT + 1) / 2 + 1, -1);
        }
    }
}
