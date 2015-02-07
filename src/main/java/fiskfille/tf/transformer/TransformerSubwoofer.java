package fiskfille.tf.transformer;

import net.minecraft.item.Item;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.model.transformer.ModelSubwoofer;
import fiskfille.tf.proxy.ClientProxy;
import fiskfille.tf.transformer.base.TransformerCar;

public class TransformerSubwoofer extends TransformerCar
{
	private ModelSubwoofer model = new ModelSubwoofer();
	
	@Override
	public Item getHelmet() 
	{
		return TFItems.subwooferHelmet;
	}

	@Override
	public Item getChestplate() 
	{
		return TFItems.subwooferChestplate;
	}

	@Override
	public Item getLeggings()
	{
		return TFItems.subwooferLeggings;
	}

	@Override
	public Item getBoots()
	{
		return TFItems.subwooferBoots;
	}
	
	@Override
	public Biped getModel()
	{
		return model;
	}
}