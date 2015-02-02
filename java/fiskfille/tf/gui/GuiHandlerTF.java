package fiskfille.tf.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import fiskfille.tf.block.TFBlocks;
import fiskfille.tf.container.ContainerDisplayPillar;
import fiskfille.tf.tileentity.TileEntityDisplayPillar;

public class GuiHandlerTF implements IGuiHandler
{
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getTileEntity(x, y, z);
		
		switch (id)
		{
			case 0: return id == 0 &&  world.getBlock(x, y, z) == TFBlocks.displayPillar ? new ContainerDisplayPillar(player.inventory, (TileEntityDisplayPillar)tile_entity) : null;
		}
		return null;
	}

	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getTileEntity(x, y, z);
		
		switch (id)
		{
			case 0: return id == 0 &&  world.getBlock(x, y, z) == TFBlocks.displayPillar ? new GuiDisplayPillar(player.inventory, (TileEntityDisplayPillar)tile_entity) : null;
		}
		return null;
	}
}