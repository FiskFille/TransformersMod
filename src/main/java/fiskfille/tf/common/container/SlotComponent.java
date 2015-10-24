package fiskfille.tf.common.container;

import fiskfille.tf.common.component.IComponent;
import fiskfille.tf.common.item.ItemColorComponent;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SlotComponent extends Slot
{
    public SlotComponent(IInventory iinventory, int id, int x, int y)
    {
        super(iinventory, id, x, y);
    }

    public int getSlotStackLimit()
    {
        return 1;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        if (itemstack == null)
        {
            return false;
        }

        return itemstack.getItem() instanceof IComponent;
    }

    public IIcon getBackgroundIconIndex()
    {
        return ItemColorComponent.outline;
    }
}
