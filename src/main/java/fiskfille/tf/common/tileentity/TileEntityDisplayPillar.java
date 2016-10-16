package fiskfille.tf.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDisplayPillar extends TileEntity
{
    private ItemStack displayItem;

    @Override
    public void updateEntity()
    {
        super.updateEntity();
    }

    public void setDisplayItem(ItemStack item, boolean sync)
    {
        if (item != displayItem)
        {
            if (sync)
            {
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }

            displayItem = ItemStack.copyItemStack(item);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);

        NBTTagCompound itemTag = tagCompound.getCompoundTag("Item");

        displayItem = ItemStack.loadItemStackFromNBT(itemTag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);

        NBTTagCompound itemTag = new NBTTagCompound();

        if (displayItem != null)
        {
            displayItem.writeToNBT(itemTag);
        }

        tagCompound.setTag("Item", itemTag);
    }

    public ItemStack getDisplayItem()
    {
        return displayItem;
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
}