package fiskfille.tf.common.transformer;

import net.minecraft.item.Item;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerTank;

/**
 * @author gegy1000
 */
public class TransformerPurge extends TransformerTank
{
    public TransformerPurge()
    {
        super("Purge");
    }
    
    @Override
    public Item getHelmet()
    {
        return TFItems.purgeHelmet;
    }
    
    @Override
    public Item getChestplate()
    {
        return TFItems.purgeChestplate;
    }
    
    @Override
    public Item getLeggings()
    {
        return TFItems.purgeLeggings;
    }
    
    @Override
    public Item getBoots()
    {
        return TFItems.purgeBoots;
    }
}