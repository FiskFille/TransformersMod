package fiskfille.tf.data;

import com.google.common.collect.ComputationException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class TFPlayerDataNew implements IExtendedEntityProperties
{
	public boolean mode;
	
	public static final String IDENTIFIER = "TFPLAYERDATA";
	
	public static TFPlayerDataNew getData(EntityPlayer player)
	{
		return (TFPlayerDataNew) player.getExtendedProperties(IDENTIFIER);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) 
	{
		compound.setBoolean("mode", mode);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		mode = compound.getBoolean("mode");
	}

	@Override
	public void init(Entity entity, World world) 
	{
		
	}
}
