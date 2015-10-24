package fiskfille.tf.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.helper.TFArmorDyeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.Color;

@SideOnly(Side.CLIENT)
public class GuiColorPresets extends GuiScreen
{
    private TileEntityDisplayStation tileentity;
    private GuiColor parent;

    public static int ticks;
    public static float[][] tempLayerColors = {{1, 1, 1}, {1, 1, 1}};

    public ColorPreset[] presets = {};
    public int columnsPerPage = 5;
    public int rowsPerPage = 2;

    public int maxPages = 0;
    public int page = 0;

    public void initGui()
    {
        super.initGui();
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 6 + 168, I18n.format("gui.done", new Object[0])));
        buttonList.add(new GuiButton(1, width / 2 - 85, height / 6 + 130, 20, 20, "<"));
        buttonList.add(new GuiButton(2, width / 2 + 65, height / 6 + 130, 20, 20, ">"));

        presets = new ColorPreset[]
                {
                        // TFMod
                        new ColorPreset(0xffffffff, 0xffcd0000, "Skystrike"),
                        new ColorPreset(0xffa7a180, 0xff672222, "Purge"),
                        new ColorPreset(0xffa0a0a0, 0xff651212, "Skystrike (Weathered)"),
                        new ColorPreset(0xffff0000, 0xff101010, "Purge (Classic)"),

                        // Abstract
                        new ColorPreset(0xffff4a00, 0xff000000, "Halloween"),
                        new ColorPreset(0xff2b0051, 0xffbdbdbd, "Indigo"),
                        new ColorPreset(0xff3b1458, 0xff322277, "Eclipse"),
                        new ColorPreset(0xff3db4d6, 0xffb2ffff, "Cold"),
                        new ColorPreset(0xff090909, 0xff000000, "Bat"),
                        new ColorPreset(0xff3d87ff, 0xff003dff, "Ocean Blue"),
                        new ColorPreset(0xffa7a180, 0xff686653, "Desert"),
                        new ColorPreset(0xffffffff, 0xffffffff, "Blank"),
                        new ColorPreset(0xff687893, 0xff711010, "Perception"),

                        // Canon
                        new ColorPreset(0xff0000ff, 0xffff0000, "G1 Optimus Prime"),
                        new ColorPreset(0xffd7d7d7, 0xff666868, "G1 Megatron"),
                        new ColorPreset(0xffe4160e, 0xff3636e8, "G1 Starscream"),
                        new ColorPreset(0xffa0ff36, 0xff9a009a, "G1 Constructicon"),
                        new ColorPreset(0xfffe3978, 0xff198014, "G1 Scorponok"),
                        new ColorPreset(0xff7148d6, 0xfffe6c6c, "G1 Galvatron"),
                        new ColorPreset(0xffcdcdcd, 0xff0e0e0e, "G1 Prowl"),
                        new ColorPreset(0xff000083, 0xffbb0000, "Movie Optimus Prime"),
                        new ColorPreset(0xffa7a7a7, 0xff810000, "Movie Wreckage"),
                        new ColorPreset(0xffddc600, 0xff101010, "Bumblebee"),
                        new ColorPreset(0xff173f17, 0xff513838, "Brawl"),
                        new ColorPreset(0xff880000, 0xff4f0000, "Warpath"),
                        new ColorPreset(0xff4f00b2, 0xff656565, "Vehicon"),
                        new ColorPreset(0xffa51919, 0xffcf6300, "Hot-Rod"),
                        new ColorPreset(0xff737a80, 0xff2f3b47, "Starscream"),
                };

        int maxPresetsPerPage = columnsPerPage * rowsPerPage;
        int xOffset = 0;
        int yOffset = 0;
        maxPages = presets.length / (maxPresetsPerPage);

        for (int i = 0; i < presets.length; ++i)
        {
            ColorPreset preset = presets[i];

            if (i >= maxPresetsPerPage * page && i < maxPresetsPerPage * (page + 1))
            {
                preset.posX = width / 2 - 85 + xOffset;
                preset.posY = height / 6 + yOffset;
                xOffset += 58;

                if ((i % maxPresetsPerPage) % columnsPerPage == columnsPerPage - 1)
                {
                    xOffset = 0;
                    yOffset += 58;
                }
            }

            preset.initGui();
        }

        ((GuiButton) buttonList.get(1)).enabled = page > 0;
        ((GuiButton) buttonList.get(2)).enabled = page < maxPages;
    }

    public GuiColorPresets(TileEntityDisplayStation tile, GuiColor gui)
    {
        tileentity = tile;
        parent = gui;
    }

    public void updateScreen()
    {
        super.updateScreen();
        ticks = ++parent.ticks;

        for (ColorPreset preset : presets)
        {
            preset.updateScreen();
        }
    }

    protected void keyTyped(char c, int key)
    {
        if (key == 1)
        {
            mc.displayGuiScreen((GuiScreen) null);
        }

        for (ColorPreset preset : presets)
        {
            preset.keyTyped(c, key);
        }
    }

    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            parent.fromPresetMenu = true;
            mc.displayGuiScreen(parent);
        }
        if (id == 1)
        {
            page -= page > 0 ? 1 : 0;
            mc.displayGuiScreen(this);
        }
        if (id == 2)
        {
            page += page < maxPages ? 1 : 0;
            mc.displayGuiScreen(this);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        for (ColorPreset preset : presets)
        {
            if (button == 0)
            {
                if (mouseX >= preset.posX && mouseX < preset.posX + 50 && mouseY >= preset.posY && mouseY < preset.posY + 50)
                {
                    Color primary = new Color(preset.primaryColor);
                    Color secondary = new Color(preset.secondaryColor);

                    GuiColor.layerColors[0][0] = (float) primary.getRed() / 255;
                    GuiColor.layerColors[0][1] = (float) primary.getGreen() / 255;
                    GuiColor.layerColors[0][2] = (float) primary.getBlue() / 255;
                    GuiColor.layerColors[1][0] = (float) secondary.getRed() / 255;
                    GuiColor.layerColors[1][1] = (float) secondary.getGreen() / 255;
                    GuiColor.layerColors[1][2] = (float) secondary.getBlue() / 255;
                    GuiColor.fromPresetMenu = true;

                    mc.displayGuiScreen(parent);
                }
            }

            preset.mouseClicked(mouseX, mouseY, button);
        }
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, StatCollector.translateToLocal("gui.display_station.color.presets"), width / 2, 15, 16777215);

        int maxPresetsPerPage = columnsPerPage * rowsPerPage;
        int xOffset = 0;
        int yOffset = 0;

        for (int i = 0; i < presets.length; ++i)
        {
            ColorPreset preset = presets[i];
            preset.fontRendererObj = fontRendererObj;

            if (i >= maxPresetsPerPage * page && i < maxPresetsPerPage * (page + 1))
            {
                preset.posX = width / 2 - 85 + xOffset;
                preset.posY = height / 6 + yOffset;
                preset.drawScreen(mouseX, mouseY, partialTicks);

                if (mouseX >= preset.posX && mouseX < preset.posX + 50 && mouseY >= preset.posY && mouseY < preset.posY + 50)
                {
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    float opacityMax = 20;
                    float opacity = ticks % opacityMax;
                    opacity = opacity > opacityMax / 2 ? opacityMax / 2 - (opacity - opacityMax / 2) : opacity;
                    GL11.glColor4f(1, 1, 0, opacity / opacityMax + 0.1F);
                    drawTexturedModalRect(preset.posX - 2, preset.posY - 2, 0, 0, 54, 2);
                    drawTexturedModalRect(preset.posX - 2, preset.posY + 50, 0, 0, 54, 2);
                    drawTexturedModalRect(preset.posX - 2, preset.posY, 0, 0, 2, 50);
                    drawTexturedModalRect(preset.posX + 50, preset.posY, 0, 0, 2, 50);
                    GL11.glEnable(GL11.GL_TEXTURE_2D);

                    Color color = new Color(preset.primaryColor);
                    Color color1 = new Color(preset.secondaryColor);
                    tempLayerColors[0][0] = (float) color.getRed() / 255;
                    tempLayerColors[0][1] = (float) color.getGreen() / 255;
                    tempLayerColors[0][2] = (float) color.getBlue() / 255;
                    tempLayerColors[1][0] = (float) color1.getRed() / 255;
                    tempLayerColors[1][1] = (float) color1.getGreen() / 255;
                    tempLayerColors[1][2] = (float) color1.getBlue() / 255;

                    drawCenteredString(fontRendererObj, preset.name, width / 2 - 150, height / 6 - 15, 0xffffff);
                }

                xOffset += 58;

                if ((i % maxPresetsPerPage) % columnsPerPage == columnsPerPage - 1)
                {
                    xOffset = 0;
                    yOffset += 58;
                }
            }
        }

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(0, 0, 0, 0.4F);
        drawTexturedModalRect(width / 2 - 200, height / 6, 0, 0, 100, 150);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        boolean flag = false;

        for (float[] f : tempLayerColors)
        {
            for (float f1 : f)
            {
                if (f1 != 0)
                {
                    flag = true;
                }
            }
        }

        if (flag)
        {
            EntityClientPlayerMP entity = tileentity.fakePlayer;
            ItemStack head = tileentity.getStackInSlot(0).copy();
            ItemStack chest = tileentity.getStackInSlot(1).copy();
            ItemStack legs = tileentity.getStackInSlot(2).copy();
            ItemStack feet = tileentity.getStackInSlot(3).copy();

            Color primary = new Color(tempLayerColors[0][0], tempLayerColors[0][1], tempLayerColors[0][2]);
            Color secondary = new Color(tempLayerColors[1][0], tempLayerColors[1][1], tempLayerColors[1][2]);
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

            int k = width / 2 - 200 + 50;
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
            GL11.glRotatef((float) ticks / 2, 0.0F, 1.0F, 0.0F);
            RenderManager.instance.playerViewY = 180.0F;
            startGlScissor(width / 2 - 200, height / 6, 100, 150);
            RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
            endGlScissor();
            GL11.glPopMatrix();
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        }
        else
        {
            ticks = GuiColor.ticks = 0;
        }

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        drawTexturedModalRect(width / 2 - 60, height / 6 + 130, 0, 0, 120, 20);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        drawCenteredString(fontRendererObj, "Page " + (page + 1) + "/" + (maxPages + 1), width / 2, height / 6 + 135, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
        tempLayerColors[0][0] = 0;
        tempLayerColors[0][1] = 0;
        tempLayerColors[0][2] = 0;
        tempLayerColors[1][0] = 0;
        tempLayerColors[1][1] = 0;
        tempLayerColors[1][2] = 0;
    }

    public static void startGlScissor(int x, int y, int width, int height)//From top left corner, like how Minecraft guis are. Don't forget to call endGlScissor after rendering
    {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution reso = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        double scaleW = (double) mc.displayWidth / reso.getScaledWidth_double();
        double scaleH = (double) mc.displayHeight / reso.getScaledHeight_double();

        if (width <= 0 || height <= 0)
        {
            return;
        }
        if (x < 0)
        {
            x = 0;
        }
        if (y < 0)
        {
            y = 0;
        }

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) Math.floor((double) x * scaleW), (int) Math.floor((double) mc.displayHeight - ((double) (y + height) * scaleH)), (int) Math.floor((double) (x + width) * scaleW) - (int) Math.floor((double) x * scaleW), (int) Math.floor((double) mc.displayHeight - ((double) y * scaleH)) - (int) Math.floor((double) mc.displayHeight - ((double) (y + height) * scaleH))); //starts from lower left corner (minecraft starts from upper left)
    }

    public static void endGlScissor()
    {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static class ColorPreset extends Gui
    {
        public FontRenderer fontRendererObj;
        public int posX;
        public int posY;

        public int primaryColor;
        public int secondaryColor;
        public String name;

        public ColorPreset(int primaryColor, int secondaryColor, String name)
        {
            this.primaryColor = primaryColor;
            this.secondaryColor = secondaryColor;
            this.name = name;
        }

        public void initGui()
        {
        }

        public void updateScreen()
        {
        }

        protected void keyTyped(char c, int key)
        {
        }

        protected void mouseClicked(int mouseX, int mouseY, int button)
        {
        }

        public void drawScreen(int mouseX, int mouseY, float partialTicks)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            Color color = new Color(primaryColor);
            Color color1 = new Color(secondaryColor);

            GL11.glColor4f(0, 0, 0, 1);
            drawTexturedModalRect(posX, posY, 0, 0, 50, 50);

            GL11.glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, 1);
            drawTexturedModalRect(posX + 1, posY + 1, 0, 0, 23, 48);
            GL11.glColor4f((float) color1.getRed() / 255, (float) color1.getGreen() / 255, (float) color1.getBlue() / 255, 1);
            drawTexturedModalRect(posX + 26, posY + 1, 0, 0, 23, 48);

            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }
    }
}
