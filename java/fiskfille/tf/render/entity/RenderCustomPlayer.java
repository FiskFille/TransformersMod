package fiskfille.tf.render.entity;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
		if (!(TFDataManager.isInVehicleMode(player) && TFDataManager.getTransformationTimer(player) == 0))
		{
			super.renderFirstPersonArm(player);
		}
	}
}