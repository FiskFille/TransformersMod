package fiskfille.tf.common.item.armor;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.Transformer;

public class ItemSkystrikeArmor extends ItemTransformerArmor
{
    public ItemSkystrikeArmor(int armorPiece)
    {
        super(TFItems.TRANSFORMERMATERIAL, 4, armorPiece);
    }

    @Override
    public Transformer getTransformer()
    {
        return TransformerManager.SKYSTRIKE;
    }
}