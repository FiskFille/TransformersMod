package fiskfille.tf.common.block;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.displayable.Displayable;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDisplayPillar extends BlockBasic implements ITileEntityProvider
{
    private Random rand = new Random();

    public BlockDisplayPillar()
    {
        super(Material.rock);
        setHardness(0.5F);
        setResistance(1.0F);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntityDisplayPillar tileEntityDisplayPillar = (TileEntityDisplayPillar) world.getTileEntity(x, y, z);
        ItemStack itemstack = tileEntityDisplayPillar.getDisplayItem();

        if (itemstack != null)
        {
            float f = rand.nextFloat() * 0.8F + 0.1F;
            float f1 = rand.nextFloat() * 0.8F + 0.1F;
            float f2 = rand.nextFloat() * 0.8F + 0.1F;

            while (itemstack.stackSize > 0)
            {
                int j1 = rand.nextInt(21) + 10;

                if (j1 > itemstack.stackSize)
                {
                    j1 = itemstack.stackSize;
                }

                itemstack.stackSize -= j1;

                EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                if (itemstack.hasTagCompound())
                {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                }

                float f3 = 0.05F;

                entityitem.motionX = (float) rand.nextGaussian() * f3;
                entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
                entityitem.motionZ = (float) rand.nextGaussian() * f3;

                world.spawnEntityInWorld(entityitem);
            }
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileEntityDisplayPillar tileEntityDisplayPillar = (TileEntityDisplayPillar) world.getTileEntity(x, y, z);

        if (tileEntityDisplayPillar != null)
        {
            ItemStack heldItem = player.getHeldItem();
            ItemStack displayItem = tileEntityDisplayPillar.getDisplayItem();

            if (heldItem == null && displayItem != null)
            {
                player.setCurrentItemOrArmor(0, displayItem);
                tileEntityDisplayPillar.setDisplayItem(null, true);

                return true;
            }
            else if (heldItem != null && TransformersAPI.hasDisplayable(heldItem.getItem()))
            {
                if (displayItem == null)
                {
                    tileEntityDisplayPillar.setDisplayItem(heldItem, true);
                    player.setCurrentItemOrArmor(0, null);

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 p_149731_5_, Vec3 p_149731_6_)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.collisionRayTrace(world, x, y, z, p_149731_5_, p_149731_6_);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        TileEntityDisplayPillar tileEntityDisplayPillar = (TileEntityDisplayPillar) world.getTileEntity(x, y, z);

        if (tileEntityDisplayPillar != null)
        {
            ItemStack displayItem = tileEntityDisplayPillar.getDisplayItem();

            if (displayItem != null)
            {
                Displayable displayable = TransformersAPI.getDisplayableFor(displayItem.getItem());

                if (displayable != null)
                {
                    setBlockBounds(0, 0, 0, 1, displayable.getBlockHeight(displayItem), 1);
                }
            }
            else
            {
                setBlockBounds(0, 0, 0, 1, 0.0625F * 9, 1);
            }
        }

        super.setBlockBoundsBasedOnState(world, x, y, z);
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
    public boolean hasTileEntity()
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityDisplayPillar();
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(TransformersMod.modid + ":" + getUnlocalizedName().substring(5));
    }
}
