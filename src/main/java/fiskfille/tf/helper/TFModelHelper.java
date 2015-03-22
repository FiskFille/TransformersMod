package fiskfille.tf.helper;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;

public class TFModelHelper
{
	public static ModelBiped modelBipedMain;
	
	public static Map<EntityPlayer, ModelOffset> offsets = new HashMap<EntityPlayer, ModelOffset>();
	
	public static ModelOffset getOffsets(EntityPlayer player)
	{
		ModelOffset modelOffset = offsets.get(player);
		
		if(modelOffset == null)
		{
			modelOffset = new ModelOffset();
			offsets.put(player, modelOffset);
		}
		
		return modelOffset;
	}
}
