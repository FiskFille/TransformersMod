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
        setMaxStackSize(1);
        component = c;
    }

    @Override
    public Component getComponent()
    {
        return component;
    }

    @Override
    public void registerIcons(IIconRegister par1IIconRegister)
    {
        super.registerIcons(par1IIconRegister);
        outline = par1IIconRegister.registerIcon(TransformersMod.modid + ":component_outline");
    }
}
