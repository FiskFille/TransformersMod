package fiskfille.tf.common.transformer;

import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

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

    @Override
    public void tick(EntityPlayer player, int timer)
    {
        super.tick(player, timer);

        player.addStat(TFAchievements.purge, 1);
    }
}