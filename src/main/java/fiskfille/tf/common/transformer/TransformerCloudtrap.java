package fiskfille.tf.common.transformer;

import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.tf.client.model.transformer.ModelCloudtrap;
import fiskfille.tf.client.model.transformer.TFModelRegistry;
import fiskfille.tf.client.model.transformer.ModelTransformer.Biped;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerJet;

public class TransformerCloudtrap extends TransformerJet 
{
	public TransformerCloudtrap()
	{
		super("Cloudtrap");
	}
	
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

	@Override
	public Biped getModel() 
	{
		return TFModelRegistry.getModel(this);
	}
}
