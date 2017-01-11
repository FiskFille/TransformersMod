package fiskfille.tf.common.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.TFReflection;
import fiskfille.tf.client.gui.GuiGroundBridge;
import fiskfille.tf.client.gui.GuiOverlay;
import fiskfille.tf.client.gui.GuiSelectReceivers;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.render.block.RenderBlockEnergonTank;
import fiskfille.tf.client.render.block.RenderBlockGroundBridgeFrame;
import fiskfille.tf.client.render.entity.RenderBassCharge;
import fiskfille.tf.client.render.entity.RenderBlank;
import fiskfille.tf.client.render.entity.RenderLaser;
import fiskfille.tf.client.render.entity.RenderMissile;
import fiskfille.tf.client.render.entity.RenderTankShell;
import fiskfille.tf.client.render.entity.RenderTransformiumSeedEntity;
import fiskfille.tf.client.render.item.RenderItemArmor;
import fiskfille.tf.client.render.item.RenderItemBassBlaster;
import fiskfille.tf.client.render.item.RenderItemColumn;
import fiskfille.tf.client.render.item.RenderItemControlPanel;
import fiskfille.tf.client.render.item.RenderItemDataCore;
import fiskfille.tf.client.render.item.RenderItemDisplayStation;
import fiskfille.tf.client.render.item.RenderItemDisplayVehicle;
import fiskfille.tf.client.render.item.RenderItemEnergonTank;
import fiskfille.tf.client.render.item.RenderItemFlamethrower;
import fiskfille.tf.client.render.item.RenderItemPowerCanister;
import fiskfille.tf.client.render.item.RenderItemPurgesKatana;
import fiskfille.tf.client.render.item.RenderItemRelayTower;
import fiskfille.tf.client.render.item.RenderItemSkystrikesCrossbow;
import fiskfille.tf.client.render.item.RenderItemTileEntity;
import fiskfille.tf.client.render.item.RenderItemTransmitter;
import fiskfille.tf.client.render.item.RenderItemVurpsSniper;
import fiskfille.tf.client.render.tileentity.RenderAlloyCrucible;
import fiskfille.tf.client.render.tileentity.RenderAssemblyTable;
import fiskfille.tf.client.render.tileentity.RenderColumn;
import fiskfille.tf.client.render.tileentity.RenderControlPanel;
import fiskfille.tf.client.render.tileentity.RenderCrystal;
import fiskfille.tf.client.render.tileentity.RenderDisplayPillar;
import fiskfille.tf.client.render.tileentity.RenderDisplayStation;
import fiskfille.tf.client.render.tileentity.RenderEmlTest;
import fiskfille.tf.client.render.tileentity.RenderEnergonProcessor;
import fiskfille.tf.client.render.tileentity.RenderEnergonTank;
import fiskfille.tf.client.render.tileentity.RenderGroundBridgeTeleporter;
import fiskfille.tf.client.render.tileentity.RenderRelayTower;
import fiskfille.tf.client.render.tileentity.RenderTransformiumSeed;
import fiskfille.tf.client.render.tileentity.RenderTransmitter;
import fiskfille.tf.client.tutorial.TutorialHandler;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.InventoryGroundBridge;
import fiskfille.tf.common.entity.EntityBassCharge;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;
import fiskfille.tf.common.entity.EntityLaser;
import fiskfille.tf.common.entity.EntityLaserBeam;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.entity.EntityTankShell;
import fiskfille.tf.common.entity.EntityTransformiumSeed;
import fiskfille.tf.common.event.ClientEventHandler;
import fiskfille.tf.common.groundbridge.RemoteData;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.tick.ClientTickHandler;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;
import fiskfille.tf.common.tileentity.TileEntityAssemblyTable;
import fiskfille.tf.common.tileentity.TileEntityColumn;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.common.tileentity.TileEntityCrystal;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEmbTest;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;
import fiskfille.tf.common.tileentity.TileEntityRelayTorch;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.List;

public class ClientProxy extends CommonProxy
{
    private Minecraft mc = Minecraft.getMinecraft();

    public static GuiOverlay guiOverlay = new GuiOverlay();

    @Override
    public void preInit()
    {
        super.preInit();
        TFReflection.client();
        TFKeyBinds.register();
        TutorialHandler.init();

        registerEventHandler(new ClientEventHandler());
        registerEventHandler(new ClientTickHandler());
        registerEventHandler(guiOverlay);
    }

    @Override
    public void init()
    {
        super.init();
        TFModelRegistry.registerModels();

        RenderingRegistry.registerEntityRenderingHandler(EntityTankShell.class, new RenderTankShell());
        RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderMissile());
        RenderingRegistry.registerEntityRenderingHandler(EntityTransformiumSeed.class, new RenderTransformiumSeedEntity());
        RenderingRegistry.registerEntityRenderingHandler(EntityFlamethrowerFire.class, new RenderBlank());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserBeam.class, new RenderBlank());
        RenderingRegistry.registerEntityRenderingHandler(EntityBassCharge.class, new RenderBassCharge());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
