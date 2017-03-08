package fiskfille.tf.client.gui;

import java.util.List;

import javax.vecmath.Vector3d;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL14;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.block.BlockMachineBase;
import fiskfille.tf.common.container.ContainerEmpty;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tick.ClientTickHandler;
import fiskfille.tf.common.tileentity.TileEntityMachine;
import fiskfille.tf.common.tileentity.TileEntityMachine.EnumIO;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTextureHelper;
import fiskfille.tf.helper.TFTileHelper;

@SideOnly(Side.CLIENT)
public class GuiConfigSides extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/configure.png");
    private TileEntityMachine machine;
    private GuiScreen parent;
    private GuiButton buttonDistribution;

    private RenderBlocks renderBlocks;
    private World world;

    private List<ChunkCoordinates> neighbors = Lists.newArrayList();
    private List<ChunkCoordinates> configurables = Lists.newArrayList();
    private Vector3d camera;
    private boolean renderNeighbours = true;

    public GuiConfigSides(InventoryPlayer inventoryPlayer, GuiScreen gui, TileEntityMachine tile)
    {
        super(new ContainerEmpty(inventoryPlayer, 16));
        machine = tile;
        parent = gui;
        ySize = 182;

        world = tile.getWorldObj();
        renderBlocks = new RenderBlocks(world);
        camera = new Vector3d(-machine.xCoord - 0.5F, -machine.yCoord - 0.5F, -machine.zCoord - 0.5F);
        configurables.add(new DimensionalCoords(tile));
    }

    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        
        buttonList.add(buttonDistribution = new GuiButtonDistribution(0, x + 111, y + 66, machine));

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            int index = dir.ordinal();
            buttonList.add(new GuiButtonIO(index + 1, x + 108 + index % 2 * 15, y + 18 + index / 2 * 15, machine, dir));
        }
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        for (GuiButton button : (List<GuiButton>) buttonList)
        {
            if (button instanceof GuiButtonIO)
            {
                GuiButtonIO iobutton = (GuiButtonIO) button;
                button.enabled = iobutton.machine.canTransfer(iobutton.side);
            }
        }

        neighbors.clear();

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            int x = machine.xCoord + dir.offsetX;
            int y = machine.yCoord + dir.offsetY + (dir.offsetY > 0 ? machine.getBlockType().getBlockHeight() - 1 : 0);
            int z = machine.zCoord + dir.offsetZ;

            if (!world.isAirBlock(x, y, z))
            {
                neighbors.add(new ChunkCoordinates(x, y, z));
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(machine), mc.thePlayer, -machine.io.length - 3));
        }
        else if (id > 0 && id <= ForgeDirection.VALID_DIRECTIONS.length)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(new DimensionalCoords(machine), mc.thePlayer, -id));
        }
    }

    @Override
    protected void keyTyped(char c, int key)
    {
        if (key == 1 || key == mc.gameSettings.keyBindInventory.getKeyCode())
        {
            mc.displayGuiScreen(parent);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        
        String s = I18n.format("gui.tf.io");
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 94, 4210752);

        float height = machine.getBlockType().getBlockHeight();
        float scale = 30 - (height - 1) * 5;
        GL11.glPushMatrix();
        GL11.glTranslatef(73, 51, 100);
        GL11.glRotatef(-TFHelper.median(mc.thePlayer.rotationPitch, mc.thePlayer.prevRotationPitch, ClientTickHandler.renderTick), 1, 0, 0);
        GL11.glRotatef(TFHelper.median(mc.thePlayer.rotationYaw, mc.thePlayer.prevRotationYaw, ClientTickHandler.renderTick), 0, 1, 0);
        GL11.glScalef(-scale, -scale, -scale);
        GL11.glTranslatef(0, 0.5F - height / 2, 0);
        TFRenderHelper.startGlScissor(x + 41, y + 19, 64, 64);
        renderScene();
        TFRenderHelper.endGlScissor();
        GL11.glPopMatrix();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(guiTextures);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    private void renderScene()
    {
        Tessellator tessellator = Tessellator.instance;
        BlockMachineBase block = machine.getBlockType();
        int x = machine.xCoord;
        int y = machine.yCoord;
        int z = machine.zCoord;

        mc.entityRenderer.disableLightmap(0);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        for (int pass = 0; pass < 2; ++pass)
        {
            setGlStateForPass(pass, false);
            doTileEntityRenderPass(configurables, pass);

            if (renderNeighbours)
            {
                setGlStateForPass(pass, true);
                doTileEntityRenderPass(neighbors, pass);
            }
        }

        mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        GL11.glDisable(GL11.GL_LIGHTING);

        for (int pass = 0; pass < 1; ++pass)
        {
            setGlStateForPass(pass, false);
            doWorldRenderPass(configurables, pass);

            if (renderNeighbours)
            {
                setGlStateForPass(pass, true);
                doWorldRenderPass(neighbors, pass);
            }
        }

        ForgeHooksClient.setRenderPass(-1);
        setGlStateForPass(0, false);
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        tessellator.setTranslation(-x - 0.5F, -y - 0.5F, -z - 0.5F);
        renderBlocks.setRenderAllFaces(true);
        
        for (GuiButton button : (List<GuiButton>) buttonList)
        {
            if (button instanceof GuiButtonIO)
            {
                GuiButtonIO iobutton = (GuiButtonIO) button;
                
                if (iobutton.func_146115_a())
                {
                    ForgeDirection dir = iobutton.side;
                    IIcon icon = renderBlocks.getBlockIcon(Blocks.wool);
                    
                    if (iobutton.enabled)
                    {
                        GL11.glColor4f(0.2F, 1, 0.2F, 0.5F);
                    }
                    else
                    {
                        GL11.glColor4f(1, 0.2F, 0.2F, 0.5F);
                    }
                    
                    block.setBlockBoundsBasedOnState(world, x, y, z);
                    renderBlocks.setRenderBoundsFromBlock(block);
                    
                    for (int i = 0; i < 2; ++i)
                    {
                        if (i == 1)
                        {
                            EnumIO io = machine.getInOutMode(dir);
                            
                            if (io.ordinal() == 0)
                            {
                                break;
                            }
                            
                            GL11.glColor4f(1, 1, 1, 1);
                            renderBlocks.setRenderBounds(0, 0, 0, 1, 1, 1);
                            icon = TFTextureHelper.ioIcons[io.ordinal()];
                        }
                        
                        tessellator.startDrawingQuads();
                        renderFace(dir, block, x, y + (i == 1 && dir.offsetY > 0 ? block.getBlockHeight() - 1 : 0), z, icon);
                        tessellator.draw();
                    }
                }
            }
        }
        
//        GL11.glColor4f(1, 1, 1, 1);
//        
//        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
//        {
//            EnumIO io = machine.getInOutMode(dir);
//            
//            if (io.ordinal() > 0)
//            {
//                renderBlocks.setRenderBounds(0, 0, 0, 1, 1, 1);
//                
//                tessellator.startDrawingQuads();
//                renderFace(dir, block, x, y, z, TFTextureHelper.ioIcons[io.ordinal()]);
//                tessellator.draw();
//            }
//        }
        
        tessellator.setTranslation(0, 0, 0);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    private void renderFace(ForgeDirection dir, Block block, int x, int y, int z, IIcon icon)
    {
        switch (dir)
        {
        case DOWN:
            renderBlocks.renderFaceYNeg(block, x, y, z, icon);
            break;
        case UP:
            renderBlocks.renderFaceYPos(block, x, y, z, icon);
            break;
        case NORTH:
            renderBlocks.renderFaceZNeg(block, x, y, z, icon);
            break;
        case SOUTH:
            renderBlocks.renderFaceZPos(block, x, y, z, icon);
            break;
        case EAST:
            renderBlocks.renderFaceXPos(block, x, y, z, icon);
            break;
        case WEST:
            renderBlocks.renderFaceXNeg(block, x, y, z, icon);
            break;
        default:
            break;
        }
    }

    private void doTileEntityRenderPass(List<ChunkCoordinates> blocks, int pass)
    {
        ForgeHooksClient.setRenderPass(pass);
        
        for (ChunkCoordinates coords : blocks)
        {
            TileEntity tile = TFTileHelper.getTileBase(world.getTileEntity(coords.posX, coords.posY, coords.posZ));
            
            if (tile != null)
            {
                GL11.glColor4f(1, 1, 1, 1);
                TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, tile.xCoord + camera.x, tile.yCoord + camera.y, tile.zCoord + camera.z, 0);
            }
        }
    }

    private void doWorldRenderPass(List<ChunkCoordinates> blocks, int pass)
    {
        ForgeHooksClient.setRenderPass(pass);

        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.setTranslation(camera.x, camera.y, camera.z);
        Tessellator.instance.setBrightness(15 << 20 | 15 << 4);

        for (ChunkCoordinates coords : blocks)
        {
            Block block = world.getBlock(coords.posX, coords.posY, coords.posZ);
            
            if (block != null)
            {
                if (block.canRenderInPass(pass))
                {
                    renderBlocks.renderAllFaces = true;
                    renderBlocks.setRenderAllFaces(true);
                    renderBlocks.setRenderBounds(0, 0, 0, 1, 1, 1);
                    renderBlocks.renderBlockByRenderType(block, coords.posX, coords.posY, coords.posZ);
                }
            }
        }

        Tessellator.instance.draw();
        Tessellator.instance.setTranslation(0, 0, 0);
    }

    private void setGlStateForPass(int pass, boolean isNeighbour)
    {
        GL11.glColor4f(1, 1, 1, 1);
        
        if (isNeighbour)
        {
            float alpha = 0.6F;
            
            if (pass == 0)
            {
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glBlendFunc(GL11.GL_CONSTANT_ALPHA, GL11.GL_CONSTANT_COLOR);
                GL14.glBlendColor(1, 1, 1, alpha);
                GL11.glDepthMask(true);
            }
            else
            {
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_CONSTANT_COLOR);
                GL14.glBlendColor(1, 1, 1, 0.4F);
                GL14.glBlendColor(1, 1, 1, alpha);
                GL11.glDepthMask(false);
            }
            
            return;
        }

        if (pass == 0)
        {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
        }
        else
        {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDepthMask(false);
        }
    }
}
