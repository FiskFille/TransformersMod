package fiskfille.tf;

import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.TransformerWarden;
import fiskfille.tf.common.transformer.base.Transformer;

public class TransformerManager
{
    public static final Transformer SKYSTRIKE = new TransformerSkystrike();
    public static final Transformer PURGE = new TransformerPurge();
    public static final Transformer VURP = new TransformerVurp();
    public static final Transformer SUBWOOFER = new TransformerSubwoofer();
    public static final Transformer CLOUDTRAP = new TransformerCloudtrap();
    public static final Transformer WARDEN = new TransformerWarden();

    public static void register()
    {
        TransformersAPI.registerTransformer(SKYSTRIKE);
        TransformersAPI.registerTransformer(PURGE);
        TransformersAPI.registerTransformer(VURP);
        TransformersAPI.registerTransformer(SUBWOOFER);
        TransformersAPI.registerTransformer(CLOUDTRAP);
        TransformersAPI.registerTransformer(WARDEN);
    }
}
