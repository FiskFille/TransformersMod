package fiskfille.tf.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;

public class BlockAlloyCrucible extends BlockMachineBase
{
    private IIcon bottomIcon;
    private IIcon[] topIcons;
    private IIcon[] frontIcons;

    protected BlockAlloyCrucible()
    {
        super(Material.iron);
        setHarvestLevel("pickaxe", 1);
        setHardness(6.0F);
        setResistance(10.0F);
    }

    @Override
    public int getPlacedRotation(EntityLivingBase entity)
    {
        return super.getPlacedRotation(entity);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ)
    {
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
        int pass = side >= 100 ? 1 : 0;

        if (pass == 1)
        {
            side -= 100;
        }

        return side == 1 ? topIcons[pass] : side == 0 ? bottomIcon : side != new int[] {3, 4, 2, 5}[metadata % 4] ? blockIcon : frontIcons[pass];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        frontIcons = new IIcon[2];
        topIcons = new IIcon[2];

        frontIcons[0] = iconRegister.registerIcon(getTextureName() + "_front");
        frontIcons[1] = iconRegister.registerIcon(getTextureName() + "_front_overlay");
        topIcons[0] = iconRegister.registerIcon(getTextureName() + "_top");
        topIcons[1] = iconRegister.registerIcon(getTextureName() + "_top_overlay");
        bottomIcon = iconRegister.registerIcon(getTextureName() + "_bottom");
        blockIcon = iconRegister.registerIcon(getTextureName() + "_side");
    }
}
