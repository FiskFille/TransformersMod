package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFVectorHelper;

public class ItemPurgesKatana extends ItemSword
{
    public ItemPurgesKatana()
    {
        super(ToolMaterial.EMERALD);
        setMaxDamage(1500);
        setCreativeTab(TransformersMod.tabTransformers);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time)
    {
        if (!TFHelper.isFullyTransformed(player) && TFHelper.getTransformer(player) instanceof TransformerPurge)
        {
            int timeLeft = getMaxItemUseDuration(stack) - time;
            double force = (double) timeLeft / 10;

            if (force > 2.0D)
            {
                force = 2.0D;
            }

            stack.damageItem(1, player);
            Vec3 vec3 = TFVectorHelper.getFrontCoords(player, player.onGround ? force : force * 0.75D, true);
            player.motionX += vec3.xCoord - player.posX;
            player.motionY += vec3.yCoord - player.boundingBox.minY;
            player.motionZ += vec3.zCoord - player.posZ;
            player.fallDistance = 0;
            player.swingItem();
        }
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.drink;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (TFHelper.getTransformer(player) instanceof TransformerPurge)
        {
            if (!TFHelper.isFullyTransformed(player))
            {
                player.setItemInUse(stack, getMaxItemUseDuration(stack));
            }
        }

        return stack;
    }

    public static List<Entity> getEntitiesNear(World world, double x, double y, double z, float range)
    {
        return world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range), IEntitySelector.selectAnything);
    }

    @Override
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.removeAll(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 8.0D, 0));
        return multimap;
    }
}
