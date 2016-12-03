package fiskfille.tf.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.ContainerAssemblyTable;
import fiskfille.tf.common.container.ContainerDisplayStation;
import fiskfille.tf.common.container.ContainerDisplayStationArmor;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.container.ContainerGroundBridge;
import fiskfille.tf.common.container.ContainerTransmitter;
import fiskfille.tf.common.container.InventoryGroundBridge;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.MessageOpenSetReceiversGUI;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import java.util.List;

public class GuiHandlerTF implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        World worldserver = DimensionManager.getWorld(world.provider.dimensionId);
        
        if (id != 6)
        {
            worldserver = world;
        }
        
        TileEntity tile = worldserver.getTileEntity(x, y, z);

        switch (id)
        {
        case 0:
            return worldserver.getBlock(x, y, z) == TFBlocks.displayStation ? new ContainerDisplayStation(player.inventory, (TileEntityDisplayStation) tile) : null;
        case 1:
            return worldserver.getBlock(x, y, z) == TFBlocks.energonProcessor ? new ContainerEnergonProcessor(player.inventory, (TileEntityEnergonProcessor) tile) : null;
        case 2:
            return worldserver.getBlock(x, y, z) == TFBlocks.assemblyTable ? new ContainerAssemblyTable(player.inventory, worldserver, x, y, z) : null;
        case 3:
            return worldserver.getBlock(x, y, z) == TFBlocks.displayStation ? new ContainerDisplayStationArmor(player.inventory, (TileEntityDisplayStation) tile) : null;
        case 4:
            return worldserver.getBlock(x, y, z) == TFBlocks.transmitter ? new ContainerTransmitter(player.inventory, (TileEntityTransmitter) tile) : null;
        case 6:
            return worldserver.getBlock(x, y, z) == TFBlocks.groundBridgeControlPanel && player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote ? new ContainerGroundBridge(player.inventory, new InventoryGroundBridge(player, player.getHeldItem()), (TileEntityControlPanel) tile) : null;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        World worldserver = DimensionManager.getWorld(world.provider.dimensionId);
        
        if (id != 6)
        {
            worldserver = world;
        }
        
        TileEntity tile = worldserver.getTileEntity(x, y, z);

        switch (id)
        {
        case 0:
            return worldserver.getBlock(x, y, z) == TFBlocks.displayStation ? new GuiDisplayStation(player.inventory, (TileEntityDisplayStation) tile) : null;
        case 1:
            return worldserver.getBlock(x, y, z) == TFBlocks.energonProcessor ? new GuiEnergonProcessor(player.inventory, (TileEntityEnergonProcessor) tile) : null;
        case 2:
            return worldserver.getBlock(x, y, z) == TFBlocks.assemblyTable ? new GuiAssemblyTable(player.inventory, worldserver, x, y, z) : null;
        case 3:
            return worldserver.getBlock(x, y, z) == TFBlocks.displayStation ? new GuiDisplayStationArmor(player.inventory, (TileEntityDisplayStation) tile) : null;
        case 4:
            return worldserver.getBlock(x, y, z) == TFBlocks.transmitter ? new GuiTransmitter(player.inventory, (TileEntityTransmitter) tile) : null;
        case 6:
            return worldserver.getBlock(x, y, z) == TFBlocks.groundBridgeControlPanel && player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote ? new GuiGroundBridge(player.inventory, new InventoryGroundBridge(player, player.getHeldItem()), (TileEntityControlPanel) tile) : null;
        }

        return null;
    }

    public static void openSetReceivers(World world, EntityPlayer player, TileEntity tile, List<ChunkCoordinates> grandparents)
    {
        if (world.isRemote)
        {
            openSetReceiversClient(tile, grandparents);
        }
        else
        {
            openSetReceiversServer(player, tile, grandparents);
        }
    }

    private static void openSetReceiversClient(TileEntity tile, List<ChunkCoordinates> grandparents)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiSelectReceivers(tile, grandparents));
    }

    private static void openSetReceiversServer(EntityPlayer player, TileEntity tile, List<ChunkCoordinates> grandparents)
    {
        ChunkCoordinates coordinates = new ChunkCoordinates(tile.xCoord, tile.yCoord, tile.zCoord);
        TFNetworkManager.networkWrapper.sendTo(new MessageOpenSetReceiversGUI(coordinates, grandparents), (EntityPlayerMP) player);
    }
}
