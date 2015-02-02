package fiskfille.tf.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.container.ContainerDisplayPillar;
import fiskfille.tf.tileentity.TileEntityDisplayPillar;

@SideOnly(Side.CLIENT)
public class GuiDisplayPillar extends GuiContainer
{
	private ResourceLocation texture = new ResourceLocation(TransformersMod.modid, "textures/gui/container/display_pillar.png");
    public TileEntityDisplayPillar thePillar;

	public GuiDisplayPillar(InventoryPlayer inventoryplayer, TileEntityDisplayPillar tile)
	{
		super(new ContainerDisplayPillar(inventoryplayer, tile));
		this.thePillar = tile;
	}

	public void onGuiClosed()
	{
		super.onGuiClosed();
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
        String s = this.thePillar.hasCustomInventoryName() ? this.thePillar.getInventoryName() : I18n.format(this.thePillar.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}
	
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(texture);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}
}