package fiskfille.tf.client.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;

public class TFGuiFactory implements IModGuiFactory
{
    public void initialize(Minecraft minecraftInstance)
    {

    }

    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return GuiTFModConfig.class;
    }

    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }
}
