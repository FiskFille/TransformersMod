package fiskfille.transformersmod.client.model.transformer;

import java.util.HashMap;
import java.util.Map;

import fiskfille.transformersmod.TransformerManager;
import fiskfille.transformersmod.client.model.transformer.ModelTransformer.Base;
import fiskfille.transformersmod.client.model.transformer.ModelTransformer.Biped;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelSkystrikeVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelSubwooferVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelVurpVehicle;
import fiskfille.transformersmod.common.transformer.base.Transformer;

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
	
	public static void registerModels()
	{
		TFModelRegistry.registerModel(TransformerManager.transformerCloudtrap, new ModelCloudtrap(), new ModelCloudtrapVehicle());
		TFModelRegistry.registerModel(TransformerManager.transformerPurge, new ModelPurge(), new ModelPurgeVehicle());
		TFModelRegistry.registerModel(TransformerManager.transformerSkystrike, new ModelSkystrike(), new ModelSkystrikeVehicle());
		TFModelRegistry.registerModel(TransformerManager.transformerSubwoofer, new ModelSubwoofer(), new ModelSubwooferVehicle());
		TFModelRegistry.registerModel(TransformerManager.transformerVurp, new ModelVurp(), new ModelVurpVehicle());
	}
}
