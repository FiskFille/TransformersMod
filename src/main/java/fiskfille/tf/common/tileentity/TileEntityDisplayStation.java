package fiskfille.tf.common.tileentity;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.item.ItemComponent;
import fiskfille.tf.common.item.ItemDisplayVehicle;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.network.MessageTileTrigger.ITileDataCallback;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;

public class TileEntityDisplayStation extends TileEntityContainer implements IMultiTile, ITileDataCallback
{
    public boolean isRedstonePowered = false;
    
    public final GameProfile username;
    public EntityPlayer fakePlayer;

    public TileEntityDisplayStation()
    {
        username = new GameProfile(UUID.randomUUID(), String.format("[%s]", new DimensionalCoords(this)));
    }
    
    @Override
    public void updateEntity()
    {
        if (getBlockMetadata() < 4)
        {
            if (worldObj.isRemote)
            {
                clientTick();
            }
            else
            {
                fakePlayer = FakePlayerFactory.get((WorldServer) worldObj, username);
            }
            
            if (fakePlayer != null)
            {
                ++fakePlayer.ticksExisted;
                fakePlayer.motionY = 1.25E-85;
                
                for (int i = 0; i < 4; ++i)
                {
                    fakePlayer.setCurrentItemOrArmor(4 - i, getStackInSlot(i));
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void clientTick()
    {
        if (fakePlayer == null)
        {
            Minecraft mc = Minecraft.getMinecraft();

            if (mc != null && mc.playerController != null && getWorldObj() != null)
            {
                EntityClientPlayerMP player = new EntityClientPlayerMP(mc, getWorldObj(), mc.getSession(), mc.getNetHandler(), new StatFileWriter())
                {
                    @Override
                    public boolean isInvisibleToPlayer(EntityPlayer player)
                    {
                        return true;
                    }
                };
                
                player.movementInput = new MovementInputFromOptions(mc.gameSettings);
                fakePlayer = player;
            }
        }
    }
    
    public boolean canTransform()
    {
        ItemStack vehicle = getStackInSlot(6);
        
        if (getStackInSlot(6) != null)
        {
            for (int i = 0; i < 4; ++i)
            {
                if (getStackInSlot(i) != null)
                {
                    return false;
                }
            }
            
            return true;
        }
        
        return TFHelper.isTransformer(getStackInSlot(0), getStackInSlot(1), getStackInSlot(2), getStackInSlot(3));
    }

    public boolean setColor(int primaryColor, int secondaryColor)
    {
        ItemStack head = getStackInSlot(0);
        ItemStack chest = getStackInSlot(1);
        ItemStack legs = getStackInSlot(2);
        ItemStack feet = getStackInSlot(3);

        if (head != null && chest != null && legs != null && feet != null)
        {
            if (primaryColor == secondaryColor && primaryColor == Integer.MIN_VALUE)
            {
                TFArmorDyeHelper.removeColor(head);
                TFArmorDyeHelper.removeColor(chest);
                TFArmorDyeHelper.removeColor(legs);
                TFArmorDyeHelper.removeColor(feet);
            }
            else
            {
                TFArmorDyeHelper.setPrimaryColor(head, primaryColor);
                TFArmorDyeHelper.setPrimaryColor(chest, primaryColor);
                TFArmorDyeHelper.setPrimaryColor(legs, primaryColor);
                TFArmorDyeHelper.setPrimaryColor(feet, primaryColor);
                TFArmorDyeHelper.setSecondaryColor(head, secondaryColor);
                TFArmorDyeHelper.setSecondaryColor(chest, secondaryColor);
                TFArmorDyeHelper.setSecondaryColor(legs, secondaryColor);
                TFArmorDyeHelper.setSecondaryColor(feet, secondaryColor);
            }

            return true;
        }

        return false;
    }
    
    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);
        isRedstonePowered = nbt.getBoolean("Powered");
    }
    
    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        super.writeCustomNBT(nbt);
        nbt.setBoolean("Powered", isRedstonePowered);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return super.getRenderBoundingBox().addCoord(0, 1, 0).expand(0.5F, 0.5F, 0.5F);
    }
    
    @Override
    public int getSizeInventory()
    {
        return 7;
    }

    @Override
    public String getInventoryName()
    {
        return "gui.display_station";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return getStackInSlot(slot) == null && (slot < 4 ? stack.getItem() instanceof ItemTransformerArmor && stack.getItem().isValidArmor(stack, slot, fakePlayer) : slot == 6 ? stack.getItem() == TFItems.displayVehicle : stack.getItem() instanceof ItemComponent);
    }

    @Override
    public int[] getBaseOffsets(int metadata)
    {
        return new int[] {0, -metadata / 4, 0};
    }

    @Override
    public void receive(EntityPlayer player, int action)
    {
        if (action == 0)
        {
            if (canTransform())
            {
                ItemStack vehicle = getStackInSlot(6);
                
                if (vehicle != null)
                {
                    if (!vehicle.hasTagCompound())
                    {
                        vehicle.setTagCompound(new NBTTagCompound());
                    }

                    ItemStack[] armorFromNBT = ItemDisplayVehicle.getArmorFromNBT(vehicle);

                    if (armorFromNBT == null)
                    {
                        ItemDisplayVehicle.setNBTData(vehicle);
                        armorFromNBT = ItemDisplayVehicle.getArmorFromNBT(vehicle);
                    }

                    for (int i = 0; i < armorFromNBT.length; ++i)
                    {
                        setInventorySlotContents(i, armorFromNBT[i]);
                    }

                    setInventorySlotContents(6, null);
                }
                else
                {
                    ItemStack itemstack = new ItemStack(TFItems.displayVehicle, 1, TransformersAPI.getTransformers().indexOf(TFHelper.getTransformer(getStackInSlot(0))));
                    ItemStack[] itemstacks = {getStackInSlot(0), getStackInSlot(1), getStackInSlot(2), getStackInSlot(3)};
                    NBTTagList itemsList = new NBTTagList();

                    for (int i = 0; i < itemstacks.length; ++i)
                    {
                        if (itemstacks[i] != null)
                        {
                            NBTTagCompound itemTag = new NBTTagCompound();
                            itemTag.setByte("Slot", (byte) i);
                            itemstacks[i].writeToNBT(itemTag);
                            itemsList.appendTag(itemTag);
                        }
                    }

                    itemstack.setTagCompound(new NBTTagCompound());
                    itemstack.getTagCompound().setTag("Items", itemsList);

                    for (int i = 0; i < 4; ++i)
                    {
                        setInventorySlotContents(i, null);
                    }

                    setInventorySlotContents(6, itemstack);
                }
            }
        }
        
        markDirty();
    }
}
