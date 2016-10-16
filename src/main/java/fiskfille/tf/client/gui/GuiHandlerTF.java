package fiskfille.tf.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.ContainerAssemblyTable;
import fiskfille.tf.common.container.ContainerDisplayStation;
import fiskfille.tf.common.container.ContainerDisplayStationArmor;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.container.ContainerTransmitter;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class GuiHandlerTF implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (id)
        {
            case 0:
                return world.getBlock(x, y, z) == TFBlocks.displayStation ? new ContainerDisplayStation(player.inventory, (TileEntityDisplayStation) tile) : null;
            case 1:
                return world.getBlock(x, y, z) == TFBlocks.energonProcessor ? new ContainerEnergonProcessor(player.inventory, (TileEntityEnergonProcessor) tile) : null;
            case 2:
                return world.getBlock(x, y, z) == TFBlocks.assemblyTable ? new ContainerAssemblyTable(player.inventory, world, x, y, z) : null;
            case 3:
                return world.getBlock(x, y, z) == TFBlocks.displayStation ? new ContainerDisplayStationArmor(player.inventory, (TileEntityDisplayStation) tile) : null;
            case 4:
                return world.getBlock(x, y, z) == TFBlocks.transmitter ? new ContainerTransmitter(player.inventory, (TileEntityTransmitter) tile) : null;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (id)
        {
            case 0:
                return world.getBlock(x, y, z) == TFBlocks.displayStation ? new GuiDisplayStation(player.inventory, (TileEntityDisplayStation) tile) : null;
            case 1:
                return world.getBlock(x, y, z) == TFBlocks.energonProcessor ? new GuiEnergonProcessor(player.inventory, (TileEntityEnergonProcessor) tile) : null;
            case 2:
                return world.getBlock(x, y, z) == TFBlocks.assemblyTable ? new GuiAssemblyTable(player.inventory, world, x, y, z) : null;
            case 3:
                return world.getBlock(x, y, z) == TFBlocks.displayStation ? new GuiDisplayStationArmor(player.inventory, (TileEntityDisplayStation) tile) : null;
            case 4:
                return world.getBlock(x, y, z) == TFBlocks.transmitter ? new GuiTransmitter(player.inventory, (TileEntityTransmitter) tile) : null;
            case 5:
                return world.getTileEntity(x, y, z) instanceof IEnergyTransmitter ? new GuiSelectReceivers(tile) : null;
        }

        return null;
    }
}
