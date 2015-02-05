package fiskfille.tf.transformer;

import fiskfille.tf.TFHelper;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.entity.EntityMissile;
import fiskfille.tf.entity.EntityTankShell;
import fiskfille.tf.item.TFItems;
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
		return TFItems.skystrikeLeggings;
	}

	@Override
	public Item getBoots()
	{
		return TFItems.skystrikeBoots;
	}
}
