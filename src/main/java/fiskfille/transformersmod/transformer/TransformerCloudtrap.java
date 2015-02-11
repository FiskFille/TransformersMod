package fiskfille.transformersmod.transformer;

import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.transformersmod.client.model.transformer.ModelCloudtrap;
import fiskfille.transformersmod.client.model.transformer.TFModelRegistry;
import fiskfille.transformersmod.client.model.transformer.ModelTransformer.Biped;
import fiskfille.transformersmod.item.TFItems;
import fiskfille.transformersmod.transformer.base.TransformerJet;

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
