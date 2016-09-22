package fiskfille.tf.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class BlockTransmitter extends Block implements ITileEntityProvider
{
    protected final Random rand = new Random();

    public BlockTransmitter()
    {
        super(Material.rock);
        setHarvestLevel("pickaxe", 0);
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
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
	{
    	int metadata = world.getBlockMetadata(x, y, z);
    	float f = 0.0625F;
    	
    	if (metadata < 4)
    	{
    		addBox(0, 0, 0, 1, f * 4, 1, world, x, y, z, aabb, list, entity);
    		
    		for (int i = 0; i < 26; ++i)
    		{
            	float width = 1 - 0.6F * ((float)i / 26);
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
    
    public void addBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
    	setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    	super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
    	setBlockBoundsBasedOnState(world, x, y, z);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        setBounds(world.getBlockMetadata(x, y, z));
    }

    public void setBounds(int metadata)
    {
        float f = 0.0625F;

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
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!player.isSneaking())
        {
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
                	hitY += (metadata >= 8 ? 2 : (metadata >= 4 ? 1 : 0));
                }

                TileEntityTransmitter tile = (TileEntityTransmitter) world.getTileEntity(x, y - (metadata >= 8 ? 2 : (metadata >= 4 ? 1 : 0)), z);

                if (tile != null)
                {
                	if (onRightClick(world, tile.xCoord, tile.yCoord, tile.zCoord, tile, player, face, hitX, hitY))
                	{
                		return true;
                	}
                }
            }
            
            if (metadata >= 4)
            {
            	if (metadata < 8)
            	{
            		y -= 1;
            	}
            	else
            	{
            		y -= 2;
            	}
            }
            
            if (world.getTileEntity(x, y, z) instanceof TileEntityTransmitter)
            {
                player.openGui(TransformersMod.instance, 4, world, x, y, z);
            }

            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean onRightClick(World world, int x, int y, int z, TileEntityTransmitter tile, EntityPlayer player, int face, float hitX, float hitY)
    {
        // 0 = back, 1 = front, 2 = left, 3 = right, 4 = top, 5 = bottom
        float f = 0.0625F;
        
        if (face == 1)
        {
        	if (hitX > f * 5.5F && hitX < f * 10.5F && hitY > f * 10 && hitY < f * 20)
        	{
        		player.openGui(TransformersMod.instance, 5, world, x, y, z);
        		return true;
        	}
        }
        
        return false;
    }
    
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntityTransmitter tileentity = (TileEntityTransmitter) world.getTileEntity(x, y, z);

        if (tileentity != null)
        {
            for (int j1 = 0; j1 < tileentity.getSizeInventory(); ++j1)
            {
                ItemStack itemstack = tileentity.getStackInSlot(j1);

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
            FluidEvent.fireEvent(new FluidEvent.FluidSpilledEvent(tileentity.tank.getFluid(), world, x, y, z));
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        if (metadata < 8)
        {
            if (world.getBlock(x, y + 1, z) != TFBlocks.transmitter)
            {
                world.setBlockToAir(x, y, z);
                breakBlock(world, x, y, z, block, metadata);
            }
        }
        
        if (metadata >= 4)
        {
        	if (world.getBlock(x, y - 1, z) != TFBlocks.transmitter)
            {
                world.setBlockToAir(x, y, z);
                breakBlock(world, x, y, z, block, metadata);
            }
        }
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return y >= world.getHeight() - 2 ? false : super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z) && super.canPlaceBlockAt(world, x, y + 2, z);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
    {
        int rotation = MathHelper.floor_double((double) ((entity.rotationYaw * 4F) / 360F) + 2.5D) & 3;

        world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
        world.setBlock(x, y + 1, z, this, rotation + 4, 2);
        world.setBlock(x, y + 2, z, this, rotation + 8, 2);
    }

    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityTransmitter();
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        blockIcon = par1IIconRegister.registerIcon("stone");
    }
}
