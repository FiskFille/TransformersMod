package fiskfille.tf.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBasic extends Container
{
	public void addPlayerInventory(InventoryPlayer inventoryPlayer, int yOffset)
	{
		int i;
		int j;
		
		for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + yOffset + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142 + yOffset));
        }
	}

	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}
}