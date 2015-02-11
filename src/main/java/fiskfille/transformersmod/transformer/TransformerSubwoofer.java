package fiskfille.transformersmod.transformer;

import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.transformersmod.client.model.transformer.ModelSubwoofer;
import fiskfille.transformersmod.client.model.transformer.TFModelRegistry;
import fiskfille.transformersmod.client.model.transformer.ModelTransformer.Biped;
import fiskfille.transformersmod.item.TFItems;
import fiskfille.transformersmod.transformer.base.TransformerCar;

public class TransformerSubwoofer extends TransformerCar
{
	public TransformerSubwoofer(String name) 
	{
		super(name);
	}

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
		return TFModelRegistry.getModel(this);
	}
}