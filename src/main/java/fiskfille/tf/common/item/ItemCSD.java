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
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import fiskfille.tf.common.block.BlockControlPanel;
import fiskfille.tf.common.network.MessageControlPanelSetConfig;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFHelper;

public class ItemCSD extends Item
{
    private static String[] modes = {"save", "load"};

    public ItemCSD()
    {
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
    {
        DimensionalCoords coords = getCoords(itemstack);
        list.add(coords.getFormatted().getFormattedText());
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        if (player.isSneaking())
        {
            DimensionalCoords coords = new DimensionalCoords(x, y + 1, z, world.provider.dimensionId);

            if (world.getTileEntity(x, y, z) instanceof TileEntityControlPanel && BlockControlPanel.isBlockLeftSideOfPanel(metadata))
            {
                TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(x, y, z);
                coords.set(tile.destX, tile.destY, tile.destZ, tile.getDestDimensionID());
            }

            setCoords(itemstack, coords);

            if (world.isRemote)
            {
                player.addChatComponentMessage(new ChatComponentTranslation("csd.save.success", coords.getFormatted()));
            }

            return true;
        }
        else
        {
            if (world.getTileEntity(x, y, z) instanceof TileEntityControlPanel && BlockControlPanel.isBlockLeftSideOfPanel(metadata))
            {
                TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(x, y, z);

                if (!tile.activationLeverState)
                {
                    DimensionalCoords coords = getCoords(itemstack);

                    if (world.isRemote)
                    {
                        TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanelSetConfig(x, y, z, tile.getWorldObj().provider.dimensionId, coords));
                        player.addChatComponentMessage(new ChatComponentTranslation("csd.load.success", coords.getFormatted()));
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

        public IChatComponent getFormatted()
        {
            ChatStyle green = new ChatStyle().setColor(EnumChatFormatting.GREEN);
            return new ChatComponentTranslation("csd.format", new ChatComponentText(TFHelper.getDimensionName(dimension)).setChatStyle(green), new ChatComponentText(posX + "").setChatStyle(green), new ChatComponentText(posY + "").setChatStyle(green), new ChatComponentText(posZ + "").setChatStyle(green));
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
