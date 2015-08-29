package fiskfille.tf.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;

public class BlockCosmicRust extends Block
{
    private IIcon[] icons;
    
    protected BlockCosmicRust()
    {
        super(Material.circuits);
        setTickRandomly(true);
    }

    public float getBlockHardness(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 2 ? 2.0F : this.blockHardness;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int meta)
    {
        return meta;
    }

    /**
     * Metadata and fortune sensitive version, this replaces the old (int meta, Random rand)
     * version in 1.1.
     *
     * @param meta Blocks Metadata
     * @param fortune Current item fortune level
     * @param random Random number generator
     * @return The number of items to drop
     */
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return meta == 2 ? 1 : 0;
    }

    public int tickRate(World world)
    {
        float f = 1F;
        return (int)(1000000000F * f);
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

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List subBlocks)
    {
        subBlocks.add(new ItemStack(item, 1, 2));
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
        return metadata == 0 ? icons[0] : icons[2];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        icons = new IIcon[3];
        
        for (int i = 0; i < icons.length; ++i)
        {
            icons[i] = par1IIconRegister.registerIcon(TransformersMod.modid + ":cosmic_rust_stage_" + i);
        }
    }
}