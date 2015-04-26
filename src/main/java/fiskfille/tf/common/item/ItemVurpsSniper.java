package fiskfille.tf.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import fiskfille.tf.common.entity.EntityLaser;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.helper.TFHelper;

public class ItemVurpsSniper extends ItemSword
{
    public ItemVurpsSniper(ToolMaterial material)
    {
        super(material);
        this.setMaxDamage(1500);
    }
    
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        boolean isCreativeMode = player.capabilities.isCreativeMode;
        
        if (TFHelper.isPlayerVurp(player) && !TFDataManager.isInVehicleMode(player) && (player.inventory.hasItem(TFItems.energonCrystalPiece) || isCreativeMode))
        {
            stack.damageItem(1, player);
            
            if (!world.isRemote)
            {
                EntityLaser entity = new EntityLaser(world, player);
                entity.posY-=1D;
                world.spawnEntityInWorld(entity);
                
                if (!isCreativeMode)
                {
                    player.inventory.consumeInventoryItem(TFItems.energonCrystalPiece);
                }
            }
        }
        
        return stack;
    }
}