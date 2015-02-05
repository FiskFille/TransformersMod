package fiskfille.tf.model.player;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;

public class ModelCustomPlayer
{
	private static ModelCustomPlayer instance = new ModelCustomPlayer();
	
	public static ModelCustomPlayer getInstance()
	{
		return instance;
	}
	
    public ModelBiped setRotationAngles(EntityPlayer player, ModelBiped model)
    {
    	model.bipedRightArm.rotateAngleX = 2;
    	return model;
    }
}