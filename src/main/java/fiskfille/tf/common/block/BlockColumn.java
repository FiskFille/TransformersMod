package fiskfille.tf.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityColumn;
import fiskfille.tf.helper.TFHelper;

public class BlockColumn extends BlockContainer implements ITileEntityProvider
{
    protected final Random rand = new Random();

    public BlockColumn()
    {
        super(Material.iron);
        setHardness(5.0F);
        setResistance(10.0F);
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
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
    }

    public void addBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        setBounds(world.getBlockMetadata(x, y, z));
    }

    public void setBounds(int metadata)
    {
        float f = 0.0625F;
        float width = f * 3.125F;

        if (metadata < 4)
        {
            setBlockBounds(width, 0, width, 1 - width, 2, 1 - width);
        }
        else
        {
            setBlockBounds(width, -1, width, 1 - width, 1, 1 - width);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!player.isSneaking())
        {
            TileEntity tile = TFHelper.getTileBase(world.getTileEntity(x, y, z));

            if (tile instanceof TileEntityColumn)
            {
                player.openGui(TransformersMod.instance, 8, world, tile.xCoord, tile.yCoord, tile.zCoord);
            }

            return true;
        }

        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        IInventory iinventory = (IInventory) world.getTileEntity(x, y, z);

        if (iinventory != null)
        {
            for (int j1 = 0; j1 < iinventory.getSizeInventory(); ++j1)
            {
                ItemStack itemstack = iinventory.getStackInSlot(j1);

                if (itemstack != null)
                {
                    float f = rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int k1 = rand.nextInt(21) + 10;

                        if (k1 > itemstack.stackSize)
                        {
                            k1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= k1;
                        EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double) ((float) rand.nextGaussian() * f3);
                        entityitem.motionY = (double) ((float) rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double) ((float) rand.nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        if (metadata >= 4)
        {
            if (world.getBlock(x, y - 1, z) != TFBlocks.energyColumn)
            {
                world.setBlockToAir(x, y, z);
                breakBlock(world, x, y, z, block, metadata);
            }
        }
        else
        {
            if (world.getBlock(x, y + 1, z) != TFBlocks.energyColumn)
            {
                world.setBlockToAir(x, y, z);
                breakBlock(world, x, y, z, block, metadata);
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return y < world.getHeight() - 1 && super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
    {
        int rotation = MathHelper.floor_double((double) ((entity.rotationYaw * 4F) / 360F) + 2.5D) & 3;

        world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
        world.setBlock(x, y + 1, z, this, rotation + 4, 2);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityColumn();
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        blockIcon = par1IIconRegister.registerIcon("iron_block");
    }
}
