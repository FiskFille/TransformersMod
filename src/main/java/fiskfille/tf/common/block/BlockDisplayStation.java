package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.helper.TFTileHelper;

public class BlockDisplayStation extends BlockMachineBase
{
    public BlockDisplayStation()
    {
        super(TFMaterial.display);
        setHardness(2);
        setResistance(5);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getBlockHeight()
    {
        return 2;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        if (metadata >= 4)
        {
            setBlockBounds(0, -1, 0, 1, 1, 1);
        }
        else
        {
            setBlockBounds(0, 0, 0, 1, 2, 1);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
        {
            return true;
        }
        
        if (!player.isSneaking())
        {
            TileEntity tile = TFTileHelper.getTileBase(world.getTileEntity(x, y, z));

            if (tile instanceof TileEntityDisplayStation)
            {
                TFGui.DISPLAY_STATION.open(player, tile);
            }

            return true;
        }

        return false;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(world, x, y, z, block);
        
        if (world.getTileEntity(x, y, z) instanceof TileEntityDisplayStation)
        {
            TileEntityDisplayStation tile = TFTileHelper.getTileBase((TileEntityDisplayStation) world.getTileEntity(x, y, z));
            
            if (!world.isRemote)
            {
                boolean flag = world.isBlockIndirectlyGettingPowered(tile.xCoord, tile.yCoord, tile.zCoord);
                
                if (flag && !tile.isRedstonePowered)
                {
                    if (tile.canTransform())
                    {
                        TFNetworkManager.networkWrapper.sendToAll(new MessageTileTrigger(new DimensionalCoords(tile), null, 0));
                        tile.receive(null, 0);
                    }
                }
                
                tile.isRedstonePowered = flag;
            }
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("stone");
    }
}
