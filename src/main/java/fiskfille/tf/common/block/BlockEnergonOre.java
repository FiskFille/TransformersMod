package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import fiskfille.tf.client.render.block.RenderBlockEnergonOre;
import fiskfille.tf.common.item.TFItems;

public class BlockEnergonOre extends Block
{
    private IIcon[] icons;
    private Random rand = new Random();
    
    public static int renderPass;

    public BlockEnergonOre()
    {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
    }
    
    @Override
    public int getMixedBrightnessForBlock(IBlockAccess world, int x, int y, int z)
    {
        if (renderPass > 1)
        {
            return 0xF000F0;
        }
        
        return super.getMixedBrightnessForBlock(world, x, y, z);
    }
    
    @Override
    public int getRenderType()
    {
        return RenderBlockEnergonOre.renderId;
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return TFItems.energonDust;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return quantityDropped(random) + random.nextInt(fortune + 1);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4 + random.nextInt(2);
    }

    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
        if (getItemDropped(metadata, rand, fortune) != Item.getItemFromBlock(this))
        {
            return 1 + rand.nextInt(5);
        }

        return 0;
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return icons[MathHelper.clamp_int(renderPass, 0, icons.length - 1)];
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[3];
        icons[0] = iconRegister.registerIcon(getTextureName());
        icons[1] = iconRegister.registerIcon(getTextureName() + "_background");
        icons[2] = iconRegister.registerIcon(getTextureName() + "_overlay");
    }
}
