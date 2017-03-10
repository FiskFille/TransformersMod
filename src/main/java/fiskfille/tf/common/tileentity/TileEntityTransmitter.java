package fiskfille.tf.common.tileentity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.chunk.ForcedChunk;
import fiskfille.tf.common.chunk.SubTicket;
import fiskfille.tf.common.chunk.TFChunkManager;
import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.data.tile.TileDataTransmitter;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverEntry;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFTileHelper;

public class TileEntityTransmitter extends TileEntityMachineContainer implements IEnergyTransmitter, IFluidHandlerTF, ISidedInventory, IChunkLoaderTile, IMultiTile
{
    public TileDataTransmitter data = new TileDataTransmitter(16000, 6000);
    public ItemStack[] inventory = new ItemStack[1];

    public int animationTimer;

    public Ticket chunkTicket;

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        ++animationTimer;

        if (!data.isInitialized())
        {
            data.initialize(this);
        }

        if (getBlockMetadata() < 4)
        {
            if (!worldObj.isRemote)
            {
                if (chunkTicket == null)
                {
                    Ticket ticket = TFChunkManager.getTicketForChunk(ForcedChunk.fromTile(this));

                    if (ticket != null)
                    {
                        SubTicket subTicket = SubTicket.fromTile(this);
                        forceChunks(subTicket.assign(ticket));
                    }
                }

                data.serverTickPre();
                
                if (getEnergy() > 0 && canActivate())
                {
                    List<ReceiverEntry> receiversToPower = TFEnergyHelper.getReceiversToPower(this);
                    float f = Math.min(getEnergy(), getTransmissionRate()) / receiversToPower.size();
                    
                    for (ReceiverEntry entry : receiversToPower)
                    {
                        IEnergyReceiver receiver = entry.getReceiver();

                        if (receiver.canReceiveEnergy(this))
                        {
                            TFEnergyHelper.transferEnergy(receiver, this, f, false);
                        }
                    }
                }

                ItemStack fluidContainer = getStackInSlot(0);
                FluidStack fluidStack = data.tank.getFluid();

                if (fluidStack != null && fluidStack.amount > 0)
                {
                    Map<String, Float> ratios = FluidEnergon.getRatios(fluidStack);
                    int max = Math.min(10, fluidStack.amount);

                    for (Map.Entry<String, Float> e : ratios.entrySet())
                    {
                        Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());

                        if (energon != null)
                        {
                            float factor = energon.getEnergyValue();
                            float receivedEnergy = receiveEnergy(e.getValue() * factor * max, false);
                            drain(ForgeDirection.UNKNOWN, Math.round(receivedEnergy / factor), true);
                        }
                    }
                }

                if (fluidContainer != null && fluidContainer.getItem() instanceof IFluidContainerItem)
                {
                    IFluidContainerItem container = (IFluidContainerItem) fluidContainer.getItem();
                    FluidStack fluid = container.getFluid(fluidContainer);

                    if (fluid != null && fluid.amount > 0 && fluid.getFluid() == TFFluids.energon)
                    {
                        int amount = Math.min(100, Math.min(ItemFuelCanister.getFluidAmount(fluidContainer), data.getCapacity() - data.getFluidAmount()));
                        int success = fill(ForgeDirection.UNKNOWN, container.drain(fluidContainer, amount, false), true);

                        if (success > 0)
                        {
                            container.drain(fluidContainer, success, true);
                        }
                    }
                }

                data.serverTick();
            }

            TileData prevData = TFTileHelper.getTileData(new DimensionalCoords(this));

            if (prevData instanceof TileDataTransmitter)
            {
                data = new TileDataTransmitter((TileDataTransmitter) prevData);
            }
        }
    }

    @Override
    public String getInventoryName()
    {
        return "gui.transmitter";
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
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0.35D, 0, 0.35D).addCoord(0, 2, 0);

        if (getBlockMetadata() < 4)
        {
            Set<ReceiverEntry> receivers = data.transmissionHandler.getReceivers();

            for (ReceiverEntry entry : receivers)
            {
                TileEntity tile = entry.getTile();

                if (tile != null)
                {
                    bounds = bounds.func_111270_a(tile.getRenderBoundingBox());
                }
            }
        }

        return bounds;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);

        if (nbt.getBoolean("Base"))
        {
            if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
            {
                NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
                data.transmissionHandler.readFromNBT(config);
                data.storage.readFromNBT(config);
                data.tank.readFromNBT(config);
            }
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        super.writeCustomNBT(nbt);

        boolean base = getBlockMetadata() < 4;
        nbt.setBoolean("Base", base);

        if (base)
        {
            if (data.getEnergy() > 0 || data.getFluidAmount() > 0 || !data.transmissionHandler.getReceivers().isEmpty())
            {
                NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
                data.transmissionHandler.writeToNBT(config);
                data.storage.writeToNBT(config);
                data.tank.writeToNBT(config);
                nbt.setTag("ConfigDataTF", config);
            }
        }
    }

    @Override
    public TransmissionHandler getTransmissionHandler()
    {
        return data.transmissionHandler;
    }

    @Override
    public float getTransmissionRate()
    {
        return 350;
    }

    @Override
    public float getRange()
    {
        return 20;
    }

    @Override
    public Vec3 getEnergyOutputOffset()
    {
        return Vec3.createVectorHelper(0, 2 + (Math.cos((animationTimer + TransformersMod.proxy.getRenderTick()) / 10) * 2 + 2) / 16, 0);
    }

    @Override
    public float receiveEnergy(float amount, boolean simulate)
    {
        return data.storage.add(amount, simulate);
    }

    @Override
    public float extractEnergy(float amount, boolean simulate)
    {
        return data.storage.remove(amount, simulate);
    }

    @Override
    public float getEnergy()
    {
        return data.storage.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return data.storage.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return data.storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return data.storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        data.storage.setUsage(usage);
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
        if (resource == null || !resource.isFluidEqual(data.tank.getFluid()))
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
        return getBlockMetadata() < 4 && fluid == TFFluids.energon;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return getBlockMetadata() < 4;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[] {data.tank.getInfo()};
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return new int[] {0};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side)
    {
        if (getBlockMetadata() >= 4)
        {
            return false;
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return isItemValidForSlot(slot, itemstack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side)
    {
        return getBlockMetadata() < 4 && ItemFuelCanister.isEmpty(itemstack);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return getBlockMetadata() < 4 && itemstack.getItem() instanceof IFluidContainerItem && !ItemFuelCanister.isEmpty(itemstack) && ItemFuelCanister.getContainerFluid(itemstack).getFluid() == TFFluids.energon;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
            data.kill();
            releaseChunks();
        }
    }

    @Override
    public void forceChunks(SubTicket subTicket)
    {
        releaseChunks();
        chunkTicket = subTicket.owner;
        TFChunkManager.forceChunk(subTicket.owner, ForcedChunk.fromTile(this));
    }

    public void releaseChunks()
    {
        if (chunkTicket != null)
        {
            TFChunkManager.releaseChunk(SubTicket.fromTile(chunkTicket, this), ForcedChunk.fromTile(this));
            chunkTicket = null;
        }
    }

    @Override
    public FluidTankTF getTank()
    {
        return data.tank;
    }

    @Override
    public int[] getBaseOffsets(int metadata)
    {
        return new int[] {0, -metadata / 4, 0};
    }
}
