package coda.goatskitchen.common.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.blocks.BlenderBlock;
import coda.goatskitchen.common.blocks.LawyersWigMushroomBlock;
import coda.goatskitchen.common.blocks.PineapplePlantBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<Block> BLENDER = REGISTER.register("blender", () -> new BlenderBlock(AbstractBlock.Properties.of(Material.GLASS).harvestTool(ToolType.AXE).strength(1.5f).noOcclusion().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> PINEAPPLE = REGISTER.register("pineapple", () -> new PineapplePlantBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> LAWYERS_WIG_MUSHROOM = REGISTER.register("lawyers_wig_mushroom", () -> new LawyersWigMushroomBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_WHITE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LAWYERS_WIG_MUSHROOM_BLOCK = REGISTER.register("lawyers_wig_mushroom_block", () -> new HugeMushroomBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> POTTED_LAWYERS_WIG_MUSHROOM = REGISTER.register("potted_lawyers_wig_mushroom", () -> new FlowerPotBlock(LAWYERS_WIG_MUSHROOM.get(), AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak()));
}