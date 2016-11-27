package fiskfille.tf.common.item;

import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.ItemFluidContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.TFFluids;

public class ItemFuelCanister extends ItemFluidContainer
{
    public static final String[] unlocalizedNames = new String[] {"", "creative_"};
    public IIcon[] icons;
    
    @SideOnly(Side.CLIENT)
    private IIcon[] overlays;

    public ItemFuelCanister()
    {
        super(0, 2000);
        setHasSubtypes(true);
        setMaxDamage(0);
    }
    
    public static boolean isEmpty(ItemStack itemstack)
    {
    	return getFluidAmount(itemstack) <= 0;
    }
    
    public static boolean isFull(ItemStack itemstack)
    {
    	return getFluidAmount(itemstack) >= getContainerCapacity(itemstack);
    }
    
    public static int getFluidAmount(ItemStack itemstack)
    {
    	if (itemstack.stackTagCompound == null || !itemstack.stackTagCompound.hasKey("Fluid"))
        {
            return 0;
        }
    	
        return itemstack.stackTagCompound.getCompoundTag("Fluid").getInteger("Amount");
    }
    
    public static FluidStack getContainerFluid(ItemStack itemstack)
    {
    	if (itemstack.stackTagCompound == null || !itemstack.stackTagCompound.hasKey("Fluid"))
        {
            return null;
        }
    	
        return FluidStack.loadFluidStackFromNBT(itemstack.stackTagCompound.getCompoundTag("Fluid"));
    }
    
    public static int getContainerCapacity(ItemStack itemstack)
    {
    	if (itemstack.getItem() instanceof IFluidContainerItem)
    	{
    		return ((IFluidContainerItem)itemstack.getItem()).getCapacity(itemstack);
    	}
    	
    	return 0;
    }
    
    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill)
    {
    	if (resource == null || resource.getFluid() != TFFluids.energon)
    	{
    		return 0;
    	}
    	
    	return super.fill(container, resource, doFill);
    }
    
    @Override
    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain)
    {
        return super.drain(container, maxDrain, container.getItemDamage() == 0 && doDrain);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_)
    {
    	FluidStack stack = getFluid(itemstack);
    	int liquidAmount = stack != null ? stack.amount : 0;
    	
    	if (stack != null && stack.amount > 0)
    	{
    		Map<String, Float> ratios = FluidEnergon.getRatios(stack);
    		boolean flag = false;

			for (Map.Entry<String, Float> e : ratios.entrySet())
			{
				Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
				int percent = Math.round(e.getValue() * 100);
				
				if (percent > 0)
				{
					list.add(StatCollector.translateToLocalFormatted("gui.energon_processor.content", energon.getTranslatedName(), percent));
					flag = true;
				}
			}
        	
    		if (flag)
    		{
                list.add("");
    		}
    		else
    		{
    			list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("gui.energon_processor.unidentified"));
    		}
    		
    		list.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", liquidAmount, capacity));
        }
        else
        {
        	list.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", liquidAmount, capacity));
        }
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int i = MathHelper.clamp_int(itemstack.getItemDamage(), 0, unlocalizedNames.length - 1);
        return "item." + unlocalizedNames[i] + "fuel_canister";
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < unlocalizedNames.length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
            
            for (Energon energon : TransformersAPI.getEnergonTypes())
            {
                ItemStack itemstack = new ItemStack(item, 1, i);
                fill(itemstack, FluidEnergon.create(energon, capacity), true);
                list.add(itemstack);
            }
        }
    }

    @Override
    public IIcon getIcon(ItemStack itemstack, int pass)
    {
    	if (pass > 0)
    	{
    		FluidStack fluidStack = getFluid(itemstack);
    		
    		if (fluidStack != null && fluidStack.amount > 0)
    		{
    			int i = Math.round((float)fluidStack.amount / capacity * 4);
    			return overlays[i % overlays.length];
    		}
    	}
    	
    	return super.getIcon(itemstack, pass);
    }
    
    @Override
    public IIcon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, unlocalizedNames.length - 1);
        return icons[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int pass)
    {
        if (pass == 1)
        {
        	FluidStack fluidStack = getFluid(itemstack);
        	
        	if (fluidStack != null && fluidStack.amount > 0)
        	{
        		return FluidEnergon.getLiquidColor(fluidStack);
        	}
        }
        
        return super.getColorFromItemStack(itemstack, pass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IIconRegister)
    {
        icons = new IIcon[unlocalizedNames.length];
        overlays = new IIcon[5];

        for (int i = 0; i < icons.length; ++i)
        {
            icons[i] = par1IIconRegister.registerIcon(TransformersMod.modid + ":" + unlocalizedNames[i] + "fuel_canister");
        }
        
        for (int i = 0; i < overlays.length; ++i)
        {
        	overlays[i] = par1IIconRegister.registerIcon(TransformersMod.modid + ":fuel_canister_overlay_" + i);
        }
    }
}
