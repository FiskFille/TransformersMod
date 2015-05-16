package fiskfille.tf.common.tileentity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.google.common.collect.Maps;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.helper.TFHelper;

public class TileEntityEnergonProcessor extends TileEntity implements ISidedInventory
{
    private static final int[] slotsTop = new int[] {1};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    
    private ItemStack[] itemStacks = new ItemStack[3];
    private String inventoryName;
    
    public int burnTime;
    public int powerTime;
    public int liquidAmount;
    public int fillTime;
    public int currentMaxPowerTime;
    public float animationTimer;
    
    public HashMap<String, Integer> energonContentMap = Maps.newHashMap();
    public int liquidColor = 0xffffff;
    
    public void updateEntity()
    {
    	if (burnTime > 0)
    	{
    		animationTimer += 0.001F;
    		animationTimer *= 1.05F;
    	}
    	else
    	{
    		animationTimer *= 0.95F;
    	}
    	
    	if (animationTimer > 1.0F)
    	{
    		animationTimer = 1.0F;
    	}
    	
    	
//    	if (!worldObj.isRemote)
    	{
    		ItemStack power = itemStacks[0];
    		ItemStack crystal = itemStacks[1];
    		ItemStack container = itemStacks[2];
    		
    		if (powerTime > 0)
    		{
    			--powerTime;
    		}
    		
    		if (powerTime <= 0 && power != null && isItemValidForSlot(0, power) && canBurnCrystal(crystal))
    		{
    			currentMaxPowerTime = powerTime = PowerManager.getPowerSourceAmount(power) + 2;
    			decrStackSize(0, 1);
    		}
    		
    		if (powerTime > 0 && canBurnCrystal(crystal))
    		{
    			if (burnTime < 200)
    			{
    				++burnTime;
    			}
    			else if (burnTime >= 200)
    			{
    				IEnergon ienergon = (IEnergon)(crystal.getItem() instanceof ItemBlock ? Block.getBlockFromItem(crystal.getItem()) : crystal.getItem());
    				
    				if (liquidColor == 0xffffff)
    				{
    					liquidColor = ienergon.getEnergonType().getColor();
    				}
    				
    				int num = energonContentMap.get(ienergon.getEnergonType().getId()) == null ? 0 : energonContentMap.get(ienergon.getEnergonType().getId());
    				energonContentMap.put(ienergon.getEnergonType().getId(), num + ienergon.getMass());
    				liquidAmount += ienergon.getMass();
    				
    				burnTime = 0;
    				decrStackSize(1, 1);
    			}
    		}
    		else if (burnTime > 0)
    		{
    			--burnTime;
    		}
    		
    		if (liquidAmount > 0 && container != null && container.getItem() instanceof ItemBucket)
    		{
    			if (fillTime < 100)
    			{
    				++fillTime;
    			}
    			else if (fillTime >= 100)
    			{
    				
    			}
    		}
    		else if (fillTime > 0)
    		{
    			--fillTime;
    		}
    		
    		
    		for (Map.Entry<String, Integer> e : energonContentMap.entrySet())
    		{
    			for (Energon energon : TransformersAPI.getEnergonTypes())
    			{
    				if (energon.getId().equals(e.getKey()))
    				{
    					liquidColor = TFHelper.blend(liquidColor, energon.getColor(), 0.5F);
    				}
    			}
    		}
    	}
    }
    
    public boolean canBurnCrystal(ItemStack itemstack)
    {
    	if (itemstack != null && isItemValidForSlot(1, itemstack))
		{
			IEnergon ienergon = (IEnergon)(itemstack.getItem() instanceof ItemBlock ? Block.getBlockFromItem(itemstack.getItem()) : itemstack.getItem());
			
			if (liquidAmount + ienergon.getMass() <= 52)
			{
				return true;
			}
		}
    	
    	return false;
    }
    
    public int getSizeInventory()
    {
        return this.itemStacks.length;
    }
    
    public ItemStack getStackInSlot(int slot)
    {
        return this.itemStacks[slot];
    }
    
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.itemStacks[slot] != null)
        {
            ItemStack itemstack;
            
            if (this.itemStacks[slot].stackSize <= amount)
            {
                itemstack = this.itemStacks[slot];
                this.itemStacks[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.itemStacks[slot].splitStack(amount);

                if (this.itemStacks[slot].stackSize == 0)
                {
                    this.itemStacks[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
    
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        if (this.itemStacks[p_70304_1_] != null)
        {
            ItemStack itemstack = this.itemStacks[p_70304_1_];
            this.itemStacks[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }
    
    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        this.itemStacks[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }
    
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.inventoryName : "gui.energon_processor";
    }
    
    public boolean hasCustomInventoryName()
    {
        return this.inventoryName != null && this.inventoryName.length() > 0;
    }

    public void func_145951_a(String p_145951_1_)
    {
        this.inventoryName = p_145951_1_;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.itemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.itemStacks.length)
            {
                this.itemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        energonContentMap = (HashMap<String, Integer>)readMapFromString(nbt.getString("Contents"));
        burnTime = nbt.getInteger("BurnTime");
        powerTime = nbt.getInteger("PowerTime");
        liquidAmount = nbt.getInteger("LiquidAmount");
        liquidColor = nbt.getInteger("LiquidColor");
        fillTime = nbt.getInteger("FillTime");
        currentMaxPowerTime = nbt.getInteger("MaxPowerTime");
        
        if (nbt.hasKey("CustomName", 8))
        {
            this.inventoryName = nbt.getString("CustomName");
        }
    }
    
    public Map readMapFromString(String s)
    {
    	s = s.replace("{", "").replace("}", "");
    	String[] astring = s.split(", ");
    	Map map = Maps.newHashMap(); 
    	
    	for (String s1 : astring)
    	{
    		String key = s1.split("=")[0];
    		String value = s1.split("=")[1];
    		map.put(key, Integer.valueOf(value));
    	}
    	
    	return map;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("BurnTime", burnTime);
        nbt.setInteger("PowerTime", powerTime);
        nbt.setInteger("LiquidAmount", liquidAmount);
        nbt.setInteger("LiquidColor", liquidColor);
        nbt.setInteger("FillTime", fillTime);
        nbt.setInteger("MaxPowerTime", currentMaxPowerTime);
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
        
        nbt.setString("Contents", energonContentMap.toString());
        nbt.setTag("Items", nbttaglist);
        
        if (this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.inventoryName);
        }
    }
    
    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : p_70300_1_.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {}

    public void closeInventory() {}
    
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return slot == 0 ? PowerManager.isPowerSource(itemstack) : (slot == 1 ? (itemstack.getItem() instanceof IEnergon || Block.getBlockFromItem(itemstack.getItem()) instanceof IEnergon) : true);
    }
    
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
    }
    
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
    {
        return this.isItemValidForSlot(p_102007_1_, p_102007_2_);
    }
    
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return p_102008_3_ != 0 || p_102008_1_ != 1 || p_102008_2_.getItem() == Items.bucket;
    }
}