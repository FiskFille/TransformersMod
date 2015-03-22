package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityTransformiumSeed;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;

public class BlockTransformiumSeed extends BlockBasic implements
        ITileEntityProvider
{
    public BlockTransformiumSeed()
    {
        super(Material.circuits);
        this.setCreativeTab(TransformersMod.tabTransformers);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);

        if (world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            this.ignite(world, x, y, z, world.getBlockMetadata(x, y, z),
                    (EntityLivingBase) null);
            world.setBlockToAir(x, y, z);
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z,
            Block block)
    {
        if (world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            this.ignite(world, x, y, z, block.getDamageValue(world, x, y, z),
                    (EntityLivingBase) null);
            world.setBlockToAir(x, y, z);
        }
    }

    public int quantityDropped(Random p_149745_1_)
    {
        return 1;
    }

    public void ignite(World world, int x, int y, int z, int metadata,
            EntityLivingBase entity)
    {
        if (!world.isRemote)
        {
            EntityTransformiumSeed seed = new EntityTransformiumSeed(world,
                    (double) ((float) x + 0.5F), (double) ((float) y + 0.5F),
                    (double) ((float) z + 0.5F), entity);
            world.spawnEntityInWorld(seed);
            world.playSoundAtEntity(seed, "note.pling", 1.0F, 0.5F);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int metadata, float p_149727_7_,
            float p_149727_8_, float p_149727_9_)
    {
        this.ignite(world, x, y, z, 1, player);
        world.setBlockToAir(x, y, z);
        return true;
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y,
            int z, Vec3 p_149731_5_, Vec3 p_149731_6_)
    {
        float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);

        return super
                .collisionRayTrace(world, x, y, z, p_149731_5_, p_149731_6_);
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