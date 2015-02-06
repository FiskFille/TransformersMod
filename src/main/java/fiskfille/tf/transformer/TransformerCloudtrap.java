package fiskfille.tf.transformer;

import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.proxy.ClientProxy;
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
	public Biped getModel()
	{
		return ClientProxy.getCloudtrap();
	}
	
	@Override
	public boolean hasJetpack() 
	{
		return true;
	}
}
