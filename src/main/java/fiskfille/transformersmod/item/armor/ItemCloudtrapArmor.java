package fiskfille.transformersmod.item.armor;

import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.item.TFItems;
import fiskfille.transformersmod.transformer.base.Transformer;

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
		return TransformersMod.transformerCloudtrap;
	}
}