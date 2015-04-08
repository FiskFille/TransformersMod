package fiskfille.tf.common.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import fiskfille.tf.common.entity.ai.EntityAITransform;

public class EntityTransformer extends EntityCreature
{
    private boolean transformed;
    private int transformationTimer;
    
    public EntityTransformer(World world)
    {
        super(world);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIWander(this, 0.45F));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.5D, true));
        this.tasks.addTask(1, new EntityAITransform(this, EntityVillager.class, 20)); //Robots in disguise!
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 4F, 10F));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityTransformiumSeed.class, 100F, 0.6F, 0.6F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, false));
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if(!transformed)
        {
            float damage = 10;
            
            return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
        }
        
        return false;
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
    }
    
    @Override
    public void entityInit()
    {
        super.entityInit();
        
        this.dataWatcher.addObject(20, transformedByte());
    }
    
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }
    
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        
        if(worldObj.isRemote)
        {
            this.transformed = this.dataWatcher.getWatchableObjectByte(20) == 1;
        }
        
        if(transformed)
        {
            this.stepHeight = 1.0F;
        }
        else
        {
            this.stepHeight = 0.5F;
        }
        
        if(transformed && transformationTimer > 0)
            transformationTimer-=2;
        else if(!transformed && transformationTimer < 20)
            transformationTimer+=2;
        
        this.setSize(0.8F, (transformationTimer * 0.05F) + 1F);
        
        if(!worldObj.isRemote)
        {
            this.dataWatcher.updateObject(20, transformedByte());
        }
    }
    
    private byte transformedByte()
    {
        return transformed ? (byte) 1 : (byte) 0;
    }
    
    public boolean isTransformed()
    {
        return transformed;
    }
    
    public int getTransformationTimer()
    {
        return transformationTimer;
    }
    
    public void setInVehicleMode(boolean vehicleMode)
    {
        this.transformed = vehicleMode;
    }
    
    @Override
    public ItemStack getHeldItem()
    {
        return null;
        //return new ItemStack(TFItems.purgesKatana);
    }
    
    @Override
    public ItemStack getEquipmentInSlot(int slot)
    {
        return null;
    }
    
    @Override
    public void setCurrentItemOrArmor(int slot, ItemStack stack)
    {
    }
    
    @Override
    public ItemStack[] getLastActiveItems()
    {
        return new ItemStack[]{};
    }
    
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        transformed = nbt.getBoolean("Transformed");
        transformationTimer = transformed ? 0 : 20;
    }
    
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean("Transformed", transformed);
    }
}
