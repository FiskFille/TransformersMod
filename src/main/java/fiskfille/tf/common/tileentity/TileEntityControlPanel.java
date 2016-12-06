package fiskfille.tf.common.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
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

import fiskfille.tf.common.block.BlockControlPanel;
import fiskfille.tf.common.block.BlockGroundBridgeFrame;
import fiskfille.tf.common.block.BlockGroundBridgeTeleporter;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.chunk.ForcedChunk;
import fiskfille.tf.common.chunk.SubTicket;
import fiskfille.tf.common.chunk.TFChunkManager;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.groundbridge.GroundBridgeError;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.helper.TFMathHelper;

public class TileEntityControlPanel extends TileEntityContainer implements ISidedInventory, IChunkLoaderTile, IMultiTile/* IEnergonPowered*/ // TODO
{
    public Integer[][] switches = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    private ItemStack[] inventory = new ItemStack[3];

    public int destX;
    public int destY;
    public int prevDestY;
    public int destZ;
    public int destDimIndex = 1;

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
    public LinkedList<Ticket> chunkTickets = Lists.newLinkedList(Arrays.asList((Ticket) null, (Ticket) null));
    public LinkedList<ForcedChunk> forcedChunks = Lists.newLinkedList(Arrays.asList((ForcedChunk) null, (ForcedChunk) null));

    private Integer[] cachedDimensionIDs;

    private int ticks;

