package fiskfille.tf.common.transformer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.TransformerJet;

/**
 * @author gegy1000
 */
public class TransformerSkystrike extends TransformerJet
{
    public TransformerSkystrike()
    {
        super("Skystrike");
    }
    
    @Override
    public Item getHelmet()
    {
        return TFItems.skystrikeHelmet;
    }
    
    @Override
    public Item getChestplate()
    {
        return TFItems.skystrikeChestplate;
    }
    
    @Override
    public Item getLeggings()
    {
        return TFItems.skystrikeLeggings;
    }
    
    @Override
    public Item getBoots()
    {
        return TFItems.skystrikeBoots;
    }
    
    @Override
    public void onJump(EntityPlayer player)
    {
        player.motionY += 0.205D;
    }
    
    @Override
    public void tick(EntityPlayer player, int timer)
    {
        if(timer == 20)
        {
            if (!player.capabilities.isFlying && !(TFDataManager.getTransformationTimer(player) < 10))
            {
                if (player.motionY < 0.0D)
                {
                    player.motionY *= 0.85F;
                }
                else
                {
                    player.motionY += 0.02D;
                }
            }
        }
    }
}