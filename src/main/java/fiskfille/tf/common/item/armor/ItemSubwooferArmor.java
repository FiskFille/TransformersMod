package fiskfille.tf.common.item.armor;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.Transformer;

public class ItemSubwooferArmor extends ItemTransformerArmor
{
    public ItemSubwooferArmor(int armorPiece)
    {
        super(TFItems.SUBWOOFERMATERIAL, 4, armorPiece);
        this.setCreativeTab(TransformersMod.tabTransformers);
    }
    
    @Override
    public Transformer getTransformer()
    {
        return TransformerManager.transformerSubwoofer;
    }
}