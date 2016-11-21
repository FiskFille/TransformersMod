package fiskfille.tf.helper;

import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class TFEnergyHelper
{
    public static String formatNumber(float f)
    {
        String s = (long) f + "";

        if (!s.contains("E"))
        {
            String s1 = "";

            for (int i = 0; i < s.length(); ++i)
            {
                s1 += s.charAt(i);

                if ((s.length() - i) % 3 == 1)
                {
                    s1 += ",";
                }
            }

            return s1.substring(0, s1.length() - 1);
        }

        return s;
    }

    public static boolean isInRange(TileEntity transmitterTile, TileEntity receiverTile)
    {
        try
        {
            IEnergyTransmitter transmitter = (IEnergyTransmitter) transmitterTile;
            IEnergyReceiver receiver = (IEnergyReceiver) receiverTile;
            Vec3 src = transmitter.getEnergyOutputOffset().addVector(transmitterTile.xCoord + 0.5F, transmitterTile.yCoord + 0.5F, transmitterTile.zCoord + 0.5F);
            Vec3 dst = receiver.getEnergyInputOffset().addVector(receiverTile.xCoord + 0.5F, receiverTile.yCoord + 0.5F, receiverTile.zCoord + 0.5F);

            return src.distanceTo(dst) <= transmitter.getRange();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean isInRange(TileEntity transmitterTile, ChunkCoordinates dstCoords)
    {
        try
        {
            IEnergyTransmitter transmitter = (IEnergyTransmitter) transmitterTile;
            Vec3 src = transmitter.getEnergyOutputOffset().addVector(transmitterTile.xCoord + 0.5F, transmitterTile.yCoord + 0.5F, transmitterTile.zCoord + 0.5F);
            Vec3 dst = Vec3.createVectorHelper(dstCoords.posX + 0.5F, dstCoords.posY + 0.5F, dstCoords.posZ + 0.5F);

            return src.distanceTo(dst) <= transmitter.getRange();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean isReceivingPowerFromAnother(TileEntity from, TileEntity to)
    {
        World world = from.getWorldObj();

        try
        {
            for (TileEntity tile : (List<TileEntity>) world.loadedTileEntityList)
            {
                if (tile != from && tile instanceof IEnergyTransmitter && ((IEnergyTransmitter) tile).isPowering(to))
                {
                    return true;
                }
            }

            return false;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static TileEntity getPoweredBy(TileEntity tileentity)
    {
        World world = tileentity.getWorldObj();

        try
        {
            for (TileEntity tile : (List<TileEntity>) world.loadedTileEntityList)
            {
                if (tileentity != tile && tile instanceof IEnergyTransmitter && ((IEnergyTransmitter) tile).isPowering(tileentity))
                {
                    return tile;
                }
            }

            return null;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static TransmissionHandler getTransmissionHandler(TileEntity tile)
    {
        if (tile instanceof IEnergyTransmitter)
        {
            return ((IEnergyTransmitter) tile).getTransmissionHandler();
        }

        return null;
    }

    public static ReceiverHandler getReceiverHandler(TileEntity tile)
    {
        if (tile instanceof IEnergyTransmitter)
        {
            return ((IEnergyTransmitter) tile).getReceiverHandler();
        }
        else if (tile instanceof IEnergyReceiver)
        {
            return ((IEnergyReceiver) tile).getReceiverHandler();
        }

        return null;
    }

    public static void addReceivers(World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof IEnergyReceiver)
        {
            ReceiverHandler receiverHandler = ((IEnergyReceiver) tile).getReceiverHandler();

            for (TileEntity worldTile : (List<TileEntity>) world.loadedTileEntityList)
            {
                if (worldTile instanceof IEnergyTransmitter)
                {
                    TransmissionHandler transmissionHandler = ((IEnergyTransmitter) worldTile).getTransmissionHandler();

                    transmissionHandler.queue(new ChunkCoordinates(x, y, z));
                    receiverHandler.queue(new ChunkCoordinates(worldTile.xCoord, worldTile.yCoord, worldTile.zCoord));
                }
            }
        }
    }

    public static boolean isGrandParentTo(TileEntity from, TileEntity to)
    {
        if (from instanceof IEnergyTransmitter)
        {
            IEnergyTransmitter transmitter = (IEnergyTransmitter) from;

            for (TileEntity tile : transmitter.getTransmissionHandler().getReceivers())
            {
                if (tile == to)
                {
                    return true;
                }

                if (isGrandParentTo(tile, to))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public static MovingObjectPosition rayTraceBlocks(World world, Vec3 src, Vec3 dst)
    {
        return rayTraceBlocks(world, src, dst, false, false, false);
    }

    public static MovingObjectPosition rayTraceBlocks(World world, Vec3 src, Vec3 dst, boolean flag)
    {
        return rayTraceBlocks(world, src, dst, flag, false, false);
    }

    public static MovingObjectPosition rayTraceBlocks(World world, Vec3 src, Vec3 dst, boolean flag, boolean flag1, boolean flag2)
    {
        if (!Double.isNaN(src.xCoord) && !Double.isNaN(src.yCoord) && !Double.isNaN(src.zCoord))
        {
            if (!Double.isNaN(dst.xCoord) && !Double.isNaN(dst.yCoord) && !Double.isNaN(dst.zCoord))
            {
                int x = MathHelper.floor_double(dst.xCoord);
                int y = MathHelper.floor_double(dst.yCoord);
                int z = MathHelper.floor_double(dst.zCoord);
                int x1 = MathHelper.floor_double(src.xCoord);
                int y1 = MathHelper.floor_double(src.yCoord);
                int z1 = MathHelper.floor_double(src.zCoord);
                Block block = world.getBlock(x1, y1, z1);
                int metadata = world.getBlockMetadata(x1, y1, z1);

                if ((!flag1 || block.getCollisionBoundingBoxFromPool(world, x1, y1, z1) != null) && block.canCollideCheck(metadata, flag) && !(!block.isOpaqueCube() && world.getTileEntity(x1, y1, z1) instanceof IEnergyReceiver))
                {
                    MovingObjectPosition mop = block.collisionRayTrace(world, x1, y1, z1, src, dst);

                    if (mop != null)
                    {
                        return mop;
                    }
                }

                MovingObjectPosition movingobjectposition2 = null;
                metadata = 200;

                while (metadata-- >= 0)
                {
                    if (Double.isNaN(src.xCoord) || Double.isNaN(src.yCoord) || Double.isNaN(src.zCoord))
                    {
                        return null;
                    }

                    if (x1 == x && y1 == y && z1 == z)
                    {
                        return flag2 ? movingobjectposition2 : null;
                    }

                    boolean flag6 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (x > x1)
                    {
                        d0 = (double) x1 + 1.0D;
                    }
                    else if (x < x1)
                    {
                        d0 = (double) x1 + 0.0D;
                    }
                    else
                    {
                        flag6 = false;
                    }

                    if (y > y1)
                    {
                        d1 = (double) y1 + 1.0D;
                    }
                    else if (y < y1)
                    {
                        d1 = (double) y1 + 0.0D;
                    }
                    else
                    {
                        flag3 = false;
                    }

                    if (z > z1)
                    {
                        d2 = (double) z1 + 1.0D;
                    }
                    else if (z < z1)
                    {
                        d2 = (double) z1 + 0.0D;
                    }
                    else
                    {
                        flag4 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = dst.xCoord - src.xCoord;
                    double d7 = dst.yCoord - src.yCoord;
                    double d8 = dst.zCoord - src.zCoord;

                    if (flag6)
                    {
                        d3 = (d0 - src.xCoord) / d6;
                    }

                    if (flag3)
                    {
                        d4 = (d1 - src.yCoord) / d7;
                    }

                    if (flag4)
                    {
                        d5 = (d2 - src.zCoord) / d8;
                    }

                    boolean flag5 = false;
                    byte b0;

                    if (d3 < d4 && d3 < d5)
                    {
                        if (x > x1)
                        {
                            b0 = 4;
                        }
                        else
                        {
                            b0 = 5;
                        }

                        src.xCoord = d0;
                        src.yCoord += d7 * d3;
                        src.zCoord += d8 * d3;
                    }
                    else if (d4 < d5)
                    {
                        if (y > y1)
                        {
                            b0 = 0;
                        }
                        else
                        {
                            b0 = 1;
                        }

                        src.xCoord += d6 * d4;
                        src.yCoord = d1;
                        src.zCoord += d8 * d4;
                    }
                    else
                    {
                        if (z > z1)
                        {
                            b0 = 2;
                        }
                        else
                        {
                            b0 = 3;
                        }

                        src.xCoord += d6 * d5;
                        src.yCoord += d7 * d5;
                        src.zCoord = d2;
                    }

                    Vec3 vec32 = Vec3.createVectorHelper(src.xCoord, src.yCoord, src.zCoord);
                    x1 = (int) (vec32.xCoord = (double) MathHelper.floor_double(src.xCoord));

                    if (b0 == 5)
                    {
                        --x1;
                        ++vec32.xCoord;
                    }

                    y1 = (int) (vec32.yCoord = (double) MathHelper.floor_double(src.yCoord));

                    if (b0 == 1)
                    {
                        --y1;
                        ++vec32.yCoord;
                    }

                    z1 = (int) (vec32.zCoord = (double) MathHelper.floor_double(src.zCoord));

                    if (b0 == 3)
                    {
                        --z1;
                        ++vec32.zCoord;
                    }

                    Block block1 = world.getBlock(x1, y1, z1);
                    int l1 = world.getBlockMetadata(x1, y1, z1);

                    if (!flag1 || block1.getCollisionBoundingBoxFromPool(world, x1, y1, z1) != null)
                    {
                        if (block1.canCollideCheck(l1, flag) && !(!block1.isOpaqueCube() && world.getTileEntity(x1, y1, z1) instanceof IEnergyReceiver))
                        {
                            MovingObjectPosition movingobjectposition1 = block1.collisionRayTrace(world, x1, y1, z1, src, dst);

                            if (movingobjectposition1 != null)
                            {
                                return movingobjectposition1;
                            }
                        }
                        else
                        {
                            movingobjectposition2 = new MovingObjectPosition(x1, y1, z1, b0, src, false);
                        }
                    }
                }

                return flag2 ? movingobjectposition2 : null;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public static boolean canPowerChainReach(TileEntity origin)
    {
        try
        {
            ReceiverHandler receiverHandler = getReceiverHandler(origin);

            if (receiverHandler != null)
            {
                List<TileEntity> transmitters = receiverHandler.getTransmitters();

                if (!transmitters.isEmpty())
                {
                    for (TileEntity transmitter : transmitters)
                    {
                        if (transmitter instanceof IEnergyTransmitter && ((IEnergyTransmitter) transmitter).canPowerReach(origin))
                        {
                            if (((IEnergyTransmitter) transmitter).getEnergy() > 0 || canPowerChainReach(transmitter))
                            {
                                return true;
                            }
                        }
                    }
                }
            }

            return false;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
