package fiskfille.tf.main.misc;

import fiskfille.tf.main.MainClass;
import fiskfille.tf.main.TFBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabTransformers extends CreativeTabs
{
	public CreativeTabTransformers()
	{
		super("Transformers");
	}

	public String getTranslatedTabLabel()
	{
		return "Transformers";
	}

	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(TFBlocks.transformiumOre);
	}
}