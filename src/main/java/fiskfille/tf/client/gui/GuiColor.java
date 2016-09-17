package fiskfille.tf.client.gui;

import java.awt.Color;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.network.MessageColorArmor;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiColor extends GuiScreen
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/display_station.png");
    private TileEntityDisplayStation tileentity;

    public static int ticks;
    public static boolean fromPresetMenu = false;

    public static int layerSelected;
    public static float[][] layerColors = {{1, 1, 1}, {1, 1, 1}};

    public static GuiColorSlider sliderRed;
    public static GuiColorSlider sliderGreen;
    public static GuiColorSlider sliderBlue;
    public static GuiTextField inputField;

    public void initGui()
    {
        super.initGui();
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 6 + 168, I18n.format("gui.done", new Object[0])));
        buttonList.add(sliderRed = new GuiColorSlider(1, width / 2 - 22, height / 6, 0, StatCollector.translateToLocal("gui.display_station.color.red")));
        buttonList.add(sliderGreen = new GuiColorSlider(2, width / 2 - 22, height / 6 + 21, 1, StatCollector.translateToLocal("gui.display_station.color.green")));
        buttonList.add(sliderBlue = new GuiColorSlider(3, width / 2 - 22, height / 6 + 42, 2, StatCollector.translateToLocal("gui.display_station.color.blue")));
        buttonList.add(new GuiButton(4, width / 2 + 42, height / 6 + 63, 86, 20, StatCollector.translateToLocal("gui.display_station.color.presets")));
        buttonList.add(new GuiButton(5, width / 2 + 112, height / 6 + 84, 25, 20, "<->"));


        ItemStack head = tileentity.getStackInSlot(0).copy();
        ItemStack chest = tileentity.getStackInSlot(1).copy();
        ItemStack legs = tileentity.getStackInSlot(2).copy();
        ItemStack feet = tileentity.getStackInSlot(3).copy();

        if (TFArmorDyeHelper.isDyed(head) && !fromPresetMenu)
        {
            Color primary = new Color(TFArmorDyeHelper.getPrimaryColor(head));
            Color secondary = new Color(TFArmorDyeHelper.getSecondaryColor(head));

            layerColors[0][0] = (float) primary.getRed() / 255;
            layerColors[0][1] = (float) primary.getGreen() / 255;
            layerColors[0][2] = (float) primary.getBlue() / 255;
            layerColors[1][0] = (float) secondary.getRed() / 255;
            layerColors[1][1] = (float) secondary.getGreen() / 255;
            layerColors[1][2] = (float) secondary.getBlue() / 255;
        }

        fromPresetMenu = false;
        sliderRed.percentage = layerColors[layerSelected][0];
        sliderGreen.percentage = layerColors[layerSelected][1];
        sliderBlue.percentage = layerColors[layerSelected][2];

        Keyboard.enableRepeatEvents(true);
        inputField = new GuiTextField(fontRendererObj, width / 2 - 21, height / 6 + 64, 61, 17);
        inputField.setMaxStringLength(20);
    }

    public GuiColor(TileEntityDisplayStation tile)
    {
        tileentity = tile;
    }

    public void updateScreen()
    {
        super.updateScreen();
        inputField.updateCursorCounter();
        ++ticks;

        layerColors[layerSelected][0] = sliderRed.percentage;
        layerColors[layerSelected][1] = sliderGreen.percentage;
        layerColors[layerSelected][2] = sliderBlue.percentage;

        if (!inputField.isFocused())
        {
            Color color = new Color(sliderRed.percentage, sliderGreen.percentage, sliderBlue.percentage);
            inputField.setText(Integer.toHexString(color.getRGB()));
        }
        else
        {
            try
            {
                String s = inputField.getText();
                int i = (int) Long.parseLong(s, 16);
                Color color = new Color(i);
                sliderRed.percentage = (float) color.getRed() / 255;
                sliderGreen.percentage = (float) color.getGreen() / 255;
                sliderBlue.percentage = (float) color.getBlue() / 255;
            }
            catch (Exception e)
            {
            }
        }

        sliderRed.prevPercentage = sliderRed.percentage;
        sliderGreen.prevPercentage = sliderGreen.percentage;
        sliderBlue.prevPercentage = sliderBlue.percentage;
    }

    public void updateSliders()
    {

    }

    protected void keyTyped(char c, int key)
    {
        if (key == 1)
        {
            mc.displayGuiScreen((GuiScreen) null);
        }
        else
        {
            inputField.textboxKeyTyped(c, key);
        }
    }

    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            Color primary = new Color(layerColors[0][0], layerColors[0][1], layerColors[0][2]);
            Color secondary = new Color(layerColors[1][0], layerColors[1][1], layerColors[1][2]);
            TFNetworkManager.networkWrapper.sendToServer(new MessageColorArmor(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, primary.getRGB(), secondary.getRGB()));
            TFNetworkManager.networkWrapper.sendToAll(new MessageColorArmor(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, primary.getRGB(), secondary.getRGB()));

            mc.displayGuiScreen(null);
        }
        else if (id == 4)
        {
            mc.displayGuiScreen(new GuiColorPresets(tileentity, this));
        }
        else if (id == 5)
        {
            float[] afloat = layerColors[0];
            layerColors[0] = layerColors[1];
            layerColors[1] = afloat;
            sliderRed.percentage = layerColors[layerSelected][0];
            sliderGreen.percentage = layerColors[layerSelected][1];
            sliderBlue.percentage = layerColors[layerSelected][2];
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        if (button == 0)
        {
            for (int i = 0; i < 2; ++i)
            {
                int x = width / 2 - 21 + 67 * i;
                int y = height / 6 + 85;

                if (mouseX >= x && mouseX < x + 64 && mouseY >= y && mouseY < y + 64)
                {
                    layerSelected = i;
                    sliderRed.percentage = layerColors[layerSelected][0];
                    sliderGreen.percentage = layerColors[layerSelected][1];
                    sliderBlue.percentage = layerColors[layerSelected][2];
                }
            }
        }

        inputField.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int button, long timeSinceMouseClick)
    {
        super.mouseClickMove(mouseX, mouseY, button, timeSinceMouseClick);
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        inputField.drawTextBox();
        drawCenteredString(fontRendererObj, StatCollector.translateToLocal("gui.display_station.color"), width / 2, 15, 16777215);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(0, 0, 0, 0.4F);
        drawTexturedModalRect(width / 2 - 128, height / 6, 0, 0, 100, 150);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        EntityClientPlayerMP entity = tileentity.fakePlayer;
        ItemStack head = tileentity.getStackInSlot(0).copy();
        ItemStack chest = tileentity.getStackInSlot(1).copy();
        ItemStack legs = tileentity.getStackInSlot(2).copy();
        ItemStack feet = tileentity.getStackInSlot(3).copy();

        Color primary = new Color(layerColors[0][0], layerColors[0][1], layerColors[0][2]);
        Color secondary = new Color(layerColors[1][0], layerColors[1][1], layerColors[1][2]);
        TFArmorDyeHelper.setPrimaryColor(head, primary.getRGB());
        TFArmorDyeHelper.setPrimaryColor(chest, primary.getRGB());
        TFArmorDyeHelper.setPrimaryColor(legs, primary.getRGB());
        TFArmorDyeHelper.setPrimaryColor(feet, primary.getRGB());
        TFArmorDyeHelper.setSecondaryColor(head, secondary.getRGB());
        TFArmorDyeHelper.setSecondaryColor(chest, secondary.getRGB());
        TFArmorDyeHelper.setSecondaryColor(legs, secondary.getRGB());
        TFArmorDyeHelper.setSecondaryColor(feet, secondary.getRGB());

        entity.setCurrentItemOrArmor(4, head);
        entity.setCurrentItemOrArmor(3, chest);
        entity.setCurrentItemOrArmor(2, legs);
        entity.setCurrentItemOrArmor(1, feet);
        entity.capabilities.isFlying = true;
        entity.rotationYawHead = 0;
        entity.setInvisible(true);

        int k = width / 2 - 128 + 50;
        int l = height / 6 + 132;
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) k, (float) l, 50.0F);
        GL11.glScalef((float) (-60), (float) 60, (float) 60);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset;
        float f3 = entity.rotationYaw;
        float f4 = entity.rotationPitch;
        float f5 = entity.prevRotationYawHead;
        float f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, entity.yOffset, 10.0F);
        GL11.glRotatef((float) (ticks + partialTicks) / 2, 0.0F, 1.0F, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        TFRenderHelper.startGlScissor(width / 2 - 128, height / 6, 100, 150);
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        TFRenderHelper.endGlScissor();
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glColor4f(0, 0, 0, 1);
        drawTexturedModalRect(width / 2 - 22, height / 6 + 84, 0, 0, 66, 66);
        drawTexturedModalRect(width / 2 - 22 + 67, height / 6 + 84, 0, 0, 66, 66);

        float opacityMax = 20;
        float opacity = (ticks + partialTicks) % opacityMax;
        opacity = opacity > opacityMax / 2 ? opacityMax / 2 - (opacity - opacityMax / 2) : opacity;
        GL11.glColor4f(1, 1, 0, opacity / opacityMax + 0.1F);
        drawTexturedModalRect(width / 2 - 23 + 67 * layerSelected, height / 6 + 83, 0, 0, 68, 68);

        GL11.glColor4f(layerColors[0][0], layerColors[0][1], layerColors[0][2], 1);
        drawTexturedModalRect(width / 2 - 21, height / 6 + 85, 0, 0, 64, 64);

        GL11.glColor4f(layerColors[1][0], layerColors[1][1], layerColors[1][2], 1);
        drawTexturedModalRect(width / 2 - 21 + 67, height / 6 + 85, 0, 0, 64, 64);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
