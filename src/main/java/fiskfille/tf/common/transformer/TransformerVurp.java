package fiskfille.tf.common.transformer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.common.entity.EntityLaser;
import fiskfille.tf.common.item.ItemVurpsSniper;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.TransformerCar;

/**
 * @author gegy1000, FiskFille
 */
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
	
	@Override
    public Item getShootItem()
    {
        return TFItems.energonCrystalPiece;
    }
    
    @Override
    public Entity getShootEntity(EntityPlayer player)
    {
        EntityLaser entityLaser = new EntityLaser(player.worldObj, player);
        entityLaser.posY -= 0.3;
        return entityLaser;
    }
    
    @Override
    public String getShootSound()
    {
    	return "random.fizz";
    }
    
    @Override
    public float getShootVolume()
    {
    	return 0.3F;
    }
    
    @Override
    public int getShots()
    {
        return 64;
    }
    
    @Override
    public boolean hasRapidFire()
    {
    	return true;
    }

	@Override
	public void tick(EntityPlayer player, int timer)
	{
		super.tick(player, timer);
		
		ItemStack heldItem = player.getHeldItem();

		boolean holdingSniper = heldItem != null && heldItem.getItem() instanceof ItemVurpsSniper;

		int zoomTimer = TFDataManager.getZoomTimer(player);

		PotionEffect activePotionEffect = player.getActivePotionEffect(Potion.nightVision);
		
		if(activePotionEffect == null || (activePotionEffect != null && activePotionEffect.getDuration() == 0))
		{
			if (holdingSniper && zoomTimer > 7)
			{
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1, 0));
			}
			else
			{
				player.removePotionEffect(Potion.nightVision.id);
			}
		}

		if(player.worldObj.isRemote)
		{
			if (holdingSniper && TFKeyBinds.keyBindingZoom.getIsKeyPressed())
			{
				if (zoomTimer < 10)
				{
					TFDataManager.setZoomTimer(player, zoomTimer + 1);
				}
			}
			else
			{
				if (zoomTimer > 0)
				{
					TFDataManager.setZoomTimer(player, zoomTimer - 1);
				}
			}
		}
	}
}