package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
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
    public boolean isItemValid(ItemStack itemstack)
    {
        boolean flag = itemstack.getItem() instanceof ItemTransformerArmor;
        return type ? flag : !flag && tile.getStackInSlot(slotNumber % 4) != null;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex()
    {
        return !type && tile.getStackInSlot(slotNumber % 4) != null ? ItemArmor.func_94602_b(slotNumber % 4) : null;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack itemstack)
    {
        super.onPickupFromSlot(player, itemstack);
        int piece = slotNumber % 4;
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
        int piece = slotNumber % 4;
        ItemStack transformer = tile.getStackInSlot(piece);
        ItemStack shell = parent.craftMatrix.getStackInSlot(piece);

        if (transformer != null)
        {
            if (type)
            {
                parent.craftMatrix.setInventorySlotContents(piece, TFArmorHelper.getArmorShell(transformer));
            }
            else
            {
                TFArmorHelper.setArmorShell(transformer, shell);
            }
        }
    }
}
