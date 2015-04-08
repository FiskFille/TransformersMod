package fiskfille.tf.common.item.armor;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.Transformer;

public class ItemVurpArmor extends ItemTransformerArmor
{
    public ItemVurpArmor(int armorPiece)
    {
        super(TFItems.TRANSFORMERMATERIAL, 4, armorPiece);
    }
    
    @Override
    public Transformer getTransformer()
    {
        return TransformerManager.transformerVurp;
    }
}