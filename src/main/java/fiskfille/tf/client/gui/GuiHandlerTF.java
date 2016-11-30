package fiskfille.tf.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.network.IGuiHandler;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.ContainerAssemblyTable;
import fiskfille.tf.common.container.ContainerDisplayStation;
import fiskfille.tf.common.container.ContainerDisplayStationArmor;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.container.ContainerGroundBridge;
import fiskfille.tf.common.container.ContainerTransmitter;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class GuiHandlerTF implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        WorldServer worldserver = DimensionManager.getWorld(world.provider.dimensionId);
        TileEntity tile = worldserver.getTileEntity(x, y, z);
        int metadata = worldserver.getBlockMetadata(x, y, z);

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
            return worldserver.getBlock(x, y, z) == TFBlocks.groundBridgeControlPanel ? new ContainerGroundBridge(player.inventory, (TileEntityControlPanel) tile) : null;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        WorldServer worldserver = DimensionManager.getWorld(world.provider.dimensionId);
        TileEntity tile = worldserver.getTileEntity(x, y, z);
        int metadata = worldserver.getBlockMetadata(x, y, z);

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
        case 5:
            return worldserver.getTileEntity(x, y, z) instanceof IEnergyTransmitter ? new GuiSelectReceivers(tile) : null;
        case 6:
            return worldserver.getBlock(x, y, z) == TFBlocks.groundBridgeControlPanel ? new GuiGroundBridge(player.inventory, (TileEntityControlPanel) tile) : null;
        }

        return null;
    }
}
