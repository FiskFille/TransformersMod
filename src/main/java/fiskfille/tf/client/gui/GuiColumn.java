package fiskfille.tf.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerColumn;
import fiskfille.tf.common.energon.power.IEnergyContainerItem;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityColumn;

@SideOnly(Side.CLIENT)
public class GuiColumn extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/energy_column.png");
    private TileEntityColumn tileentity;
    
    private GuiHoverFieldEnergy[] fieldEnergy;

    public GuiColumn(InventoryPlayer inventoryPlayer, TileEntityColumn tile)
    {
        super(new ContainerColumn(inventoryPlayer, tile));
        tileentity = tile;
        ySize = 190;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        fieldEnergy = new GuiHoverFieldEnergy[6];
        
        for (int i = 0; i < fieldEnergy.length; ++i)
        {
            buttonList.add(fieldEnergy[i] = new GuiHoverFieldEnergy(x + 25 + i * 22, y + 19, 16, 52, tileentity.storage));
        }
        
        buttonList.add(new GuiButtonConfigSides(0, x + xSize - 18, y + 5));
        buttonList.add(new GuiButtonConfigRedstone(1, x + xSize - 18, y + 20, tileentity));
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        
        for (GuiHoverFieldEnergy field : fieldEnergy)
        {
            field.update(tileentity.storage);
        }
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            mc.displayGuiScreen(new GuiConfigSides(mc.thePlayer.inventory, this, tileentity));
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, -tileentity.io.length - 1));
        }
        else if (id == 1)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(tileentity), mc.thePlayer, -tileentity.io.length - 2));
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = I18n.format(tileentity.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        for (int i = 0; i < tileentity.getSizeInventory(); ++i)
        {
            ItemStack itemstack = tileentity.getStackInSlot(i);
            float energy = 0;
            float max = 0;

            if (itemstack != null && itemstack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) itemstack.getItem();
                energy = container.getEnergyStored(itemstack);
                max = container.getEnergyCapacity(itemstack);
            }

            if (energy > 0)
            {
                float f = energy / max;
                drawTexturedModalRect(x + 25 + i * 22, y + 19 + Math.round(52 * (1 - f)), 176, Math.round(52 * (1 - f)), 16, Math.round(52 * f));
            }
        }
    }
}
