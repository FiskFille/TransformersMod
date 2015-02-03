package fiskfille.tf.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

public class TileEntityDisplayPillar extends TileEntity
{
	public ItemStack displayItem;
	
	public void updateEntity()
	{
		super.updateEntity();
	}

	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.displayItem = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag("Item"));
	}

	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		NBTTagCompound itemTag = new NBTTagCompound();
		displayItem.writeToNBT(itemTag);

		tagCompound.setTag("Item", itemTag);
	}
}