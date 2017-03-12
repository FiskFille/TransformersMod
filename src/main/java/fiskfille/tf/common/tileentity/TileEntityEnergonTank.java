package fiskfille.tf.common.tileentity;

import java.util.List;
import java.util.Map;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;

import com.google.common.collect.Lists;

import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.data.tile.TileDataEnergonTank;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.helper.TFTileHelper;

public class TileEntityEnergonTank extends TileEntityMachineContainer implements IFluidHandlerTF, ISidedInventory, IMultiTile
{
    private static final int[] slotsSides = {0, 1};

    public TileDataEnergonTank data = new TileDataEnergonTank(16000);
    private ItemStack[] inventory = new ItemStack[2];

    public int fillTime;
    public int lastFluidUsage;

    @Override
    public void updateEntity()
    {
        ItemStack input = getStackInSlot(0);
        ItemStack output = getStackInSlot(1);

        if (!data.isInitialized())
        {
            data.initialize(this);
        }

        if (data.getFluidAmount() > 0 && output != null && output.getItem() instanceof IFluidContainerItem && (ItemFuelCanister.isEmpty(output) || ItemFuelCanister.getContainerFluid(output).getFluid() == TFFluids.energon && !ItemFuelCanister.isFull(output)))
        {
            if (fillTime < 100)
            {
                ++fillTime;
            }
            else
            {
                if (!worldObj.isRemote)
                {
                    fillCanister(output);
                }

                fillTime = 0;
            }
        }
        else if (fillTime > 0)
        {
            --fillTime;
        }

        if (data.getFluid() != null && data.getFluidAmount() == 0)
        {
            data.setFluid(null);
        }

        if (!worldObj.isRemote)
        {
            if (data.getFluidAmount() > 0)
            {
                TileEntityEnergonTank tileBase = TFTileHelper.getTileBase(this);

                if (tileBase != this)
                {
                    if (TFTileHelper.getTileBase(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)) == tileBase)
                    {
                        TileEntityEnergonTank tile1 = (TileEntityEnergonTank) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);

                        if (tile1.getTank().getFluidAmount() < tile1.getTank().getCapacity())
                        {
                            FluidStack fluid = data.tank.drain(data.getFluidAmount(), false);

                            if (fluid != null && fluid.amount > 0 && tile1.canFill(ForgeDirection.UP, fluid.getFluid()))
                            {
                                int amount = tile1.fill(ForgeDirection.UP, fluid, true);

                                if (amount > 0)
                                {
                                    data.tank.drain(amount, true);
                                }
                            }
                        }
                    }
                }
                else
                {
                    ForgeDirection dir = ForgeDirection.getOrientation(getBlockMetadata());
                    TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
                    int maxTransfer = 100;

                    if (canActivate() && maxTransfer > 0 && tile instanceof IFluidHandler)
                    {
                        IFluidHandler fluidHandler = (IFluidHandler) tile;
                        FluidStack fluid = drain(dir, maxTransfer, false);

                        if (fluid != null && fluid.amount > 0 && fluidHandler.canFill(dir.getOpposite(), fluid.getFluid()))
                        {
                            int amount = fluidHandler.fill(dir.getOpposite(), fluid, true);

                            if (amount > 0)
                            {
                                drain(dir, amount, true);
                            }
                        }
                    }
                }
            }

            if (input != null && input.getItem() instanceof IFluidContainerItem)
            {
                IFluidContainerItem container = (IFluidContainerItem) input.getItem();
                FluidStack fluid = container.getFluid(input);

                if (fluid != null && fluid.amount > 0 && fluid.getFluid() == TFFluids.energon)
                {
                    int success = TFTileHelper.getTileBase(this).fill(ForgeDirection.UNKNOWN, container.drain(input, ItemFuelCanister.getFluidAmount(input), false), true);

                    if (success > 0)
                    {
                        container.drain(input, success, true);
                    }
                }
            }

            TileEntityEnergonTank tileBase = TFTileHelper.getTileBase(this);

