package fiskfille.transformersmod.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import fiskfille.transformersmod.TransformersMod;

public class GuiTFModConfig extends GuiConfig
{
	public GuiTFModConfig(GuiScreen parent)
	{
		super(
			parent,
			new ConfigElement(TransformersMod.configFile.getCategory("Options")).getChildElements(),
			TransformersMod.modid,
			false,
			false,
			GuiConfig.getAbridgedConfigPath(TransformersMod.configFile.toString()));
	}
}