package fiskfille.tf.common.item.armor;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.Transformer;

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