package fiskfille.tf.transformer;

import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.proxy.ClientProxy;
import net.minecraft.item.Item;

public class TransformerPurge extends TransformerTank
{
	@Override
	public Item getHelmet() 
	{
		return TFItems.purgeHelmet;
	}

	@Override
	public Item getChestplate() 
	{
		return TFItems.purgeChestplate;
	}

	@Override
	public Item getLeggings()
	{
		return TFItems.purgeLeggings;
	}

	@Override
	public Item getBoots()
	{
		return TFItems.purgeBoots;
	}
	
	@Override
	public Biped getModel()
	{
		return ClientProxy.getPurge();
	}
}
