package fiskfille.tf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fiskfille.tf.client.displayable.DisplayableRender;
import net.minecraft.item.Item;
import fiskfille.tf.common.transformer.base.Transformer;

/**
 * @author gegy1000, FiskFille
 */
public class TransformersAPI
{
    private static List<Transformer> transformers = new ArrayList<Transformer>();
    private static Map<Item, DisplayableRender> displayables = new HashMap<Item, DisplayableRender>();
    
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
     * Gets an instance of a Transformer by name.
     * 
     * @param name The name of the Transformer
     * @return the Transformer with the specified name, or null if there is none.
     */
    public static Transformer getTransformerByName(String name)
    {
    	for (Transformer transformer : transformers)
    	{
    		if (transformer.getName().equals(name))
    		{
    			return transformer;
    		}
    	}
    	
    	return null;
    }
    
    /**
     * Used to register the specified DisplayableRender.
     * 
     * @param item The item to be assigned to.
     * @param displayable The DisplayableRender registered.
     */
    public static void registerDisplayable(Item item, DisplayableRender displayable)
    {
    	displayables.put(item, displayable);
    }
    
    /**
     * @returns a list of registered Displayables.
     */
    public static Map<Item, DisplayableRender> getDisplayables()
    {
        return displayables;
    }
    
    /**
     * @param item The item to get the DisplayableRender for.
     * @return the DisplayableRender for the specific item.
     */
    public static DisplayableRender getDisplayableFor(Item item)
    {
    	for (Map.Entry<Item, DisplayableRender> e : displayables.entrySet())
    	{
    		if (e.getKey() == item)
    		{
    			return e.getValue();
    		}
    	}
    	
    	return null;
    }
}
