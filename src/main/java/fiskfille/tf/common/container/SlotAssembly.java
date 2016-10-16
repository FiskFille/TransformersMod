package fiskfille.tf.common.container;

import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.tf.common.recipe.AssemblyTableCraftingManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import java.util.Map;

public class SlotAssembly extends Slot
{
    private final InventoryAssembly craftMatrix;
    private EntityPlayer thePlayer;
    private int amountCrafted;

    public SlotAssembly(EntityPlayer player, InventoryAssembly inventory1, IInventory inventory2, int id, int x, int y)
    {
        super(inventory2, id, x, y);
        this.thePlayer = player;
        this.craftMatrix = inventory1;
    }

    @Override
    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(amount, this.getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }

    @Override
    protected void onCrafting(ItemStack itemstack, int amount)
    {
        this.amountCrafted += amount;
        this.onCrafting(itemstack);
    }

    @Override
    protected void onCrafting(ItemStack itemstack)
    {
        itemstack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
        this.amountCrafted = 0;

        if (itemstack.getItem() == Item.getItemFromBlock(Blocks.crafting_table))
        {
            this.thePlayer.addStat(AchievementList.buildWorkBench, 1);
        }

        if (itemstack.getItem() instanceof ItemPickaxe)
        {
            this.thePlayer.addStat(AchievementList.buildPickaxe, 1);
        }

        if (itemstack.getItem() == Item.getItemFromBlock(Blocks.furnace))
        {
            this.thePlayer.addStat(AchievementList.buildFurnace, 1);
        }

        if (itemstack.getItem() instanceof ItemHoe)
        {
            this.thePlayer.addStat(AchievementList.buildHoe, 1);
        }

        if (itemstack.getItem() == Items.bread)
        {
            this.thePlayer.addStat(AchievementList.makeBread, 1);
        }

        if (itemstack.getItem() == Items.cake)
        {
            this.thePlayer.addStat(AchievementList.bakeCake, 1);
        }

        if (itemstack.getItem() instanceof ItemPickaxe && ((ItemPickaxe) itemstack.getItem()).func_150913_i() != Item.ToolMaterial.WOOD)
        {
            this.thePlayer.addStat(AchievementList.buildBetterPickaxe, 1);
        }

        if (itemstack.getItem() instanceof ItemSword)
        {
            this.thePlayer.addStat(AchievementList.buildSword, 1);
        }

        if (itemstack.getItem() == Item.getItemFromBlock(Blocks.enchanting_table))
        {
            this.thePlayer.addStat(AchievementList.enchantments, 1);
        }

        if (itemstack.getItem() == Item.getItemFromBlock(Blocks.bookshelf))
        {
            this.thePlayer.addStat(AchievementList.bookcase, 1);
        }
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack itemstack)
    {
        FMLCommonHandler.instance().firePlayerCraftingEvent(player, itemstack, craftMatrix);
        this.onCrafting(itemstack);

        for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);

            if (itemstack1 != null)
            {
                Map<Integer, Integer> map = AssemblyTableCraftingManager.getInstance().getDecrMap(itemstack);
                this.craftMatrix.decrStackSize(i, map.get(i) == null ? 0 : map.get(i));

                if (itemstack1.getItem().hasContainerItem(itemstack1))
                {
                    ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);

                    if (itemstack2 != null && itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                    {
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(thePlayer, itemstack2));
                        continue;
                    }

                    if (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !this.thePlayer.inventory.addItemStackToInventory(itemstack2))
                    {
                        if (this.craftMatrix.getStackInSlot(i) == null)
                        {
                            this.craftMatrix.setInventorySlotContents(i, itemstack2);
                        }
                        else
                        {
                            this.thePlayer.dropPlayerItemWithRandomChoice(itemstack2, false);
                        }
                    }
                }
            }
        }
    }
}
