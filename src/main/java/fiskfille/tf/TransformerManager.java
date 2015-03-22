package fiskfille.tf;

import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;

public class TransformerManager
{
    public static Transformer transformerSkystrike = new TransformerSkystrike();
    public static Transformer transformerPurge = new TransformerPurge();
    public static Transformer transformerVurp = new TransformerVurp();
    public static Transformer transformerSubwoofer = new TransformerSubwoofer();
    public static Transformer transformerCloudtrap = new TransformerCloudtrap();
    
    public static void register()
    {
        TransformersAPI.registerTransformer(transformerSkystrike);
        TransformersAPI.registerTransformer(transformerPurge);
        TransformersAPI.registerTransformer(transformerVurp);
        TransformersAPI.registerTransformer(transformerSubwoofer);
        TransformersAPI.registerTransformer(transformerCloudtrap);
    }
}
