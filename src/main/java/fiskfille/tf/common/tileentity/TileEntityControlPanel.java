package fiskfille.tf.common.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.Lists;

import fiskfille.tf.common.block.BlockControlPanel;
import fiskfille.tf.common.block.BlockGroundBridgeFrame;
import fiskfille.tf.common.block.BlockGroundBridgeTeleporter;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.chunk.ForcedChunk;
import fiskfille.tf.common.chunk.SubTicket;
import fiskfille.tf.common.chunk.TFChunkManager;
import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.data.tile.TileDataControlPanel;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.groundbridge.GroundBridgeError;
import fiskfille.tf.common.groundbridge.GroundBridgeError.ErrorContainer;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.MessageTileTrigger.ITileDataCallback;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFDimensionHelper;
import fiskfille.tf.helper.TFMathHelper;
import fiskfille.tf.helper.TFTileHelper;

public class TileEntityControlPanel extends TileEntityMachineContainer implements ISidedInventory, IEnergyReceiver, ITileDataCallback, IChunkLoaderTile, IMultiTile
{
    public static final int[][] directions = new int[][] { {-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public ReceiverHandler receiverHandler = new ReceiverHandler(this);
    public Integer[][] switches = { {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    public TileDataControlPanel data = new TileDataControlPanel();
    private ItemStack[] inventory = new ItemStack[3];

    public float animPortalDirection;
    public float prevAnimPortalDirection;
    public boolean activationLeverCoverState = false;
    public float activationLeverTimer;
    public float prevActivationLeverTimer;
    public float activationLeverCoverTimer;
    public float prevActivationLeverCoverTimer;

    public boolean hasSpace;
    public float lastUsage;
    public int destDimIndex = 1;

    public LinkedList<Ticket> chunkTickets = Lists.newLinkedList(Arrays.asList((Ticket) null, (Ticket) null));
    public LinkedList<ForcedChunk> forcedChunks = Lists.newLinkedList(Arrays.asList((ForcedChunk) null, (ForcedChunk) null));

    @Override
    public void updateEntity()
    {
        if (BlockControlPanel.isBlockLeftSideOfPanel(getBlockMetadata()))
        {
            if (!data.isInitialized())
            {
                data.initialize(this);
            }

            if (!data.activationLeverState)
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

                for (TileEntity tile : list)
                {
                    if (Math.sqrt(getDistanceFrom(tile.xCoord, tile.yCoord, tile.zCoord)) <= 20)
                    {
                        if (tile instanceof TileEntityGroundBridgeFrame)
                        {
                            ForgeDirection direction = BlockGroundBridgeFrame.getFrameDirection(worldObj, tile.xCoord, tile.yCoord, tile.zCoord);

                            if (direction != null)
                            {
                                data.framePos = new DimensionalCoords(tile.xCoord, tile.yCoord, tile.zCoord, worldObj.provider.dimensionId);
                                break;
                            }
                        }
                    }
                }
            }

            if (data.framePos != null)
            {
                int x = data.framePos.posX;
                int y = data.framePos.posY;
                int z = data.framePos.posZ;

                if (BlockGroundBridgeFrame.getFrameDirection(worldObj, x, y, z) == null)
                {
                    data.framePos = null;
                }
                else if (!data.activationLeverState)
                {
                    int dir = worldObj.getBlockMetadata(x, y, z) * 2;

                    if (BlockGroundBridgeFrame.getFrameDirection(worldObj, x, y, z) == ForgeDirection.EAST)
                    {
                        dir += 1;
                    }

                    data.frameDirection = dir;
                }
            }

            if (!worldObj.isRemote)
            {
                data.errors.clear();
                data.upgrades.clear();
                List<DataCore> upgrades = Lists.newArrayList();

                for (int i = 0; i < getSizeInventory(); ++i)
                {
                    ItemStack itemstack = getStackInSlot(i);

                    if (itemstack != null && itemstack.getItem() == TFItems.dataCore)
                    {
                        upgrades.add(DataCore.get(itemstack.getItemDamage()));
                    }
                }

                data.upgrades.addAll(upgrades);

                calculateCoords();
                loadChunks();

                if (data.framePos != null)
                {
                    int x = data.framePos.posX;
                    int y = data.framePos.posY;
                    int z = data.framePos.posZ;

                    ForgeDirection direction = BlockGroundBridgeFrame.getFrameDirection(worldObj, x, y, z);

                    if (direction == null || isPortalObstructed(x, y, z, direction))
                    {
                        data.errors.add(new ErrorContainer(GroundBridgeError.PORTAL_OBSTRUCTED));
                    }
                }
                else
                {
                    data.errors.add(new ErrorContainer(GroundBridgeError.NO_PORTAL_LINKED));
                }

                int destX = data.destination.posX;
                int destY = data.modifiedDestY;
                int destZ = data.destination.posZ;

                if (!TFConfig.groundBridgeMinRange || Math.sqrt(getDistanceFrom(destX, destY, destZ)) <= 64 && getDestDimensionID() == worldObj.provider.dimensionId)
                {
                    data.errors.add(new ErrorContainer(GroundBridgeError.INVALID_COORDS, 64));
                }

                if (destY < 0 || destY + 5 >= getDestWorld().getHeight())
                {
                    data.errors.add(new ErrorContainer(GroundBridgeError.OUT_OF_BOUNDS, getDestWorld().getHeight()));
                }

                if (!(hasSpace = checkForSpace(getDestWorld(), destX, destY, destZ)))
                {
                    data.errors.add(new ErrorContainer(GroundBridgeError.NOT_ENOUGH_SPACE));
                }

                if (extractEnergy(getConsumptionRate(), true) < getConsumptionRate())
                {
                    data.errors.add(new ErrorContainer(GroundBridgeError.NOT_ENOUGH_ENERGY));
                }

                if (data.errors.isEmpty() && data.activationLeverState && data.framePos != null)
                {
                    try
                    {
                        int x = data.framePos.posX;
                        int y = data.framePos.posY;
                        int z = data.framePos.posZ;
                        BlockGroundBridgeTeleporter.spawnTeleporter(worldObj, x, y, z, this);

                        if (data.direction % 2 == 0)
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

                data.serverTick();
            }
            else
            {
                prevActivationLeverTimer = activationLeverTimer;
                prevActivationLeverCoverTimer = activationLeverCoverTimer;
                prevAnimPortalDirection = animPortalDirection;

                activationLeverCoverState = data.activationLeverState || data.errors.isEmpty();

                if (data.activationLeverState)
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

                int dir = data.direction == 0 && animPortalDirection > data.direction ? animPortalDirection == 0 ? 0 : 4 : data.direction;
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

            TileData prevData = TFTileHelper.getTileData(new DimensionalCoords(this));

            if (prevData instanceof TileDataControlPanel)
            {
                data = new TileDataControlPanel((TileDataControlPanel) prevData);
            }
        }
    }

    private void calculateCoords()
    {
        data.destination.set(this);

        int[] increments = getCoordinateIncrements();
        int[] coordArray = data.destination.toArray();

        for (int i = 0; i < switches.length; ++i)
        {
            for (int j = 0; j < switches[i].length; ++j)
            {
                coordArray[i] += switches[i][j] * increments[j];
            }
        }

        data.destination.set(coordArray);
        data.destination.dimension = getDestDimensionID();

        if (!worldObj.isRemote)
        {
            World world = getDestWorld();

            if (world != null)
            {
                int x = data.destination.posX;
                int y = data.destination.posY;
                int z = data.destination.posZ;

                if (hasUpgrade(DataCore.leveler) && !data.activationLeverState)
                {
                    while (y > 0 && checkForSpace(world, x, y - 1, z))
                    {
                        --y;
                    }
                }

                data.modifiedDestY = y;
            }
        }
    }

    private int[] getCoordinateIncrements()
    {
        int[] increments = {1, 10, 100, 1000};

        if (hasUpgrade(DataCore.range))
        {
            for (int i = 0; i < data.upgrades.size(); ++i)
            {
                DataCore dataCore = data.upgrades.get(i);

                if (dataCore == DataCore.range)
                {
                    for (int j = 0; j < increments.length; ++j)
                    {
                        increments[j] *= 2;
                    }
                }
            }
        }

        return increments;
    }

    private boolean isPortalObstructed(int x, int y, int z, ForgeDirection direction)
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

    private boolean checkForSpace(World world, int x, int y, int z)
    {
        Block b = Blocks.air;
        Block b1 = TFBlocks.groundBridgeTeleporter;

        if (data.direction % 2 == 0)
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

    private WorldServer getDestWorld()
    {
        MinecraftServer server = MinecraftServer.getServer();
        return server.worldServerForDimension(getDestDimensionID());
    }

    public void changeSwitch(int group, int id, int amount)
    {
        if (!data.activationLeverState)
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
        if (!data.activationLeverState)
        {
            int[] increments = getCoordinateIncrements();
            int[] aint = {coords.posX, coords.posY, coords.posZ};
            int[] aint1 = {xCoord, yCoord, zCoord};

            switches = new Integer[][] { {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

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

            for (int i = 0; i < TFDimensionHelper.dimensionIDs.length; ++i)
            {
                int id = TFDimensionHelper.dimensionIDs[i];

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
        if (!data.activationLeverState)
        {
            destDimIndex += amount;

            if (destDimIndex < 0)
            {
                destDimIndex = TFDimensionHelper.dimensionIDs.length - 1;
            }
            else if (destDimIndex >= TFDimensionHelper.dimensionIDs.length)
            {
                destDimIndex = 0;
            }

            calculateCoords();
            markBlockForUpdate();
        }
    }

    public List<DataCore> getUpgrades()
    {
        return data.upgrades;
    }

    public boolean hasUpgrade(DataCore dataCore)
    {
        return data.hasUpgrade(dataCore);
    }

    private int getDestDimensionID()
    {
        if (!hasUpgrade(DataCore.spaceBridge) || TFDimensionHelper.dimensionIDs.length < 2)
        {
            return worldObj.provider.dimensionId;
        }

        return TFDimensionHelper.dimensionIDs[MathHelper.clamp_int(destDimIndex, 0, TFDimensionHelper.dimensionIDs.length - 1)];
    }

    public float getConsumptionRate()
    {
        float base = 10;

        for (int i = 0; i < data.upgrades.size(); ++i)
        {
            DataCore dataCore = data.upgrades.get(i);

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
//        super.markBlockForUpdate(); TODO: Uncomment, or remove...?

        if (!worldObj.isRemote)
        {
            Ticket ticket = chunkTickets.get(1);

            if (ticket != null)
            {
                SubTicket subTicket = getSubTicket(ticket, 1);

                if (subTicket != null)
                {
                    NBTTagCompound nbt = subTicket.getTag();
                    boolean updateTicket = true;

                    if (data.destination.posX == nbt.getInteger("destX") && data.destination.posZ == nbt.getInteger("destZ") && getDestWorld() == subTicket.owner.world)
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
        data.direction = nbt.getInteger("PortalDirection");
        data.frameDirection = nbt.getInteger("SrcPortalDirection");
        data.activationLeverState = nbt.getBoolean("Lever");
        activationLeverCoverState = nbt.getBoolean("LeverCover");
        activationLeverTimer = prevActivationLeverTimer = data.activationLeverState ? 1 : 0;
        hasSpace = nbt.getBoolean("HasSpace");
        destDimIndex = nbt.getInteger("DestDimIndex");

        if (nbt.getBoolean("ReadFramePos"))
        {
            data.framePos = new DimensionalCoords(nbt.getInteger("FrameX"), nbt.getInteger("FrameY"), nbt.getInteger("FrameZ"), nbt.getInteger("FrameDim"));
        }

        if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.storage.readFromNBT(config);
        }

        if (nbt.hasKey("Switches"))
        {
            NBTTagList nbttaglist = nbt.getTagList("Switches", NBT.TAG_COMPOUND);

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
        nbt.setInteger("PortalDirection", data.direction);
        nbt.setInteger("SrcPortalDirection", data.frameDirection);
        nbt.setBoolean("Lever", data.activationLeverState);
        nbt.setBoolean("LeverCover", activationLeverCoverState);
        nbt.setBoolean("HasSpace", hasSpace);
        nbt.setBoolean("ReadFramePos", data.framePos != null);
        nbt.setInteger("DestDimIndex", destDimIndex);

        if (data.framePos != null)
        {
            nbt.setInteger("FrameX", data.framePos.posX);
            nbt.setInteger("FrameY", data.framePos.posY);
            nbt.setInteger("FrameZ", data.framePos.posZ);
            nbt.setInteger("FrameDim", data.framePos.dimension);
        }

        if (data.storage.getEnergy() > 0)
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.storage.writeToNBT(config);
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
        return super.isUseableByPlayer(player) || player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote;
    }

    @Override
    public float receiveEnergy(float amount, boolean simulate)
    {
        return data.storage.add(amount, simulate);
    }

    @Override
    public float extractEnergy(float amount, boolean simulate)
    {
        return data.storage.remove(amount, simulate);
    }

    @Override
    public float getEnergy()
    {
        return data.storage.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return data.storage.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return data.storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return data.storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        data.storage.setUsage(usage);
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
        float yaw = (getBlockMetadata() + 2) * 90;
        Vec3 vec3 = Vec3.createVectorHelper(-0.055F, 0.175F, -0.5F);
        vec3.rotateAroundY(-yaw * (float) Math.PI / 180.0F);

        return vec3;
    }

    @Override
    public int getMapColor()
    {
        return 0x26FF8B;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
            data.kill();

            releaseChunk(0);
            releaseChunk(1);
        }
    }

    @Override
    public void forceChunks(SubTicket subTicket)
    {
        if (subTicket.getTag().hasKey("destX") && subTicket.getTag().hasKey("destZ"))
        {
            forceChunk(subTicket, 1, new ForcedChunk(getDestWorld(), data.destination.posX >> 4, data.destination.posZ >> 4));
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
            subTicket.getTag().setInteger("destX", data.destination.posX);
            subTicket.getTag().setInteger("destZ", data.destination.posZ);

            loadChunk(SubTicket.fromTile(this), 0, ForcedChunk.fromTile(this));
            loadChunk(subTicket, 1, new ForcedChunk(getDestWorld(), data.destination.posX >> 4, data.destination.posZ >> 4));
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

        return new int[] {-(isSide ? directions[direction][0] : 0), -(BlockControlPanel.isBlockTopOfPanel(metadata) ? 1 : 0), -(isSide ? directions[direction][1] : 0)};
    }

    @Override
    public void receive(EntityPlayer player, int action)
    {
        super.receive(player, action);
        
        if (player != null)
        {
            if (isUseableByPlayer(player) || player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote)
            {
                int increment = player.isSneaking() ? -1 : 1;

                if (action >= 1 && action <= 4)
                {
                    changeSwitch(0, action - 1, increment);
                }
                else if (action >= 5 && action <= 8)
                {
                    changeSwitch(1, action - 5, increment);
                }
                else if (action >= 9 && action <= 12)
                {
                    changeSwitch(2, action - 9, increment);
                }
                else if (action == 13)
                {
                    if (!player.worldObj.isRemote)
                    {
                        data.direction = (data.direction + 1) % 4;
                    }
                }
                else if (action == 14)
                {
                    if (!player.worldObj.isRemote)
                    {
                        data.activationLeverState = !data.activationLeverState;
                    }
                }
                else if (action >= 15 && action <= 17)
                {
                    if (!data.activationLeverState)
                    {
                        int slot = action - 15;

                        if (getStackInSlot(slot) == null && player.getHeldItem() != null && isItemValidForSlot(slot, player.getHeldItem()))
                        {
                            setInventorySlotContents(slot, player.getHeldItem());
                            player.setCurrentItemOrArmor(0, null);
                        }
                        else if (getStackInSlot(slot) != null && (player.getHeldItem() == null || isItemValidForSlot(slot, player.getHeldItem())))
                        {
                            ItemStack itemstack = getStackInSlot(slot).copy();

                            setInventorySlotContents(slot, player.getHeldItem());
                            player.setCurrentItemOrArmor(0, itemstack);
                        }

                        markBlockForUpdate();
                    }
                }
                else if (action == 18 || action == 19)
                {
                    if (hasUpgrade(DataCore.spaceBridge))
                    {
                        cycleDimensionID(action == 18 ? -1 : 1);
                    }
                }
            }
        }
    }
}
