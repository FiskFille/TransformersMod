package fiskfille.tf.common.capability;

import fiskfille.tf.TransformersMod;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;

public class TFCapabilities
{
    public static final ResourceLocation PLAYER_DATA_ID = new ResourceLocation(TransformersMod.MODID, "player_data");
    public static final ResourceLocation ENTITY_DATA_ID = new ResourceLocation(TransformersMod.MODID, "entity_data");

    @CapabilityInject(TFPlayerData.class)
    public static Capability<TFPlayerData> PLAYER_DATA_CAP;

    @CapabilityInject(TFEntityData.class)
    public static Capability<TFEntityData> ENTITY_DATA_CAP;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(TFPlayerData.class, new DefaultStorage<>(), TFPlayerData.Implementation::new);
        CapabilityManager.INSTANCE.register(TFEntityData.class, new DefaultStorage<>(), TFEntityData.Implementation::new);
    }

    private static class DefaultStorage<T extends INBTSerializable<NBTTagCompound>> implements Capability.IStorage<T>
    {
        @Override
        public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side)
        {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt)
        {
            instance.deserializeNBT((NBTTagCompound) nbt);
        }
    }
}
