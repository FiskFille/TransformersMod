package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverEntry;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageConnectReceiver;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFVectorHelper;

@SideOnly(Side.CLIENT)
public class GuiSelectReceivers extends GuiScreen
{
    public TileEntity owner;
    public IEnergyTransmitter transmitter;

    public DimensionalCoords[] coordArray;
    public List<Integer> layers = Lists.newArrayList();

    public GuiVerticalHeightSlider heightSlider;

    public int spacing = 1;
    public int size = 3;

    public GuiSelectReceivers(TileEntity tile)
    {
        owner = tile;
        transmitter = (IEnergyTransmitter) owner;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        float range = transmitter.getRange();
        float boardWidth = 1 + range * 2;
        int boardWidthFl = MathHelper.floor_float(boardWidth);
        int baseX = MathHelper.floor_double(width / 2 - (spacing + size) * boardWidth / 2);
        int baseY = MathHelper.floor_double(height / 2 - (spacing + size) * boardWidth / 2);

        buttonList.add(new GuiButton(0, width / 2 - 100, height - height / 7, I18n.format("gui.done")));
        buttonList.add(heightSlider = new GuiVerticalHeightSlider(1, this, baseX + boardWidthFl * (spacing + size), baseY - 1, boardWidthFl * (spacing + size) + 1, new Runnable()
        {
            @Override
            public void run()
            {
                updateBlocks();
            }
        }));

        coordArray = new DimensionalCoords[boardWidthFl * boardWidthFl];
        layers.clear();
        layers.add(owner.yCoord);

        List<TileEntity> tiles = mc.theWorld.loadedTileEntityList;

        for (TileEntity loadedTile : tiles)
        {
            if (loadedTile instanceof IEnergyReceiver && ((IEnergyReceiver) loadedTile).canReceiveEnergy(owner) && TFEnergyHelper.isInRange(owner, loadedTile))
            {
                if (!layers.contains(loadedTile.yCoord))
                {
                    layers.add(loadedTile.yCoord);
                }
            }
        }

        Collections.sort(layers);

        heightSlider.enabled = layers.size() > 1;

        updateBlocks();
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        updateBlocks();
    }

    protected void updateBlocks()
    {
        float range = transmitter.getRange();
        float boardWidth = 1 + range * 2;
        int boardWidthFl = MathHelper.floor_float(boardWidth);
        int dimension = owner.getWorldObj().provider.dimensionId;

        coordArray = new DimensionalCoords[boardWidthFl * boardWidthFl];

        for (int i = 0; i < boardWidthFl; ++i)
        {
            for (int j = 0; j < boardWidthFl; ++j)
            {
                int x = MathHelper.floor_double(owner.xCoord - boardWidthFl / 2 + i);
                int z = MathHelper.floor_double(owner.zCoord - boardWidthFl / 2 + j);

                DimensionalCoords coords = new DimensionalCoords(x, getLayer(), z, dimension);

                if (TFEnergyHelper.isInRange(owner, coords))
                {
                    coordArray[i + j * boardWidthFl] = coords;
                }
            }
        }

        int direction = MathHelper.floor_double(mc.thePlayer.rotationYaw * 4F / 360F + 2.5D) & 3;

        if (direction > 0)
        {
            DimensionalCoords[] coordArray1 = coordArray.clone();

            for (int i = 0; i < boardWidthFl; ++i)
            {
                for (int j = 0; j < boardWidthFl; ++j)
                {
                    DimensionalCoords coords = coordArray[i + j * boardWidthFl];

                    if (direction == 1)
                    {
                        coords = coordArray[boardWidthFl - 1 - j + i * boardWidthFl];
                    }
                    else if (direction == 2)
                    {
                        coords = coordArray[boardWidthFl - 1 - i + (boardWidthFl - 1 - j) * boardWidthFl];
                    }
                    else if (direction == 3)
                    {
                        coords = coordArray[j + (boardWidthFl - 1 - i) * boardWidthFl];
                    }

                    coordArray1[i + j * boardWidthFl] = coords;
                }
            }

            coordArray = coordArray1;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            mc.thePlayer.closeScreen();
        }
    }

