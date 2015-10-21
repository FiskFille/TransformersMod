package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import fiskfille.tf.TransformersMod;

public class BlockAssemblyTable extends BlockBasic
{
    public BlockAssemblyTable()
    {
        super(Material.glass);
        setHarvestLvl("pickaxe", 1);
        setStepSound(Block.soundTypeMetal);
        setHardness(6.0F);
        setResistance(10.0F);
        setLightLevel(0.75F);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ)
    {
        if (!player.isSneaking())
        {
            player.openGui(TransformersMod.instance, 2, world, x, y, z);
            return true;
        }
        else
        {
            return false;
        }
    }
}
