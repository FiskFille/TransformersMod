package fiskfille.tf.common.transformer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import fiskfille.tf.client.model.transformer.TFModelRegistry;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import fiskfille.tf.common.transformer.base.TransformerJet;

public class TransformerSkystrike extends TransformerJet
{
	public TransformerSkystrike()
	{
		super("Skystrike");
	}
	
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
		return TFModelRegistry.getModel(this);
	}
	
	@Override
	public void onJump(EntityPlayer player)
	{
		player.motionY += 0.205D;
	}
	
	@Override
	public void robotTick(EntityPlayer player)
	{
		if (!player.capabilities.isFlying && !(TFDataManager.getTransformationTimer(player) < 10))
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
	
	@Override
	public void vehicleTick(EntityPlayer player)
	{
	}
}