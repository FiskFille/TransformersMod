package fiskfille.tf.common.block;

import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.entity.EntityTransformiumSeed;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Random;

public class BlockTransformiumSeed extends BlockBasic implements ITileEntityProvider
{
    public BlockTransformiumSeed()
    {
        super(Material.circuits);
        setResistance(1000000.0F);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);

        if (world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            ignite(world, x, y, z, world.getBlockMetadata(x, y, z), (EntityLivingBase) null);
            world.setBlockToAir(x, y, z);
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            ignite(world, x, y, z, block.getDamageValue(world, x, y, z), (EntityLivingBase) null);
            world.setBlockToAir(x, y, z);
        }
    }

    public int quantityDropped(Random p_149745_1_)
    {
        return 1;
    }

    public void ignite(World world, int x, int y, int z, int metadata, EntityLivingBase entity)
    {
        if (!world.isRemote)
        {
            EntityTransformiumSeed seed = new EntityTransformiumSeed(world, x + 0.5F, y + 0.5F, z + 0.5F, entity);
            world.spawnEntityInWorld(seed);
            world.playSoundAtEntity(seed, "note.pling", 1.0F, 0.5F);
        }

        if (entity instanceof EntityPlayer)
        {
            ((EntityPlayer) entity).addStat(TFAchievements.detonateSeed, 1);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        ignite(world, x, y, z, 1, player);
        world.setBlockToAir(x, y, z);
        return true;
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 p_149731_5_, Vec3 p_149731_6_)
    {
        float f = 0.2F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);

        return super.collisionRayTrace(world, x, y, z, p_149731_5_, p_149731_6_);
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

    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityTransformiumSeed();
    }
}