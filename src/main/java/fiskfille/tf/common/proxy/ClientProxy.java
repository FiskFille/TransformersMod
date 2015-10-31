package fiskfille.tf.common.proxy;

import java.lang.reflect.Field;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.render.entity.RenderBassCharge;
import fiskfille.tf.client.render.entity.RenderBlank;
import fiskfille.tf.client.render.entity.RenderLaser;
import fiskfille.tf.client.render.entity.RenderMissile;
import fiskfille.tf.client.render.entity.RenderTankShell;
import fiskfille.tf.client.render.entity.RenderTransformer;
import fiskfille.tf.client.render.entity.RenderTransformiumSeedEntity;
import fiskfille.tf.client.render.entity.player.RenderCustomPlayer;
import fiskfille.tf.client.render.item.RenderItemArmor;
import fiskfille.tf.client.render.item.RenderItemBassBlaster;
import fiskfille.tf.client.render.item.RenderItemControlPanel;
import fiskfille.tf.client.render.item.RenderItemDisplayStation;
import fiskfille.tf.client.render.item.RenderItemDisplayVehicle;
import fiskfille.tf.client.render.item.RenderItemFlamethrower;
import fiskfille.tf.client.render.item.RenderItemPurgesKatana;
import fiskfille.tf.client.render.item.RenderItemSkystrikesCrossbow;
import fiskfille.tf.client.render.item.RenderItemTileEntity;
import fiskfille.tf.client.render.item.RenderItemVurpsSniper;
import fiskfille.tf.client.render.tileentity.RenderAssemblyTable;
import fiskfille.tf.client.render.tileentity.RenderControlPanel;
import fiskfille.tf.client.render.tileentity.RenderCrystal;
import fiskfille.tf.client.render.tileentity.RenderDisplayPillar;
import fiskfille.tf.client.render.tileentity.RenderDisplayStation;
import fiskfille.tf.client.render.tileentity.RenderEnergonProcessor;
import fiskfille.tf.client.render.tileentity.RenderTransformiumSeed;
import fiskfille.tf.client.tick.ClientTickHandler;
import fiskfille.tf.client.tutorial.TutorialHandler;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.entity.EntityBassCharge;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;
import fiskfille.tf.common.entity.EntityLaser;
import fiskfille.tf.common.entity.EntityLaserBeam;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.entity.EntityTankShell;
import fiskfille.tf.common.entity.EntityTransformer;
import fiskfille.tf.common.entity.EntityTransformiumSeed;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.tileentity.TileEntityAssemblyTable;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.common.tileentity.TileEntityCrystal;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;

public class ClientProxy extends CommonProxy
{
    public static Field camRollField;
    private Minecraft mc = Minecraft.getMinecraft();

    public static RenderCustomPlayer renderCustomPlayer;

    @Override
    public World getWorld(MessageContext ctx)
    {
        return mc.theWorld;
    }

    @Override
    public EntityPlayer getPlayer(MessageContext ctx)
    {
        return mc.thePlayer;
    }

    @Override
    public void preInit()
    {
        TutorialHandler.init();
    }

    @Override
    public void registerRenderInformation()
    {
        renderCustomPlayer = new RenderCustomPlayer();
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
        RenderingRegistry.registerEntityRenderingHandler(EntityTransformiumSeed.class, new RenderTransformiumSeedEntity());
        RenderingRegistry.registerEntityRenderingHandler(EntityFlamethrowerFire.class, new RenderBlank());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserBeam.class, new RenderBlank());
        RenderingRegistry.registerEntityRenderingHandler(EntityBassCharge.class, new RenderBassCharge());
        RenderingRegistry.registerEntityRenderingHandler(EntityTransformer.class, new RenderTransformer());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayPillar.class, new RenderDisplayPillar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new RenderCrystal());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTransformiumSeed.class, new RenderTransformiumSeed());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayStation.class, new RenderDisplayStation());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergonProcessor.class, new RenderEnergonProcessor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAssemblyTable.class, new RenderAssemblyTable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityControlPanel.class, new RenderControlPanel());

        MinecraftForgeClient.registerItemRenderer(TFItems.skystrikesCrossbow, new RenderItemSkystrikesCrossbow());
        MinecraftForgeClient.registerItemRenderer(TFItems.purgesKatana, new RenderItemPurgesKatana());
        MinecraftForgeClient.registerItemRenderer(TFItems.vurpsSniper, new RenderItemVurpsSniper());
        MinecraftForgeClient.registerItemRenderer(TFItems.subwoofersBassBlaster, new RenderItemBassBlaster());
        MinecraftForgeClient.registerItemRenderer(TFItems.cloudtrapsFlamethrower, new RenderItemFlamethrower());

        MinecraftForgeClient.registerItemRenderer(TFItems.displayVehicle, new RenderItemDisplayVehicle());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.displayStation), new RenderItemDisplayStation());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.energonProcessor), new RenderItemTileEntity(new TileEntityEnergonProcessor()));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.assemblyTable), new RenderItemTileEntity(new TileEntityAssemblyTable()));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.groundBridgeControlPanel), new RenderItemControlPanel());

        registerArmorRenderer(TFItems.cloudtrapHelmet, TFItems.cloudtrapChestplate, TFItems.cloudtrapLeggings, TFItems.cloudtrapBoots);
        registerArmorRenderer(TFItems.skystrikeHelmet, TFItems.skystrikeChestplate, TFItems.skystrikeLeggings, TFItems.skystrikeBoots);
        registerArmorRenderer(TFItems.purgeHelmet, TFItems.purgeChestplate, TFItems.purgeLeggings, TFItems.purgeBoots);
        registerArmorRenderer(TFItems.vurpHelmet, TFItems.vurpChestplate, TFItems.vurpLeggings, TFItems.vurpBoots);
        registerArmorRenderer(TFItems.subwooferHelmet, TFItems.subwooferChestplate, TFItems.subwooferLeggings, TFItems.subwooferBoots);

        TFModelRegistry.registerModels();
    }

    public void registerArmorRenderer(ItemTransformerArmor helmet, ItemTransformerArmor chest, ItemTransformerArmor legs, ItemTransformerArmor boots)
    {
        MinecraftForgeClient.registerItemRenderer(helmet, new RenderItemArmor(helmet.getTransformer(), 0));
        MinecraftForgeClient.registerItemRenderer(chest, new RenderItemArmor(chest.getTransformer(), 1));
        MinecraftForgeClient.registerItemRenderer(legs, new RenderItemArmor(legs.getTransformer(), 2));
        MinecraftForgeClient.registerItemRenderer(boots, new RenderItemArmor(boots.getTransformer(), 3));
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