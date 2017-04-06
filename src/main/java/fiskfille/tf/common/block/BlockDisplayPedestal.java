package fiskfille.tf.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.Lists;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.client.render.block.RenderBlockDisplayPedestal;
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
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < getTextures().size(); ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }
    
    @Override
    public String getHarvestTool(int metadata)
    {
        BlockIcon icon = getTexture(metadata)[0];
        return icon.block.getHarvestTool(icon.metadata);
    }
    
    @Override
    public boolean isToolEffective(String type, int metadata)
    {
        BlockIcon icon = getTexture(metadata)[0];
        return icon.block.isToolEffective(type, icon.metadata);
    }
    
    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        BlockIcon[] icons = getTexture(world.getBlockMetadata(x, y, z));
        int fire = 0;
        
        for (int i = 0; i < icons.length; ++i)
        {
            fire += Blocks.fire.getFlammability(icons[i].block);
        }
        
        return fire / icons.length;
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        BlockIcon[] icons = getTexture(world.getBlockMetadata(x, y, z));
        int fire = 0;
        
        for (int i = 0; i < icons.length; ++i)
        {
            fire += Blocks.fire.getEncouragement(icons[i].block);
        }
        
        return fire / icons.length;
    }

    @Override
    public int getPlacedRotation(EntityLivingBase entity)
    {
        return 0;
    }
    
    @Override
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        for (AxisAlignedBB aabb1 : getBounds())
        {
            addBox(aabb1.minX, aabb1.minY, aabb1.minZ, aabb1.maxX, aabb1.maxY, aabb1.maxZ, x, y, z, aabb, list);
        }
    }
    
    public static List<BlockIcon[]> getTextures()
    {
        List<BlockIcon[]> list = Lists.newArrayList();
        list.add(new BlockIcon[] {new BlockIcon(Blocks.cobblestone, 0, 0), new BlockIcon(Blocks.stonebrick, 0, 0), new BlockIcon(Blocks.stone, 0, 0)});
        list.add(new BlockIcon[] {new BlockIcon(Blocks.stonebrick, 0, 3), new BlockIcon(Blocks.stonebrick, 0, 1), new BlockIcon(Blocks.stonebrick, 0, 2)});
        list.add(new BlockIcon[] {new BlockIcon(Blocks.sandstone, 2, 0), new BlockIcon(Blocks.sandstone, 2, 1), new BlockIcon(Blocks.sandstone, 1, 0)});
        list.add(new BlockIcon[] {new BlockIcon(Blocks.quartz_block, 1, 1), new BlockIcon(Blocks.quartz_block, 2, 2), new BlockIcon(Blocks.quartz_block, 1, 2)});
        list.add(new BlockIcon[] {new BlockIcon(Blocks.nether_brick, 0, 0), new BlockIcon(Blocks.netherrack, 0, 0), new BlockIcon(Blocks.nether_brick, 0, 0)});
        
        for (int i = 0; i < 6; ++i)
        {
            Block log = i < 4 ? Blocks.log : Blocks.log2;
            list.add(new BlockIcon[] {new BlockIcon(Blocks.planks, 0, i), new BlockIcon(log, 2, i % 4), new BlockIcon(Blocks.planks, 0, i)});
        }
        
        return list;
    }

    public static BlockIcon[] getTexture(int metadata)
    {
        return getTextures().get(MathHelper.clamp_int(metadata, 0, getTextures().size() - 1));
    }

    public static AxisAlignedBB[] getBounds()
    {
        float f = 0.0625F;
        return new AxisAlignedBB[] {AxisAlignedBB.getBoundingBox(f * 2, 0, f * 2, f * 14, f * 2, f * 14), AxisAlignedBB.getBoundingBox(f * 5, f * 2, f * 5, 1 - f * 5, f * 7, 1 - f * 5), AxisAlignedBB.getBoundingBox(f * 4, f * 7, f * 4, 1 - f * 4, f * 9, 1 - f * 4)};
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
        AxisAlignedBB aabb = null;
        
        for (AxisAlignedBB aabb1 : getBounds())
        {
            if (aabb == null)
            {
                aabb = aabb1;
            }
            else
            {
                aabb = aabb.func_111270_a(aabb1);
            }
        }
        
        setBlockBounds((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderBlockDisplayPedestal.renderId;
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        BlockIcon icon = getTexture(metadata)[side % getTexture(metadata).length];
        return icon.block.getIcon(icon.side, icon.metadata);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    }

    public static class BlockIcon
    {
        public final Block block;
        public final int side;
        public final int metadata;

        public BlockIcon(Block block, int side, int metadata)
        {
            this.block = block;
            this.side = side;
            this.metadata = metadata;
        }
    }
}
