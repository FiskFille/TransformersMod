package fiskfille.tf.common.achievement;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

public class TFAchievement extends Achievement
{
    private ItemStack displayItem;
    
    public TFAchievement(String name, int x, int y, Achievement parent)
    {
        super("achievement.tf." + name, "tf." + name, x, y, Items.fish, parent);
    }
    
    public void setDisplayItem(ItemStack itemstack)
    {
        displayItem = itemstack;
    }
    
    public ItemStack getDisplayItem()
    {
        return displayItem;
    }
}
