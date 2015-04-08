package fiskfille.tf.common.playerdata;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class TFPlayerData implements IExtendedEntityProperties
{
    public boolean vehicle;
    public boolean stealthForce;
    
    public static final String IDENTIFIER = "TFPLAYERDATA";
    
    private EntityPlayer player;
    
    public static TFPlayerData getData(EntityPlayer player)
    {
        return (TFPlayerData) player.getExtendedProperties(IDENTIFIER);
    }
    
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        compound.setBoolean("mode", vehicle);
        compound.setBoolean("stealth", stealthForce);
    }
    
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        vehicle = compound.getBoolean("mode");
        stealthForce = compound.getBoolean("stealth");
        
        TFDataManager.setTransformationTimer(player, vehicle ? 0 : 20);
        TFDataManager.setStealthModeTimer(player, stealthForce ? 0 : 5);
    }
    
    @Override
    public void init(Entity entity, World world)
    {
    	if(entity instanceof EntityPlayer)
    	{
    		player = (EntityPlayer) entity;
    	}
    }
}
