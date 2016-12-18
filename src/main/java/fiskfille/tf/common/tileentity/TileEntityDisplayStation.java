package fiskfille.tf.common.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntity;
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

public class TileEntityDisplayStation extends TileEntity implements IInventory, IMultiTile
{
    private ItemStack[] itemStacks = new ItemStack[7];
    private String inventoryName;

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

    public boolean transform()
    {
        ItemStack vehicle = getStackInSlot(6);
        ItemDisplayVehicle item = (ItemDisplayVehicle) TFItems.displayVehicle;

        if (vehicle != null)
        {
            if (getStackInSlot(0) == null && getStackInSlot(1) == null && getStackInSlot(2) == null && getStackInSlot(3) == null)
            {
                if (!vehicle.hasTagCompound())
                {
                    vehicle.setTagCompound(new NBTTagCompound());
                }

                ItemStack[] armorFromNBT = item.getArmorFromNBT(vehicle);

                if (armorFromNBT == null)
                {
                    item.setNBTData(vehicle);
                    armorFromNBT = item.getArmorFromNBT(vehicle);
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
    public int getSizeInventory()
    {
        return itemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return itemStacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (itemStacks[slot] != null)
        {
            ItemStack itemstack;

            if (itemStacks[slot].stackSize <= amount)
            {
                itemstack = itemStacks[slot];
                itemStacks[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = itemStacks[slot].splitStack(amount);

                if (itemStacks[slot].stackSize == 0)
                {
                    itemStacks[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (itemStacks[slot] != null)
        {
            ItemStack itemstack = itemStacks[slot];
            itemStacks[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        itemStacks[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return hasCustomInventoryName() ? inventoryName : "gui.display_station";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return inventoryName != null && inventoryName.length() > 0;
    }

    public void func_145951_a(String p_145951_1_)
    {
        inventoryName = p_145951_1_;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        itemStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte slot = nbttagcompound1.getByte("Slot");

            if (slot >= 0 && slot < itemStacks.length)
            {
                itemStacks[slot] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        if (nbt.hasKey("CustomName", 8))
        {
            inventoryName = nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < itemStacks.length; ++i)
        {
            if (itemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                itemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);

        if (hasCustomInventoryName())
        {
            nbt.setString("CustomName", inventoryName);
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return slot < 4 ? stack.getItem() instanceof ItemTransformerArmor && stack.getItem().isValidArmor(stack, slot, null) : (slot == 6 ? stack.getItem() == TFItems.displayVehicle : stack.getItem() instanceof ItemComponent);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        writeToNBT(syncData);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public int[] getBaseOffsets(int metadata)
    {
        return new int[] {0, -metadata / 4, 0};
    }
}