            if (tileBase == this)
            {
                FluidStack mix = new FluidStack(TFFluids.energon, 0);
                int y = tileBase.yCoord;
                float f = 0;

                Map<String, Float> ratios = FluidEnergon.getRatios(mix);
                List<TileEntityEnergonTank> tiles = Lists.newArrayList();

                while (y < worldObj.getHeight() && TFTileHelper.getTileBase(worldObj.getTileEntity(xCoord, y, zCoord)) == tileBase)
                {
                    TileEntityEnergonTank tile = (TileEntityEnergonTank) worldObj.getTileEntity(xCoord, y, zCoord);
                    tiles.add(tile);

                    if (tile.getTank().getFluid() != null)
                    {
                        Map<String, Float> ratios1 = FluidEnergon.getRatios(tile.getTank().getFluid());

                        for (Map.Entry<String, Float> e : ratios1.entrySet())
                        {
                            ratios.put(e.getKey(), ratios.get(e.getKey()) + e.getValue() * tile.getTank().getFluidAmount());
                        }

                        f += tile.getTank().getFluidAmount();
                    }

                    ++y;
                }

                if (data.getFluid() != null)
                {
                    for (Map.Entry<String, Float> e : ratios.entrySet())
                    {
                        ratios.put(e.getKey(), ratios.get(e.getKey()) / f);
                    }

                    for (int i = 0; i < tiles.size(); ++i)
                    {
                        TileEntityEnergonTank tile = tiles.get(i);
                        FluidStack fluid = tile.getTank().getFluid();

                        if (fluid != null)
                        {
                            FluidEnergon.setRatios(fluid, ratios);
                        }
                    }
                }
            }

