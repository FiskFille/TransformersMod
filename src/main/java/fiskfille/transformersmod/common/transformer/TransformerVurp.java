package fiskfille.transformersmod.common.transformer;

import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.transformersmod.client.model.transformer.ModelVurp;
import fiskfille.transformersmod.client.model.transformer.TFModelRegistry;
import fiskfille.transformersmod.client.model.transformer.ModelTransformer.Biped;
import fiskfille.transformersmod.common.item.TFItems;
import fiskfille.transformersmod.common.transformer.base.TransformerCar;

public class TransformerVurp extends TransformerCar
{
	public TransformerVurp()
	{
		super("Vurp");
	}
	
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
		return TFModelRegistry.getModel(this);
	}
}