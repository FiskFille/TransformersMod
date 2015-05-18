package fiskfille.tf.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import fiskfille.tf.TransformersMod;

public class GuiTFModConfig extends GuiConfig
{
    //    public GuiTFModConfig(GuiScreen parent)
    //    {
    //        super(parent, new ConfigElement(TransformersMod.configFile.getCategory("options")).getChildElements(), TransformersMod.modid, false, false, GuiConfig.getAbridgedConfigPath(TransformersMod.configFile.toString()));
    //    }
    
    public GuiTFModConfig(GuiScreen parent)
    {
        super(parent, getConfigElements(), TransformersMod.modid, false, false, "Transformers Mod Configuration");
    }
    
    /** Compiles a list of config elements */
    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> elements = new ArrayList<IConfigElement>();
        
        elements.add(categoryElement("Options", "General", "General Options"));
        elements.add(categoryElement("Aesthetic", "Aesthetic", "Aesthetic Options"));
        elements.add(categoryElement("Projectiles", "Projectiles", "Projectile Options"));
        elements.add(categoryElement("Transformation", "Transformation", "Transformation Options"));
        
        return elements;
    }
    
    /** Creates a button linking to another screen where all options of the category are available */
    private static IConfigElement categoryElement(String category, String name, String tooltip_key)
    {
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key, new ConfigElement(TransformersMod.configFile.getCategory(category.toLowerCase())).getChildElements());
    }
}