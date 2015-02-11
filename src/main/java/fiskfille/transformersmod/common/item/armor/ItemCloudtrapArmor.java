package fiskfille.transformersmod.common.item.armor;

import fiskfille.transformersmod.TransformerManager;
import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.common.item.TFItems;
import fiskfille.transformersmod.common.transformer.base.Transformer;

public class ItemCloudtrapArmor extends ItemTransformerArmor
{
	public ItemCloudtrapArmor(int armorPiece)
	{
		super(TFItems.TRANSFORMERMATERIAL, 4, armorPiece);
		this.setCreativeTab(TransformersMod.transformersTab);
	}

	@Override
	public Transformer getTransformer() 
	{
		return TransformerManager.transformerCloudtrap;
	}
}