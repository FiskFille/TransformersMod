package fiskfille.tf.common.transformer;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * @author gegy1000
 */
public class TransformerPurge extends TransformerTank
{
    public TransformerPurge()
    {
        super(1, new ResourceLocation(TransformersMod.MODID, "purge"));
    }

    @Override
    public Item getHelmet()
    {
        return TFItems.PURGE_HELMET;
    }

    @Override
    public Item getChestplate()
    {
        return TFItems.PURGE_CHEST;
    }

    @Override
    public Item getLeggings()
    {
        return TFItems.PURGE_LEGGINGS;
    }

    @Override
    public Item getBoots()
    {
        return TFItems.PURGE_BOOTS;
    }

    @Override
    public float getHeightOffset(EntityPlayer player, int altMode)
    {
        return -0.1F;
    }

    @Override
    public float getVehicleHeightOffset(EntityPlayer player, int altMode)
    {
        return -1.1F;
    }

    /*@Override
    public void tick(EntityPlayer player, float timer)
    {
        super.tick(player, timer);

        player.addStat(TFAchievements.purge, 1);
    }*/
}