            data.serverTick();
        }

        TileData prevData = TFTileHelper.getTileData(new DimensionalCoords(this));

        if (prevData instanceof TileDataEnergonTank)
        {
            data = new TileDataEnergonTank((TileDataEnergonTank) prevData);
        }
    }

    public void fillCanister(ItemStack fluidContainer)
    {
        if (fluidContainer.getItem() instanceof IFluidContainerItem)
        {
            IFluidContainerItem item = (IFluidContainerItem) fluidContainer.getItem();
            int amount = Math.min(data.getFluidAmount(), item.getCapacity(fluidContainer) - ItemFuelCanister.getFluidAmount(fluidContainer));

            FluidStack stack = item.getFluid(fluidContainer);

            if (stack == null)
            {
                stack = new FluidStack(TFFluids.energon, 0);
            }

            FluidStack stack1 = new FluidStack(TFFluids.energon, amount);
            FluidEnergon.setRatios(stack1, FluidEnergon.getRatios(data.getFluid()));
            NBTTagCompound prevNBT = stack1.tag;

            stack1.tag = stack.tag;
            int i = item.fill(fluidContainer, stack1, true);
            drain(ForgeDirection.UNKNOWN, amount, true);
            stack.amount += i;
            stack1.tag = prevNBT;

            FluidEnergon.merge(stack, stack1, amount);

            if (!fluidContainer.hasTagCompound())
            {
                fluidContainer.setTagCompound(new NBTTagCompound());
            }

            fluidContainer.getTagCompound().setTag("Fluid", stack.writeToNBT(new NBTTagCompound()));
        }

        markBlockForUpdate();
        notifyNeighborBlocksOfChange();
    }

    public void notifyNeighborBlocksOfChange()
    {
        worldObj.getBlock(xCoord + 1, yCoord, zCoord).onNeighborBlockChange(worldObj, xCoord + 1, yCoord, zCoord, blockType);
        worldObj.getBlock(xCoord - 1, yCoord, zCoord).onNeighborBlockChange(worldObj, xCoord - 1, yCoord, zCoord, blockType);
        worldObj.getBlock(xCoord, yCoord, zCoord + 1).onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord + 1, blockType);
        worldObj.getBlock(xCoord, yCoord, zCoord - 1).onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord - 1, blockType);
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
            data.kill();
        }
    }

    @Override
    public String getInventoryName()
    {
        return "gui.energon_fluid_tank";
    }

    @Override
    public ItemStack[] getItemStacks()
    {
        return inventory;
    }

    @Override
    public void setItemStacks(ItemStack[] itemstacks)
    {
        inventory = itemstacks;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        FluidStack stack = data.getFluid();
        int fill = resource.amount;

        if (stack == null || stack.amount <= 0 || FluidStack.areFluidStackTagsEqual(stack, resource))
        {
            resource.amount -= data.tank.fill(resource, doFill);
        }
        else if (stack.getFluid() == TFFluids.energon)
        {
            NBTTagCompound prevNBT = resource.tag;

            resource.tag = stack.tag;
            int amount = data.tank.fill(resource, doFill);
            resource.tag = prevNBT;

            FluidEnergon.merge(stack, resource, amount);
            resource.amount -= amount;
        }

        if (resource.amount > 0)
        {
            int y = yCoord + 1;

            while (y < worldObj.getHeight() && TFTileHelper.getTileBase(worldObj.getTileEntity(xCoord, y, zCoord)) == TFTileHelper.getTileBase(this))
            {
                TileEntityEnergonTank tile = (TileEntityEnergonTank) worldObj.getTileEntity(xCoord, y, zCoord);
                resource.amount -= tile.fill(ForgeDirection.UNKNOWN, resource, doFill);

                if (resource.amount <= 0)
                {
                    break;
                }

                ++y;
            }
        }

        return fill - resource.amount;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(data.getFluid()))
        {
            return null;
        }

        return drain(from, resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        TileEntity tileBase = TFTileHelper.getTileBase(this);
        TileEntityEnergonTank topTile = null;
        int y = tileBase.yCoord;

        while (y < worldObj.getHeight() && TFTileHelper.getTileBase(worldObj.getTileEntity(xCoord, y, zCoord)) == tileBase)
        {
            topTile = (TileEntityEnergonTank) worldObj.getTileEntity(xCoord, y, zCoord);
            ++y;
        }

        if (topTile != null)
        {
            FluidStack drained = new FluidStack(TFFluids.energon, 0);
            y = topTile.yCoord;

            while (y > 0 && TFTileHelper.getTileBase(worldObj.getTileEntity(xCoord, y, zCoord)) == tileBase)
            {
                TileEntityEnergonTank tile = (TileEntityEnergonTank) worldObj.getTileEntity(xCoord, y, zCoord);
                FluidStack stack = tile.getTank().drain(maxDrain - drained.amount, doDrain);

                if (stack != null && stack.amount > 0)
                {
                    FluidEnergon.merge(drained, stack, stack.amount);
                    drained.amount += stack.amount;

                    if (drained.amount >= maxDrain)
                    {
                        break;
                    }
                }

                --y;
            }

            return drained;
        }

        return data.tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        return fluid == TFFluids.energon;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[] {data.tank.getInfo()};
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);
        fillTime = nbt.getInteger("FillTime");

        if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.tank.readFromNBT(config);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        super.writeCustomNBT(nbt);
        nbt.setInteger("FillTime", fillTime);

        if (data.getFluid() != null && data.getFluidAmount() > 0)
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.tank.writeToNBT(config);
            nbt.setTag("ConfigDataTF", config);
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return stack.getItem() instanceof IFluidContainerItem && (slot == 0 ? !ItemFuelCanister.isEmpty(stack) && ItemFuelCanister.getContainerFluid(stack).getFluid() == TFFluids.energon : ItemFuelCanister.isEmpty(stack) || !ItemFuelCanister.isFull(stack) && ItemFuelCanister.getContainerFluid(stack).getFluid() == TFFluids.energon);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side)
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side)
    {
        if (stack.getItem() instanceof IFluidContainerItem)
        {
            if (slot == 1)
            {
                return !ItemFuelCanister.isEmpty(stack);
            }

            return ItemFuelCanister.isEmpty(stack);
        }

        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return slotsSides;
    }

    @Override
    public FluidTankTF getTank()
    {
        return data.tank;
    }

    @Override
    public int[] getBaseOffsets(int metadata)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(metadata);
        int offset = 0;

        if (dir == ForgeDirection.DOWN || dir == ForgeDirection.UP)
        {
            int y = yCoord - 1;

            while (y > 0 && worldObj.getTileEntity(xCoord, y, zCoord) instanceof TileEntityEnergonTank && (worldObj.getBlockMetadata(xCoord, y, zCoord) == dir.ordinal() || worldObj.getBlockMetadata(xCoord, y, zCoord) == dir.getOpposite().ordinal()))
            {
                --y;
                --offset;
            }
        }

        return new int[] {0, offset, 0};
    }
}
