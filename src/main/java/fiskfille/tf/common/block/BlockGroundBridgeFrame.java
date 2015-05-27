package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockGroundBridgeFrame extends Block
{
    public BlockGroundBridgeFrame()
    {
        super(Material.circuits);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TFBlocks.groundBridgeTeleporter.spawnTeleporter(world, x, y, z);
        
        return true;
    }
}
