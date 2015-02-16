package fiskfille.tf.model.transformer;

import java.util.HashMap;
import java.util.Map;

import fiskfille.tf.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.transformer.base.Transformer;

public class TFModelRegistry 
{
	private static Map<String, Biped> models = new HashMap<String, Biped>();
	
	public static void registerModel(String name, Biped model)
	{
		models.put(name, model);
	}
	
	public static Biped getModel(Transformer transformer)
	{
		return models.get(transformer.getName());
	}
}
