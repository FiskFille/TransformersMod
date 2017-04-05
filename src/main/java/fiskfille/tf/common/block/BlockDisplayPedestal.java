package fiskfille.tf.common.block;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.displayable.Displayable;
import fiskfille.tf.common.tileentity.TileEntityDisplayPedestal;

public class BlockDisplayPedestal extends BlockMachineBase
{
    public BlockDisplayPedestal()
    {
        super(TFMaterial.display);
        setHardness(2.0F);
        setResistance(5.0F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getPlacedRotation(EntityLivingBase entity)
    {
        return 0;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
        {
            return true;
        }
        
        TileEntityDisplayPedestal tile = (TileEntityDisplayPedestal) world.getTileEntity(x, y, z);

        if (tile != null)
        {
            ItemStack heldItem = player.getHeldItem();
            ItemStack displayItem = tile.getDisplayItem();

            if (heldItem == null && displayItem != null)
            {
                player.setCurrentItemOrArmor(0, displayItem);
                tile.setDisplayItem(null, true);

                return true;
            }
            else if (heldItem != null && TransformersAPI.hasDisplayable(heldItem.getItem()))
            {
                if (displayItem == null)
                {
                    tile.setDisplayItem(heldItem, true);
                    player.setCurrentItemOrArmor(0, null);

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        TileEntityDisplayPedestal tile = (TileEntityDisplayPedestal) world.getTileEntity(x, y, z);

        if (tile != null)
        {
            ItemStack displayItem = tile.getDisplayItem();

            if (displayItem != null)
            {
                Displayable displayable = TransformersAPI.getDisplayableFor(displayItem.getItem());

                if (displayable != null)
                {
                    setBlockBounds(0, 0, 0, 1, displayable.getBlockHeight(displayItem), 1);
                }
            }
            else
            {
                setBlockBounds(0, 0, 0, 1, 0.0625F * 9, 1);
            }
        }
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(TransformersMod.modid + ":" + getUnlocalizedName().substring(5));
    }
}
