package fiskfille.transformersmod.common.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import fiskfille.transformersmod.common.block.TFBlocks;

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