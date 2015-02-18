package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
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
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.helper.TFHelper;

public class ItemPurgesKatana extends ItemSword
{
	public ItemPurgesKatana(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(TransformersMod.tabTransformers);
		this.setMaxDamage(1500);
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time)
    {
		if (!TFDataManager.isInVehicleMode(player) && TFHelper.isPlayerPurge(player))
		{
	        int timeLeft = this.getMaxItemUseDuration(stack) - time;
	        double d = (double)timeLeft / 10;
	        
	        if (d > 2.0D)
	        {
	        	d = 2.0D;
	        }
	        	        
	        stack.damageItem(2, player);
	        Vec3 vec3 = TFMotionManager.getFrontCoords(player, player.onGround ? d : d * 0.75D, true);
	        player.motionX = (vec3.xCoord - player.posX);
	        player.motionY = (vec3.yCoord - player.posY) / 2;
			player.motionZ = (vec3.zCoord - player.posZ);
		}
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        return stack;
    }
    
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
    
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.drink;
    }
    
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	if (TFHelper.isPlayerPurge(player))
    	{
    		if (!TFDataManager.isInVehicleMode(player))
        	{
        		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        	}
    	}
    	
        return stack;
    }
	
    public static List<Entity> getEntitiesNear(World world, double x, double y, double z, float par4)
    {
        List<Entity> entities = world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - par4, y - par4, z - par4, x + par4, y + par4, z + par4), IEntitySelector.selectAnything);
        return entities;
    }
	
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(TransformersMod.modid + ":" + iconString);
	}
	
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.removeAll(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 8.0D, 0));
        return multimap;
    }
}