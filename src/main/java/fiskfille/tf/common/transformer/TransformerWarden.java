package fiskfille.tf.common.transformer;

import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerJet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

/**
 * @author gegy1000
 */
public class TransformerWarden extends TransformerJet
{
    public TransformerWarden()
    {
        super("Warden");
    }

    @Override
    public Item getHelmet()
    {
        return TFItems.wardenHelmet;
    }

    @Override
    public Item getChestplate()
    {
        return TFItems.wardenChestplate;
    }

    @Override
    public Item getLeggings()
    {
        return TFItems.wardenLeggings;
    }

    @Override
    public Item getBoots()
    {
        return TFItems.wardenBoots;
    }

    @Override
    public void tick(EntityPlayer player, int timer)
    {
        player.addStat(TFAchievements.warden, 1);
    }

    @Override
    public int getAltModeCount()
    {
        return 2;
    }
}