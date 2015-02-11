package fiskfille.transformersmod;

import java.util.ArrayList;
import java.util.List;

import fiskfille.transformersmod.client.model.transformer.ModelTransformer;
import fiskfille.transformersmod.common.transformer.base.Transformer;

public class TransformersAPI 
{
	private static List<Transformer> transformers = new ArrayList<Transformer>();
	
	public static void registerTransformer(Transformer transformer)
	{
		if(!transformers.contains(transformer))
		{
			transformers.add(transformer);
		}
		else
		{
			System.err.println("[TransformersAPI] " + transformer.getName() + " has already been registered!");
		}
	}
	
	public static List<Transformer> getTransformers()
	{
		return transformers;
	}
}