    @Override
    public void updateEntity()
    {
        ticks++;

        prevActivationLeverTimer = activationLeverTimer;
        prevActivationLeverCoverTimer = activationLeverCoverTimer;
        prevAnimPortalDirection = animPortalDirection;

        if (BlockControlPanel.isBlockLeftSideOfPanel(getBlockMetadata()))
        {
            calculateCoords();
            errors.clear();

            if (hasUpgrade(DataCore.leveler))
            {
                World world = getDestWorld();

                if (world != null)
                {
                    int x = destX;
                    int y = destY;
                    int z = destZ;

                    while (y > 0 && checkForSpace(world, x, y - 1, z))
                    {
                        --y;
                    }

                    destY = y;
                }
            }

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
                if (ticks % 20 == 0)
                {
                    List<TileEntity> list = new ArrayList<TileEntity>(worldObj.loadedTileEntityList);
                    Collections.sort(list, new Comparator<TileEntity>()
                            {
                        @Override
                        public int compare(TileEntity tile1, TileEntity tile2)
                        {
                            return Double.valueOf(Math.sqrt(getDistanceFrom(tile1.xCoord, tile1.zCoord, tile1.yCoord))).compareTo(Math.sqrt(getDistanceFrom(tile2.xCoord, tile2.zCoord, tile2.yCoord)));
                        }
                            });

                    for (TileEntity tileentity : list)
                    {
                        if (Math.sqrt(getDistanceFrom(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)) <= 20)
                        {
                            if (tileentity instanceof TileEntityGroundBridgeFrame)
                            {
                                TileEntityGroundBridgeFrame tile = (TileEntityGroundBridgeFrame) tileentity;
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

            if (destY < 0 || destY + 5 >= worldObj.getHeight()) // TODO
            {
                GroundBridgeError.OUT_OF_BOUNDS.arguments = new Object[] {getWorldObj().getHeight()};
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
                try
                {
                    int x = groundBridgeFramePos.posX;
                    int y = groundBridgeFramePos.posY;
                    int z = groundBridgeFramePos.posZ;
                    BlockGroundBridgeTeleporter.spawnTeleporter(worldObj, x, y, z, this);

                    if (portalDirection % 2 == 0)
                    {
                        BlockGroundBridgeTeleporter.fillNorthFacingFrame(getDestWorld(), destX, destY - 1, destZ, TFBlocks.groundBridgeTeleporter, this, true);
                    }
                    else
                    {
                        BlockGroundBridgeTeleporter.fillEastFacingFrame(getDestWorld(), destX, destY - 1, destZ, TFBlocks.groundBridgeTeleporter, this, true);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
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

            animPortalDirection = (float) Math.round(animPortalDirection * 1000) / 1000;

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

        prevDestY = destY;
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
        return checkForSpace(getDestWorld(), destX, destY, destZ);
    }

    public boolean checkForSpace(World world, int x, int y, int z)
    {
        Block b = Blocks.air;
        Block b1 = TFBlocks.groundBridgeTeleporter;

        if (portalDirection % 2 == 0)
        {
            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 3; ++j)
                {
                    if (!(world.getBlock(x - 1 + j, y + i, z) == b || world.getBlock(x - 1 + j, y + i, z) == b1) || !(world.getBlock(x - 2 + i, y + 1 + j, z) == b || world.getBlock(x - 2 + i, y + 1 + j, z) == b1))
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
                    if (!(world.getBlock(x, y + i, z - 1 + j) == b || world.getBlock(x, y + i, z - 1 + j) == b1) || !(world.getBlock(x, y + 1 + j, z - 2 + i) == b || world.getBlock(x, y + 1 + j, z - 2 + i) == b1))
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

    public void setSwitchesTo(DimensionalCoords coords)
    {
        if (!activationLeverState)
        {
            int[] increments = {1, 10, 100, 1000};
            int[] aint = {coords.posX, coords.posY, coords.posZ};
            int[] aint1 = {xCoord, yCoord, zCoord};

            switches = new Integer[][] {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

            for (int i = 0; i < aint.length; ++i)
            {
                int[] aint2 = TFMathHelper.split(aint[i] - aint1[i], increments.length);
                int[] aint3 = new int[increments.length];

                for (int index = aint2.length - 1; index >= 0; --index)
                {
                    int num = aint2[index];

                    if (index > 0)
                    {
                        int nextNum = aint2[index - 1] / increments[index - 1];

                        if (nextNum == 0)
                        {
                            num -= increments[index];
                            aint2[index - 1] += increments[index];
                        }
                    }

                    aint3[index] = num;
                }

                for (int j = 0; j < aint3.length; ++j)
                {
                    switches[i][j] = MathHelper.clamp_int(aint3[j] / increments[j], -10, 10);
                }
            }

            for (int i = 0; i < getDimensionIDs().length; ++i)
            {
                int id = getDimensionIDs()[i];

                if (coords.dimension == id)
                {
                    destDimIndex = i;
                    break;
                }
            }

            calculateCoords();
        }
    }

    public void cycleDimensionID(int amount)
    {
        if (!activationLeverState)
        {
            destDimIndex += amount;
            destDimIndex %= getDimensionIDs().length;

            if (destDimIndex < 0)
            {
                destDimIndex = getDimensionIDs().length - 1;
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
            return worldObj.provider.dimensionId;
        }

        return getDimensionIDs()[MathHelper.clamp_int(destDimIndex, 0, getDimensionIDs().length - 1)];
    }

    private Integer[] getDimensionIDs()
    {
        if (cachedDimensionIDs == null)
        {
            Integer[] aint = DimensionManager.getIDs();
            List<Integer> list = Lists.newArrayList();

            for (int i = 0; i < aint.length; ++i)
            {
                if (DimensionManager.shouldLoadSpawn(aint[i]))
                {
                    list.add(aint[i]);
                }
            }

            Collections.sort(list, new Comparator<Integer>()
                    {
                @Override
                public int compare(Integer arg0, Integer arg1)
                {
                    return Double.valueOf(arg0).compareTo(Double.valueOf(arg1));
                }
                    });

            cachedDimensionIDs = list.toArray(new Integer[list.size()]);
        }

        return cachedDimensionIDs;
    }

    public void markBlockForUpdate()
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        if (!worldObj.isRemote)
        {
            Ticket ticket = chunkTickets.get(1);

            if (ticket != null)
            {
                SubTicket subTicket = getSubTicket(ticket, 1);

                if (subTicket != null)
                {
                    NBTTagCompound data = subTicket.getTag();
                    boolean updateTicket = true;

                    if (destX == data.getInteger("destX") && destZ == data.getInteger("destZ") && getDestWorld() == subTicket.owner.world)
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
        activationLeverTimer = prevActivationLeverTimer = activationLeverState ? 1 : 0;
        activationLeverCoverTimer = prevActivationLeverCoverTimer = activationLeverCoverState ? 1 : 0;
        hasSpace = nbt.getBoolean("HasSpace");
        destX = nbt.getInteger("DestX");
        destY = nbt.getInteger("DestY");
        destZ = nbt.getInteger("DestZ");
        destDimIndex = nbt.getInteger("DestDimIndex");

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
        nbt.setInteger("DestDimIndex", destDimIndex);

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
    public boolean canExtractItem(int slot, ItemStack itemstack, int side)
    {
        return false;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side)
    {
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return new int[] {};
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
            SubTicket subTicket = getSubTicket(chunkTickets.get(index), index);

            if (subTicket != null)
            {
                TFChunkManager.releaseChunk(subTicket, forcedChunks.get(index));
                chunkTickets.set(index, null);
                forcedChunks.set(index, null);
            }
        }
    }

    public SubTicket getSubTicket(Ticket ticket, int index)
    {
        List<SubTicket> list = SubTicket.getChildren(chunkTickets.get(index));

        for (SubTicket subTicket : list)
        {
            if (subTicket.xCoord == xCoord && subTicket.yCoord == yCoord && subTicket.zCoord == zCoord)
            {
                if (index == 1 && subTicket.getTag().hasKey("destX") && subTicket.getTag().hasKey("destZ"))
                {
                    return subTicket;
                }
                else if (index == 0)
                {
                    return subTicket;
                }
            }
        }

        return null;
    }

    @Override
    public int[] getBaseOffsets()
    {
        int direction = BlockControlPanel.getDirection(getBlockMetadata());
        boolean isSide = !BlockControlPanel.isBlockLeftSideOfPanel(getBlockMetadata());
        boolean isTop = BlockControlPanel.isBlockTopOfPanel(getBlockMetadata());

        return new int[] {-(isSide ? BlockControlPanel.directions[direction][0] : 0), -(isTop ? 1 : 0), -(isSide ? BlockControlPanel.directions[direction][1] : 0)};
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
