package fiskfille.transformersmod;

import fiskfille.transformersmod.common.transformer.TransformerCloudtrap;
import fiskfille.transformersmod.common.transformer.TransformerPurge;
import fiskfille.transformersmod.common.transformer.TransformerSkystrike;
import fiskfille.transformersmod.common.transformer.TransformerSubwoofer;
import fiskfille.transformersmod.common.transformer.TransformerVurp;
import fiskfille.transformersmod.common.transformer.base.Transformer;

public class TransformerManager
{
	public static Transformer transformerPurge = new TransformerPurge();
	public static Transformer transformerSkystrike = new TransformerSkystrike();
	public static Transformer transformerCloudtrap = new TransformerCloudtrap();
	public static Transformer transformerVurp = new TransformerVurp();
	public static Transformer transformerSubwoofer = new TransformerSubwoofer();
	
	public static void register()
	{
		TransformersAPI.registerTransformer(transformerCloudtrap);
		TransformersAPI.registerTransformer(transformerPurge);
		TransformersAPI.registerTransformer(transformerSkystrike);
		TransformersAPI.registerTransformer(transformerSubwoofer);
		TransformersAPI.registerTransformer(transformerVurp);
	}
}
