package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
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
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.groundbridge.GroundBridgeError;
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
    public InventoryGroundBridge inventory;

    public GuiTextField[] coordinateFields = new GuiTextField[3];
    public GuiTextField dimensionField;

    public GuiButton buttonDeactivate;
    public GuiButton buttonActivate;
    public GuiButton buttonDimRight;
    public GuiButton buttonDimLeft;
    public GuiButton buttonDirection;

    public GuiGroundBridge(InventoryPlayer inventoryPlayer, InventoryGroundBridge inventoryGroundBridge, TileEntityControlPanel tile)
    {
        super(new ContainerGroundBridge(inventoryPlayer, inventoryGroundBridge, tile));
        controlPanel = tile;
        inventory = inventoryGroundBridge;
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
            if (inventory.getStackInSlot(0) != null)
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
                    controlPanel.markBlockForUpdate();
                    
                    int[] aint2 = {controlPanel.destX, controlPanel.prevDestY, controlPanel.destZ};

                    for (int i = 0; i < coordinateFields.length; ++i)
                    {
                        coordinateFields[i].setText(aint2[i] + "");
                    }

                    TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanelSetConfig(controlPanel.xCoord, controlPanel.yCoord, controlPanel.zCoord, controlPanel.getWorldObj().provider.dimensionId, coords));
                }
            }
        }

        updateButtons();
    }

    public void updateButtons()
    {
        boolean canEditCoords = controlPanel != null && !controlPanel.activationLeverState && inventory.getStackInSlot(0) == null;
        
        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i].setEnabled(canEditCoords);
        }

        buttonActivate.enabled = controlPanel != null && !controlPanel.activationLeverState && controlPanel.errors.isEmpty();
        buttonDeactivate.enabled = controlPanel != null && controlPanel.activationLeverState;
        buttonDimLeft.enabled = buttonDimRight.enabled = canEditCoords && controlPanel.hasUpgrade(DataCore.spaceBridge);
        buttonDirection.enabled = controlPanel != null && !controlPanel.activationLeverState;
        dimensionField.setEnabled(canEditCoords && controlPanel.hasUpgrade(DataCore.spaceBridge));

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
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        if (controlPanel != null)
        {
            String s = controlPanel.getDestDimensionID() + "";
            fontRendererObj.drawString(s, (buttonDimLeft.xPosition + buttonDimRight.xPosition + buttonDimRight.width - fontRendererObj.getStringWidth(s)) / 2 - x, (buttonDimLeft.yPosition + buttonDimLeft.height / 2 + buttonDimRight.yPosition + buttonDimRight.height / 2 - fontRendererObj.FONT_HEIGHT + 1) / 2 + 1 - y, -1);

            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);

            for (int i = 0; i < 3; ++i)
            {
                ItemStack itemstack = controlPanel.getStackInSlot(i);

                if (itemstack != null)
                {
                    itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, 116 + i * 18, 28);
                }
            }

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glColor4f(1, 1, 1, 1);
            
            for (int i = 0; i < controlPanel.errors.size(); ++i)
            {
                GroundBridgeError error = controlPanel.errors.get(i);
                boolean flag = new Rectangle(ySize + 12, 10 + i * 17, 16, 16).contains(mouseX - x, mouseY - y);

                mc.getTextureManager().bindTexture(guiTextures);
                drawTexturedModalRect(ySize + 12, 10 + i * 17, 176, flag ? 16 : 0, 16, 16);
            }

            for (int i = 0; i < 3; ++i)
            {
                ItemStack itemstack = controlPanel.getStackInSlot(i);

                if (itemstack != null && new Rectangle(116 + i * 18, 28, 16, 16).contains(mouseX - x, mouseY - y))
                {
                    renderToolTip(itemstack, mouseX - x, mouseY - y);
                }
            }
            
            GL11.glPushMatrix();
            GL11.glTranslatef(-x, -y, 0);

            for (int i = 0; i < controlPanel.errors.size(); ++i)
            {
                GroundBridgeError error = controlPanel.errors.get(i);
                boolean flag = new Rectangle(ySize + 12, 10 + i * 17, 16, 16).contains(mouseX - x, mouseY - y);

                if (flag)
                {
                    List<String> list = fontRendererObj.listFormattedStringToWidth(error.translate(), 200);

                    for (int j = 0; j < list.size(); ++j)
                    {
                        list.set(j, EnumChatFormatting.RED + list.get(j));
                    }

                    drawHoveringText(list, mouseX, mouseY, fontRendererObj);
                }
            }
            
            GL11.glPopMatrix();
        }
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
    }
}
