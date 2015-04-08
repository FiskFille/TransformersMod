package fiskfille.tf.common.transformer;

import net.minecraft.item.Item;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerCar;
import fiskfille.tf.common.transformer.base.TransformerTruck;

/**
 * @author gegy1000
 */
public class TransformerSubwoofer extends TransformerTruck
{
    public TransformerSubwoofer()
    {
        super("Subwoofer");
    }
    
    @Override
    public Item getHelmet()
    {
        return TFItems.subwooferHelmet;
    }
    
    @Override
    public Item getChestplate()
    {
        return TFItems.subwooferChestplate;
    }
    
    @Override
    public Item getLeggings()
    {
        return TFItems.subwooferLeggings;
    }
    
    @Override
    public Item getBoots()
    {
        return TFItems.subwooferBoots;
    }
}