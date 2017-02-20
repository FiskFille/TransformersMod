package fiskfille.tf.common.container;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerEmpty extends ContainerBasic
{
    public ContainerEmpty(InventoryPlayer inventoryPlayer, int yOffset)
    {
        super(null);
        addPlayerInventory(inventoryPlayer, yOffset);
    }

    public ContainerEmpty(InventoryPlayer inventoryPlayer)
    {
        this(inventoryPlayer, 0);
    }
}
