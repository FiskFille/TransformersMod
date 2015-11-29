package fiskfille.tf;

import fiskfille.tf.client.gui.ColorPreset;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;

/**
 * @author gegy1000
 */
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

        // TFMod
        TransformersAPI.registerColorPreset(new ColorPreset(0xffffffff, 0xffcd0000, "Skystrike"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffa7a180, 0xff672222, "Purge"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffa0a0a0, 0xff651212, "Skystrike (Weathered)"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffff0000, 0xff101010, "Purge (Classic)"));

        // Abstract
        TransformersAPI.registerColorPreset(new ColorPreset(0xffff4a00, 0xff000000, "Halloween"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff2b0051, 0xffbdbdbd, "Indigo"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff3b1458, 0xff322277, "Eclipse"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff3db4d6, 0xffb2ffff, "Cold"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff090909, 0xff000000, "Bat"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff3d87ff, 0xff003dff, "Ocean Blue"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffa7a180, 0xff686653, "Desert"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffffffff, 0xffffffff, "Blank"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff687893, 0xff711010, "Perception"));

        // Canon
        TransformersAPI.registerColorPreset(new ColorPreset(0xff0000ff, 0xffff0000, "G1 Optimus Prime"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffd7d7d7, 0xff666868, "G1 Megatron"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffe4160e, 0xff3636e8, "G1 Starscream"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffa0ff36, 0xff9a009a, "G1 Constructicon"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xfffe3978, 0xff198014, "G1 Scorponok"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff7148d6, 0xfffe6c6c, "G1 Galvatron"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffcdcdcd, 0xff0e0e0e, "G1 Prowl"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff000083, 0xffbb0000, "Movie Optimus Prime"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffa7a7a7, 0xff810000, "Movie Wreckage"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffddc600, 0xff101010, "Bumblebee"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff173f17, 0xff513838, "Brawl"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff880000, 0xff4f0000, "Warpath"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff4f00b2, 0xff656565, "Vehicon"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xffa51919, 0xffcf6300, "Hot-Rod"));
        TransformersAPI.registerColorPreset(new ColorPreset(0xff737a80, 0xff2f3b47, "Starscream"));
    }
}
