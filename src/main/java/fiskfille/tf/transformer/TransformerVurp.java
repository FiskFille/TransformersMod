package fiskfille.tf.transformer;

import net.minecraft.item.Item;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.model.transformer.ModelVurp;
import fiskfille.tf.proxy.ClientProxy;
import fiskfille.tf.transformer.base.TransformerCar;

public class TransformerVurp extends TransformerCar
{
	private ModelVurp model = new ModelVurp();
	
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
	
	@Override
	public Biped getModel()
	{
		return model;
	}
}