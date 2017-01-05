package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TFLog;
import fiskfille.tf.TransformersMod;

public abstract class BlockTF extends Block
{
    protected final Class<? extends TileEntity> tileClass;
    protected final Class<? extends ItemBlock> itemClass;
    protected final String name;

    protected BlockTF(String name, Class<? extends TileEntity> tileClass)
    {
        this(name, tileClass, ItemBlock.class);
    }

    protected BlockTF(String name, Class<? extends TileEntity> tileClass, Class<? extends ItemBlock> itemClass)
    {
        this(new Material(MapColor.ironColor), name, tileClass, itemClass);
    }

    protected BlockTF(Material material, String name, Class<? extends TileEntity> tileClass)
    {
        this(material, name, tileClass, ItemBlock.class);
    }

    protected BlockTF(Material material, String name, Class<? extends TileEntity> tileClass, Class<? extends ItemBlock> itemClass)
    {
        super(material);
        this.name = name;
        this.tileClass = tileClass;
        this.itemClass = itemClass;

        init();
    }

    protected void init()
    {
        String unlocalizedName = name.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");

        if (tileClass != null)
        {
            GameRegistry.registerTileEntity(tileClass, name);
        }

        setBlockName(unlocalizedName);
        setBlockTextureName(TransformersMod.modid + ":" + unlocalizedName);
        GameRegistry.registerBlock(this, itemClass, unlocalizedName);

        setHardness(0.5F);
        setStepSound(Block.soundTypeMetal);
        setCreativeTab(TransformersMod.tabTransformers);
    }

    public BlockTF setHarvestLvl(String tool, int level)
    {
        setHarvestLevel(tool, level);

        return this;
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return tileClass != null;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        if (tileClass != null)
        {
            try
            {
                return tileClass.newInstance();
            }
            catch (Exception e)
            {
                TFLog.error("Could not create tile entity for block '%s' from class %s", name, tileClass);
            }
        }

        return null;
    }
}
