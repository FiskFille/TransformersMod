package fiskfille.tf.client.gui;

import java.lang.reflect.Constructor;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.network.IGuiHandler;
import fiskfille.tf.TFLog;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.ContainerAlloyCrucible;
import fiskfille.tf.common.container.ContainerAssemblyTable;
import fiskfille.tf.common.container.ContainerColumn;
import fiskfille.tf.common.container.ContainerDisplayStation;
import fiskfille.tf.common.container.ContainerDisplayStationArmor;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.container.ContainerEnergonTank;
import fiskfille.tf.common.container.ContainerGroundBridge;
import fiskfille.tf.common.container.ContainerTransmitter;
import fiskfille.tf.common.container.InventoryGroundBridge;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;
import fiskfille.tf.common.tileentity.TileEntityAssemblyTable;
import fiskfille.tf.common.tileentity.TileEntityColumn;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class GuiHandlerTF implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == TFGui.GROUND_BRIDGE_REMOTE.guiId)
        {
            world = DimensionManager.getWorld(world.provider.dimensionId);
        }

        TileEntity tile = world.getTileEntity(x, y, z);
        TFGui tfGui = TFGui.get(id);

        if (tfGui != null)
        {
            if (tfGui.containerClass == null)
            {
                return null;
            }

            if (world.getBlock(x, y, z) == tfGui.containerBlock)
            {
                int[] coords = {x, y, z};
                int integer = 0;

                try
                {
                    Constructor c = tfGui.containerClass.getConstructor(tfGui.containerArgs);
                    Object[] args = new Object[tfGui.containerArgs.length];

                    for (int i = 0; i < tfGui.containerArgs.length; ++i)
                    {
                        Class clazz = tfGui.containerArgs[i];

                        if (InventoryPlayer.class.isAssignableFrom(clazz))
                        {
                            args[i] = player.inventory;
                        }
                        else if (TileEntity.class.isAssignableFrom(clazz))
                        {
                            args[i] = clazz.cast(tile);
                        }
                        else if (InventoryGroundBridge.class.isAssignableFrom(clazz))
                        {
                            ItemStack itemstack = player.getHeldItem();

                            if (itemstack == null || itemstack.getItem() != TFItems.groundBridgeRemote)
                            {
                                return null;
                            }

                            args[i] = new InventoryGroundBridge(player, itemstack);
                        }
                        else if (World.class.isAssignableFrom(clazz))
                        {
                            args[i] = world;
                        }
                        else if (int.class.isAssignableFrom(clazz))
                        {
                            if (integer < 3)
                            {
                                args[i] = coords[integer];
                            }
                            else
                            {
                                args[i] = 0;
                            }

                            ++integer;
                        }
                    }

                    return c.newInstance(args);
                }
                catch (Exception e)
                {
                    TFLog.error("Unable to load class for gui element %s", tfGui);
                    e.printStackTrace();
                }

                return null;
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == TFGui.GROUND_BRIDGE_REMOTE.guiId)
        {
            world = DimensionManager.getWorld(world.provider.dimensionId);
        }

        TileEntity tile = world.getTileEntity(x, y, z);
        TFGui tfGui = TFGui.get(id);

        if (tfGui != null)
        {
            if (tfGui.guiPath == null)
            {
                return null;
            }

            if (world.getBlock(x, y, z) == tfGui.containerBlock)
            {
                int[] coords = {x, y, z};
                int integer = 0;

                try
                {
                    Constructor c = Class.forName(tfGui.guiPath).getConstructor(tfGui.guiArgs);
                    Object[] args = new Object[tfGui.guiArgs.length];

                    for (int i = 0; i < tfGui.guiArgs.length; ++i)
                    {
                        Class clazz = tfGui.guiArgs[i];

                        if (InventoryPlayer.class.isAssignableFrom(clazz))
                        {
                            args[i] = player.inventory;
                        }
                        else if (TileEntity.class.isAssignableFrom(clazz))
                        {
                            args[i] = clazz.cast(tile);
                        }
                        else if (InventoryGroundBridge.class.isAssignableFrom(clazz))
                        {
                            ItemStack itemstack = player.getHeldItem();

                            if (itemstack == null || itemstack.getItem() != TFItems.groundBridgeRemote)
                            {
                                return null;
                            }

                            args[i] = new InventoryGroundBridge(player, itemstack);
                        }
                        else if (World.class.isAssignableFrom(clazz))
                        {
                            args[i] = world;
                        }
                        else if (int.class.isAssignableFrom(clazz))
                        {
                            if (integer < 3)
                            {
                                args[i] = coords[integer];
                            }
                            else
                            {
                                args[i] = 0;
                            }

                            ++integer;
                        }
                    }

                    return c.newInstance(args);
                }
                catch (Exception e)
                {
                    TFLog.error("Unable to load class for gui element '%s'", tfGui);
                    e.printStackTrace();
                }

                return null;
            }
        }

        return null;
    }

    public static class TFGui
    {
        private static final List<TFGui> list = Lists.newArrayList();
        private static int nextId = -1;

        public static TFGui ALLOY_CRUCIBLE;
        public static TFGui ASSEMBLY_TABLE;
        public static TFGui DISPLAY_STATION;
        public static TFGui DISPLAY_STATION_ARMOR;
        public static TFGui DISPLAY_STATION_COLOR;
        public static TFGui ENERGON_PROCESSOR;
        public static TFGui ENERGON_TANK;
        public static TFGui ENERGON_TRANSMITTER;
        public static TFGui ENERGY_COLUMN;
        public static TFGui GROUND_BRIDGE_REMOTE;

        public final int guiId;
        private final Block containerBlock;
        private final Class containerClass;
        private final Class[] containerArgs;
        private final String guiPath;
        private final Class[] guiArgs;

        public TFGui(Block block, Class<? extends Container> clazz, String path, Class... gui)
        {
            this(block, clazz, path, gui, gui);
        }

        public TFGui(Block block, Class<? extends Container> clazz, String path, Class[] gui, Class[] container)
        {
            this(++nextId, block, clazz, path, gui, container);
        }

        public TFGui(int id, Block block, Class<? extends Container> clazz, String path, Class... gui)
        {
            this(id, block, clazz, path, gui, gui);
        }

        public TFGui(int id, Block block, Class<? extends Container> clazz, String path, Class[] gui, Class[] container)
        {
            guiId = id;
            guiPath = path;
            guiArgs = gui;
            containerClass = clazz;
            containerArgs = container;
            containerBlock = block;

            list.add(this);
        }

        public static void register()
        {
            ALLOY_CRUCIBLE = new TFGui(TFBlocks.alloyCrucible, ContainerAlloyCrucible.class, "fiskfille.tf.client.gui.GuiAlloyCrucible", InventoryPlayer.class, TileEntityAlloyCrucible.class);
            ASSEMBLY_TABLE = new TFGui(TFBlocks.assemblyTable, ContainerAssemblyTable.class, "fiskfille.tf.client.gui.GuiAssemblyTable", InventoryPlayer.class, TileEntityAssemblyTable.class);
            DISPLAY_STATION = new TFGui(TFBlocks.displayStation, ContainerDisplayStation.class, "fiskfille.tf.client.gui.GuiDisplayStation", InventoryPlayer.class, TileEntityDisplayStation.class);
            DISPLAY_STATION_ARMOR = new TFGui(TFBlocks.displayStation, ContainerDisplayStationArmor.class, "fiskfille.tf.client.gui.GuiDisplayStationArmor", InventoryPlayer.class, TileEntityDisplayStation.class);
            DISPLAY_STATION_COLOR = new TFGui(TFBlocks.displayStation, null, "fiskfille.tf.client.gui.GuiColor", TileEntityDisplayStation.class);
            ENERGON_PROCESSOR = new TFGui(TFBlocks.energonProcessor, ContainerEnergonProcessor.class, "fiskfille.tf.client.gui.GuiEnergonProcessor", InventoryPlayer.class, TileEntityEnergonProcessor.class);
            ENERGON_TANK = new TFGui(TFBlocks.energonFluidTank, ContainerEnergonTank.class, "fiskfille.tf.client.gui.GuiEnergonTank", InventoryPlayer.class, TileEntityEnergonTank.class);
            ENERGON_TRANSMITTER = new TFGui(TFBlocks.transmitter, ContainerTransmitter.class, "fiskfille.tf.client.gui.GuiTransmitter", InventoryPlayer.class, TileEntityTransmitter.class);
            ENERGY_COLUMN = new TFGui(TFBlocks.energyColumn, ContainerColumn.class, "fiskfille.tf.client.gui.GuiColumn", InventoryPlayer.class, TileEntityColumn.class);
            GROUND_BRIDGE_REMOTE = new TFGui(TFBlocks.groundBridgeControlPanel, ContainerGroundBridge.class, "fiskfille.tf.client.gui.GuiGroundBridge", InventoryPlayer.class, InventoryGroundBridge.class, TileEntityControlPanel.class);
        }

        public void open(EntityPlayer player, TileEntity tile)
        {
            open(player, player.worldObj, tile);
        }

        public void open(EntityPlayer player, World world, TileEntity tile)
        {
            open(player, world, tile.xCoord, tile.yCoord, tile.zCoord);
        }

        public void open(EntityPlayer player, int x, int y, int z)
        {
            open(player, player.worldObj, x, y, z);
        }

        public void open(EntityPlayer player, World world, int x, int y, int z)
        {
            player.openGui(TransformersMod.instance, guiId, world, x, y, z);
        }

        public static TFGui get(int id)
        {
            for (int i = 0; i < list.size(); ++i)
            {
                TFGui gui = list.get(i);

                if (gui.guiId == id)
                {
                    return gui;
                }
            }

            return null;
        }

        @Override
        public String toString()
        {
            return String.format("TFGui{id=%s, path=%s}", guiId, guiPath);
        }
    }
}
