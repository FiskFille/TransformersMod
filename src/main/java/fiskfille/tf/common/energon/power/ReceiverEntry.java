package fiskfille.tf.common.energon.power;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;

public class ReceiverEntry extends NetworkEntry
{
    private boolean canReach;
    
    public ReceiverEntry(DimensionalCoords coordinates)
    {
        super(coordinates, null);
    }

    public ReceiverEntry(TileEntity tile)
    {
        super(tile);
    }
    
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setBoolean("CanReach", canReach);
    }
    
    public static ReceiverEntry readFromNBT(NBTTagCompound compound)
    {
        ReceiverEntry entry = new ReceiverEntry(new DimensionalCoords(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z"), compound.getInteger("Dim")));
        entry.setCanReach(compound.getBoolean("CanReach"));
        
        return entry;
    }
    
    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeBoolean(canReach);
    }
    
    public static ReceiverEntry fromBytes(ByteBuf buf)
    {
        ReceiverEntry entry = new ReceiverEntry(new DimensionalCoords().fromBytes(buf));
        entry.setCanReach(buf.readBoolean());
        
        return entry;
    }

    public boolean canReach()
    {
        return canReach;
    }

    public void setCanReach(boolean canReach)
    {
        this.canReach = canReach;
    }
}
