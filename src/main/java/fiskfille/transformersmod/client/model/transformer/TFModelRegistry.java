package fiskfille.transformersmod.client.model.transformer;

import java.util.HashMap;
import java.util.Map;

import fiskfille.transformersmod.client.model.transformer.ModelTransformer.Base;
import fiskfille.transformersmod.client.model.transformer.ModelTransformer.Biped;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.transformersmod.transformer.base.Transformer;

public class TFModelRegistry 
{
	private static Map<Transformer, Biped> models = new HashMap<Transformer, Biped>();
	private static Map<Transformer, ModelVehicleBase> vehicleModels = new HashMap<Transformer, ModelVehicleBase>();
	
	public static ModelVehicleBase getVehicleModel(Transformer transformer)
	{
		return vehicleModels.get(transformer);
	}
	
	public static void registerModel(Transformer transformer, Biped model, ModelVehicleBase vehicleModel)
	{
		models.put(transformer, model);
		vehicleModels.put(transformer, vehicleModel);
	}
	
	public static Biped getModel(Transformer transformer)
	{
		return models.get(transformer);
	}
}
