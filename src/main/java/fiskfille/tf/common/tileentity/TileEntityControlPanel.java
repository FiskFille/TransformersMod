package fiskfille.tf.common.tileentity;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.network.NetworkRegistry;
import fiskfille.tf.common.block.BlockControlPanel;
import fiskfille.tf.common.block.BlockGroundBridgeFrame;
import fiskfille.tf.common.block.BlockGroundBridgeTeleporter;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.chunk.ForcedChunk;
import fiskfille.tf.common.chunk.SubTicket;
import fiskfille.tf.common.chunk.TFChunkManager;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.groundbridge.GroundBridgeError;
import fiskfille.tf.common.groundbridge.RemoteData;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.MessageUpdateEnergyState;
import fiskfille.tf.common.network.MessageUpdateRemote;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFMathHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TileEntityControlPanel extends TileEntityContainer implements ISidedInventory, IEnergyReceiver, IChunkLoaderTile, IMultiTile
{
    public static final int[][] directions = new int[][] { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public ReceiverHandler receiverHandler = new ReceiverHandler(this);
    public Integer[][] switches = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

    public EnergyStorage storage = new EnergyStorage(64000);

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
    public float lastUsage;

    public ChunkCoordinates groundBridgeFramePos;
    public LinkedList<Ticket> chunkTickets = Lists.newLinkedList(Arrays.asList((Ticket) null, (Ticket) null));
    public LinkedList<ForcedChunk> forcedChunks = Lists.newLinkedList(Arrays.asList((ForcedChunk) null, (ForcedChunk) null));

    public Set<EntityPlayer> controllingPlayers = new HashSet<EntityPlayer>();

    private Integer[] cachedDimensionIDs;
    private RemoteData lastRemoteData;

    private int updates;

    @Override
    public void updateEntity()
    {
        updates++;

        prevActivationLeverTimer = activationLeverTimer;
        prevActivationLeverCoverTimer = activationLeverCoverTimer;
        prevAnimPortalDirection = animPortalDirection;

        if (BlockControlPanel.isBlockLeftSideOfPanel(getBlockMetadata()))
        {
            receiverHandler.onUpdate(worldObj);
            calculateCoords();
            errors.clear();

            if (!worldObj.isRemote)
            {
                loadChunks();

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

                        if (y != destY)
                        {
                            destY = y;
                            world.markBlockForUpdate(xCoord, yCoord, zCoord);
                        }
                    }
                }
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

            if (groundBridgeFramePos == null)
            {
                errors.add(GroundBridgeError.NO_PORTAL_LINKED);
            }

            if (flag)
            {
                errors.add(GroundBridgeError.PORTAL_OBSTRUCTED);
            }

            if (Math.sqrt(getDistanceFrom(destX, destY, destZ)) <= 64 && getDestDimensionID() == worldObj.provider.dimensionId)
            {
                GroundBridgeError.INVALID_COORDS.arguments = new Object[] { 64 };
                errors.add(GroundBridgeError.INVALID_COORDS);
            }

            if (destY < 0 || destY + 5 >= worldObj.getHeight())
            {
                GroundBridgeError.OUT_OF_BOUNDS.arguments = new Object[] { worldObj.getHeight() };
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

            if (extractEnergy(getConsumptionRate(), true) < getConsumptionRate())
            {
                errors.add(GroundBridgeError.NOT_ENOUGH_ENERGY);
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
                if (!worldObj.isRemote)
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


                        extractEnergy(getConsumptionRate(), false);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            int dir = portalDirection == 0 && animPortalDirection > portalDirection ? animPortalDirection == 0 ? 0 : 4 : portalDirection;
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

            if (worldObj.isRemote)
            {
                TFEnergyHelper.applyClientEnergyUsage(this);
            }
            else
            {
                float usage = storage.calculateUsage();

                if (Math.abs(usage - lastUsage) > 0.001F)
                {
                    updateClientEnergy();
                }

                lastUsage = usage;

                if (controllingPlayers.isEmpty())
                {
                    lastRemoteData = null;
                }
                else
                {
                    if (updates % 20 == 0)
                    {
                        Set<EntityPlayer> deadPlayers = new HashSet<EntityPlayer>();

                        for (EntityPlayer player : controllingPlayers)
                        {
                            if (player.isDead || !player.worldObj.playerEntities.contains(player))
                            {
                                deadPlayers.add(player);
                            }
                        }

                        controllingPlayers.removeAll(deadPlayers);
                    }

                    RemoteData remoteData = new RemoteData(this);

                    if (lastRemoteData != null && !lastRemoteData.equals(remoteData))
                    {
                        MessageUpdateRemote message = new MessageUpdateRemote(remoteData, false);

                        for (EntityPlayer player : controllingPlayers)
                        {
                            TFNetworkManager.networkWrapper.sendTo(message, (EntityPlayerMP) player);
                        }
                    }

                    lastRemoteData = remoteData;
                }
            }
        }
    }

    public void calculateCoords()
    {
        destX = xCoord;
        destY = yCoord;
        destZ = zCoord;

        int[] increments = getCoordinateIncrements();

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

    public int[] getCoordinateIncrements()
    {
        List<DataCore> upgrades = getUpgrades();
        int[] increments = { 1, 10, 100, 1000 };

        for (int i = 0; i < upgrades.size(); ++i)
        {
            DataCore dataCore = upgrades.get(i);

            if (dataCore == DataCore.range)
            {
                for (int j = 0; j < increments.length; ++j)
                {
                    increments[j] *= 2;
                }
            }
        }

        return increments;
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
            int[] increments = getCoordinateIncrements();
            int[] aint = { coords.posX, coords.posY, coords.posZ };
            int[] aint1 = { xCoord, yCoord, zCoord };

            switches = new Integer[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

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

    public List<DataCore> getUpgrades()
    {
        List<DataCore> list = Lists.newArrayList();

        for (int i = 0; i < getSizeInventory(); ++i)
        {
            ItemStack itemstack = getStackInSlot(i);

            if (itemstack != null && itemstack.getItem() == TFItems.dataCore)
            {
                list.add(DataCore.get(itemstack.getItemDamage()));
            }
        }

        return list;
    }

    public boolean hasUpgrade(DataCore dataCore)
    {
        return getUpgrades().contains(dataCore);
    }

    public int getDestDimensionID()
    {
        if (!hasUpgrade(DataCore.spaceBridge) || getDimensionIDs().length < 1)
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

    public float getConsumptionRate()
    {
        List<DataCore> upgrades = getUpgrades();
        float base = 10;

        for (int i = 0; i < upgrades.size(); ++i)
        {
            DataCore dataCore = upgrades.get(i);

            if (dataCore == DataCore.range)
            {
                base *= 1.25F;
            }
        }

        if (getDestDimensionID() != worldObj.provider.dimensionId)
        {
            base *= 2;
        }

        return base;
    }

    @Override
    public void markBlockForUpdate()
    {
        super.markBlockForUpdate();

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
        return super.getRenderBoundingBox().addCoord(0, 0.5D, 0).expand(1, 0, 1);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);
        portalDirection = nbt.getInteger("PortalDirection");
        srcPortalDirection = nbt.getInteger("SrcPortalDirection");
        activationLeverState = nbt.getBoolean("Lever");
        activationLeverCoverState = nbt.getBoolean("LeverCover");
        activationLeverTimer = prevActivationLeverTimer = activationLeverState ? 1 : 0;
        hasSpace = nbt.getBoolean("HasSpace");
        destX = nbt.getInteger("DestX");
        destY = nbt.getInteger("DestY");
        destZ = nbt.getInteger("DestZ");
        destDimIndex = nbt.getInteger("DestDimIndex");

        if (nbt.getBoolean("ReadFramePos"))
        {
            groundBridgeFramePos = new ChunkCoordinates(nbt.getInteger("FrameX"), nbt.getInteger("FrameY"), nbt.getInteger("FrameZ"));
        }

        receiverHandler.readFromNBT(nbt);

        if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            storage.readFromNBT(config);
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
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        super.writeCustomNBT(nbt);
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

        receiverHandler.writeToNBT(nbt);

        if (storage.getEnergy() > 0)
        {
            NBTTagCompound config = new NBTTagCompound();
            storage.writeToNBT(config);
            nbt.setTag("ConfigDataTF", config);
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
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public float receiveEnergy(float amount, boolean simulate)
    {
        return storage.add(amount, simulate);
    }

    @Override
    public float extractEnergy(float amount, boolean simulate)
    {
        return storage.remove(amount, simulate);
    }

    @Override
    public float getEnergy()
    {
        return storage.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return storage.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        storage.setUsage(usage);
    }

    @Override
    public ReceiverHandler getReceiverHandler()
    {
        return receiverHandler;
    }

    @Override
    public boolean canReceiveEnergy(TileEntity from)
    {
        return BlockControlPanel.isBlockLeftSideOfPanel(getBlockMetadata());
    }

    @Override
    public Vec3 getEnergyInputOffset()
    {
        float pitch = 0;
        float yaw = (getBlockMetadata() + 2) * 90;
        Vec3 vec3 = Vec3.createVectorHelper(-0.055F, 0.175F, -0.5F);
        vec3.rotateAroundX(-pitch * (float) Math.PI / 180.0F);
        vec3.rotateAroundY(-yaw * (float) Math.PI / 180.0F);

        return vec3;
    }

    @Override
    public int getMapColor()
    {
        return 0x26FF8B;
    }

    @Override
    public void updateClientEnergy()
    {
        NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord + 0.5F, yCoord, zCoord + 0.5F, 128);
        TFNetworkManager.networkWrapper.sendToAllAround(new MessageUpdateEnergyState(this), target);
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
    public int[] getBaseOffsets(int metadata)
    {
        int direction = BlockControlPanel.getDirection(metadata);
        boolean isSide = !BlockControlPanel.isBlockLeftSideOfPanel(metadata);

        return new int[] { -(isSide ? directions[direction][0] : 0), -(BlockControlPanel.isBlockTopOfPanel(metadata) ? 1 : 0), -(isSide ? directions[direction][1] : 0) };
    }
}
