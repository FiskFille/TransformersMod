package fiskfille.tf.transformer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.proxy.ClientProxy;

public class TransformerVurp extends TransformerCar
{
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
		return ClientProxy.getVurp();
	}
}