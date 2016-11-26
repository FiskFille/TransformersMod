package fiskfille.tf.common.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.Lists;

import fiskfille.tf.common.block.BlockGroundBridgeControl;
import fiskfille.tf.common.block.BlockGroundBridgeFrame;
import fiskfille.tf.common.block.BlockGroundBridgeTeleporter;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.chunk.ForcedChunk;
import fiskfille.tf.common.chunk.SubTicket;
import fiskfille.tf.common.chunk.TFChunkManager;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.groundbridge.GroundBridgeError;
import fiskfille.tf.common.item.TFItems;

public class TileEntityControlPanel extends TileEntityContainer implements IChunkLoaderTile/*IEnergonPowered*/ // TODO
{
    public Integer[][] switches = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    private ItemStack[] inventory = new ItemStack[3];

    public int destX;
    public int destY;
    public int destZ;
    public int destDimIndex;

    public int portalDirection;
    public float animPortalDirection;
    public float prevAnimPortalDirection;
    public int srcPortalDirection;
    public boolean activationLeverState = false;
    public boolean activationLeverCoverState = false;
    public float activationLeverTimer;
    public float prevActivationLeverTimer;
    public float activationLeverCoverTimer;
    public float prevActivationLeverCoverTimer;

    public List<GroundBridgeError> errors = Lists.newArrayList();
    public boolean hasSpace;

    public ChunkCoordinates groundBridgeFramePos;
    public LinkedList<Ticket> chunkTickets = Lists.newLinkedList(Arrays.asList((Ticket)null, (Ticket)null));
    public LinkedList<ForcedChunk> forcedChunks = Lists.newLinkedList(Arrays.asList((ForcedChunk)null, (ForcedChunk)null));

    @Override
    public void updateEntity()
    {
        prevActivationLeverTimer = activationLeverTimer;
        prevActivationLeverCoverTimer = activationLeverCoverTimer;
        prevAnimPortalDirection = animPortalDirection;

        if (BlockGroundBridgeControl.isBlockLeftSideOfPanel(getBlockMetadata()))
        {
            calculateCoords();
            errors.clear();
            
            if (!worldObj.isRemote)
            {
                loadChunks();
            }

            if (groundBridgeFramePos != null)
            {
                int x = groundBridgeFramePos.posX;
                int y = groundBridgeFramePos.posY;
                int z = groundBridgeFramePos.posZ;

                if (BlockGroundBridgeFrame.getFrameDirection(worldObj, x, y, z) == null)
                {
                    groundBridgeFramePos = null;
                }
            }

            boolean flag = false;

            if (!activationLeverState)
            {
                List<TileEntity> list = new ArrayList<TileEntity>(worldObj.loadedTileEntityList);
                Collections.sort(list, new Comparator<TileEntity>()
                {
                    @Override
                    public int compare(TileEntity arg0, TileEntity arg1)
                    {
                        return Integer.compare((int)Math.sqrt(getDistanceFrom(arg0.xCoord, arg0.zCoord, arg0.yCoord)), (int)Math.sqrt(getDistanceFrom(arg1.xCoord, arg1.zCoord, arg1.yCoord)));
                    }
                });

                for (TileEntity tileentity : list)
                {
                    if (Math.sqrt(getDistanceFrom(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)) <= 20)
                    {
                        if (tileentity instanceof TileEntityGroundBridgeFrame)
                        {
                            TileEntityGroundBridgeFrame tile = (TileEntityGroundBridgeFrame)tileentity;
                            ForgeDirection direction = BlockGroundBridgeFrame.getFrameDirection(worldObj, tile.xCoord, tile.yCoord, tile.zCoord);

                            if (direction != null)
                            {
                                flag = isPortalObstructed(tile.xCoord, tile.yCoord, tile.zCoord, direction);
                                groundBridgeFramePos = new ChunkCoordinates(tile.xCoord, tile.yCoord, tile.zCoord);
                                break;
                            }
                        }
                    }
                }
            }

            if (groundBridgeFramePos == null)
            {
                errors.add(GroundBridgeError.NO_PORTAL_LINKED);
            }

            if (flag)
            {
                errors.add(GroundBridgeError.PORTAL_OBSTRUCTED);
            }

            if (Math.sqrt(getDistanceFrom(destX, destY, destZ)) <= 64)
            {
//                errors.add(GroundBridgeError.INVALID_COORDS); TODO: Uncomment
            }

            if (destY - 1 <= 0 || destY + 3 >= worldObj.getHeight())
            {
                errors.add(GroundBridgeError.OUT_OF_BOUNDS);
            }

            if (!worldObj.isRemote)
            {
                boolean newSpace = checkForSpace();

                if (newSpace != hasSpace)
                {
                    markBlockForUpdate();
                    hasSpace = newSpace;
                }
            }

            if (!hasSpace)
            {
                errors.add(GroundBridgeError.NOT_ENOUGH_SPACE);
            }

            activationLeverCoverState = !(!errors.isEmpty() && !activationLeverState);

            if (activationLeverState)
            {
                if (activationLeverTimer < 1)
                {
                    activationLeverTimer += 0.125F;
                }
            }
            else
            {
                if (activationLeverTimer > 0)
                {
                    activationLeverTimer -= 0.125F;
                }
            }

            if (activationLeverCoverState)
            {
                if (activationLeverCoverTimer < 1)
                {
                    activationLeverCoverTimer += 0.1F;
                }
            }
            else
            {
                if (activationLeverCoverTimer > 0)
                {
                    activationLeverCoverTimer -= 0.1F;
                }
            }

            activationLeverTimer = MathHelper.clamp_float(activationLeverTimer, 0, 1);
            activationLeverCoverTimer = MathHelper.clamp_float(activationLeverCoverTimer, 0, 1);

            if (!activationLeverState && groundBridgeFramePos != null)
            {
                int x = groundBridgeFramePos.posX;
                int y = groundBridgeFramePos.posY;
                int z = groundBridgeFramePos.posZ;
                srcPortalDirection = worldObj.getBlockMetadata(x, y, z);
            }

            if (errors.isEmpty() && activationLeverState && groundBridgeFramePos != null)
            {
                int x = groundBridgeFramePos.posX;
                int y = groundBridgeFramePos.posY;
                int z = groundBridgeFramePos.posZ;
                BlockGroundBridgeTeleporter.spawnTeleporter(worldObj, x, y, z, this);

                if (portalDirection % 2 == 0)
                {
                    BlockGroundBridgeTeleporter.fillNorthFacingFrame(getDestWorld(), destX, destY - 3, destZ, TFBlocks.groundBridgeTeleporter, this, true);
                }
                else
                {
                    BlockGroundBridgeTeleporter.fillEastFacingFrame(getDestWorld(), destX, destY - 3, destZ, TFBlocks.groundBridgeTeleporter, this, true);
                }
            }

            int dir = portalDirection == 0 && animPortalDirection > portalDirection ? (animPortalDirection == 0 ? 0 : 4) : portalDirection;
            float incr = 0.2F;

            if (animPortalDirection < dir)
            {
                animPortalDirection += incr;
            }
            else if (animPortalDirection > dir)
            {
                animPortalDirection -= incr;
            }

            animPortalDirection = (float)Math.round(animPortalDirection * 1000) / 1000;

            if (animPortalDirection > 3)
            {
                animPortalDirection -= 4;
                prevAnimPortalDirection -= 4;
            }
        }
    }

