package fiskfille.tf.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemDisplayVehicle extends Item
{
    public ItemDisplayVehicle()
    {
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        Transformer transformer = TransformersAPI.getTransformers().get(stack.getItemDamage());

        if (transformer != null)
        {
            return StatCollector.translateToLocal("item.display_" + transformer.getName().toLowerCase().replaceAll(" ", "_") + ".name");
        }
        else
        {
            return super.getItemStackDisplayName(stack);
        }
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List info, boolean p_77624_4_)
    {
        info.add("Equippable");
    }

    public static void setNBTData(ItemStack itemstack)
    {
        int transformerIndex = 0;

        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            if (transformerIndex == itemstack.getItemDamage())
            {
                ItemStack head = new ItemStack(transformer.getHelmet());
                ItemStack chest = new ItemStack(transformer.getChestplate());
                ItemStack legs = new ItemStack(transformer.getLeggings());
                ItemStack feet = new ItemStack(transformer.getBoots());

                ItemStack[] itemstacks = {head, chest, legs, feet};

                NBTTagList itemsList = new NBTTagList();

                for (int i = 0; i < itemstacks.length; ++i)
                {
                    if (itemstacks[i] != null)
                    {
                        NBTTagCompound itemTag = new NBTTagCompound();
                        itemTag.setByte("Slot", (byte) i);
                        itemstacks[i].writeToNBT(itemTag);
                        itemsList.appendTag(itemTag);
                    }
                }

                itemstack.getTagCompound().setTag("Items", itemsList);
            }

            transformerIndex++;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!itemstack.hasTagCompound())
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (player.isSneaking())
        {
            ItemStack[] armorFromNBT = getArmorFromNBT(itemstack);

            if (armorFromNBT == null)
            {
                setNBTData(itemstack);
                armorFromNBT = getArmorFromNBT(itemstack);
            }

            boolean server = !player.worldObj.isRemote;

            if (armorFromNBT[0] != null)
            {
                if (server)
                {
                    if (player.getCurrentArmor(3) != null)
                    {
                        player.entityDropItem(player.getCurrentArmor(3), 0);
                    }
                }

                player.setCurrentItemOrArmor(4, armorFromNBT[0]);
            }

            if (armorFromNBT[1] != null)
            {
                if (server)
                {
                    if (player.getCurrentArmor(2) != null)
                    {
                        player.entityDropItem(player.getCurrentArmor(2), 0);
                    }
                }

                player.setCurrentItemOrArmor(3, armorFromNBT[1]);
            }

            if (armorFromNBT[2] != null)
            {
                if (server)
                {
                    if (player.getCurrentArmor(1) != null)
                    {
                        player.entityDropItem(player.getCurrentArmor(1), 0);
                    }
                }

                player.setCurrentItemOrArmor(2, armorFromNBT[2]);
            }

            if (armorFromNBT[3] != null)
            {
                if (server)
                {
                    if (player.getCurrentArmor(0) != null)
                    {
                        player.entityDropItem(player.getCurrentArmor(0), 0);
                    }
                }

                player.setCurrentItemOrArmor(1, armorFromNBT[3]);
            }

            player.setCurrentItemOrArmor(0, null);
        }

        return itemstack;
    }

    public static ItemStack[] getArmorFromNBT(ItemStack itemstack)
    {
        if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Items"))
        {
            NBTTagList nbtItems = itemstack.getTagCompound().getTagList("Items", 10);
            ItemStack[] items = new ItemStack[4];

            for (int i = 0; i < nbtItems.tagCount(); ++i)
            {
                NBTTagCompound item = nbtItems.getCompoundTagAt(i);

                byte slot = item.getByte("Slot");

                if (slot >= 0 && slot < items.length)
                {
                    items[slot] = ItemStack.loadItemStackFromNBT(item);
                }
            }

            return items;
        }

        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List subItems)
    {
        int index = 0;

        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            subItems.add(new ItemStack(this, 1, index));
            index++;
        }
    }
}