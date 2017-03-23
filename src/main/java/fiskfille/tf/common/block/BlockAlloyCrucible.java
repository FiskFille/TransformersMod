package fiskfille.tf.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.client.render.block.RenderBlockAlloyCrucible;
import fiskfille.tf.helper.TFRenderHelper;

public class BlockAlloyCrucible extends BlockMachineBase
{
    private IIcon bottomIcon;
    private IIcon[] topIcons;
    private IIcon[] frontIcons;
    
    public static final int FLAG_TOP = 4;
    public static final int FLAG_FRONT = 8;
    
    public static int renderPass;

    public BlockAlloyCrucible()
    {
        super(Material.iron);
        setHarvestLevel("pickaxe", 1);
        setHardness(6.0F);
        setResistance(10.0F);
    }
    
    public static int getRotation(int metadata)
    {
        return metadata & 3;
    }
    
    public static boolean getFlag(int metadata, int flag)
    {
        return (metadata & flag) == flag;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
        {
            return true;
        }
        
        if (!player.isSneaking())
        {
            TFGui.ALLOY_CRUCIBLE.open(player, x, y, z);
            return true;
        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        IIcon topIcon = topIcons[1];
        IIcon frontIcon = frontIcons[1];
        
        if (renderPass == 1)
        {
            topIcon = topIcons[getFlag(metadata, FLAG_TOP) ? 2 : 0];
            frontIcon = frontIcons[getFlag(metadata, FLAG_FRONT) ? 2 : 0];
        }
        else
        {
            topIcon = topIcons[getFlag(metadata, FLAG_TOP) ? 0 : 1];
            frontIcon = frontIcons[getFlag(metadata, FLAG_FRONT) ? 0 : 1];
        }
        
        return side == 1 ? topIcon : side == 0 ? bottomIcon : side != new int[] {3, 4, 2, 5}[getRotation(metadata)] ? blockIcon : frontIcon;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        if (renderPass == 1)
        {
            ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
            int metadata = world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
            boolean flag = side == 1 && getFlag(metadata, FLAG_TOP);
                        
            if (getFlag(metadata, FLAG_FRONT))
            {
                flag |= side == new int[] {3, 4, 2, 5}[getRotation(metadata)];
            }
            
            return flag;
        }
        
        return super.shouldSideBeRendered(world, x, y, z, side);
    }
    
    @Override
    public int getMixedBrightnessForBlock(IBlockAccess world, int x, int y, int z)
    {
        if (renderPass == 1)
        {
            return TFRenderHelper.LIGHTING_LUMINOUS;
        }
        
        return super.getMixedBrightnessForBlock(world, x, y, z);
    }
    
    @Override
    public int getRenderType()
    {
        return RenderBlockAlloyCrucible.renderId;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        frontIcons = new IIcon[3];
        topIcons = new IIcon[3];
        
        for (int i = 0; i < 3; ++i)
        {
            String[] astring = {"", "_off", "_on"};
            topIcons[i] = iconRegister.registerIcon(getTextureName() + "_top" + astring[i]);
            frontIcons[i] = iconRegister.registerIcon(getTextureName() + "_front" + astring[i]);
        }

        bottomIcon = iconRegister.registerIcon(getTextureName() + "_bottom");
        blockIcon = iconRegister.registerIcon(getTextureName() + "_side");
    }
}
