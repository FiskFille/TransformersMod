package fiskfille.tf.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class TFPlayerData implements IExtendedEntityProperties
{
	public boolean mode;
	public boolean stealthMode;
	
	public static final String IDENTIFIER = "TFPLAYERDATA";
	
	public static TFPlayerData getData(EntityPlayer player)
	{
		return (TFPlayerData) player.getExtendedProperties(IDENTIFIER);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) 
	{
		compound.setBoolean("mode", mode);
		compound.setBoolean("stealth", stealthMode);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		mode = compound.getBoolean("mode");
		stealthMode = compound.getBoolean("stealth");
	}

	@Override
	public void init(Entity entity, World world) 
	{
	}
}
