package fiskfille.tf.common.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovementInputFromOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.item.ItemComponent;
import fiskfille.tf.common.item.ItemDisplayVehicle;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;

public class TileEntityDisplayStation extends TileEntityContainer implements IMultiTile
{
    private ItemStack[] inventory = new ItemStack[7];

    @SideOnly(Side.CLIENT)
    public EntityClientPlayerMP fakePlayer;

    @Override
    @SideOnly(Side.CLIENT)
    public void updateEntity()
    {
        if (fakePlayer == null)
        {
            Minecraft mc = Minecraft.getMinecraft();

            if (mc != null && mc.playerController != null && getWorldObj() != null)
            {
                fakePlayer = mc.playerController.func_147493_a(getWorldObj(), new StatFileWriter());
                fakePlayer.movementInput = new MovementInputFromOptions(mc.gameSettings);
            }
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return super.getRenderBoundingBox().addCoord(0, 1, 0);
    }

    public boolean transform()
    {
        ItemStack vehicle = getStackInSlot(6);
        if (vehicle != null)
        {
            if (getStackInSlot(0) == null && getStackInSlot(1) == null && getStackInSlot(2) == null && getStackInSlot(3) == null)
            {
                if (!vehicle.hasTagCompound())
                {
                    vehicle.setTagCompound(new NBTTagCompound());
                }

                ItemStack[] armorFromNBT = ItemDisplayVehicle.getArmorFromNBT(vehicle);

                if (armorFromNBT == null)
                {
                    ItemDisplayVehicle.setNBTData(vehicle);
                    armorFromNBT = ItemDisplayVehicle.getArmorFromNBT(vehicle);
                }

                for (int i = 0; i < armorFromNBT.length; ++i)
                {
                    setInventorySlotContents(i, armorFromNBT[i]);
                }

                setInventorySlotContents(6, null);
                getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);

                return true;
            }
        }
        else
        {
            ItemStack head = getStackInSlot(0);
            ItemStack chest = getStackInSlot(1);
            ItemStack legs = getStackInSlot(2);
            ItemStack feet = getStackInSlot(3);
            Transformer helmetTransformer = TFHelper.getTransformerFromArmor(head);
            Transformer chestTransformer = TFHelper.getTransformerFromArmor(chest);
            Transformer legsTransformer = TFHelper.getTransformerFromArmor(legs);
            Transformer feetTransformer = TFHelper.getTransformerFromArmor(feet);

            if (helmetTransformer != null && helmetTransformer == chestTransformer && chestTransformer == legsTransformer && legsTransformer == feetTransformer)
            {
                ItemStack itemstack = new ItemStack(TFItems.displayVehicle, 1);
                itemstack.setTagCompound(new NBTTagCompound());

                if (head != null && chest != null && legs != null && feet != null)
                {
                    Item headItem = head.getItem();
                    Item chestItem = chest.getItem();
                    Item legsItem = legs.getItem();
                    Item feetItem = feet.getItem();

                    int i = 0;

                    for (Transformer transformer : TransformersAPI.getTransformers())
                    {
                        Item helmet = transformer.getHelmet();
                        Item chestplate = transformer.getChestplate();
                        Item leggings = transformer.getLeggings();
                        Item boots = transformer.getBoots();

                        if (headItem == helmet && chestItem == chestplate && legsItem == leggings && feetItem == boots)
                        {
                            itemstack.setItemDamage(i);

                            ItemStack[] itemstacks = {head, chest, legs, feet};
                            NBTTagList itemsList = new NBTTagList();

                            for (int j = 0; j < itemstacks.length; ++j)
                            {
                                if (itemstacks[j] != null)
                                {
                                    NBTTagCompound itemTag = new NBTTagCompound();
                                    itemTag.setByte("Slot", (byte) j);
                                    itemstacks[j].writeToNBT(itemTag);
                                    itemsList.appendTag(itemTag);
                                }
                            }

                            itemstack.getTagCompound().setTag("Items", itemsList);

                            for (int j = 0; j < 4; ++j)
                            {
                                setInventorySlotContents(j, null);
                            }

                            setInventorySlotContents(6, itemstack);

                            return true;
                        }

                        i++;
                    }
                }
            }
        }

        return false;
    }

    public boolean setColor(int primaryColor, int secondaryColor)
    {
        ItemStack head = getStackInSlot(0);
        ItemStack chest = getStackInSlot(1);
        ItemStack legs = getStackInSlot(2);
        ItemStack feet = getStackInSlot(3);

        if (head != null && chest != null && legs != null && feet != null)
        {
            if (primaryColor == secondaryColor && primaryColor == Integer.MIN_VALUE)
            {
                TFArmorDyeHelper.removeColor(head);
                TFArmorDyeHelper.removeColor(chest);
                TFArmorDyeHelper.removeColor(legs);
                TFArmorDyeHelper.removeColor(feet);
            }
            else
            {
                TFArmorDyeHelper.setPrimaryColor(head, primaryColor);
                TFArmorDyeHelper.setPrimaryColor(chest, primaryColor);
                TFArmorDyeHelper.setPrimaryColor(legs, primaryColor);
                TFArmorDyeHelper.setPrimaryColor(feet, primaryColor);
                TFArmorDyeHelper.setSecondaryColor(head, secondaryColor);
                TFArmorDyeHelper.setSecondaryColor(chest, secondaryColor);
                TFArmorDyeHelper.setSecondaryColor(legs, secondaryColor);
                TFArmorDyeHelper.setSecondaryColor(feet, secondaryColor);
            }

            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

            return true;
        }

        return false;
    }

    @Override
    public ItemStack[] getItemStacks()
    {
        return inventory;
    }

    @Override
    public void setItemStacks(ItemStack[] itemstacks)
    {
        inventory = itemstacks;
    }

    @Override
    public String getInventoryName()
    {
        return "gui.display_station";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return slot < 4 ? stack.getItem() instanceof ItemTransformerArmor && stack.getItem().isValidArmor(stack, slot, null) : slot == 6 ? stack.getItem() == TFItems.displayVehicle : stack.getItem() instanceof ItemComponent;
    }

    @Override
    public int[] getBaseOffsets(int metadata)
    {
        return new int[] {0, -metadata / 4, 0};
    }
}
