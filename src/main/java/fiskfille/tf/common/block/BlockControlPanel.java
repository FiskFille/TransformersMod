package fiskfille.tf.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.network.MessageControlPanel;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFHelper;

public class BlockControlPanel extends BlockMachineBase
{
    public BlockControlPanel()
    {
        super(Material.iron);
        setHardness(4.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
    }

    @Override
    public int getPlacedRotation(EntityLivingBase entity)
    {
        return 0;
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
        TileEntity tile = TFHelper.getTileBase(world.getTileEntity(x, y, z));
        int metadata = world.getBlockMetadata(x, y, z);
        int direction = getDirection(metadata);
        float f = 0.0625F;
        float f1 = 0.9575F;

        if (isBlockTopOfPanel(metadata))
        {
            if (tile instanceof TileEntityControlPanel && ((TileEntityControlPanel) tile).hasUpgrade(DataCore.spaceBridge))
            {
                float width = f * 6;
                float depth = f * 4;
                float height = f * 8;

                if (direction == 0)
                {
                    setBlockBounds(0, f1 - 1, 1 - depth, width, f1 - 1 + height, 1);
                }
                else if (direction == 1)
                {
                    setBlockBounds(0, f1 - 1, 0, depth, f1 - 1 + height, width);
                }
                else if (direction == 2)
                {
                    setBlockBounds(1 - width, f1 - 1, 0, 1, f1 - 1 + height, depth);
                }
                else if (direction == 3)
                {
                    setBlockBounds(1 - depth, f1 - 1, 1 - width, 1, f1 - 1 + height, 1);
                }
            }
            else
            {
                setBlockBounds(0, 0, 0, 0, 0, 0);
            }
        }
        else
        {
            setBlockBounds(0, 0, 0, 1, f1, 1);
        }
    }

    public static boolean isBlockLeftSideOfPanel(int metadata)
    {
        return metadata < 4;
    }

    public static boolean isBlockTopOfPanel(int metadata)
    {
        return metadata >= 8;
    }

    public static int getDirection(int metadata)
    {
        return metadata % 4;
    }

    @Override
    public int getMobilityFlag()
    {
        return 1;
    }

    @Override
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

            boolean isSide = !isBlockLeftSideOfPanel(metadata);
            boolean isTop = isBlockTopOfPanel(metadata);

            if (isSide && face != 2 && face != 3)
            {
                ++hitX;
            }

            if (isTop && face != 4 && face != 5)
            {
                --hitY;
            }

            TileEntity tile = TFHelper.getTileBase(world.getTileEntity(x, y, z));

            if (tile instanceof TileEntityControlPanel)
            {
                return onRightClick(world, tile.xCoord, tile.yCoord, tile.zCoord, (TileEntityControlPanel) tile, player, face, hitX, hitY);
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
            if (face == 0)
            {
                if (hitY > f * 4 && hitY <= f * 7.5F)
                {
                    for (int i = 0; i < 3; ++i)
                    {
                        if (player.getHeldItem() != null && tile.isItemValidForSlot(i, player.getHeldItem()) || tile.getStackInSlot(i) != null && player.getHeldItem() == null)
                        {
                            if (hitX > f * (19 + i * 3.5F) && hitX <= f * (22 + i * 3.5F))
                            {
                                sendActionPacket(tile, player, 15 + i);
                                return true;
                            }
                        }
                    }
                }

                if (hitY >= f * -3.7F && hitY < f * -1.7F)
                {
                    if (hitX >= f * 26.5F && hitX < f * 28.5F)
                    {
                        sendActionPacket(tile, player, 18);
                        return true;
                    }
                    else if (hitX >= f * 28.7F && hitX < f * 30.7F)
                    {
                        sendActionPacket(tile, player, 19);
                        return true;
                    }
                }
            }
            else if (face == 4)
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
        TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(player, tile.xCoord, tile.yCoord, tile.zCoord, tile.getWorldObj().provider.dimensionId, action));
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("iron_block");
    }
}
