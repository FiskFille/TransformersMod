package fiskfille.tf.render.entity;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TFHelper;
import fiskfille.tf.data.TFDataManager;

@SideOnly(Side.CLIENT)
public class RenderCustomPlayer extends RenderPlayer
{
	public RenderCustomPlayer()
	{
		super();
	}

	@Override
	public void renderFirstPersonArm(EntityPlayer player)
	{
		ItemStack currentArmour = player.getCurrentArmor(2);
		
		if(currentArmour != null)
		{
			if (!TFHelper.isTransformerArmor(player, currentArmour.getItem()))
			{
				super.renderFirstPersonArm(player);
			}
		}
		else
		{
			super.renderFirstPersonArm(player);
		}
	}
}