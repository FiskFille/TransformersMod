package fiskfille.tf.transformer;

import fiskfille.tf.item.TFItems;
import net.minecraft.item.Item;

public class TransformerCloudtrap extends TransformerJet 
{
	@Override
	public Item getHelmet() 
	{
		return TFItems.cloudtrapHelmet;
	}

	@Override
	public Item getChestplate() 
	{
		return TFItems.cloudtrapChestplate;
	}

	@Override
	public Item getLeggings()
	{
		return TFItems.cloudtrapLeggings;
	}

	@Override
	public Item getBoots()
	{
		return TFItems.cloudtrapBoots;
	}
	
	@Override
	public boolean hasJetpack() 
	{
		return true;
	}
}
