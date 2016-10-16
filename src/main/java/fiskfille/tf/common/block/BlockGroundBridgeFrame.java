package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.render.block.RenderBlockGroundBridgeFrame;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeFrame;

public class BlockGroundBridgeFrame extends Block implements ITileEntityProvider
{
	@SideOnly(Side.CLIENT)
	public IIcon centerIcon;
	
    public BlockGroundBridgeFrame()
    {
        super(Material.iron);
        setHarvestLevel(null, -1);
        setHardness(1);
        setResistance(5);
    }

    @Override
    public int getRenderType()
    {
    	return RenderBlockGroundBridgeFrame.renderId;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	int metadata = world.getBlockMetadata(x, y, z);
    	
    	if (getFrameDirection(world, x, y, z) != null)
    	{
    		if (metadata == 0)
    		{
    			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
    		}
    		else
    		{
    			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
    		}
    		
    		return true;
    	}
    	
        return false;
    }
    
    public static ForgeDirection getFrameDirection(IBlockAccess world, int x, int y, int z)
    {
    	if (BlockGroundBridgeTeleporter.isNorthSouthFacingFramePresent(world, x, y, z))
    	{
    		return ForgeDirection.NORTH;
    	}
    	else if (BlockGroundBridgeTeleporter.isEastWestFacingFramePresent(world, x, y, z))
    	{
    		return ForgeDirection.EAST;
    	}
    	
    	return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityGroundBridgeFrame();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
    	return side == 1 && getFrameDirection(world, x, y, z) != null ? centerIcon : super.getIcon(world, x, y, z, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        blockIcon = par1IIconRegister.registerIcon(getTextureName());
        centerIcon = par1IIconRegister.registerIcon(getTextureName() + "_center");
    }
}
