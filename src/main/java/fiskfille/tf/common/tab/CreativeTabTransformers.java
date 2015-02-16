package fiskfille.tf.common.tab;

import fiskfille.tf.common.block.TFBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabTransformers extends CreativeTabs
{
	public CreativeTabTransformers()
	{
		super("Transformers");
	}

	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(TFBlocks.energonCrystal);
	}
}