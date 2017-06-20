package fiskfille.tf;

import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;

/**
 * @author gegy1000, FiskFille
 */
public class TransformersAPI
{
    /**
     * Registry of transformers and their identifiers
     */
    public static final RegistryNamespaced<ResourceLocation, Transformer> REGISTRY = new RegistryNamespaced<>();

    /**
     * Used to register the specified Transformer.
     *
     * @param transformer The Transformer registered.
     */
    public static void registerTransformer(Transformer transformer)
    {
        if (!REGISTRY.containsKey(transformer.getIdentifier()))
        {
            REGISTRY.register(transformer.getId(), transformer.getIdentifier(), transformer);
        }
        else
        {
            TransformersMod.LOGGER.warn("{} has already been registered!", transformer.getIdentifier());
        }
    }
}
