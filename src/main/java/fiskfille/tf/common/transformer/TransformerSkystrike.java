package fiskfille.tf.common.transformer;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * @author gegy1000
 */
public class TransformerSkystrike extends TransformerJet
{
    public TransformerSkystrike()
    {
        super(0, new ResourceLocation(TransformersMod.MODID, "skystrike"));
    }

    @Override
    public Item getHelmet()
    {
        return TFItems.SKYSTRIKE_HELMET;
    }

    @Override
    public Item getChestplate()
    {
        return TFItems.SKYSTRIKE_CHEST;
    }

    @Override
    public Item getLeggings()
    {
        return TFItems.SKYSTRIKE_LEGGINGS;
    }

    @Override
    public Item getBoots()
    {
        return TFItems.SKYSTRIKE_BOOTS;
    }

    @Override
    public float getHeightOffset(EntityPlayer player, int altMode)
    {
        return -0.1F;
    }

    @Override
    public float getVehicleHeightOffset(EntityPlayer player, int altMode)
    {
        return -1.25F;
    }

    @Override
    public boolean onJump(EntityPlayer player)
    {
        player.motionY += 0.205D;
        return true;
    }

    /*@Override
    public void tick(EntityPlayer player, float timer)
    {
        player.addStat(TFAchievements.skystrike, 1);
    }*/
}
