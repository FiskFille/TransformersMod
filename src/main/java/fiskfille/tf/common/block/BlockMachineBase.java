package fiskfille.tf.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TFLog;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.helper.TFTileHelper;

public class BlockMachineBase extends Block implements ITileEntityProvider
{
    protected final Random rand = new Random();

    public Class<? extends TileEntity> tileClass;

    protected BlockMachineBase(Material material)
    {
        super(material);
    }

    public int getBlockHeight()
    {
        return 1;
    }

    public int getPlacedRotation(EntityLivingBase entity)
    {
        return MathHelper.floor_double(entity.rotationYaw * 4F / 360F + 2.5D) & 3;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileEntity tile = TFTileHelper.getTileBase(world.getTileEntity(x, y, z));
        ItemStack heldItem = player.getHeldItem();
        
        if (tile instanceof IFluidHandlerTF && heldItem != null && heldItem.getItem() instanceof IFluidContainerItem)
        {
            IFluidHandlerTF fluidHandler = (IFluidHandlerTF) tile;
            IFluidContainerItem item = (IFluidContainerItem) heldItem.getItem();
            
            boolean empty = ItemFuelCanister.isEmpty(heldItem);
            
            if (!empty)
            {
                FluidStack stack = item.getFluid(heldItem);
                
                if (stack != null && fluidHandler.canFill(ForgeDirection.UNKNOWN, stack.getFluid()))
                {
                    int amount = fluidHandler.fill(ForgeDirection.UNKNOWN, item.drain(heldItem, stack.amount, false), true);
                    
                    if (amount > 0)
                    {
                        ItemStack newItem = new ItemStack(heldItem.getItem(), 1, heldItem.getItemDamage());
                        item.fill(newItem, stack, true);
                        item.drain(newItem, amount, true);
                        
                        player.setCurrentItemOrArmor(0, addItem(player, heldItem, newItem));
                        
                        return true;
                    }
                }
            }
            
            FluidStack stack = fluidHandler.getTank().getFluid();
            
            if (stack != null && fluidHandler.canDrain(ForgeDirection.UNKNOWN, item.getFluid(heldItem) == null ? stack.getFluid() : item.getFluid(heldItem).getFluid()))
            {
                FluidStack drained = fluidHandler.drain(ForgeDirection.UNKNOWN, item.getCapacity(heldItem) - ItemFuelCanister.getFluidAmount(heldItem), false);
                
                if (drained != null && drained.amount > 0)
                {
                    ItemStack newItem = new ItemStack(heldItem.getItem(), 1, heldItem.getItemDamage());
                    item.fill(newItem, item.getFluid(heldItem), true);
                    
                    int amount = drained.amount;
                    FluidStack stack1 = item.getFluid(newItem);

                    if (stack1 == null)
                    {
                        stack1 = new FluidStack(TFFluids.energon, 0);
                    }

                    FluidStack stack2 = new FluidStack(TFFluids.energon, amount);
                    FluidEnergon.setRatios(stack2, FluidEnergon.getRatios(stack));
                    NBTTagCompound prevNBT = stack2.tag;

                    stack2.tag = stack1.tag;
                    int i = item.fill(newItem, stack2, true);
                    fluidHandler.drain(ForgeDirection.UNKNOWN, amount, true);
                    stack1.amount += i;
                    stack2.tag = prevNBT;

                    FluidEnergon.merge(stack1, stack2, amount);

                    if (!newItem.hasTagCompound())
                    {
                        newItem.setTagCompound(new NBTTagCompound());
                    }

                    newItem.getTagCompound().setTag("Fluid", stack1.writeToNBT(new NBTTagCompound()));
                    
                    player.setCurrentItemOrArmor(0, addItem(player, heldItem, newItem));
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public ItemStack addItem(EntityPlayer player, ItemStack itemstack, ItemStack newItem)
    {
        if (player.capabilities.isCreativeMode)
        {
            return itemstack;
        }
        else if (itemstack.stackSize - 1 <= 0)
        {
            return newItem;
        }
        else
        {
            --itemstack.stackSize;
            
            if (!player.inventory.addItemStackToInventory(newItem))
            {
                player.dropPlayerItemWithRandomChoice(newItem, false);
            }

            return itemstack;
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        boolean flag = super.canPlaceBlockAt(world, x, y, z);
        int height = getBlockHeight();

        for (int i = 1; i < height; ++i)
        {
            flag &= super.canPlaceBlockAt(world, x, y + i, z);
        }

        return y + height - 1 < world.getHeight() && flag;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    public void addBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        AxisAlignedBB aabb1 = getCollisionBoundingBoxFromPool(world, x, y, z);

        if (aabb1 != null && aabb.intersectsWith(aabb1))
        {
            list.add(aabb1);
        }

        setBlockBoundsBasedOnState(world, x, y, z);
    }

    @Override
    public boolean hasComparatorInputOverride()
    {
        if (tileClass != null && (IFluidHandler.class.isAssignableFrom(tileClass) || IInventory.class.isAssignableFrom(tileClass)))
        {
            return true;
        }

        return false;
    }

    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int metadata)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof IFluidHandler)
        {
            IFluidHandler fluidHandler = (IFluidHandler) tile;
            float amount = 0;
            float capacity = 0;

            for (ForgeDirection dir : ForgeDirection.values())
            {
                FluidTankInfo[] info = fluidHandler.getTankInfo(dir);

                for (int i = 0; i < info.length; ++i)
                {
                    capacity += info[i].capacity;

                    if (info[i].fluid != null)
                    {
                        amount += info[i].fluid.amount;
                    }
                }
            }

            return Math.round(amount / capacity * 15);
        }
        else if (tile instanceof IInventory)
        {
            return Container.calcRedstoneFromInventory((IInventory) tile);
        }

        return 0;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof IInventory)
        {
            IInventory inventory = (IInventory) tile;

            for (int i = 0; i < inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = inventory.getStackInSlot(i);

                if (itemstack != null)
                {
                    float f = rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int j = rand.nextInt(21) + 10;

                        if (j > itemstack.stackSize)
                        {
                            j = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j;
                        EntityItem entity = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entity.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entity.motionX = (float) rand.nextGaussian() * f3;
                        entity.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
                        entity.motionZ = (float) rand.nextGaussian() * f3;
                        world.spawnEntityInWorld(entity);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        TileEntity tileBase = TFTileHelper.getTileBase(tile);

        if (tileBase != null && tile != tileBase && getBlockHeight() > 0)
        {
            world.setBlockToAir(tileBase.xCoord, tileBase.yCoord, tileBase.zCoord);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
    {
        if (!world.isRemote && (player == null || !player.capabilities.isCreativeMode))
        {
            ItemStack itemstack = new ItemStack(this, 1, damageDropped(world.getBlockMetadata(x, y, z)));
            TileEntity tile = world.getTileEntity(x, y, z);

            if (getBlockHeight() > 0)
            {
                tile = TFTileHelper.getTileBase(world.getTileEntity(x, y, z));
            }

            if (tile != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                tile.writeToNBT(nbttagcompound);

                if (nbttagcompound.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
                {
                    NBTTagCompound config = nbttagcompound.getCompoundTag("ConfigDataTF");

                    if (!itemstack.hasTagCompound())
                    {
                        itemstack.setTagCompound(new NBTTagCompound());
                    }

                    itemstack.getTagCompound().setTag("ConfigDataTF", config);
                }
            }

            float f = 0.7F;
            double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
            double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
            double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, itemstack);

            entityitem.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(entityitem);
        }

        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
    {
        super.onBlockPlacedBy(world, x, y, z, entity, itemstack);
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile != null)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            tile.writeToNBT(nbttagcompound);

            if (itemstack != null && itemstack.hasTagCompound())
            {
                if (itemstack.getTagCompound().hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
                {
                    NBTTagCompound config = itemstack.getTagCompound().getCompoundTag("ConfigDataTF");
                    nbttagcompound.setTag("ConfigDataTF", config);

                    tile.readFromNBT(nbttagcompound);
                }
            }
        }

        for (int i = 0; i < getBlockHeight(); ++i)
        {
            int metadata = getPlacedRotation(entity) + i * 4;

            if (metadata > 0)
            {
                if (i == 0)
                {
                    world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
                }
                else
                {
                    world.setBlock(x, y + i, z, this, metadata, 2);
                }
            }
        }

        if (!world.isRemote)
        {
            world.markBlockForUpdate(x, y, z);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        world.markBlockForUpdate(x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);

        if (tile != null && getBlockHeight() > 0)
        {
            int[] offsets = TFTileHelper.getTileBaseOffsets(tile, metadata);

            if (world.getBlock(x + offsets[0], y + offsets[1], z + offsets[2]) != this)
            {
                world.setBlockToAir(x, y, z);
            }
        }
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return tileClass != null;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        if (tileClass != null)
        {
            try
            {
                return tileClass.newInstance();
            }
            catch (Exception e)
            {
                TFLog.error("Could not create tile entity for block '%s' from class %s", delegate.name(), tileClass);
            }
        }

        return null;
    }
}