    public void calculateCoords()
    {
        destX = xCoord;
        destY = yCoord;
        destZ = zCoord;

        int[] increments = {1, 10, 100, 1000};

        for (int i = 0; i < switches[0].length; ++i)
        {
            destX += switches[0][i] * increments[i];
        }

        for (int i = 0; i < switches[1].length; ++i)
        {
            destY += switches[1][i] * increments[i];
        }

        for (int i = 0; i < switches[2].length; ++i)
        {
            destZ += switches[2][i] * increments[i];
        }
    }

    public boolean isPortalObstructed(int x, int y, int z, ForgeDirection direction)
    {
        if (direction == ForgeDirection.NORTH)
        {
            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 3; ++j)
                {
                    if (!(worldObj.getBlock(x - 1 + j, y + 1 + i, z) == Blocks.air || worldObj.getBlock(x - 1 + j, y + 1 + i, z) == TFBlocks.groundBridgeTeleporter) || !(worldObj.getBlock(x - 2 + i, y + 2 + j, z) == Blocks.air || worldObj.getBlock(x - 2 + i, y + 2 + j, z) == TFBlocks.groundBridgeTeleporter))
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 3; ++j)
                {
                    if (!(worldObj.getBlock(x, y + 1 + i, z - 1 + j) == Blocks.air || worldObj.getBlock(x, y + 1 + i, z - 1 + j) == TFBlocks.groundBridgeTeleporter) || !(worldObj.getBlock(x, y + 2 + j, z - 2 + i) == Blocks.air || worldObj.getBlock(x, y + 2 + j, z - 2 + i) == TFBlocks.groundBridgeTeleporter))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean checkForSpace()
    {
        World world = getDestWorld();
        Block b = Blocks.air;
        Block b1 = TFBlocks.groundBridgeTeleporter;

        if (portalDirection % 2 == 0)
        {
            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 3; ++j)
                {
                    if (!(world.getBlock(destX - 1 + j, destY - 2 + i, destZ) == b || world.getBlock(destX - 1 + j, destY - 2 + i, destZ) == b1) || !(world.getBlock(destX - 2 + i, destY - 1 + j, destZ) == b || world.getBlock(destX - 2 + i, destY - 1 + j, destZ) == b1))
                    {
                        return false;
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 3; ++j)
                {
                    if (!(world.getBlock(destX, destY - 2 + i, destZ - 1 + j) == b || world.getBlock(destX, destY - 2 + i, destZ - 1 + j) == b1) || !(world.getBlock(destX, destY - 1 + j, destZ - 2 + i) == b || world.getBlock(destX, destY - 1 + j, destZ - 2 + i) == b1))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public int getSrcPortalDirection()
    {
        int i = 0;

        if (groundBridgeFramePos != null)
        {
            int x = groundBridgeFramePos.posX;
            int y = groundBridgeFramePos.posY;
            int z = groundBridgeFramePos.posZ;

            if (BlockGroundBridgeFrame.getFrameDirection(worldObj, x, y, z) == ForgeDirection.EAST)
            {
                i = 1;
            }
        }

        return i + srcPortalDirection * 2;
    }

    public WorldServer getDestWorld()
    {
        MinecraftServer server = MinecraftServer.getServer();
        return server.worldServerForDimension(getDestDimensionID());
    }

    public void changeSwitch(int group, int id, int amount)
    {
        if (!activationLeverState)
        {
            if (switches[group][id] + amount <= 10 && switches[group][id] + amount >= -10)
            {
                switches[group][id] += amount;
            }

            calculateCoords();
            markBlockForUpdate();
        }
    }

    public void cycleDimensionID(int amount)
    {
        if (!activationLeverState)
        {
            destDimIndex += amount;
            destDimIndex %= DimensionManager.getStaticDimensionIDs().length;

            if (destDimIndex < 0)
            {
                destDimIndex = DimensionManager.getStaticDimensionIDs().length - 1;
            }

            markBlockForUpdate();
        }
    }

    public boolean hasUpgrade(DataCore dataCore)
    {
        for (int i = 0; i < getSizeInventory(); ++i)
        {
            ItemStack itemstack = getStackInSlot(i);

            if (itemstack != null && itemstack.getItem() == TFItems.dataCore)
            {
                if (DataCore.get(itemstack.getItemDamage()) == dataCore)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public int getDestDimensionID()
    {
        if (!hasUpgrade(DataCore.spaceBridge))
        {
            return 0;
        }

        return DimensionManager.getStaticDimensionIDs()[MathHelper.clamp_int(destDimIndex, 0, DimensionManager.getIDs().length - 1)];
    }

    public void markBlockForUpdate()
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        if (!worldObj.isRemote)
        {
            Ticket ticket = chunkTickets.get(1);

            if (ticket != null)
            {
                NBTTagCompound modData = ticket.getModData();
                boolean updateTicket = true;

                if (destX == modData.getInteger("destX") && destZ == modData.getInteger("destZ") && getDestWorld() == ticket.world)
                {
                    updateTicket = false;
                }

                if (updateTicket)
                {
                    releaseChunk(1);
                    loadChunks();
                }
            }
        }
    }

    @Override
    public String getInventoryName()
    {
        return "gui.control_panel";
    }

    @Override
    public ItemStack[] getItemStacks()
    {
        return inventory;
    }

    @Override
    public void setItemStacks(ItemStack[] itemstacks)
    {
        inventory = itemstacks;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return super.getRenderBoundingBox().addCoord(1, 0.5D, 1);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        animPortalDirection = prevAnimPortalDirection = portalDirection = nbt.getInteger("PortalDirection");
        srcPortalDirection = nbt.getInteger("SrcPortalDirection");
        activationLeverState = nbt.getBoolean("Lever");
        activationLeverCoverState = nbt.getBoolean("LeverCover");
        hasSpace = nbt.getBoolean("HasSpace");
        destX = nbt.getInteger("DestX");
        destY = nbt.getInteger("DestY");
        destZ = nbt.getInteger("DestZ");

        if (nbt.getBoolean("ReadFramePos"))
        {
            groundBridgeFramePos = new ChunkCoordinates(nbt.getInteger("FrameX"), nbt.getInteger("FrameY"), nbt.getInteger("FrameZ"));
        }

        if (nbt.hasKey("Switches"))
        {
            NBTTagList nbttaglist = nbt.getTagList("Switches", 10);

            for (int i = 0; i < nbttaglist.tagCount(); i++)
            {
                NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);

                for (int j = 0; j < 4; ++j)
                {
                    switches[i][j] = nbttagcompound.getInteger("Switch" + (j + 1));
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("PortalDirection", portalDirection);
        nbt.setInteger("SrcPortalDirection", srcPortalDirection);
        nbt.setBoolean("Lever", activationLeverState);
        nbt.setBoolean("LeverCover", activationLeverCoverState);
        nbt.setBoolean("HasSpace", hasSpace);
        nbt.setBoolean("ReadFramePos", groundBridgeFramePos != null);
        nbt.setInteger("DestX", destX);
        nbt.setInteger("DestY", destY);
        nbt.setInteger("DestZ", destZ);

        if (groundBridgeFramePos != null)
        {
            nbt.setInteger("FrameX", groundBridgeFramePos.posX);
            nbt.setInteger("FrameY", groundBridgeFramePos.posY);
            nbt.setInteger("FrameZ", groundBridgeFramePos.posZ);
        }

        NBTTagList nbttaglist = new NBTTagList();

        for (Integer[] aint : switches)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            for (int j = 0; j < aint.length; ++j)
            {
                int value = aint[j];
                nbttagcompound.setInteger("Switch" + (j + 1), value);
            }

            nbttaglist.appendTag(nbttagcompound);
        }

        nbt.setTag("Switches", nbttaglist);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return stack.getItem() == TFItems.dataCore;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        writeToNBT(syncData);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
            releaseChunk(0);
            releaseChunk(1);
        }
    }

    @Override
    public void forceChunks(SubTicket subTicket)
    {
        if (subTicket.getTag().hasKey("destX") && subTicket.getTag().hasKey("destZ"))
        {
            forceChunk(subTicket, 1, new ForcedChunk(getDestWorld(), destX >> 4, destZ >> 4));
        }
        else
        {
            forceChunk(subTicket, 0, ForcedChunk.fromTile(this));
        }
    }

    public void forceChunk(SubTicket subTicket, int index, ForcedChunk chunk)
    {
        releaseChunk(index);
        chunkTickets.set(index, subTicket.owner);
        forcedChunks.set(index, chunk);
        TFChunkManager.forceChunk(subTicket.owner, chunk);
    }
    
    public void loadChunks()
    {
        if (chunkTickets.get(0) == null || chunkTickets.get(1) == null)
        {
            SubTicket subTicket = SubTicket.fromTile(this);
            subTicket.getTag().setInteger("destX", destX);
            subTicket.getTag().setInteger("destZ", destZ);

            loadChunk(SubTicket.fromTile(this), 0, ForcedChunk.fromTile(this));
            loadChunk(subTicket, 1, new ForcedChunk(getDestWorld(), destX >> 4, destZ >> 4));
        }
    }

    public void loadChunk(SubTicket subTicket, int index, ForcedChunk chunk)
    {
        if (chunkTickets.get(index) == null)
        {
            Ticket ticket = TFChunkManager.getTicketForChunk(chunk);

            if (ticket != null)
            {
                forceChunk(subTicket.assign(ticket), index, chunk);
            }
        }
    }

    public void releaseChunk(int index)
    {
        if (chunkTickets.get(index) != null)
        {
            List<SubTicket> list = SubTicket.getChildren(chunkTickets.get(index));
            SubTicket subTicket = null;
            
            for (SubTicket subTicket1 : list)
            {
                if (subTicket1.xCoord == xCoord && subTicket1.yCoord == yCoord && subTicket1.zCoord == zCoord)
                {
                    if (index == 1 && subTicket1.getTag().hasKey("destX") && subTicket1.getTag().hasKey("destZ"))
                    {
                        subTicket = subTicket1;
                        break;
                    }
                    else if (index == 0)
                    {
                        subTicket = subTicket1;
                        break;
                    }
                }
            }
            
            if (subTicket != null)
            {
                TFChunkManager.releaseChunk(SubTicket.fromTile(chunkTickets.get(index), this), forcedChunks.get(index));
                chunkTickets.set(index, null);
                forcedChunks.set(index, null);
            }
        }
    }

//    @Override
//    public boolean canBePowered()
//    {
//        return !BlockGroundBridgeControl.isBlockSideOfPanel(getBlockMetadata());
//    }
//
//    @Override
//    public Vec3 getPowerInputOffset()
//    {
//        Vec3 vec3 = Vec3.createVectorHelper(-0.055F, 0.175F, -0.5F);
//        float pitch = 0;
//        float yaw = getBlockMetadata() * 90 + 180;
//        vec3.rotateAroundX(-pitch * (float)Math.PI / 180.0F);
//        vec3.rotateAroundY(-yaw * (float)Math.PI / 180.0F);
//
//        return vec3;
//    }
}
