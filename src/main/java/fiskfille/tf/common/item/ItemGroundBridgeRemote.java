package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.block.BlockGroundBridgeControl;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFHelper;

public class ItemGroundBridgeRemote extends Item
{
    public ItemGroundBridgeRemote()
    {
        super();
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
    {
        DimensionalCoords coords = ItemCSD.getCoords(itemstack);
        list.add(formatCoords(coords).getFormattedText());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        DimensionalCoords coords = ItemCSD.getCoords(itemstack);
        WorldServer worldserver = DimensionManager.getWorld(coords.dimension);

        if (!player.isSneaking())
        {
            if (worldserver != null)
            {
                int metadata = worldserver.getBlockMetadata(coords.posX, coords.posY, coords.posZ);
                System.out.println(worldserver.getTileEntity(coords.posX, coords.posY, coords.posZ) + ", " + worldserver.provider.dimensionId);
                // TODO: player.openGui doesn't preserve dimension id client-side - we need to create a new packet that does that for us

                if (worldserver.getTileEntity(coords.posX, coords.posY, coords.posZ) instanceof TileEntityControlPanel && BlockGroundBridgeControl.isBlockLeftSideOfPanel(metadata))
                {
                    if (world.isRemote)
                    {
                        player.addChatComponentMessage(new ChatComponentTranslation("ground_bridge_remote.connect", formatCoords(coords)));
                    }
                    else
                    {
                        TFHelper.openGui(player, TransformersMod.instance, 6, worldserver, coords.posX, coords.posY, coords.posZ);
                    }

                    return itemstack;
                }
            }

            if (world.isRemote)
            {
                player.addChatComponentMessage(new ChatComponentTranslation("ground_bridge_remote.connect.fail", formatCoords(coords).getUnformattedText()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
        }

        return itemstack;
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (player.isSneaking())
        {
            int metadata = world.getBlockMetadata(x, y, z);

            if (world.getTileEntity(x, y, z) instanceof TileEntityControlPanel && BlockGroundBridgeControl.isBlockLeftSideOfPanel(metadata))
            {
                TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(x, y, z);
                DimensionalCoords coords = new DimensionalCoords(x, y, z, world.provider.dimensionId);

                ItemCSD.setCoords(itemstack, coords);

                if (world.isRemote)
                {
                    player.addChatComponentMessage(new ChatComponentTranslation("ground_bridge_remote.connect.add", formatCoords(coords)));
                }

                return true;
            }
        }

        return false;
    }

    public IChatComponent formatCoords(DimensionalCoords coords)
    {
        ChatStyle green = new ChatStyle().setColor(EnumChatFormatting.GREEN);
        return new ChatComponentTranslation("csd.format", new ChatComponentText(TFHelper.getDimensionName(coords.dimension)).setChatStyle(green), new ChatComponentText(coords.posX + "").setChatStyle(green), new ChatComponentText(coords.posY + "").setChatStyle(green), new ChatComponentText(coords.posZ + "").setChatStyle(green));
    }
}
