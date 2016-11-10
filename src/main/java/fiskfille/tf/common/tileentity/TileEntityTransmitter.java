package fiskfille.tf.common.tileentity;

import java.util.List;
import java.util.Map;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;

import com.google.common.collect.Lists;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.network.MessageUpdateEnergyState;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFHelper;

public class TileEntityTransmitter extends TileEntityContainer implements IEnergyTransmitter, IFluidHandler, ISidedInventory, IChunkLoaderTile
{
    public TransmissionHandler transmissionHandler = new TransmissionHandler(this);
    public ReceiverHandler receiverHandler = new ReceiverHandler(this);

    public EnergyStorage storage = new EnergyStorage(8000);
    public FluidTank tank = new FluidTank(2000);

    public ItemStack[] inventory = new ItemStack[1];

    public int animationTimer;
    public float prevEnergy;

    public float lastUsage;
    
    private Ticket chunkTicket;

    @Override
    public void updateEntity()
    {
        prevEnergy = storage.getEnergy();
        ++animationTimer;

        if (getBlockMetadata() < 4)
        {
        	loadChunks();
            transmissionHandler.onUpdate(worldObj);
            receiverHandler.onUpdate(worldObj);

            if (worldObj.isRemote)
            {
                TFHelper.applyClientEnergyUsage(this);
            }
            else
            {
                FluidStack stack = tank.getFluid();
//                receiveEnergy(110); // TODO: Remove

                if (getEnergy() > 0)
                {
                    List<TileEntity> tilesToPower = getTilesToPower();

                    for (TileEntity tile : tilesToPower)
                    {
                        IEnergyReceiver receiver = (IEnergyReceiver) tile;

                        if (receiver.canReceiveEnergy(this))
                        {
                            TFHelper.transferEnergy(receiver, this, Math.min(getEnergy(), 100F) / tilesToPower.size());
                        }
                    }
                }

                if (stack != null && stack.amount > 0)
                {
                    Map<String, Integer> contents = FluidEnergon.getContents(stack);
                    int i = 10;

                    for (Map.Entry<String, Integer> entry : contents.entrySet())
                    {
                        String energonType = entry.getKey();
                        Energon energon = TransformersAPI.getEnergonTypeByName(energonType);

                        if (energon != null)
                        {
                            float factor = energon.getEnergyValue();
                            drain(ForgeDirection.UNKNOWN, Math.round(receiveEnergy(Math.round(TFHelper.getPercentOf(energonType, contents) * factor) * i) / factor), true);
                        }
                    }
                }

                if (inventory[0] != null)
                {
                    ItemStack fluidContainer = inventory[0];

                    if (fluidContainer.getItem() instanceof IFluidContainerItem)
                    {
                        IFluidContainerItem container = (IFluidContainerItem) fluidContainer.getItem();
                        FluidStack fluid = container.getFluid(fluidContainer);

                        if (fluid != null && fluid.amount > 0 && (FluidStack.areFluidStackTagsEqual(stack, fluid) || (stack == null || stack.amount <= 0)))
                        {
                            if (fluid.getFluid() == TFFluids.energon)
                            {
                                int amount = Math.min(ItemFuelCanister.getFluidAmount(fluidContainer), tank.getCapacity() - tank.getFluidAmount());
                                fill(ForgeDirection.UNKNOWN, container.drain(fluidContainer, amount, true), true);
                            }
                        }
                    }
                }

                float usage = storage.calculateUsage();

                if (Math.abs(usage - lastUsage) > 0.001F)
                {
                    updateClients();
                }

                lastUsage = usage;
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

    public List<TileEntity> getTilesToPower()
    {
        List<TileEntity> tilesToPower = Lists.newArrayList();

        for (TileEntity tile : transmissionHandler.getReceivers())
        {
            if (canPowerReach(tile))
            {
                tilesToPower.add(tile);
            }
        }

        return tilesToPower;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0.35D, 0, 0.35D).addCoord(0, 2, 0);

        if (getBlockMetadata() < 4 && getEnergy() > 0)
        {
            for (TileEntity tile : transmissionHandler.getReceivers())
            {
                bounds = TFHelper.wrapAroundAABB(bounds, tile.getRenderBoundingBox());
            }
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

    public ReceiverHandler getReceiverHandler()
    {
        return receiverHandler;
    }

    public TransmissionHandler getTransmissionHandler()
    {
        return transmissionHandler;
    }

    @Override
    public boolean isPowering(TileEntity tile)
    {
        return getBlockMetadata() < 4 && getEnergy() > 0 && getTilesToPower().contains(tile);
    }

    @Override
    public boolean canPowerReach(TileEntity tile)
    {
        if (tile instanceof IEnergyReceiver)
        {
            IEnergyReceiver receiver = (IEnergyReceiver) tile;
            Vec3 position = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
            Vec3 start = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
            Vec3 end = getEnergyOutputOffset().addVector(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F);

            double deltaScale = 1F / position.distanceTo(end);
            end = Vec3.createVectorHelper(end.xCoord + (position.xCoord - end.xCoord) * deltaScale, end.yCoord + (position.yCoord - end.yCoord) * deltaScale, end.zCoord + (position.zCoord - end.zCoord) * deltaScale);
            MovingObjectPosition result = TFEnergyHelper.rayTraceBlocks(worldObj, end, position);

            if (result != null)
            {
                position = result.hitVec;
            }

            if (position.xCoord == start.xCoord && position.yCoord == start.yCoord && position.zCoord == start.zCoord)
            {
                return true;
            }
        }

        return false;
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
    public void updateClients()
    {
        TFNetworkManager.networkWrapper.sendToDimension(new MessageUpdateEnergyState(this), this.worldObj.provider.dimensionId);
    }
    
    @Override
    public void loadChunks()
	{
		if (!worldObj.isRemote)
		{
			while (chunkTicket == null)
			{
				chunkTicket = ForgeChunkManager.requestTicket(TransformersMod.instance, worldObj, Type.NORMAL);
			}
			
			if (chunkTicket == null)
			{
				System.err.println("Unable to load chunks!");
			}
			else
            {
                NBTTagCompound modData = chunkTicket.getModData();

                modData.setInteger("blockX", xCoord);
                modData.setInteger("blockY", yCoord);
                modData.setInteger("blockZ", zCoord);

                ForgeChunkManager.forceChunk(chunkTicket, new ChunkCoordIntPair(xCoord >> 4, zCoord >> 4));
            }
		}
	}

    @Override
	public void unloadChunks()
	{
		ForgeChunkManager.unforceChunk(chunkTicket, new ChunkCoordIntPair(xCoord >> 4, zCoord >> 4));
	}
	
    @Override
	public void loadTicket(Ticket ticket)
	{
		if (chunkTicket == null)
		{
			chunkTicket = ticket;
		}
		
		ChunkCoordIntPair loadChunk = new ChunkCoordIntPair(xCoord >> 4, zCoord >> 4);
		ForgeChunkManager.forceChunk(ticket, loadChunk);
	}
}
