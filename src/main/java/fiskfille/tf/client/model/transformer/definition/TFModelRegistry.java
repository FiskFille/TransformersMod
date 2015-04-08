package fiskfille.tf.client.model.transformer.definition;

import java.util.HashMap;
import java.util.Map;

import javax.vecmath.Vector3f;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelRenderer;
import fiskfille.tf.TransformerManager;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelSkystrikeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelSubwooferVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelVurpVehicle;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFModelRegistry
{
    private static Map<Transformer, TransformerModel> models = new HashMap<Transformer, TransformerModel>();
    
    public static void registerModel(Transformer transformer, TransformerModel model)
    {
        models.put(transformer, model);
    }
    
    public static TransformerModel getModel(Transformer transformer)
    {
        return models.get(transformer);
    }
    
    public static void registerModels()
    {
        TFModelRegistry.registerModel(TransformerManager.transformerCloudtrap, new TFModelCloudtrap());
        TFModelRegistry.registerModel(TransformerManager.transformerPurge, new TFModelPurge());
        TFModelRegistry.registerModel(TransformerManager.transformerSkystrike, new TFModelSkystrike());
        TFModelRegistry.registerModel(TransformerManager.transformerSubwoofer, new TFModelSubwoofer());
        TFModelRegistry.registerModel(TransformerManager.transformerVurp, new TFModelVurp());
    }
}
