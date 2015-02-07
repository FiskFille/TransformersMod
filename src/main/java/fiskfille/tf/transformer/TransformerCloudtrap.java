package fiskfille.tf.transformer;

import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.model.transformer.ModelCloudtrap;
import fiskfille.tf.model.transformer.TFModelRegistry;
import fiskfille.tf.transformer.base.TransformerJet;

public class TransformerCloudtrap extends TransformerJet 
{
	public TransformerCloudtrap(String name)
	{
		super(name);
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
