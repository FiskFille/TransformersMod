package fiskfille.tf.common.tileentity;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.Maps;

import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.data.tile.TileDataEnergyContainer;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.energon.power.IEnergyContainer;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.helper.TFTileHelper;

public class TileEntityIsoCondenser extends TileEntityMachine implements IEnergyContainer
{
    public TileDataEnergyContainer data = new TileDataEnergyContainer(8000);
    public Map<ForgeDirection, Block> providers = Maps.newHashMap();
    public Map<ForgeDirection, Float> animationTimer = Maps.newHashMap();
    public Map<ForgeDirection, Float> prevAnimationTimer = Maps.newHashMap();

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        for (Map.Entry<ForgeDirection, Float> e : animationTimer.entrySet())
        {
            prevAnimationTimer.put(e.getKey(), e.getValue());
        }

        if (!data.isInitialized())
        {
            data.initialize(this);
        }

        providers.clear();

        for (ForgeDirection dir : new ForgeDirection[] {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST})
        {
            Block block = worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
            float f = animationTimer.get(dir) == null ? 0 : animationTimer.get(dir);
            
            boolean active = false;
            
            if (block instanceof IEnergon && ((IEnergon) block).getMass() > 0)
            {
                providers.put(dir, block);
                active = canActivate();
            }
            
            if (active)
            {
                animationTimer.put(dir, MathHelper.clamp_float(f + 1F / 10, 0, 1));
            }
            else
            {
                animationTimer.put(dir, MathHelper.clamp_float(f - 1F / 10, 0, 1));
            }
        }

        if (!worldObj.isRemote)
        {
            if (canActivate())
            {
                for (Map.Entry<ForgeDirection, Block> e : providers.entrySet())
                {
                    IEnergon ienergon = (IEnergon) e.getValue();
                    receiveEnergy(getGenerationRate(ienergon.getMass()), false);
                }
            }

            data.serverTick();
        }

        TileData prevData = TFTileHelper.getTileData(new DimensionalCoords(this));

        if (prevData instanceof TileDataEnergyContainer)
        {
            data = new TileDataEnergyContainer((TileDataEnergyContainer) prevData);
        }
    }

    public float getGenerationRate(int mass)
    {
        if (!canActivate())
        {
            return 0;
        }
        
        return (float) mass / Energon.CRYSTAL_BLOCK * 0.1F;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
            data.kill();
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return super.getRenderBoundingBox().expand(1, 1, 1);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);
        
        if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.storage.readFromNBT(config);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        super.writeCustomNBT(nbt);
        
        if (data.storage.getEnergy() > 0)
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.storage.writeToNBT(config);
            nbt.setTag("ConfigDataTF", config);
        }
    }

    @Override
    public float receiveEnergy(float amount, boolean simulate)
    {
        return data.storage.add(amount, simulate);
    }

    @Override
    public float extractEnergy(float amount, boolean simulate)
    {
        return data.storage.remove(amount, simulate);
    }

    @Override
    public float getEnergy()
    {
        return data.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return data.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return data.storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return data.storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        data.storage.setUsage(usage);
    }
}
