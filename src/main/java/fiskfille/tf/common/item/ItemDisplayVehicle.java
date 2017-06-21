package fiskfille.tf.common.item;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import java.util.List;

public class ItemDisplayVehicle extends Item
{
    public ItemDisplayVehicle()
    {
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        Transformer transformer = TransformersAPI.REGISTRY.getObjectById(stack.getItemDamage());

        if (transformer != null)
        {
            return I18n.translateToLocal("item.display_" + transformer.getIdentifier().getResourcePath() + ".name");
        }
        else
        {
            return super.getItemStackDisplayName(stack);
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> info, boolean advanced)
    {
        info.add("Equippable");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack heldItem = player.getHeldItem(hand);

        if (!heldItem.hasTagCompound())
        {
            heldItem.setTagCompound(new NBTTagCompound());
        }

        if (player.isSneaking())
        {
            ItemStack[] armor = getArmorFromNBT(heldItem);

            if (armor == null)
            {
                setNBTData(heldItem);
                armor = getArmorFromNBT(heldItem);
            }

            for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
            {
                if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
                {
                    ItemStack current = player.getItemStackFromSlot(slot);

                    if (!current.isEmpty())
                    {
                        if (!player.inventory.addItemStackToInventory(current))
                        {
                            if (!player.world.isRemote)
                            {
                                player.entityDropItem(current, 0.0F);
                            }
                        }
                    }

                    player.setItemStackToSlot(slot, armor[slot.getIndex()]);
                }
            }

            return ActionResult.newResult(EnumActionResult.SUCCESS, ItemStack.EMPTY);
        }

        return ActionResult.newResult(EnumActionResult.PASS, heldItem);
    }

    public static void setNBTData(ItemStack stack)
    {
        int transformerIndex = 0;

        for (Transformer transformer : TransformersAPI.REGISTRY)
        {
            if (transformerIndex == stack.getItemDamage())
            {
                ItemStack head = new ItemStack(transformer.getHelmet());
                ItemStack chest = new ItemStack(transformer.getChestplate());
                ItemStack legs = new ItemStack(transformer.getLeggings());
                ItemStack feet = new ItemStack(transformer.getBoots());

                ItemStack[] stacks = { head, chest, legs, feet };
                NBTTagList itemsList = new NBTTagList();

                for (int i = 0; i < stacks.length; ++i)
                {
                    if (!stacks[i].isEmpty())
                    {
                        NBTTagCompound itemTag = new NBTTagCompound();
                        itemTag.setByte("Slot", (byte) i);
                        stacks[i].writeToNBT(itemTag);
                        itemsList.appendTag(itemTag);
                    }
                }

                stack.getTagCompound().setTag("Items", itemsList);
                break;
            }

            transformerIndex++;
        }
    }

    public static ItemStack[] getArmorFromNBT(ItemStack held)
    {
        if (held.hasTagCompound() && held.getTagCompound().hasKey("Items"))
        {
            NBTTagList nbtItems = held.getTagCompound().getTagList("Items", 10);
            ItemStack[] items = new ItemStack[4];

            for (int i = 0; i < nbtItems.tagCount(); ++i)
            {
                NBTTagCompound item = nbtItems.getCompoundTagAt(i);
                byte slot = item.getByte("Slot");

                if (slot >= 0 && slot < items.length)
                {
                    items[slot] = new ItemStack(item);
                }
            }

            return items;
        }

        return null;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        for (Transformer transformer : TransformersAPI.REGISTRY)
        {
            subItems.add(new ItemStack(this, 1, TransformersAPI.REGISTRY.getIDForObject(transformer)));
        }
    }
}
