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
public abstract class TransformerCar extends Transformer
{
    public TransformerCar(String name)
    {
        super(name);
    }
    
    @Override
    public float fall(EntityPlayer player, float distance)
    {
        return TFDataManager.isInVehicleMode(player) ? distance / 2 : super.fall(player, distance);
    }
    
    @Override
    public void tick(EntityPlayer player, int timer)
    {
        boolean vehicle = TFDataManager.isInVehicleMode(player);
        
        if (timer >= 14 && vehicle)
        {
        	player.motionY += 0.225;
        }
        
        if (vehicle && timer == 0)
        {
            IAttributeInstance entityAttribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            entityAttribute.setBaseValue(0.0D);
        }
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
        return -1.1F;
    }
    
    @Override
    public void updateMovement(EntityPlayer player)
    {
        TFMotionManager.motionTruck(player, 60, 100, 20, 20);
    }
    
    @Override
    public boolean canUseNitro(EntityPlayer player)
    {
        return !TFDataManager.isInStealthMode(player);
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
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord + 0.35F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
        
        for (int i = 0; i < 10; ++i)
        {
            Vec3 side = NitroParticleHandler.getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord + 0.35F, side.zCoord, rand.nextFloat() / 10, rand.nextFloat() / 10 + 0.05F, rand.nextFloat() / 10);
        }
    }
}
