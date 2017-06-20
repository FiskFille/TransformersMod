package fiskfille.tf.common.data;

import com.google.common.collect.Lists;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.capability.TFCapabilities;
import fiskfille.tf.common.capability.TFPlayerData;
import fiskfille.tf.common.event.PlayerTransformEvent;
import fiskfille.tf.common.helper.TFFormatHelper;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static fiskfille.tf.common.data.TFPredicates.*;

public class TFData<T>
{
    public static final TFData<Integer> ALT_MODE = new TFData<>(-1, isTransformer());
    public static final TFData<Boolean> STEALTH_FORCE = new TFData<>(false, and(isInVehicleMode(), hasStealthForce()));
    public static final TFData<Float> TRANSFORM_PROGRESS = new TFData<>(0.0F, isTransformer());
    public static final TFData<Float> STEALTH_FORCE_PROGRESS = new TFData<>(0.0F, isInVehicleMode());
    public static final TFData<Double> FORWARD_VELOCITY = new TFData<>(0.0D, player -> true);
    public static final TFData<Double> HORIZONTAL_VELOCITY = new TFData<>(0.0D, player -> true);
    public static final TFData<Boolean> BOOSTING = new TFData<>(false, player -> true);
    public static final TFData<Float> NITRO = new TFData<>(1.0F, player -> true);

    public static final TFData<Integer> PREV_ALT_MODE = new TFDataPrev<>(ALT_MODE);
    public static final TFData<Float> PREV_TRANSFORM_PROGRESS = new TFData<>(0.0F, isTransformer());
    public static final TFData<Float> PREV_STEALTH_FORCE_PROGRESS = new TFData<>(0.0F, isInVehicleMode());
    public static final TFData<Transformer> PREV_TRANSFORMER = new TFData<>(null, player -> true);
    public static final TFData<Float> PREV_NITRO = new TFData<>(1.0F, player -> true);

    public static final List<TFData<?>> VALUES = Lists.newArrayList();

    public String id;
    public final boolean save;
    public final T defaultValue;

    protected TFData(T defaultValue, Predicate<EntityPlayer> canSet)
    {
        this(true, defaultValue, canSet);
    }

    protected TFData(boolean save, T defaultValue, Predicate<EntityPlayer> canSet)
    {
        this.save = save;
        this.defaultValue = defaultValue;
    }

    public Predicate<EntityPlayer> predicate(final TFData data, final T value)
    {
        return input -> value == null ? data.get(input) == value : data.get(input) == value || data.get(input).equals(value);
    }

    public boolean set(EntityPlayer player, T value)
    {
        if (this.get(player) == null || !this.get(player).equals(value))
        {
//            if (canSet.apply(player)) // TODO: Gegy-proof
            {
                if (this.setWithoutNotify(player, value))
                {
                    if (player.world.isRemote)
                    {
//                        TFNetworkManager.networkWrapper.sendToServer(new MessagePlayerData(player, this, value)); TODO: Implement
                    }
                    else
                    {
//                        TFNetworkManager.networkWrapper.sendToDimension(new MessagePlayerData(player, this, value), player.dimension);
                    }

                    return true;
                }
            }
//            else
//            {
//                // reset to default
//            }
        }

        return false;
    }

