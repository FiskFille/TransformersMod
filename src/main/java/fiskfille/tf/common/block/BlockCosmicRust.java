package fiskfille.tf.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;

public class BlockCosmicRust extends Block
{
    private IIcon coreIcon;

    protected BlockCosmicRust()
    {
        super(Material.circuits);
        setTickRandomly(true);
    }

    public float getBlockHardness(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 1 ? 2.0F : blockHardness;
    }

    public int damageDropped(int meta)
    {
        return meta;
    }

    public int quantityDropped(int meta, int fortune, Random random)
    {
        return meta == 1 ? 1 : 0;
    }

    public int tickRate(World world)
    {
        float f = 1F;
        return (int) (1000000000F * f);
    }

    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        tryRust(world, x + 1, y, z);
        tryRust(world, x - 1, y, z);
        tryRust(world, x, y - 1, z);
        tryRust(world, x, y + 1, z);
        tryRust(world, x, y, z - 1);
        tryRust(world, x, y, z + 1);

        if (world.getBlockMetadata(x, y, z) == 0)
        {
            world.setBlockToAir(x, y, z);
        }
    }

    private void tryRust(World world, int x, int y, int z)
    {
        Random rand = new Random();

        if (world.getBlock(x, y, z) == TFBlocks.transformiumStone)
        {
            world.setBlock(x, y, z, this);
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List subBlocks)
    {
        subBlocks.add(new ItemStack(item, 1, 1));
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        onBlockAdded(world, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return metadata == 1 ? coreIcon : blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
    	blockIcon = par1IIconRegister.registerIcon(TransformersMod.modid + ":cosmic_rust");
    	coreIcon = par1IIconRegister.registerIcon(TransformersMod.modid + ":cosmic_rust_core");
    }
}
