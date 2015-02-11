package fiskfille.transformersmod.common.transformer;

import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.transformersmod.client.model.transformer.ModelPurge;
import fiskfille.transformersmod.client.model.transformer.TFModelRegistry;
import fiskfille.transformersmod.client.model.transformer.ModelTransformer.Biped;
import fiskfille.transformersmod.common.item.TFItems;
import fiskfille.transformersmod.common.transformer.base.TransformerTank;

public class TransformerPurge extends TransformerTank
{
	public TransformerPurge()
	{
		super("Purge");
	}
	
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
		return TFModelRegistry.getModel(this);
	}
}
