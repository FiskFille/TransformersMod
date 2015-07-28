package fiskfille.tf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import fiskfille.tf.client.displayable.Displayable;
import fiskfille.tf.common.transformer.base.Transformer;

/**
 * @author gegy1000, FiskFille
 */
public class TransformersAPI
{
    private static List<Transformer> transformers = new ArrayList<Transformer>();
    private static Map<Item, Displayable> displayables = new HashMap<Item, Displayable>();
    
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
    
    /**
     * Used to register the specified Displayable.
     * 
     * @param item The item to be assigned to.
     * @param displayable The Displayable registered.
     */
    public static void registerDisplayable(Item item, Displayable displayable)
    {
    	displayables.put(item, displayable);
    }
    
    /**
     * @returns a list of registered Displayables.
     */
    public static Map<Item, Displayable> getDisplayables()
    {
        return displayables;
    }
    
    /**
     * @param item The item to get the Displayable for.
     * @return the Displayable for the specific item.
     */
    public static Displayable getDisplayableFor(Item item)
    {
    	for (Map.Entry<Item, Displayable> e : displayables.entrySet())
    	{
    		if (e.getKey() == item)
    		{
    			return e.getValue();
    		}
    	}
    	
    	return null;
    }
}
