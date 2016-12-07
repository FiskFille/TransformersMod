package fiskfille.tf.client.model.transformer.definition;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.common.transformer.base.Transformer;

import java.util.HashMap;
import java.util.Map;

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
        TFModelRegistry.registerModel(TransformerManager.CLOUDTRAP, new TFModelCloudtrap());
        TFModelRegistry.registerModel(TransformerManager.PURGE, new TFModelPurge());
        TFModelRegistry.registerModel(TransformerManager.SKYSTRIKE, new TFModelSkystrike());
        TFModelRegistry.registerModel(TransformerManager.SUBWOOFER, new TFModelSubwoofer());
        TFModelRegistry.registerModel(TransformerManager.VURP, new TFModelVurp());
//        TFModelRegistry.registerModel(TransformerManager.WARDEN, new TFModelWarden());
    }
}
