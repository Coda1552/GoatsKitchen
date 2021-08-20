package coda.goatskitchen.common.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.blocks.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<Block> BLENDER = REGISTER.register("blender", () -> new BlenderBlock(BlockBehaviour.Properties.of(Material.GLASS).harvestTool(ToolType.AXE).strength(1.5f).noOcclusion().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> PINEAPPLE = REGISTER.register("pineapple", () -> new PineapplePlantBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> LAWYERS_WIG_MUSHROOM = REGISTER.register("lawyers_wig_mushroom", () -> new LawyersWigMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_WHITE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LAWYERS_WIG_MUSHROOM_BLOCK = REGISTER.register("lawyers_wig_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> POTTED_LAWYERS_WIG_MUSHROOM = REGISTER.register("potted_lawyers_wig_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LAWYERS_WIG_MUSHROOM, BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak()));

    public static final RegistryObject<Block> CHILI_PEPPER_PLANT_TOP = REGISTER.register("chili_pepper_plant_top", () -> new ChiliPepperTopBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.NETHER).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES)));
    public static final RegistryObject<Block> CHILI_PEPPER_PLANT = REGISTER.register("chili_pepper_plant", () -> new ChiliPepperBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.NETHER).noCollission().instabreak().sound(SoundType.WEEPING_VINES)));
}