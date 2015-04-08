package fiskfille.tf.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.network.MessageVurpSniperShoot;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFShootManager;

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
        
        if (TFHelper.isPlayerVurp(player) && !TFDataManager.isInVehicleMode(player) && (player.inventory.hasItem(TFItems.miniMissile) || isCreativeMode))
        {
            stack.damageItem(1, player);
            
            if (world.isRemote)
            {
                if (TFShootManager.shotsLeft > 0) //TODO-TF Shots Left API
                {
                    if (TFShootManager.shootCooldown <= 0)
                    {
                        TFNetworkManager.networkWrapper.sendToServer(new MessageVurpSniperShoot(player));
                        
                        TFShootManager.shotsLeft--;
                        
                        if (TFShootManager.shotsLeft <= 0)
                        {
                            TFShootManager.shootCooldown = 20;
                            TFShootManager.reloading = true;
                        }
                    }
                }
                else
                {
                    if (!TFShootManager.reloading)
                    {
                        TFShootManager.shootCooldown = 20;
                        TFShootManager.reloading = true;
                    }
                }
            }
        }
        
        return stack;
    }
}