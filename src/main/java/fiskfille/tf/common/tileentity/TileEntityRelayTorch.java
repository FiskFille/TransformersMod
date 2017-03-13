package fiskfille.tf.common.tileentity;

import java.util.Set;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;
import fiskfille.tf.common.energon.power.ReceiverEntry;

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
        ForgeDirection dir = ForgeDirection.getOrientation(getBlockMetadata());
        float f = 0.0625F * 3.5F;
        
        if (dir == ForgeDirection.UP)
        {
            return Vec3.createVectorHelper(0, -f, 0);
        }
        else if (dir == ForgeDirection.DOWN)
        {
            return Vec3.createVectorHelper(0, f, 0);
        }
        
        int[] rotations = {2, 0, 1, 3};
        float yaw = rotations[dir.ordinal() - 2] * 90;
        
        Vec3 vec3 = Vec3.createVectorHelper(0, 0, -f);
        vec3.rotateAroundY(-yaw * (float) Math.PI / 180.0F);

        return vec3;
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
