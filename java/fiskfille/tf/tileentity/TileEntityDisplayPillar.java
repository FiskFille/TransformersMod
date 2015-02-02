package fiskfille.tf.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

public class TileEntityDisplayPillar extends TileEntity implements ISidedInventory
{
	private ItemStack[] itemStacks = new ItemStack[1];
	public ItemStack displayItem;
	public boolean refreshed = false;
	
	public void updateEntity()
    {
//        if (!this.worldObj.isRemote) Fisk what is this?! xD if is on server set player to something that can only be retrieved on client
//        {
//        	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
//        }
    }
	
	public int getSizeInventory()
	{
		return itemStacks.length;
	}
	
	public ItemStack getStackInSlot(int i) 
	{
		return itemStacks[i];
	}
	
	public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.itemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.itemStacks[par1].stackSize <= par2)
            {
                itemstack = this.itemStacks[par1];
                this.itemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.itemStacks[par1].splitStack(par2);

                if (this.itemStacks[par1].stackSize == 0)
                {
                    this.itemStacks[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
        this.itemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.itemStacks.length)
            {
                this.itemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
//        this.displayItem = ItemStack.loadItemStackFromNBT(par1NBTTagCompound);
    }
	
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
//        this.displayItem.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.itemStacks.length; ++i)
        {
            if (this.itemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.itemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
    }
    
//    public Packet getDescriptionPacket()
//    {
//        return new PacketUpdateDisplayPillar();
//    }

	public ItemStack getStackInSlotOnClosing(int i) 
	{	
		if (this.itemStacks[i] != null)
		{
			ItemStack itemstack = this.itemStacks[i];
			this.itemStacks[i] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}
	
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.itemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }
	
	public int getInventoryStackLimit() 
	{		
		return 64;
	}
	
	public boolean isUseableByPlayer(EntityPlayer entityplayer) 
	{		
		return true;
	}

	public boolean isItemValidForSlot(int slot, ItemStack itemstack) 
	{
		return true;
	}

	public int[] getAccessibleSlotsFromSide(int var1) 
	{	
		return new int[]{0};
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j) 
	{
		return false;
	}

	public boolean canExtractItem(int i, ItemStack itemstack, int j) 
	{
		return false;
	}
	
	public String getInventoryName()
	{
		return StatCollector.translateToLocal("gui.display_pillar.name");
	}
	
	public boolean hasCustomInventoryName()
	{
		return false;
	}
	
	public void openInventory()
	{
		
	}
	
	public void closeInventory()
	{
		
	}
}