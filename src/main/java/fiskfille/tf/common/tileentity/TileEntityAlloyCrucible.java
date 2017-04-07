package fiskfille.tf.common.tileentity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.Constants.NBT;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.block.BlockAlloyCrucible;
import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.data.tile.TileDataEnergyContainer;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageTileTrigger.ITileDataCallback;
import fiskfille.tf.common.recipe.AlloyRecipes;
import fiskfille.tf.common.recipe.AlloyRecipes.AlloyIngredients;
import fiskfille.tf.helper.TFTileHelper;

public class TileEntityAlloyCrucible extends TileEntityMachineContainer implements IEnergyReceiver, ISidedInventory, ITileDataCallback
{
    private static final int[] slotsBottom = new int[] {3};
    private static final int[] slotsSides = new int[] {0, 1, 2};

    public ReceiverHandler receiverHandler = new ReceiverHandler(this);
    public TileDataEnergyContainer data = new TileDataEnergyContainer(32000);

    public EnumSmeltingMode smeltingMode = EnumSmeltingMode.ALLOY;
    public ItemStack smeltingResult;
    public boolean alloyResult;
    public int smeltTime;
    
    public int alloyResults;
    public int furnaceResults;
    
    private int metadataFlags;

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!data.isInitialized())
        {
            data.initialize(this);
        }

        if (!worldObj.isRemote)
        {
            if (canSmelt())
            {
                if (extractEnergy(getConsumptionRate(), false) >= getConsumptionRate())
                {
                    if (++smeltTime == getSmeltTimeMax())
                    {
                        smeltTime = 0;
                        smeltItem();
                        markDirty();
                    }
                }
            }
            else
            {
                smeltTime = 0;
            }

            data.serverTick();
        }

        TileData prevData = TFTileHelper.getTileData(new DimensionalCoords(this));

        if (prevData instanceof TileDataEnergyContainer)
        {
            data = new TileDataEnergyContainer((TileDataEnergyContainer) prevData);
        }
        
        int i = getMetadataFlags();

        if (i != metadataFlags)
        {
            metadataFlags = i;
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
        }
    }
    
    public int getMetadataFlags()
    {
        int metadata = BlockAlloyCrucible.getRotation(getBlockMetadata());
        
        if (getEnergy() > 0)
        {
            metadata |= BlockAlloyCrucible.FLAG_TOP;
        }
        
        if (canSmelt())
        {
            metadata |= BlockAlloyCrucible.FLAG_FRONT;
        }
        
        return metadata;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
            data.kill();
        }
    }
    
    @Override
    public int getSizeInventory()
    {
        return 4;
    }

    @Override
    public String getInventoryName()
    {
        return "gui.alloy_crucible";
    }

    public boolean canSmelt()
    {
        return canActivate() && getEnergy() >= getConsumptionRate() && getStacksToSmelt().length > 0;
    }

    public float getConsumptionRate()
    {
        return 2;
    }

    public int getSmeltTimeMax()
    {
        return alloyResult && smeltingResult != null ? AlloyRecipes.getInstance().getSmeltTime(smeltingResult) : 150;
    }

    @SideOnly(Side.CLIENT)
    public int getSmeltProgressScaled(int i)
    {
        return smeltTime * i / getSmeltTimeMax();
    }

    public ItemStack[] getStacksToSmelt()
    {
        LinkedList<ItemStack> list = Lists.newLinkedList();

        if (smeltingMode != EnumSmeltingMode.FURNACE)
        {
            for (int i = 0; i < 3; ++i)
            {
                if (inventory[i] != null)
                {
                    list.add(inventory[i]);
                    ItemStack result = AlloyRecipes.getInstance().getSmeltingResult(new AlloyIngredients(list.toArray()));

                    if (result != null)
                    {
                        if (inventory[3] == null)
                        {
                            smeltingResult = result;
                            alloyResult = true;

                            return list.toArray(new ItemStack[3]);
                        }
                        else if (inventory[3].isItemEqual(result))
                        {
                            int amount = inventory[3].stackSize + result.stackSize;

                            if (amount <= getInventoryStackLimit() && amount <= inventory[3].getMaxStackSize())
                            {
                                smeltingResult = result;
                                alloyResult = true;

                                return list.toArray(new ItemStack[3]);
                            }
                        }
                    }
                }
            }

            list.clear();
        }

        if (smeltingMode != EnumSmeltingMode.ALLOY)
        {
            for (int i = 0; i < 3; ++i)
            {
                if (inventory[i] != null)
                {
                    ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inventory[i]);

                    if (result != null)
                    {
                        if (inventory[3] == null)
                        {
                            list.add(inventory[i]);
                        }
                        else if (inventory[3].isItemEqual(result))
                        {
                            int amount = inventory[3].stackSize + result.stackSize;

                            if (amount <= getInventoryStackLimit() && amount <= inventory[3].getMaxStackSize())
                            {
                                list.add(inventory[i]);
                            }
                        }
                    }
                }
            }
        }

        if (!list.isEmpty())
        {
            smeltingResult = list.getFirst();
            alloyResult = false;
        }

        return list.toArray(new ItemStack[list.size()]);
    }

    public void smeltItem()
    {
        if (canSmelt())
        {
            if (smeltingMode != EnumSmeltingMode.FURNACE)
            {
                ItemStack result = AlloyRecipes.getInstance().getSmeltingResult(new AlloyIngredients(Arrays.asList(getStacksToSmelt()).toArray()));

                if (result != null)
                {
                    if (inventory[3] == null)
                    {
                        inventory[3] = result.copy();
                    }
                    else if (inventory[3].getItem() == result.getItem())
                    {
                        inventory[3].stackSize += result.stackSize;
                    }

                    List<ItemStack> list = Lists.newArrayList(getStacksToSmelt());

                    for (int i = 0; i < 3; ++i)
                    {
                        if (inventory[i] != null && list.contains(inventory[i]))
                        {
                            list.remove(inventory[i]);
                            --inventory[i].stackSize;

                            if (inventory[i].stackSize <= 0)
                            {
                                inventory[i] = null;
                            }
                        }
                    }

                    smeltingResult = null;
                    ++alloyResults;

                    return;
                }
            }

            if (smeltingMode != EnumSmeltingMode.ALLOY)
            {
                ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(getStacksToSmelt()[0]);

                if (result != null)
                {
                    for (int i = 0; i < 3; ++i)
                    {
                        if (inventory[i] == getStacksToSmelt()[0])
                        {
                            if (inventory[3] == null)
                            {
                                inventory[3] = result.copy();
                            }
                            else if (inventory[3].getItem() == result.getItem())
                            {
                                inventory[3].stackSize += result.stackSize;
                            }

                            --inventory[i].stackSize;

                            if (inventory[i].stackSize <= 0)
                            {
                                inventory[i] = null;
                            }

                            smeltingResult = null;
                            ++furnaceResults;

                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);
        smeltingMode = EnumSmeltingMode.values()[nbt.getByte("Mode") % EnumSmeltingMode.values().length];
        smeltTime = nbt.getShort("SmeltTime");
        alloyResults = nbt.getByte("AlloySmelts");
        furnaceResults = nbt.getByte("FurnaceSmelts");

        if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.storage.readFromNBT(config);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        super.writeCustomNBT(nbt);
        nbt.setByte("Mode", (byte) smeltingMode.ordinal());
        nbt.setShort("SmeltTime", (short) smeltTime);
        nbt.setByte("AlloySmelts", (byte) alloyResults);
        nbt.setByte("FurnaceSmelts", (byte) furnaceResults);

        if (data.storage.getEnergy() > 0)
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.storage.writeToNBT(config);
            nbt.setTag("ConfigDataTF", config);
        }
    }

    @Override
    public float receiveEnergy(float amount, boolean simulate)
    {
        return data.storage.add(amount, simulate);
    }

    @Override
    public float extractEnergy(float amount, boolean simulate)
    {
        return data.storage.remove(amount, simulate);
    }

    @Override
    public float getEnergy()
    {
        return data.storage.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return data.storage.getMaxEnergy();
    }

    @Override
    public float getEnergyUsage()
    {
        return data.storage.getUsage();
    }

    @Override
    public ReceiverHandler getReceiverHandler()
    {
        return receiverHandler;
    }

    @Override
    public boolean canReceiveEnergy(TileEntity from)
    {
        return true;
    }

    @Override
    public Vec3 getEnergyInputOffset()
    {
        return Vec3.createVectorHelper(0, 0.5D, 0);
    }

    @Override
    public int getMapColor()
    {
        return 0xFF0000;
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (slot == 3)
        {
            ItemStack itemstack = getStackInSlot(slot);
            
            if (itemstack != null)
            {
                int toRemove = Math.min(itemstack.stackSize, amount);
                int i = Math.min(alloyResults, toRemove);
                
                toRemove -= i;
                alloyResults -= i;
                furnaceResults -= Math.min(furnaceResults, toRemove);
            }
        }
        
        return super.decrStackSize(slot, amount);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return slot != 3;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == 0 ? slotsBottom : slotsSides;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side)
    {
        return isItemValidForSlot(slot, itemstack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side)
    {
        return slot == 3;
    }

    @Override
    public void receive(EntityPlayer player, int action)
    {
        super.receive(player, action);
        
        if (action == 0)
        {
            smeltingMode = EnumSmeltingMode.values()[(smeltingMode.ordinal() + 1) % EnumSmeltingMode.values().length];
        }

        markDirty();
    }

    public enum EnumSmeltingMode
    {
        ALLOY, FURNACE, BOTH
    }
}