    @Override
    protected void keyTyped(char c, int key)
    {
        if (key == 1 || key == mc.gameSettings.keyBindInventory.getKeyCode())
        {
            mc.thePlayer.closeScreen();
        }
    }

    @Override
    public void onGuiClosed()
    {
//        TFNetworkManager.networkWrapper.sendToServer(new MessageSetReceivers(owner.xCoord, owner.yCoord, owner.zCoord, receivers));
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        if (button == 0)
        {
            int boardWidth = 1 + getRange() * 2;
            int baseX = MathHelper.floor_double(width / 2 - (spacing + size) * boardWidth / 2);
            int baseY = MathHelper.floor_double(height / 2 - (spacing + size) * boardWidth / 2);

            if (coordArray != null)
            {
                for (int i = 0; i < boardWidth; ++i)
                {
                    for (int j = 0; j < boardWidth; ++j)
                    {
                        int x = baseX + (spacing + size) * i;
                        int y = baseY + (spacing + size) * j;

//                        if (mouseX >= x && mouseX < x + size && mouseY >= y && mouseY < y + size)
                        if (new Rectangle(x, y, size, size).contains(mouseX, mouseY))
                        {
                            DimensionalCoords coords = coordArray[i + j * boardWidth];

                            if (coords != null && !coords.equals(new DimensionalCoords(owner)))
                            {
                                TileEntity tile = mc.theWorld.getTileEntity(coords.posX, coords.posY, coords.posZ);

                                if (tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canReceiveEnergy(owner) && !(tile instanceof IEnergyTransmitter && TFEnergyHelper.getDescendants((IEnergyTransmitter) tile).contains(new DimensionalCoords(owner))))
                                {
//                                    ReceiverHandler receiverHandler = TFEnergyHelper.getReceiverHandler(tile);
//                                    NetworkEntry entry = receiverHandler.getOwner();
//
//                                    if (receivers.contains(entry))
//                                    {
//                                        receivers.remove(entry);
//                                        receiverCoords.remove(entry.getCoords());
//                                    }
//                                    else
//                                    {
//                                        receivers.add(new ReceiverEntry(tile));
//                                        receiverCoords.add(entry.getCoords());
//                                    }

                                    TFNetworkManager.networkWrapper.sendToServer(new MessageConnectReceiver(new DimensionalCoords(owner), coords));
                                    mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

//    private boolean isGrandParent(DimensionalCoords coords)
//    {
//        return grandparents.contains(coords);
//    }

    public int getLayer()
    {
        float f = 1 - heightSlider.percentage;
        int amount = layers.size() - 1;

        for (int i = 0; i < layers.size(); ++i)
        {
            if (f >= (i - 0.5F) / amount && f < (i + 0.5F) / amount)
            {
                return layers.get(i);
            }
        }

        return owner.yCoord;
    }

    public int getRange()
    {
        return MathHelper.floor_float(transmitter.getRange());
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, I18n.format("gui.transmitter.select_receivers"), width / 2, 15, 16777215);

        if (coordArray == null || layers.isEmpty())
        {
            updateBlocks();
        }

        int boardWidth = 1 + getRange() * 2;
        int baseX = MathHelper.floor_double(width / 2 - (spacing + size) * boardWidth / 2);
        int baseY = MathHelper.floor_double(height / 2 - (spacing + size) * boardWidth / 2);

        if (coordArray != null)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            GL11.glColor4f(0.1F, 0.1F, 0.1F, 1);
            drawTexturedModalRect(baseX - spacing, baseY - spacing, 0, 0, MathHelper.floor_float((spacing + size) * boardWidth) + spacing, MathHelper.floor_float((spacing + size) * boardWidth) + spacing);
            Vec3 src = Vec3.createVectorHelper(size * getRange() - 0.5F, size * getRange() - 0.5F, 0);

            int maxWidth = 0;

            for (int i = 0; i < boardWidth; ++i)
            {
                for (int j = 0; j < boardWidth; ++j)
                {
                    DimensionalCoords coords = coordArray[i + j * boardWidth];

                    if (coords != null)
                    {
                        Vec3 src1 = Vec3.createVectorHelper(owner.xCoord + 0.5F, 0, owner.zCoord + 0.5F);
                        Vec3 dst = Vec3.createVectorHelper(coords.posX + 0.5F, 0, coords.posZ + 0.5F);
                        maxWidth = Math.max(maxWidth, MathHelper.floor_double(src1.distanceTo(dst)));
                    }
                }
            }

            maxWidth = 1 + maxWidth * 2;

            for (int i = 0; i < boardWidth; ++i)
            {
                for (int j = 0; j < boardWidth; ++j)
                {
                    Vec3 dst = Vec3.createVectorHelper(size * i, size * j, 0);
                    float opacity = MathHelper.clamp_float((1 - (float) src.distanceTo(dst) / maxWidth * 2 / size) * 2.5F, 0, 1);
                    int x = baseX + (spacing + size) * i;
                    int y = baseY + (spacing + size) * j;

                    GL11.glColor4f(0.075F, 0.075F, 0.075F, 1);
                    drawTexturedModalRect(x, y, 0, 0, size, size);

                    DimensionalCoords coords = coordArray[i + j * boardWidth];

                    if (coords != null)
                    {
                        if (!mc.theWorld.isAirBlock(coords.posX, coords.posY, coords.posZ))
                        {
                            float[] afloat = TFRenderHelper.hexToRGB(0x707070);

                            GL11.glColor4f(afloat[0], afloat[1], afloat[2], opacity);
                            drawTexturedModalRect(x, y, 0, 0, size, size);
                        }
                    }
                }
            }

            Set<DimensionalCoords> receiverCoords = new HashSet<DimensionalCoords>();

            for (ReceiverEntry entry : transmitter.getTransmissionHandler().getReceivers())
            {
                receiverCoords.add(entry.getCoords());
            }

            Tessellator tessellator = Tessellator.instance;
            float prevWidth = GL11.glGetFloat(GL11.GL_LINE_WIDTH);
            GL11.glLineWidth(size);
            GL11.glColor4f(0, 1, 1, 1);

            List<DimensionalCoords> coordList = Lists.newArrayList(coordArray);
//            float f = 1 - ((mc.thePlayer.ticksExisted + partialTicks) / 30) % 1;
            float f = 0.5F;
            float angle = 35;
            float length = 4;

            for (int i = 0; i < boardWidth; ++i)
            {
                for (int j = 0; j < boardWidth; ++j)
                {
                    DimensionalCoords coords = coordArray[i + j * boardWidth];
                    int x = baseX + (spacing + size) * i;
                    int y = baseY + (spacing + size) * j;

                    if (coords != null)
                    {
                        TileEntity tile = mc.theWorld.getTileEntity(coords.posX, coords.posY, coords.posZ);

                        if (tile instanceof IEnergyTransmitter && tile != owner)
                        {
                            IEnergyTransmitter transmitter1 = (IEnergyTransmitter) tile;
                            int color = 0x00FFFF;

                            if (tile instanceof IEnergyReceiver)
                            {
                                color = ((IEnergyReceiver) tile).getMapColor();
                            }

                            for (ReceiverEntry entry : transmitter1.getTransmissionHandler().getReceivers())
                            {
                                int index = coordList.indexOf(entry.getCoords());

                                if (index >= 0 && entry.getCoords().posY == getLayer())
                                {
                                    int k = index;
                                    int l = 0;

                                    for (l = 0; k >= boardWidth; ++l)
                                    {
                                        k -= boardWidth;
                                    }

                                    Vec3 vec3 = Vec3.createVectorHelper(x + (float) size / 2, y + (float) size / 2, 0);
                                    Vec3 vec31 = Vec3.createVectorHelper(baseX + (spacing + size) * k + (float) size / 2, baseY + (spacing + size) * l + (float) size / 2, 0);
                                    Vec3 vec32 = vec31.subtract(vec3);
                                    Vec3 vec33 = vec31.subtract(vec3);
                                    Vec3 vec34 = vec31.subtract(vec3);
                                    vec33.xCoord *= f;
                                    vec33.yCoord *= f;
                                    vec33 = TFVectorHelper.add(vec33, vec31);
                                    vec32.rotateAroundZ((float) Math.toRadians(angle));
                                    vec34.rotateAroundZ((float) Math.toRadians(-angle));
                                    vec32 = vec32.normalize();
                                    vec34 = vec34.normalize();
                                    vec32.xCoord *= length;
                                    vec32.yCoord *= length;
                                    vec34.xCoord *= length;
                                    vec34.yCoord *= length;
                                    vec32 = TFVectorHelper.add(vec32, vec33);
                                    vec34 = TFVectorHelper.add(vec34, vec33);

                                    tessellator.startDrawing(GL11.GL_LINE_STRIP);
                                    tessellator.setColorRGBA_I(0x00FFFF, 50);
                                    tessellator.addVertex(vec3.xCoord, vec3.yCoord, 0);
                                    tessellator.addVertex(vec31.xCoord, vec31.yCoord, 0);
                                    tessellator.draw();

                                    tessellator.startDrawing(GL11.GL_TRIANGLES);
                                    tessellator.setColorRGBA_I(color, 150);
                                    tessellator.addVertex(vec32.xCoord, vec32.yCoord, 0);
                                    tessellator.addVertex(vec33.xCoord, vec33.yCoord, 0);
                                    tessellator.addVertex(vec34.xCoord, vec34.yCoord, 0);
                                    tessellator.draw();
                                }
                            }
                        }
                    }
                }
            }

            for (DimensionalCoords coords : receiverCoords)
            {
                int index = coordList.indexOf(coords);

                if (index >= 0 && coords.posY == getLayer())
                {
                    int k = index;
                    int l = 0;

                    for (l = 0; k >= boardWidth; ++l)
                    {
                        k -= boardWidth;
                    }

                    Vec3 vec3 = Vec3.createVectorHelper(baseX + (spacing + size) * boardWidth / 2 - 0.5F, baseY + (spacing + size) * boardWidth / 2 - 0.5F, 0);
                    Vec3 vec31 = Vec3.createVectorHelper(baseX + (spacing + size) * k + (float) size / 2, baseY + (spacing + size) * l + (float) size / 2, 0);
                    Vec3 vec32 = vec31.subtract(vec3);
                    Vec3 vec33 = vec31.subtract(vec3);
                    Vec3 vec34 = vec31.subtract(vec3);
                    vec33.xCoord *= f;
                    vec33.yCoord *= f;
                    vec33 = TFVectorHelper.add(vec33, vec31);
                    vec32.rotateAroundZ((float) Math.toRadians(angle));
                    vec34.rotateAroundZ((float) Math.toRadians(-angle));
                    vec32 = vec32.normalize();
                    vec34 = vec34.normalize();
                    vec32.xCoord *= length;
                    vec32.yCoord *= length;
                    vec34.xCoord *= length;
                    vec34.yCoord *= length;
                    vec32 = TFVectorHelper.add(vec32, vec33);
                    vec34 = TFVectorHelper.add(vec34, vec33);

                    tessellator.startDrawing(GL11.GL_LINE_STRIP);
                    tessellator.setColorRGBA_I(0x00FFFF, 200);
                    tessellator.addVertex(vec3.xCoord, vec3.yCoord, 0);
                    tessellator.addVertex(vec31.xCoord, vec31.yCoord, 0);
                    tessellator.draw();

                    tessellator.startDrawing(GL11.GL_TRIANGLES);
                    tessellator.setColorRGBA_I(0x00FFFF, 150);
                    tessellator.addVertex(vec32.xCoord, vec32.yCoord, 0);
                    tessellator.addVertex(vec33.xCoord, vec33.yCoord, 0);
                    tessellator.addVertex(vec34.xCoord, vec34.yCoord, 0);
                    tessellator.draw();
                }
            }

            for (int i = 0; i < boardWidth; ++i)
            {
                for (int j = 0; j < boardWidth; ++j)
                {
                    Vec3 dst = Vec3.createVectorHelper(size * i, size * j, 0);
                    float opacity = MathHelper.clamp_float((1 - (float) src.distanceTo(dst) / boardWidth * 2 / size) * 2.5F, 0, 1);
                    int x = baseX + (spacing + size) * i;
                    int y = baseY + (spacing + size) * j;

                    DimensionalCoords coords = coordArray[i + j * boardWidth];

                    if (coords != null)
                    {
                        TileEntity tile = mc.theWorld.getTileEntity(coords.posX, coords.posY, coords.posZ);

                        if (receiverCoords.contains(coords) && spacing > 0)
                        {
                            GL11.glColor4f(0, 1, 1, opacity);
                            drawTexturedModalRect(x - spacing, y - spacing, 0, 0, size + spacing * 2, size + spacing * 2);
                        }

                        if (tile == owner)
                        {
                            GL11.glColor4f(0, 0.6F, 0, opacity);
                        }
                        else
                        {
                            if (tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canReceiveEnergy(owner))
                            {
                                float[] afloat = TFRenderHelper.hexToRGB(((IEnergyReceiver) tile).getMapColor());

                                if (tile instanceof IEnergyTransmitter && TFEnergyHelper.isPowering((IEnergyTransmitter) tile, owner))
                                {
                                    for (int k = 0; k < afloat.length; ++k)
                                    {
                                        afloat[k] *= 0.3F;
                                    }
                                }

                                GL11.glColor4f(afloat[0], afloat[1], afloat[2], opacity);
                            }
                            else if (tile instanceof IEnergyTransmitter && TFEnergyHelper.getDescendants((IEnergyTransmitter) tile).contains(new DimensionalCoords(owner)))
                            {
                                GL11.glColor4f(0.2F, 0, 0.2F, opacity);
                            }
                            else
                            {
                                continue;
                            }
                        }

                        drawTexturedModalRect(x, y, 0, 0, size, size);
                    }
                }
            }

            GL11.glLineWidth(prevWidth);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        if (layers.size() > 1)
        {
            for (int i = 0; i < layers.size(); i += layers.size() - 1)
            {
                float f = (float) i / (layers.size() - 1);
                drawString(mc.fontRenderer, layers.get(i) + "", heightSlider.xPosition + heightSlider.width + 3, heightSlider.yPosition + (int) ((1 - f) * (heightSlider.height - 8)), 0x4C4C4C);
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);

        int direction = MathHelper.floor_double(mc.thePlayer.rotationYaw * 4F / 360F + 2.5D) & 3;
        String[] dirs = {"north", "east", "south", "west"};
        String[] astring = new String[dirs.length];

        for (int i = 0; i < dirs.length; ++i)
        {
            astring[i] = I18n.format("direction." + dirs[(i + direction) % dirs.length] + ".short");
        }

        drawCenteredString(fontRendererObj, astring[0], baseX + (spacing + size) * boardWidth / 2, baseY - fontRendererObj.FONT_HEIGHT / 2, -1);
        drawCenteredString(fontRendererObj, astring[1], baseX + (spacing + size) * boardWidth, baseY + (spacing + size) * boardWidth / 2 - fontRendererObj.FONT_HEIGHT / 2, -1);
        drawCenteredString(fontRendererObj, astring[2], baseX + (spacing + size) * boardWidth / 2, baseY + (spacing + size) * boardWidth - fontRendererObj.FONT_HEIGHT / 2, -1);
        drawCenteredString(fontRendererObj, astring[3], baseX, baseY + (spacing + size) * boardWidth / 2 - fontRendererObj.FONT_HEIGHT / 2, -1);
    }
}
