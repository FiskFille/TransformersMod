package fiskfille.tf.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;
import fiskfille.tf.main.MainClass;

public class GuiTFModConfig extends GuiConfig
{
	public GuiTFModConfig(GuiScreen parent)
	{
		super(
			parent,
			new ConfigElement(MainClass.configFile.getCategory("Options")).getChildElements(),
			MainClass.modid,
			false,
			false,
			GuiConfig.getAbridgedConfigPath(MainClass.configFile.toString()));
	}
}