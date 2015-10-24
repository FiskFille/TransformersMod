package fiskfille.tf.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGroundBridgeTeleporter extends BlockBreakable
{
    public static final int[][] field_150001_a = new int[][]{new int[0], {3, 1}, {2, 0}};

    public BlockGroundBridgeTeleporter()
    {
        super(TransformersMod.modid + ":ground_bridge_teleporter", Material.portal, false);
        this.setTickRandomly(true);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int l = func_149999_b(world.getBlockMetadata(x, y, z));

        if (l == 0)
        {
            if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this)
            {
                l = 2;
            }
            else
            {
                l = 1;
            }

            if (world instanceof World && !((World) world).isRemote)
            {
                ((World) world).setBlockMetadataWithNotify(x, y, z, l, 2);
            }
        }

        float f = 0.125F;
        float f1 = 0.125F;

        if (l == 1)
        {
            f = 0.5F;
        }

        if (l == 2)
        {
            f1 = 0.5F;
        }

        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean spawnTeleporter(World world, int x, int y, int z)
    {
        if (isNorthSouthFacingFramePresent(world, x, y, z))
        {
            fillNorthFacingFrame(world, x, y, z, TFBlocks.groundBridgeTeleporter);
            return true;
        }
        else if (isEastWestFacingFramePresent(world, x, y, z))
        {
            fillEastFacingFrame(world, x, y, z, TFBlocks.groundBridgeTeleporter);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (neighbor == this || neighbor == TFBlocks.groundBridgeFrame)
        {
            world.setBlockToAir(x, y, z);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        int i1 = 0;

        if (world.getBlock(x, y, z) == this)
        {
            i1 = func_149999_b(world.getBlockMetadata(x, y, z));

            if (i1 == 0)
            {
                return false;
            }

            if (i1 == 2 && side != 5 && side != 4)
            {
                return false;
            }

            if (i1 == 1 && side != 3 && side != 2)
            {
                return false;
            }
        }

        boolean flag = world.getBlock(x - 1, y, z) == this && world.getBlock(x - 2, y, z) != this;
        boolean flag1 = world.getBlock(x + 1, y, z) == this && world.getBlock(x + 2, y, z) != this;
        boolean flag2 = world.getBlock(x, y, z - 1) == this && world.getBlock(x, y, z - 2) != this;
        boolean flag3 = world.getBlock(x, y, z + 1) == this && world.getBlock(x, y, z + 2) != this;
        boolean flag4 = flag || flag1 || i1 == 1;
        boolean flag5 = flag2 || flag3 || i1 == 2;
        return flag4 && side == 4 ? true : (flag4 && side == 5 ? true : (flag5 && side == 2 ? true : flag5 && side == 3));
    }

    public int quantityDropped(Random rand)
    {
        return 0;
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {

    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        for (int l = 0; l < 4; ++l)
        {
            double particleX = (double) ((float) x + rand.nextFloat());
            double particleY = (double) ((float) y + rand.nextFloat());
            double particleZ = (double) ((float) z + rand.nextFloat());
            int i1 = rand.nextInt(2) * 2 - 1;
            double motionX = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            double motionY = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            double motionZ = ((double) rand.nextFloat() - 0.5D) * 0.5D;

            if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this)
            {
                particleX = (double) x + 0.5D + 0.25D * (double) i1;
                motionX = (double) (rand.nextFloat() * 2.0F * (float) i1);
            }
            else
            {
                particleZ = (double) z + 0.5D + 0.25D * (double) i1;
                motionZ = (double) (rand.nextFloat() * 2.0F * (float) i1);
            }

            world.spawnParticle("smoke", particleX, particleY, particleZ, motionX / 10, motionY, motionZ / 10);
        }
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemById(0);
    }

    public static int func_149999_b(int meta)
    {
        return meta & 3;
    }

    public boolean isNorthSouthFacingFramePresent(World world, int x, int y, int z)
    {
        Block b = TFBlocks.groundBridgeFrame;
        boolean flag = false;

        if (world.getBlock(x, y, z) == b && world.getBlock(x - 1, y, z) == b && world.getBlock(x + 1, y, z) == b)
        {
            if (world.getBlock(x - 2, y + 1, z) == b && world.getBlock(x + 2, y + 1, z) == b)
            {
                int j = 0;

                for (int i = 0; i < 3; ++i)
                {
                    if (world.getBlock(x - 3, y + 2 + i, z) == b && world.getBlock(x + 3, y + 2 + i, z) == b)
                    {
                        ++j;
                    }
                }

                if (j == 3)
                {
                    if (world.getBlock(x - 2, y + 5, z) == b && world.getBlock(x + 2, y + 5, z) == b)
                    {
                        if (world.getBlock(x, y + 6, z) == b && world.getBlock(x - 1, y + 6, z) == b && world.getBlock(x + 1, y + 6, z) == b)
                        {
                            flag = true;
                        }
                    }
                }
            }
        }

        return flag;
    }

    public boolean isEastWestFacingFramePresent(World world, int x, int y, int z)
    {
        Block b = TFBlocks.groundBridgeFrame;
        boolean flag = false;

        if (world.getBlock(x, y, z) == b && world.getBlock(x, y, z - 1) == b && world.getBlock(x, y, z + 1) == b)
        {
            if (world.getBlock(x, y + 1, z - 2) == b && world.getBlock(x, y + 1, z + 2) == b)
            {
                int j = 0;

                for (int i = 0; i < 3; ++i)
                {
                    if (world.getBlock(x, y + 2 + i, z - 3) == b && world.getBlock(x, y + 2 + i, z + 3) == b)
                    {
                        ++j;
                    }
                }

                if (j == 3)
                {
                    if (world.getBlock(x, y + 5, z - 2) == b && world.getBlock(x, y + 5, z + 2) == b)
                    {
                        if (world.getBlock(x, y + 6, z) == b && world.getBlock(x, y + 6, z - 1) == b && world.getBlock(x, y + 6, z + 1) == b)
                        {
                            flag = true;
                        }
                    }
                }
            }
        }

        return flag;
    }

    public void fillNorthFacingFrame(World world, int x, int y, int z, Block block)
    {
        for (int i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                world.setBlock(x - 1 + j, y + 1 + i, z, block);
                world.setBlock(x - 2 + i, y + 2 + j, z, block);
            }
        }
    }

    public void fillEastFacingFrame(World world, int x, int y, int z, Block block)
    {
        for (int i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                world.setBlock(x, y + 1 + i, z - 1 + j, block);
                world.setBlock(x, y + 2 + j, z - 2 + i, block);
            }
        }
    }
}
