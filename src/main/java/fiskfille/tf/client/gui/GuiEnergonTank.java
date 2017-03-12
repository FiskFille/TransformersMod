package fiskfille.tf.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerEnergonTank;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.helper.TFFluidRenderHelper;
import fiskfille.tf.helper.TFTileHelper;

@SideOnly(Side.CLIENT)
public class GuiEnergonTank extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/energon_fluid_tank.png");
    private TileEntityEnergonTank tileentity;

    public FluidTankTF fluidTank;
    
    private GuiHoverFieldFluid fieldFluid;

    public GuiEnergonTank(InventoryPlayer inventoryPlayer, TileEntityEnergonTank tile)
    {
        super(new ContainerEnergonTank(inventoryPlayer, tile));
        tileentity = tile;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        buttonList.add(fieldFluid = new GuiHoverFieldFluid(x + 61, y + 17, 52, 52, tileentity.data.tank));
        buttonList.add(new GuiButtonConfigRedstone(1, x + xSize - 18, y + 5, tileentity));
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        updateFluids();
        fieldFluid.update(fluidTank);
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 1)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, -tileentity.io.length - 2));
        }
    }

    public void updateFluids()
    {
        TileEntityEnergonTank tileBase = TFTileHelper.getTileBase(tileentity);
        FluidStack stack = null;
        int y = tileBase.yCoord;
        int capacity = 0;

        while (y < tileentity.getWorldObj().getHeight() && TFTileHelper.getTileBase(tileentity.getWorldObj().getTileEntity(tileentity.xCoord, y, tileentity.zCoord)) == tileBase)
        {
            TileEntityEnergonTank tile = (TileEntityEnergonTank) tileentity.getWorldObj().getTileEntity(tileentity.xCoord, y, tileentity.zCoord);
            FluidTank tank = tile.getTank();

            if (stack == null && tank.getFluid() != null)
            {
                stack = tank.getFluid().copy();
                stack.amount = 0;
            }

            if (stack != null)
            {
                stack.amount += tank.getFluidAmount();
            }

            capacity += tank.getCapacity();
            ++y;
        }

        fluidTank = new FluidTankTF(stack, capacity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = I18n.format(tileentity.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);

        if (fluidTank == null)
        {
            updateFluids();
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (tileentity.fillTime > 0)
        {
            int i = (int) (tileentity.fillTime * 0.13F);
            drawTexturedModalRect(x + 120, y + 36, 176, 0, i, 12);
        }

        if (fluidTank == null)
        {
            updateFluids();
        }
        
        GL11.glEnable(GL11.GL_BLEND);
        TFFluidRenderHelper.renderIntoGUI(fluidTank, x + 64, y + 19, 48, 48, zLevel);
        GL11.glDisable(GL11.GL_BLEND);

        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x + 62, y + 17, 204, 0, 52, 52);
    }
}