    public boolean setWithoutNotify(EntityPlayer player, T value)
    {
        TFPlayerData playerData = player.getCapability(TFCapabilities.PLAYER_DATA_CAP, null);

        if (playerData != null)
        {
            if (this.get(player) == null || !this.get(player).equals(value))
            {
//              if (canSet.apply(player))
                {
                    if (this == ALT_MODE)
                    {
                        if (!MinecraftForge.EVENT_BUS.post(new PlayerTransformEvent(player, TFHelper.getTransformer(player), (Integer) value, STEALTH_FORCE.get(player))))
                        {
//                           player.addStat(TFAchievements.transform); TODO: Implement

                            if ((Integer) value == -1)
                            {
                                STEALTH_FORCE.setWithoutNotify(player, false);
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }

                    for (TFData type : TFData.VALUES)
                    {
                        if (type instanceof TFDataPrev && ((TFDataPrev) type).tracking == this)
                        {
                            playerData.putData(type, this.get(player));
                        }
                    }

                    playerData.putData(this, value);

                    return true;
                }
//              else
//              {
//                  // reset to default
//              }
            }
        }

        return false;
    }

    public void incr(EntityPlayer player, T value)
    {
        if (value instanceof Integer)
        {
            Integer i = (Integer) this.get(player) + (Integer) value;
            this.set(player, (T) i);
        }
        else if (value instanceof Float)
        {
            Float f = (Float) this.get(player) + (Float) value;
            this.set(player, (T) f);
        }
        else if (value instanceof Double)
        {
            Double d = (Double) this.get(player) + (Double) value;
            this.set(player, (T) d);
        }
        else if (value instanceof String)
        {
            String s = (String) this.get(player) + value;
            this.set(player, (T) s);
        }
        else
        {
            throw new RuntimeException("Cannot increment a non-numerical data type unless a String!");
        }
    }

    public void incrWithoutNotify(EntityPlayer player, T value)
    {
        if (value instanceof Integer)
        {
            Integer i = (Integer) this.get(player) + (Integer) value;
            this.setWithoutNotify(player, (T) i);
        }
        else if (value instanceof Float)
        {
            Float f = (Float) this.get(player) + (Float) value;
            this.setWithoutNotify(player, (T) f);
        }
        else if (value instanceof Double)
        {
            Double d = (Double) this.get(player) + (Double) value;
            this.setWithoutNotify(player, (T) d);
        }
        else if (value instanceof String)
        {
            String s = (String) this.get(player) + value;
            this.setWithoutNotify(player, (T) s);
        }
        else
        {
            throw new RuntimeException("Cannot increment a non-numerical data type unless a String!");
        }
    }

    public void clamp(EntityPlayer player, T min, T max)
    {
        if (min instanceof Integer)
        {
            if ((Integer) this.get(player) < (Integer) min)
            {
                this.set(player, min);
            }
            else if ((Integer) this.get(player) > (Integer) max)
            {
                this.set(player, max);
            }
        }
        else if (min instanceof Float)
        {
            if ((Float) this.get(player) < (Float) min)
            {
                this.set(player, min);
            }
            else if ((Float) this.get(player) > (Float) max)
            {
                this.set(player, max);
            }
        }
        else if (min instanceof Double)
        {
            if ((Double) this.get(player) < (Double) min)
            {
                this.set(player, min);
            }
            else if ((Double) this.get(player) > (Double) max)
            {
                this.set(player, max);
            }
        }
        else
        {
            throw new RuntimeException("Cannot clamp a non-numerical data type!");
        }
    }

    public void clampWithoutNotify(EntityPlayer player, T min, T max)
    {
        if (min instanceof Integer)
        {
            if ((Integer) this.get(player) < (Integer) min)
            {
                this.setWithoutNotify(player, min);
            }
            else if ((Integer) this.get(player) > (Integer) max)
            {
                this.setWithoutNotify(player, max);
            }
        }
        else if (min instanceof Float)
        {
            if ((Float) this.get(player) < (Float) min)
            {
                this.setWithoutNotify(player, min);
            }
            else if ((Float) this.get(player) > (Float) max)
            {
                this.setWithoutNotify(player, max);
            }
        }
        else if (min instanceof Double)
        {
            if ((Double) this.get(player) < (Double) min)
            {
                this.setWithoutNotify(player, min);
            }
            else if ((Double) this.get(player) > (Double) max)
            {
                this.setWithoutNotify(player, max);
            }
        }
        else
        {
            throw new RuntimeException("Cannot clamp a non-numerical data type!");
        }
    }

    public T get(EntityPlayer player)
    {
        TFPlayerData playerData = player.getCapability(TFCapabilities.PLAYER_DATA_CAP, null);

        if (playerData != null)
        {
            T value = playerData.getData(this);

            if (this == ALT_MODE)
            {
                if (!TFHelper.isTransformer(player) && (Integer) value != -1)
                {
                    playerData.putData(this, (T) Integer.valueOf(-1));
                    value = (T) Integer.valueOf(-1);
                }
            }

            return value;
        }

        return null;
    }

    public static NBTTagCompound writeToNBT(NBTTagCompound nbt, Map<TFData, Object> data)
    {
        NBTTagCompound compound = new NBTTagCompound();

        for (Map.Entry<TFData, Object> entry : data.entrySet())
        {
            if (entry.getKey().save)
            {
                Object value = entry.getValue();

                if (value instanceof Transformer)
                {
                    value = ((Transformer) value).getId();
                }

                entry.getKey().writeDataToNBT(compound, value);
            }
        }

        nbt.setTag("DataArray", compound);

        return nbt;
    }

    public static Map<TFData, Object> readFromNBT(NBTTagCompound nbt, Map<TFData, Object> data)
    {
        NBTTagCompound compound = nbt.getCompoundTag("DataArray");

        for (TFData<?> type : TFData.VALUES)
        {
            if (type.save)
            {
                Object obj = type.readDataFromNBT(compound);

                if (obj != null)
                {
                    if (type.defaultValue instanceof Transformer && obj instanceof Integer)
                    {
                        obj = TransformersAPI.REGISTRY.getObjectById((Integer) obj);
                    }

                    data.put(type, obj);
                }
            }
        }

        return data;
    }

    public NBTTagCompound writeDataToNBT(NBTTagCompound nbt, Object obj)
    {
        if (obj instanceof Integer)
        {
            nbt.setInteger(this.id, (Integer) obj);
        }
        else if (obj instanceof Float)
        {
            nbt.setFloat(this.id, (Float) obj);
        }
        else if (obj instanceof Double)
        {
            nbt.setDouble(this.id, (Double) obj);
        }
        else if (obj instanceof Boolean)
        {
            nbt.setBoolean(this.id, (Boolean) obj);
        }
        else if (obj instanceof String)
        {
            String s = (String) obj;

            if (!s.isEmpty())
            {
                nbt.setString(this.id, s);
            }
        }

        return nbt;
    }

    public Object readDataFromNBT(NBTTagCompound nbt)
    {
        NBTBase tag = nbt.getTag(this.id);

        if (tag instanceof NBTTagInt)
        {
            return ((NBTTagInt) tag).getInt();
        }
        else if (tag instanceof NBTTagFloat)
        {
            return ((NBTTagFloat) tag).getFloat();
        }
        else if (tag instanceof NBTTagDouble)
        {
            return ((NBTTagDouble) tag).getDouble();
        }
        else if (tag instanceof NBTTagByte)
        {
            return ((NBTTagByte) tag).getByte() == 1;
        }
        else if (tag instanceof NBTTagString)
        {
            return ((NBTTagString) tag).getString();
        }

        return null;
    }

    static
    {
        for (Field field : TFData.class.getFields())
        {
            String s = field.getType().getName();

            if (s.equals(TFData.class.getName()))
            {
                try
                {
                    TFData<?> data = (TFData<?>) field.get(null);
                    data.id = TFFormatHelper.getUnconventionalName(field.getName());
                    TFData.VALUES.add(data);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
