package fiskfille.tf.transformer;

import fiskfille.tf.item.TFItems;
import fiskfille.tf.model.transformer.ModelPurge;
import fiskfille.tf.model.transformer.ModelSkystrike;
import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.proxy.ClientProxy;
import fiskfille.tf.transformer.base.TransformerJet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class TransformerSkystrike extends TransformerJet
{
	private ModelSkystrike model = new ModelSkystrike();
	
	@Override
	public Item getHelmet() 
	{
		return TFItems.skystrikeHelmet;
	}

	@Override
	public Item getChestplate() 
	{
		return TFItems.skystrikeChestplate;
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
	
	@Override
	public Biped getModel()
	{
		return model;
	}
	
	@Override
	public void onJump(EntityPlayer player)
	{
		player.motionY += 0.205D;
	}
	
	@Override
	public void vehicleTick(EntityPlayer player)
	{
		if (!player.capabilities.isFlying)
		{
			if (player.motionY < 0.0D)
			{
				player.motionY *= 0.85F;
			}
			else
			{
				player.motionY += 0.02D;
			}
		}
	}
}
