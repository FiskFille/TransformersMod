package fiskfille.tf.common.tileentity;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.recipe.PowerManager;

public class TileEntityEnergonProcessor extends TileEntityContainer implements IFluidHandler, ISidedInventory
{
	private static final int[] slotsTop = new int[]{1};
	private static final int[] slotsBottom = new int[]{2};
	private static final int[] slotsSides = new int[]{0, 2};

	public FluidTank tank = new FluidTank(2000);

	private ItemStack[] inventory = new ItemStack[3];

	public int burnTime;
	public int powerTime;
	public int fillTime;
	public int currentMaxPowerTime;
	public float animationTimer;
	public float prevAnimationTimer;
	public int animationBurnTime;

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

		ItemStack power = getStackInSlot(0);
		ItemStack crystal = getStackInSlot(1);
		ItemStack canister = getStackInSlot(2);

		if (powerTime > 0)
		{
			--powerTime;
		}

		if (powerTime <= 0 && power != null && isItemValidForSlot(0, power) && canBurnCrystal(crystal))
		{
			currentMaxPowerTime = powerTime = PowerManager.getPowerSourceAmount(power) + 2;
			decrStackSize(0, 1);
			markDirty();
		}

		if (powerTime > 0 && canBurnCrystal(crystal))
		{
			if (burnTime < 200)
			{
				++burnTime;
			}
			else if (addContents(crystal))
			{
				decrStackSize(1, 1);
				burnTime = 0;
			}
		}
		else if (burnTime > 0)
		{
			--burnTime;
		}

		if (tank.getFluidAmount() > 0 && canister != null && canister.getItem() instanceof IFluidContainerItem && (ItemFuelCanister.isEmpty(canister) || ItemFuelCanister.getContainerFluid(canister).getFluid() == TFFluids.energon && !ItemFuelCanister.isFull(canister)))
		{
			if (fillTime < 100)
			{
				++fillTime;
			}
			else
			{
				fillCanister(canister);
				fillTime = 0;
			}
		}
		else if (fillTime > 0)
		{
			--fillTime;
		}
	}

	public void fillCanister(ItemStack itemstack)
	{
		if (itemstack.getItem() instanceof IFluidContainerItem)
		{
			IFluidContainerItem item = (IFluidContainerItem)itemstack.getItem();
			int amount = Math.min(tank.getFluidAmount(), item.getCapacity(itemstack) - ItemFuelCanister.getFluidAmount(itemstack));

			FluidStack stack = item.getFluid(itemstack);
			FluidStack stack1 = new FluidStack(TFFluids.energon, amount);
			NBTTagCompound tag = null;

			if (stack != null)
			{
				tag = (NBTTagCompound) (stack.tag != null ? stack.tag.copy() : null);
				FluidEnergon.addContents(stack, FluidEnergon.getContents(tank.getFluid()));
				FluidEnergon.setContents(stack1, FluidEnergon.getContents(stack));
			}
			else
			{
				FluidEnergon.setContents(stack1, FluidEnergon.getContents(tank.getFluid()));
			}

			Map<String, Integer> map = FluidEnergon.getContents(tank.getFluid());
			FluidEnergon.setContents(tank.getFluid(), FluidEnergon.getContents(stack1));
			stack1 = drain(ForgeDirection.UNKNOWN, stack1, false);
			
			if (tank.getFluid() != null)
			{
				FluidEnergon.setContents(tank.getFluid(), map);
			}

			int i = item.fill(itemstack, stack1, true);

			if (i > 0)
			{
				stack1.amount = i;
				drain(ForgeDirection.UNKNOWN, stack1, true);
			}
			else if (itemstack.hasTagCompound())
			{
				itemstack.getTagCompound().getCompoundTag("Fluid").setTag("Tag", tag);
			}
		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		notifyNeighborBlocksOfChange();
	}

	public boolean addContents(ItemStack crystal)
	{
		FluidStack stack = tank.getFluid();

		if (stack == null)
		{
			tank.setFluid(new FluidStack(TFFluids.energon, 0));
			stack = tank.getFluid();
		}

		if (stack.getFluid() == TFFluids.energon)
		{
			IEnergon energon = (IEnergon) (crystal.getItem() instanceof ItemBlock ? Block.getBlockFromItem(crystal.getItem()) : crystal.getItem());

			FluidStack stack1 = new FluidStack(TFFluids.energon, energon.getMass());
			FluidEnergon.setContents(stack, FluidEnergon.getContents(stack));
			FluidEnergon.setContents(stack1, FluidEnergon.getContents(stack));

			int i = fill(ForgeDirection.UNKNOWN, stack1, true);

			if (i > 0)
			{
				FluidEnergon.addContents(stack, Energon.createContentMap(energon.getMass(), energon.getEnergonType()));
			}

			return true;
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

	public boolean canBurnCrystal(ItemStack itemstack)
	{
		if (itemstack != null && isItemValidForSlot(1, itemstack))
		{
			IEnergon ienergon = (IEnergon) (itemstack.getItem() instanceof ItemBlock ? Block.getBlockFromItem(itemstack.getItem()) : itemstack.getItem());

			if (tank.getFluidAmount() + ienergon.getMass() <= tank.getCapacity())
			{
				return true;
			}
		}

		return false;
	}

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

	public AxisAlignedBB getRenderBoundingBox()
	{
		return super.getRenderBoundingBox().addCoord(0, 0.5D, 0);
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		burnTime = nbt.getInteger("BurnTime");
		powerTime = nbt.getInteger("PowerTime");
		fillTime = nbt.getInteger("FillTime");
		currentMaxPowerTime = nbt.getInteger("MaxPowerTime");

		tank.readFromNBT(nbt);
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("BurnTime", burnTime);
		nbt.setInteger("PowerTime", powerTime);
		nbt.setInteger("FillTime", fillTime);
		nbt.setInteger("MaxPowerTime", currentMaxPowerTime);

		tank.writeToNBT(nbt);
	}

	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return slot == 0 ? PowerManager.isPowerSource(stack) : (slot == 1 ? (stack.getItem() instanceof IEnergon || Block.getBlockFromItem(stack.getItem()) instanceof IEnergon) : (slot == 2 ? stack.getItem() == TFItems.fuelCanister && ItemFuelCanister.isEmpty(stack) && stack.stackSize == 1 && inventory[slot] == null : false));
	}

	public int[] getAccessibleSlotsFromSide(int side)
	{
		return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
	}

	public boolean canInsertItem(int slot, ItemStack stack, int side)
	{
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return isItemValidForSlot(slot, stack);
	}

	public boolean canExtractItem(int slot, ItemStack stack, int side)
	{
		return side != 0 || slot == 0 || (slot == 2 && ItemFuelCanister.isEmpty(stack));
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
