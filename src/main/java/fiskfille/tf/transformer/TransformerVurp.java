package fiskfille.tf.transformer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.item.TFItems;

public class TransformerVurp extends TransformerCar
{
	@Override
	public Item getHelmet() 
	{
		return TFItems.vurpHelmet;
	}

	@Override
	public Item getChestplate() 
	{
		return TFItems.vurpChestplate;
	}

	@Override
	public Item getLeggings()
	{
		return TFItems.vurpLeggings;
	}

	@Override
	public Item getBoots()
	{
		return TFItems.vurpBoots;
	}
}
