package fiskfille.tf.common.item;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFDimensionHelper;
import fiskfille.tf.helper.TFTileHelper;

public class ItemCSD extends Item
{
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
        world.getBlockMetadata(x, y, z);

        if (player.isSneaking())
        {
            DimensionalCoords coords = new DimensionalCoords(x, y + 1, z, world.provider.dimensionId);
            TileEntity tile = TFTileHelper.getTileBase(world.getTileEntity(x, y, z));

            if (tile instanceof TileEntityControlPanel)
            {
                coords.set(((TileEntityControlPanel) tile).data.destination);
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
            if (world.getTileEntity(x, y, z) instanceof TileEntityControlPanel)
            {
                TileEntityControlPanel tile = TFTileHelper.getTileBase((TileEntityControlPanel) world.getTileEntity(x, y, z));

                if (!tile.data.activationLeverState)
                {
                    DimensionalCoords coords = getCoords(itemstack);
                    tile.setSwitchesTo(coords);
                    tile.markBlockForUpdate();

                    if (world.isRemote)
                    {
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

        public DimensionalCoords()
        {
        }

        public DimensionalCoords(int x, int y, int z, int dim)
        {
            super(x, y, z);
            dimension = dim;
        }

        public DimensionalCoords(ChunkCoordinates coords, int dim)
        {
            super(coords);
            dimension = dim;
        }

        public DimensionalCoords(TileEntity tile)
        {
            set(tile);
        }

        public static DimensionalCoords copy(DimensionalCoords coords)
        {
            if (coords != null)
            {
                return new DimensionalCoords().set(coords);
            }

            return null;
        }

        public DimensionalCoords set(int x, int y, int z, int dim)
        {
            posX = x;
            posY = y;
            posZ = z;
            dimension = dim;

            return this;
        }

        public DimensionalCoords set(TileEntity tile)
        {
            if (tile.getWorldObj() != null)
            {
                return set(tile.xCoord, tile.yCoord, tile.zCoord, tile.getWorldObj().provider.dimensionId);
            }

            return this;
        }

        public DimensionalCoords set(DimensionalCoords coords)
        {
            return set(coords.toArray());
        }

        public DimensionalCoords set(int[] aint)
        {
            int[] aint1 = toArray();

            for (int i = 0; i < Math.min(aint.length, aint1.length); ++i)
            {
                aint1[i] = aint[i];
            }

            return set(aint1[0], aint1[1], aint1[2], aint1[3]);
        }

        public IChatComponent getFormatted()
        {
            ChatStyle green = new ChatStyle().setColor(EnumChatFormatting.GREEN);
            return new ChatComponentTranslation("csd.format", new ChatComponentText(TFDimensionHelper.getDimensionName(dimension)).setChatStyle(green), new ChatComponentText(posX + "").setChatStyle(green), new ChatComponentText(posY + "").setChatStyle(green), new ChatComponentText(posZ + "").setChatStyle(green));
        }

        public void toBytes(ByteBuf buf)
        {
            buf.writeInt(posX);
            buf.writeInt(posY);
            buf.writeInt(posZ);
            buf.writeInt(dimension);
        }

        public DimensionalCoords fromBytes(ByteBuf buf)
        {
            set(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());

            return this;
        }

        public int[] toArray()
        {
            return new int[] {posX, posY, posZ, dimension};
        }

        public static DimensionalCoords fromArray(int[] aint)
        {
            int[] aint1 = new int[4];

            for (int i = 0; i < Math.min(aint.length, aint1.length); ++i)
            {
                aint1[i] = aint[i];
            }

            return new DimensionalCoords(aint1[0], aint1[1], aint1[2], aint1[3]);
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
                DimensionalCoords coords = (DimensionalCoords) obj;
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
            return compareTo((DimensionalCoords) obj);
        }

        public int compareTo(DimensionalCoords coords)
        {
            return dimension == coords.dimension ? posY == coords.posY ? posZ == coords.posZ ? posX - coords.posX : posZ - coords.posZ : posY - coords.posY : dimension - coords.dimension;
        }
    }
}
