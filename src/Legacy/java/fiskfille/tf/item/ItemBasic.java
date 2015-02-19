package fiskfille.tf.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import fiskfille.tf.main.MainClass;

public class ItemBasic extends Item
{
	public ItemBasic()
	{
		super();
		this.setCreativeTab(MainClass.tabTransformers);
	}
	
	public void registerIcons(IIconRegister par1IconRegister)
	{
		itemIcon = par1IconRegister.registerIcon(MainClass.modid + ":" + iconString);
	}
}