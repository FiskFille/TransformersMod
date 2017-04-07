package fiskfille.tf.common.data;

import static fiskfille.tf.common.data.TFPredicates.and;
import static fiskfille.tf.common.data.TFPredicates.hasStealthForce;
import static fiskfille.tf.common.data.TFPredicates.isInVehicleMode;
import static fiskfille.tf.common.data.TFPredicates.isTransformer;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.event.PlayerTransformEvent;
import fiskfille.tf.common.network.MessagePlayerData;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFFormatHelper;
import fiskfille.tf.helper.TFHelper;

public class TFData<T>
{
    public static final TFData<Integer> ALT_MODE = new TFData<Integer>(-1, isTransformer());
    public static final TFData<Boolean> STEALTH_FORCE = new TFData<Boolean>(false, and(isInVehicleMode(), hasStealthForce()));
    public static final TFData<Float> TRANSFORM_PROGRESS = new TFData<Float>(0.0F, isTransformer());
    public static final TFData<Float> STEALTH_FORCE_PROGRESS = new TFData<Float>(0.0F, isInVehicleMode());
    public static final TFData<Double> FORWARD_VELOCITY = new TFData<Double>(0.0D, Predicates.<EntityPlayer> alwaysTrue());
    public static final TFData<Double> HORIZONTAL_VELOCITY = new TFData<Double>(0.0D, Predicates.<EntityPlayer> alwaysTrue());
    public static final TFData<Boolean> BOOSTING = new TFData<Boolean>(false, Predicates.<EntityPlayer> alwaysTrue());
    public static final TFData<Float> NITRO = new TFData<Float>(1.0F, Predicates.<EntityPlayer> alwaysTrue());

