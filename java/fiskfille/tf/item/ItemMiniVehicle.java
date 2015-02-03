package fiskfille.tf.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMiniVehicle extends ItemMetaBasic implements IDisplayPillarItem
{
	public ItemMiniVehicle(String... itemNames)
	{
		super(itemNames);
		this.setMaxStackSize(1);
	}
	
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_)
    {
    	if (getArmorFromNBT(itemstack) != null)
    	{
    		list.add("Equippable");
    	}
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
    	if (player.isSneaking() && getArmorFromNBT(itemstack) != null)
    	{
    		if (getArmorFromNBT(itemstack)[0] != null)
    		{
        		if (!player.worldObj.isRemote)
        		{
        			if (player.getCurrentArmor(3) != null)
        			{
        				player.entityDropItem(player.getCurrentArmor(3), 0);
        			}
        		}
    			
    			player.setCurrentItemOrArmor(4, getArmorFromNBT(itemstack)[0]);
    		}
    		if (getArmorFromNBT(itemstack)[1] != null)
    		{
        		if (!player.worldObj.isRemote)
        		{
        			if (player.getCurrentArmor(2) != null)
        			{
        				player.entityDropItem(player.getCurrentArmor(2), 0);
        			}
        		}
    			
    			player.setCurrentItemOrArmor(3, getArmorFromNBT(itemstack)[1]);
    		}
    		if (getArmorFromNBT(itemstack)[2] != null)
    		{
        		if (!player.worldObj.isRemote)
        		{
        			if (player.getCurrentArmor(1) != null)
        			{
        				player.entityDropItem(player.getCurrentArmor(1), 0);
        			}
        		}
    			
    			player.setCurrentItemOrArmor(2, getArmorFromNBT(itemstack)[2]);
    		}
    		if (getArmorFromNBT(itemstack)[3] != null)
    		{    			
        		if (!player.worldObj.isRemote)
        		{
        			if (player.getCurrentArmor(0) != null)
        			{
        				player.entityDropItem(player.getCurrentArmor(0), 0);
        			}
        		}
        		
    			player.setCurrentItemOrArmor(1, getArmorFromNBT(itemstack)[3]);
    		}
    		
    		player.setCurrentItemOrArmor(0, null);
    	}
    	
    	return itemstack;
    }
    
    public ItemStack[] getArmorFromNBT(ItemStack itemstack)
    {
    	if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Items"))
    	{
        	NBTTagList nbttaglist = itemstack.getTagCompound().getTagList("Items", 10);
            ItemStack[] itemstacks = new ItemStack[4];

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
                byte b0 = nbttagcompound1.getByte("Slot");

                if (b0 >= 0 && b0 < itemstacks.length)
                {
                    itemstacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
                }
            }
            
        	return itemstacks;
    	}
    	
    	return null;
    }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		
	}
}