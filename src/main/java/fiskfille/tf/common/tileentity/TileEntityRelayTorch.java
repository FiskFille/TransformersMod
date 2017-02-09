package fiskfille.tf.common.tileentity;

import java.util.Set;

import fiskfille.tf.common.energon.power.ReceiverEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class TileEntityRelayTorch extends TileEntityRelayTower
{
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);

        if (isValid(getBlockMetadata()))
        {
            Set<ReceiverEntry> receivers = data.transmissionHandler.getReceivers();

            for (ReceiverEntry entry : receivers)
            {
                TileEntity tile = entry.getTile();
                
                if (tile != null)
                {
                    bounds = bounds.func_111270_a(tile.getRenderBoundingBox());
                }
            }
        }

        return bounds;
    }
    
    @Override
    public float getTransmissionRate()
    {
        return 300;
    }

    @Override
    public float getRange()
    {
        return 10;
    }

    @Override
    public Vec3 getEnergyInputOffset()
    {
        int metadata = worldObj != null ? getBlockMetadata() : 0;
        float offset = 0.0625F * 3.5F;

        if (metadata == 1)
        {
            return Vec3.createVectorHelper(offset, 0, 0);
        }
        else if (metadata == 2)
        {
            return Vec3.createVectorHelper(-offset, 0, 0);
        }
        else if (metadata == 3)
        {
            return Vec3.createVectorHelper(0, 0, offset);
        }
        else if (metadata == 4)
        {
            return Vec3.createVectorHelper(0, 0, -offset);
        }
        else if (metadata == 5)
        {
            return Vec3.createVectorHelper(0, offset, 0);
        }
        else if (metadata == 6)
        {
            return Vec3.createVectorHelper(0, -offset, 0);
        }

        return Vec3.createVectorHelper(0, 0, 0);
    }

    @Override
    public boolean isValid(int metadata)
    {
        return true;
    }

    @Override
    public int[] getBaseOffsets(int metadata)
    {
        return new int[] {0, 0, 0};
    }
}
