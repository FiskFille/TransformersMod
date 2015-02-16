package fiskfille.tf.common.item;

import fiskfille.tf.TransformersMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemBasic extends Item
{
	public ItemBasic()
	{
		super();
		this.setCreativeTab(TransformersMod.transformersTab);
	}
	
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(TransformersMod.modid + ":" + iconString);
	}
}