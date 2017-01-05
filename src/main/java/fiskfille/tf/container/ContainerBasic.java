package fiskfille.tf.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBasic extends Container
{
	public void addPlayerInventory(InventoryPlayer par1InventoryPlayer, int par2)
	{
		int i;
		int j;
		
		for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + par2 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142 + par2));
        }
	}

	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}
}