//        RenderingRegistry.registerEntityRenderingHandler(EntityTransformer.class, new RenderTransformer());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.displayStation), new RenderItemDisplayStation());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.energonProcessor), new RenderItemTileEntity(new TileEntityEnergonProcessor()));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.assemblyTable), new RenderItemTileEntity(new TileEntityAssemblyTable()));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.energonFluidTank), new RenderItemEnergonTank());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.transmitter), new RenderItemTransmitter());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.relayTower), new RenderItemRelayTower());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.relayTorch), new RenderItemTileEntity(new TileEntityRelayTorch()));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.energyColumn), new RenderItemColumn());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFBlocks.groundBridgeControlPanel), new RenderItemControlPanel());
        MinecraftForgeClient.registerItemRenderer(TFItems.displayVehicle, new RenderItemDisplayVehicle());
        MinecraftForgeClient.registerItemRenderer(TFItems.dataCore, new RenderItemDataCore());
        MinecraftForgeClient.registerItemRenderer(TFItems.powerCanister, new RenderItemPowerCanister());
        MinecraftForgeClient.registerItemRenderer(TFItems.skystrikesCrossbow, new RenderItemSkystrikesCrossbow());
        MinecraftForgeClient.registerItemRenderer(TFItems.purgesKatana, new RenderItemPurgesKatana());
        MinecraftForgeClient.registerItemRenderer(TFItems.vurpsSniper, new RenderItemVurpsSniper());
        MinecraftForgeClient.registerItemRenderer(TFItems.subwoofersBassBlaster, new RenderItemBassBlaster());
        MinecraftForgeClient.registerItemRenderer(TFItems.cloudtrapsFlamethrower, new RenderItemFlamethrower());
        registerArmorRenderer(TFItems.cloudtrapHelmet, TFItems.cloudtrapChestplate, TFItems.cloudtrapLeggings, TFItems.cloudtrapBoots);
        registerArmorRenderer(TFItems.skystrikeHelmet, TFItems.skystrikeChestplate, TFItems.skystrikeLeggings, TFItems.skystrikeBoots);
        registerArmorRenderer(TFItems.purgeHelmet, TFItems.purgeChestplate, TFItems.purgeLeggings, TFItems.purgeBoots);
        registerArmorRenderer(TFItems.vurpHelmet, TFItems.vurpChestplate, TFItems.vurpLeggings, TFItems.vurpBoots);
        registerArmorRenderer(TFItems.subwooferHelmet, TFItems.subwooferChestplate, TFItems.subwooferLeggings, TFItems.subwooferBoots);

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayPillar.class, new RenderDisplayPillar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new RenderCrystal());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTransformiumSeed.class, new RenderTransformiumSeed());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayStation.class, new RenderDisplayStation());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergonProcessor.class, new RenderEnergonProcessor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergonTank.class, new RenderEnergonTank());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTransmitter.class, new RenderTransmitter());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRelayTower.class, new RenderRelayTower());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityColumn.class, new RenderColumn());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAssemblyTable.class, new RenderAssemblyTable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlloyCrucible.class, new RenderAlloyCrucible());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityControlPanel.class, new RenderControlPanel());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGroundBridgeTeleporter.class, new RenderGroundBridgeTeleporter());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEmbTest.class, new RenderEmlTest());

        RenderingRegistry.registerBlockHandler(RenderBlockGroundBridgeFrame.instance);
        RenderingRegistry.registerBlockHandler(RenderBlockEnergonTank.instance);
    }

    public void registerArmorRenderer(ItemTransformerArmor helmet, ItemTransformerArmor chest, ItemTransformerArmor legs, ItemTransformerArmor boots)
    {
        MinecraftForgeClient.registerItemRenderer(helmet, new RenderItemArmor(helmet.getTransformer(), 0));
        MinecraftForgeClient.registerItemRenderer(chest, new RenderItemArmor(chest.getTransformer(), 1));
        MinecraftForgeClient.registerItemRenderer(legs, new RenderItemArmor(legs.getTransformer(), 2));
        MinecraftForgeClient.registerItemRenderer(boots, new RenderItemArmor(boots.getTransformer(), 3));
    }

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
    public float getRenderTick()
    {
        return ClientTickHandler.renderTick;
    }

    @Override
    public void runTasks()
    {
        if (mc.thePlayer != null)
        {
            super.runTasks();
        }
    }

    @Override
    public void openSetReceivers(World world, EntityPlayer player, TileEntity tile, List<ChunkCoordinates> grandparents)
    {
        if (mc.thePlayer == player)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiSelectReceivers(tile, grandparents));
        }
    }

    @Override
    public void updateRemote(RemoteData data, boolean open)
    {
        super.updateRemote(data, open);

        if (open)
        {
            if (this.mc.currentScreen == null)
            {
                EntityClientPlayerMP player = mc.thePlayer;
                ItemStack heldItem = player.getHeldItem();

                if (heldItem != null)
                {
                    this.mc.displayGuiScreen(new GuiGroundBridge(player.inventory, new InventoryGroundBridge(player, heldItem), data));
                }
            }
        }
        else
        {
            GuiScreen gui = this.mc.currentScreen;

            if (gui instanceof GuiGroundBridge)
            {
                ((GuiGroundBridge) gui).update(data);
            }
        }
    }

    @Override
    public void updateReceivers(TileEntity tileEntity, List<ChunkCoordinates> grandparents)
    {
        super.updateReceivers(tileEntity, grandparents);

        GuiScreen screen = mc.currentScreen;

        if (screen instanceof GuiSelectReceivers)
        {
            GuiSelectReceivers receivers = (GuiSelectReceivers) screen;

            if (receivers.tile.xCoord == tileEntity.xCoord && receivers.tile.yCoord == tileEntity.yCoord && receivers.tile.zCoord == tileEntity.zCoord)
            {
                receivers.update(tileEntity, grandparents);
            }
        }
    }
}
