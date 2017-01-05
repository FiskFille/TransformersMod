package fiskfille.tf.common.tileentity;

import java.util.List;
import java.util.Map;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.energon.power.TargetReceiver;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.network.MessageUpdateEnergyState;
import fiskfille.tf.common.network.MessageUpdateFluidState;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFHelper;

public class TileEntityTransmitter extends TileEntityContainer implements IEnergyTransmitter, IFluidHandlerTF, ISidedInventory, IChunkLoaderTile, IMultiTile
{
    public TransmissionHandler transmissionHandler = new TransmissionHandler(this);
    public ReceiverHandler receiverHandler = new ReceiverHandler(this);

    public EnergyStorage storage = new EnergyStorage(16000);
    public FluidTankTF tank = new FluidTankTF(6000);

    public ItemStack[] inventory = new ItemStack[1];

    public int animationTimer;
    public float prevEnergy;

    public float lastEnergyUsage;
    public int lastFluidUsage;

    public Ticket chunkTicket;

    @Override
    public void updateEntity()
    {
        prevEnergy = storage.getEnergy();
        ++animationTimer;

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
            }

            transmissionHandler.onUpdate(worldObj);
            receiverHandler.onUpdate(worldObj);

            FluidStack fluidStack = tank.getFluid();

            if (worldObj.isRemote)
            {
                TFEnergyHelper.applyClientEnergyUsage(this);
                TFHelper.applyClientFluidUsage(this);
            }
            else
            {
                if (getEnergy() > 0)
                {
                    List<TargetReceiver> receiversToPower = TFEnergyHelper.getReceiversToPower(this);

                    for (TargetReceiver target : receiversToPower)
                    {
                        IEnergyReceiver receiver = target.getReceiver();

                        if (receiver.canReceiveEnergy(this))
                        {
                            TFEnergyHelper.transferEnergy(receiver, this, Math.min(getEnergy(), getTransmissionRate()) / receiversToPower.size());
                        }
                    }
                }

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
                            float receivedEnergy = receiveEnergy(e.getValue() * factor * max);
                            drain(ForgeDirection.UNKNOWN, Math.round(receivedEnergy / factor), true);
                        }
                    }
                }

                boolean updatedClientFluid = false;
                ItemStack fluidContainer = getStackInSlot(0);

                if (fluidContainer != null && fluidContainer.getItem() instanceof IFluidContainerItem)
                {
                    IFluidContainerItem container = (IFluidContainerItem) fluidContainer.getItem();
                    FluidStack fluid = container.getFluid(fluidContainer);

                    if (fluid != null && fluid.amount > 0 && fluid.getFluid() == TFFluids.energon)
                    {
                        int amount = Math.min(ItemFuelCanister.getFluidAmount(fluidContainer), tank.getCapacity() - tank.getFluidAmount());
                        int success = fill(ForgeDirection.UNKNOWN, container.drain(fluidContainer, amount, false), true);

                        if (success > 0)
                        {
                            container.drain(fluidContainer, success, true);
                            updateClientFluid();
                            updatedClientFluid = true;
                        }
                    }
                }

                float energyUsage = storage.calculateUsage();
                int fluidUsage = tank.calculateUsage();

                if (Math.abs(energyUsage - lastEnergyUsage) > 0.001F)
                {
                    updateClientEnergy();
                }

                lastEnergyUsage = energyUsage;

                if (fluidUsage != lastFluidUsage && !updatedClientFluid)
                {
                    updateClientFluid();
                }

                lastFluidUsage = fluidUsage;
            }
        }
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

        if (getBlockMetadata() < 4 && getEnergy() > 0)
        {
            float radius = getRange() + 2;
            return bounds.expand(radius, radius, radius);
        }

        return bounds;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);

        if (nbt.getBoolean("Base"))
        {
            transmissionHandler.readFromNBT(nbt);
            receiverHandler.readFromNBT(nbt);

            if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
            {
                NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
                storage.readFromNBT(config);
                tank.readFromNBT(config);
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
            transmissionHandler.writeToNBT(nbt);
            receiverHandler.writeToNBT(nbt);

            if (storage.getEnergy() > 0 || tank.getFluid() != null && tank.getFluidAmount() > 0)
            {
                NBTTagCompound config = new NBTTagCompound();
                storage.writeToNBT(config);
                tank.writeToNBT(config);
                nbt.setTag("ConfigDataTF", config);
            }
        }
    }

    @Override
    public ReceiverHandler getReceiverHandler()
    {
        return receiverHandler;
    }

    @Override
    public TransmissionHandler getTransmissionHandler()
    {
        return transmissionHandler;
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
    public float receiveEnergy(float amount)
    {
        return storage.add(amount);
    }

    @Override
    public float extractEnergy(float amount)
    {
        return storage.remove(amount);
    }

    @Override
    public float getEnergy()
    {
        return storage.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return storage.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        storage.setUsage(usage);
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        FluidStack stack = tank.getFluid();

        if (stack == null || stack.amount <= 0 || FluidStack.areFluidStackTagsEqual(stack, resource))
        {
            return tank.fill(resource, doFill);
        }
        else if (stack.getFluid() == TFFluids.energon)
        {
            NBTTagCompound prevNBT = resource.tag;

            resource.tag = stack.tag;
            int amount = tank.fill(resource, true);
            resource.tag = prevNBT;

            FluidEnergon.merge(stack, resource, amount);

            return amount;
        }

        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
        {
            return null;
        }

        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return tank.drain(maxDrain, doDrain);
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
        return new FluidTankInfo[] {tank.getInfo()};
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
    public String getInventoryName()
    {
        return "gui.transmitter";
    }

    @Override
    public void updateClientEnergy()
    {
        TFNetworkManager.networkWrapper.sendToDimension(new MessageUpdateEnergyState(this), worldObj.provider.dimensionId);
    }

    @Override
    public void updateClientFluid()
    {
        TFNetworkManager.networkWrapper.sendToDimension(new MessageUpdateFluidState(this), worldObj.provider.dimensionId);
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
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
        return tank;
    }

    @Override
    public int[] getBaseOffsets(int metadata)
    {
        return new int[] {0, -metadata / 4, 0};
    }
}
