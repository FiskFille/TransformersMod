package fiskfille.tf.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ISpecialArmor;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerDisplayStationArmor;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;

@SideOnly(Side.CLIENT)
public class GuiDisplayStationArmor extends GuiContainer
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/display_station_armor.png");
    private TileEntityDisplayStation tileentity;

    public GuiDisplayStationArmor(InventoryPlayer inventoryPlayer, TileEntityDisplayStation tile)
    {
        super(new ContainerDisplayStationArmor(inventoryPlayer, tile));
        tileentity = tile;
        ySize = 186;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = tileentity.hasCustomInventoryName() ? tileentity.getInventoryName() : I18n.format(tileentity.getInventoryName(), new Object[0]);
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

        ContainerDisplayStationArmor container = (ContainerDisplayStationArmor) inventorySlots;

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemstack = tileentity.getStackInSlot(i);
            ItemStack itemstack1 = container.craftMatrix.getStackInSlot(i);

            if (itemstack != null && itemstack1 != null)
            {
                ItemStack itemstack2 = new ItemStack(itemstack.getItem());

                int amount = getArmorValue(itemstack1, i) - getArmorValue(itemstack2, i);
                String s = (amount < 0 ? EnumChatFormatting.DARK_RED : EnumChatFormatting.DARK_BLUE) + I18n.format(amount < 0 ? "gui.display_station.armor.neg" : "gui.display_station.armor", amount < 0 ? -amount : amount);
                fontRendererObj.drawString(s, k + 71, l + 22 + i * 18, 0xffffff);
            }
        }
    }

    public int getArmorValue(ItemStack itemstack, int slot)
    {
        if (itemstack != null && itemstack.getItem() instanceof ISpecialArmor)
        {
            return ((ISpecialArmor) itemstack.getItem()).getArmorDisplay(mc.thePlayer, itemstack, slot);
        }
        else if (itemstack != null && itemstack.getItem() instanceof ItemArmor)
        {
            return ((ItemArmor) itemstack.getItem()).damageReduceAmount;
        }

        return 0;
    }
}
