package fiskfille.tf.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.component.Component;
import fiskfille.tf.common.component.IComponent;

public class ItemComponent extends Item implements IComponent
{
    public static IIcon outline;
    private Component component;

    public ItemComponent(Component c)
    {
        component = c;
    }

    @Override
    public Component getComponent()
    {
        return component;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        super.registerIcons(iconRegister);
        outline = iconRegister.registerIcon(TransformersMod.modid + ":component_outline");
    }
}
