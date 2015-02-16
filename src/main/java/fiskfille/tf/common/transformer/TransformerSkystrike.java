package fiskfille.tf.common.transformer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.tf.client.model.transformer.ModelSkystrike;
import fiskfille.tf.client.model.transformer.TFModelRegistry;
import fiskfille.tf.client.model.transformer.ModelTransformer.Biped;
import fiskfille.tf.common.item.TFItems;
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
