package fiskfille.tf.common.tileentity;

import net.minecraft.util.Vec3;

public class TileEntityRelayTorch extends TileEntityRelayTower
{
    @Override
    public float getRange()
    {
        return 10;
    }

    @Override
    public Vec3 getEnergyInputOffset()
    {
        int metadata = getBlockMetadata();
        float offset = 0.0625F * 11.5F - 0.5F;
        
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
}
