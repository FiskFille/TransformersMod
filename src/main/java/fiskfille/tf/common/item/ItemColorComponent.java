package fiskfille.tf.common.item;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.component.Component;
import fiskfille.tf.common.component.ComponentColor;
import fiskfille.tf.common.component.IComponent;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemColorComponent extends Item implements IComponent
{
    public static IIcon outline;

    public ItemColorComponent()
    {
        super();
        setMaxStackSize(1);
    }

    @Override
    public Component getComponent()
    {
        return new ComponentColor();
    }

    @Override
    public void registerIcons(IIconRegister par1IIconRegister)
    {
        super.registerIcons(par1IIconRegister);
        outline = par1IIconRegister.registerIcon(TransformersMod.modid + ":component_outline");
    }
}
