package fiskfille.tf.common.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import fiskfille.tf.helper.TFTileHelper;

public class BlockTransmitter extends BlockMachineBase
{
    public BlockTransmitter()
    {
        super(Material.rock);
        setHardness(5.0F);
        setResistance(10.0F);
    }

    @Override
    public int getBlockHeight()
    {
        return 3;
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
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        float f = 0.0625F;

        if (metadata < 4)
        {
            addBox(0, 0, 0, 1, f * 4, 1, world, x, y, z, aabb, list, entity);

            for (int i = 0; i < 26; ++i)
            {
                float width = 1 - 0.6F * ((float) i / 26);
                float f1 = 1 - width;
                addBox(f1 / 2, f * (i + 4), f1 / 2, 1 - f1 / 2, f * (i + 5), 1 - f1 / 2, world, x, y, z, aabb, list, entity);
            }
        }
        else if (metadata < 8)
        {
            addBox(0, f * 14, 0, 1, 1, 1, world, x, y, z, aabb, list, entity);
        }
        else
        {
            addBox(0, 0, 0, 1, 1, 1, world, x, y, z, aabb, list, entity);
        }

        setBlockBoundsBasedOnState(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        if (metadata < 4)
        {
            setBlockBounds(0, 0, 0, 1, 3, 1);
        }
        else if (metadata < 8)
        {
            setBlockBounds(0, -1, 0, 1, 2, 1);
        }
        else
        {
            setBlockBounds(0, -2, 0, 1, 1, 1);
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
            TileEntity tile = world.getTileEntity(x, y, z);
            TileEntity tileBase = TFTileHelper.getTileBase(tile);
            int metadata = world.getBlockMetadata(x, y, z);
            int direction = metadata % 4;
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

                if (face < 4)
                {
                    hitY = 1 - hitY;
                    hitY += TFTileHelper.getTileBaseOffsets(tile, metadata)[1];
                }

                if (tileBase instanceof TileEntityTransmitter)
                {
                    if (onRightClick(world, tileBase.xCoord, tileBase.yCoord, tileBase.zCoord, (TileEntityTransmitter) tileBase, player, face, hitX, hitY))
                    {
                        return true;
                    }
                }
            }

            if (tileBase instanceof TileEntityTransmitter)
            {
                TFGui.ENERGON_TRANSMITTER.open(player, tileBase);
            }

            return true;
        }

        return false;
    }

    public boolean onRightClick(World world, int x, int y, int z, TileEntityTransmitter tile, EntityPlayer player, int face, float hitX, float hitY)
    {
        // 0 = back, 1 = front, 2 = left, 3 = right, 4 = top, 5 = bottom
        float f = 0.0625F;

        if (face == 1)
        {
            if (hitX > f * 5.5F && hitX < f * 10.5F && hitY > f * 10 && hitY < f * 20)
            {
                TFGui.RECEIVER_NETWORK.open(player, tile);
                return true;
            }
        }

        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("stone");
    }
}
