package fiskfille.transformersmod.common.item.armor;

import fiskfille.transformersmod.TransformerManager;
import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.common.item.TFItems;
import fiskfille.transformersmod.common.transformer.base.Transformer;

public class ItemPurgeArmor extends ItemTransformerArmor
{
	public ItemPurgeArmor(int armorPiece)
	{
		super(TFItems.TANKMATERIAL, 4, armorPiece);
		this.setCreativeTab(TransformersMod.transformersTab);
	}

	@Override
	public Transformer getTransformer() 
	{
		return TransformerManager.transformerPurge;
	}
}