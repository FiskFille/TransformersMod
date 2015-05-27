package fiskfille.tf;

import java.util.ArrayList;
import java.util.List;

import fiskfille.tf.common.transformer.base.Transformer;

/**
 * @author gegy1000
 */
public class TransformersAPI
{
    private static List<Transformer> transformers = new ArrayList<Transformer>();
    
    /**
     * Used to register the specified Transformer.
     * 
     * @param transformer The Transformer registered.
     */
    public static void registerTransformer(Transformer transformer)
    {
        if (!transformers.contains(transformer))
        {
            transformers.add(transformer);
        }
        else
        {
            System.err.println("[TransformersAPI] " + transformer.getName() + " has already been registered!");
        }
    }
    
    /**
     * @returns a list of registered Transformers.
     */
    public static List<Transformer> getTransformers()
    {
        return transformers;
    }
}
