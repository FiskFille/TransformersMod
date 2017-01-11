package fiskfille.tf.common.groundbridge;

import cpw.mods.fml.common.network.ByteBufUtils;
import fiskfille.tf.common.item.ItemCSD;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RemoteData
{
    private int x;
    private int y;
    private int z;

    private int sourceDimension;
    private int destinationDimension;
    private int destinationDimensionIndex;
    private float energy;
    private float energyUsage;
    private float maxEnergy;
    private int destinationX;
    private int destinationY;
    private int destinationZ;
    private int direction;
    private boolean activationLeverState;
    private List<DataCore> upgrades;
    private List<GroundBridgeError> errors;
    private ItemStack[] inventory;

    public RemoteData(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        sourceDimension = buf.readInt();
        destinationDimension = buf.readInt();
        destinationDimensionIndex = buf.readInt();
        energy = buf.readFloat();
        energyUsage = buf.readFloat();
        maxEnergy = buf.readFloat();
        destinationX = buf.readInt();
        destinationY = buf.readInt();
        destinationZ = buf.readInt();
        direction = buf.readByte() & 0xFF;
        activationLeverState = buf.readBoolean();

        upgrades = new ArrayList<DataCore>();

        int upgradeCount = buf.readByte() & 0xFF;

        for (int i = 0; i < upgradeCount; i++)
        {
            int index = buf.readByte() & 0xFF;

            DataCore core = DataCore.get(index);

            if (core != null)
            {
                upgrades.add(core);
            }
        }

        errors = new ArrayList<GroundBridgeError>();

        int errorCount = buf.readByte() & 0xFF;

        for (int i = 0; i < errorCount; i++)
        {
            try
            {
                GroundBridgeError error = GroundBridgeError.values()[buf.readByte()];
                errors.add(error);
            }
            catch (Exception e)
            {
            }
        }

        this.inventory = new ItemStack[3];

        for (int i = 0; i < 3; i++)
        {
            boolean exists = buf.readBoolean();

            if (exists)
            {
                this.inventory[i] = ByteBufUtils.readItemStack(buf);
            }
        }
    }

    public RemoteData(TileEntityControlPanel panel)
    {
        this.x = panel.xCoord;
        this.y = panel.yCoord;
        this.z = panel.zCoord;
        this.sourceDimension = panel.getWorldObj().provider.dimensionId;
        this.destinationDimension = panel.getDestDimensionID();
        this.destinationDimensionIndex = panel.destDimIndex;
        this.energy = panel.getEnergy();
        this.energyUsage = panel.getEnergyUsage();
        this.maxEnergy = panel.getMaxEnergy();
        this.destinationX = panel.destX;
        this.destinationY = panel.prevDestY;
        this.destinationZ = panel.destZ;
        this.direction = panel.portalDirection;
        this.activationLeverState = panel.activationLeverState;
        this.upgrades = panel.getUpgrades();
        this.errors = panel.errors;
        this.inventory = panel.getItemStacks();
    }

    public void serialize(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(sourceDimension);
        buf.writeInt(destinationDimension);
        buf.writeInt(destinationDimensionIndex);
        buf.writeFloat(energy);
        buf.writeFloat(energyUsage);
        buf.writeFloat(maxEnergy);
        buf.writeInt(destinationX);
        buf.writeInt(destinationY);
        buf.writeInt(destinationZ);
        buf.writeByte(direction & 0xFF);
        buf.writeBoolean(activationLeverState);

        buf.writeByte(upgrades.size() & 0xFF);

        for (DataCore core : upgrades)
        {
            buf.writeByte(core.index & 0xFF);
        }

        buf.writeByte(errors.size() & 0xFF);

        for (GroundBridgeError error : errors)
        {
            buf.writeByte(error.ordinal() & 0xFF);
        }

        for (int i = 0; i < inventory.length; i++)
        {
            ItemStack stack = inventory[i];

            if (stack != null)
            {
                buf.writeBoolean(true);
                ByteBufUtils.writeItemStack(buf, stack);
            }
            else
            {
                buf.writeBoolean(false);
            }
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    public float getEnergy()
    {
        return energy;
    }

    public float getEnergyUsage()
    {
        return energyUsage;
    }

    public float getMaxEnergy()
    {
        return maxEnergy;
    }

    public int getDestinationDimension()
    {
        return destinationDimension;
    }

    public int getDestinationDimensionIndex()
    {
        return destinationDimensionIndex;
    }

    public int getDestinationX()
    {
        return destinationX;
    }

    public int getDestinationY()
    {
        return destinationY;
    }

    public int getDestinationZ()
    {
        return destinationZ;
    }

    public int getDirection()
    {
        return direction;
    }

    public int getSourceDimension()
    {
        return sourceDimension;
    }

    public List<DataCore> getUpgrades()
    {
        return upgrades;
    }

    public List<GroundBridgeError> getErrors()
    {
        return errors;
    }

    public boolean getActivationLeverState()
    {
        return activationLeverState;
    }

    public void updateEnergy()
    {
        energy += energyUsage;

        if (energy < 0)
        {
            energy = 0;
        }

        if (energy > maxEnergy)
        {
            energy = maxEnergy;
        }
    }

    public void setDestinationX(int destinationX)
    {
        this.destinationX = destinationX;
    }

    public void setDestinationY(int destinationY)
    {
        this.destinationY = destinationY;
    }

    public void setDestinationZ(int destinationZ)
    {
        this.destinationZ = destinationZ;
    }

    public void setDestinationDimension(int destinationDimension)
    {
        this.destinationDimension = destinationDimension;
    }

    public void setDestinationDimensionIndex(int destinationDimensionIndex)
    {
        this.destinationDimensionIndex = destinationDimensionIndex;
    }

    public void setActivationLeverState(boolean activationLeverState)
    {
        this.activationLeverState = activationLeverState;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public void setEnergy(float energy)
    {
        this.energy = energy;
    }

    public void setEnergyUsage(float energyUsage)
    {
        this.energyUsage = energyUsage;
    }

    public void setErrors(List<GroundBridgeError> errors)
    {
        this.errors = errors;
    }

    public void setMaxEnergy(float maxEnergy)
    {
        this.maxEnergy = maxEnergy;
    }

    public void setSourceDimension(int sourceDimension)
    {
        this.sourceDimension = sourceDimension;
    }

    public void setUpgrades(List<DataCore> upgrades)
    {
        this.upgrades = upgrades;
    }

    public void setDestination(ItemCSD.DimensionalCoords destination)
    {
        this.destinationX = destination.posX;
        this.destinationY = destination.posY;
        this.destinationZ = destination.posZ;
        this.destinationDimensionIndex = destination.dimension;
    }

    public ItemStack[] getInventory()
    {
        return inventory;
    }

    public boolean hasUpgrade(DataCore core)
    {
        return this.upgrades.contains(core);
    }

    public ItemStack getStackInSlot(int slot)
    {
        return inventory[slot];
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof RemoteData)
        {
            RemoteData data = (RemoteData) obj;
            boolean sourceEquals = data.x == x && data.y == y && data.z == z && data.sourceDimension == sourceDimension;
            boolean destinationEquals = data.destinationDimension == destinationDimension && data.destinationDimensionIndex == destinationDimensionIndex && data.destinationX == destinationX && data.destinationY == destinationY && data.destinationZ == destinationZ;
            boolean energyEquals = data.maxEnergy == maxEnergy && energyUsage == data.energyUsage;
            boolean errorsEqual = data.errors.size() == errors.size();

            if (errorsEqual)
            {
                for (int i = 0; i < data.errors.size(); i++)
                {
                    if (data.errors.get(i) != errors.get(i))
                    {
                        errorsEqual = false;
                        break;
                    }
                }
            }

            boolean coresEqual = data.upgrades.size() == upgrades.size();

            if (coresEqual)
            {
                for (int i = 0; i < data.upgrades.size(); i++)
                {
                    if (data.upgrades.get(i).index != upgrades.get(i).index)
                    {
                        coresEqual = false;
                        break;
                    }
                }
            }

            return coresEqual && errorsEqual && sourceEquals && destinationEquals && data.activationLeverState == activationLeverState && data.direction == direction && energyEquals;
        }

        return false;
    }
}