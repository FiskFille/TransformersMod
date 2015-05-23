package fiskfille.tf.common.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;

public class ContainerEnergonProcessor extends ContainerBasic
{
	public static final int FUEL = 0, INPUT = 1, OUTPUT_1 = 2;
	
	private TileEntityEnergonProcessor tileentity;
    private int lastBurnTime;
    private int lastPowerTime;
    private int lastLiquidAmount;
    private int lastLiquidColor;
    private int lastFillTime;
    private int lastCurrentMaxPowerTime;
    
    public ContainerEnergonProcessor(InventoryPlayer inventoryPlayer, TileEntityEnergonProcessor tile)
    {
    	this.tileentity = tile;
        int i;
        int j;
        
        this.addSlotToContainer(new Slot(tile, 0, 24, 53));
        this.addSlotToContainer(new Slot(tile, 1, 24, 17));
        this.addSlotToContainer(new Slot(tile, 2, 138, 53)
        {
        	public int getSlotStackLimit()
        	{
        		return 1;
        	}
        });

        this.addPlayerInventory(inventoryPlayer, 0);
    }
    
    public boolean canInteractWith(EntityPlayer player)
    {
    	return true;
    }
    
    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.tileentity.burnTime);
        icrafting.sendProgressBarUpdate(this, 1, this.tileentity.powerTime);
        icrafting.sendProgressBarUpdate(this, 2, this.tileentity.liquidAmount);
        icrafting.sendProgressBarUpdate(this, 3, this.tileentity.liquidColor);
        icrafting.sendProgressBarUpdate(this, 4, this.tileentity.fillTime);
        icrafting.sendProgressBarUpdate(this, 5, this.tileentity.currentMaxPowerTime);
    }
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastBurnTime != this.tileentity.burnTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileentity.burnTime);
            }
            
            if (this.lastPowerTime != this.tileentity.powerTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileentity.powerTime);
            }
            
            if (this.lastLiquidAmount != this.tileentity.liquidAmount)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileentity.liquidAmount);
            }
            
            if (this.lastLiquidColor != this.tileentity.liquidColor)
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileentity.liquidColor);
            }
            
            if (this.lastFillTime != this.tileentity.fillTime)
            {
                icrafting.sendProgressBarUpdate(this, 4, this.tileentity.fillTime);
            }
            
            if (this.lastCurrentMaxPowerTime != this.tileentity.currentMaxPowerTime)
            {
                icrafting.sendProgressBarUpdate(this, 5, this.tileentity.currentMaxPowerTime);
            }
        }
        
        this.lastBurnTime = this.tileentity.burnTime;
        this.lastPowerTime = this.tileentity.powerTime;
        this.lastLiquidAmount = this.tileentity.liquidAmount;
        this.lastLiquidColor = this.tileentity.liquidColor;
        this.lastFillTime = this.tileentity.fillTime;
        this.lastCurrentMaxPowerTime = this.tileentity.currentMaxPowerTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
        	this.tileentity.burnTime = par2;
        }
        
        if (par1 == 1)
        {
        	this.tileentity.powerTime = par2;
        }
        
        if (par1 == 2)
        {
        	this.tileentity.liquidAmount = par2;
        }
        
        if (par1 == 3)
        {
        	this.tileentity.liquidColor = par2;
        }
        
        if (par1 == 4)
        {
        	this.tileentity.fillTime = par2;
        }
        
        if (par1 == 5)
        {
        	this.tileentity.currentMaxPowerTime = par2;
        }
    }
    
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
    	ItemStack itemstack = null;
    	Slot slot = (Slot)this.inventorySlots.get(par2);

    	if (slot != null && slot.getHasStack())
    	{
    		ItemStack itemstack1 = slot.getStack();
    		itemstack = itemstack1.copy();

    		// If itemstack is in Output stack
    		if (par2 == OUTPUT_1)
    		{
    			// try to place in player inventory / action bar; add 36 + 1 because mergeItemStack uses < index,
    			// so the last slot in the inventory won't get checked if you don't add 1
    			if (!this.mergeItemStack(itemstack1, OUTPUT_1 + 1, OUTPUT_1 + 36 + 1, true))
    			{
    				return null;
    			}

    			slot.onSlotChange(itemstack1, itemstack);
    		}
    		// itemstack is in player inventory, try to place in appropriate furnace slot
    		else if (par2 != FUEL && par2 != INPUT)
    		{
    			// if it can be smelted, place in the input slots
    			if (itemstack1.getItem() instanceof IEnergon || Block.getBlockFromItem(itemstack1.getItem()) instanceof IEnergon)
    			{
    				// try to place in either Input slot; add 1 to final input slot because mergeItemStack uses < index
    				if (!this.mergeItemStack(itemstack1, INPUT, INPUT + 1, false))
    				{
    					return null;
    				}
    			}
    			// if it's an energy source, place in Fuel slot
    			else if (PowerManager.isPowerSource(itemstack1))
    			{
    				if (!this.mergeItemStack(itemstack1, FUEL, FUEL + 1, false))
    				{
    					return null;
    				}
    			}
    			// item in player's inventory, but not in action bar
    			else if (par2 >= OUTPUT_1 + 1 && par2 < OUTPUT_1 + 28)
    			{
    				// place in action bar
    				if (!this.mergeItemStack(itemstack1, OUTPUT_1 + 28, OUTPUT_1 + 37, false))
    				{
    					return null;
    				}
    			}
    			// item in action bar - place in player inventory
    			else if (par2 >= OUTPUT_1 + 28 && par2 < OUTPUT_1 + 37 && !this.mergeItemStack(itemstack1, OUTPUT_1 + 1, OUTPUT_1 + 28, false))
    			{
    				return null;
    			}
    		}
    		// In one of the infuser slots; try to place in player inventory / action bar
    		else if (!this.mergeItemStack(itemstack1, OUTPUT_1 + 1, OUTPUT_1 + 37, false))
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

    		slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
    	}
    	return itemstack;
    }
}