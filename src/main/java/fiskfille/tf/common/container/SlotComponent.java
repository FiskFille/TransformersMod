package fiskfille.tf.common.container;

import fiskfille.tf.common.component.IComponent;
import fiskfille.tf.common.item.ItemComponent;
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

    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack itemstack)
    {
        return itemstack != null && itemstack.getItem() instanceof IComponent;

    }

    @Override
    public IIcon getBackgroundIconIndex()
    {
        return ItemComponent.outline;
    }
}
