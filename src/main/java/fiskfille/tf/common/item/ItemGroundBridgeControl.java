package fiskfille.tf.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import fiskfille.tf.common.block.BlockControlPanel;
import fiskfille.tf.common.block.TFBlocks;

public class ItemGroundBridgeControl extends ItemMachine
{
    public ItemGroundBridgeControl(Block block)
    {
        super(block);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            if (side == 0)
            {
                --y;
            }

            if (side == 1)
            {
                ++y;
            }

            if (side == 2)
            {
                --z;
            }

            if (side == 3)
            {
                ++z;
            }

            if (side == 4)
            {
                --x;
            }

            if (side == 5)
            {
                ++x;
            }

            BlockControlPanel block = (BlockControlPanel) TFBlocks.groundBridgeControlPanel;
            int direction = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            byte x1 = 0;
            byte z1 = 0;

            if (direction == 0)
            {
                x1 = -1;
            }

            if (direction == 1)
            {
                z1 = -1;
            }

            if (direction == 2)
            {
                x1 = 1;
            }

            if (direction == 3)
            {
                z1 = 1;
            }

            if (player.canPlayerEdit(x, y, z, side, itemstack) && player.canPlayerEdit(x + x1, y, z + z1, side, itemstack) && player.canPlayerEdit(x + x1, y + 1, z + z1, side, itemstack))
            {
                if (world.isAirBlock(x, y, z) && world.isAirBlock(x + x1, y, z + z1) && world.isAirBlock(x, y + 1, z) && world.isAirBlock(x + x1, y + 1, z + z1))
                {
                    if (placeBlockAt(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ, direction))
                    {
                        world.setBlock(x + x1, y, z + z1, block, direction + 4, 3);

                        if (world.getBlock(x + x1, y, z + z1) == block)
                        {
                            world.setBlock(x + x1, y + 1, z + z1, block, direction + 8, 3);
                        }

                        world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block.stepSound.func_150496_b(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                        --itemstack.stackSize;
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
