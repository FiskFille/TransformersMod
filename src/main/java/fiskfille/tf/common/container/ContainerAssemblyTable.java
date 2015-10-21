package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.recipe.AssemblyTableCraftingManager;

public class ContainerAssemblyTable extends ContainerBasic
{
	public InventoryAssembly craftMatrix = new InventoryAssembly(this, 5, 5);
	public IInventory craftResult = new InventoryCraftResult();
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;

	public ContainerAssemblyTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
	{
		this.worldObj = world;
		this.posX = x;
		this.posY = y;
		this.posZ = z;

		this.addSlotToContainer(new SlotAssembly(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 136, 25));
		this.addSlotToContainer(new Slot(this.craftMatrix, 0, 136, 56));
		this.addSlotToContainer(new Slot(this.craftMatrix, 4, 136, 74));
		this.addSlotToContainer(new Slot(this.craftMatrix, 20, 136, 92));		
		this.addSlotToContainer(new Slot(this.craftMatrix, 1, 42, 25));
		this.addSlotToContainer(new Slot(this.craftMatrix, 2, 60, 25));
		this.addSlotToContainer(new Slot(this.craftMatrix, 3, 78, 25));
		this.addSlotToContainer(new Slot(this.craftMatrix, 21, 42, 105));
		this.addSlotToContainer(new Slot(this.craftMatrix, 22, 60, 105));
		this.addSlotToContainer(new Slot(this.craftMatrix, 23, 78, 105));
		this.addSlotToContainer(new Slot(this.craftMatrix, 5, 20, 47));
		this.addSlotToContainer(new Slot(this.craftMatrix, 10, 20, 65));
		this.addSlotToContainer(new Slot(this.craftMatrix, 15, 20, 83));
		this.addSlotToContainer(new Slot(this.craftMatrix, 9, 100, 47));
		this.addSlotToContainer(new Slot(this.craftMatrix, 14, 100, 65));
		this.addSlotToContainer(new Slot(this.craftMatrix, 19, 100, 83));
		
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				this.addSlotToContainer(new Slot(this.craftMatrix, 6 + i + j * 2 + j * 3, 42 + i * 18, 47 + j * 18));
			}
		}
		
		this.addPlayerInventory(inventoryPlayer, 56);
		this.onCraftMatrixChanged(this.craftMatrix);
	}
	
	@Override
	public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
	{
		ItemStack itemstack = super.slotClick(p_75144_1_, p_75144_2_, p_75144_3_, p_75144_4_);
		this.onCraftMatrixChanged(this.craftMatrix);
		return itemstack;
	}
	
	public void onCraftMatrixChanged(IInventory iinventory)
	{
		this.craftResult.setInventorySlotContents(0, AssemblyTableCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
	}

	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);

		if (!this.worldObj.isRemote)
		{
			for (int i = 0; i < 25; ++i)
			{
				ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

				if (itemstack != null)
				{
					player.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
	}

	public boolean canInteractWith(EntityPlayer player)
	{
		return this.worldObj.getBlock(this.posX, this.posY, this.posZ) != TFBlocks.assemblyTable ? false : player.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
		ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(slotId);
        int MAX = 24;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // If itemstack is in Output stack
            if (slotId == MAX)
            {
                // try to place in player inventory / action bar; add 36 + 1 because mergeItemStack uses < index,
                // so the last slot in the inventory won't get checked if you don't add 1
                if (!mergeItemStack(itemstack1, MAX + 1, MAX + 36 + 1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            // itemstack is in player inventory, try to place in appropriate furnace slot
            else if (slotId > MAX)
            {
            	if (slotId >= MAX + 1 && slotId < MAX + 28)
                {
                    // place in action bar
                    if (!mergeItemStack(itemstack1, MAX + 28, MAX + 37, false))
                    {
                        return null;
                    }
                }
                // item in action bar - place in player inventory
                else if (slotId >= MAX + 28 && slotId < MAX + 37 && !mergeItemStack(itemstack1, MAX + 1, MAX + 28, false))
                {
                    return null;
                }
            }
            // In one of the infuser slots; try to place in player inventory / action bar
            else if (!mergeItemStack(itemstack1, MAX + 1, MAX + 37, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }
        
        return itemstack;
    }

	public boolean func_94530_a(ItemStack itemstack, Slot slot)
	{
		return slot.inventory != this.craftResult && super.func_94530_a(itemstack, slot);
	}
}
