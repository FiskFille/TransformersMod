package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
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
    
    public int quantityDropped(Random random)
    {
        return 0;
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