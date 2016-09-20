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
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;

import com.google.common.collect.Lists;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFHelper;

public class TileEntityTransmitter extends TileEntityContainer implements IEnergyTransmitter, IFluidHandler, ISidedInventory
{
	public ReceiverHandler receiverHandler = new ReceiverHandler();
	public EnergyStorage storage = new EnergyStorage(8000);
	public FluidTank tank = new FluidTank(2000);
	
	public ItemStack[] inventory = new ItemStack[1];
	
	public int animationTimer;
	public float prevEnergy;
	
	public void updateEntity()
	{
		prevEnergy = storage.getEnergy();
		++animationTimer;
		
		receiverHandler.onUpdate(worldObj);
		
		if (getBlockMetadata() < 4)
		{
			FluidStack stack = tank.getFluid();
			
			for (TileEntity tile : getTilesToPower())
			{
				IEnergyReceiver receiver = (IEnergyReceiver)tile;
				TFHelper.transferEnergy(receiver, this, Math.min(getEnergy(), 100F) / getTilesToPower().size());
			}
			
			if (stack != null && stack.amount > 0)
			{
				Map<String, Integer> contents = FluidEnergon.getContents(stack);
				int i = 10;
				
				for (Map.Entry<String, Integer> e : contents.entrySet())
				{
					String s = e.getKey();
					Energon energon = TransformersAPI.getEnergonTypeByName(s);
					
					if (energon != null)
					{
						float factor = energon.getEnergyValue();
						drain(ForgeDirection.UNKNOWN, Math.round(receiveEnergy(Math.round(TFHelper.getPercentOf(s, contents) * factor) * i) / factor), true);
					}
				}
			}
			
			if (inventory[0] != null)
			{
				ItemStack itemstack = inventory[0];
				
				if (itemstack.getItem() instanceof IFluidContainerItem)
				{
					IFluidContainerItem container = (IFluidContainerItem)itemstack.getItem();
					FluidStack stack1 = container.getFluid(itemstack);
					
					if (stack1 != null && stack1.amount > 0 && (FluidStack.areFluidStackTagsEqual(stack, stack1) || (stack == null || stack.amount <= 0)))
					{
						if (stack1.getFluid() == TFFluids.energon)
						{
							int amount = Math.min(ItemFuelCanister.getFluidAmount(itemstack), tank.getCapacity() - tank.getFluidAmount());
							fill(ForgeDirection.UNKNOWN, container.drain(itemstack, amount, true), true);
						}
					}
				}
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
	
	public List<TileEntity> getTilesToPower()
	{
		List<TileEntity> list = Lists.newArrayList();
		
		for (TileEntity tile : getTilesToTryPower())
		{
			if (canPower(tile))
			{
				list.add(tile);
			}
		}
		
		return list;
	}
	
	public List<TileEntity> getTilesToTryPower()
	{
		List<TileEntity> list = Lists.newArrayList();
		
		if (getBlockMetadata() < 4/* && getEnergy() > 0*/)
		{
			for (TileEntity tile : (List<TileEntity>)worldObj.loadedTileEntityList)
			{
				if (tile instanceof IEnergyReceiver && receiverHandler.receivers.contains(tile))
				{
					IEnergyReceiver receiver = (IEnergyReceiver)tile;
					
					if (receiver.canReceiveEnergy() && receiver.getEnergy() < receiver.getMaxEnergy() && TFEnergyHelper.isInRange(this, tile))
					{
						list.add(tile);
					}
				}
			}
		}
		
		return list;
	}
	
	public boolean canPower(TileEntity tile)
	{
		if (tile instanceof IEnergyReceiver)
		{
			IEnergyReceiver receiver = (IEnergyReceiver)tile;
			Vec3 vec3 = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
			Vec3 vec31 = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
			Vec3 vec32 = getEnergyOutputOffset().addVector(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F);
			
			double d = 1F / vec3.distanceTo(vec32);
			vec32 = Vec3.createVectorHelper(vec32.xCoord + (vec3.xCoord - vec32.xCoord) * d, vec32.yCoord + (vec3.yCoord - vec32.yCoord) * d, vec32.zCoord + (vec3.zCoord - vec32.zCoord) * d);
			MovingObjectPosition mop = worldObj.rayTraceBlocks(vec32, vec3);
			
			if (mop != null)
			{	
				vec3 = mop.hitVec;
			}
			
			if (vec3.xCoord == vec31.xCoord && vec3.yCoord == vec31.yCoord && vec3.zCoord == vec31.zCoord)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isPowering(TileEntity tile)
	{
		return getTilesToPower().contains(tile);
	}
	
	public AxisAlignedBB getRenderBoundingBox()
	{
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0.35D, 0, 0.35D).addCoord(0, 2, 0);
		
		if (getBlockMetadata() < 4)
		{
			for (TileEntity tile : getTilesToTryPower())
			{
				aabb = TFHelper.wrapAroundAABB(aabb, tile.getRenderBoundingBox());
			}
		}
		
		return aabb;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		receiverHandler.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		tank.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		receiverHandler.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		tank.writeToNBT(nbt);
	}

	@Override
	public ReceiverHandler getReceiverHandler()
	{
		return receiverHandler;
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
		return new int[] {0};
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
}
