package fiskfille.tf.common.chunk;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.util.Constants.NBT;

import com.google.common.collect.Lists;

public class SubTicket
{
    public Ticket owner;
    private NBTTagCompound nbtTag;

    public int xCoord;
    public int yCoord;
    public int zCoord;

    public SubTicket(int x, int y, int z)
    {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public boolean matches(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        else if (!(obj instanceof SubTicket))
        {
            return false;
        }
        else
        {
            SubTicket subTicket = (SubTicket) obj;
            return xCoord == subTicket.xCoord && yCoord == subTicket.yCoord && zCoord == subTicket.zCoord;
        }
    }

    public NBTTagCompound getTag()
    {
        if (nbtTag == null)
        {
            nbtTag = new NBTTagCompound();
        }

        return nbtTag;
    }

    public SubTicket assign(Ticket ticket)
    {
        NBTTagList nbttaglist = ticket.getModData().getTagList("SubTickets", NBT.TAG_COMPOUND);
        nbttaglist.appendTag(writeToNBT());

        ticket.getModData().setTag("SubTickets", nbttaglist);
        owner = ticket;

        return this;
    }

    public Ticket remove()
    {
        List<SubTicket> list = SubTicket.getChildren(owner);
        List<SubTicket> list1 = Lists.newArrayList();

        for (SubTicket subTicket : list)
        {
            if (!matches(subTicket))
            {
                list1.add(subTicket);
            }
        }

        owner.getModData().setTag("SubTickets", new NBTTagList());

        for (SubTicket subTicket : list1)
        {
            assign(owner);
        }

        return owner;
    }

    public NBTTagCompound writeToNBT()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setInteger("xCoord", xCoord);
        nbttagcompound.setInteger("yCoord", yCoord);
        nbttagcompound.setInteger("zCoord", zCoord);
        nbttagcompound.setTag("Tag", getTag());

        return nbttagcompound;
    }

    public static SubTicket readFromNBT(NBTTagCompound nbttagcompound)
    {
        SubTicket subTicket = new SubTicket(nbttagcompound.getInteger("xCoord"), nbttagcompound.getInteger("yCoord"), nbttagcompound.getInteger("zCoord"));
        subTicket.nbtTag = nbttagcompound.getCompoundTag("Tag");

        return subTicket;
    }

    public static List<SubTicket> getChildren(Ticket ticket)
    {
        List<SubTicket> list = Lists.newArrayList();
        NBTTagList nbttaglist = ticket.getModData().getTagList("SubTickets", NBT.TAG_COMPOUND);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            SubTicket subTicket = readFromNBT(nbttaglist.getCompoundTagAt(i));
            subTicket.owner = ticket;
            list.add(subTicket);
        }

        return list;
    }

    public static SubTicket get(Ticket ticket, TileEntity tile)
    {
        List<SubTicket> list = getChildren(ticket);

        for (SubTicket subTicket : list)
        {
            if (subTicket.xCoord == tile.xCoord && subTicket.yCoord == tile.yCoord && subTicket.zCoord == tile.zCoord)
            {
                return subTicket;
            }
        }

        return null;
    }

    public static SubTicket fromTile(TileEntity tile)
    {
        return fromTile(null, tile);
    }

    public static SubTicket fromTile(Ticket ticket, TileEntity tile)
    {
        SubTicket subTicket = new SubTicket(tile.xCoord, tile.yCoord, tile.zCoord);
        subTicket.owner = ticket;

        return subTicket;
    }
}
