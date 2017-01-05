package fiskfille.tf.common.container;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.tf.common.recipe.AlloyRecipes;

public class SlotAlloyCrucible extends Slot
{
    private EntityPlayer thePlayer;
    private int totalAmount;

    public SlotAlloyCrucible(EntityPlayer player, IInventory inventory, int id, int x, int y)
    {
        super(inventory, id, x, y);
        thePlayer = player;
    }

    @Override
    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount)
    {
        if (getHasStack())
        {
            totalAmount += Math.min(amount, getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack itemstack)
    {
        onCrafting(itemstack);
        super.onPickupFromSlot(player, itemstack);
    }

    @Override
    protected void onCrafting(ItemStack itemstack, int amount)
    {
        totalAmount += amount;
        onCrafting(itemstack);
    }

    @Override
    protected void onCrafting(ItemStack itemstack)
    {
        itemstack.onCrafting(thePlayer.worldObj, thePlayer, totalAmount);

        if (!thePlayer.worldObj.isRemote)
        {
            int amount = totalAmount;
            float xp = FurnaceRecipes.smelting().func_151398_b(itemstack);
            int i;

            for (int j = 0; j < 2; ++j)
            {
                if (xp > 0 && xp < 1.0F)
                {
                    i = MathHelper.floor_float(totalAmount * xp);

                    if (i < MathHelper.ceiling_float_int(totalAmount * xp) && (float) Math.random() < totalAmount * xp - i)
                    {
                        ++i;
                    }

                    amount += i;
                }

                if (xp > 0)
                {
                    break;
                }

                xp = AlloyRecipes.getInstance().getXpYield(itemstack);
            }

            while (amount > 0)
            {
                i = EntityXPOrb.getXPSplit(amount);
                amount -= i;
                thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(thePlayer.worldObj, thePlayer.posX, thePlayer.posY + 0.5D, thePlayer.posZ + 0.5D, i));
            }
        }

        totalAmount = 0;
        FMLCommonHandler.instance().firePlayerSmeltedEvent(thePlayer, itemstack);

        if (itemstack.getItem() == Items.iron_ingot)
        {
            thePlayer.addStat(AchievementList.acquireIron, 1);
        }

        if (itemstack.getItem() == Items.cooked_fished)
        {
            thePlayer.addStat(AchievementList.cookFish, 1);
        }
    }
}
