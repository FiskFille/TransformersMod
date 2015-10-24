package fiskfille.tf.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.ContainerAssemblyTable;
import fiskfille.tf.common.container.ContainerDisplayStation;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandlerTF implements IGuiHandler
{
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        switch (id)
        {
            case 0:
                return world.getBlock(x, y, z) == TFBlocks.displayStation ? new ContainerDisplayStation(player.inventory, (TileEntityDisplayStation) tileEntity) : null;
            case 1:
                return world.getBlock(x, y, z) == TFBlocks.energonProcessor ? new ContainerEnergonProcessor(player.inventory, (TileEntityEnergonProcessor) tileEntity) : null;
            case 2:
                return world.getBlock(x, y, z) == TFBlocks.assemblyTable ? new ContainerAssemblyTable(player.inventory, world, x, y, z) : null;
        }

        return null;
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        switch (id)
        {
            case 0:
                return world.getBlock(x, y, z) == TFBlocks.displayStation ? new GuiDisplayStation(player.inventory, (TileEntityDisplayStation) tileEntity) : null;
            case 1:
                return world.getBlock(x, y, z) == TFBlocks.energonProcessor ? new GuiEnergonProcessor(player.inventory, (TileEntityEnergonProcessor) tileEntity) : null;
            case 2:
                return world.getBlock(x, y, z) == TFBlocks.assemblyTable ? new GuiAssemblyTable(player.inventory, world, x, y, z) : null;
        }

        return null;
    }
}
