package fiskfille.tf.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.data.tile.TileDataEnergonTank;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.helper.TFTileHelper;

public class TileEntityEnergonProcessor extends TileEntityMachineContainer implements IFluidHandlerTF, ISidedInventory
{
    private static final int[] slotsTop = {1};
    private static final int[] slotsBottom = {2};
    private static final int[] slotsSides = {0, 2};

    public TileDataEnergonTank data = new TileDataEnergonTank(2000);
    private ItemStack[] inventory = new ItemStack[3];

    public int burnTime;
    public int powerTime;
    public int fillTime;
    public int currentMaxPowerTime;
    public float animationTimer;
    public float prevAnimationTimer;
    public int animationBurnTime;

    @Override
    public void updateEntity()
    {
        prevAnimationTimer = animationTimer;

        if (burnTime > 0)
        {
            ++animationBurnTime;
            animationTimer += 0.001F;
            animationTimer *= 1.05F;
        }
        else
        {
            animationBurnTime = 0;
            animationTimer *= 0.95F;
        }

        if (animationTimer > 1.0F)
        {
            animationTimer = 1.0F;
        }

        if (!data.isInitialized())
        {
            data.initialize(this);
        }

        ItemStack power = getStackInSlot(0);
        ItemStack crystal = getStackInSlot(1);
        ItemStack canister = getStackInSlot(2);

        if (powerTime > 0)
        {
            --powerTime;
        }

        if (powerTime <= 0 && power != null && isItemValidForSlot(0, power) && canProcessCrystal(crystal))
        {
            currentMaxPowerTime = powerTime = PowerManager.getPowerSourceAmount(power) + 2;
            decrStackSize(0, 1);
            markDirty();
        }

        if (powerTime > 0 && canProcessCrystal(crystal))
        {
            if (burnTime < 200)
            {
                ++burnTime;
            }
            else if (addContents(crystal))
            {
                decrStackSize(1, 1);
                burnTime = 0;

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                notifyNeighborBlocksOfChange();
            }
        }
        else
        {
            burnTime = 0;
        }

        if (data.getFluidAmount() > 0 && canister != null && canister.getItem() instanceof IFluidContainerItem && (ItemFuelCanister.isEmpty(canister) || ItemFuelCanister.getContainerFluid(canister).getFluid() == TFFluids.energon && !ItemFuelCanister.isFull(canister)))
        {
            if (fillTime < 100)
            {
                ++fillTime;
            }
            else
            {
                if (!worldObj.isRemote)
                {
                    fillCanister(canister);
                }

                fillTime = 0;
            }
        }
        else
        {
            fillTime = 0;
        }

        if (data.getFluid() != null && data.getFluidAmount() == 0)
        {
            data.setFluid(null);
        }

        if (!worldObj.isRemote)
        {
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

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        notifyNeighborBlocksOfChange();
    }

    public boolean addContents(ItemStack crystal)
    {
        if (!worldObj.isRemote)
        {
            FluidStack stack = data.getFluid();

            if (stack == null)
            {
                data.setFluid(new FluidStack(TFFluids.energon, 0));
                stack = data.getFluid();
            }

            if (stack.getFluid() == TFFluids.energon)
            {
                FluidStack stack1 = FluidEnergon.create(crystal);
                NBTTagCompound prevNBT = stack1.tag;

                stack1.tag = stack.tag;
                int amount = fill(ForgeDirection.UNKNOWN, stack1, true);
                stack1.tag = prevNBT;

                FluidEnergon.merge(stack, stack1, amount);
                return true;
            }
        }

        return false;
    }

    public void notifyNeighborBlocksOfChange()
    {
        worldObj.getBlock(xCoord + 1, yCoord, zCoord).onNeighborBlockChange(worldObj, xCoord + 1, yCoord, zCoord, blockType);
        worldObj.getBlock(xCoord - 1, yCoord, zCoord).onNeighborBlockChange(worldObj, xCoord - 1, yCoord, zCoord, blockType);
        worldObj.getBlock(xCoord, yCoord, zCoord + 1).onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord + 1, blockType);
        worldObj.getBlock(xCoord, yCoord, zCoord - 1).onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord - 1, blockType);
    }

    public boolean canProcessCrystal(ItemStack itemstack)
    {
        if (itemstack != null && isItemValidForSlot(1, itemstack) && canActivate())
        {
            IEnergon ienergon = (IEnergon) (itemstack.getItem() instanceof ItemBlock ? Block.getBlockFromItem(itemstack.getItem()) : itemstack.getItem());

            if (data.getFluidAmount() + ienergon.getMass() <= data.getCapacity())
            {
                return true;
            }
        }

        return false;
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
        return "gui.energon_processor";
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
        FluidStack stack = data.tank.getFluid();

        if (stack == null || stack.amount <= 0 || FluidStack.areFluidStackTagsEqual(stack, resource))
        {
            return data.tank.fill(resource, doFill);
        }
        else if (stack.getFluid() == TFFluids.energon)
        {
            NBTTagCompound prevNBT = resource.tag;

            resource.tag = stack.tag;
            int amount = data.tank.fill(resource, doFill);
            resource.tag = prevNBT;

            FluidEnergon.merge(stack, resource, amount);

            return amount;
        }

        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(data.getFluid()))
        {
            return null;
        }

        return data.tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
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
    public AxisAlignedBB getRenderBoundingBox()
    {
        return super.getRenderBoundingBox().addCoord(0, 0.5D, 0);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);
        burnTime = nbt.getInteger("BurnTime");
        powerTime = nbt.getInteger("PowerTime");
        fillTime = nbt.getInteger("FillTime");
        currentMaxPowerTime = nbt.getInteger("MaxPowerTime");

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
        nbt.setInteger("BurnTime", burnTime);
        nbt.setInteger("PowerTime", powerTime);
        nbt.setInteger("FillTime", fillTime);
        nbt.setInteger("MaxPowerTime", currentMaxPowerTime);

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
        return slot == 0 ? PowerManager.isPowerSource(stack) : slot == 1 ? stack.getItem() instanceof IEnergon || Block.getBlockFromItem(stack.getItem()) instanceof IEnergon : slot == 2 && stack.getItem() == TFItems.fuelCanister && ItemFuelCanister.isEmpty(stack) && stack.stackSize == 1 && inventory[slot] == null;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == 0 ? slotsBottom : side == 1 ? slotsTop : slotsSides;
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
        return side != 0 || slot == 0 || slot == 2 && !ItemFuelCanister.isEmpty(stack);
    }

    @Override
    public FluidTankTF getTank()
    {
        return data.tank;
    }
}
