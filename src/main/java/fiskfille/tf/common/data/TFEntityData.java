package fiskfille.tf.common.data;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class TFEntityData implements IExtendedEntityProperties
{
    public static final String IDENTIFIER = "TFEntity";

    public int groundBridgeCooldown;

    public static TFEntityData getData(Entity entity)
    {
        return (TFEntityData) entity.getExtendedProperties(IDENTIFIER);
    }

    public void onUpdate()
    {
        if (groundBridgeCooldown > 0)
        {
            --groundBridgeCooldown;
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        compound.setInteger("GroundBridgeCooldown", groundBridgeCooldown);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        groundBridgeCooldown = compound.getInteger("GroundBridgeCooldown");
    }

    @Override
    public void init(Entity entity, World world)
    {
        Entity entity1 = entity;
    }
}
