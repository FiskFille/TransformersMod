package fiskfille.tf.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.component.Component;
import fiskfille.tf.common.component.IComponent;
import fiskfille.tf.common.container.ContainerDisplayStation;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.network.MessageTransformDisplayStation;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;

@SideOnly(Side.CLIENT)
public class GuiDisplayStation extends GuiContainer
{
	private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/display_station.png");
	private TileEntityDisplayStation tileentity;

	@Override
	public void initGui()
	{
		super.initGui();
		buttonList.add(new GuiButton(0, width / 2 - 50, height / 2 - 28, 70, 20, StatCollector.translateToLocal("gui.display_station.transform")));
		buttonList.add(new GuiButtonInitComponent(1, width / 2 + 43, height / 2 - 73));
		buttonList.add(new GuiButtonInitComponent(2, width / 2 + 43, height / 2 - 73 + 18));

		((GuiButton) buttonList.get(1)).enabled = getComponent(0) != null && getComponent(0).canLoad(tileentity, 0);
		((GuiButton) buttonList.get(2)).enabled = getComponent(1) != null && getComponent(1).canLoad(tileentity, 1);
	}

	public GuiDisplayStation(InventoryPlayer inventoryPlayer, TileEntityDisplayStation tile)
	{
		super(new ContainerDisplayStation(inventoryPlayer, tile));
		tileentity = tile;
		ySize = 186;
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		((GuiButton) buttonList.get(1)).enabled = getComponent(0) != null && getComponent(0).canLoad(tileentity, 0);
		((GuiButton) buttonList.get(2)).enabled = getComponent(1) != null && getComponent(1).canLoad(tileentity, 1);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		int id = button.id;

		if (id == 0)
		{
			TFNetworkManager.networkWrapper.sendToServer(new MessageTransformDisplayStation(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord));
			TFNetworkManager.networkWrapper.sendToAll(new MessageTransformDisplayStation(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord));
		}
		else if (id == 1 || id == 2)
		{
			Component component = getComponent(id - 1);

			if (component != null)
			{
				component.load(tileentity, id - 1, mc.thePlayer);
			}
		}
	}

	public Component getComponent(int slot)
	{
		ItemStack itemstack = tileentity.getStackInSlot(4 + slot);

		if (itemstack != null && itemstack.getItem() instanceof IComponent)
		{
			IComponent icomponent = (IComponent) itemstack.getItem();
			return icomponent.getComponent();
		}

		return null;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String s = tileentity.hasCustomInventoryName() ? tileentity.getInventoryName() : I18n.format(tileentity.getInventoryName(), new Object[0]);
		fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 94, 4210752);

		if (tileentity.getStackInSlot(6) == null)
		{
			boolean prevColor = itemRender.renderWithColor;
			int i = mc.thePlayer.ticksExisted / 20;
			ItemStack itemstack = new ItemStack(TFItems.displayVehicle, 1, i % TransformersAPI.getTransformers().size());

			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glEnable(GL11.GL_COLOR_MATERIAL);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(0.6F, 0.6F, 0.6F, 0.25F);
			itemRender.renderWithColor = false;
			itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, 75, 45);
			itemRender.renderWithColor = prevColor;
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(guiTextures);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}
