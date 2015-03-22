package fiskfille.tf.common.transformer;

import net.minecraft.item.Item;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.TFModelRegistry;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerCar;

public class TransformerVurp extends TransformerCar
{
    public TransformerVurp()
    {
        super("Vurp");
    }
    
    @Override
    public Item getHelmet()
    {
        return TFItems.vurpHelmet;
    }
    
    @Override
    public Item getChestplate()
    {
        return TFItems.vurpChestplate;
    }
    
    @Override
    public Item getLeggings()
    {
        return TFItems.vurpLeggings;
    }
    
    @Override
    public Item getBoots()
    {
        return TFItems.vurpBoots;
    }
}