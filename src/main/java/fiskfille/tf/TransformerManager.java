package fiskfille.tf;

import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;

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
