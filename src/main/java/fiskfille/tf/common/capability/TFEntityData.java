package fiskfille.tf.common.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public interface TFEntityData extends ICapabilitySerializable<NBTTagCompound>
{
    int getGroundBridgeCooldown();

    void decrementGroundBridgeCooldown();

    class Implementation implements TFEntityData
    {
        private int groundBridgeCooldown;

        @Override
        public int getGroundBridgeCooldown()
        {
            return this.groundBridgeCooldown;
        }

        @Override
        public void decrementGroundBridgeCooldown()
        {
            if (this.groundBridgeCooldown > 0)
            {
                --this.groundBridgeCooldown;
            }
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing)
        {
            return capability == TFCapabilities.ENTITY_DATA_CAP;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing)
        {
            return this.hasCapability(capability, facing) ? TFCapabilities.ENTITY_DATA_CAP.cast(this) : null;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("GroundBridgeCooldown", this.groundBridgeCooldown);

            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound)
        {
            this.groundBridgeCooldown = compound.getInteger("GroundBridgeCooldown");
        }
    }
}
