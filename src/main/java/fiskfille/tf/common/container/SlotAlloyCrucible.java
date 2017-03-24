package fiskfille.tf.common.container;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.tf.common.recipe.AlloyRecipes;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;

public class SlotAlloyCrucible extends Slot
{
    private EntityPlayer thePlayer;
    private TileEntityAlloyCrucible tileentity;
    private int amountTaken;

    public SlotAlloyCrucible(EntityPlayer player, TileEntityAlloyCrucible tile, int id, int x, int y)
    {
        super(tile, id, x, y);
        thePlayer = player;
        tileentity = tile;
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
            amountTaken += Math.min(amount, getStack().stackSize);
            tileentity.alloyResults += amountTaken;
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
        amountTaken += amount;
        onCrafting(itemstack);
    }

    @Override
    protected void onCrafting(ItemStack itemstack)
    {
        itemstack.onCrafting(thePlayer.worldObj, thePlayer, amountTaken);

        if (!thePlayer.worldObj.isRemote)
        {
            int amount = amountTaken;
            float xp = (AlloyRecipes.getInstance().getXpYield(itemstack) * tileentity.alloyResults + FurnaceRecipes.smelting().func_151398_b(itemstack) * tileentity.furnaceResults) / amountTaken;
            int i;

            if (xp == 0.0F)
            {
                amount = 0;
            }
            else if (xp < 1.0F)
            {
                i = MathHelper.floor_float(amount * xp);

                if (i < MathHelper.ceiling_float_int(amount * xp) && (float) Math.random() < amount * xp - i)
                {
                    ++i;
                }

                amount = i;
            }

            while (amount > 0)
            {
                i = EntityXPOrb.getXPSplit(amount);
                amount -= i;
                thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(thePlayer.worldObj, thePlayer.posX, thePlayer.posY + 0.5D, thePlayer.posZ + 0.5D, i));
            }
        }
        
        int i = amountTaken;
        int j = Math.min(tileentity.alloyResults, i);
        
        i -= j;
        tileentity.alloyResults -= j;
        tileentity.furnaceResults -= Math.min(tileentity.furnaceResults, i);
        amountTaken = 0;
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
