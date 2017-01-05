package fiskfille.tf.helper;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.network.MessageOpenGui;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.IMultiTile;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;

/**
 * @author FiskFille, gegy1000
 */
public class TFHelper
{
    /**
     * @returns whether the player is wearing the 'Cloudtrap' set.
     */
    public static boolean isPlayerCloudtrap(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerCloudtrap;
    }

    /**
     * @returns whether the player is wearing the 'Skystrike' set.
     */
    public static boolean isPlayerSkystrike(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerSkystrike;
    }

    /**
     * @returns whether the player is wearing the 'Purge' set.
     */
    public static boolean isPlayerPurge(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerPurge;
    }

    /**
     * @returns whether the player is wearing the 'Vurp' set.
     */
    public static boolean isPlayerVurp(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerVurp;
    }

    /**
     * @returns whether the player is wearing the 'Subwoofer' set.
     */
    public static boolean isPlayerSubwoofer(EntityPlayer player)
    {
        return getTransformer(player) instanceof TransformerSubwoofer;
    }

    /**
     * @returns whether the player is wearing a full Transformer set.
     */
    public static boolean isPlayerTransformer(EntityPlayer player)
    {
        Transformer helmetTransformer = getTransformerFromArmor(player, 3);
        Transformer chestTransformer = getTransformerFromArmor(player, 2);
        Transformer legsTransformer = getTransformerFromArmor(player, 1);
        Transformer feetTransformer = getTransformerFromArmor(player, 0);

        return helmetTransformer != null && helmetTransformer == chestTransformer && chestTransformer == legsTransformer && legsTransformer == feetTransformer;
    }

    /**
     * @returns the Transformer that the player currently has fully equipped, null when not wearing a full set.
     */
    public static Transformer getTransformer(EntityPlayer player)
    {
        if (player != null && isPlayerTransformer(player))
        {
            return getTransformerFromArmor(player, 0);
        }

        return null;
    }

    /**
     * @returns the Transformer that the player is wearing in the specified slot.
     */
    public static Transformer getTransformerFromArmor(EntityPlayer player, int slot)
    {
        ItemStack currentArmorStack = player.getCurrentArmor(slot);

        if (currentArmorStack != null)
        {
            Item currentArmor = currentArmorStack.getItem();

            if (currentArmor instanceof ItemTransformerArmor)
            {
                return ((ItemTransformerArmor) currentArmor).getTransformer();
            }
        }

        return null;
    }

    /**
     * @returns the Transformer for the specific armor ItemStack.
     */
    public static Transformer getTransformerFromArmor(ItemStack itemstack)
    {
        if (itemstack != null)
        {
            Item currentArmor = itemstack.getItem();

            if (currentArmor instanceof ItemTransformerArmor)
            {
                return ((ItemTransformerArmor) currentArmor).getTransformer();
            }
        }

        return null;
    }

    public static boolean isFullyTransformed(EntityPlayer player)
    {
        return isPlayerTransformer(player) && getTransformationTimer(player) == 1;
    }

    public static boolean isInRobotMode(EntityPlayer player)
    {
        return isPlayerTransformer(player) && getTransformationTimer(player) == 0;
    }

    public static boolean isInStealthMode(EntityPlayer player)
    {
        Transformer transformer = TFHelper.getTransformer(player);
        int altMode = TFData.ALT_MODE.get(player);

        if (altMode == -1)
        {
            altMode = TFData.PREV_ALT_MODE.get(player);
        }

        return transformer != null && transformer.hasStealthForce(player, altMode) && altMode != -1 && getStealthModeTimer(player) > 0;
    }

    public static float getTransformationTimer(EntityPlayer player)
    {
        return median(TFData.TRANSFORM_PROGRESS.get(player), TFData.PREV_TRANSFORM_PROGRESS.get(player), TransformersMod.proxy.getRenderTick());
    }

    public static float getStealthModeTimer(EntityPlayer player)
    {
        return median(TFData.STEALTH_FORCE_PROGRESS.get(player), TFData.PREV_STEALTH_FORCE_PROGRESS.get(player), TransformersMod.proxy.getRenderTick());
    }

    public static void replaceBlock(World world, int x, int y, int z, Block block, Block replacement)
    {
        if (world.getBlock(x, y, z) == block)
        {
            world.setBlock(x, y, z, replacement);
        }
    }

    public static AxisAlignedBB wrapAroundAABB(AxisAlignedBB aabb, AxisAlignedBB aabb1)
    {
        double d0 = Math.min(aabb.minX, aabb1.minX);
        double d1 = Math.min(aabb.minY, aabb1.minY);
        double d2 = Math.min(aabb.minZ, aabb1.minZ);
        double d3 = Math.max(aabb.maxX, aabb1.maxX);
        double d4 = Math.max(aabb.maxY, aabb1.maxY);
        double d5 = Math.max(aabb.maxZ, aabb1.maxZ);
        return AxisAlignedBB.getBoundingBox(d0, d1, d2, d3, d4, d5);
    }

    public static void applyClientFluidUsage(IFluidHandlerTF tankContainer)
    {
        FluidTankTF tank = tankContainer.getTank();
        FluidStack fluidStack = tank.getFluid();

        int usage = tank.getUsage();

        if (fluidStack != null)
        {
            fluidStack.amount += usage;

            if (fluidStack.amount < 0)
            {
                fluidStack.amount = 0;
            }
            else if (fluidStack.amount > tank.getCapacity())
            {
                fluidStack.amount = tank.getCapacity();
            }
        }
    }

