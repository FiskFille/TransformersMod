package fiskfille.tf.transformer;

import fiskfille.tf.TFHelper;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.entity.EntityMissile;
import fiskfille.tf.entity.EntityTankShell;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.proxy.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class TransformerSubwoofer extends TransformerCar
{
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
		return ClientProxy.getSubwoofer();
	}
}