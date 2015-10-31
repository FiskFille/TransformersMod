package fiskfille.tf.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.network.MessageControlPanel;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGroundBridgeControl extends BlockDirectional implements ITileEntityProvider
{
    public static final int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public BlockGroundBridgeControl()
    {
        super(Material.iron);
        setHarvestLevel("pickaxe", 0);
        setStepSound(soundTypeMetal);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return -1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean hasTileEntity()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        setBounds(world.getBlockMetadata(x, y, z));
    }

    public void setBounds(int metadata)
    {
        int direction = getDirection(metadata);
        int i = isBlockSideOfPanel(metadata) ? 0 : 1;
        float f = 0.9575F;

        if (direction == 0)
        {
            setBlockBounds(-i, 0, 0, 2 - i, f, 1);
        }
        else if (direction == 1)
        {
            setBlockBounds(0, 0, -i, 1, f, 2 - i);
        }
        else if (direction == 2)
        {
            setBlockBounds(-1 + i, 0, 0, 1 + i, f, 1);
        }
        else if (direction == 3)
        {
            setBlockBounds(0, 0, -1 + i, 1, f, 1 + i);
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        int direction = getDirection(metadata);

        if (isBlockSideOfPanel(metadata))
        {
            if (world.getBlock(x - directions[direction][0], y, z - directions[direction][1]) != this)
            {
                world.setBlockToAir(x, y, z);
            }
        }
        else if (world.getBlock(x + directions[direction][0], y, z + directions[direction][1]) != this)
        {
            world.setBlockToAir(x, y, z);

            if (!world.isRemote)
            {
                this.dropBlockAsItem(world, x, y, z, metadata, 0);
            }
        }
    }

    public Item getItemDropped(int metadata, Random rand, int p_149650_3_)
    {
        return isBlockSideOfPanel(metadata) ? Item.getItemById(0) : super.getItemDropped(metadata, rand, p_149650_3_);
    }

    public static boolean isBlockSideOfPanel(int metadata)
    {
        return (metadata & 8) != 0;
    }

    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float p_149690_6_, int p_149690_7_)
    {
        if (!isBlockSideOfPanel(metadata))
        {
            super.dropBlockAsItemWithChance(world, x, y, z, metadata, p_149690_6_, 0);
        }
    }

    public int getMobilityFlag()
    {
        return 1;
    }

    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode && isBlockSideOfPanel(metadata))
        {
            int direction = getDirection(metadata);
            x -= directions[direction][0];
            z -= directions[direction][1];

            if (world.getBlock(x, y, z) == this)
            {
                world.setBlockToAir(x, y, z);
            }
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        int direction = getDirection(metadata);
        int face = -1;

        if (side == 0)
        {
            face = 5;
        }
        else if (side == 1)
        {
            face = 4;
        }

        if (direction == 0)
        {
            if (side == 2)
            {
                face = 0;
            }
            else if (side == 3)
            {
                face = 1;
            }
            else if (side == 4)
            {
                face = 2;
            }
            else if (side == 5)
            {
                face = 3;
            }
        }
        else if (direction == 1)
        {
            if (side == 5)
            {
                face = 0;
            }
            else if (side == 4)
            {
                face = 1;
            }
            else if (side == 2)
            {
                face = 2;
            }
            else if (side == 3)
            {
                face = 3;
            }
        }
        else if (direction == 2)
        {
            if (side == 3)
            {
                face = 0;
            }
            else if (side == 2)
            {
                face = 1;
            }
            else if (side == 5)
            {
                face = 2;
            }
            else if (side == 4)
            {
                face = 3;
            }
        }
        else if (direction == 3)
        {
            if (side == 4)
            {
                face = 0;
            }
            else if (side == 5)
            {
                face = 1;
            }
            else if (side == 3)
            {
                face = 2;
            }
            else if (side == 2)
            {
                face = 3;
            }
        }

        if (face != -1)
        {
            if (side == 1 || side == 0)
            {
                if (direction == 0)
                {
                    hitX = 1 - hitX;
                    hitY = (side == 1 ? 1 : hitZ * 2) - hitZ;
                }
                else if (direction == 1)
                {
                    hitY = (side == 0 ? 1 : hitX * 2) - hitX;
                    hitX = 1 - hitZ;
                }
                else if (direction == 2)
                {
                    hitY = (side == 0 ? 1 : hitZ * 2) - hitZ;
                }
                else if (direction == 3)
                {
                    hitY = (side == 1 ? 1 : hitX * 2) - hitX;
                    hitX = hitZ;
                }
            }
            else if (side == 3)
            {
                hitY = 1 - hitY;
            }
            else if (side == 2)
            {
                hitX = 1 - hitX;
                hitY = 1 - hitY;
            }
            else if (side == 5)
            {
                hitX = 1 - hitZ;
                hitY = 1 - hitY;
            }
            else if (side == 4)
            {
                hitX = hitZ;
                hitY = 1 - hitY;
            }

            if (face == 1)
            {
                hitX = 1 - hitX;
            }

            boolean isSide = isBlockSideOfPanel(metadata);

            if (isSide && face != 2 && face != 3)
            {
                ++hitX;
            }

            TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(x - (isSide ? directions[direction][0] : 0), y, z - (isSide ? directions[direction][1] : 0));

            if (tile != null)
            {
                return onRightClick(world, x, y, z, tile, player, face, hitX, hitY);
            }
        }

        return false;
    }

    public boolean onRightClick(World world, int x, int y, int z, TileEntityControlPanel tile, EntityPlayer player, int face, float hitX, float hitY)
    {
        // 0 = front, 1 = back, 2 = right, 3 = left, 4 = top, 5 = bottom
        float f = 0.0625F;

        if (world.isRemote)
        {
            if (face == 4)
            {
                if (hitY > f * 2 && hitY <= f * 4.5F)
                {
                    if (hitX > f * 1.15F && hitX <= f * 3.21F)
                    {
                        sendActionPacket(tile, player, 1);
                        return true;
                    }
                    if (hitX > f * 3.25F && hitX <= f * 5.39F)
                    {
                        sendActionPacket(tile, player, 2);
                        return true;
                    }
                    if (hitX > f * 5.55F && hitX <= f * 7.6F)
                    {
                        sendActionPacket(tile, player, 3);
                        return true;
                    }
                    if (hitX > f * 7.6F && hitX <= f * 10F)
                    {
                        sendActionPacket(tile, player, 4);
                        return true;
                    }
                }
                if (hitY > f * 6.2F && hitY <= f * 8.8F)
                {
                    if (hitX > f * 1.15F && hitX <= f * 3.21F)
                    {
                        sendActionPacket(tile, player, 5);
                        return true;
                    }
                    if (hitX > f * 3.25F && hitX <= f * 5.39F)
                    {
                        sendActionPacket(tile, player, 6);
                        return true;
                    }
                    if (hitX > f * 5.55F && hitX <= f * 7.6F)
                    {
                        sendActionPacket(tile, player, 7);
                        return true;
                    }
                    if (hitX > f * 7.6F && hitX <= f * 10F)
                    {
                        sendActionPacket(tile, player, 8);
                        return true;
                    }
                }
                if (hitY > f * 10F && hitY <= f * 13F)
                {
                    if (hitX > f * 1.15F && hitX <= f * 3.21F)
                    {
                        sendActionPacket(tile, player, 9);
                        return true;
                    }
                    if (hitX > f * 3.25F && hitX <= f * 5.39F)
                    {
                        sendActionPacket(tile, player, 10);
                        return true;
                    }
                    if (hitX > f * 5.55F && hitX <= f * 7.6F)
                    {
                        sendActionPacket(tile, player, 11);
                        return true;
                    }
                    if (hitX > f * 7.6F && hitX <= f * 10F)
                    {
                        sendActionPacket(tile, player, 12);
                        return true;
                    }
                }
                if (hitX > f * 13.5F && hitX <= f * 18.5F && hitY > f * 5.65F && hitY <= f * 10.85F)
                {
                    if (!tile.activationLeverState)
                    {
                        sendActionPacket(tile, player, 13);
                    }

                    return true;
                }
                if (hitX > f * 23F && hitX <= f * 31F && hitY > f * 4F && hitY <= f * 12.75F)
                {
                    if (tile.activationLeverCoverState && (tile.activationLeverTimer == 0 || tile.activationLeverTimer == 1))
                    {
                        sendActionPacket(tile, player, 14);
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public void sendActionPacket(TileEntityControlPanel tile, EntityPlayer player, int action)
    {
//    	if (player.worldObj.isRemote)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(player, tile.xCoord, tile.yCoord, tile.zCoord, action));
        }
//    	else
//    	{
//    		TFNetworkManager.networkWrapper.sendToDimension(new MessageControlPanelAction(player, tile.xCoord, tile.yCoord, tile.zCoord, action), player.dimension);
//    	}
    }

    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityControlPanel();
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        blockIcon = par1IIconRegister.registerIcon("iron_block");
    }
}
