package fiskfille.tf.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockEnergyPort extends BlockMachineBase
{
    public BlockEnergyPort()
    {
        super(Material.iron);
        setHarvestLevel("pickaxe", 1);
        setHardness(6.0F);
        setResistance(10.0F);
    }
    
    @Override
    public int getPlacedRotation(EntityLivingBase entity)
    {
        return 0;
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        return ForgeDirection.getOrientation(side).getOpposite().ordinal();
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(metadata).getOpposite();
        float f = 0.0625F * 3.5F;
        
        switch (dir)
        {
        case DOWN:
            setBlockBounds(0, 1 - f, 0, 1, 1, 1);
            break;
        case UP:
            setBlockBounds(0, 0, 0, 1, f, 1);
            break;
        case NORTH:
            setBlockBounds(0, 0, 1 - f, 1, 1, 1);
            break;
        case SOUTH:
            setBlockBounds(0, 0, 0, 1, 1, f);
            break;
        case WEST:
            setBlockBounds(1 - f, 0, 0, 1, 1, 1);
            break;
        case EAST:
            setBlockBounds(0, 0, 0, f, 1, 1);
            break;
        default:
            setBlockBounds(0, 0, 0, 1, 1, 1);
            break;
        }
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("iron_block");
    }
}
