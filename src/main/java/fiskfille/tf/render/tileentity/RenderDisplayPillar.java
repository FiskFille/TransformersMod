package fiskfille.tf.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.main.MainClass;
import fiskfille.tf.model.tileentity.ModelDisplayPillar;
import fiskfille.tf.model.vehicle.ModelCloudtrapVehicle;
import fiskfille.tf.model.vehicle.ModelPurgeVehicle;
import fiskfille.tf.model.vehicle.ModelSkystrikeVehicle;
import fiskfille.tf.model.vehicle.ModelSubwooferVehicle;
import fiskfille.tf.model.vehicle.ModelVehicleBase;
import fiskfille.tf.model.vehicle.ModelVurpVehicle;
import fiskfille.tf.tileentity.TileEntityDisplayPillar;
 
public class RenderDisplayPillar extends TileEntitySpecialRenderer
{
	private ResourceLocation texture = new ResourceLocation(MainClass.modid + ":textures/models/tiles/display_pillar.png");
	private ModelDisplayPillar model;
	private ModelSkystrikeVehicle skystrike = new ModelSkystrikeVehicle();
	private ModelPurgeVehicle purge = new ModelPurgeVehicle();
	private ModelVurpVehicle vurp = new ModelVurpVehicle();
	private ModelSubwooferVehicle subwoofer = new ModelSubwooferVehicle();
	private ModelCloudtrapVehicle cloudtrap = new ModelCloudtrapVehicle();
	private ItemRenderer itemRenderer;
 
    public RenderDisplayPillar()
    {
        model = new ModelDisplayPillar();
        itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
    }
       
    public void renderAModelAt(TileEntityDisplayPillar tileentity, double d, double d1, double d2, float f)
    {
    	GL11.glPushMatrix();
    	GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
    	GL11.glScalef(1.0F, -1F, -1F);
    	this.bindTexture(texture);
    	model.renderAll();
		int meta = tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
		
    	if (meta != 0)
    	{
    		this.bindTexture(new ResourceLocation(MainClass.modid, "textures/models/" + getTextureFromMetadata(meta)));
    		ModelVehicleBase vehicle = getModelFromMetadata(meta);
    		
    		GL11.glRotatef(Minecraft.getMinecraft().thePlayer.ticksExisted, 0.0F, 1.0F, 0.0F);
    		GL11.glTranslatef(0.0F, -0.2F/* + (vehicle == skystrike ? 0.15F : 0.0F)*/, 0.0F);
    		
    		float scale = 0.75F;
    		GL11.glScalef(scale, scale, scale);
    		vehicle.render();
    	}
    	
    	GL11.glPopMatrix();
    }
    
    public ModelVehicleBase getModelFromMetadata(int metadata)
	{
		if (metadata == 1) {return skystrike;}
		if (metadata == 2) {return purge;}
		if (metadata == 3) {return vurp;}
		if (metadata == 4) {return subwoofer;}
		if (metadata == 5) {return cloudtrap;}
		return null;
	}
	
	public String getTextureFromMetadata(int metadata)
	{
		if (metadata == 1) {return "skystrike/skystrike.png";}
		if (metadata == 2) {return "purge/purge.png";}
		if (metadata == 3) {return "vurp/vurp.png";}
		if (metadata == 4) {return "subwoofer/subwoofer.png";}
		if (metadata == 5) {return "cloudtrap/cloudtrap.png";}
		return null;
	}
    
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
    	renderAModelAt((TileEntityDisplayPillar)tileentity, d, d1, d2, f);
    } 
}