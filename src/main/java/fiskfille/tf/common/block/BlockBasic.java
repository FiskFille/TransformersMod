package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import fiskfille.tf.TransformersMod;

public class BlockBasic extends Block
{
    public BlockBasic(Material material)
    {
        super(material);
    }
    
    public BlockBasic setHarvestLvl(String tool, int level)
    {
        this.setHarvestLevel(tool, level);
        
        return this;
    }
}