package fiskfille.tf.common.transformer.base;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.network.MessageVehicleNitro;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.proxy.ClientProxy;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFModelHelper;

public abstract class TransformerJet extends Transformer
{
    public TransformerJet(String name)
    {
        super(name);
    }
    
    @Override
    public float fall(EntityPlayer player, float distance)
    {
        return 0;
    }
    
    @Override
    public float getCameraYOffset(EntityPlayer player)
    {
        return 0;
    }
    
    @Override
    public float getVehicleCameraYOffset(EntityPlayer player)
    {
        return 0;
    }
    
    @Override
    public float getThirdPersonDistance(EntityPlayer player)
    {
        return 4.0F;
    }
    
    @Override
    public void updateMovement(EntityPlayer player)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        
        boolean moveForward = minecraft.gameSettings.keyBindForward.getIsKeyPressed();
        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || minecraft.gameSettings.keyBindSprint.getIsKeyPressed();
        
        VehicleMotion motion = TFMotionManager.getTransformerPlayer(player);
        
        int nitro = 0;
        double vel = 0;
        
        if (motion != null)
        {
            nitro = motion.getNitro();
            vel = motion.getForwardVelocity();
            
            boolean prevNitro = TFMotionManager.prevNitro;
            
            double increment = ((nitroPressed && nitro > 0 ? 6.84D : 1.36D) - vel) / 10 + 0.001D;
            
            if (moveForward && vel <= 1.41D)
            {
                vel += increment;
            }
            if (vel > 0.14D && !moveForward)
            {
                vel -= 0.14D;
            }
            if (vel <= 0.14D)
            {
                vel = 0.14D;
            }
            
            if ((player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) != Blocks.air || player.worldObj.getBlock((int) player.posX, (int) player.posY - 2, (int) player.posZ) != Blocks.air || player.worldObj.getBlock((int) player.posX, (int) player.posY - 3, (int) player.posZ) != Blocks.air))
            {
                player.setPosition(player.posX, player.posY + 0.8, player.posZ);
            }
            
            Vec3 vec3 = TFMotionManager.getFrontCoords(player, vel, true);
            player.motionX = (vec3.xCoord - player.posX);
            player.motionY = (vec3.yCoord - player.posY) + getMotionYOffset();
            player.motionZ = (vec3.zCoord - player.posZ);
            if (vel <= 0.09F)
            {
                vel = 0.09F;
            }
            if (vel > 1.41F)
            {
                vel = 1.41F;
            }
            
            if (player == Minecraft.getMinecraft().thePlayer)
            {
                if (nitro > 0 && nitroPressed && moveForward)
                {
                    if (!player.capabilities.isCreativeMode)
                        --nitro;
                    
                    if (!prevNitro)
                    {
                        TFNetworkManager.networkWrapper.sendToServer(new MessageVehicleNitro(player, true));
                        TFMotionManager.prevNitro = true;
                    }
                    
                    for (int i = 0; i < 4; ++i)
                    {
                        Vec3 side = TFMotionManager.getBackSideCoords(player, 0.15F, i < 2, -1.5, true);
                        Random rand = new Random();
                        player.worldObj.spawnParticle("flame", side.xCoord, side.yCoord - 0.2F, side.zCoord, rand.nextFloat() / 20, -0.2F + rand.nextFloat() / 20, rand.nextFloat() / 20);
                    }
                }
                else
                {
                    if (prevNitro)
                    {
                        TFNetworkManager.networkWrapper.sendToServer(new MessageVehicleNitro(player, false));
                        TFMotionManager.prevNitro = false;
                    }
                }
                
                try
                {
                    if (TFConfig.rollWithJet)
                    {
                        EntityRenderer entityRenderer = Minecraft.getMinecraft().entityRenderer;
                        
                        ModelBiped modelBipedMain = TFModelHelper.modelBipedMain;
                        
                        float yaw = (modelBipedMain.bipedHead.rotateAngleY - modelBipedMain.bipedBody.rotateAngleY) * 100;
                        
                        ClientProxy.camRollField.set(entityRenderer, yaw);
                    }
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
            
            motion.setNitro(nitro);
            motion.setForwardVelocity(vel);
        }
    }
    
    @Override
    public boolean canShoot(EntityPlayer player)
    {
        return true;
    }
    
    @Override
    public Item getShootItem()
    {
        return TFItems.missile;
    }
    
    @Override
    public Entity getShootEntity(EntityPlayer player)
    {
        return new EntityMissile(player.worldObj, player, 3, TFConfig.allowMissileExplosions, TFDataManager.isInStealthMode(player));
    }
    
    @Override
    public void doNitroParticles(EntityPlayer player)
    {
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = NitroParticleHandler.getBackSideCoords(player, 0.15F, i < 2, -1, true);
            Random rand = new Random();
            player.worldObj.spawnParticle("flame", side.xCoord, side.yCoord + 1.5F, side.zCoord, rand.nextFloat() / 20, -0.2F + rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
    }
    
    public float getMotionYOffset()
    {
        return -0.1f;
    }
}
