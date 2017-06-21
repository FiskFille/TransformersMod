package fiskfille.tf.common.block;

import fiskfille.tf.TransformersMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class TFBlocks
{
    public static final List<Block> REGISTERED_BLOCKS = new ArrayList<>();

    public static final Block TRANSFORMIUM_ORE = new BlockBasic(Material.ROCK).setHarvestLvl("pickaxe", 2).setHardness(10.0F).setResistance(1000.0F);

    @SubscribeEvent
    public static void register(RegistryEvent<Block> event)
    {
        TFBlocks.register(TRANSFORMIUM_ORE, new ResourceLocation(TransformersMod.MODID, "transformium_ore"));
    }

    private static void register(Block block, ResourceLocation identifier)
    {
        block.setUnlocalizedName(identifier.getResourcePath());

        ItemBlock item = new ItemBlock(block);

        GameRegistry.register(block, identifier);
        GameRegistry.register(item, identifier);

        REGISTERED_BLOCKS.add(block);
    }
}
