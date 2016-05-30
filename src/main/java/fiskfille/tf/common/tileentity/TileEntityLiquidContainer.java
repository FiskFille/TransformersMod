package fiskfille.tf.common.tileentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.helper.TFRenderHelper;

public abstract class TileEntityLiquidContainer extends TileEntity
{
    public Map<String, Float> energonContentMap = Maps.newTreeMap();
    public float liquidAmount;
    public int liquidColor = 0xffffff;
    
    public abstract float getMaxStorage();
    
    public void addContents(ItemStack itemstack)
    {
    	IEnergon ienergon = (IEnergon) (itemstack.getItem() instanceof ItemBlock ? Block.getBlockFromItem(itemstack.getItem()) : itemstack.getItem());

        float num = energonContentMap.get(ienergon.getEnergonType().getId()) == null ? 0 : energonContentMap.get(ienergon.getEnergonType().getId());
        energonContentMap.put(ienergon.getEnergonType().getId(), num + ienergon.getMass());
        liquidAmount += ienergon.getMass();
        liquidColor = 0xffffff;

        ArrayList<String> list = Lists.newArrayList();

        for (Map.Entry<String, Float> e : energonContentMap.entrySet())
        {
            list.add(e.getKey() + ": " + e.getValue());
        }

        Collections.sort(list);
        energonContentMap.clear();

        for (String s : list)
        {
            String[] astring = s.split(": ");
            energonContentMap.put(astring[0], Float.valueOf(astring[1]));
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        notifyNeighborBlocksOfChange();
    }
    
    public ItemStack fillCanister(ItemStack itemstack)
    {
    	ItemStack itemstack1 = new ItemStack(TFItems.filledFuelCanister, itemstack.stackSize);
        itemstack1.setTagCompound(itemstack.getTagCompound());

        ItemFuelCanister.refreshNBT(itemstack1);
        itemstack1.getTagCompound().setString("Contents", energonContentMap.toString());
        ItemFuelCanister.setLiquidColor(itemstack1, liquidColor);
        
        energonContentMap.clear();
        liquidAmount = 0;
        liquidColor = 0xffffff;
        
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        notifyNeighborBlocksOfChange();
        return itemstack1;
    }
    
    public void blendColor()
    {
    	float percentMultiplier = 1.0F / liquidAmount;

        for (Map.Entry<String, Float> e : energonContentMap.entrySet())
        {
            Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
            float percent = e.getValue() * percentMultiplier;

            if (energon != null)
            {
                liquidColor = TFRenderHelper.blend(liquidColor, energon.getColor(), percent);
            }
        }
    }
    
    public void notifyNeighborBlocksOfChange()
    {
        worldObj.getBlock(xCoord + 1, yCoord, zCoord).onNeighborBlockChange(worldObj, xCoord + 1, yCoord, zCoord, blockType);
        worldObj.getBlock(xCoord - 1, yCoord, zCoord).onNeighborBlockChange(worldObj, xCoord - 1, yCoord, zCoord, blockType);
        worldObj.getBlock(xCoord, yCoord, zCoord + 1).onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord + 1, blockType);
        worldObj.getBlock(xCoord, yCoord, zCoord - 1).onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord - 1, blockType);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        energonContentMap = (HashMap<String, Float>)ItemFuelCanister.readMapFromString(nbt.getString("Contents"));
        liquidAmount = nbt.getFloat("LiquidAmount");
        liquidColor = nbt.getInteger("LiquidColor");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setString("Contents", energonContentMap.toString());
        nbt.setFloat("LiquidAmount", liquidAmount);
        nbt.setInteger("LiquidColor", liquidColor);
    }
}
