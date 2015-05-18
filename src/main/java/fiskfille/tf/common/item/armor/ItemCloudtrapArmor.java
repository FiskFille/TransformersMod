package fiskfille.tf.common.item.armor;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.Transformer;

public class ItemCloudtrapArmor extends ItemTransformerArmor
{
    public ItemCloudtrapArmor(int armorPiece)
    {
        super(TFItems.TRANSFORMERMATERIAL, 4, armorPiece);
        this.setCreativeTab(null); //R.I.P CT for now...
    }
    
    @Override
    public Transformer getTransformer()
    {
        return TransformerManager.transformerCloudtrap;
    }
}