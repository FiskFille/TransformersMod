package fiskfille.tf.client.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiColorPresets extends GuiScreen
{
    private TileEntityDisplayStation tileentity;
    private GuiColor parent;

    public static int ticks;
    public static float[][] tempLayerColors = { {1, 1, 1}, {1, 1, 1}};

    public ColorPreset[] presets = {};
    public int columnsPerPage = 5;
    public int rowsPerPage = 2;

    public int maxPages = 0;
    public int page = 0;

    @Override
    public void initGui()
    {
        super.initGui();
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 6 + 168, I18n.format("gui.done")));
        buttonList.add(new GuiButton(1, width / 2 - 85, height / 6 + 130, 20, 20, "<"));
        buttonList.add(new GuiButton(2, width / 2 + 65, height / 6 + 130, 20, 20, ">"));

        presets = new ColorPreset[] {
                // TFMod
                new ColorPreset(0xa9a9a9, 0xcd0000, "Skystrike"), new ColorPreset(0xa7a180, 0x672222, "Purge"), new ColorPreset(0x919191, 0x651212, "Skystrike (Weathered)"), new ColorPreset(0xff0000, 0x101010, "Purge (Classic)"),

                // Abstract
                new ColorPreset(0xff4a00, 0x090909, "Halloween"), new ColorPreset(0x2b0051, 0xbdbdbd, "Indigo"), new ColorPreset(0x3b1458, 0x322277, "Eclipse"), new ColorPreset(0x3db4d6, 0xb2ffff, "Cold"), new ColorPreset(0x090909, 0x101010, "Night"), new ColorPreset(0x3d87ff, 0x003dff, "Ocean"), new ColorPreset(0xa7a180, 0x686653, "Desert"), new ColorPreset(0x687893, 0x711010, "Perception"),

                // Canon
                new ColorPreset(0x0000ff, 0xff0000, "G1 Optimus Prime"), new ColorPreset(0xd7d7d7, 0x666868, "G1 Megatron"), new ColorPreset(0xe4160e, 0x3636e8, "G1 Starscream"), new ColorPreset(0xa0ff36, 0x9a009a, "G1 Constructicon"), new ColorPreset(0xfe3978, 0x198014, "G1 Scorponok"), new ColorPreset(0x7148d6, 0xfe6c6c, "G1 Galvatron"), new ColorPreset(0xcdcdcd, 0x0e0e0e, "G1 Prowl"), new ColorPreset(0x000083, 0xbb0000, "Movie Optimus Prime"), new ColorPreset(0xddc600, 0x101010, "Bumblebee"), new ColorPreset(0x173f17, 0x513838, "Brawl"), new ColorPreset(0x880000, 0x4f0000, "Warpath"), new ColorPreset(0x4f00b2, 0x656565, "Vehicon"), new ColorPreset(0xa51919, 0xcf6300, "Hot-Rod"), new ColorPreset(0x737a80, 0x2f3b47, "Starscream"),};

        int maxPresetsPerPage = columnsPerPage * rowsPerPage;
        int xOffset = 0;
        int yOffset = 0;
        maxPages = presets.length / maxPresetsPerPage;

        for (int i = 0; i < presets.length; ++i)
        {
            ColorPreset preset = presets[i];

            if (i >= maxPresetsPerPage * page && i < maxPresetsPerPage * (page + 1))
            {
                preset.posX = width / 2 - 85 + xOffset;
                preset.posY = height / 6 + yOffset;
                xOffset += 58;

                if (i % maxPresetsPerPage % columnsPerPage == columnsPerPage - 1)
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

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        ticks = ++GuiColor.ticks;

        for (ColorPreset preset : presets)
        {
            preset.updateScreen();
        }
    }

    @Override
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

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            GuiColor.fromPresetMenu = true;
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

    @Override
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

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, I18n.format("gui.display_station.color.presets"), width / 2, 15, 16777215);

        int maxPresetsPerPage = columnsPerPage * rowsPerPage;
        int xOffset = 0;
        int yOffset = 0;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

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
                    float opacity = (ticks + partialTicks) % opacityMax;
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

                    drawCenteredString(fontRendererObj, preset.name, width / 2 - 150, height / 6 - 15, -1);
                }

                xOffset += 58;

                if (i % maxPresetsPerPage % columnsPerPage == columnsPerPage - 1)
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
            GL11.glTranslatef(k, l, 50.0F);
            GL11.glScalef(-60, 60, 60);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
            RenderHelper.enableStandardItemLighting();
            GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, entity.yOffset, 10.0F);
            GL11.glRotatef((ticks + partialTicks) / 2, 0.0F, 1.0F, 0.0F);
            RenderManager.instance.playerViewY = 180.0F;
            TFRenderHelper.startGlScissor(width / 2 - 200, height / 6, 100, 150);
            RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
            TFRenderHelper.endGlScissor();
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
        GL11.glDisable(GL11.GL_BLEND);

        drawCenteredString(fontRendererObj, I18n.format("gui.display_station.color.presets.page", page + 1, maxPages + 1), width / 2, height / 6 + 135, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
        tempLayerColors[0][0] = 0;
        tempLayerColors[0][1] = 0;
        tempLayerColors[0][2] = 0;
        tempLayerColors[1][0] = 0;
        tempLayerColors[1][1] = 0;
        tempLayerColors[1][2] = 0;
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
            Color color = new Color(primaryColor);
            Color color1 = new Color(secondaryColor);

            Minecraft.getMinecraft().getTextureManager().bindTexture(GuiButtonFlat.tfButtonTextures);
            GL11.glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, 1);
            drawTexturedModalRect(posX, posY, 156, 206, 50, 50);
            GL11.glColor4f((float) color1.getRed() / 255, (float) color1.getGreen() / 255, (float) color1.getBlue() / 255, 1);
            drawTexturedModalRect(posX, posY, 206, 206, 50, 50);

//            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationItemsTexture);
//            Item item = Items.spawn_egg;
//            GL11.glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, 1);
//            drawTexturedModelRectFromIcon(posX + 1, posY + 1, item.getIconFromDamageForRenderPass(0, 0), 48, 48);
//            GL11.glColor4f((float) color1.getRed() / 255, (float) color1.getGreen() / 255, (float) color1.getBlue() / 255, 1);
//            drawTexturedModelRectFromIcon(posX + 1, posY + 1, item.getIconFromDamageForRenderPass(0, 1), 48, 48);
        }
    }
}
