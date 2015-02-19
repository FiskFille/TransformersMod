package fiskfille.tf.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.main.MainClass;
import fiskfille.tf.main.TFHelper;

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