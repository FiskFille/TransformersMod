package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.block.BlockControlPanel;
import fiskfille.tf.common.data.tile.TileDataControlPanel;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFTileHelper;

public class ItemGroundBridgeRemote extends Item
{
    public IIcon[] icons;

    public ItemGroundBridgeRemote()
    {
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
    {
        ItemCSD.DimensionalCoords coords = ItemCSD.getCoords(itemstack);
        list.add(coords.getFormatted().getFormattedText());
    }

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int usingTick, boolean holding)
    {
        ItemCSD.DimensionalCoords coords = ItemCSD.getCoords(itemstack);

        if (TFTileHelper.getTileData(coords) instanceof TileDataControlPanel)
        {
            itemstack.setItemDamage(1);
        }
        else
        {
            itemstack.setItemDamage(0);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            ItemCSD.DimensionalCoords coords = ItemCSD.getCoords(stack);
            WorldServer targetWorld = MinecraftServer.getServer().worldServerForDimension(coords.dimension);

            if (!player.isSneaking())
            {
                if (targetWorld != null)
                {
                    TileEntity tile = targetWorld.getTileEntity(coords.posX, coords.posY, coords.posZ);
                    int metadata = targetWorld.getBlockMetadata(coords.posX, coords.posY, coords.posZ);

                    if (tile instanceof TileEntityControlPanel && BlockControlPanel.isBlockLeftSideOfPanel(metadata))
                    {
                        player.openGui(TransformersMod.instance, coords.dimension << 8 | TFGui.GROUND_BRIDGE_REMOTE.guiId, targetWorld, coords.posX, coords.posY, coords.posZ);
                        player.addChatComponentMessage(new ChatComponentTranslation("ground_bridge_remote.connect", coords.getFormatted()));

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
            TileEntity tile = TFTileHelper.getTileBase(world.getTileEntity(x, y, z));

            if (tile instanceof TileEntityControlPanel && BlockControlPanel.isBlockLeftSideOfPanel(tile.getBlockMetadata()))
            {
                ItemCSD.DimensionalCoords coords = new ItemCSD.DimensionalCoords(tile.xCoord, tile.yCoord, tile.zCoord, world.provider.dimensionId);
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

    @Override
    public IIcon getIconFromDamage(int damage)
    {
        return icons[Math.min(damage, 1)];
    }

    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[2];
        icons[0] = iconRegister.registerIcon(getIconString() + "_off");
        icons[1] = iconRegister.registerIcon(getIconString() + "_on");
    }
}
