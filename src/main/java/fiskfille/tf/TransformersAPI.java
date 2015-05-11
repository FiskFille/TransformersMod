package fiskfille.tf;

import java.util.ArrayList;
import java.util.List;

import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.transformer.base.Transformer;

/**
 * @author gegy1000
 */
public class TransformersAPI
{
    private static List<Transformer> transformers = new ArrayList<Transformer>();
    private static List<Energon> energonTypes = new ArrayList<Energon>();
    
    /**
     * Used to register the specified energon type.
     * 
     * @param energon The energon type being registered.
     */
    public static void registerEnergonType(Energon energon)
    {
        if (!energonTypes.contains(energon))
        {
            energonTypes.add(energon);
        }
        else
        {
            System.err.println("[TransformersAPI] A mod is trying to register an energon type twice!");
        }
    }
    
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
     * @returns a list of registered Energon Types.
     */
    public static List<Energon> getEnergonTypes()
    {
        return energonTypes;
    }
    
    /**
     * @returns a list of registered Transformers.
     */
    public static List<Transformer> getTransformers()
    {
        return transformers;
    }
}
