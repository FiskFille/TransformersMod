package fiskfille.tf.transformer;

import net.minecraft.item.Item;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.model.transformer.ModelPurge;
import fiskfille.tf.proxy.ClientProxy;
import fiskfille.tf.transformer.base.TransformerTank;

public class TransformerPurge extends TransformerTank
{
	private ModelPurge model = new ModelPurge();
	
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
		return model;
	}
}
