package fiskfille.tf.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

import fiskfille.tf.common.energon.power.IEnergyContainer;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.energon.power.TargetReceiver;
import fiskfille.tf.common.energon.power.TargetingTransmitter;
import fiskfille.tf.common.energon.power.TransmissionHandler;

public class TFEnergyHelper
{
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

    public static boolean isGrandParentTo(TileEntity from, TileEntity to)
    {
        if (from instanceof IEnergyTransmitter)
        {
            IEnergyTransmitter transmitter = (IEnergyTransmitter) from;

            for (TargetReceiver receiver : transmitter.getTransmissionHandler().getReceivers())
            {
                if (receiver.getCoordinates().equals(new ChunkCoordinates(to.xCoord, to.yCoord, to.zCoord)))
                {
                    return true;
                }

                if (isGrandParentTo(receiver.getTile(), to))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public static List<ChunkCoordinates> getGrandparents(TileEntity tile)
    {
        List<ChunkCoordinates> coordinates = new ArrayList<ChunkCoordinates>();

        for (TileEntity loadedTile : (List<TileEntity>) tile.getWorldObj().loadedTileEntityList)
        {
            if (loadedTile instanceof IEnergyReceiver && ((IEnergyReceiver) loadedTile).canReceiveEnergy(tile) && TFEnergyHelper.isInRange(tile, loadedTile))
            {
                if (isGrandParentTo(loadedTile, tile))
                {
                    coordinates.add(new ChunkCoordinates(loadedTile.xCoord, loadedTile.yCoord, loadedTile.zCoord));
                }
            }
        }

        return coordinates;
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
                        d0 = x1 + 1.0D;
                    }
                    else if (x < x1)
                    {
                        d0 = x1 + 0.0D;
                    }
                    else
                    {
                        flag6 = false;
                    }

                    if (y > y1)
                    {
                        d1 = y1 + 1.0D;
                    }
                    else if (y < y1)
                    {
                        d1 = y1 + 0.0D;
                    }
                    else
                    {
                        flag3 = false;
                    }

                    if (z > z1)
                    {
                        d2 = z1 + 1.0D;
                    }
                    else if (z < z1)
                    {
                        d2 = z1 + 0.0D;
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
                    x1 = (int) (vec32.xCoord = MathHelper.floor_double(src.xCoord));

                    if (b0 == 5)
                    {
                        --x1;
                        ++vec32.xCoord;
                    }

                    y1 = (int) (vec32.yCoord = MathHelper.floor_double(src.yCoord));

                    if (b0 == 1)
                    {
                        --y1;
                        ++vec32.yCoord;
                    }

                    z1 = (int) (vec32.zCoord = MathHelper.floor_double(src.zCoord));

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

    public static boolean canPowerReach(TileEntity origin, TargetReceiver receiver)
    {
        try
        {
            IEnergyTransmitter transmitter = (IEnergyTransmitter) origin;

            ChunkCoordinates pos = receiver.getCoordinates();
            Vec3 hit = receiver.getEnergyInputOffset().addVector(pos.posX + 0.5F, pos.posY + 0.5F, pos.posZ + 0.5F);
            Vec3 original = receiver.getEnergyInputOffset().addVector(pos.posX + 0.5F, pos.posY + 0.5F, pos.posZ + 0.5F);
            Vec3 output = transmitter.getEnergyOutputOffset().addVector(origin.xCoord + 0.5F, origin.yCoord + 0.5F, origin.zCoord + 0.5F);

            double deltaScale = 1F / hit.distanceTo(output);
            output = Vec3.createVectorHelper(output.xCoord + (hit.xCoord - output.xCoord) * deltaScale, output.yCoord + (hit.yCoord - output.yCoord) * deltaScale, output.zCoord + (hit.zCoord - output.zCoord) * deltaScale);
            MovingObjectPosition result = rayTraceBlocks(origin.getWorldObj(), output, hit);

            if (result != null)
            {
                hit = result.hitVec;
            }

            return hit.xCoord == original.xCoord && hit.yCoord == original.yCoord && hit.zCoord == original.zCoord;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean canPowerChainReach(TileEntity origin)
    {
        try
        {
            ReceiverHandler receiverHandler = getReceiverHandler(origin);

            if (receiverHandler != null)
            {
                Set<TargetingTransmitter> transmitters = receiverHandler.getTransmitters();

                if (!transmitters.isEmpty())
                {
                    for (TargetingTransmitter target : transmitters)
                    {
                        TileEntity tile = target.getTile();
                        IEnergyTransmitter transmitter = target.getTransmitter();

                        if (canPowerReach(tile, receiverHandler.getReceiver()))
                        {
                            if (transmitter.getEnergy() > 0 || canPowerChainReach(tile))
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

    public static List<TargetReceiver> getReceiversToPower(IEnergyTransmitter transmitter)
    {
        try
        {
            List<TargetReceiver> tilesToPower = Lists.newArrayList();
            TransmissionHandler transmissionHandler = transmitter.getTransmissionHandler();

            for (TargetReceiver receiver : transmissionHandler.getReceivers())
            {
                if (canPowerReach(transmissionHandler.getTransmitter().getTile(), receiver))
                {
                    tilesToPower.add(receiver);
                }
            }

            return tilesToPower;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean isPowering(IEnergyTransmitter transmitter, TargetReceiver receiver)
    {
        try
        {
            return getReceiversToPower(transmitter).contains(receiver);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean isPowering(IEnergyTransmitter transmitter, TileEntity tile)
    {
        try
        {
            List<TargetReceiver> receivers = getReceiversToPower(transmitter);

            for (TargetReceiver receiver : receivers)
            {
                ChunkCoordinates coordinates = receiver.getCoordinates();

                if (coordinates.posX == tile.xCoord && coordinates.posY == tile.yCoord && coordinates.posZ == tile.zCoord)
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

    public static float transferEnergy(IEnergyContainer to, IEnergyContainer from, float amount, boolean simulate)
    {
        return to.receiveEnergy(from.extractEnergy(to.receiveEnergy(amount, true), simulate), simulate);
    }

    public static void applyClientEnergyUsage(IEnergyContainer container)
    {
        float usage = container.getEnergyUsage();

        if (usage < 0.0F)
        {
            container.extractEnergy(-usage, false);
        }
        else if (usage > 0.0F)
        {
            container.receiveEnergy(usage, false);
        }
    }
}
