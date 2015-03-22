package fiskfille.tf.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import fiskfille.tf.TransformersMod;

public class ItemBasic extends Item
{
    public ItemBasic()
    {
        super();
        this.setCreativeTab(TransformersMod.tabTransformers);
    }

    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(TransformersMod.modid + ":"
                + iconString);
    }
}