    public static final TFData<Integer> PREV_ALT_MODE = new TFDataPrev(ALT_MODE);
    public static final TFData<Float> PREV_TRANSFORM_PROGRESS = new TFData<Float>(0.0F, isTransformer());
    public static final TFData<Float> PREV_STEALTH_FORCE_PROGRESS = new TFData<Float>(0.0F, isInVehicleMode());
    public static final TFData<Transformer> PREV_TRANSFORMER = new TFData<Transformer>(null, Predicates.<EntityPlayer> alwaysTrue());
    public static final TFData<Float> PREV_NITRO = new TFData<Float>(1.0F, Predicates.<EntityPlayer> alwaysTrue());

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
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                return value == null ? data.get(input) == value : data.get(input) == value || data.get(input).equals(value);
            }
        };
    }

    public boolean set(EntityPlayer player, T value)
    {
        if (get(player) == null || !get(player).equals(value))
        {
//            if (canSet.apply(player)) // TODO: Gegy-proof
            {
                if (setWithoutNotify(player, value))
                {
                    if (player.worldObj.isRemote)
                    {
                        TFNetworkManager.networkWrapper.sendToServer(new MessagePlayerData(player, this, value));
                    }
                    else
                    {
                        TFNetworkManager.networkWrapper.sendToDimension(new MessagePlayerData(player, this, value), player.dimension);
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
        if (get(player) == null || !get(player).equals(value))
        {
//            if (canSet.apply(player))
            {
                if (this == ALT_MODE)
                {
                    if (!MinecraftForge.EVENT_BUS.post(new PlayerTransformEvent(player, TFHelper.getTransformer(player), (Integer) value, STEALTH_FORCE.get(player))))
                    {
                        player.triggerAchievement(TFAchievements.transform);

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
                        TFPlayerData.getData(player).putData(type, get(player));
                    }
                }

                TFPlayerData.getData(player).putData(this, value);

                return true;
            }
//            else
//            {
//                // reset to default
//            }
        }

        return false;
    }

    public void incr(EntityPlayer player, T value)
    {
        if (value instanceof Integer)
        {
            Integer i = (Integer) get(player) + (Integer) value;
            set(player, (T) i);
        }
        else if (value instanceof Float)
        {
            Float f = (Float) get(player) + (Float) value;
            set(player, (T) f);
        }
        else if (value instanceof Double)
        {
            Double d = (Double) get(player) + (Double) value;
            set(player, (T) d);
        }
        else if (value instanceof String)
        {
            String s = (String) get(player) + (String) value;
            set(player, (T) s);
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
            Integer i = (Integer) get(player) + (Integer) value;
            setWithoutNotify(player, (T) i);
        }
        else if (value instanceof Float)
        {
            Float f = (Float) get(player) + (Float) value;
            setWithoutNotify(player, (T) f);
        }
        else if (value instanceof Double)
        {
            Double d = (Double) get(player) + (Double) value;
            setWithoutNotify(player, (T) d);
        }
        else if (value instanceof String)
        {
            String s = (String) get(player) + (String) value;
            setWithoutNotify(player, (T) s);
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
            if ((Integer) get(player) < (Integer) min)
            {
                set(player, min);
            }
            else if ((Integer) get(player) > (Integer) max)
            {
                set(player, max);
            }
        }
        else if (min instanceof Float)
        {
            if ((Float) get(player) < (Float) min)
            {
                set(player, min);
            }
            else if ((Float) get(player) > (Float) max)
            {
                set(player, max);
            }
        }
        else if (min instanceof Double)
        {
            if ((Double) get(player) < (Double) min)
            {
                set(player, min);
            }
            else if ((Double) get(player) > (Double) max)
            {
                set(player, max);
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
            if ((Integer) get(player) < (Integer) min)
            {
                setWithoutNotify(player, min);
            }
            else if ((Integer) get(player) > (Integer) max)
            {
                setWithoutNotify(player, max);
            }
        }
        else if (min instanceof Float)
        {
            if ((Float) get(player) < (Float) min)
            {
                setWithoutNotify(player, min);
            }
            else if ((Float) get(player) > (Float) max)
            {
                setWithoutNotify(player, max);
            }
        }
        else if (min instanceof Double)
        {
            if ((Double) get(player) < (Double) min)
            {
                setWithoutNotify(player, min);
            }
            else if ((Double) get(player) > (Double) max)
            {
                setWithoutNotify(player, max);
            }
        }
        else
        {
            throw new RuntimeException("Cannot clamp a non-numerical data type!");
        }
    }

    public T get(EntityPlayer player)
    {
        T value = TFPlayerData.getData(player).getData(this);

        if (this == ALT_MODE)
        {
            if (!TFHelper.isTransformer(player) && (Integer) value != -1)
            {
                TFPlayerData.getData(player).putData(this, (T) Integer.valueOf(-1));
                value = (T) Integer.valueOf(-1);
            }
        }

        return value;
    }

    public static NBTTagCompound writeToNBT(NBTTagCompound nbt, Map<TFData, Object> data)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        for (Map.Entry<TFData, Object> e : data.entrySet())
        {
            if (e.getKey().save)
            {
                Object obj = e.getValue();

                if (obj instanceof Transformer)
                {
                    obj = ((Transformer) obj).getName();
                }

                e.getKey().writeDataToNBT(nbttagcompound, obj);
            }
        }

        nbt.setTag("DataArray", nbttagcompound);

        return nbt;
    }

    public static Map<TFData, Object> readFromNBT(NBTTagCompound nbt, Map<TFData, Object> data)
    {
        NBTTagCompound nbttagcompound = nbt.getCompoundTag("DataArray");

        for (TFData<?> type : TFData.VALUES)
        {
            if (type.save)
            {
                Object obj = type.readDataFromNBT(nbttagcompound);

                if (obj != null)
                {
                    if (type.defaultValue instanceof Transformer && obj instanceof String)
                    {
                        obj = TransformersAPI.getTransformerByName((String) obj);
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
            nbt.setInteger(id, (Integer) obj);
        }
        else if (obj instanceof Float)
        {
            nbt.setFloat(id, (Float) obj);
        }
        else if (obj instanceof Double)
        {
            nbt.setDouble(id, (Double) obj);
        }
        else if (obj instanceof Boolean)
        {
            nbt.setBoolean(id, (Boolean) obj);
        }
        else if (obj instanceof String)
        {
            String s = (String) obj;
            if (s != null && !s.isEmpty())
            {
                nbt.setString(id, s);
            }
        }

        return nbt;
    }

    public Object readDataFromNBT(NBTTagCompound nbt)
    {
        NBTBase tag = nbt.getTag(id);

        if (tag instanceof NBTTagInt)
        {
            return ((NBTTagInt) tag).func_150287_d();
        }
        else if (tag instanceof NBTTagFloat)
        {
            return ((NBTTagFloat) tag).func_150288_h();
        }
        else if (tag instanceof NBTTagDouble)
        {
            return ((NBTTagDouble) tag).func_150286_g();
        }
        else if (tag instanceof NBTTagByte)
        {
            return ((NBTTagByte) tag).func_150290_f() == 1;
        }
        else if (tag instanceof NBTTagString)
        {
            return ((NBTTagString) tag).func_150285_a_();
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
