package fiskfille.tf.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;

public class GuiHandlerTF implements IGuiHandler
{
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        
        switch (id)
        {
        	case 0: return id == 0 && world.getBlock(x, y, z) == TFBlocks.energonProcessor ? new ContainerEnergonProcessor(player.inventory, (TileEntityEnergonProcessor)tileEntity) : null;
        }
        
        return null;
    }
    
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        
        switch (id)
        {
        	case 0: return id == 0 && world.getBlock(x, y, z) == TFBlocks.energonProcessor ? new GuiEnergonProcessor(player.inventory, (TileEntityEnergonProcessor)tileEntity) : null;
        }
        
        return null;
    }
}