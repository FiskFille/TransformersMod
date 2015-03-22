package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;

public class BlockTransformiumStone extends BlockBasic
{
    public BlockTransformiumStone()
    {
        super(Material.rock);
        this.setCreativeTab(TransformersMod.tabTransformers);
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        // world.spawnParticle("smoke", x + 0.5F + (rand.nextFloat() - 0.5F), y
        // + 1.0F, z + 0.5F + (rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
    }
}