package fiskfille.tf.client.gui;

import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.common.network.MessageSetReceivers;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiSelectReceivers extends GuiScreen
{
	private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/display_station.png");
	
	public TileEntity tileentity;
	public IEnergyTransmitter transmitter;
	
	public List<ChunkCoordinates> receiverCoords = Lists.newArrayList();
	public ChunkCoordinates[] coordArray;
	public List<Integer> layers = Lists.newArrayList();
	
	public GuiVerticalHeightSlider heightSlider;
	
	public int spacing = 1;
	public int size = 3;

	public void initGui()
	{
		super.initGui();
		float range = transmitter.getRange();
		float boardWidth = 1 + range * 2;
		int boardWidthFl = MathHelper.floor_float(boardWidth);
		int baseX = MathHelper.floor_double(width / 2 - (spacing + size) * boardWidth / 2);
		int baseY = MathHelper.floor_double(height / 2 - (spacing + size) * boardWidth / 2);
		
		buttonList.add(new GuiButton(0, width / 2 - 100, height - height / 7, StatCollector.translateToLocal("gui.done")));
		buttonList.add(heightSlider = new GuiVerticalHeightSlider(1, this, baseX + boardWidthFl * (spacing + size), baseY - 1, boardWidthFl * (spacing + size) + 1));
	}

	public GuiSelectReceivers(TileEntity tile)
	{
		tileentity = tile;
		transmitter = (IEnergyTransmitter)tile;
		receiverCoords.addAll(transmitter.getReceiverHandler().receiverCoords);
	}

	public void updateScreen()
	{
		int boardWidth = 1 + getRange() * 2;
		coordArray = new ChunkCoordinates[boardWidth * boardWidth];
		layers.clear();
		layers.add(tileentity.yCoord);
		
		for (TileEntity tile : (List<TileEntity>)mc.theWorld.loadedTileEntityList)
		{
			if (tile instanceof IEnergyReceiver && ((IEnergyReceiver)tile).canReceiveEnergy(tileentity) && TFEnergyHelper.isInRange(tileentity, tile))
			{
				if (!layers.contains(tile.yCoord))
				{
					layers.add(tile.yCoord);
				}
			}
		}
		
		Collections.sort(layers);
		
		for (int i = 0; i < boardWidth; ++i)
		{
			for (int j = 0; j < boardWidth; ++j)
			{
				int x = MathHelper.floor_double(tileentity.xCoord - boardWidth / 2 + i);
				int z = MathHelper.floor_double(tileentity.zCoord - boardWidth / 2 + j);				
				ChunkCoordinates coords = new ChunkCoordinates(x, getLayer(), z);
				
				if (TFEnergyHelper.isInRange(tileentity, coords))
				{
					coordArray[i + j * boardWidth] = coords;
				}
			}
		}
		
		int metadata = tileentity.getBlockMetadata();
		
		if (metadata > 0)
		{
			ChunkCoordinates[] coordArray1 = coordArray.clone();
			
			for (int i = 0; i < boardWidth; ++i)
			{
				for (int j = 0; j < boardWidth; ++j)
				{
					ChunkCoordinates coords = coordArray[i + j * boardWidth];
					
					if (metadata == 1)
					{
						coords = coordArray[(boardWidth - 1 - j) + i * boardWidth];
					}
					else if (metadata == 2)
					{
						coords = coordArray[(boardWidth - 1 - i) + (boardWidth - 1 - j) * boardWidth];
					}
					else if (metadata == 3)
					{
						coords = coordArray[j + (boardWidth - 1 - i) * boardWidth];
					}
					
					coordArray1[i + j * boardWidth] = coords;
				}
			}
			
			coordArray = coordArray1;
		}
		
		heightSlider.enabled = layers.size() > 1;
	}

	protected void actionPerformed(GuiButton button)
	{
		int id = button.id;

		if (id == 0)
		{
			mc.thePlayer.closeScreen();
		}
	}
	
	protected void keyTyped(char c, int key)
    {
        if (key == 1 || key == mc.gameSettings.keyBindInventory.getKeyCode())
        {
            mc.thePlayer.closeScreen();
        }
    }
	
	@Override
	public void onGuiClosed()
	{
		TFNetworkManager.networkWrapper.sendToServer(new MessageSetReceivers(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, receiverCoords));
		TFNetworkManager.networkWrapper.sendToAll(new MessageSetReceivers(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, receiverCoords));
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int button)
	{
		super.mouseClicked(mouseX, mouseY, button);
		
		if (button == 0)
		{
			int boardWidth = 1 + getRange() * 2;
			int baseX = MathHelper.floor_double(width / 2 - (spacing + size) * boardWidth / 2);
			int baseY = MathHelper.floor_double(height / 2 - (spacing + size) * boardWidth / 2);
						
			if (coordArray != null)
			{
				for (int i = 0; i < boardWidth; ++i)
				{
					for (int j = 0; j < boardWidth; ++j)
					{
						int x = baseX + (spacing + size) * i;
						int y = baseY + (spacing + size) * j;
						
						if (mouseX >= x && mouseX < x + size && mouseY >= y && mouseY < y + size)
						{
							ChunkCoordinates coords = coordArray[i + j * boardWidth];
							
							if (coords != null)
							{
								TileEntity tile = mc.theWorld.getTileEntity(coords.posX, coords.posY, coords.posZ);
								
								if (tile != tileentity && tile instanceof IEnergyReceiver && ((IEnergyReceiver)tile).canReceiveEnergy(tileentity) && (!(tile instanceof IEnergyTransmitter) || !TFEnergyHelper.isGrandParentTo(tile, tileentity)))
								{
									if (receiverCoords.contains(coords))
									{
										receiverCoords.remove(coords);
									}
									else
									{
										receiverCoords.add(coords);
									}
									
									mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1));
								}
							}
						}
					}
				}
			}
		}
	}
	
	public int getLayer()
	{
		float f = 1 - heightSlider.percentage;
		int highest = layers.get(layers.size() - 1);
		int lowest = layers.get(0);
		int num = Math.round(TFRenderHelper.median(highest, lowest, f));
		
		if (!layers.contains(num))
		{
			int dist = Math.abs(layers.get(0) - num);
			int idx = 0;
			
			for (int i = 1; i < layers.size(); ++i)
			{
			    int cdist = Math.abs(layers.get(i) - num);
			    
			    if (cdist < dist)
			    {
			        idx = i;
			        dist = cdist;
			    }
			}
			
			return layers.get(idx);
		}
		
		return num;
	}
	
	public int getRange()
	{
		return MathHelper.floor_float(transmitter.getRange());
	}

	public boolean doesGuiPauseGame()
	{
		return false;
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("gui.transmitter.select_receivers"), width / 2, 15, 16777215);
		
		if (coordArray == null || layers.isEmpty())
		{
			updateScreen();
		}
		
		if (coordArray != null)
		{
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			int boardWidth = 1 + getRange() * 2;
			int baseX = MathHelper.floor_double(width / 2 - (spacing + size) * boardWidth / 2);
			int baseY = MathHelper.floor_double(height / 2 - (spacing + size) * boardWidth / 2);
			
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 1);
			drawTexturedModalRect(baseX - spacing, baseY - spacing, 0, 0, MathHelper.floor_float((spacing + size) * boardWidth) + spacing, MathHelper.floor_float((spacing + size) * boardWidth) + spacing);
			Vec3 src = Vec3.createVectorHelper(size * getRange() - 0.5F, size * getRange() - 0.5F, 0);
			
			for (int i = 0; i < boardWidth; ++i)
			{
				for (int j = 0; j < boardWidth; ++j)
				{
					Vec3 dst = Vec3.createVectorHelper(size * i, size * j, 0);
					float opacity = MathHelper.clamp_float((1 - (float)src.distanceTo(dst) / boardWidth * 2 / size) * 2.5F, 0, 1);
					int x = baseX + (spacing + size) * i;
					int y = baseY + (spacing + size) * j;
					
					GL11.glColor4f(0.075F, 0.075F, 0.075F, 1);
					drawTexturedModalRect(x, y, 0, 0, size, size);
					
					ChunkCoordinates coords = coordArray[i + j * boardWidth];
					
					if (coords != null)
					{
						Block block = mc.theWorld.getBlock(coords.posX, coords.posY, coords.posZ);
						int metadata = mc.theWorld.getBlockMetadata(coords.posX, coords.posY, coords.posZ);
						
						if (!mc.theWorld.isAirBlock(coords.posX, coords.posY, coords.posZ))
						{
//							MapColor color = block.getMapColor(metadata);
//							float[] afloat = TFRenderHelper.hexToRGB(color.colorValue);
							float[] afloat = TFRenderHelper.hexToRGB(0x707070);
							
							GL11.glColor4f(afloat[0], afloat[1], afloat[2], opacity);
							drawTexturedModalRect(x, y, 0, 0, size, size);
						}
					}
				}
			}
			
			Tessellator tessellator = Tessellator.instance;
			float prevWidth = GL11.glGetFloat(GL11.GL_LINE_WIDTH);
			GL11.glLineWidth(size);
			GL11.glColor4f(0, 1, 1, 1);
			
			for (int i = 0; i < boardWidth; ++i)
			{
				for (int j = 0; j < boardWidth; ++j)
				{
					ChunkCoordinates coords = coordArray[i + j * boardWidth];
					int x = baseX + (spacing + size) * i;
					int y = baseY + (spacing + size) * j;
					
					if (receiverCoords.contains(coords))
					{
						tessellator.startDrawing(3);
						tessellator.addVertex(x + (float)size / 2, y + (float)size / 2, 0);
						tessellator.addVertex(baseX + (spacing + size) * boardWidth / 2 - 0.5F, baseY + (spacing + size) * boardWidth / 2 - 0.5F, 0);
						tessellator.draw();
					}
				}
			}
			
			for (int i = 0; i < boardWidth; ++i)
			{
				for (int j = 0; j < boardWidth; ++j)
				{
					Vec3 dst = Vec3.createVectorHelper(size * i, size * j, 0);
					float opacity = MathHelper.clamp_float((1 - (float)src.distanceTo(dst) / boardWidth * 2 / size) * 2.5F, 0, 1);
					int x = baseX + (spacing + size) * i;
					int y = baseY + (spacing + size) * j;
					ChunkCoordinates coords = coordArray[i + j * boardWidth];
					
					if (coords != null)
					{
						TileEntity tile = mc.theWorld.getTileEntity(coords.posX, coords.posY, coords.posZ);
						
						if (receiverCoords.contains(coords) && spacing > 0)
						{
							GL11.glColor4f(0, 1, 1, opacity);
							drawTexturedModalRect(x - spacing, y - spacing, 0, 0, size + spacing * 2, size + spacing * 2);
						}
						
						if (tile == tileentity)
						{
							GL11.glColor4f(0, 0.6F, 0, opacity);
						}
						else if (tile instanceof IEnergyTransmitter && tile instanceof IEnergyReceiver && ((IEnergyReceiver)tile).canReceiveEnergy(tileentity))
						{
							if (((IEnergyTransmitter)tile).isPowering(tileentity))
							{
								GL11.glColor4f(0.2F, 0, 0.4F, opacity);
							}
							else
							{
								GL11.glColor4f(0.75F, 0, 0.75F, opacity);
							}
						}
						else if (tile instanceof IEnergyReceiver && ((IEnergyReceiver)tile).canReceiveEnergy(tileentity))
						{
							GL11.glColor4f(1, 0, 0, opacity);
						}
						else if (tile instanceof IEnergyTransmitter && TFEnergyHelper.isGrandParentTo(tile, tileentity))
						{
							GL11.glColor4f(0.2F, 0, 0.2F, opacity);
						}
						else
						{
							continue;
						}

						drawTexturedModalRect(x, y, 0, 0, size, size);
					}
				}
			}
			
			GL11.glLineWidth(prevWidth);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		
		for (float f = 0; f <= 1; f += 1)
		{
			int highest = layers.get(layers.size() - 1);
			int lowest = layers.get(0);
			int num = Math.round(TFRenderHelper.median(highest, lowest, f));
			
			drawString(mc.fontRenderer, num + "", heightSlider.xPosition + heightSlider.width + 3, heightSlider.yPosition + (int) ((1 - f) * (float) (heightSlider.height - 8)), 0x4C4C4C);
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
