package fiskfille.tf.common.transformer.base;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.event.CommonEventHandler;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.network.MessageVehicleNitro;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.playerdata.TFPlayerData;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

/**
 * @author gegy1000
 */
public abstract class TransformerTruck extends Transformer
{
    public TransformerTruck(String name)
    {
        super(name);
    }
    
    @Override
    public float fall(EntityPlayer player, float distance)
    {
        return TFDataManager.isInVehicleMode(player) ? distance / 4 : super.fall(player, distance);
    }
    
    @Override
    public boolean hasStealthForce(EntityPlayer player)
    {
        return true;
    }
    
    @Override
    public boolean canJumpAsVehicle(EntityPlayer player)
    {
        return TFDataManager.isInStealthMode(player);
    }
    
    @Override
    public float getCameraYOffset(EntityPlayer player)
    {
        return -1F;
    }
    
    @Override
    public boolean canUseNitro(EntityPlayer player)
    {
        return !TFDataManager.isInStealthMode(player);
    }
    
    @Override
    public void updateMovement(EntityPlayer player)
    {
        TFMotionManager.motion(player, 40, 60, 20, 10, false, true, TFDataManager.isInStealthMode(player), true);
    }
    
    @Override
    public boolean canShoot(EntityPlayer player)
    {
        return TFDataManager.getStealthModeTimer(player) < 5;
    }
    
    @Override
    public Item getShootItem()
    {
        return TFItems.missile;
    }
    
    @Override
    public Entity getShootEntity(EntityPlayer player)
    {
        //        EntityMissile entityMissile = new EntityMissile(player.worldObj, player, 3, TFConfig.allowMissileExplosions, TFDataManager.isInStealthMode(player));
        EntityMissile entityMissile = new EntityMissile(player.worldObj, player, TFConfig.allowMissileExplosions, TFDataManager.isInStealthMode(player));
        //        entityMissile.posY--;
        
        return entityMissile;
    }
    
    @Override
    public int getShots()
    {
        return 8;
    }
    
    @Override
    public void doNitroParticles(EntityPlayer player)
    {
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = NitroParticleHandler.getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord + 0.25F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
        
        for (int i = 0; i < 10; ++i)
        {
            Vec3 side = NitroParticleHandler.getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord + 0.25F, side.zCoord, rand.nextFloat() / 10, rand.nextFloat() / 10 + 0.05F, rand.nextFloat() / 10);
        }
    }
    
    @Override
    public void tick(EntityPlayer player, int timer)
    {
        IAttributeInstance entityAttribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        
        if (TFDataManager.isInVehicleMode(player) && timer == 0)
        {
            entityAttribute.setBaseValue(0.0D);
        }
        else if (timer == 20)
        {
            entityAttribute.setBaseValue(0.1D);
        }
        //            if (!TFPlayerData.getData(player).stealthForce)
        //            {
        //                CommonEventHandler.prevMove = entityAttribute.getAttributeValue();
        //                entityAttribute.setBaseValue(0.0D);
        //            }
        //            else
        //            {
        //                if (CommonEventHandler.prevMove != 0)
        //                {
        //                    entityAttribute.setBaseValue(CommonEventHandler.prevMove);
        //                }
        //            }
    }
}