    public static void travelToDimension(Entity entity, int dimension, Teleporter teleporter)
    {
        if (!entity.worldObj.isRemote && !entity.isDead)
        {
            entity.worldObj.theProfiler.startSection("changeDimension");
            MinecraftServer server = MinecraftServer.getServer();
            int j = entity.dimension;
            WorldServer worldserver = server.worldServerForDimension(j);
            WorldServer worldserver1 = server.worldServerForDimension(dimension);
            entity.dimension = dimension;

            if (j == 1 && dimension == 1)
            {
                worldserver1 = server.worldServerForDimension(0);
                entity.dimension = 0;
            }

            entity.worldObj.removeEntity(entity);
            entity.isDead = false;
            entity.worldObj.theProfiler.startSection("reposition");
            server.getConfigurationManager().transferEntityToWorld(entity, j, worldserver, worldserver1, teleporter);
            entity.worldObj.theProfiler.endStartSection("reloading");
            Entity entity1 = EntityList.createEntityByName(EntityList.getEntityString(entity), worldserver1);

            if (entity1 != null)
            {
                entity1.copyDataFrom(entity, true);

                if (j == 1 && dimension == 1)
                {
                    ChunkCoordinates chunkcoordinates = worldserver1.getSpawnPoint();
                    chunkcoordinates.posY = entity.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
                    entity1.setLocationAndAngles(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, entity1.rotationYaw, entity1.rotationPitch);
                }

                worldserver1.spawnEntityInWorld(entity1);
            }

            entity.isDead = true;
            entity.worldObj.theProfiler.endSection();
            worldserver.resetUpdateEntityTick();
            worldserver1.resetUpdateEntityTick();
            entity.worldObj.theProfiler.endSection();
        }
    }

    public static String getDimensionName(int id)
    {
        WorldProvider provider = DimensionManager.getProvider(id);

        if (provider != null)
        {
            return provider.getDimensionName();
        }

        return "Unknown";
    }

    /**
     * A world-sensitive version of {@link FMLNetworkHandler#openGui()}
     */
    public static void openGui(EntityPlayer player, Object mod, int modGuiId, World world, int x, int y, int z)
    {
        ModContainer mc = FMLCommonHandler.instance().findContainerFor(mod);

        if (player instanceof EntityPlayerMP)
        {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            Container remoteGuiContainer = NetworkRegistry.INSTANCE.getRemoteGuiContainer(mc, playerMP, modGuiId, world, x, y, z);

            if (remoteGuiContainer != null)
            {
                playerMP.getNextWindowId();
                playerMP.closeContainer();
                int windowId = playerMP.currentWindowId;

                TFNetworkManager.networkWrapper.sendTo(new MessageOpenGui(playerMP, modGuiId, x, y, z, world.provider.dimensionId), playerMP);

                playerMP.openContainer = remoteGuiContainer;
                playerMP.openContainer.windowId = windowId;
                playerMP.openContainer.addCraftingToCrafters(playerMP);
            }
        }
        else if (FMLCommonHandler.instance().getSide().equals(Side.CLIENT))
        {
            Object guiContainer = NetworkRegistry.INSTANCE.getLocalGuiContainer(mc, player, modGuiId, world, x, y, z);
            FMLCommonHandler.instance().showGuiScreen(guiContainer);
        }
        else
        {
            FMLLog.fine("Invalid attempt to open a local GUI on a dedicated server. This is likely a bug. GUIID: %s,%d", mc.getModId(), modGuiId);
        }
    }

    public static int[] getTileBaseOffsets(TileEntity tile, int metadata)
    {
        if (tile instanceof IMultiTile)
        {
            return ((IMultiTile) tile).getBaseOffsets(metadata);
        }

        return new int[] {0, 0, 0};
    }

    public static <T extends TileEntity> T getTileBase(T tile)
    {
        int[] offsets = getTileBaseOffsets(tile, tile != null ? tile.getBlockMetadata() : 0);

        if (offsets[0] != 0 || offsets[1] != 0 || offsets[2] != 0)
        {
            return (T) tile.getWorldObj().getTileEntity(tile.xCoord + offsets[0], tile.yCoord + offsets[1], tile.zCoord + offsets[2]);
        }

        return tile;
    }

    public static float median(float curr, float prev, float partialTicks)
    {
        return prev + (curr - prev) * partialTicks;
    }

    public static double median(double curr, double prev, float partialTicks)
    {
        return prev + (curr - prev) * partialTicks;
    }

    public static float getWidth(EntityPlayer player)
    {
        return 0.6F;
    }

    public static float getHeight(EntityPlayer player)
    {
        return 1.8F + getCameraYOffset(player);
    }

    public static float getScale(EntityPlayer player)
    {
        return 1;
    }

    public static float getCameraYOffset(EntityPlayer player)
    {
        Transformer transformer = TFHelper.getTransformer(player);

        if (transformer != null)
        {
            int altMode = TFData.ALT_MODE.get(player);

            return TFHelper.median(transformer.getVehicleHeightOffset(player, altMode), transformer.getHeightOffset(player, altMode), TFHelper.getTransformationTimer(player));
        }

        return 0;
    }

    public static boolean shouldOverrideScale(EntityPlayer player)
    {
        if (getTransformer(player) == null && TFData.PREV_TRANSFORMER.get(player) != null)
        {
            return true;
        }

        return !player.isEntityAlive() || (getTransformer(player) != null || TFData.PREV_TRANSFORMER.get(player) != null) && (getHeight(player) != player.height || getWidth(player) != player.width);
    }
}
