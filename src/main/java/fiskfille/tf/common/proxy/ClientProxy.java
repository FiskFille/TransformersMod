package fiskfille.tf.common.proxy;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.transformer.TFModelRegistry;
import fiskfille.tf.client.render.entity.RenderBassCharge;
import fiskfille.tf.client.render.entity.RenderCustomPlayer;
import fiskfille.tf.client.render.entity.RenderBlank;
import fiskfille.tf.client.render.entity.RenderMiniMissile;
import fiskfille.tf.client.render.entity.RenderMissile;
import fiskfille.tf.client.render.entity.RenderTankShell;
import fiskfille.tf.client.render.entity.RenderTransformiumSeedEntity;
import fiskfille.tf.client.render.item.RenderItemBassBlaster;
import fiskfille.tf.client.render.item.RenderItemDisplayVehicle;
import fiskfille.tf.client.render.item.RenderItemFlamethrower;
import fiskfille.tf.client.render.item.RenderItemPurgesKatana;
import fiskfille.tf.client.render.item.RenderItemSkystrikesCrossbow;
import fiskfille.tf.client.render.item.RenderItemVurpsSniper;
import fiskfille.tf.client.render.tileentity.RenderCrystal;
import fiskfille.tf.client.render.tileentity.RenderDisplayPillar;
import fiskfille.tf.client.render.tileentity.RenderTransformiumSeed;
import fiskfille.tf.client.tick.ClientTickHandler;
import fiskfille.tf.common.entity.EntityBassCharge;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;
import fiskfille.tf.common.entity.EntityLaserBeam;
import fiskfille.tf.common.entity.EntityMiniMissile;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.entity.EntityTankShell;
import fiskfille.tf.common.entity.EntityTransformiumSeed;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tileentity.TileEntityCrystal;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;

public class ClientProxy extends CommonProxy
{
    public static Field camRollField;
    private Minecraft mc = Minecraft.getMinecraft();
    
    @Override
    public World getWorld()
    {
        return mc.theWorld;
    }
    
    @Override
    public EntityPlayer getPlayer()
    {
        return mc.thePlayer;
    }
    
    @Override
    public void registerRenderInformation()
    {
        RenderCustomPlayer renderCustomPlayer = new RenderCustomPlayer();
        renderCustomPlayer.setRenderManager(RenderManager.instance);
        RenderManager.instance.entityRenderMap.put(EntityPlayer.class, renderCustomPlayer);
        
        int i = 0;
        for (Field curField : EntityRenderer.class.getDeclaredFields())
        {
            if (curField.getType() == float.class)
            {
                if (++i == 15)
                {
                    camRollField = curField;
                    curField.setAccessible(true);
                }
            }
        }
        
        RenderingRegistry.registerEntityRenderingHandler(EntityTankShell.class, new RenderTankShell());
        RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderMissile());
        RenderingRegistry.registerEntityRenderingHandler(EntityMiniMissile.class, new RenderMiniMissile());
        RenderingRegistry.registerEntityRenderingHandler(EntityTransformiumSeed.class, new RenderTransformiumSeedEntity());
        RenderingRegistry.registerEntityRenderingHandler(EntityFlamethrowerFire.class, new RenderBlank());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserBeam.class, new RenderBlank());
        RenderingRegistry.registerEntityRenderingHandler(EntityBassCharge.class, new RenderBassCharge());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayPillar.class, new RenderDisplayPillar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new RenderCrystal());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTransformiumSeed.class, new RenderTransformiumSeed());
        
        MinecraftForgeClient.registerItemRenderer(TFItems.skystrikesCrossbow, new RenderItemSkystrikesCrossbow());
        MinecraftForgeClient.registerItemRenderer(TFItems.purgesKatana, new RenderItemPurgesKatana());
        MinecraftForgeClient.registerItemRenderer(TFItems.vurpsSniper, new RenderItemVurpsSniper());
        MinecraftForgeClient.registerItemRenderer(TFItems.subwoofersBassBlaster, new RenderItemBassBlaster());
        MinecraftForgeClient.registerItemRenderer(TFItems.cloudtrapsFlamethrower, new RenderItemFlamethrower());
        
        MinecraftForgeClient.registerItemRenderer(TFItems.displayVehicle, new RenderItemDisplayVehicle());
        
        TFModelRegistry.registerModels();
    }
    
    @Override
    public void registerTickHandlers()
    {
        tickHandler = new ClientTickHandler();
    }
    
    @Override
    public void registerKeyBinds()
    {
        TFKeyBinds.register();
    }
}