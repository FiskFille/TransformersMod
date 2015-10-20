package fiskfille.tf.common.tileentity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.google.common.collect.Maps;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.helper.TFHelper;

public class TileEntityEnergonProcessor extends TileEntity implements ISidedInventory
{
    private static final int[] slotsTop = new int[] {1};
    private static final int[] slotsBottom = new int[] {2};
    private static final int[] slotsSides = new int[] {0, 2};
    
    private ItemStack[] itemStacks = new ItemStack[3];
    private String inventoryName;
    
    public int burnTime;
    public int powerTime;
    public int liquidAmount;
    public int fillTime;
    public int currentMaxPowerTime;
    public float animationTimer;
    public int animationBurnTime;
    
    public HashMap<String, Integer> energonContentMap = Maps.newHashMap();
    public int liquidColor = 0xffffff;
    
    public void updateEntity()
    {
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
        
        if (!worldObj.isRemote)
        {
//          worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            ItemStack power = itemStacks[0];
            ItemStack crystal = itemStacks[1];
            ItemStack canister = itemStacks[2];
            
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
                    
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    burnTime = 0;
                    decrStackSize(1, 1);
                    notifyNeighborBlocksOfChange();
                }
            }
            else if (burnTime > 0)
            {
                --burnTime;
            }
            
            if (liquidAmount > 0 && canister != null && canister.getItem() == TFItems.emptyFuelCanister && ItemFuelCanister.getContents(canister).isEmpty())
            {
                if (fillTime < 100)
                {
                    ++fillTime;
                }
                else if (fillTime >= 100)
                {
                    ItemStack itemstack = new ItemStack(TFItems.filledFuelCanister);
                    itemstack.setTagCompound(canister.getTagCompound());
                    itemstack.stackSize = canister.stackSize;
                    
                    ItemFuelCanister.refreshNBT(itemstack);
                    itemstack.getTagCompound().setString("Contents", energonContentMap.toString());
                    ItemFuelCanister.setLiquidColor(itemstack, liquidColor);
                    itemStacks[2] = itemstack.copy();
                    fillTime = 0;
                    energonContentMap.clear();
                    liquidAmount = 0;
                    liquidColor = 0xffffff;
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    notifyNeighborBlocksOfChange();
                }
            }
            else if (fillTime > 0)
            {
                --fillTime;
            }
            
            
            float percentMultiplier = 100F / liquidAmount;
            
            if (!energonContentMap.isEmpty())
            {
                for (Map.Entry<String, Integer> e : energonContentMap.entrySet())
                {
                    Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                    int percent = Math.round(e.getValue() * percentMultiplier);
                    
                    if (energon != null)
                    {
                        liquidColor = TFHelper.blend(liquidColor, energon.getColor(), (float)percent / 100);
                    }
                }
            }
        }
    }
    
    private void notifyNeighborBlocksOfChange()
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
            IEnergon ienergon = (IEnergon)(itemstack.getItem() instanceof ItemBlock ? Block.getBlockFromItem(itemstack.getItem()) : itemstack.getItem());
            
            if (liquidAmount + ienergon.getMass() <= 100)
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
    
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.itemStacks[slot] != null)
        {
            ItemStack itemstack = this.itemStacks[slot];
            this.itemStacks[slot] = null;
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
            byte slot = nbttagcompound1.getByte("Slot");
            
            if (slot >= 0 && slot < this.itemStacks.length)
            {
                this.itemStacks[slot] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
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
    
    public Map readMapFromString(String mapString)
    {
        mapString = mapString.replace("{", "").replace("}", "");
        String[] entries = mapString.split(", ");
        Map map = Maps.newHashMap(); 
        
        for (String entry : entries)
        {
            String[] keyValue = entry.split("=");
            
            if (keyValue.length == 2)
            {
                String key = keyValue[0];
                String value = keyValue[1];
                map.put(key, Integer.valueOf(value));
            }
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
    
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }
    
    public void openInventory() {}
    
    public void closeInventory() {}
    
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return slot == 0 ? PowerManager.isPowerSource(stack) : (slot == 1 ? (stack.getItem() instanceof IEnergon || Block.getBlockFromItem(stack.getItem()) instanceof IEnergon) : (slot == 2 ? stack.getItem() == TFItems.emptyFuelCanister && stack.stackSize == 1 && itemStacks[slot] == null : false));
    }
    
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
    }
    
    public boolean canInsertItem(int slot, ItemStack stack, int p_102007_3_)
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return this.isItemValidForSlot(slot, stack);
    }
    
    public boolean canExtractItem(int slot, ItemStack stack, int p_102008_3_)
    {
        return p_102008_3_ != 0 || slot == 0 || (slot == 2 && stack.getItem() == TFItems.filledFuelCanister);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeToNBT(syncData);
        
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }
    
    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
    {
        this.readFromNBT(packet.func_148857_g());
    }
}
