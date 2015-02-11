package fiskfille.transformersmod.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBasic extends Container
{
	public void addPlayerInventory(InventoryPlayer par1InventoryPlayer, int par2)
	{
		int slotX;
		int slotY;
		
		for (slotX = 0; slotX < 3; ++slotX)
        {
            for (slotY = 0; slotY < 9; ++slotY)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, slotY + slotX * 9 + 9, 8 + slotY * 18, 84 + par2 + slotX * 18));
            }
        }

        for (slotX = 0; slotX < 9; ++slotX)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, slotX, 8 + slotX * 18, 142 + par2));
        }
	}

	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}
}