package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import fiskfille.tf.common.block.BlockGroundBridgeControl;
import fiskfille.tf.common.network.MessageControlPanelSetConfig;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFHelper;

public class ItemCSD extends Item
{
    private static String[] modes = {"save", "load"};
    
    public ItemCSD()
    {
        super();
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
    {
        int mode = getMode(itemstack);
        DimensionalCoords coords = getCoords(itemstack);
        
        list.add(StatCollector.translateToLocalFormatted("csd.mode", EnumChatFormatting.YELLOW + StatCollector.translateToLocal("csd." + modes[mode])));
        list.add(formatCoords(coords).getFormattedText());
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (player.isSneaking())
        {
            int mode = getMode(itemstack);
            mode = setMode(itemstack, mode == 1 ? 0 : 1);
            
            if (world.isRemote)
            {
                player.addChatComponentMessage(new ChatComponentTranslation("csd.mode", new ChatComponentTranslation("csd." + modes[mode]).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW))));
            }
        }
        
        return itemstack;
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        int mode = getMode(itemstack);
        
        if (mode == 0)
        {
            DimensionalCoords coords = new DimensionalCoords(x, y + 1, z, world.provider.dimensionId);
            
            if (world.getTileEntity(x, y, z) instanceof TileEntityControlPanel && BlockGroundBridgeControl.isBlockLeftSideOfPanel(metadata))
            {
                TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(x, y, z);
                coords.set(tile.destX, tile.destY, tile.destZ, tile.getDestDimensionID());
            }
            
            setCoords(itemstack, coords);
            
            if (world.isRemote)
            {
                player.addChatComponentMessage(new ChatComponentTranslation("csd.save.success", formatCoords(coords)));
            }
            
            return true;
        }
        else if (mode == 1)
        {
            if (world.getTileEntity(x, y, z) instanceof TileEntityControlPanel && BlockGroundBridgeControl.isBlockLeftSideOfPanel(metadata))
            {
                TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(x, y, z);
                
                if (!tile.activationLeverState)
                {
                    DimensionalCoords coords = getCoords(itemstack);
                    
                    if (world.isRemote)
                    {
                        TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanelSetConfig(x, y, z, coords));
                        player.addChatComponentMessage(new ChatComponentTranslation("csd.load.success", formatCoords(coords)));
                    }
                    
                    return true;
                }
                else
                {
                    if (world.isRemote)
                    {
                        player.addChatComponentMessage(new ChatComponentTranslation("csd.load.fail").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                    }
                }
            }
        }

        return false;
    }
    
    public IChatComponent formatCoords(DimensionalCoords coords)
    {
        ChatStyle green = new ChatStyle().setColor(EnumChatFormatting.GREEN);
        return new ChatComponentTranslation("csd.format", new ChatComponentText(TFHelper.getDimensionName(coords.dimension)).setChatStyle(green), new ChatComponentText(coords.posX + "").setChatStyle(green), new ChatComponentText(coords.posY + "").setChatStyle(green), new ChatComponentText(coords.posZ + "").setChatStyle(green));
    }
    
    public static int getMode(ItemStack itemstack)
    {
        return itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Mode") ? itemstack.getTagCompound().getInteger("Mode") : 0;
    }
    
    public static int setMode(ItemStack itemstack, int mode)
    {
        if (!itemstack.hasTagCompound())
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        
        itemstack.getTagCompound().setInteger("Mode", mode);
        
        return mode;
    }
    
    public static DimensionalCoords getCoords(ItemStack itemstack)
    {
        DimensionalCoords coords = new DimensionalCoords();
        
        if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Coordinates", NBT.TAG_COMPOUND))
        {
            NBTTagCompound nbttagcompound = itemstack.getTagCompound().getCompoundTag("Coordinates");
            coords.set(nbttagcompound.getInteger("x"), nbttagcompound.getInteger("y"), nbttagcompound.getInteger("z"), nbttagcompound.getInteger("dim"));
        }
        
        return coords;
    }
    
    public static DimensionalCoords setCoords(ItemStack itemstack, DimensionalCoords coords)
    {
        if (!itemstack.hasTagCompound())
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setInteger("x", coords.posX);
        nbttagcompound.setInteger("y", coords.posY);
        nbttagcompound.setInteger("z", coords.posZ);
        nbttagcompound.setInteger("dim", coords.dimension);
        
        itemstack.getTagCompound().setTag("Coordinates", nbttagcompound);
        
        return coords;
    }

    public static class DimensionalCoords extends ChunkCoordinates
    {
        public int dimension;

        public DimensionalCoords() {}

        public DimensionalCoords(int x, int y, int z, int dim)
        {
            super(x, y, z);
            dimension = dim;
        }

        public DimensionalCoords(ChunkCoordinates coords, int dimension)
        {
            super(coords);
        }

        public void set(int x, int y, int z, int dim)
        {
            posX = x;
            posY = y;
            posZ = z;
            dimension = dim;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof DimensionalCoords))
            {
                return false;
            }
            else
            {
                DimensionalCoords coords = (DimensionalCoords)obj;
                return posX == coords.posX && posY == coords.posY && posZ == coords.posZ && dimension == coords.dimension;
            }
        }

        @Override
        public int hashCode()
        {
            return posX + posZ << 8 + posY << 16 + dimension << 32;
        }

        @Override
        public String toString()
        {
            return "Pos{x=" + posX + ", y=" + posY + ", z=" + posZ + ", dim=" + dimension + '}';
        }

        @Override
        public int compareTo(Object obj)
        {
            return compareTo((DimensionalCoords)obj);
        }

        public int compareTo(DimensionalCoords coords)
        {
            return dimension == coords.dimension ? (posY == coords.posY ? (posZ == coords.posZ ? posX - coords.posX : posZ - coords.posZ) : posY - coords.posY) : dimension - coords.dimension;
        }
    }
}
