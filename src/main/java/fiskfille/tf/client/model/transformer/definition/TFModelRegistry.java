package fiskfille.tf.client.model.transformer.definition;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class TFModelRegistry
{
    private static final Map<ResourceLocation, TransformerModel> MODELS = new HashMap<>();

    public static void registerModel(Transformer transformer, TransformerModel model)
    {
        MODELS.put(transformer.getIdentifier(), model);
    }

    public static TransformerModel getModel(Transformer transformer)
    {
        return MODELS.get(transformer.getIdentifier());
    }

    public static void register()
    {
        TFModelRegistry.registerModel(TransformerManager.SKYSTRIKE, new TFModelSkystrike());
//        TFModelRegistry.registerModel(TransformerManager.CLOUDTRAP, new TFModelCloudtrap());
//        TFModelRegistry.registerModel(TransformerManager.PURGE, new TFModelPurge());
//        TFModelRegistry.registerModel(TransformerManager.SUBWOOFER, new TFModelSubwoofer());
//        TFModelRegistry.registerModel(TransformerManager.VURP, new TFModelVurp());
//        TFModelRegistry.registerModel(TransformerManager.WARDEN, new TFModelWarden());
    }
}
