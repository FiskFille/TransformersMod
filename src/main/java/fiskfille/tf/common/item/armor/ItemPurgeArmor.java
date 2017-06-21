package fiskfille.tf.common.item.armor;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemPurgeArmor extends ItemTransformerArmor
{
    public ItemPurgeArmor(EntityEquipmentSlot armorPiece)
    {
        super(TFItems.TRANSFORMERMATERIAL, 4, armorPiece);
    }

    @Override
    public Transformer getTransformer()
    {
        return TransformerManager.PURGE;
    }
}
