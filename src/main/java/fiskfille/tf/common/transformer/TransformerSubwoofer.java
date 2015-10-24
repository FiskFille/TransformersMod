package fiskfille.tf.common.transformer;

import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerTruck;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

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

    @Override
    public void tick(EntityPlayer player, int timer)
    {
        super.tick(player, timer);

        player.addStat(TFAchievements.subwoofer, 1);
    }
}