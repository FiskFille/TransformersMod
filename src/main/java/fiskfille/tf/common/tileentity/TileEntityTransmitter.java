package fiskfille.tf.common.tileentity;

import com.google.common.collect.Lists;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.chunk.ForcedChunk;
import fiskfille.tf.common.chunk.SubTicket;
import fiskfille.tf.common.chunk.TFChunkManager;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.power.EnergonTank;
import fiskfille.tf.common.energon.power.EnergonTankContainer;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.energon.power.TargetReceiver;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.network.MessageUpdateEnergyState;
import fiskfille.tf.common.network.MessageUpdateFluidState;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFHelper;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.List;
import java.util.Map;

public class TileEntityTransmitter extends TileEntityContainer implements IEnergyTransmitter, IFluidHandler, ISidedInventory, IChunkLoaderTile, EnergonTankContainer
{
    public TransmissionHandler transmissionHandler = new TransmissionHandler(this);
    public ReceiverHandler receiverHandler = new ReceiverHandler(this);

    public EnergyStorage storage = new EnergyStorage(8000);
    public EnergonTank tank = new EnergonTank(2000);

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
                TFHelper.applyClientEnergyUsage(this);
                TFHelper.applyClientFluidUsage(this);
            }
            else
            {
                receiveEnergy(110); // TODO: Remove & Add

                if (getEnergy() > 0)
                {
                    List<TargetReceiver> receiversToPower = getReceiversToPower();

                    for (TargetReceiver target : receiversToPower)
                    {
                        IEnergyReceiver receiver = target.getReceiver();

                        if (receiver.canReceiveEnergy(this))
                        {
                            TFHelper.transferEnergy(receiver, this, Math.min(getEnergy(), 100F) / receiversToPower.size());
                        }
                    }
                }

                if (fluidStack != null && fluidStack.amount > 0)
                {
                    Map<String, Float> ratios = FluidEnergon.getRatios(fluidStack);
                    int i = 10;

                    for (Map.Entry<String, Float> e : ratios.entrySet())
                    {
                        Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());

                        if (energon != null)
                        {
                            float factor = energon.getEnergyValue();
                            float receivedEnergy = receiveEnergy(Math.round(e.getValue() * factor) * i);
                            drain(ForgeDirection.UNKNOWN, Math.round(receivedEnergy / factor), true);

                            if (worldObj.isRemote)
                            {
                                extractEnergy(receivedEnergy);
                            }
                        }
                    }
                }

                boolean updatedClientFluid = false;

                ItemStack fluidContainer = inventory[0];

                if (fluidContainer != null)
                {
                    if (fluidContainer.getItem() instanceof IFluidContainerItem)
                    {
                        IFluidContainerItem container = (IFluidContainerItem) fluidContainer.getItem();
                        FluidStack fluid = container.getFluid(fluidContainer);

                        if (fluid != null && fluid.amount > 0 && (FluidStack.areFluidStackTagsEqual(fluidStack, fluid) || (fluidStack == null || fluidStack.amount <= 0)))
                        {
                            if (fluid.getFluid() == TFFluids.energon)
                            {
                                int amount = Math.min(ItemFuelCanister.getFluidAmount(fluidContainer), tank.getCapacity() - tank.getFluidAmount());
                                fill(ForgeDirection.UNKNOWN, container.drain(fluidContainer, amount, true), true);
                                this.updateClientFluid();
                                updatedClientFluid = true;
                            }
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
    public void setItemStacks(ItemStack[] inventory)
    {
        this.inventory = inventory;
    }

    public List<TargetReceiver> getReceiversToPower()
    {
        List<TargetReceiver> receiversToPower = Lists.newArrayList();

        for (TargetReceiver receiver : transmissionHandler.getReceivers())
        {
            if (canPowerReach(receiver))
            {
                receiversToPower.add(receiver);
            }
        }

        return receiversToPower;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0.35D, 0, 0.35D).addCoord(0, 2, 0);

        if (getBlockMetadata() < 4 && getEnergy() > 0)
        {
            return INFINITE_EXTENT_AABB;
        }

        return bounds;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        if (nbt.getBoolean("Base"))
        {
            transmissionHandler.readFromNBT(nbt);
            receiverHandler.readFromNBT(nbt);
            storage.readFromNBT(nbt);
            tank.readFromNBT(nbt);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        boolean base = getBlockMetadata() < 4;
        nbt.setBoolean("Base", base);

        if (base)
        {
            transmissionHandler.writeToNBT(nbt);
            receiverHandler.writeToNBT(nbt);
            storage.writeToNBT(nbt);
            tank.writeToNBT(nbt);
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
    public boolean isPowering(TargetReceiver receiver)
    {
        return getBlockMetadata() < 4 && getEnergy() > 0 && getReceiversToPower().contains(receiver);
    }

    @Override
    public boolean canPowerReach(TargetReceiver target)
    {
        ChunkCoordinates pos = target.getCoordinates();
        Vec3 position = target.getEnergyInputOffset().addVector(pos.posX + 0.5F, pos.posY + 0.5F, pos.posZ + 0.5F);
        Vec3 start = target.getEnergyInputOffset().addVector(pos.posX + 0.5F, pos.posY + 0.5F, pos.posZ + 0.5F);
        Vec3 end = getEnergyOutputOffset().addVector(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F);

        double deltaScale = 1F / position.distanceTo(end);
        end = Vec3.createVectorHelper(end.xCoord + (position.xCoord - end.xCoord) * deltaScale, end.yCoord + (position.yCoord - end.yCoord) * deltaScale, end.zCoord + (position.zCoord - end.zCoord) * deltaScale);
        MovingObjectPosition result = TFEnergyHelper.rayTraceBlocks(worldObj, end, position);

        if (result != null)
        {
            position = result.hitVec;
        }

        return position.xCoord == start.xCoord && position.yCoord == start.yCoord && position.zCoord == start.zCoord;
    }

    @Override
    public float getRange()
    {
        return 20;
    }

    @Override
    public Vec3 getEnergyOutputOffset()
    {
        return Vec3.createVectorHelper(0, 2, 0);
    }

    @Override
    public boolean isPowering(TileEntity tile)
    {
        if (getBlockMetadata() < 4 && getEnergy() > 0)
        {
            List<TargetReceiver> receivers = getReceiversToPower();

            for (TargetReceiver receiver : receivers)
            {
                ChunkCoordinates coordinates = receiver.getCoordinates();

                if (coordinates.posX == tile.xCoord && coordinates.posY == tile.yCoord && coordinates.posZ == tile.zCoord)
                {
                    return true;
                }
            }
        }

        return false;
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
        return tank.fill(resource, doFill);
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
        return new FluidTankInfo[] { tank.getInfo() };
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return new int[] { 0 };
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side)
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return isItemValidForSlot(slot, itemstack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side)
    {
        return ItemFuelCanister.isEmpty(itemstack);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return getBlockMetadata() < 4 && itemstack.getItem() instanceof IFluidContainerItem && !ItemFuelCanister.isEmpty(itemstack) && ItemFuelCanister.getContainerFluid(itemstack).getFluid() == TFFluids.energon;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        writeToNBT(syncData);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public void updateClientEnergy()
    {
        TFNetworkManager.networkWrapper.sendToDimension(new MessageUpdateEnergyState(this), this.worldObj.provider.dimensionId);
    }

    @Override
    public void updateClientFluid()
    {
        TFNetworkManager.networkWrapper.sendToDimension(new MessageUpdateFluidState(this), this.worldObj.provider.dimensionId);
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
    public EnergonTank getTank()
    {
        return tank;
    }
}
