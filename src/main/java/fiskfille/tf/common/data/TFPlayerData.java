package fiskfille.tf.common.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class TFPlayerData implements IExtendedEntityProperties
{
    public static final String IDENTIFIER = "TFPLAYERDATA";
    private EntityPlayer player;

    public int altMode;
    public boolean stealthForce;

    public static TFPlayerData getData(EntityPlayer player)
    {
        return (TFPlayerData) player.getExtendedProperties(IDENTIFIER);
    }
    
    public void onUpdate()
    {
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        compound.setByte("mode", (byte) altMode);
        compound.setBoolean("stealth", stealthForce);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        altMode = compound.getByte("mode");
        stealthForce = compound.getBoolean("stealth");
    }

    @Override
    public void init(Entity entity, World world)
    {
        if (entity instanceof EntityPlayer)
        {
            player = (EntityPlayer) entity;
        }
    }
}
