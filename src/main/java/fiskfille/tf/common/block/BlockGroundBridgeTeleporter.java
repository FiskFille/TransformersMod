package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGroundBridgeTeleporter extends BlockBreakable
{
    public static final int[][] field_150001_a = new int[][] {new int[0], {3, 1}, {2, 0}};
    
    public BlockGroundBridgeTeleporter()
    {
        super("ground_bridge_teleporter", Material.portal, false);
        this.setTickRandomly(true);
    }
    
//    /**
//     * Ticks the block if it's been scheduled
//     */
//    public void updateTick(World world, int x, int y, int z, Random rand)
//    {
//        super.updateTick(world, x, y, z, rand);
//        
//        if (world.provider.isSurfaceWorld() && world.getGameRules().getGameRuleBooleanValue("doMobSpawning") && rand.nextInt(2000) < world.difficultySetting.getDifficultyId())
//        {
//            int l;
//            
//            for (l = y; !World.doesBlockHaveSolidTopSurface(world, x, l, z) && l > 0; --l)
//            {
//                ;
//            }
//            
//            if (l > 0 && !world.getBlock(x, l + 1, z).isNormalCube())
//            {
//                Entity entity = ItemMonsterPlacer.spawnCreature(world, 57, (double)x + 0.5D, (double)l + 1.1D, (double)z + 0.5D);
//                
//                if (entity != null)
//                {
//                    entity.timeUntilPortal = entity.getPortalCooldown();
//                }
//            }
//        }
//    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
    
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int l = func_149999_b(world.getBlockMetadata(x, y, z));
        
        if (l == 0)
        {
            if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this)
            {
                l = 2;
            }
            else
            {
                l = 1;
            }
            
            if (world instanceof World && !((World)world).isRemote)
            {
                ((World)world).setBlockMetadataWithNotify(x, y, z, l, 2);
            }
        }
        
        float f = 0.125F;
        float f1 = 0.125F;
        
        if (l == 1)
        {
            f = 0.5F;
        }
        
        if (l == 2)
        {
            f1 = 0.5F;
        }
        
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
    }
    
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public boolean spawnTeleporter(World world, int x, int y, int z)
    {
        BlockGroundBridgeTeleporter.Size size = new BlockGroundBridgeTeleporter.Size(world, x, y, z, 1);
        BlockGroundBridgeTeleporter.Size size1 = new BlockGroundBridgeTeleporter.Size(world, x, y, z, 2);
        
        if (size.func_150860_b() && size.field_150864_e == 0)
        {
            size.func_150859_c();
            return true;
        }
        else if (size1.func_150860_b() && size1.field_150864_e == 0)
        {
            size1.func_150859_c();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        int l = func_149999_b(world.getBlockMetadata(x, y, z));
        BlockGroundBridgeTeleporter.Size size = new BlockGroundBridgeTeleporter.Size(world, x, y, z, 1);
        BlockGroundBridgeTeleporter.Size size1 = new BlockGroundBridgeTeleporter.Size(world, x, y, z, 2);
        
        if (l == 1 && (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g))
        {
            world.setBlock(x, y, z, Blocks.air);
        }
        else if (l == 2 && (!size1.func_150860_b() || size1.field_150864_e < size1.field_150868_h * size1.field_150862_g))
        {
            world.setBlock(x, y, z, Blocks.air);
        }
        else if (l == 0 && !size.func_150860_b() && !size1.func_150860_b())
        {
            world.setBlock(x, y, z, Blocks.air);
        }
    }
    
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        int i1 = 0;
        
        if (world.getBlock(x, y, z) == this)
        {
            i1 = func_149999_b(world.getBlockMetadata(x, y, z));
            
            if (i1 == 0)
            {
                return false;
            }
            
            if (i1 == 2 && side != 5 && side != 4)
            {
                return false;
            }
            
            if (i1 == 1 && side != 3 && side != 2)
            {
                return false;
            }
        }
        
        boolean flag = world.getBlock(x - 1, y, z) == this && world.getBlock(x - 2, y, z) != this;
        boolean flag1 = world.getBlock(x + 1, y, z) == this && world.getBlock(x + 2, y, z) != this;
        boolean flag2 = world.getBlock(x, y, z - 1) == this && world.getBlock(x, y, z - 2) != this;
        boolean flag3 = world.getBlock(x, y, z + 1) == this && world.getBlock(x, y, z + 2) != this;
        boolean flag4 = flag || flag1 || i1 == 1;
        boolean flag5 = flag2 || flag3 || i1 == 2;
        return flag4 && side == 4 ? true : (flag4 && side == 5 ? true : (flag5 && side == 2 ? true : flag5 && side == 3));
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random rand)
    {
        return 0;
    }
    
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        world.getChunkProvider().provideChunk(x >> 4, z >> 4);
        
        int heightValue = world.getHeightValue(0, 0);
        
        if(world.blockExists(0, heightValue + 1, 0) && world.isAirBlock(0, heightValue + 1, 0))
        {
            entity.setPositionAndRotation(0, heightValue + 1, 0, func_149999_b(world.getBlockMetadata(x, y, z)) * 90 - 90, 0);
        }
    }
    
    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        for (int l = 0; l < 4; ++l)
        {
            double particleX = (double)((float)x + rand.nextFloat());
            double particleY = (double)((float)y + rand.nextFloat());
            double particleZ = (double)((float)z + rand.nextFloat());
            int i1 = rand.nextInt(2) * 2 - 1;
            double motionX = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double motionY = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double motionZ = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            
            if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this)
            {
                particleX = (double)x + 0.5D + 0.25D * (double)i1;
                motionX = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                particleZ = (double)z + 0.5D + 0.25D * (double)i1;
                motionZ = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }
            
            world.spawnParticle("smoke", particleX, particleY, particleZ, motionX, motionY, motionZ);
        }
    }
    
    public static int func_149999_b(int meta)
    {
        return meta & 3;
    }
    
    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemById(0);
    }
    
    public static class Size
    {
        private final World worldObj;
        private final int field_150865_b;
        private final int field_150866_c;
        private final int field_150863_d;
        private int field_150864_e = 0;
        private ChunkCoordinates position;
        private int field_150862_g;
        private int field_150868_h;
        
        public Size(World world, int p_i45415_2_, int p_i45415_3_, int p_i45415_4_, int p_i45415_5_)
        {
            this.worldObj = world;
            this.field_150865_b = p_i45415_5_;
            this.field_150863_d = BlockGroundBridgeTeleporter.field_150001_a[p_i45415_5_][0];
            this.field_150866_c = BlockGroundBridgeTeleporter.field_150001_a[p_i45415_5_][1];
            
            for (int i1 = p_i45415_3_; p_i45415_3_ > i1 - 21 && p_i45415_3_ > 0 && this.func_150857_a(world.getBlock(p_i45415_2_, p_i45415_3_ - 1, p_i45415_4_)); --p_i45415_3_)
            {
                ;
            }
            
            int j1 = this.func_150853_a(p_i45415_2_, p_i45415_3_, p_i45415_4_, this.field_150863_d) - 1;
            
            if (j1 >= 0)
            {
                this.position = new ChunkCoordinates(p_i45415_2_ + j1 * Direction.offsetX[this.field_150863_d], p_i45415_3_, p_i45415_4_ + j1 * Direction.offsetZ[this.field_150863_d]);
                this.field_150868_h = this.func_150853_a(this.position.posX, this.position.posY, this.position.posZ, this.field_150866_c);
                
                if (this.field_150868_h < 2 || this.field_150868_h > 21)
                {
                    this.position = null;
                    this.field_150868_h = 0;
                }
            }
            
            if (this.position != null)
            {
                this.field_150862_g = this.func_150858_a();
            }
        }
        
        protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_)
        {
            int j1 = Direction.offsetX[p_150853_4_];
            int k1 = Direction.offsetZ[p_150853_4_];
            int i1;
            Block block;
            
            for (i1 = 0; i1 < 22; ++i1)
            {
                block = this.worldObj.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
                
                if (!this.func_150857_a(block))
                {
                    break;
                }
                
                Block block1 = this.worldObj.getBlock(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);
                
                if (block1 != TFBlocks.groundBridgeFrame)
                {
                    break;
                }
            }
            
            block = this.worldObj.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
            return block == TFBlocks.groundBridgeFrame ? i1 : 0;
        }
        
        protected int func_150858_a()
        {
            int i;
            int j;
            int k;
            int l;
            label56:
                
                for (this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g)
                {
                    i = this.position.posY + this.field_150862_g;
                    
                    for (j = 0; j < this.field_150868_h; ++j)
                    {
                        k = this.position.posX + j * Direction.offsetX[BlockGroundBridgeTeleporter.field_150001_a[this.field_150865_b][1]];
                        l = this.position.posZ + j * Direction.offsetZ[BlockGroundBridgeTeleporter.field_150001_a[this.field_150865_b][1]];
                        Block block = this.worldObj.getBlock(k, i, l);
                        
                        if (!this.func_150857_a(block))
                        {
                            break label56;
                        }
                        
                        if (block == TFBlocks.groundBridgeTeleporter)
                        {
                            ++this.field_150864_e;
                        }
                        
                        if (j == 0)
                        {
                            block = this.worldObj.getBlock(k + Direction.offsetX[BlockGroundBridgeTeleporter.field_150001_a[this.field_150865_b][0]], i, l + Direction.offsetZ[BlockGroundBridgeTeleporter.field_150001_a[this.field_150865_b][0]]);
                            
                            if (block != TFBlocks.groundBridgeFrame)
                            {
                                break label56;
                            }
                        }
                        else if (j == this.field_150868_h - 1)
                        {
                            block = this.worldObj.getBlock(k + Direction.offsetX[BlockGroundBridgeTeleporter.field_150001_a[this.field_150865_b][1]], i, l + Direction.offsetZ[BlockGroundBridgeTeleporter.field_150001_a[this.field_150865_b][1]]);
                            
                            if (block != TFBlocks.groundBridgeFrame)
                            {
                                break label56;
                            }
                        }
                    }
                }
            
            for (i = 0; i < this.field_150868_h; ++i)
            {
                j = this.position.posX + i * Direction.offsetX[BlockGroundBridgeTeleporter.field_150001_a[this.field_150865_b][1]];
                k = this.position.posY + this.field_150862_g;
                l = this.position.posZ + i * Direction.offsetZ[BlockGroundBridgeTeleporter.field_150001_a[this.field_150865_b][1]];
                
                if (this.worldObj.getBlock(j, k, l) != TFBlocks.groundBridgeFrame)
                {
                    this.field_150862_g = 0;
                    break;
                }
            }
            
            if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
            {
                return this.field_150862_g;
            }
            else
            {
                this.position = null;
                this.field_150868_h = 0;
                this.field_150862_g = 0;
                return 0;
            }
        }
        
        protected boolean func_150857_a(Block block)
        {
            return block.getMaterial() == Material.air || block == Blocks.fire || block == TFBlocks.groundBridgeTeleporter;
        }
        
        public boolean func_150860_b()
        {
            return this.position != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
        }
        
        public void func_150859_c()
        {
            for (int i = 0; i < this.field_150868_h; ++i)
            {
                int j = this.position.posX + Direction.offsetX[this.field_150866_c] * i;
                int k = this.position.posZ + Direction.offsetZ[this.field_150866_c] * i;
                
                for (int l = 0; l < this.field_150862_g; ++l)
                {
                    int i1 = this.position.posY + l;
                    this.worldObj.setBlock(j, i1, k, TFBlocks.groundBridgeTeleporter, this.field_150865_b, 2);
                }
            }
        }
    }
}