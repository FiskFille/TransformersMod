package fiskfille.tf.common.item;

import fiskfille.tf.common.block.BlockControlPanel;
import fiskfille.tf.common.container.ContainerGroundBridge;
import fiskfille.tf.common.container.InventoryGroundBridge;
import fiskfille.tf.common.groundbridge.RemoteData;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageUpdateRemote;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

import java.util.List;

public class ItemGroundBridgeRemote extends Item
{
    public ItemGroundBridgeRemote()
    {
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
    {
        DimensionalCoords coords = ItemCSD.getCoords(itemstack);
        list.add(coords.getFormatted().getFormattedText());
    }

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean currentItem)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            if (!player.worldObj.isRemote)
            {
                TileEntityControlPanel tile = getTile(itemstack);

                if (currentItem && tile != null)
                {
                    InventoryGroundBridge inventory = new InventoryGroundBridge(player, itemstack);
                    ItemStack itemstack1 = inventory.getStackInSlot(0);

                    if (itemstack1 != null && itemstack1.getItem() == TFItems.csd)
                    {
                        DimensionalCoords coords = ItemCSD.getCoords(itemstack1);

                        if (coords.posX != tile.destX || coords.posY != tile.prevDestY || coords.posZ != tile.destZ || coords.dimension != tile.getDestDimensionID())
                        {
                            tile.setSwitchesTo(coords);
                            tile.markBlockForUpdate();
                        }
                    }
                }
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            DimensionalCoords coords = ItemCSD.getCoords(stack);
            WorldServer targetWorld = DimensionManager.getWorld(coords.dimension);

            if (!player.isSneaking())
            {
                if (targetWorld != null)
                {
                    int metadata = targetWorld.getBlockMetadata(coords.posX, coords.posY, coords.posZ);

                    TileEntity tile = targetWorld.getTileEntity(coords.posX, coords.posY, coords.posZ);

                    if (tile instanceof TileEntityControlPanel && BlockControlPanel.isBlockLeftSideOfPanel(metadata))
                    {
                        EntityPlayerMP playerMP = (EntityPlayerMP) player;

                        player.addChatComponentMessage(new ChatComponentTranslation("ground_bridge_remote.connect", coords.getFormatted()));
                        TFNetworkManager.networkWrapper.sendTo(new MessageUpdateRemote(new RemoteData((TileEntityControlPanel) tile), true), playerMP);

                        playerMP.getNextWindowId();
                        playerMP.closeContainer();
                        int windowId = playerMP.currentWindowId;
                        playerMP.openContainer = new ContainerGroundBridge(player.inventory, new InventoryGroundBridge(player, stack), (TileEntityControlPanel) tile);
                        playerMP.openContainer.windowId = windowId;
                        playerMP.openContainer.addCraftingToCrafters(playerMP);

                        ((TileEntityControlPanel) tile).controllingPlayers.add(player);

                        return stack;
                    }
                }

                player.addChatComponentMessage(new ChatComponentTranslation("ground_bridge_remote.connect.fail", coords.getFormatted().getUnformattedText()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
        }

        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (player.isSneaking())
        {
            int metadata = world.getBlockMetadata(x, y, z);

            if (world.getTileEntity(x, y, z) instanceof TileEntityControlPanel && BlockControlPanel.isBlockLeftSideOfPanel(metadata))
            {
                world.getTileEntity(x, y, z);
                DimensionalCoords coords = new DimensionalCoords(x, y, z, world.provider.dimensionId);

                ItemCSD.setCoords(itemstack, coords);

                if (world.isRemote)
                {
                    player.addChatComponentMessage(new ChatComponentTranslation("ground_bridge_remote.connect.add", coords.getFormatted()));
                }

                return true;
            }
        }

        return false;
    }

    public TileEntityControlPanel getTile(ItemStack itemstack)
    {
        DimensionalCoords coords = ItemCSD.getCoords(itemstack);
        WorldServer worldserver = DimensionManager.getWorld(coords.dimension);

        if (worldserver != null)
        {
            TileEntity tile = worldserver.getTileEntity(coords.posX, coords.posY, coords.posZ);
            int metadata = worldserver.getBlockMetadata(coords.posX, coords.posY, coords.posZ);

            if (tile instanceof TileEntityControlPanel && BlockControlPanel.isBlockLeftSideOfPanel(metadata))
            {
                return (TileEntityControlPanel) tile;
            }
        }

        return null;
    }
}
