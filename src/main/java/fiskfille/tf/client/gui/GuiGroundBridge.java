package fiskfille.tf.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerGroundBridge;
import fiskfille.tf.common.container.InventoryGroundBridge;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.groundbridge.GroundBridgeError;
import fiskfille.tf.common.groundbridge.RemoteData;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageCloseRemote;
import fiskfille.tf.common.network.MessageControlPanel;
import fiskfille.tf.common.network.MessageControlPanelSetConfig;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFFormatHelper;
import fiskfille.tf.helper.TFHelper;
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

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;

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

    public RemoteData remoteData;

    public GuiGroundBridge(InventoryPlayer inventoryPlayer, InventoryGroundBridge inventoryGroundBridge, RemoteData remoteData)
    {
        super(new ContainerGroundBridge(inventoryPlayer, inventoryGroundBridge, null));
        this.inventory = inventoryGroundBridge;
        this.remoteData = remoteData;
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
        int[] destination = { remoteData.getDestinationX(), remoteData.getDestinationY(), remoteData.getDestinationZ() };

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

        remoteData.updateEnergy();

        DimensionalCoords coords = new DimensionalCoords();
        int[] originalDestination = { remoteData.getDestinationX(), remoteData.getDestinationY(), remoteData.getDestinationZ() };
        int[] newDestination = new int[originalDestination.length];

        for (int i = 0; i < coordinateFields.length; ++i)
        {
            coordinateFields[i].updateCursorCounter();

            int coord = originalDestination[i];

            try
            {
                coord = Integer.valueOf(coordinateFields[i].getText());
            }
            catch (Exception e)
            {
            }

            newDestination[i] = coord;
        }

        if (inventory.getStackInSlot(0) != null)
        {
            for (int i = 0; i < coordinateFields.length; ++i)
            {
                coordinateFields[i].setText(originalDestination[i] + "");
            }
        }
        else if (!remoteData.getActivationLeverState())
        {
            boolean updatedDestination = false;

            for (int i = 0; i < originalDestination.length; ++i)
            {
                if (originalDestination[i] != newDestination[i])
                {
                    updatedDestination = true;
                }
            }

            if (updatedDestination)
            {
                coords.set(newDestination[0], newDestination[1], newDestination[2], remoteData.getDestinationDimensionIndex());
                remoteData.setDestination(coords);

                for (int i = 0; i < coordinateFields.length; ++i)
                {
                    coordinateFields[i].setText(newDestination[i] + "");
                }

                TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanelSetConfig(remoteData.getX(), remoteData.getY(), remoteData.getZ(), remoteData.getSourceDimension(), coords));
            }
        }

        updateButtons();
    }

    public void updateButtons()
    {
        boolean activationLeverState = remoteData.getActivationLeverState();

        boolean canEditCoords = remoteData != null && !activationLeverState && inventory.getStackInSlot(0) == null;

        for (GuiTextField coordinateField : coordinateFields)
        {
            coordinateField.setEnabled(canEditCoords);
        }

        buttonActivate.enabled = remoteData != null && !activationLeverState && remoteData.getErrors().isEmpty();
        buttonDeactivate.enabled = remoteData != null && activationLeverState;
        buttonDimLeft.enabled = buttonDimRight.enabled = canEditCoords && remoteData.hasUpgrade(DataCore.spaceBridge);
        buttonDirection.enabled = remoteData != null && !activationLeverState;
        dimensionField.setEnabled(canEditCoords && remoteData.hasUpgrade(DataCore.spaceBridge));

        if (remoteData != null)
        {
            String[] directions = { "north", "east", "south", "west" };
            buttonDirection.displayString = StatCollector.translateToLocal("ground_bridge.direction." + directions[remoteData.getDirection() % directions.length]);

            dimensionField.setText(TFHelper.getDimensionName(remoteData.getDestinationDimension()));
            dimensionField.setCursorPositionZero();
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;
        int dimension = remoteData.getSourceDimension();

        int x = remoteData.getX();
        int y = remoteData.getY();
        int z = remoteData.getZ();

        if (id == 0 || id == 1)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(x, y, z, dimension, 14));
        }
        else if (id == 2 || id == 3)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(x, y, z, dimension, 16 + id));
        }
        else if (id == 4)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(x, y, z, dimension, 13));
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

        if (remoteData != null)
        {
            String destDimension = String.valueOf(remoteData.getDestinationDimension());
            fontRendererObj.drawString(destDimension, (buttonDimLeft.xPosition + buttonDimRight.xPosition + buttonDimRight.width - fontRendererObj.getStringWidth(destDimension)) / 2 - x, (buttonDimLeft.yPosition + buttonDimLeft.height / 2 + buttonDimRight.yPosition + buttonDimRight.height / 2 - fontRendererObj.FONT_HEIGHT + 1) / 2 + 1 - y, -1);

            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);

            for (int i = 0; i < 3; ++i)
            {
                ItemStack stack = remoteData.getStackInSlot(i);

                if (stack != null)
                {
                    itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), stack, 116 + i * 18, 28);
                }
            }

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glColor4f(1, 1, 1, 1);

            mc.getTextureManager().bindTexture(guiTextures);
            drawTexturedModalRect(xSize + 2, 2, 192, 0, 16, 86);

            float energy = remoteData.getEnergy();

            if (energy > 0)
            {
                float f = energy / remoteData.getMaxEnergy();
                drawTexturedModalRect(xSize + 6, 6 + Math.round(78 * (1 - f)), 208, Math.round(78 * (1 - f)), 16, Math.round(78 * f));
            }

            for (int i = 0; i < remoteData.getErrors().size(); ++i)
            {
                boolean flag = new Rectangle(xSize + 20, 10 + i * 17, 16, 16).contains(mouseX - x, mouseY - y);

                drawTexturedModalRect(xSize + 20, 10 + i * 17, 176, flag ? 16 : 0, 16, 16);
            }

            for (int i = 0; i < 3; ++i)
            {
                ItemStack stack = remoteData.getStackInSlot(i);

                if (stack != null && new Rectangle(115 + i * 18, 27, 18, 18).contains(mouseX - x, mouseY - y))
                {
                    renderToolTip(stack, mouseX - x, mouseY - y);
                }
            }

            GL11.glPushMatrix();
            GL11.glTranslatef(-x, -y, 0);

            if (new Rectangle(xSize + 2, 2, 16, 86).contains(mouseX - x, mouseY - y))
            {
                drawHoveringText(Collections.singletonList(StatCollector.translateToLocalFormatted("gui.emb.storage", TFFormatHelper.formatNumber(remoteData.getEnergy()), TFFormatHelper.formatNumber(remoteData.getMaxEnergy()))), mouseX, mouseY, fontRendererObj);
            }

            for (int i = 0; i < remoteData.getErrors().size(); ++i)
            {
                GroundBridgeError error = remoteData.getErrors().get(i);

                if (new Rectangle(xSize + 20, 10 + i * 17, 16, 16).contains(mouseX - x, mouseY - y))
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

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();

        TFNetworkManager.networkWrapper.sendToServer(new MessageCloseRemote(remoteData.getX(), remoteData.getY(), remoteData.getZ(), remoteData.getSourceDimension()));
    }

    public void update(RemoteData data)
    {
        this.remoteData = data;
    }
}
