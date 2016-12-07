//package fiskfille.tf.common.transformer;
//
//import fiskfille.tf.common.achievement.TFAchievements;
//import fiskfille.tf.common.block.TFBlocks;
//import fiskfille.tf.common.entity.EntityLaser;
//import fiskfille.tf.common.item.TFItems;
//import fiskfille.tf.common.transformer.base.TransformerJet;
//import fiskfille.tf.helper.TFVectorHelper;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.Item;
//import net.minecraft.util.Vec3;
//
//import java.util.Random;
//
///**
// * @author gegy1000
// */
//public class TransformerWarden extends TransformerJet
//{
//    public TransformerWarden()
//    {
//        super("Warden");
//    }
//
//    @Override
//    public Item getHelmet()
//    {
//        return TFItems.wardenHelmet;
//    }
//
//    @Override
//    public Item getChestplate()
//    {
//        return TFItems.wardenChestplate;
//    }
//
//    @Override
//    public Item getLeggings()
//    {
//        return TFItems.wardenLeggings;
//    }
//
//    @Override
//    public Item getBoots()
//    {
//        return TFItems.wardenBoots;
//    }
//
//    @Override
//    public void tick(EntityPlayer player, int timer)
//    {
//        player.addStat(TFAchievements.warden, 1);
//    }
//
//    @Override
//    public Item getShootItem(int altMode)
//    {
//        return Item.getItemFromBlock(TFBlocks.energonCube);
//    }
//
//    @Override
//    public Entity getShootEntity(EntityPlayer player, int altMode)
//    {
//        EntityLaser entityLaser = new EntityLaser(player.worldObj, player);
//        entityLaser.posY++;
//        return entityLaser;
//    }
//
//    @Override
//    public String getShootSound(int altMode)
//    {
//        return "random.fizz";
//    }
//
//    @Override
//    public float getShootVolume(int altMode)
//    {
//        return 0.3F;
//    }
//
//    @Override
//    public int getShots(int altMode)
//    {
//        return 64;
//    }
//
//    @Override
//    public boolean hasRapidFire(int altMode)
//    {
//        return true;
//    }
//
//    @Override
//    public int getAltModeCount()
//    {
//        return 2;
//    }
//
//    @Override
//    public void doNitroParticles(EntityPlayer player, int altMode)
//    {
//        if (altMode == 0)
//        {
//            for (int i = 0; i < 4; ++i)
//            {
//                Vec3 side = TFVectorHelper.getBackSideCoords(player, i % 2 == 0 ? 0.15F : 0.25, i < 2, -2.5, true);
//                Random rand = new Random();
//
//                if (player != Minecraft.getMinecraft().thePlayer)
//                {
//                    side.yCoord += 0.8F;
//                }
//
//                player.worldObj.spawnParticle("flame", side.xCoord, side.yCoord - (i % 2 == 0 ? 0.3F : 0.45F), side.zCoord, rand.nextFloat() / 20, -0.2F + rand.nextFloat() / 20, rand.nextFloat() / 20);
//            }
//        }
//    }
//
//    @Override
//    public float getVehicleCameraYOffset(EntityPlayer player, int altMode)
//    {
//        if (altMode == 1)
//        {
//            return -1;
//        }
//
//        return super.getVehicleCameraYOffset(player, altMode);
//    }
//
//    @Override
//    public float getCameraYOffset(EntityPlayer player, int altMode)
//    {
//        if (altMode == 1)
//        {
//            return -1;
//        }
//
//        return super.getCameraYOffset(player, altMode);
//    }
//}