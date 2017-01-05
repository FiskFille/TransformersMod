package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.helper.TFArmorHelper;

public class SlotDisplayStationArmor extends Slot
{
    private ContainerDisplayStationArmor parent;
    private TileEntityDisplayStation tile;
    private boolean type;

    public SlotDisplayStationArmor(ContainerDisplayStationArmor parent, IInventory inventory, TileEntityDisplayStation tile, int id, int x, int y)
    {
        super(inventory, id, x, y);
        this.parent = parent;
        this.tile = tile;
        this.type = tile == inventory;
    }

    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack itemstack)
    {
        super.onPickupFromSlot(player, itemstack);
        int piece = type ? slotNumber : slotNumber - 4;
        ItemStack transformer = tile.getStackInSlot(piece);
        parent.craftMatrix.getStackInSlot(piece);

        if (type)
        {
            parent.craftMatrix.setInventorySlotContents(piece, null);
        }
        else
        {
            if (transformer != null)
            {
                TFArmorHelper.setArmorShell(transformer, null);
            }
        }
    }

    @Override
    public void putStack(ItemStack itemstack)
    {
        super.putStack(itemstack);
        int piece = type ? slotNumber : slotNumber - 4;
        ItemStack transformer = tile.getStackInSlot(piece);
        ItemStack shell = parent.craftMatrix.getStackInSlot(piece);

        if (type)
        {
            if (transformer != null)
            {
                parent.craftMatrix.setInventorySlotContents(piece, TFArmorHelper.getArmorShell(transformer));
            }
        }
        else
        {
            if (transformer != null)
            {
                TFArmorHelper.setArmorShell(transformer, shell);
            }
        }
    }
}
